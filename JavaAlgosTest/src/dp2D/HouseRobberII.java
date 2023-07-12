/*
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed.

All houses at this place are arranged in a circle. That means the first house is the
neighbor of the last one. Meanwhile, adjacent houses have a security system connected,
and it will automatically contact the police if two adjacent houses were broken into on
the same night.

Given an integer array nums representing the amount of money of each house, return the
maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
because they are adjacent houses.

Example 2:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 3:

Input: nums = [1,2,3]
Output: 3

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 1000
 */

package dp2D;

import java.util.Vector;
class HouseRobberIIUtil{

    public int dfs(Vector<Integer>nums, int startIdx, int endIdx, int[][] memo){
        if(endIdx<startIdx) return 0;
        if(memo[endIdx][startIdx]!=-1) return memo[endIdx][startIdx];
        int maxx=Math.max(nums.get(endIdx)+dfs(nums, startIdx, endIdx-2, memo), dfs(nums, startIdx, endIdx-1, memo));
        return memo[endIdx][startIdx]=maxx;
    }

    public int rob(Vector<Integer>nums){
        int sz=nums.size();
        int[][] memo=new int[101][2];
        for(int i=0; i<101; ++i){
            for(int j=0; j<2; ++j){
                memo[i][j]=-1;
            }
        }
        if(nums.size()==1) return nums.get(0);
        int startIdx=0, endIdx=sz;
        int ans1=dfs(nums, startIdx, endIdx-2, memo);
        for(int i=0; i<101; ++i){
            for(int j=0; j<2; ++j){
                memo[i][j]=-1;
            }
        }
        int ans2=dfs(nums, startIdx+1, endIdx-1, memo);
        return Math.max(ans1, ans2);
    }
}

public class HouseRobberII {

    public static void main(String[] args){
        Vector<Integer>nums = new Vector<>();
        nums.add(2);
        nums.add(3);
        nums.add(2);
        HouseRobberIIUtil obj=new HouseRobberIIUtil();
        int ans1=obj.rob(nums);
        System.out.println("ans1: "+ans1);
    }
}
