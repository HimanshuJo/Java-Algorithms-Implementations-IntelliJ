// 2218. Maximum Value of K Coins From Piles
/*
There are n piles of coins on a table. Each pile consists of a positive number of coins
of assorted denominations.

In one move, you can choose any coin on top of any pile, remove it, and add it to your wallet.

Given a list piles, where piles[i] is a list of integers denoting the composition of the
i'th pile from top to bottom, and a positive integer k, return the maximum total value
of coins you can have in your wallet if you choose exactly k coins optimally.

Example 1:

Input: piles = [[1,100,3],[7,8,9]], k = 2
Output: 101
Explanation:
The above diagram shows the different ways we can choose k coins.
The maximum total we can obtain is 101.

Example 2:

Input: piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
Output: 706
Explanation:
The maximum total can be obtained if we choose all coins from the last pile.

Constraints:

n == piles.length
1 <= n <= 1000
1 <= piles[i][j] <= 10^5
1 <= k <= sum(piles[i].length) <= 2000
*/

/*
Solution:

Either pick all k from previous piles or choose j from current piles and pick k-j from the previous piles

Recurrence relation:

    Base cases:

        f(0, k)=0, f(n, 0)=0

    f(n, k) -> represents the max sum of coins collected from first n piles and choosing k top coins

    f(n, k) = max(f(n-1, k), max(f(n-1, k-j-1)+sum(0, j) for j=0 to min(k, p[n-1].size())

              where f(n-1, k) represents picking k from the previous n-1 piles

              f(n-1, k-(j+1)) represents picking j+1 from the current pile and k-j-1 from previous n-1 piles
 */

package dp2D;

import java.util.Vector;
class MaxValueKCoinFromPilesUtil{

    public int dfs(Vector<Vector<Integer>>piles, int k, int pilesSize, int idx, int[][] memo){
        if(k==0||idx>=pilesSize) return 0;
        if(memo[idx][k]!=-1) return memo[idx][k];
        int ans, ntpk=Integer.MIN_VALUE, pk=Integer.MIN_VALUE;
        ntpk=Math.max(ntpk, dfs(piles, k, pilesSize, idx+1, memo));
        int curpilesz=piles.get(idx).size();
        curpilesz--;
        int tempans=0;
        for(int j=0; j<=Math.min(curpilesz, k-1); ++j){
            tempans+=piles.get(idx).get(j);
            pk=Math.max(pk, tempans+dfs(piles, k-(j+1), pilesSize, idx+1, memo));
        }
        ans=Math.max(ntpk, pk);
        return memo[idx][k]=ans;
    }

    public int maxValueOfCoins(Vector<Vector<Integer>>piles, int k){
        int pilesSize=piles.size();
        int idx=0;
        int[][] memo=new int[1010][2010];
        for(int i=0; i<=1000; ++i){
            for(int j=0; j<=2000; ++j){
                memo[i][j]=-1;
            }
        }
        return dfs(piles, k, pilesSize, idx, memo);
    }
}

public class MaxValueKCoinsFromPiles {

    public static void main(String[] args){
        MaxValueKCoinFromPilesUtil obj=new MaxValueKCoinFromPilesUtil();
        int[][] pilesArray=new int[][]{{1,100,3}, {7,8,9}};
        Vector<Vector<Integer>>piles=new Vector<>();
        for(int i=0; i<pilesArray.length; ++i){
            piles.add(new Vector<>());
            for(int j=0; j<pilesArray[i].length; ++j){
                piles.get(i).add(pilesArray[i][j]);
            }
        }
        int k=2;
        int ans=obj.maxValueOfCoins(piles, k);
        System.out.println("ans: "+ans);
    }
}
