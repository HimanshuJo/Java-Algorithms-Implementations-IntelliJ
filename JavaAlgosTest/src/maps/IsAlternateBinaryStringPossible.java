package maps;

import java.util.HashMap;
import java.util.Map;

class Pair{

    private int first, second;

    public Pair(){}

    public Pair(int first, int second){
        this.first=first;
        this.second=second;
    }

    public int getFirst(){
        return first;
    }

    public void setFirst(int first){
        this.first=first;
    }

    public int getSecond(){
        return second;
    }

    public void setSecond(int second){
        this.second=second;
    }
}

class IsAlternateBinaryStringPossibleUtil{

    public boolean isAlternateBinaryStringPosb(String s, int K, HashMap<Character, Pair>mp){
        int sz=s.length();
        for(int i=0; i<sz; ++i){
            boolean tochk=mp.containsKey(s.charAt(i));
            if(!tochk){
                mp.put(s.charAt(i), new Pair(i, (i%2==0)?0:1));
            } else{
                int prevIdx=mp.get(s.charAt(i)).getFirst();
                if((i-prevIdx)%2!=0){
                    return false;
                } else{
                    Pair toprc=mp.get(s.charAt(i));
                    toprc.setFirst(i);
                    mp.put(s.charAt(i), toprc);
                    if(i%2==0){
                        toprc.setSecond(0);
                    } else{
                        toprc.setSecond(1);
                    }
                }
            }
        }
        return mp.size()<=K;
    }

}

public class IsAlternateBinaryStringPossible {

    public static void main(String[] arguments){
        String s="axnyyjk";
        int K=1;
        HashMap<Character, Pair>mp=new HashMap<>();
        IsAlternateBinaryStringPossibleUtil obj=new IsAlternateBinaryStringPossibleUtil();
        boolean flag=obj.isAlternateBinaryStringPosb(s, K, mp);
        if(flag){
            for(Map.Entry<Character, Pair> entry: mp.entrySet()){
                System.out.println(entry.getKey()+" : "+ entry.getValue().getSecond());
            }
            System.out.println();
            System.out.println("-------");
            int sz=s.length();
            for(int i=0; i<sz; ++i){
                System.out.print(mp.get(s.charAt(i)).getSecond()+" ");
            }
        } else{
            System.out.println("NO");
        }
    }
}
