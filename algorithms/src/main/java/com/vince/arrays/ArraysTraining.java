package com.vince.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ArraysTraining {
    /**
     * Given an array of integers and an integer k, return the maximum average of any contiguous sub-array of K size.
     * @param input, an Integer array
     * @param k, size of the sub-arrays
     * @return maximum average of any contiguous sub-array of K size
     */
    public static double maxAverageUsingFixSlidingWindow(int[] input, int k) {

        double sum = 0;

        // If input array is smaller than k, then we will have only one sub-array, so we return the average of the whole array
        if(input.length < k) {
            for (int j : input) {
                sum += j;
            }
            return sum/input.length;
        }

        // If input array is bigger than K
        // Adding first K elements
        for(int i = 0; i < k; i++) {
            sum += input[i];
        }

        // Storing sum of first K elements as Max
        double max = sum;

        // Implementing sliding window technique
        for (int i = k; i < input.length; i++) {
            // Adding to the sum the next element and subtracting the first element
            sum = sum + input[i] - input[i - k];

            // Comparing sum and max, storing new max value
            max = Math.max(max, sum);
        }

        // Returning max average
        return max/k;
    }

    /**
     * Given an array of integers and an integer k, return the maximum sum of any contiguous sub-array of K size.
     * @param input, an Integer array
     * @param k, size of the sub-arrays
     * @return maximum sum of any contiguous sub-array of K size
     */
    public static int maxSumUsingFixSlidingWindow(int[] input, int k) {
        int sum = 0;

        if(input.length < k) {
            for(int j : input){
                sum += j;
            }
            return sum;
        }

        for(int i = 0; i < k; i++) {
            sum += input[i];
        }

        int max = sum;

        for (int i = k; i < input.length; i++) {
            sum = sum + input[i] - input[i - k];
            max = Math.max(max, sum);
        }

        return max;
    }

    /**
     * Given an array of integers and an integer target, return the number of sub-arrays that sum of elements is bigger or equals than target.
     * @param input, an Integer array
     * @param k, size of the sub-arrays
     * @param target, minimum sum of any contiguous sub-array
     * @return number of sub-arrays that sum of elements is bigger or equals than target
     */
    public static int countSubArraysUsingFixSlidingWindow(int[] input, int k, int target) {
        int count = 0;

        if(input.length < k){
            throw new IllegalStateException("Array is smaller than k");
        }

        int sum = 0;
        for(int i = 0; i < k; i++) {
            sum += input[i];
        }

        if(sum >= target) {
            count++;
        }

        for (int i = k; i < input.length; i++) {
            sum = sum + input[i] - input[i - k];
            if(sum >= target) {
                count++;
            }
        }

        return count;
    }

    /**
     * Given an array of integers and an integer target, return the length of the minimum array that sum of elements is bigger or equals than target.
     * @param input, an Integer array
     * @param target, minimum sum of any contiguous sub-array
     * @return length of the minimum array that sum of elements is bigger or equals than target
     */
    public static int minArrayLengthUsingFlexSlidingWindow(int[] input, int target) {

        int start = 0, end = 0;
        int windowSum = 0;
        int minLength = Integer.MAX_VALUE;

        while(end < input.length) {
            windowSum += input[end];
            while(windowSum >= target) {
                minLength = Math.min(minLength, end - start + 1);
                windowSum -= input[start];
                start++;
            }
            end++;
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /**
     * Given a string s, find the length of the longest substring without repeating characters.
     * @param input, a String
     * @return length of the longest substring without repeating characters
     */
    public static int longestSubStringNoRepeatingCharsLengthUsingSlidingWindow(String input) {
        int start = 0, end = 0;
        int maxLength = 0;
        Set<Character> chars = new HashSet<>();

        while(end < input.length()) {
            char currentChar = input.charAt(end);

            if (!chars.contains(currentChar)) {
                chars.add(currentChar);
                maxLength = Math.max(maxLength, end - start + 1);
                end++;
            } else {
                chars.remove(input.charAt(start));
                start++;
            }
        }

        return maxLength;
    }

    /**
     * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s
     * such that every character in t (including duplicates) is included in the window.
     * @param string, a String
     * @param target, a String
     * @return minimum substring containing all the characters in target
     */
    public static String minSubStringContainingCharsInInput(String string, String target)  {

        int start = 0, end = 0;
        int minLength = Integer.MAX_VALUE;
        int minStart = 0;

        Map<Character, Integer> targetCount = new HashMap<>();
        for(char c : target.toCharArray()){
            targetCount.put(c, targetCount.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> windowCount = new HashMap<>();
        int required = targetCount.size();
        int formed = 0;

        while(end < string.length()) {
            char currEndChar = string.charAt(end);
            windowCount.put(currEndChar, windowCount.getOrDefault(currEndChar, 0) + 1);

            if(targetCount.containsKey(currEndChar) &&
                    windowCount.get(currEndChar).intValue() == targetCount.get(currEndChar).intValue()) {
                formed++;
            }

            while(formed == required) {
                if(end - start + 1 < minLength) {
                    minLength = end - start + 1;
                    minStart = start;
                }

                char currStartChar = string.charAt(start);
                windowCount.put(currStartChar, windowCount.get(currStartChar) - 1);


                if(targetCount.containsKey(currStartChar) &&
                        windowCount.get(currStartChar) < targetCount.get(currStartChar)){
                    formed--;
                }
                start++;
            }
            end++;
        }

        return minLength == Integer.MAX_VALUE ? "" : string.substring(minStart, minStart + minLength);
    }

    /**
     * Given a string s and an integer k, return the length of the longest substring containing at most k distinct characters.
     * @param s, a String
     * @param k, an integer
     * @return length of the longest substring containing at most k distinct characters
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {

        int start = 0, end = 0;

        if(k == 0 || s == null || s.isEmpty())
            return 0;

        Map<Character, Integer> charCount = new HashMap<>();
        int maxLength = 0;

        while(end < s.length()) {
            char currentChar = s.charAt(end);
            charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);
            while(charCount.size() > k) {
                char startChar = s.charAt(start);
                if(charCount.get(startChar) == 1){
                    charCount.remove(startChar);
                } else{
                    charCount.put(startChar, charCount.get(startChar) - 1);
                }
                start++;
            }

            maxLength = Math.max(maxLength, end - start + 1);
            end++;
        }

        return maxLength;
    }

    /**
     * SOLVED USING TWO POINTERS TECHNIQUE
     * Given a SORTED array of integers and an integer target, return true if there are two numbers in the array that sum is equals to target.
     * @param nums, an Integer array
     * @param target, an integer
     * @return true - if there are two numbers in array that sum is equals to target, false otherwise
     */
    public static boolean hasTwoNumbersSumTarget(int[] nums, int target) {
        int left = 0, right = nums.length -1;
        while(left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) return true;
            else if (sum < target) left++;
            else right--;
        }
        return false;
    }

    /**
     * SOLVED USING TWO POINTERS TECHNIQUE
     * Given a SORTED array of integers and an integer target, return the indexes of the two numbers in the array that sum is equals to target.
     * @param nums, an Integer array
     * @param target, an integer
     * @return indexes of the two numbers in array that sum is equals to target
     */
    public static int[] twoNumbersSumTargetIndexes(int[] nums, int target) {
        int left= 0, right = nums.length - 1;

        while(left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) return new int[]{left + 1, right + 1};
            else if (sum < target) left++;
            else right--;
        }

        return new int[]{-1, -1};
    }

    /**
     * SOLVED USING TWO POINTERS TECHNIQUE
     * Given a string input, determine if it is a palindrome.
     * @param input, a String
     * @return true - if input is a palindrome, false otherwise
     */
    public static boolean isPalindrome(String input) {
        String trimmedInput = input.toLowerCase().replaceAll("[^a-z0-9]", "");
        int left = 0, right = trimmedInput.length() -1;
        while(left < right) {
            if (trimmedInput.charAt(left) != trimmedInput.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    /**
     * SOLVED USING TWO POINTERS TECHNIQUE
     * Given an integer array height of length n, return the maximum amount of water a container can store.
     * @param height, an Integer array representing the height of each line
     * @return maximum amount of water a container can store.
     */
    public static int areaOfContainerWithMostWater(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0, currArea = 0;
        while(left < right) {
            int minHeight = Math.min(height[left], height[right]);
            currArea = minHeight * (right - left);
            maxArea = Math.max(maxArea, currArea);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
