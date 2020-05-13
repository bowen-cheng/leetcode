/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else
 * False.
 *
 * <p>Note: Do not use any built-in library function such as sqrt.
 *
 * <p>Example 1: Input: 16 Output: true
 *
 * <p>Example 2: Input: 14 Output: false
 */
public class ValidPerfectSquare {

  public static void main(String[] args) {
    for (int i = 0; i <= 9; i++) {
      System.out.printf("%s: %s\n", i, isPerfectSquare(i));
    }
  }

  /** $tag$ Binary search */
  public static boolean isPerfectSquare(int num) {
    long l = 0;
    long h = num;

    while (l <= h) {
      long mid = l + (h - l) / 2;
      long sqr = mid * mid; // $$: use int type for the square result may result in overflow

      if (sqr == num) {
        return true;
      } else if (sqr < num) {
        l = mid + 1;
      } else {
        // sqr > num
        h = mid - 1;
      }
    }
    return false;
  }
}
