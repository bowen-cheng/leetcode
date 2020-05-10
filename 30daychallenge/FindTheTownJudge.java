import java.util.HashMap;
import java.util.Map;

/**
 * In a town, there are N people labelled from 1 to N. There is a rumor that one of these people is
 * secretly the town judge.
 *
 * <p>If the town judge exists, then:
 *
 * <p>1. The town judge trusts nobody.
 *
 * <p>2. Everybody (except for the town judge) trusts the town judge.
 *
 * <p>3. There is exactly one person that satisfies properties 1 and 2.
 *
 * <p>You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled
 * a trusts the person labelled b.
 *
 * <p>If the town judge exists and can be identified, return the label of the town judge. Otherwise,
 * return -1.
 *
 * <p>Example 1: Input: N = 2, trust = [[1,2]] Output: 2
 *
 * <p>Example 2: Input: N = 3, trust = [[1,3],[2,3]] Output: 3
 *
 * <p>Example 3: Input: N = 3, trust = [[1,3],[2,3],[3,1]] Output: -1
 *
 * <p>Example 4: Input: N = 3, trust = [[1,2],[2,3]] Output: -1
 *
 * <p>Example 5: Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]] Output: 3
 *
 * <p>Note:
 *
 * <p>1 <= N <= 1000 trust.length <= 10000
 *
 * <p>trust[i] are all different
 *
 * <p>trust[i][0] != trust[i][1]
 *
 * <p>1 <= trust[i][0], trust[i][1] <= N
 */
public class FindTheTownJudge {

  /**
   * $$ Consider "trust" as a graph, all pairs are directed edge. we need that vertex that has N-1
   * in-edge and 0 out-edge
   */
  public int findJudge1(int N, int[][] trust) {
    // Use N+1 capacity and abandon index 0 because all people are labeled starting from 1 to N
    int[] inEdge = new int[N+1];
    int[] outEdge = new int[N+1];

    for (int[] directedEdge : trust) {
      int fromVertex = directedEdge[0];
      int toVertex = directedEdge[1];
      inEdge[toVertex]++;
      outEdge[fromVertex]++;
    }

    for (int i = 1; i <= N; i++) {
        if ((inEdge[i] == N-1) && (outEdge[i] == 0)) {
          return i;
        }
    }
    return -1;
  }

  /** Use hash map to represent the two constraints (in and out edges) */
  public int findJudge2(int N, int[][] trust) {

    if (N == 1) {
      return 1;
    }

    HashMap<Integer, Integer> trustCountMap = new HashMap<>();
    HashMap<Integer, Boolean> trustsSomeone = new HashMap<>();

    for (int[] aTrust : trust) {
      int truster = aTrust[0];
      int trustee = aTrust[1];

      trustCountMap.merge(trustee, 1, Integer::sum);
      trustsSomeone.put(truster, true);
    }

    for (Map.Entry<Integer, Integer> entry : trustCountMap.entrySet()) {
      int person = entry.getKey();
      int trustCount = entry.getValue();

      /* (everyone trusts him) && (he trusts no one) */
      if (trustCount == (N - 1) && trustsSomeone.get(person) == null) {
        return person;
      }
    }
    return -1;
  }
}
