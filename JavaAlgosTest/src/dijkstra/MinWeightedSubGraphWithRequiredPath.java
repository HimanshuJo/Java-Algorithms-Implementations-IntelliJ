// Minimum Weighted Subgraph With the Required Paths
/*
You are given an integer n denoting the number of nodes of a weighted directed graph.
The nodes are numbered from 0 to n - 1.

You are also given a 2D integer array edges where edges[i] = [fromi, toi, weighti]
denotes that there exists a directed edge from fromi to toi with weight weighti.

Lastly, you are given three distinct integers src1, src2, and dest denoting three distinct
nodes of the graph.

Return the minimum weight of a subgraph of the graph such that it is possible
to reach dest from both src1 and src2 via a set of edges of this subgraph.

In case such a subgraph does not exist, return -1.

A subgraph is a graph whose vertices and edges are subsets of the original graph.
The weight of a subgraph is the sum of weights of its constituent edges.

Input: n = 6, edges = [[0,2,2],[0,5,6],[1,0,3],[1,4,5],[2,1,1],[2,3,3],[2,3,4],[3,4,2],[4,5,1]],
src1 = 0, src2 = 1, dest = 5
Output: 9
Explanation:
The above figure represents the input graph.
The blue edges represent one of the subgraphs that yield the optimal answer.
Note that the subgraph [[1,0,3],[0,5,6]] also yields the optimal answer.
It is not possible to get a subgraph with less weight satisfying all the constraints.

Input: n = 3, edges = [[0,1,1],[2,1,1]], src1 = 0, src2 = 1, dest = 2
Output: -1
Explanation:
The above figure represents the input graph.
It can be seen that there does not exist any path from node 1 to node 2,
hence there are no subgraphs satisfying all the constraints.

Constraints:

3 <= n <= 10^5
0 <= edges.length <= 10^5
edges[i].length == 3
0 <= fromi, toi, src1, src2, dest <= n - 1
fromi != toi
src1, src2, and dest are pairwise distinct.
1 <= weight[i] <= 10^5
*/

package dijkstra;

import java.util.PriorityQueue;
import java.util.Vector;

class Pair{

    public long first, second;

    Pair(long first, long second){
        this.first=first;
        this.second=second;
    }

}

class MinWeightedSubGraphWithRequiredPathUtil{

    public void djikstra(Vector<Vector<Pair>>gr, Vector<Long>dist, int curSource){
        dist.set(curSource, 0L);
        PriorityQueue<Pair>pq=new PriorityQueue<>((o1, o2)->Long.compare(o2.first, o1.first));
        pq.add(new Pair(0, curSource));
        while(!pq.isEmpty()){
            int sz=pq.size();
            while(sz-- >0){
                Pair curr=pq.poll();
                assert curr!=null;
                int currVert=(int)curr.second;
                long currWeight=curr.first;
                if(currWeight>dist.get(currVert)) continue;
                for(Pair nei: gr.get(currVert)){
                    int newVert=(int)nei.first;
                    long newWeight= nei.second;
                    if(dist.get(currVert)+newWeight<dist.get(newVert)){
                        dist.set(newVert, dist.get(currVert)+newWeight);
                        pq.add(new Pair(dist.get(newVert), newVert));
                    }
                }
            }
        }
    }

    public long minimumWeight(int n, Vector<Vector<Integer>>edges, int src1, int src2, int dest){
        Vector<Vector<Pair>>gr=new Vector<>();
        Vector<Vector<Pair>>revGr=new Vector<>();
        for(int i=0; i<n; ++i){
            gr.add(new Vector<>());
            revGr.add(new Vector<>());
        }
        for(Vector<Integer>edgeEntries: edges){
            gr.get(edgeEntries.get(0)).add(new Pair(edgeEntries.get(1), edgeEntries.get(2)));
            revGr.get(edgeEntries.get(1)).add(new Pair(edgeEntries.get(0), edgeEntries.get(2)));
        }
        Vector<Long>dista=new Vector<>();
        Vector<Long>distb=new Vector<>();
        Vector<Long>distc=new Vector<>();
        for(int i=0; i<n; ++i){
            dista.add(Long.MAX_VALUE);
            distb.add(Long.MAX_VALUE);
            distc.add(Long.MAX_VALUE);
        }
        djikstra(gr, dista, src1);
        djikstra(gr, distb, src2);
        djikstra(revGr, distc, dest);
        long ans=Long.MAX_VALUE;
        for(int i=0; i<n; ++i){
            if(dista.get(i)==Long.MAX_VALUE||
                    distb.get(i)==Long.MAX_VALUE||
                    distc.get(i)==Long.MAX_VALUE){
                continue;
            }
            ans=Math.min(ans, dista.get(i)+distb.get(i)+distc.get(i));
        }
        return ans==Long.MAX_VALUE?-1:ans;
    }
}

public class MinWeightedSubGraphWithRequiredPath {

    public static void main(String[] arguments){
        MinWeightedSubGraphWithRequiredPathUtil obj=new MinWeightedSubGraphWithRequiredPathUtil();
        int n=6;
        int[][] edgesArr=new int[][]
                {{0,2,2},{0,5,6},{1,0,3},{1,4,5},{2,1,1},{2,3,3},{2,3,4},{3,4,2},{4,5,1}};
        int src1=0, src2=1, dest=5;
        Vector<Vector<Integer>>edges=new Vector<>();
        for(int i=0; i<edgesArr.length; ++i){
            edges.add(new Vector<>());
            for(int nums: edgesArr[i]){
                edges.get(i).add(nums);
            }
        }
        int ans=(int)obj.minimumWeight(n, edges, src1, src2, dest);
        System.out.println("ans: "+ans);
        System.out.println("-------");
        int n2=3;
        int[][] edgesArr2=new int[][]
                {{0,1,1}, {2,1,1}};
        int srcSec1=0, srcSec2=1, destSec=2;
        Vector<Vector<Integer>>edges2=new Vector<>();
        for(int i=0; i<edgesArr2.length; ++i){
            edges2.add(new Vector<>());
            for(int nums: edgesArr2[i]){
                edges2.get(i).add(nums);
            }
        }
        int ans2=(int)obj.minimumWeight(n2, edges2, srcSec1, srcSec2, destSec);
        System.out.println("ans2: "+ans2);
    }
}
