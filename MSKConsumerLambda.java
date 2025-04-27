import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.MSKEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MSKConsumerLambda implements RequestHandler<MSKEvent, Void> {

    private static final Logger logger = LoggerFactory.getLogger(MSKConsumerLambda.class);
    private static final String BOOTSTRAP_SERVERS = System.getenv("BOOTSTRAP_SERVERS");
    private static final String TOPIC_NAME = System.getenv("TOPIC_NAME");
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
    private static final String REGEX_PATTERN_STRING = System.getenv("REGEX_PATTERN");
    private static final Pattern PATTERN = Pattern.compile(REGEX_PATTERN_STRING);

    @Override
    public Void handleRequest(MSKEvent event, Context context) {
        logger.info("Received MSK event: {}", event);

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "lambda-consumer-group"); // Important for tracking consumption
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest"); // Or "earliest" depending on your needs

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singletonList(TOPIC_NAME));

            for (MSKEvent.MSKRecord record : event.getRecords().get(event.getTops().keySet().iterator().next())) {
                String messageValue = new String(record.getValue());
                logger.info("Processing message from partition {} offset {}: {}", record.getPartition(), record.getOffset(), messageValue);

                Matcher matcher = PATTERN.matcher(messageValue);
                if (matcher.find()) {
                    String matchedGroup = matcher.group(); // Or process specific groups
                    storeInDatabase(matchedGroup);
                }
            }
            consumer.commitSync(); // Important to commit offsets
        } catch (Exception e) {
            logger.error("Error processing MSK event: {}", e.getMessage(), e);
            throw new RuntimeException(e); // Or handle the exception as needed
        }

        return null;
    }

    private void storeInDatabase(String matchedText) throws SQLException {
        String sql = "INSERT INTO your_table_name (matched_data, processed_at) VALUES (?, NOW())";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, matchedText);
            int affectedRows = pstmt.executeUpdate();
            logger.info("Inserted {} row(s) into the database.", affectedRows);
        }
    }
}