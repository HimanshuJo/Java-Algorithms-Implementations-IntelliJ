package binaryTrees;

public class Node{

    private int val;

    private Node left, right;

    public int getVal(){
        return val;
    }

    public void setVal(int val){
        this.val=val;
    }

    public Node getLeft(){
        return left;
    }

    public void setLeft(Node left){
        this.left=left;
    }

    public Node getRight(){
        return right;
    }

    public void setRight(Node right){
        this.right=right;
    }
}
