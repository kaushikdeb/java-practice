import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckSubsetStringArrays {

    public static boolean isSubset(String[] smallerArray, String[] largerArray) {
        Set<String> largerArrayList = new HashSet<>(Arrays.asList(largerArray));
        for(String smallArrString : smallerArray){
            if(!largerArrayList.contains(smallArrString)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] smaller1 = {"apple", "banana", "cherry"};
        String[] larger1 = {"apple", "banana", "cherry", "date", "fig"};
        System.out.println("Is smaller1 a subset of larger1? " + isSubset(smaller1, larger1)); // Output: true

        String[] smaller2 = {"apple", "fig", "grape"};
        String[] larger2 = {"apple", "banana", "cherry", "date", "fig"};
        System.out.println("Is smaller2 a subset of larger2? " + isSubset(smaller2, larger2)); // Output: false

        String[] smaller3 = {"apple", "banana", "banana"};
        String[] larger3 = {"apple", "banana", "cherry", "date", "fig"};
        System.out.println("Is smaller3 a subset of larger3? " + isSubset(smaller3, larger3)); // Output: true

        String[] smaller4 = {};
        String[] larger4 = {"apple", "banana", "cherry"};
        System.out.println("Is smaller4 a subset of larger4? " + isSubset(smaller4, larger4)); // Output: true

        String[] smaller5 = {"apple", "banana", "cherry"};
        String[] larger5 = {};
        System.out.println("Is smaller5 a subset of larger5? " + isSubset(smaller5, larger5)); // Output: false
    }
}