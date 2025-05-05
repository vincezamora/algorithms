package com.vince;

import com.vince.arrays.ArraysTraining;

import java.util.Arrays;

import static com.vince.arrays.ArraysTraining.*;

public class Main {
    public static void main(String[] args) {

        // Max Average Sliding Window Test case
        int[] input = {1, 12, -5, -6, 50, 3};
        int k = 4;
        double result = maxAverageUsingFixSlidingWindow(input, k);
        System.out.println("Max Average: " + result); // Should print 12.75

        // Max Sum Sliding Window Test case
        int[] input2 = {2, 1, 5, 1, 3, 2};
        k = 3;
        result = maxSumUsingFixSlidingWindow(input2, k);
        System.out.println("Max Sum: " + result); // Should print 9

        // Count Sub Arrays Sum Sliding Window Test case
        int[] input3 = {2, 1, 3, 4, 1, 2};
        k = 3;
        int target = 6;
        result = countSubArraysUsingFixSlidingWindow(input3, k, target);
        System.out.println("Count of Sub Arrays: " + result); // Should print 4

        // Find minimum length Sub Array Sum Sliding Window Test case
        int[] input4 = {1,2,3,4,15,6,7,8,9};
        target = 15;
        result = minArrayLengthUsingFlexSlidingWindow(input4, target);
        System.out.println("Min Array Length: " + result); // Should print 2

        String inputString = "abcabcbb";
        result = longestSubStringNoRepeatingCharsLengthUsingSlidingWindow(inputString);
        System.out.println("Max Sub-String Length: " + result); // Should print 3

        String text = "ADOBECODEBANC";
        String input5 = "ABC";
        String result2 = minSubStringContainingCharsInInput(text, input5);
        System.out.println("Min Sub-String: " + result2); // Should print "BANC"

        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2)); // 3 ("ece")
        System.out.println(lengthOfLongestSubstringKDistinct("aa", 1));    // 2
        System.out.println(lengthOfLongestSubstringKDistinct("abcadcacacaca", 3)); // 10 ("cadcacacaca")
        System.out.println(lengthOfLongestSubstringKDistinct("", 2));      // 0
        System.out.println(lengthOfLongestSubstringKDistinct("a", 0));     // 0

        System.out.println("hasTwoNumbersSumTarget: {2,3,4,5,6}, 8" + hasTwoNumbersSumTarget(new int[]{2,3,4,5,6}, 8)); // true

        System.out.println("isPalindrome: racecar " + isPalindrome("racecar")); // true
        System.out.println("isPalindrome: vicente " + isPalindrome("vicente")); // false
        System.out.println("isPalindrome: Vicente tnEciv " + isPalindrome("Vicente tnEciv")); // true

        System.out.println("twoNumbersSumTargetIndexes: {2,3,4,5,6}, 8 " + Arrays.toString(twoNumbersSumTargetIndexes(new int[]{2,3,4,5,6}, 8))); // [1,5]

        System.out.println("areaOfContainerWithMostWater: {1,8,6,2,5,4,8,3,7} " + areaOfContainerWithMostWater(new int[]{1,8,6,2,5,4,8,3,7})); //49
    }
}