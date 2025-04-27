import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution3Sum {
    private static final int target = 0;

    public static void main(String[] args){
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> sumList = threeSum(nums);
        System.out.println(sumList);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> sumList = new HashSet<>();
        
        if(nums.length < 3){
            return new ArrayList<>(sumList);
        }

        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){// we are going until before 2 elements at the end 
            
            int left = i+1; // left starting from next to i.
            int right = nums.length - 1; // right at the end.
            while(left<right){
                if(nums[i] + nums[left] + nums[right] == target){
                    sumList.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                }else if(nums[i] + nums[left] + nums[right] < target){
                    left++;
                }else{ //nums[i] + nums[left] + nums[right] > target 
                    right--;
                }
            }
        }

        return new ArrayList<>(sumList);
    }
}