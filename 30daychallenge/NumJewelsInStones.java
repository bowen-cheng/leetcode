import java.util.HashMap;
import java.util.Map;

/**
 * You're given strings J representing the types of stones that are jewels, and S representing the
 * stones you have. Each character in S is a type of stone you have. You want to know how many of
 * the stones you have are also jewels.
 *
 * <p>The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters
 * are case sensitive, so "a" is considered a different type of stone from "A".
 *
 * <p>Example 1:
 *
 * <p>Input: J = "aA", S = "aAAbbbb" Output: 3 Example 2:
 *
 * <p>Input: J = "z", S = "ZZ" Output: 0 Note:
 *
 * <p>S and J will consist of letters and have length at most 50.
 *
 * <p>The characters in J are distinct.
 */
public class NumJewelsInStones {

  public int solution1(String J, String S) {

    /*
    Why this brute force solution beats 100% submission and works so fast?
    It's because "S and J will consist of letters and have length at most 50".
    */
    if (J == null || J.length() == 0) {
      return 0;
    }
    int count = 0;
    for (Character c : S.toCharArray()) {
      for (Character m : J.toCharArray()) {
        if (m == c) {
          count++;
          break;
        }
      }
    }
    return count;
  }

  public int solution2(String J, String S) {

    /*
    Only beats 70% of submission because we only have small sets of data.
    This solution should work better than the previous one on large data sets.
    */
    // convert string to list
    char[] j = J.toCharArray();
    char[] s = S.toCharArray();

    // convert list to HashMap
    Map<String, Boolean> map = new HashMap<>();
    for (char c : j) {
      map.put(String.valueOf(c), true);
    }

    // For each char in S, check if it is a jewel
    int count = 0;
    for (char c : s) {
      count += map.get(String.valueOf(c)) == null ? 0 : 1;
    }
    return count;
  }
}
