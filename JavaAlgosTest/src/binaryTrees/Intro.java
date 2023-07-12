package binaryTrees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class IntroUtil{

    public Node newNode(int val){
        Node node=new Node();
        node.setVal(val);
        node.setLeft(null);
        node.setRight(null);
        return node;
    }

    public int maxInBTreeBFS(Node head){
        int maxx=Integer.MIN_VALUE;
        if(head==null) return maxx;
        Queue<Node>q=new LinkedList<>();
        q.add(head);
        while(!q.isEmpty()){
            int sz=q.size();
            while(sz-- >0){
                Node currHead=q.poll();
                assert currHead!=null;
                maxx=Math.max(maxx, currHead.getVal());
                if(currHead.getLeft()!=null){
                    q.add(currHead.getLeft());
                }
                if(currHead.getRight()!=null){
                    q.add(currHead.getRight());
                }
            }
        }
        return maxx;
    }

    public int maxInBTreeDFS(Node head){
        int maxx=Integer.MIN_VALUE;
        if(head==null) return maxx;
        maxx=head.getVal();
        int leftMaxx=maxInBTreeDFS(head.getLeft());
        int rightMaxx=maxInBTreeDFS(head.getRight());
        maxx=Math.max(maxx, Math.max(leftMaxx, rightMaxx));
        return maxx;
    }

    public int maxInBTreeDFS2(Node head){
        int maxx=Integer.MIN_VALUE;
        if(head==null) return maxx;
        Stack<Node>stk=new Stack<>();
        stk.push(head);
        while(!stk.isEmpty()){
            int sz=stk.size();
            while(sz-- >0){
                Node currHead=stk.pop();
                maxx=Math.max(maxx, currHead.getVal());
                if(currHead.getLeft()!=null){
                    stk.push(currHead.getLeft());
                }
                if(currHead.getRight()!=null){
                    stk.push(currHead.getRight());
                }
            }
        }
        return maxx;
    }

    public int minInBTreeBFS(Node head){
        int minn=Integer.MAX_VALUE;
        if(head==null) return minn;
        Queue<Node>q=new LinkedList<>();
        q.add(head);
        while(!q.isEmpty()){
            int sz=q.size();
            while(sz-- >0){
                Node currHead=q.poll();
                assert currHead!=null;
                minn=Math.min(minn, currHead.getVal());
                if(currHead.getLeft()!=null){
                    q.add(currHead.getLeft());
                }
                if(currHead.getRight()!=null){
                    q.add(currHead.getRight());
                }
            }
        }
        return minn;
    }

    public int minInBTreeDFS(Node head){
        int minn=Integer.MAX_VALUE;
        if(head==null) return minn;
        minn=head.getVal();
        int leftMinn=minInBTreeDFS(head.getLeft());
        int rightMinn=minInBTreeDFS(head.getRight());
        minn=Math.min(minn, Math.min(leftMinn, rightMinn));
        return minn;
    }

    public int minInBTreeDfs2(Node head){
        int minn=Integer.MAX_VALUE;
        if(head==null) return minn;
        Stack<Node>stk=new Stack<>();
        stk.push(head);
        while(!stk.isEmpty()){
            int sz=stk.size();
            while(sz-- >0){
                Node currHead=stk.pop();
                minn=Math.min(minn, currHead.getVal());
                if(currHead.getLeft()!=null){
                    stk.push(currHead.getLeft());
                }
                if(currHead.getRight()!=null){
                    stk.push(currHead.getRight());
                }
            }
        }
        return minn;
    }

    public int sumBTreeBFS(Node head){
        int sum=0;
        if(head==null) return sum;
        Queue<Node>q=new LinkedList<>();
        q.add(head);
        while(!q.isEmpty()){
            int sz=q.size();
            while(sz-- >0){
                Node currHead=q.poll();
                assert currHead!=null;
                sum+=currHead.getVal();
                if(currHead.getLeft()!=null){
                    q.add(currHead.getLeft());
                }
                if(currHead.getRight()!=null){
                    q.add(currHead.getRight());
                }
            }
        }
        return sum;
    }

    public int sumBTreeDFS(Node head){
        int sum=0;
        if(head==null) return sum;
        sum+=head.getVal();
        int leftTreeSum=sumBTreeDFS(head.getLeft());
        int rightTreeSum=sumBTreeDFS(head.getRight());
        sum+=(leftTreeSum+rightTreeSum);
        return sum;
    }

    public int sumBTreeDfs2(Node head){
        int sum=0;
        if(head==null) return sum;
        Stack<Node>stk=new Stack<>();
        stk.push(head);
        while(!stk.isEmpty()){
            int sz=stk.size();
            while(sz-- >0){
                Node currHead=stk.pop();
                sum+=currHead.getVal();
                if(currHead.getLeft()!=null){
                    stk.push(currHead.getLeft());
                }
                if(currHead.getRight()!=null){
                    stk.push(currHead.getRight());
                }
            }
        }
        return sum;
    }

    boolean isFound(Node head, int val){
        if(head==null) return false;
        Queue<Node>q=new LinkedList<>();
        q.add(head);
        while(!q.isEmpty()){
            int sz=q.size();
            while(sz-- >0){
                Node currHead=q.poll();
                assert currHead!=null;
                if(currHead.getVal()==val) return true;
                if(currHead.getLeft()!=null){
                    q.add(currHead.getLeft());
                }
                if(currHead.getRight()!=null){
                    q.add(currHead.getRight());
                }
            }
        }
        return false;
    }

    boolean isFoundDFS(Node head, int val){
        if(head==null) return false;
        if(head.getVal()==val) return true;
        boolean leftTreeSearch=isFoundDFS(head.getLeft(), val);
        boolean rightTreeSearch=isFoundDFS(head.getRight(), val);
        return leftTreeSearch||rightTreeSearch;
    }

    boolean isFoundDFS2(Node head, int val){
        if(head==null) return false;
        Stack<Node>stk=new Stack<>();
        stk.push(head);
        while(!stk.isEmpty()){
            int sz=stk.size();
            while(sz-- >0){
                Node currHead=stk.pop();
                if(currHead.getVal()==val) return true;
                if(currHead.getLeft()!=null){
                    stk.push(currHead.getLeft());
                }
                if(currHead.getRight()!=null){
                    stk.push(currHead.getRight());
                }
            }
        }
        return false;
    }
}

