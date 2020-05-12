/**
 * An image is represented by a 2-D array of integers, each integer representing the pixel value of
 * the image (from 0 to 65535).
 *
 * <p>Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood
 * fill, and a pixel value newColor, "flood fill" the image.
 *
 * <p>To perform a "flood fill", consider the starting pixel, plus any pixels connected
 * 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels
 * connected 4-directionally to those pixels (also with the same color as the starting pixel), and
 * so on. Replace the color of all of the aforementioned pixels with the newColor.
 *
 * <p>At the end, return the modified image.
 *
 * <p>Example 1: Input: image = [[1,1,1],[1,1,0],[1,0,1]] sr = 1, sc = 1, newColor = 2
 *
 * <p>Output: [[2,2,2],[2,2,0],[2,0,1]]
 *
 * <p>Explanation: From the center of the image (with position (sr, sc) = (1, 1)), all pixels
 * connected by a path of the same color as the starting pixel are colored with the new color. Note
 * the bottom corner is not colored 2, because it is not 4-directionally connected to the starting
 * pixel.
 *
 * <p>Note:
 *
 * <p>The length of image and image[0] will be in the range [1, 50].
 *
 * <p>The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
 *
 * <p>The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
 */
public class FloodFill {

  /** $tag$ DFS */
  public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
    int sColor = image[sr][sc];

    // $$ only pain the image if it is not already painted: if sColor != newColor
    if (sColor != newColor) fill(image, sr, sc, sColor, newColor);
    return image;
  }

  public void fill(int[][] image, int r, int c, int sColor, int newColor) {
    if (image[r][c] == sColor) {
      image[r][c] = newColor;
      if (r + 1 < image.length) fill(image, r + 1, c, sColor, newColor);
      if (c + 1 < image[0].length) fill(image, r, c + 1, sColor, newColor);
      if (r - 1 >= 0) fill(image, r - 1, c, sColor, newColor);
      if (c - 1 >= 0) fill(image, r, c - 1, sColor, newColor);
    }
  }
}
