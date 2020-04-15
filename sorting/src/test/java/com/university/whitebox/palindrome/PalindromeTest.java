package com.university.whitebox.palindrome;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PalindromeTest {

    private Palindrome underTest;
    
    @Before
    public void before() {
        underTest = new Palindrome();
    }

    @Test
    public void testIdPalindromeShouldReturnWithTrueWhenTheInputIsAnEmptyString() {
        // Given
        boolean expected = true;
        String input = "";
        // When
        boolean actual = underTest.isPalindrome(input);
        // Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testIdPalindromeShouldReturnWithTrueWhenTheInputIsAPalidrome() {
        // Given
        boolean expected = true;
        String input = "indul a görög aludni";
        // When
        boolean actual = underTest.isPalindrome(input);
        // Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testIdPalindromeShouldReturnWithTrueWhenTheInputIsAPalidromeAndContainsOnlyDigits() {
        // Given
        boolean expected = true;
        String input = "2020.02.02";
        // When
        boolean actual = underTest.isPalindrome(input);
        // Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testIdPalindromeShouldReturnWithTrueWhenTheInputIsAPalidromeAndContainsDigits() {
        // Given
        boolean expected = true;
        String input = "indul 1 görög 1ludni";
        // When
        boolean actual = underTest.isPalindrome(input);
        // Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testIdPalindromeShouldReturnWithFalseWhenTheInputIsNotAPalidrome() {
        // Given
        boolean expected = false;
        String input = "nem indul a görög aludni";
        // When
        boolean actual = underTest.isPalindrome(input);
        // Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIdPalindromeShouldReturnWithFalseWhenTheInputIsNull() {
        // Given
        String input = null;
        // When
        underTest.isPalindrome(input);
        // Then
    }
    
}
