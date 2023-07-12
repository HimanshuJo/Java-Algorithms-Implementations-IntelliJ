/*
0-1 BFS (Shortest Path in a Binary Weight Graph)

Given a graph where every edge has weight as either 0 or 1.
A source vertex is also given in the graph. Find the shortest path from source vertex to every other vertex.

Example:

Input : Source Vertex = 0 and below graph
Output : Shortest distances from given source
         0 0 1 1 2 1 2 1 2

Explanation :
Shortest distance from 0 to 0 is 0
Shortest distance from 0 to 1 is 0
Shortest distance from 0 to 2 is 1
*/
/*
In normal BFS of a graph all edges have equal weight but in 0-1 BFS some edges may have 0 weight
and some may have 1 weight.

In this we will not use boolean array to mark visited nodes but at each step we will
check for the optimal distance condition.

We use double ended queue to store the node.

While performing BFS if a edge having weight = 0 is found node is pushed at front of
double ended queue and if a edge having weight = 1 is found, it is pushed at back of double ended queue.

The approach is similar to Dijkstra that the if the shortest distance to node
is relaxed by the previous node then only it will be pushed in the queue.

The above idea works in all cases, when pop a vertex (like Dijkstra),
it is the minimum weight vertex among remaining vertices.

If there is a 0 weight vertex adjacent to it, then this adjacent has same distance.

If there is a 1 weight adjacent, then this adjacent has maximum distance among all vertices in
dequeue (because all other vertices are either adjacent of currently popped vertex or adjacent of
previously popped vertices).

-------

This problem can also be solved by Dijkstra but the time complexity will be
O(E+VLogV) whereas by BFS it will be O(E+V).
*/

package bfs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

class Node{

    public int to;
    public int weight;

    Node(int to, int weight){
        this.to=to;
        this.weight=weight;
    }
}
class ZeroOneBFSUtil{

    public static final int V=9;

    public void zeroOneBFS(int src, ArrayList<Node>[] edges){
        int[] dist =new int[V];
        for(int i=0; i<V; ++i){
            dist[i]=Integer.MAX_VALUE;
        }
        Deque<Integer> dq=new LinkedList<>();
        dist[src]=0;
        dq.offer(src);
        while(!dq.isEmpty()){
            int sz=dq.size();
            while(sz-- >0){
                if(!dq.isEmpty()){
                    int v=dq.poll();
                    for(int i=0; i<edges[v].size(); ++i){
                        /* If the already calculated distance to 'one of the neighbor of the current vertex' is
                           greater than 'already calculated distance to the current vertex' + 'weight of this neighbour node'
                           then exchange the distance accordingly */
                        if(dist[edges[v].get(i).to]>edges[v].get(i).weight+dist[v]){
                            dist[edges[v].get(i).to]=edges[v].get(i).weight+dist[v];
                            if(edges[v].get(i).weight==0){
                                dq.offerFirst(edges[v].get(i).to);
                            } else{
                                dq.offerLast(edges[v].get(i).to);
                            }
                        }
                    }
                }
            }
        }
        for(int i=0; i<V; ++i){
            System.out.println("From source: "+src+" shortest Distance to Node "+i+" is: "+dist[i]);
        }
    }

    public void addEdge(ArrayList<Node>[] edges, int u, int v, int wt){
        ArrayList<Node>temp1=edges[u];
        ArrayList<Node>temp2=edges[v];
        if(temp1==null){
            temp1=new ArrayList<>();
            temp1.add(new Node(v, wt));
            edges[u]=temp1;
        } else{
            temp1.add(new Node(v, wt));
            edges[u]=temp1;
        }
        if(temp2==null){
            temp2=new ArrayList<>();
            temp2.add(new Node(u, wt));
            edges[v]=temp2;
        } else{
            temp2.add(new Node(u, wt));
            edges[v]=temp2;
        }
    }
}
public class ZeroOneBFS {

    public static void main(String[] args){
        ZeroOneBFSUtil obj=new ZeroOneBFSUtil();
        ArrayList<Node>[]al=new ArrayList[ZeroOneBFSUtil.V];
        obj.addEdge(al, 0, 1, 0);
        obj.addEdge(al, 0, 7, 1);
        obj.addEdge(al, 1, 7, 1);
        obj.addEdge(al, 1, 2, 1);
        obj.addEdge(al, 2, 3, 0);
        obj.addEdge(al, 2, 5, 0);
        obj.addEdge(al, 2, 8, 1);
        obj.addEdge(al, 3, 4, 1);
        obj.addEdge(al, 3, 5, 1);
        obj.addEdge(al, 4, 5, 1);
        obj.addEdge(al, 5, 6, 1);
        obj.addEdge(al, 6, 7, 1);
        obj.addEdge(al, 7, 8, 1);
        int src=0;
        obj.zeroOneBFS(src, al);
    }
}
