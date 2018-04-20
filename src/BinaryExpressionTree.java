public class BinaryExpressionTree {
    public static String PostF="";
    public static String PreF="                                             QQQ";
    public static void main(String[] args) {
        String str,str2;
        str="((((b*x)-(a^z))/(~b))+((c/n)+(m/d)))";
        str2="((a*z)+q)";
        BETree t=new BETree(BETree.ParaInfixStringToBET(str));
        String In=t.GetInfix(t.getRoot());
        System.out.println(In);
        String Pre=t.GetPrefix(t.getRoot());
        System.out.println(Pre);
        String Post=t.GetPostfix(t.getRoot());
        System.out.println(Post);
        System.out.println();

        PostF=Post;
        System.out.println(String.valueOf(PostF.length()-1));
        BETree.PostfixToPrefix(PostF.length()-1, PostF.length()-1);
        System.out.println(PreF);
        
        
        
    }
}