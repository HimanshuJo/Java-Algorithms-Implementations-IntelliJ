/*
    Given a vector of integer, find the sum of integers between a specified range efficiently

    E.g. Vector = [1, 2, 3, 4, 5, 6, 7]

         Sum of integers between index 1 and index 4 inclusive is: 14 (2+3+4+5)
 */

package segmentTrees;

class SumSegmentTreeWithNodeUpdateUtil{

    public int getMid(int start, int end){
        return start+(end-start)/2;
    }

    public int getSumUtil(int[] segmentTree, int segmentStart, int segmentEnd,
                          int queryStart, int queryEnd, int currentSegmentIdx){
        if(segmentStart>queryEnd||segmentEnd<queryStart) return 0;
        if(segmentStart>=queryStart&&segmentEnd<=queryEnd) return segmentTree[currentSegmentIdx];
        int mid=getMid(segmentStart, segmentEnd);
        return getSumUtil(segmentTree, segmentStart, mid, queryStart, queryEnd, currentSegmentIdx*2+1)+
                getSumUtil(segmentTree, mid+1, segmentEnd, queryStart, queryEnd, currentSegmentIdx*2+2);
    }

    public int getSum(int[] segmentTree, int n, int queryStart, int queryEnd){
        if(queryStart<0||queryEnd>n-1||queryStart>queryEnd) return -1;
        int segmentStart=0, segmentEnd=n-1, currentSegmentIdx=0;
        return getSumUtil(segmentTree, segmentStart, segmentEnd, queryStart, queryEnd, currentSegmentIdx);
    }

    void updateUtil(int[] segmentTree, int arrIdx, int diff, int segmentStart, int segmentEnd, int currentSegmentIdx){
        if(segmentStart>arrIdx||segmentEnd<arrIdx) return;
        segmentTree[currentSegmentIdx]=segmentTree[currentSegmentIdx]+diff;
        if(segmentStart!=segmentEnd){
            int mid=getMid(segmentStart, segmentEnd);
            updateUtil(segmentTree, arrIdx, diff, segmentStart, mid, currentSegmentIdx*2+1);
            updateUtil(segmentTree, arrIdx,mid+1, segmentEnd, diff, currentSegmentIdx*2+2);
        }
    }

    void update(int[] arr, int[] segmentTree, int n, int arrIdx, int newValue){
        if(arrIdx<0||arrIdx>n-1){
            System.out.println("Invalid");
            return;
        }
        int diff=newValue-arr[arrIdx];
        arr[arrIdx]=newValue;
        int segmentStart=0, segmentEnd=n-1, currentSegmentIdx=0;
        updateUtil(segmentTree, arrIdx, diff, segmentStart, segmentEnd, currentSegmentIdx);
    }

    public int constructSegmentTreeUtil(int[] arr, int[] segmentTree, int segmentStart, int segmentEnd,
                                        int currentSegmentIdx){
        if(segmentStart==segmentEnd){
            segmentTree[currentSegmentIdx]=arr[segmentStart];
            return arr[segmentStart];
        }
        int mid=getMid(segmentStart, segmentEnd);
        segmentTree[currentSegmentIdx]=constructSegmentTreeUtil(arr, segmentTree, segmentStart, mid, currentSegmentIdx*2+1)+
                                       constructSegmentTreeUtil(arr, segmentTree, mid+1, segmentEnd, currentSegmentIdx*2+2);
        return segmentTree[currentSegmentIdx];
    }

    public int[] constructSegmentTree(int[] arr, int n){
        int x=(int)(Math.ceil(Math.log(n)/Math.log(2)));
        int maxSz=2*(int)Math.pow(2, x)-1;
        int[] segmentTree=new int[maxSz+1];
        for(int i=0; i<=maxSz; ++i){
            segmentTree[i]=0;
        }
        int segmentStart=0, segmentEnd=n-1, currentSegmentIdx=0;
        constructSegmentTreeUtil(arr, segmentTree, segmentStart, segmentEnd, currentSegmentIdx);
        return segmentTree;
    }
}

public class SumSegmentTreeWithNodeUpdate {

    public static void main(String[] args){
        int[] arr=new int[]{1, 2, 3, 4, 5, 6, 7};
        SumSegmentTreeWithNodeUpdateUtil obj=new SumSegmentTreeWithNodeUpdateUtil();
        int n=arr.length;
        int[] segmentTree=obj.constructSegmentTree(arr, n);
        int sumToCheck=obj.getSum(segmentTree, n, 2, 4);
        System.out.println("Sum: "+sumToCheck);
        System.out.println("-------");
        obj.update(arr, segmentTree, arr.length, 0, 3);
        int newSumToCheck=obj.getSum(segmentTree, n, 0, 2);
        System.out.println("Sum2: "+newSumToCheck);
    }
}
