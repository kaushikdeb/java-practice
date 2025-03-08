class ReverseWords{

  public static void main(String args[]){
    System.out.println("Hello! This program reverses words in a Sentence. Please pass the sentence as a string in double quotes as a parameter");

    if(args.length == 0){
	System.out.println("\nError! Currently, you have not passed any sentence as input. Please rerun and pass the input sentence as a parameter to the program. Exiting...");
	System.exit(0);
    }

    System.out.println("Input Sentence recognized: \n" + args[0]);
    System.out.println("The same sentence written by reversing the words: ");
    System.out.println(reverseWords(args[0]));
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
}