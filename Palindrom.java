class Palindrom {

public static void main(String args[]){
  System.out.println("Hey! Expecting the input string to be passed as a parameter..");
  
  if(args.length == 0){
    System.err.println("Error! No parameters passed. Please pass a String as a parameter to find if it is a Palindrom word or not. Exiting...");
    System.exit(1);
  }

  String inputStr = args[0].toLowerCase();
  int len = inputStr.length();
  char[] reversedChar = new char[len];

  for(int i=0;i<len/2;i++){
    if(inputStr.charAt(i) != inputStr.charAt(len-1-i)){
      System.out.println("The input word: " + inputStr + " is NOT a Palindrom.");
      System.out.println("Because, " + i + "th character " + inputStr.charAt(i) + " does not match " + (len-1-i) + "th character " + inputStr.charAt(len-1-i));
      System.exit(0);
    }
  }
  
  System.out.println("The input word: " + inputStr + " is a Palindrom.");

}

}