// Approach: 2d backtracking with BFS to calculate minDist for every permutation of n Buildings placed on the grid
// Combination of N-queens and 0-1 matrix i.e. Nearest 1s
// Time: O(HW * Permutations(HW, n)) meaning H*W options to place n no. of buildings
// Space: O(H*W)

import java.util.*;

public class OptimalPlacementOfBuildingsInAGrid {

    public static void main(String[] args) {
        BuildingPlacement buildingPlacement = new BuildingPlacement();
        System.out.println(buildingPlacement.findMinDistance(5,6,4));
    }

    public static class BuildingPlacement {
        int minDist;

        public int findMinDistance(int H, int W, int n) {
            int[][] grid = new int[H][W];
            minDist = Integer.MAX_VALUE;
            backtrack(grid, 0, 0, n, H, W);
            return minDist;
        }

        // BFS for finding minDistance of each combination
        private void bfs(int[][] grid, int H, int W) {
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[H][W];

            for (int i = 0; i<H; i++) {
                for (int j = 0; j<W; j++) {
                    if (grid[i][j] == 1) {
                        q.add(new int[]{i,j});
                        visited[i][j] = true;
                    }
                }
            }
            int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
            int dist = 1;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i<size; i++) {
                    int[] curr = q.poll();
                    for (int[] dir : dirs) {
                        int nr = curr[0] + dir[0];
                        int nc = curr[1] + dir[1];                                      // 0: parking
                        if (nr > 0 && nc > 0 && nr < H && nc < W && !visited[nr][nc] && grid[nr][nc] == 0) {
                            q.add(new int[] {nr,nc});
                            visited[nr][nc] = true;
                        }
                    }
                }
                dist++;
            }
            minDist = Math.min(minDist, dist);
        }

        // to create all combinations
        private void backtrack(int[][] grid, int r, int c, int n, int H, int W) {

            if (n == 0) {
                bfs(grid, H, W);
                return;
            }
            if (c == W) {
                c = 0;
                r++;
            }

            // 2d backtracking
            for (int i = r; i<H; i++) {
                for (int j = c; j<W; j++) {
                    // action
                    grid[i][j] = 1;         // building
                    // recurse
                    backtrack(grid, i, j+1, n-1, H, W);
                    // backtrack
                    grid[i][j] = 0;         // parking
                }
            }
        }
    }
}