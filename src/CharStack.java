public class CharStack {
    private static int MAX=100;
    private char []s=new char[MAX];
    private int top=-1;
    public void push(char c){
        if(top==MAX)
            throw new StackOverflowError();
        s[++top]=c;
    }
    public char pop(){
        if(top==-1){
            throw new NullPointerException();
        }
        return s[top--];
    }
    public char GetTop(){
        if(top==-1){
            throw new NullPointerException();
        }
        return s[top];
    }
    public int GetSize(){
        return top+1;
    }
    public boolean isEmpty(){
        return top==-1;
    }
}