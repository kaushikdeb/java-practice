
/*
 * find java code for following problem:

two strings are given, one is larger than the other, find the minimum substring in the larger string that has all the characters in the smaller string.
 * 
 */

import java.util.HashMap;
import java.util.Map;

public class SlidingWindowString {

    static String minSubstring(String large, String small){
        String smallestSubstring = "";

        int left = 0, right = 0;
        int largeLen = large.length();
        int smallLen = small.length();
        int minLength = Integer.MIN_VALUE, matchedLength = 0;
        Map<Character, Integer> targetMap = new HashMap<>(); // Contains frequency of each char in small 
        Map<Character, Integer> windowMap = new HashMap<>(); // Contains frequency of each char in the sliding window

        // Find targetMap values: i.e. frequency of each char in small
        for(int i=0;i<smallLen;i++){
            targetMap.put(small.charAt(i), targetMap.getOrDefault(small.charAt(i), 0)+1);
        }
        // System.out.println("targetmap: " + targetMap);

        while(right < largeLen){
            char charAtRight = large.charAt(right);
            windowMap.put(charAtRight, windowMap.getOrDefault(charAtRight, 0)+1);

            // Find if window has all characters from small string            
            if(targetMap.containsKey(charAtRight) && windowMap.get(charAtRight) <= targetMap.get(charAtRight)){
                matchedLength++;
                if(matchedLength == small.length()){
                    System.out.println("Found all chars matching: " + large.substring(left, right));
                }
            }
            

            right++;
        }
        // System.out.println("windowMap: " + windowMap);

        return smallestSubstring;
    }

    public static void main(String[] args) {
        String large = "ADOBECODEBANC";
        String small = "ABC";
        String result = minSubstring(large, small);
        System.out.println("Minimum substring containing all characters: " + result); // Output: BANC

        large = "aab";
        small = "ab";
        result = minSubstring(large, small);
        System.out.println("Minimum substring containing all characters: " + result); // Output: ab

        large = "aaaaa";
        small = "b";
        result = minSubstring(large, small);
        System.out.println("Minimum substring containing all characters: " + result); // Output: ""
    }
}