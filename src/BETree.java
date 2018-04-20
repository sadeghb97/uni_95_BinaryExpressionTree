public class BETree {
    private Node root;
    private int size;
    
    public BETree(){
        size=0;
    }
    public BETree(Node n){
        root=n;
        size=1;
    }
    
    public static String isCorrectParaInfixExp(String str){
        int IOPara=-1,NCPara=0;
        int [][]strPara=new int[100][2];
        for(int i=0; 100>i; i++){
            strPara[i][0]=-1;
            strPara[i][1]=-1;
        }
        int rowOptChar=0;
        int rowOpnChar=0;
        for(int i=0; str.length()>i; i++){
            char c=str.charAt(i);
            if(c=='(' || c==')'){
                rowOptChar=0;
                rowOpnChar=0;
                if(c=='('){
                    strPara[++IOPara][0]=i;
                }
                if(c==')'){
                    boolean isSet=false;
                    NCPara++;
                    for(int j=IOPara; j>=0; j--){
                        if(strPara[j][1]==-1){
                            strPara[j][1]=i;
                            isSet=true;
                            break;
                        }
                    }
                    if(isSet==false) return "ParaError";
                }
            }
            else if(myString.OperatorDetecter(c)!=0){
                rowOpnChar=0;
                rowOptChar++;
            }
            else{
                rowOptChar=0;
                rowOpnChar++;
            }
            if(rowOpnChar>1)
                return "OperandError";
            if(rowOptChar>1)
                return "OperatorError";
        }
        if(strPara[0][0]!=0 || strPara[0][1]!=str.length()-1){
            return "StartEndParaError";
        }
        
        for(int i=0; IOPara>=i; i++){
            if(strPara[i][0]+1==strPara[i+1][0]){
                if(myString.OperatorDetecter(str.charAt(strPara[i+1][1]+1))==1)
                    return "OperatorTypeError";
                else if(myString.OperatorDetecter(str.charAt(strPara[i+1][1]+1))==0)
                    return "OperatorMissedError";
                else{
                    if(str.charAt(strPara[i+1][1]+2)==')')
                        return "OperandMissedError";
                    if(str.charAt(strPara[i+1][1]+2)=='('){
                        int openpi=-1;
                        for(int j=0; strPara[j][0]!=-1; j++){
                            if(strPara[j][0]==strPara[i+1][1]+2){
                                openpi=j;
                                break;
                            }
                        }
                        if(openpi==-1)
                            throw new RuntimeException();
                        if(strPara[openpi][1]+1!=strPara[i][1])
                            return "ParaLenghthError";
                    }
                    else if(myString.OperatorDetecter(str.charAt(strPara[i+1][1]+2))==0){
                        if(strPara[i+1][1]+3!=strPara[i][1])
                            return "ParaLenghthError";
                    }
                }
            }
            else if(str.charAt(strPara[i][0]+1)==')')
                return "EmptyParaError";
            else if(myString.OperatorDetecter(str.charAt(strPara[i][0]+1))==0){
                if(str.charAt(strPara[i][0]+2)==')')
                    return "OperatorMissedError";
                if(myString.OperatorDetecter(str.charAt(strPara[i][0]+2))==1)
                    return "OperatorTypeError";
                if(str.charAt(strPara[i][0]+3)==')')
                    return "OperandMissedError";
                if(strPara[i+1][0]==strPara[i][0]+3){
                    if(strPara[i+1][1]+1!=strPara[i][1])
                        return "ParaLenghthError";
                }
                else if(myString.OperatorDetecter(str.charAt(strPara[i][0]+3))==0){
                    if(strPara[i][0]+4!=strPara[i][1])
                        return "ParaLenghthError";
                }
            }
            else if(myString.OperatorDetecter(str.charAt(strPara[i][0]+1))==1){
                if(strPara[i+1][0]==strPara[i][0]+2){
                    if(strPara[i+1][1]+1!=strPara[i][1])
                        return "ParaLenghthError";
                }
                else if(myString.OperatorDetecter(str.charAt(strPara[i][0]+2))==0){
                    if(strPara[i][1]!=strPara[i][0]+3)
                        return "ParaLenghthError";
                }
            }
            else
                return "OperatorTypeError";
        }
        return "true";
    }
        
    private static Node InnerParaInfixStringToBET(String str, int Start, int End){
        Node n=new Node();
        int Index;
        int OpenPara=0;
        int ClosePara=0;
        for(Index=Start; End>=Index; Index++){
            char c=str.charAt(Index);
            int charType=myString.OperatorDetecter(c);
            if(c=='(') OpenPara++;
            else if(c==')') ClosePara++;
            else if(OpenPara==ClosePara+1 && OpenPara!=0){
                if(charType==1){
                    n.setElement(String.valueOf(c));
                    n.setRight(InnerParaInfixStringToBET(str,Index+1,End-1));
                    return n;
                }
                else if(charType==2){
                    n.setElement(String.valueOf(c));
                    n.setLeft(InnerParaInfixStringToBET(str,Start+1,Index-1));
                    n.setRight(InnerParaInfixStringToBET(str,Index+1,End-1));
                    return n;
                }
            }
        }
        n.setElement(String.valueOf(str.charAt(Start)));
        return n;
    }
    
    public static Node ParaInfixStringToBET(String str){
        if(isCorrectParaInfixExp(str)=="true")
            return InnerParaInfixStringToBET(str, 0, str.length()-1);
        throw new RuntimeException();
    }
    
    public static Node PostfixStringToBET(String str){
        Stack st=new Stack();
        for(int i=0; str.length()>i; i++){
            if(myString.OperatorDetecter(str.charAt(i))==0){
                Node n=new Node(String.valueOf(str.charAt(i)));
                st.push(n);
            }
            else if(myString.OperatorDetecter(str.charAt(i))==1){
                Node n=new Node(String.valueOf(str.charAt(i)));
                n.setRight(st.pop());
                st.push(n);
            }
            else if(myString.OperatorDetecter(str.charAt(i))==2){
                Node n=new Node(String.valueOf(str.charAt(i)));
                n.setRight(st.pop());
                n.setLeft(st.pop());
                st.push(n);
            }
        }
        if(st.GetSize()!=1)
            throw new RuntimeException();
        return st.pop();
    }
    
    public static String InfixToPostfix(String str){
        CharStack st=new CharStack();
        String out="";
        for(int i=0; str.length()>i; i++){
            char c=str.charAt(i);
            if(c=='('){
                st.push(c);
            }
            else if(c==')'){
                while(st.GetTop()!='(')
                    out=out+st.pop();
                st.pop();
            }
            else if(myString.SendPeriority(c)==0){
                out=out+c;
            }
            else{
                if(st.GetSize()==0 || myString.SendPeriority(c)>myString.SendPeriority(st.GetTop()))
                    st.push(c);
                else if(st.GetSize()==0 || (myString.SendPeriority(c)==myString.SendPeriority(st.GetTop()) && c=='^')){
                    st.push(c);
                }
                else if(myString.SendPeriority(c)<=myString.SendPeriority(st.GetTop())){
                    while(st.GetSize()!=0 && st.GetTop()!='(')
                        out=out+st.pop();
                    st.push(c);
                }
            }
        }
        while(st.GetSize()!=0)
            out=out+st.pop();
        return out;
    }
    
    public static void PostfixToPrefix(int i, int j){
        char operator;
        switch(myString.OperatorDetecter(BinaryExpressionTree.PostF.charAt(i))){
            case 0:
                BinaryExpressionTree.PreF=myString.ReplaceChar(BinaryExpressionTree.PreF, BinaryExpressionTree.PostF.charAt(i), j);
                break;
            case 2:
                operator=BinaryExpressionTree.PostF.charAt(i);
                i--;
                PostfixToPrefix(i, j);
                i--;
                j--;
                PostfixToPrefix(i, j);
                BinaryExpressionTree.PreF=myString.ReplaceChar(BinaryExpressionTree.PreF, operator, j-1);
                j--;
                break;
            case 1:
                operator=BinaryExpressionTree.PostF.charAt(i);
                i--;
                PostfixToPrefix(i, j);
                BinaryExpressionTree.PreF=myString.ReplaceChar(BinaryExpressionTree.PreF, operator, j-1);
                j--;
                break;
        }
        //System.out.println(BinaryExpressionTree.PreF);
        
    }
    
    private int GetSizeFromNode(Node n){
        if(n==null)
            return 0;
        return 1+GetSizeFromNode(n.getLeft())+GetSizeFromNode(n.getRight());
    }
    
    public boolean isEmpty(){
        return root==null;
    }
    
    public Node setRoot(Node n){
        root=n;
        size=GetSizeFromNode(root);
        return root;
    }
    public Node getRoot(){
        if(root==null){
            throw new RuntimeException();
        }
        return root;
    }
    public String GetInfix(Node p){
        if(p==null){
            throw new NullPointerException();
        }
        String out="";
        if(p.getRight()!=null) out=out+"(";
        if(p.getLeft()!=null) out=out+GetInfix(p.getLeft());
        out=out+p.getElement();
        if(p.getRight()!=null){
            out=out+GetInfix(p.getRight());
            out=out+")";
        }
        return out;
    }
    public String GetPrefix(Node p){
        if(p==null){
            throw new NullPointerException();
        }
        String out="";
        out=out+p.getElement();
        if(p.getLeft()!=null) out=out+GetPrefix(p.getLeft());
        if(p.getRight()!=null) out=out+GetPrefix(p.getRight());
        return out;
    }
    public String GetPostfix(Node p){
        if(p==null){
            throw new NullPointerException();
        }
        String out="";
        if(p.getLeft()!=null) out=out+GetPostfix(p.getLeft());
        if(p.getRight()!=null) out=out+GetPostfix(p.getRight());
        out=out+p.getElement();
        return out;
    }
    
}