import java.util.Arrays;

/**
 * Given an array of size n, find the majority element. The majority element is the element that
 * appears more than ⌊ n/2 ⌋ times.
 *
 * <p>You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * <p>Example 1:
 *
 * <p>Input: [3,2,3] Output: 3
 *
 * <p>Example 2:
 *
 * <p>Input: [2,2,1,1,1,2,2] Output: 2
 */
public class MajorityElement {

  /** $$ observe the features of input (in this case, the majority element always present) */
  public int majorityElement(int[] nums) {
    Arrays.sort(nums);

    return nums[nums.length / 2];
  }
}
