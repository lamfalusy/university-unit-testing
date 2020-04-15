package com.university.whitebox.sorting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QuickSortTest {

    private QuickSort underTest;
    
    @Before
    public void before() {
        underTest = new QuickSort();
    }
    
    @Test
    public void testSortShouldReturnWithASortedArrayWhenTheInputContainsOnlyOneElement() {
        // Given
        Integer[] expected = {4};
        Integer[] input = {4};
        // When
        Integer[] actual = underTest.sort(input);
        // Then
        Assert.assertArrayEquals(expected, actual);
    }
    
    @Test
    public void testSortShouldReturnWithASortedArrayWhenTheInputContainsSeveralElement() {
        // Given
        Integer[] expected = {1, 4, 5, 9};
        Integer[] input = {9, 4, 5, 1};
        // When
        Integer[] actual = underTest.sort(input);
        // Then
        Assert.assertArrayEquals(expected, actual);
    }
    
    @Test
    public void testSortShouldReturnWithASortedArrayWhenTheInputIsAnEmptyArray() {
        // Given
        Integer[] expected = {};
        Integer[] input = {};
        // When
        Integer[] actual = underTest.sort(input);
        // Then
        Assert.assertArrayEquals(expected, actual);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSortShouldThrowIllegalArgumentExceptionWhenTheInputIsNull() {
        // Given
        Integer[] input = null;
        // When
        underTest.sort(input);
        // Then
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSortShouldThrowIllegalArgumentExceptionWhenTheInputContainsANullElement() {
        // Given
        Integer[] input = {9, null, 5, 1};
        // When
        underTest.sort(input);
        // Then
    }
    
}
