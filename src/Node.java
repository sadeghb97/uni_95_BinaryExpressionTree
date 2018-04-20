public class Node {
    private Node Left;
    private Node Right;
    private String Element;
    
    public Node(){
    }
    
    public Node(String data){
        Element=data;
    }
    
    public Node setLeft(Node n){
        Left=n;
        return Left;
    }
    public Node setRight(Node n){
        Right=n;
        return Right;
    }
    public void setElement(String data){
        Element=data;
    }
    
    public Node getLeft(){
        return Left;
    }
    public Node getRight(){
        return Right;
    }
    public String getElement(){
        return Element;
    }
}
