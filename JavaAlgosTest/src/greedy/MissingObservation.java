/*
You have observations of n + m 6-sided dice rolls with each face numbered from 1 to 6.

n of the observations went missing, and you only have the observations of m rolls.

Fortunately, you have also calculated the average value of the n + m rolls.

You are given an integer array rolls of length m where rolls[i] is the value of
the ith observation. You are also given the two integers mean and n.

Return an array of length n containing the missing observations such that
the average value of the n + m rolls is exactly mean. If there are multiple
valid answers, return any of them. If no such array exists, return an empty array.

The average value of a set of k numbers is the sum of the numbers divided by k.

Note that mean is an integer, so the sum of the n + m rolls should be divisible by n + m.

Example 1:

Input: rolls = [3,2,4,3], mean = 4, n = 2
Output: [6,6]
Explanation: The mean of all n + m rolls is (3 + 2 + 4 + 3 + 6 + 6) / 6 = 4.
*/

package greedy;

import java.util.Vector;

class MissingObservationUtil{

    public Vector<Integer> missingRolls(Vector<Integer>rolls, int mean, int n){
        int tempSum=0;
        for(int roll: rolls){
            tempSum+=roll;
        }
        int othSum=mean*(n+rolls.size())-tempSum;
        if(othSum<n||othSum>6*n) return new Vector<>();
        int part=othSum/n, rem=othSum%n;
        Vector<Integer>ans=new Vector<>();
        for(int i=0; i<n; ++i){
            ans.add(part);
        }
        for(int i=0; i<rem; ++i){
            ans.set(i, ans.get(i)+1);
        }
        return ans;
    }
}

public class MissingObservation {

    public static void main(String[] arguments){
        MissingObservationUtil obj=new MissingObservationUtil();
        int[] rollsArr=new int[]{3,2,4,3};
        Vector<Integer>rolls=new Vector<>();
        for(int vals: rollsArr){
            rolls.add(vals);
        }
        int mean=4, n=2;
        Vector<Integer>res=obj.missingRolls(rolls, mean, n);
        for(int vals: res){
            System.out.print(vals+" ");
        }
        System.out.println();
    }
}
