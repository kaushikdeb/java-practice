import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SlidingWindowStringArr {

    static int minElementsContainingAll(String[] largeArr, String[] smallArr){
        int minMatchingLen = Integer.MAX_VALUE;
        int largeArrLen = largeArr.length;
        int smallArrLen = smallArr.length;

        Map<String, Integer> targetMap = new HashMap<String, Integer>();
        Map<String, Integer> windowMap = new HashMap<String, Integer>();
        for(String s: smallArr){
            targetMap.put(s, targetMap.getOrDefault(s, 0)+1);
        }

        int right = 0;
        int left = 0;
        while(right < largeArrLen){
            String elementAtRight = largeArr[right];
            windowMap.put(elementAtRight, windowMap.getOrDefault(elementAtRight, 0)+1);

            // if()

        }

        return minMatchingLen;
    }

    public static void main(String[] args) {
        String[] largeArray1 = {"apple", "banana", "orange", "grape", "kiwi", "banana"};
        // String[] smallArray1 = {"banana", "orange"};
        // int result1 = minElementsContainingAll(largeArray1, smallArray1);
        // System.out.println("Minimum elements for smallArray1: " + result1); // Output: 2

        // String[] largeArray2 = {"a", "b", "c", "a", "b", "c", "d"};
        // String[] smallArray2 = {"a", "b", "c"};
        // int result2 = minElementsContainingAll(largeArray2, smallArray2);
        // System.out.println("Minimum elements for smallArray2: " + result2); // Output: 3

        // String[] largeArray3 = {"x", "y", "z"};
        // String[] smallArray3 = {"a", "b"};
        // int result3 = minElementsContainingAll(largeArray3, smallArray3);
        // System.out.println("Minimum elements for smallArray3: " + result3); // Output: -1

        // String[] largeArray4 = {"a", "b", "a"};
        // String[] smallArray4 = {"a", "a"};
        // int result4 = minElementsContainingAll(largeArray4, smallArray4);
        // System.out.println("Minimum elements for smallArray4: " + result4); // Output: 3

        for (String largeArray12 : largeArray1) {
            System.out.println(largeArray12);
        }

        List<String> uniqList = Arrays.asList(largeArray1).stream().
                                distinct().
                                collect(Collectors.toList());
        System.out.println("Unique list: " + uniqList);
    }
}
