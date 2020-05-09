/**
 * You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the
 * coordinate of a point. Check if these points make a straight line in the XY plane.
 *
 * <p>Example 1:
 *
 * <p>Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]] Output: true
 *
 * <p>Example 2:
 *
 * <p>Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]] Output: false
 *
 * <p>Constraints:
 *
 * <p>2 <= coordinates.length <= 1000
 *
 * <p>coordinates[i].length == 2
 *
 * <p>-10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 *
 * <p>coordinates contains no duplicate point.
 */
public class CheckStraightLine {

  public static void main(String[] args) {
    // int[][] input = {{1,2},{2,3},{3,4},{4,5},{5,6},{6,7}};
    int[][] input = {{1, 1}, {2, 2}, {3, 4}, {4, 5}, {5, 6}, {7, 7}};

    System.out.println(checkStraightLine(input));
  }

  /** All points should be at the line formed by the first two points */
  public static boolean checkStraightLine(int[][] coordinates) {

    if (coordinates.length == 2) {
      return true;
    }

    int inc;
    if (coordinates[1][0] == coordinates[0][0]) {
      inc = 0;
    } else {
      inc = (coordinates[1][1] - coordinates[0][1]) / (coordinates[1][0] - coordinates[0][0]);
    }

    for (int[] coordinate : coordinates) {

      int deltaX = coordinate[0] - coordinates[0][0];
      int deltaY = coordinate[1] - coordinates[0][1];
      int expectedDeltaY = deltaX * inc;

      if (expectedDeltaY != deltaY) {
        return false;
      }
    }
    return true;
  }
}
