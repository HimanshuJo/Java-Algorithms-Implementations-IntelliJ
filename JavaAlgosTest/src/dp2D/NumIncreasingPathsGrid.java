// 2328. Number of Increasing Paths in a Grid
/*
You are given an m x n integer matrix grid, where you can move from a cell to any adjacent cell in all 4 directions.

Return the number of strictly increasing paths in the grid such that you can start
from any cell and end at any cell. Since the answer may be very large, return it modulo 10^9 + 7.

Two paths are considered different if they do not have exactly the same sequence of visited cells.

Example 1:

Input: grid = [[1,1],[3,4]]
Output: 8
Explanation: The strictly increasing paths are:
- Paths with length 1: [1], [1], [3], [4].
- Paths with length 2: [1 -> 3], [1 -> 4], [3 -> 4].
- Paths with length 3: [1 -> 3 -> 4].
The total number of paths is 4 + 3 + 1 = 8.

Example 2:

Input: grid = [[1],[2]]
Output: 3
Explanation: The strictly increasing paths are:
- Paths with length 1: [1], [2].
- Paths with length 2: [1 -> 2].
The total number of paths is 2 + 1 = 3.
*/

package dp2D;

import java.util.Vector;

class NumIncreasingPathsGridUtil{

    private static final int MOD=1000000000+7;

    public boolean isValid(int rws, int cols, int currw, int curcol){
        return currw>=0&&curcol>=0&&currw<=rws-1&&curcol<=cols-1;
    }

    public int dfs(Vector<Vector<Integer>>grid, int rws, int cols, int currw, int curcol, Vector<Vector<Integer>>memo){
        if(currw>=rws||curcol>=cols||currw<0||curcol<0) return 0;
        if(memo.get(currw).get(curcol)!=-1) return memo.get(currw).get(curcol);
        int ans=0, up=0, down=0, left=0, right=0;
        if(isValid(rws, cols, currw-1, curcol)&&
                   grid.get(currw).get(curcol)<grid.get(currw-1).get(curcol)){
            up=1+dfs(grid, rws, cols, currw-1, curcol, memo);
        }
        if(isValid(rws, cols, currw+1, curcol)&&
                   grid.get(currw).get(curcol)<grid.get(currw+1).get(curcol)){
            down=1+dfs(grid, rws, cols, currw+1, curcol, memo);
        }
        if(isValid(rws, cols, currw, curcol-1)&&
                   grid.get(currw).get(curcol)<grid.get(currw).get(curcol-1)){
            left=1+dfs(grid, rws, cols, currw, curcol-1, memo);
        }
        if(isValid(rws, cols, currw, curcol+1)&&
                   grid.get(currw).get(curcol)<grid.get(currw).get(curcol+1)){
            right=1+dfs(grid, rws, cols, currw, curcol+1, memo);
        }
        ans=(up+down+left+right)%MOD;
        Vector<Integer>curr=memo.get(currw);
        Vector<Integer>nw=new Vector<>(curr);
        nw.set(curcol, ans);
        memo.set(currw, nw);
        return memo.get(currw).get(curcol);
    }

    public int countPaths(Vector<Vector<Integer>>grid){
        int rws=grid.size(), cols=grid.get(0).size();
        int ans=0;
        Vector<Vector<Integer>>memo=new Vector<>();
        for(int i=0; i<=rws; ++i){
            memo.add(new Vector<>());
            for(int j=0; j<=cols; ++j){
                memo.get(i).add(-1);
            }
        }
        for(int i=0; i<rws; ++i){
            for(int j=0; j<cols; ++j){
                ans+=dfs(grid, rws, cols, i, j, memo);
                ans%=MOD;
            }
        }
        return (ans+(rws*cols)%MOD)%MOD;
    }
}

public class NumIncreasingPathsGrid {

    public static void main(String[] args){
        NumIncreasingPathsGridUtil obj=new NumIncreasingPathsGridUtil();
        Vector<Vector<Integer>>grid=new Vector<>();
        int[][] gridArr=new int[][]{{1, 1}, {3, 4}};
        for(int i=0; i<gridArr.length; ++i){
            grid.add(new Vector<>());
            for(int j=0; j<gridArr[i].length; ++j){
                grid.get(i).add(gridArr[i][j]);
            }
        }
        int ans=obj.countPaths(grid);
        System.out.println("ans: "+ans);
    }
}
