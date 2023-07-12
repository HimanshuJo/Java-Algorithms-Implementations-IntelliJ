/*
Given an array nums of distinct integers, return all the possible permutations.
You can return the answer in any order.

Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]

Example 3:

Input: nums = [1]
Output: [[1]]
*/

package backtracking;

import java.util.ArrayList;

class AllPossiblePermutationsUtil{

    public boolean linearSearch(ArrayList<Integer>arr, int val){
        for(Integer integer: arr) {
            if(integer==val) return true;
        }
        return false;
    }

    public void dfs(ArrayList<Integer>arr, ArrayList<ArrayList<Integer>>allPermutations,
                    ArrayList<Integer>currPermutation, int sz){
        if(currPermutation.size()==arr.size()){
            /*
                Reference is still same, so in the next dfs calls if I start removing element from currPermutation
                it is also going to remove the element from the past currPermutation's inside allPermutations,
                so it is necessary to create a new copy of the currPermutation
             */
            ArrayList<Integer>nw=new ArrayList<>(currPermutation);
            allPermutations.add(nw);
        } else{
            for(int i=0; i<sz; ++i){
                boolean flag=linearSearch(currPermutation, arr.get(i));
                if(flag) continue;
                currPermutation.add(arr.get(i));
                dfs(arr, allPermutations, currPermutation, sz);
                currPermutation.remove(currPermutation.size()-1);
            }
        }
    }

    public ArrayList<ArrayList<Integer>> genAllPermutations(ArrayList<Integer>arr){
        ArrayList<ArrayList<Integer>>allPerms=new ArrayList<>();
        ArrayList<Integer>currPerm=new ArrayList<>();
        int sz=arr.size();
        dfs(arr, allPerms, currPerm, sz);
        return allPerms;
    }
}
public class AllPossiblePermutations {

    public static void main(String[] args){
        ArrayList<Integer>arr=new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        AllPossiblePermutationsUtil obj=new AllPossiblePermutationsUtil();
        ArrayList<ArrayList<Integer>>allPerms=obj.genAllPermutations(arr);
        for(ArrayList<Integer>entries: allPerms){
            for(int val: entries){
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }
}
