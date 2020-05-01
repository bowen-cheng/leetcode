/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately,
 * the latest version of your product fails the quality check. Since each version is developed based
 * on the previous version, all the versions after a bad version are also bad.
 *
 * <p>Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which
 * causes all the following ones to be bad.
 *
 * <p>You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to
 * the API.
 *
 * <p>Example:
 *
 * <p>Given n = 5, and version = 4 is the first bad version.
 *
 * <p>call isBadVersion(3) -> false call isBadVersion(5) -> true call isBadVersion(4) -> true
 *
 * <p>Then 4 is the first bad version.
 */
class VersionControl {
  final int answer = 1702766719;

  protected boolean isBadVersion(int input) {
    return input == answer;
  }
}

public class FirstBadVersion extends VersionControl {

  public static void main(String[] args) {
    final int n = 2126753390;
    System.out.println("result is: " + new FirstBadVersion().solution(n));
  }

  /** The most efficient solution is binary search */
  public int solution(int n) {

    int left = 1;
    int right = n;
    int mid;

    while (left < right) {
      /*
      mid = left + (right - left) / 2 and mid = (left + right) / 2 are both correct mathematically.
      But (left + right) may cause overflow when left and right are both very big.
       */
      mid = left + (right - left) / 2;
      System.out.printf("left: %s, mid: %s, right: %s\n", left, mid, right);
      if (isBadVersion(mid)) {
        right = mid;
      } else {
        /*
        Always remember to set lower bound to be 1 plus mid.
        This is because of the integer division in mid = left + (right - left) / 2:
        When right-left=1
        then 1/2 = 0
        then mid = left + 0
        then mid = left
        then the program will stuck at a dead loop.
         */
        left = mid + 1;
      }
    }
    return left;
  }
}
