import java.util.List;
import java.util.Collections;
import java.util.Arrays;

class ReverseWords{

  public static void main(String args[]){
    System.out.println("Hello! This program reverses words in a Sentence. Please pass the sentence as a string in double quotes as a parameter");

    if(args.length == 0){
	System.out.println("\nError! Currently, you have not passed any sentence as input. Please rerun and pass the input sentence as a parameter to the program. Exiting...");
	System.exit(0);
    }

    System.out.println("Input Sentence recognized: \n" + args[0]);
    System.out.println("The same sentence written by reversing the words: ");
    System.out.println("1. Using split and array ======: " + reverseWords(args[0]));
    List<String> splitSentence = Arrays.asList(args[0].split("\\s+"));
    Collections.reverse(splitSentence);
    System.out.println("2. Using Collections reverse()=: " + String.join(" ", splitSentence));
    System.out.println("3. Using split and 1/2 Array===: " + reverseHalfArray(args[0]));
  }


  private static String reverseWords(String inputSentence){
    StringBuilder reversedWords = new StringBuilder();

    String[] inputSplit = inputSentence.split("\\s+");

    for(int i=inputSplit.length-1;i>=0;i--)
	reversedWords.append(inputSplit[i]).append(" ");

    if(reversedWords.length() > 0)
	reversedWords.setLength(reversedWords.length() - 1);

    return reversedWords.toString();

  }

  private static String reverseHalfArray(String input){
    String[] splitInput = input.split("\\s+");
    for(int i=0;i<splitInput.length/2;i++){
      String temp = splitInput[i];
      splitInput[i] = splitInput[splitInput.length - 1 - i];
      splitInput[splitInput.length - 1 - i] = temp;
    }
    return String.join(" ", splitInput);
  }
}