package arrays101.introduction;

import java.util.Arrays;

/**
 * Given an array of integers A sorted in non-decreasing order, return an array of the squares of
 * each number, also in sorted non-decreasing order.
 *
 * <p>Example 1:
 *
 * <p>Input: [-4,-1,0,3,10] Output: [0,1,9,16,100] Example 2:
 *
 * <p>Input: [-7,-3,2,3,11] Output: [4,9,9,49,121]
 *
 * <p>Note:
 *
 * <p>1 <= A.length <= 10000 -10000 <= A[i] <= 10000 A is sorted in non-decreasing order.
 */
public class SquaresOfSortedArray {

  /**
   * Easy and dumb solution: beats 56% of submission Not taking advantage of the non-decreasing
   * order of teh array
   */
  public int[] solution1(int[] a) {

    int[] result = new int[a.length];

    for (int i = 0; i < a.length; i++) {
      result[i] = a[i] * a[i];
    }

    Arrays.sort(result);
    return result;
  }

  /**
   * Two pointer method: beats 100% of submission.
   *
   * <p>Take advantage of non-decreasing order of the array. Split it into 1 positive and 1 negative
   * array.
   */
  public int[] solution2(int[] a) {

    int pivot = -1;
    int capacity = a.length;
    int[] result = new int[capacity];

    if (capacity == 1) {
      return new int[] {sq(a[0])};
    }

    // Find and initialize pivot
    if (a[0] >= 0) {
      pivot = 0;
    } else if (a[capacity - 1] <= 0) {
      pivot = capacity - 1;
    } else {
      for (int i = 0; i < capacity; i++) {
        if (a[i] >= 0) {
          // it's key to select the number with smallest abs value in this approach
          pivot = sq(a[i]) < sq(a[i - 1]) ? i : i - 1;
          break;
        }
      }
    }

    // loop over using left and right index
    int li = pivot;
    int ri = pivot + 1;
    int idx = -1;
    while (li >= 0 || ri < capacity) {
      idx++;
      int lSq = li >= 0 ? sq(a[li]) : -1;
      int rSq = ri < capacity ? sq(a[ri]) : -1;

      if (lSq == -1) {
        result[idx] = rSq;
        ri++;
        continue;
      } else if (rSq == -1) {
        result[idx] = lSq;
        li--;
        continue;
      }

      if (lSq < rSq) {
        result[idx] = lSq;
        li -= 1;
      } else {
        result[idx] = rSq;
        ri += 1;
      }
    }

    return result;
  }

  /**
   * Simplified version of the two pointer approach.
   *
   * <p>Key difference:
   *
   * <p>li >= 0 && ri < capacity
   *
   * <p>li >= 0 || ri < capacity
   */
  public int[] solution3(int[] a) {

    int capacity = a.length;
    int[] result = new int[capacity];

    // Initialize left and right index
    int li;
    int ri = 0;
    // This loops actually finds the first positive number's indix
    while (ri < capacity && a[ri] < 0) {
      ri++;
    }
    li = ri - 1;

    // loop over the negative and positive arrays using left and right index
    int idx = 0;

    // Simplify the complex logic by stopping processing both arrays in 1 loop as soon as one is
    // finished
    while (li >= 0 && ri < capacity) {
      int lSq = sq(a[li]);
      int rSq = sq(a[ri]);

      if (lSq < rSq) {
        result[idx++] = lSq;
        li--;
      } else {
        result[idx++] = rSq;
        ri++;
      }
    }

    // either left or right array's loop will actually trigger
    while (li >= 0) {
      result[idx++] = sq(a[li--]);
    }
    while (ri < capacity) {
      result[idx++] = sq(a[ri++]);
    }

    return result;
  }

  private int sq(int input) {
    return input * input;
  }
}
