import java.util.HashMap;
import java.util.Map;

/**
 * Given an arbitrary ransom note string and another string containing letters from all the
 * magazines, write a function that will return true if the ransom note can be constructed from the
 * magazines ; otherwise, it will return false.
 *
 * <p>Each letter in the magazine string can only be used once in your ransom note.
 *
 * <p>Note: You may assume that both strings contain only lowercase letters.
 *
 * <p>canConstruct("a", "b") -> false
 *
 * <p>canConstruct("aa", "ab") -> false
 *
 * <p>canConstruct("aa", "aab") -> true
 */
public class RansomNote {

  /**
   * Map all chars into array of 26 letters, each element represents how many times the letter has
   * shown up, 0ms
   *
   * <p>$$ Input has finite variants (e.g. there are only 26 distinct letters)
   */
  public boolean solution0(String ransomNote, String magazine) {
    if (magazine.length() < ransomNote.length()) return false;

    int[] caps = new int[26];
    for (char c : ransomNote.toCharArray()) {
      int index = magazine.indexOf(c, caps[c - 'a']);
      if (index == -1) return false;
      caps[c - 'a'] = index + 1;
    }
    return true;
  }

  /** Use HashMap to compare char count, run time 14ms */
  public boolean solution1(String ransomNote, String magazine) {
    Map<Character, Integer> nm = new HashMap<>();
    Map<Character, Integer> mm = new HashMap<>();

    for (char c : ransomNote.toCharArray()) {
      int count = nm.get(c) == null ? 1 : nm.get(c) + 1;
      nm.put(c, count);
    }

    for (char c : magazine.toCharArray()) {
      int count = mm.get(c) == null ? 1 : mm.get(c) + 1;
      mm.put(c, count);
    }

    for (Map.Entry<Character, Integer> entry : nm.entrySet()) {
      char c = entry.getKey();
      int n = entry.getValue();
      if (mm.get(c) == null || mm.get(c) < n) {
        return false;
      }
    }
    return true;
  }

  /** Same idea as solution0, 1ms, 99.65% */
  public boolean solution2(String ransomNote, String magazine) {

    if (ransomNote.length() > magazine.length()) return false;

    int[] chars = new int[26];
    int counts = 0;
    for (int i = 0; i < ransomNote.length(); i++) {
      int index = ransomNote.charAt(i) - 'a';
      if (chars[index] == 0) counts++;
      chars[index]++;
    }

    for (int i = 0; i < magazine.length(); i++) {
      int index = magazine.charAt(i) - 'a';
      if (chars[index] == 1) counts--;
      if (counts == 0) return true;
      chars[index]--;
    }

    return counts == 0;
  }

  /** Same idea as solution0, 4ms 70% */
  private boolean solution3(String ransomNote, String magazine) {

    if (ransomNote.length() > magazine.length()) return false;

    int[] chars = new int[26];
    for (int i = 0; i < magazine.length(); i++) {
      int index = magazine.charAt(i) - 'a';
      chars[index]++;
    }

    for (int i = 0; i < ransomNote.length(); i++) {
      int index = ransomNote.charAt(i) - 'a';
      if (chars[index] <= 0) return false;
      chars[index]--;
    }

    return true;
  }
}
