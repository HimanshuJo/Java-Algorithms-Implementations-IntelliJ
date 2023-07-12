// D. Make Them Equal
/*
You have an array of integers a of size n. Initially, all elements of the array are equal to 1.
You can perform the following operation: choose two integers i (1≤i≤n) and x (x>0),
and then increase the value of ai by ⌊ai/x⌋ (i.e. make ai=ai+⌊ai/x⌋).

After performing all operations, you will receive ci coins for all such i that ai=bi.

Your task is to determine the maximum number of coins that you can receive by performing
no more than k operations.

Input
The first line contains a single integer t (1≤t≤100) — the number of test cases.

The first line of each test case contains two integers n and k (1≤n≤10^3;0≤k≤10^6) — the size of the array
and the maximum number of operations, respectively.

The second line contains n integers b1,b2,…,bn (1≤bi≤10^3).

The third line contains n integers c1,c2,…,cn (1≤ci≤10^6).

The sum of n over all test cases does not exceed 10^3.

Output
For each test case, print one integer — the maximum number of coins that you can get by
performing no more than k operations.

Example
input
4
4 4
1 7 5 2
2 6 5 2
3 0
3 5 2
5 4 7
5 9
5 2 5 6 3
5 9 1 9 7
6 14
11 4 6 2 8 16
43 45 9 41 15 38

output
9
0
30
167
*/

package dp2D;

import java.util.Scanner;
import java.util.Vector;

class MakeThemEqualUtil{

    public static final int N=1001;

    public long ans=0;

    public Vector<Long>cost;

    MakeThemEqualUtil(){
        cost=new Vector<>();
        for(int i=0; i<N; ++i){
            cost.add((long)N);
        }
    }

    public void makeCost(){
        cost.set(1, 0L);
        for(int i=1; i<N; ++i){
            for(int j=1; j<=i; ++j){
                long val=i+i/j;
                if(val<N){
                    cost.set((int)val, Math.min(cost.get((int)val), cost.get(i)+1));
                }
            }
        }
    }

    public long dfs(int k, Vector<Integer>B, Vector<Integer>C, int idx, Vector<Vector<Long>>memo){
        if(idx<0) return 0;
        if(k<0) return 0;
        if(memo.get(idx).get(k)!=-1) return memo.get(idx).get(k);
        if(cost.get(B.get(idx))>k){
            Vector<Long>nw=memo.get(idx);
            nw.set(k, dfs(k, B, C, idx-1, memo));
            memo.set(idx, nw);
            return memo.get(idx).get(k);
        } else{
            Vector<Long>nw=memo.get(idx);
            nw.set(k, Math.max(C.get(idx)+dfs((int)(k-cost.get(B.get(idx))), B, C, idx-1, memo),
                      dfs(k, B, C, idx-1, memo)));
            memo.set(idx, nw);
            return memo.get(idx).get(k);
        }
    }

    public void init(Scanner scn){
        int n, k;
        n=scn.nextInt();
        k=scn.nextInt();
        Vector<Integer>B=new Vector<>();
        Vector<Integer>C=new Vector<>();
        for(int i=0; i<n; ++i){
            B.add(scn.nextInt());
        }
        for(int i=0; i<n; ++i){
            C.add(scn.nextInt());
        }
        int sum=0;
        for(int nums: B){
            sum+=cost.get(nums);
        }
        k=Math.min(k, sum);
        Vector<Vector<Long>>memo=new Vector<>();
        for(int i=0; i<N; ++i){
            memo.add(new Vector<>());
            for(int j=0; j<k+1; ++j){
                memo.get(i).add(-1L);
            }
        }
        ans=dfs(k, B, C, n-1, memo);
    }
}

public class MakeThemEqual {

    public static void main(String[] arguments){
        MakeThemEqualUtil obj=new MakeThemEqualUtil();
        obj.makeCost();
        int t;
        Scanner scn=new Scanner(System.in);
        t=scn.nextInt();
        while(t-- >0){
            obj.init(scn);
            System.out.println("ans: "+obj.ans);
        }
    }
}
