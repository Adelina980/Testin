package tests;

import org.junit.Test;
import tests.BubbleSort;

import static org.junit.Assert.assertArrayEquals;

public class BubbleSortTest{
    @Test
    public void testBubbleSort() {
        int[] unsortedArray = {64, 34, 25, 12, 22, 11, 90};
        int[] expectedSortedArray = {11, 12, 22, 25, 34, 64, 90};
        BubbleSort.sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);
    }

    @Test
    public void testEmptyArray() {
        int[] emptyArray = {};
        BubbleSort.sort(emptyArray);
        assertArrayEquals(new int[]{}, emptyArray);
    }

    @Test
    public void testSingleElementArray() {
        int[] singleElementArray = {42};
        BubbleSort.sort(singleElementArray);
        assertArrayEquals(new int[]{42}, singleElementArray);
    }
}