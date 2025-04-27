import java.util.Arrays;

public class TwoPointerExample {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 6, 8, 9};
        int target = 10;

        int[] result = findPairWithSum(numbers, target);
        if (result != null) {
            System.out.println("Pair found: " + result[0] + ", " + result[1]);
        } else {
            System.out.println("No pair found.");
        }

        // Test for three-sum problem
        int[] numbersForThreeSum = {-1, 0, 1, 2, -1, -4};
        int targetForThreeSum = 0;

        System.out.println("Three-sum results:");
        findThreeSum(numbersForThreeSum, targetForThreeSum);
    }

    public static int[] findPairWithSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{numbers[left], numbers[right]};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return null; // No pair found
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
