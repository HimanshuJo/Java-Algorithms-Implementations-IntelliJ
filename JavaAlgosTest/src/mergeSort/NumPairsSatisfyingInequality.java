// 2426. Number of Pairs Satisfying Inequality
/*
You are given two 0-indexed integer arrays nums1 and nums2, each of size n, and an integer diff.
Find the number of pairs (i, j) such that:

0 <= i < j <= n - 1 and
nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
Return the number of pairs that satisfy the conditions.

Example 1:

Input: nums1 = [3,2,5], nums2 = [2,2,1], diff = 1
Output: 3
Explanation:
There are 3 pairs that satisfy the conditions:
1. i = 0, j = 1: 3 - 2 <= 2 - 2 + 1. Since i < j and 1 <= 1, this pair satisfies the conditions.
2. i = 0, j = 2: 3 - 5 <= 2 - 1 + 1. Since i < j and -2 <= 2, this pair satisfies the conditions.
3. i = 1, j = 2: 2 - 5 <= 2 - 1 + 1. Since i < j and -3 <= 2, this pair satisfies the conditions.
Therefore, we return 3.

Example 2:

Input: nums1 = [3,-1], nums2 = [-2,2], diff = -1
Output: 0
Explanation:
Since there does not exist any pair that satisfies the conditions, we return 0.

Constraints:

n == nums1.length == nums2.length
2 <= n <= 10^5
-10^4 <= nums1[i], nums2[i] <= 10^4
-10^4 <= diff <= 10^4
*/

/*
    Given that we have to find i, j such that 0 <= i < j <= n-1 and
    nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff

    Step1:

        Rewriting the inequality:

            nums1[i] - nums2[i] <= (nums1[j] - nums2[j]) + diff

    Step2:

        Make a new array/vector c which stores nums1[i] - nums2[i]
        or c[i] = nums1[i] - nums2[i] for 0 <= i <= n-1

    Step3:

        Problem reduces to find i, j such that 0 <= i < j <= n-1 and
        c[i] <= c[j] + diff
*/

package mergeSort;

import java.util.Collections;
import java.util.Vector;

class NumPairsSatisfyingInequalityUtil{

    public long count;

    public long getCount(){
        return count;
    }

    public void setCount(long count){
        this.count=count;
    }

    public void customSort(Vector<Integer>nums, int start, int end){
        Vector<Integer>normal=new Vector<>();
        Vector<Integer>sorted=new Vector<>();
        int idx=0;
        while(idx<start){
            normal.add(nums.get(idx));
            idx++;
            if(idx>=nums.size()) break;
        }
        while(idx<end){
            sorted.add(nums.get(idx));
            idx++;
            if(idx>=nums.size()) break;
        }
        Collections.sort(sorted);
        nums.clear();
        nums.addAll(normal);
        nums.addAll(sorted);
    }

    public void checkCount(Vector<Integer>nums, int start, int mid, int end, int diff){
        int left=start, right=mid+1;
        while((left<=mid)&&(right<=end)){
            if(nums.get(left)<=nums.get(right)+diff){
                count+=(end-right+1);
                left++;
            } else right++;
        }
        customSort(nums, start, end);
        return;
    }

    public void mergeSort(Vector<Integer>nums, int start, int end, int diff){
        if(start==end) return;
        int mid=(end+start)/2;
        Vector<Integer>temp1=new Vector<>(nums);
        mergeSort(temp1, start, mid, diff);
        Vector<Integer>temp2=new Vector<>(nums);
        mergeSort(temp2, mid+1, end, diff);
        checkCount(nums, start, mid, end, diff);
        return;
    }

    public long numberOfPairs(Vector<Integer>nums1, Vector<Integer>nums2, int diff){
        setCount(0);
        int sz=nums1.size();
        Vector<Integer>vec=new Vector<>();
        for(int i=0; i<sz; ++i){
            vec.add(nums1.get(i)-nums2.get(i));
        }
        mergeSort(vec, 0, sz-1, diff);
        return getCount();
    }
}

public class NumPairsSatisfyingInequality {

    public static void main(String[] arguments){
        NumPairsSatisfyingInequalityUtil obj=new NumPairsSatisfyingInequalityUtil();
        int[] nums1arr=new int[]{3,-1};
        int[] nums2arr=new int[]{-2,2};
        int diff=-1;
        Vector<Integer>nums1=new Vector<>();
        for(int val: nums1arr){
            nums1.add(val);
        }
        Vector<Integer>nums2=new Vector<>();
        for(int val: nums2arr){
            nums2.add(val);
        }
        long ans=obj.numberOfPairs(nums1, nums2, diff);
        System.out.println("ans: "+ans);
    }
}
