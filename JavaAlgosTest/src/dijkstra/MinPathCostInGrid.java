// 2304. Minimum Path Cost in a Grid
/*
You are given a 0-indexed m x n integer matrix grid consisting of distinct integers from 0 to m * n - 1.
You can move in this matrix from a cell to any other cell in the next row.

That is, if you are in cell (x, y) such that x < m - 1,
you can move to any of the cells (x + 1, 0), (x + 1, 1), ..., (x + 1, n - 1).

Note that it is not possible to move from cells in the last row.

Each possible move has a cost given by a 0-indexed 2D array moveCost of size (m * n) x n,
where moveCost[i][j] is the cost of moving from a cell with value i to a cell in column j of the next row.

The cost of moving from cells in the last row of grid can be ignored.

The cost of a path in grid is the sum of all values of cells visited plus the sum of costs
of all the moves made. Return the minimum cost of a path that starts from any cell in the first
row and ends at any cell in the last row.

Example 1:
Input: grid = [[5,3],[4,0],[2,1]], moveCost = [[9,8],[1,5],[10,12],[18,6],[2,4],[14,3]]
Output: 17
Explanation: The path with the minimum possible cost is the path 5 -> 0 -> 1.
- The sum of the values of cells visited is 5 + 0 + 1 = 6.
- The cost of moving from 5 to 0 is 3.
- The cost of moving from 0 to 1 is 8.
So the total cost of the path is 6 + 3 + 8 = 17.

Example 2:
Input: grid = [[5,1,2],[4,0,3]], moveCost = [[12,10,15],[20,23,8],[21,7,1],[8,1,13],[9,10,25],[5,3,2]]
Output: 6
Explanation: The path with the minimum possible cost is the path 2 -> 3.
- The sum of the values of cells visited is 2 + 3 = 5.
- The cost of moving from 2 to 3 is 1.
So the total cost of this path is 5 + 1 = 6.

Constraints:

m == grid.length
n == grid[i].length
2 <= m, n <= 50
grid consists of distinct integers from 0 to m * n - 1.
moveCost.length == m * n
moveCost[i].length == n
1 <= moveCost[i][j] <= 100
 */

package dijkstra;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class MinPathCostInGridUtil3{

    public int minPathCost(ArrayList<Integer>[] grid, ArrayList<Integer>[] moveCost){
        int rws=grid.length, cols=grid[0].size();
        int res=Integer.MAX_VALUE;
        int[][] dp=new int[rws+1][cols+1];
        for(int i=0; i<rws; ++i){
            for(int j=0; j<cols; ++j){
                dp[i][j]=Integer.MAX_VALUE;
            }
        }
        for(int j=0; j<cols; ++j){
            dp[rws-1][j]=grid[rws-1].get(j);
        }
        for(int i=rws-2; i>=0; --i){
            for(int j=cols-1; j>=0; --j){
                for(int k=0; k<cols; ++k){
                    int cost=dp[i+1][k]+moveCost[grid[i].get(j)].get(k)+grid[i].get(j);
                    dp[i][j]=Math.min(dp[i][j], cost);
                }
            }
        }
        for(int j=0; j<cols; ++j){
            res=Math.min(dp[0][j], res);
        }
        return res;
    }
}

class CustomComparator implements Comparator<ArrayList<Integer>> {

    @Override
    public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
        return o1.get(0)-o2.get(0);
    }
}

// Dijkstra
class MinPathCostInGridUtil2{

