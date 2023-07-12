// 1388. Pizza With 3n Slices
/*
There is a pizza with 3n slices of varying size, you and your friends will take slices of pizza as follows:

You will pick any pizza slice.
Your friend Alice will pick the next slice in the anti-clockwise direction of your pick.
Your friend Bob will pick the next slice in the clockwise direction of your pick.
Repeat until there are no more slices of pizzas.

Given an integer array slices that represent the sizes of the pizza slices in a clockwise direction,
return the maximum possible sum of slice sizes that you can pick.

Input: slices = [1,2,3,4,5,6]
Output: 10

Explanation: Pick pizza slice of size 4, Alice and Bob will pick slices with size 3 and 5 respectively.
Then Pick slices with size 6, finally Alice and Bob will pick slice of size 2 and 1 respectively. Total = 4 + 6.

Input: slices = [8,9,8,6,1,1]
Output: 16
Explanation: Pick pizza slice of size 8 in each turn. If you pick slice with size 9 your partners
will pick slices of size 8.

Constraints:

3 * n == slices.length
1 <= slices.length <= 500
1 <= slices[i] <= 1000
*/

package dp2D;

import java.util.Vector;

class PizzaWith3nSlicesUtil{

    int dfs(Vector<Integer>slices, int begin, int end, int k, Vector<Vector<Integer>>memo){
        if(begin>end||k<=0) return 0;
        if(memo.get(begin).get(k)!=-1) return memo.get(begin).get(k);
        int pk=slices.get(begin)+dfs(slices, begin+2, end, k-3, memo);
        int ntpk=dfs(slices, begin+1, end, k, memo);
        Vector<Integer>curr=memo.get(begin);
        Vector<Integer>nw=new Vector<>(curr);
        nw.set(k, Math.max(pk, ntpk));
        memo.set(begin, nw);
        return memo.get(begin).get(k);
    }

    int maxSizeSlices(Vector<Integer>slices){
        int sz=slices.size();
        Vector<Vector<Integer>>memo=new Vector<>();
        for(int i=0; i<=sz+1; ++i){
            memo.add(new Vector<Integer>());
            for(int j=0; j<=sz+1; ++j){
                memo.get(i).add(-1);
            }
        }
        int startIdx=0, endIdx=sz-2;
        int ans1=dfs(slices, startIdx, endIdx, sz, memo);
        memo.clear();
        for(int i=0; i<=sz+1; ++i){
            memo.add(new Vector<Integer>());
            for(int j=0; j<=sz+1; ++j){
                memo.get(i).add(-1);
            }
        }
        startIdx=1;
        endIdx=sz-1;
        int ans2=dfs(slices, startIdx, endIdx, sz, memo);
        return Math.max(ans1, ans2);
    }
}

public class PizzaWith3nSlices {

    public static void main(String[] args){
        PizzaWith3nSlicesUtil obj=new PizzaWith3nSlicesUtil();
        Vector<Integer>slices=new Vector<>();
        slices.add(1);
        slices.add(2);
        slices.add(3);
        slices.add(4);
        slices.add(5);
        slices.add(6);
        int ans1=obj.maxSizeSlices(slices);
        System.out.println("ans: "+ans1);
    }
}
