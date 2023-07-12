package arraysDirTraverseAndManipulations;

import java.util.Vector;

class MaxSumDiagonalUtil{

    public boolean isValid(int N, int M, int currw, int curcol){
        return currw>=0&&curcol>=0&&currw<N&&curcol<M;
    }

    public int maxSumDiagonals(int N, int M, Vector<Vector<Integer>>vec){
        int ans=Integer.MIN_VALUE;
        Vector<Vector<Integer>>dir=new Vector<>();
        int[][] dirarr=new int[][]{{1, -1}, {1, 1}, {-1, -1}, {-1, 1}};
        for(int i=0; i<4; ++i){
            dir.add(new Vector<>());
            for(int j=0; j<2; ++j){
                dir.get(i).add(dirarr[i][j]);
            }
        }
        for(int i=0; i<N; ++i){
            for(int j=0; j<M; ++j){
                int cursum=vec.get(i).get(j);
                for(Vector<Integer>vals: dir){
                    int currw=i, curcol=j;
                    while(true){
                        int nxtrw=currw+vals.get(0), nxtcol=curcol+vals.get(1);
                        if(isValid(N, M, nxtrw, nxtcol)){
                            cursum+=vec.get(nxtrw).get(nxtcol);
                            currw=nxtrw;
                            curcol=nxtcol;
                        } else break;
                    }
                }
                ans=Math.max(ans, cursum);
            }
        }
        return ans;
    }

}
public class MaxSumDiagonals {

    public static void main(String[] arguments){
        MaxSumDiagonalUtil obj=new MaxSumDiagonalUtil();
        int N=7, M=4;
        Vector<Vector<Integer>>vec=new Vector<>();
        int[][] vecarr=new int[][]{{112, 232, 2123, 111},
                                    {2231, 412, 212, 423},
                                    {212, 233, 3123, 112},
                                    {221, 412, 212, 423},
                                    {22891, 4712, 21442, 4223},
                                    {2212, 2033, 31123, 1612},
                                    {221, 412, 212, 423}};
        for(int i=0; i<N; ++i){
            vec.add(new Vector<>());
            for(int j=0; j<M; ++j){
                vec.get(i).add(vecarr[i][j]);
            }
        }
        int ans=obj.maxSumDiagonals(N, M, vec);
        System.out.println("ans: "+ans);
    }
}
