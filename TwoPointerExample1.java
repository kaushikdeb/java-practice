import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointerExample1 {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 6, 8, 9};
        Arrays.sort(numbers);
        int target = 10;

        int[] result = findPairWithSum(numbers, target);
        if (result != null) {
            System.out.println("Pair found: " + result[0] + ", " + result[1]);
        } else {
            System.out.println("No pair found.");
        }

        System.out.println(findPairWithSum2(numbers, target));
        findPairWithSum2(numbers, target).forEach(x->System.out.println(x[0] + " " + x[1]));

        // Test for three-sum problem
        // int[] numbersForThreeSum = {-1, 0, 1, 2, -1, -4};
        // int targetForThreeSum = 0;

        // System.out.println("Three-sum results:");
        // findThreeSum(numbersForThreeSum, targetForThreeSum);
    }

    public static int[] findPairWithSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;        
        while(left<right){
            int sum = numbers[left] + numbers[right];
            if(sum == target){
                return new int[]{numbers[left], numbers[right]};
            }else if(sum<target){
                left++;
            }else{
                right--;
            }
        }
        return null;
    }

    public static List<int[]> findPairWithSum2(int[] numbers, int target) {
        List<int[]> myList = new ArrayList<>();
        int left = 0;
        int right = numbers.length - 1;        
        while(left<right){
            int sum = numbers[left] + numbers[right];
            if(sum == target){
                myList.add(new int[]{numbers[left], numbers[right]});
                left++;
                right--;
            }else if(sum<target){
                left++;
            }else{
                right--;
            }
        }
        return myList;
    }

    public static void findThreeSum(int[] numbers, int target) {
        Arrays.sort(numbers); // Sort the array first
        for (int i = 0; i < numbers.length - 2; i++) {
            if (i > 0 && numbers[i] == numbers[i - 1]) {
                continue; // Skip duplicates
            }
            int left = i + 1;
            int right = numbers.length - 1;

            while (left < right) {
                int sum = numbers[i] + numbers[left] + numbers[right];
                if (sum == target) {
                    System.out.println("Triplet found: " + numbers[i] + ", " + numbers[left] + ", " + numbers[right]);
                    left++;
                    right--;
                    while (left < right && numbers[left] == numbers[left - 1]) left++; // Skip duplicates
                    while (left < right && numbers[right] == numbers[right + 1]) right--; // Skip duplicates
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
    }
}
