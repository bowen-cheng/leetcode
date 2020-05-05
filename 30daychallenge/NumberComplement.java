/**
 * Given a positive integer, output its complement number. The complement strategy is to flip the
 * bits of its binary representation.
 *
 * <p>Example 1:
 *
 * <p>Input: 5 Output: 2 Explanation: The binary representation of 5 is 101 (no leading zero bits),
 * and its complement is 010. So you need to output 2.
 *
 * <p>Example 2:
 *
 * <p>Input: 1 Output: 0 Explanation: The binary representation of 1 is 1 (no leading zero bits),
 * and its complement is 0. So you need to output 0.
 *
 * <p>Note:
 *
 * The given integer is guaranteed to fit within the range of a 32-bit signed integer.
 * You could assume no leading zero bit in the integer’s binary representation.
 * This question is the same as * 1009: https://leetcode.com/problems/complement-of-base-10-integer/
 */
public class NumberComplement {

  public static void main(String[] args) {
    System.out.println(new NumberComplement().findComplement(5));
  }

  /**
   * For example: 100110, its complement is 011001, the sum is 111111. So we only need get the min
   * number large or equal to num, then do subtraction.
   */
  public int findComplement(int num) {

    int i = 0;
    int j = 0;

    while (i < num) {
      i += Math.pow(2, j);
      j++;
      System.out.printf("i: %s, j: %s\n", i, j);
    }

    return i - num;
  }
}
