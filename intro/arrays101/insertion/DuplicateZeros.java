package arrays101.insertion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the
 * remaining elements to the right.
 *
 * <p>Note that elements beyond the length of the original array are not written.
 *
 * <p>Do the above modifications to the input array in place, do not return anything from your
 * function.
 *
 * <p>Example 1:
 *
 * <p>Input: [1,0,2,3,0,4,5,0] Output: null Explanation: After calling your function, the input
 * array is modified to: [1,0,0,2,3,0,0,4] Example 2:
 *
 * <p>Input: [1,2,3] Output: null Explanation: After calling your function, the input array is
 * modified to: [1,2,3]
 *
 * <p>Note:
 *
 * <p>1 <= arr.length <= 10000
 *
 * <p>0 <= arr[i] <= 9
 */
public class DuplicateZeros {

  /** Two pointer method, beats 100% of submission */
  public void duplicateZeros0(int[] arr) {
    int i = 0;
    int j = 0;

    // $$ Store the original value of input array in another array. Loop over both arrays with two
    // pointers (one for each array respectively). And pick values into the original array.
    int[] res = Arrays.copyOf(arr, arr.length);
    while (j < arr.length) {
      if (res[i] == 0) {
        arr[j] = 0;
        if (j + 1 < arr.length) {
          arr[j + 1] = 0;
        }
        i++;
        j = j + 2;
      } else {
        arr[j] = res[i];
        i++;
        j++;
      }
    }
  }

  /** Brute force solution, beats 30% of submission */
  public void duplicateZeros1(int[] arr) {
    int cap = arr.length;

    if (cap == 1) {
      return;
    }

    for (int i = 0; i < cap; i++) {
      if (arr[i] == 0) {
        // $$: Using System.arraycopy can save a lot of typing
        if (cap - 1 - i >= 0) System.arraycopy(arr, i, arr, i + 1, cap - 1 - i);
        // for (int r = cap - 1; r > i; r--) {
        //  arr[r] = arr[r - 1];
        // }
        i++;
      }
    }
  }

  /** Use map to memorize shifted values and positions */
  public void duplicateZeros2(int[] arr) {
    int cap = arr.length;

    if (cap == 1) {
      return;
    }
    // Create a new HashMap for fast loop up
    Map<Integer, Integer> map = new HashMap<>();

    // Map elements into their new shifted position
    map.put(0, arr[0]);
    int zeroCount = 0;
    for (int i = 1; i < cap; i++) {
      if (arr[i - 1] == 0) {
        zeroCount++;
      }
      int newPos = i + zeroCount;
      if (newPos > cap - 1) {
        break;
      }
      map.put(newPos, arr[i]);
    }

    // Update the array by looking up each new number's position
    for (int i = 0; i < cap; i++) {
      if (map.get(i) == null) {
        arr[i] = 0;
      } else {
        arr[i] = map.get(i);
      }
    }
  }

  /** Use two arrays and almost two pointers */
  public void duplicateZeros3(int[] arr) {
    int cap = arr.length;
    if (cap == 1) {
      return;
    }

    // find out how many zeros to duplicate
    int zeroCount = 0;
    for (int value : arr) {
      if (value == 0) {
        zeroCount++;
      }
    }

    // Create an array that is large enough
    int[] cp = new int[cap + zeroCount];

    // Write number into the new array
    zeroCount = 0;
    cp[0] = arr[0];
    for (int i = 1; i < cap; i++) {
      if (arr[i - 1] == 0) {
        zeroCount++;
      }
      // the new position for i is (i + zeroCount)
      cp[i + zeroCount] = arr[i];
    }

    // Copy results back to arr
    System.arraycopy(cp, 0, arr, 0, cap);
  }

  /** Interesting solution with queue */
  public void duplicateZeros4(int[] arr) {
    Queue<Integer> q = new LinkedList<>();
    for (int i : arr) {
      if (i != 0) q.offer(i);
      else {
        q.offer(0);
        q.offer(0);
      }
    }
    int n = arr.length;
    int index = 0;
    while (index < n) {
      arr[index++] = q.poll();
    }
  }
}
