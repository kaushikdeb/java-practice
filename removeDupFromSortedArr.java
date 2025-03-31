class Solution {
    public int removeDuplicates(int[] nums) {
        int k=1;
        int i=0;
        int j = i+1;
        while(i<nums.length && j<nums.length){            
            while(j<nums.length && nums[i]==nums[j]){
                j++;
            }
            if(j<nums.length){
                nums[++i]=nums[j++];
                k++;
            }
        };
        return k;
    }

    public static void main(String[] args) {
        int[] x = new int[]{1,1,2};        
        Solution solution = new Solution();
        int k = solution.removeDuplicates(x);
        System.out.println("k = " + k);        
        for(int i=0;i<x.length;i++){
            System.out.println("x[" + i + "] = " + x[i]);
        }
        
        x = new int[]{0,0,1,1,1,2,2,3,3,4};        
        k = solution.removeDuplicates(x);
        System.out.println("k = " + k);        
        for(int i=0;i<x.length;i++){
            System.out.println("x[" + i + "] = " + x[i]);
        }

        x = new int[]{4};        
        k = solution.removeDuplicates(x);
        System.out.println("k = " + k);        
        for(int i=0;i<x.length;i++){
            System.out.println("x[" + i + "] = " + x[i]);
        }

        x = new int[]{4,4};
        k = solution.removeDuplicates(x);
        System.out.println("k = " + k);        
        for(int i=0;i<x.length;i++){
            System.out.println("x[" + i + "] = " + x[i]);
        }
    }
}
