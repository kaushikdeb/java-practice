class RevChars{

  public static void main(String args[]){
    System.out.println("Hey there! Expecting the input string to be passed as a command line parameter.");
    if(args.length == 0){
	System.out.println("Error! No parameters passed. Exiting...");
	System.exit(1);
    }

    System.out.println("Input String detected: " + args[0]);
    char[] reversedWord = new char[args[0].length()];

    for(int i=args[0].length()-1,j=0;i>=0;i--,j++){
	reversedWord[j] = args[0].charAt(i);
    }

    System.out.println("1. Using char array, ======== Reversed Input: " + new String(reversedWord));
    System.out.println("2. Using StringBuilder class, Reversed Input: " + new StringBuilder(args[0]).reverse().toString());
  }

}