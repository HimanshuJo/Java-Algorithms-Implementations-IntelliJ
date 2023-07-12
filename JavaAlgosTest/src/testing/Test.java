package testing;

class TestUtil{

    public void setTempValue(int[] temp, int idx, int value){
        temp[idx]=value;
    }

    public int[] setValue(int idx, int value){
        int[] temp=new int[5];
        setTempValue(temp, idx, value);
        return temp;
    }
}

public class Test {

    public static void main(String[] args){
        TestUtil obj=new TestUtil();
        int[] tochk=obj.setValue(2, 12);
        for(int vals: tochk){
            System.out.print(vals+" ");
        }
    }
}
