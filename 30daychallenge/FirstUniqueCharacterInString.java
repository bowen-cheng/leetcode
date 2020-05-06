public class FirstUniqueCharacterInString {

  /**
   * Map all chars into array of 26 letters, each element represents how many times the letter has
   * shown up, 4ms
   *
   * <p>$$ Input has finite variants (e.g. there are only 26 distinct letters) $$ Map letters into
   * an array, each element's index is the ASCII code of the letter, each element's value is hwo
   * many times the letter has shown up
   */
  public int firstUniqChar(String s) {

    int[] alphabet = new int[26];
    char[] chArr = s.toCharArray();

    for (char c : chArr) {
      alphabet[c - 'a']++;
    }

    for (int i = 0; i < chArr.length; i++) {
      if (alphabet[chArr[i] - 'a'] == 1) {
        return i;
      }
    }
    return -1;
  }

  /** use String.indexOf() and String.lstIndexOf() methods, 21ms */
  public int solution2(String s) {
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      int idx = s.indexOf(c);
      if (idx != -1 && s.lastIndexOf(c) == idx) {
        return i;
      }
    }

    return -1;
  }
}
