public class Stack {
    private static int MAX=100;
    private Node []s=new Node[MAX];
    private int top=-1;
    public void push(Node n){
        if(top==MAX)
            throw new StackOverflowError();
        s[++top]=n;
    }
    public Node pop(){
        if(top==-1){
            throw new NullPointerException();
        }
        return s[top--];
    }
    public int GetSize(){
        return top+1;
    }
    public boolean isEmpty(){
        return top==-1;
    }
}