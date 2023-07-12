package twoPointers;

import java.util.Vector;

class ReverseWithoutChangingZerosUtil{

    public void swap(Vector<Integer>vec, int i, int j){
        int temp=vec.get(i);
        vec.set(i, vec.get(j));
        vec.set(j, temp);
    }

    public void printReverse(Vector<Integer>vec){
        int sz=vec.size();
        int i=0, j=sz-1;
        while(j>i){
            if(vec.get(i)!=0){
                if(vec.get(j)!=0) {
                    swap(vec, i, j);
                    ++i;
                }
                --j;
            } else{
                if(vec.get(j)==0){
                    ++i;
                    --j;
                } else{
                    ++i;
                }
            }
            /*
            if(vec.get(i)==0&&vec.get(j)==0){
                ++i;
                --j;
            } else if(vec.get(i)!=0&&vec.get(j)!=0){
                swap(vec, i, j);
                ++i;
                --j;
            } else if(vec.get(i)==0&&vec.get(j)!=0){
                ++i;
            } else if(vec.get(i)!=0&&vec.get(j)==0){
                --j;
            }
            */
        }
        for(Integer integer: vec){
            System.out.print(integer+" ");
        }
    }
}

public class ReverseWithoutChangingZeros {

    public static void main(String[] arguments){
        Vector<Integer>vec=new Vector<>();
        int[] vecarr=new int[]{2, 0, 5, 0, 7, 0, 3};
        for(Integer integer: vecarr){
            vec.add(integer);
        }
        ReverseWithoutChangingZerosUtil obj= new ReverseWithoutChangingZerosUtil();
        obj.printReverse(vec);
    }
}
