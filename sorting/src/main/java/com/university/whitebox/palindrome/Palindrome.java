package com.university.whitebox.palindrome;

public class Palindrome {

    /**
     * This method ignores all non-alphanumeric characters and case runs in O(n)
     * where n is the length of s
     * 
     * @param s String to check
     * @return true if s is palindrome else false
     */
    public boolean isPalindrome(String s) {
        if(s == null) {
            throw new IllegalArgumentException("Input String can not be null");
        }
        
        s = s.toLowerCase().trim();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c) || Character.isDigit(c))
                sb.append(c);
        }
        s = sb.toString();
        int start = 0;
        int end = s.length() - 1;
        while (start <= end) {
            if (s.charAt(start++) != s.charAt(end--))
                return false;

        }
        return true;
    }
    
}
