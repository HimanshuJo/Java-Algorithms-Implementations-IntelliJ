// 2425. Bitwise XOR of All Pairings
/*
You are given two 0-indexed arrays, nums1 and nums2, consisting of non-negative integers.
There exists another array, nums3, which contains the bitwise XOR of all pairings of integers
between nums1 and nums2 (every integer in nums1 is paired with every integer in nums2 exactly once).

Return the bitwise XOR of all integers in nums3.

Example 1:

Input: nums1 = [2,1,3], nums2 = [10,2,5,0]
Output: 13
Explanation:
A possible nums3 array is [8,0,7,2,11,3,4,1,9,1,6,3].
The bitwise XOR of all these numbers is 13, so we return 13.

Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 0
Explanation:
All possible pairs of bitwise XORs are nums1[0] ^ nums2[0], nums1[0] ^ nums2[1], nums1[1] ^ nums2[0],
and nums1[1] ^ nums2[1].
Thus, one possible nums3 array is [2,5,1,6].
2 ^ 5 ^ 1 ^ 6 = 0, so we return 0.

Constraints:

1 <= nums1.length, nums2.length <= 10^5
0 <= nums1[i], nums2[j] <= 10^9
*/

package xor;

import java.util.Vector;

class BitXOROfAllPairingsUtil_TLE{

    public int xorAllNums(Vector<Integer>nums1, Vector<Integer>nums2){
        Vector<Integer>all=new Vector<>();
        for(Integer integer: nums1) {
            for(Integer value: nums2) {
                all.add(integer^value);
            }
        }
        int fnxor=all.get(0);
        int sz_=all.size();
        for(int i=1; i<sz_; ++i){
            fnxor^=all.get(i);
        }
        return fnxor;
    }
}

class BitXOROfAllPairings_Opt{

    int xorAllNums(Vector<Integer>nums1, Vector<Integer>nums2){
        int sz1=nums1.size(), sz2=nums2.size();
        int xor1=nums1.get(0);
        for(int i=1; i<sz1; ++i){
            xor1^=nums1.get(i);
        }
        int xor2=nums2.get(0);
        for(int i=1; i<sz2; ++i){
            xor2^=nums2.get(i);
        }
        /*
        case 1:

            when n and m is even
            suppose nums1={a,b}, nums2={c,d}
            taking xor => {a^c,a^d,b^c,b^d }
            finally taking xor of all => {a^c^a^d^b^c^b^d} ------ (1)
            we know that x^x=0(even times xor with self = 0)
            Now, (1) becomes => {a^a^b^b^c^c^d^d} => {0^0^0^0} =0
            RESULT (case 1) = 0;
         */
        if(sz1%2==0&&sz2%2==0) return 0;
        /*
        case 2:

            when both are odd
            Let's x1= xor of all elements of nums1, x2=xor of all elements of nums2
            suppose nums1={a}, nums2={c} (you can deduce by yourself, I am taking the easier one,
            take nums1={a,b,c}, nums2={d,e,f} and solve it)
            taking xor => {a^c}
            RESULT (case 2) = (x1^x2)
         */
        if(sz1%2!=0&&sz2%2!=0) return xor1^xor2;
        /*
        Case 3/4:

            When one of them is odd and other is even
            nums1={a,b,c}, nums2={d,e}
            Here, all terms of nums1 comes even times(m times)
            in final xorr => so, we can take them as zero
            And, all terms of nums2 comes odd times(n times) =>
            so, we can take odd=(even+1) => 1 times x2
            x1=a^b^c, x2=d^e

            RESULT (CASE 3/4) = x2 (if m is even), = x1 (if n is even)
         */
        if(sz1%2!=0) return xor2;
        return xor1;
    }
}

public class BitXOROfAllPairings {

    public static void main(String[] arguments){
        Vector<Integer>nums1=new Vector<>();
        Vector<Integer>nums2=new Vector<>();
        int[] nums1arr=new int[]{2, 1, 3};
        int[] nums2arr=new int[]{10, 2, 5, 0};
        for(int nums: nums1arr){
            nums1.add(nums);
        }
        for(int nums: nums2arr){
            nums2.add(nums);
        }
        BitXOROfAllPairingsUtil_TLE obj=new BitXOROfAllPairingsUtil_TLE();
        int ans=obj.xorAllNums(nums1, nums2);
        System.out.println("ans: "+ans);
        System.out.println("-------");
        BitXOROfAllPairings_Opt obj2=new BitXOROfAllPairings_Opt();
        int ans2=obj2.xorAllNums(nums1, nums2);
        System.out.println("ans2: "+ans2);
    }
}
