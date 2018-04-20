public class myString {
    public static int OperatorDetecter(char c){
        if(c=='+' || c=='-' || c=='*' || c=='/' || c=='^' || c=='%')
            return 2;
        if(c=='~')
            return 1;
        return 0;
    }
    public static int SendPeriority(char c){
        if(OperatorDetecter(c)==0)
            return 0;
        else if(c=='~')
            return 4;
        else if(c=='^')
            return 3;
        else if(c=='*' || c=='/' || c=='%')
            return 2;
        else if(c=='+' || c=='-')
            return 1;
        throw new RuntimeException();
    }
    
    public static String ReplaceChar(String str,char c,int n){
        if(n<0)
            throw new RuntimeException();
        str=str.substring(0,n)+String.valueOf(c)+str.substring(n+1);
        return str;
    }
    
    public static void Test(){
        BinaryExpressionTree.PostF=myString.ReplaceChar(BinaryExpressionTree.PostF, 'Q', 1);
        BinaryExpressionTree.PostF=myString.ReplaceChar(BinaryExpressionTree.PostF, 'X', 2);
    }
}