    public int minPathCost(ArrayList<Integer>[] grid, ArrayList<Integer>[] moveCost){
        int rws=grid.length, cols=grid[0].size();
        int res=-1;
        int[][] seenCost=new int[rws+1][cols+1];
        for(int i=0; i<rws; ++i){
            for(int j=0; j<cols; ++j){
                seenCost[i][j]=Integer.MAX_VALUE;
            }
        }
        PriorityQueue<ArrayList<Integer>>pq=new PriorityQueue<>(new CustomComparator());
        for(int j=0; j<cols; ++j){
            ArrayList<Integer>temp=new ArrayList<>();
            temp.add(grid[0].get(j));
            temp.add(0);
            temp.add(j);
            pq.offer(temp);
        }
        while(!pq.isEmpty()){
            int sz=pq.size();
            while(sz-- >0){
                ArrayList<Integer>curr=pq.poll();
                assert curr!=null;
                int costTillHere=curr.get(0), currw=curr.get(1), curcol=curr.get(2);
                if(currw==rws-1){
                    res=costTillHere;
                    return res;
                }
                for(int cl=0; currw+1<rws&&cl<cols; ++cl){
                    int newCost=costTillHere+moveCost[grid[currw].get(curcol)].get(cl)+grid[currw+1].get(cl);
                    if(newCost<seenCost[currw+1][cl]){
                        seenCost[currw+1][cl]=newCost;
                        ArrayList<Integer>nw=new ArrayList<>();
                        nw.add(newCost);
                        nw.add(currw+1);
                        nw.add(cl);
                        pq.offer(nw);
                    }
                }
            }
        }
        return res;
    }
}

// DFS
class MinPathCostInGridUtil{

    public int dfs(ArrayList<Integer>[] grid, ArrayList<Integer>[] moveCost, int rws, int cols, int currw, int curcol,
                   int[][] memo){
        if(currw==rws-1){
            return grid[currw].get(curcol);
        }
        if(memo[currw][curcol]!=-1) return memo[currw][curcol];
        int fncost=Integer.MAX_VALUE;
        for(int j=0; j<cols; ++j){
            int curcost=0;
            curcost+=grid[currw].get(curcol);
            int nwrw=currw+1;
            curcost+=moveCost[grid[currw].get(curcol)].get(j);
            curcost+=dfs(grid, moveCost, rws, cols, nwrw, j, memo);
            fncost=Math.min(fncost, curcost);
        }
        return memo[currw][curcol]=fncost;
    }

    public int minPathCost(ArrayList<Integer>[] grid, ArrayList<Integer>[] moveCost){
        int ans=Integer.MAX_VALUE;
        int rws=grid.length, cols=grid[0].size();
        int currw=0, curcol=0;
        int[][] memo=new int[rws+1][cols+1];
        for(int i=0; i<rws; ++i){
            for(int j=0; j<cols; ++j){
                memo[i][j]=-1;
            }
        }
        for(int j=0; j<cols; ++j){
            curcol=j;
            ans=Math.min(ans, dfs(grid, moveCost, rws, cols, currw, curcol, memo));
        }
        return ans;
    }
}

public class MinPathCostInGrid {

    public static void main(String[] args){
        ArrayList<Integer>[] grid=new ArrayList[3];
        for(int i=0; i<3; ++i){
            grid[i]=new ArrayList<>();
            if(i==0){
                grid[0].add(5);
                grid[0].add(3);
            } else if(i==1){
                grid[1].add(4);
                grid[1].add(0);
            } else{
                grid[2].add(2);
                grid[2].add(1);
            }
        }
        ArrayList<Integer>[] moveCost=new ArrayList[6];
        for(int i=0; i<6; ++i){
            moveCost[i]=new ArrayList<>();
            if(i==0){
                moveCost[i].add(9);
                moveCost[i].add(8);
            } else if(i==1){
                moveCost[i].add(1);
                moveCost[i].add(5);
            } else if(i==2){
                moveCost[i].add(10);
                moveCost[i].add(12);
            } else if(i==3){
                moveCost[i].add(18);
                moveCost[i].add(6);
            } else if(i==4){
                moveCost[i].add(2);
                moveCost[i].add(4);
            } else{
                moveCost[i].add(14);
                moveCost[i].add(3);
            }
        }
        MinPathCostInGridUtil obj=new MinPathCostInGridUtil();
        System.out.println("ans: "+obj.minPathCost(grid, moveCost));
        System.out.println("------- ******* -------");
        MinPathCostInGridUtil2 obj2=new MinPathCostInGridUtil2();
        System.out.println("ans2: "+obj2.minPathCost(grid, moveCost));
        System.out.println("------- ******* -------");
        MinPathCostInGridUtil3 obj3=new MinPathCostInGridUtil3();
        System.out.println("ans3: "+obj3.minPathCost(grid, moveCost));
    }
}
