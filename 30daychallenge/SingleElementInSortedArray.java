/**
 * You are given a sorted array consisting of only integers where every element appears exactly
 * twice, except for one element which appears exactly once. Find this single element that appears
 * only once.
 *
 * <p>Example 1:
 *
 * <p>Input: [1,1,2,3,3,4,4,8,8] Output: 2 Example 2:
 *
 * <p>Input: [3,3,7,7,10,11,11] Output: 10
 *
 * <p>Note: Your solution should run in O(log n) time and O(1) space.
 */
public class SingleElementInSortedArray {

  public static void main(String[] args) {
    int[] nums = new int[] {2, 2, 3, 3, 4, 4, 8, 8, 9};
    System.out.println(singleNonDuplicate(nums));
  }

  /**
   * $tag$ binary search
   *
   * <p>$$ Sorted array and O(log n) should be a signal for using binary search
   *
   * <p>Since all numbers (except the target) appear exactly twice, then nums[even] == nums[even+1]
   * until the target number appears. After the target number appears in the array, the equality
   * pattern becomes nums[odd] == nums[odd+1].
   */
  public static int singleNonDuplicate(int[] nums) {
    int l = 0;
    int r = nums.length - 1;

    while (l < r) {
      int mid = l + (r - l) / 2;

      System.out.printf("l: %s, mid: %s, r: %s\n", l, mid, r);

      /*
       $$ The key in binary search is to know when to stop

      */
      if (mid + 1 > nums.length - 1
          || mid - 1 < 0
          || nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1]) {
        return nums[mid];
      }

      if (mid % 2 == 0) {
        // even odd
        if (nums[mid] == nums[mid + 1]) {
          l = mid + 1;
        }
        // odd even
        if (nums[mid] == nums[mid - 1]) {
          r = mid - 1;
        }
      } else {
        // odd even
        if (nums[mid] == nums[mid + 1]) {
          r = mid - 1;
        }
        if (nums[mid] == nums[mid - 1]) {
          // even odd
          l = mid + 1;
        }
      }
    }
    return nums[l];
  }
}