public class Intro {

    public static void main(String[] args){
        IntroUtil obj=new IntroUtil();
        Node head=obj.newNode(1123);
        head.setLeft(obj.newNode(245));
        head.setRight(obj.newNode(3235));
        head.getLeft().setLeft(obj.newNode(478));
        head.getRight().setRight(obj.newNode(556));
        head.getLeft().setRight(obj.newNode(623));
        head.getRight().setLeft(obj.newNode(712));
        int maxxInBTreeWithBFS=obj.maxInBTreeBFS(head);
        System.out.println("maxx with BFS: "+maxxInBTreeWithBFS);
        System.out.println("-------");
        int maxxInBTreeWithDFS=obj.maxInBTreeDFS(head);
        System.out.println("maxx with DFS: "+maxxInBTreeWithDFS);
        System.out.println("-------");
        int maxxInBTreeWithDFS2=obj.maxInBTreeDFS2(head);
        System.out.println("maxx with DFS2: "+maxxInBTreeWithDFS2);
        System.out.println("-------");
        int minnInBTreeWithBFS=obj.minInBTreeBFS(head);
        System.out.println("minn with BFS: "+minnInBTreeWithBFS);
        System.out.println("-------");
        int minnInBTreeWithDFS=obj.minInBTreeDFS(head);
        System.out.println("minn with DFS: "+minnInBTreeWithDFS);
        System.out.println("-------");
        int minnInBTreeWithDFS2=obj.minInBTreeDfs2(head);
        System.out.println("minn with DFS: "+minnInBTreeWithDFS2);
        System.out.println("-------");
        int sumInBTreeBFS=obj.sumBTreeBFS(head);
        System.out.println("sum in BTree with BFS: "+sumInBTreeBFS);
        System.out.println("-------");
        int sumInBTreeDFS=obj.sumBTreeDFS(head);
        System.out.println("sum in BTree with DFS: "+sumInBTreeDFS);
        System.out.println("-------");
        int sumInBTreeDFS2=obj.sumBTreeDfs2(head);
        System.out.println("sum in BTree with DFS2: "+sumInBTreeDFS2);
        System.out.println("-------");
        int valToFound=712;
        boolean isNodeWithValThereWithBFS=obj.isFound(head, valToFound);
        System.out.println("is node with value "+valToFound+" there with BFS: "+isNodeWithValThereWithBFS);
        System.out.println("-------");
        boolean isNodeWithValThereWithDFS1=obj.isFoundDFS(head, valToFound);
        System.out.println("is node with value "+valToFound+" there with DFS: "+isNodeWithValThereWithDFS1);
    }
}
