// 2212. Maximum Points in an Archery Competition
/*
Alice and Bob are opponents in an archery competition. The competition has set the following rules:

Alice first shoots numArrows arrows and then Bob shoots numArrows arrows.

The points are then calculated as follows:

The target has integer scoring sections ranging from 0 to 11 inclusive.

For each section of the target with score k (in between 0 and 11), say Alice and Bob
have shot ak and bk arrows on that section respectively.

If ak >= bk, then Alice takes k points.
If ak < bk, then Bob takes k points.
However, if ak == bk == 0, then nobody takes k points.

For example, if Alice and Bob both shot 2 arrows on the section with score 11, then Alice takes 11 points.
On the other hand, if Alice shot 0 arrows on the section with score 11 and Bob shot 2 arrows on that
same section, then Bob takes 11 points.

You are given the integer numArrows and an integer array aliceArrows of size 12,
which represents the number of arrows Alice shot on each scoring section from 0 to 11.
Now, Bob wants to maximize the total number of points he can obtain.

Return the array bobArrows which represents the number of arrows Bob shot on each scoring
section from 0 to 11. The sum of the values in bobArrows should equal numArrows.

If there are multiple ways for Bob to earn the maximum total points, return any one of them.

Input: numArrows = 9, aliceArrows = [1,1,0,1,0,0,2,1,0,1,2,0]
Output: [0,0,0,0,1,1,0,0,1,2,3,1]
Explanation: The table above shows how the competition is scored.
Bob earns a total point of 4 + 5 + 8 + 9 + 10 + 11 = 47.
It can be shown that Bob cannot obtain a score higher than 47 points.

Input: numArrows = 3, aliceArrows = [0,0,1,0,0,0,0,0,0,0,0,2]
Output: [0,0,0,0,0,0,0,0,1,1,1,0]
Explanation: The table above shows how the competition is scored.
Bob earns a total point of 8 + 9 + 10 = 27.
It can be shown that Bob cannot obtain a score higher than 27 points

Constraints:

1 <= numArrows <= 10^5
aliceArrows.length == bobArrows.length == 12
0 <= aliceArrows[i], bobArrows[i] <= numArrows
sum(aliceArrows[i]) == numArrows
*/

package backtracking;

import java.util.ArrayList;

class MaxPointsArcheryCompUtil{

    private ArrayList<Integer>fnres;
    private int currMaxSum;

    MaxPointsArcheryCompUtil() {
        currMaxSum=0;
        this.fnres=new ArrayList<>();
    }

    public ArrayList<Integer> getFnRes(){
        return this.fnres;
    }

    public int getCurrMaxSum(){
        return this.currMaxSum;
    }

    public void setFnRes(ArrayList<Integer>fnres){
        this.fnres=fnres;
    }

    public void setCurrMaxSum(int currMaxSum){
        this.currMaxSum=currMaxSum;
    }

    public void dfs(int n, int numArrows, ArrayList<Integer>aliceArrows, int currsum, ArrayList<Integer>res){
        if(n==-1||numArrows<=0){
            if(currsum>getCurrMaxSum()){
                setCurrMaxSum(currsum);
                if(numArrows>0){
                    res.set(0, res.get(0)+numArrows);
                }
                ArrayList<Integer>temp=new ArrayList<>(res);
                setFnRes(temp);
            }
            return;
        }
        int req=aliceArrows.get(n)+1;
        if(req<=numArrows){
            res.set(n, req);
            dfs(n-1, numArrows-req, aliceArrows, currsum+n, res);
            res.set(n, 0);
        }
        dfs(n-1, numArrows, aliceArrows, currsum, res);
    }

    void maximumBobPoints(int numArrows, ArrayList<Integer>aliceArrows){
        ArrayList<Integer>res=new ArrayList<>();
        int currsum=0;
        for(int i=0; i<12; ++i){
            res.add(0);
        }
        dfs(11, numArrows, aliceArrows, currsum, res);
    }
}
public class MaxPointsArcheryComp {

    public static void main(String[] args){
        MaxPointsArcheryCompUtil obj=new MaxPointsArcheryCompUtil();
        int numArrows=9;
        int[] arr=new int[]{1,1,0,1,0,0,2,1,0,1,2,0};
        ArrayList<Integer>aliceArrows=new ArrayList<>();
        for(int x: arr){
            aliceArrows.add(x);
        }
        obj.maximumBobPoints(numArrows, aliceArrows);
        for(int vals: obj.getFnRes()){
            System.out.print(vals+" ");
        }
    }
}
