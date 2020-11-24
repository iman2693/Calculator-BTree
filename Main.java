import java.util.*;
import jdk.nashorn.internal.runtime.JSType;
class Main{
    public static void main(String args[])
        throws java.io.IOException{
	char temp;  // Baraye Khali kardane Catche.
	char choice; 
	Scanner Input=new Scanner(System.in);
	String InFixNotation = null;
        BTree bt = new BTree(); 
        boolean flag;
	do{
            System.out.println("\t__________________________________________\n\t\\\t\t\t\t\t /\n\t|\t[S]et the display format\t |\n\t|\t[E]nter a new expression\t |\n\t|\t[Q]uit\t\t\t\t |\n\t/________________________________________\\");
            System.out.print("\n\tEnter Your Choice: ");
            choice=(char)System.in.read();
            do{
		temp=(char)System.in.read();
            }while(temp!='\n');
            switch (choice){
		case 's':
		case 'S':{
                    do{
                        System.out.println("\t____________________________\n\t\\\t\t\t   /\n\t|\t[P]ostfix\t  |\n\t|\t[I]nfix\t\t  |\n\t|\tp[R]efix\t  |\n\t/__________________________\\");
                        System.out.print("\nEnter your preferred output display:  ");
                        choice=(char)System.in.read();
                        do{
                            temp=(char)System.in.read();
                        }while(temp!='\n');
                        flag=true;
                        switch (choice){
                            case 'p':
                            case 'P':{
                                if(bt.getroot()!=null){
                                    bt.PostOrder(bt.getroot());
                                    System.out.print(" = " + Evaluate(bt.getroot()));
                                    System.out.println();
                                }
                                else{
                                    System.out.println("ERROR!");
                                }
                                flag=false;
                                break;
                            }
                            case 'i':
                            case 'I':{
                                if(bt.getroot()!=null){
                                    System.out.print(InFixNotation+" = " + Evaluate(bt.getroot()));
                                    System.out.println();
                                }
                                else{
                                    System.out.println("ERROR!");
                                }
                                flag=false;
                                break;
                            }
                            case 'r':
                            case 'R':{
                                if(bt.getroot()!=null){
                                    System.out.println();
                                    bt.PreOrder(bt.getroot());
                                    System.out.print(" = " + Evaluate(bt.getroot()));
                                    System.out.println();
                                }
                                else{
                                    System.out.println("ERROR!");
                                }
                                flag=false;
                                break;
                            }
                            default:{
                                System.out.println("\t_________________________________________________________\n\t\\\t\t\t\t\t\t\t/\n\t|\tERROR! You must enter one of [P], [I] or [R]!\t|\n\t/________________________________________________________\\");
                                break;
                            }
                        }
                    }while(flag);			
                    break;
		}
		case 'e':
                case 'E':{
                    System.out.print("Enter your expression in infix notation: ");
                    InFixNotation=Input.nextLine().replace(" ", "");
                    bt = BuildExpression(InFixNotation);
                    if(bt.getroot()!=null){
                                    System.out.print(InFixNotation+" = " + Evaluate(bt.getroot()));
                                    System.out.println();
                    }
                    else{
                        System.out.println("ERROR!");
                    }
                    break;
		}
		case 'q':
		case 'Q':{       
                    System.out.println("\t_________________________\n\t\\\t\t\t/\n\t|\tGood Bye!!\t|\n\t/________________________\\");
                    break;
                }
                default:{
                    System.out.println("\t_________________________________________________________\n\t\\\t\t\t\t\t\t\t/\n\t|\tERROR! You must enter one of [E], [S] or [Q]!\t|\n\t/________________________________________________________\\");
		}
            }
	}while(choice!='q' && choice!='Q');
    }
    public static BTree  BuildExpression(String s){
        Stack <TNode> st = new Stack();
        BTree bt = new BTree();
        for(int i=0 ; i<s.length() ; i++){
                if(s.charAt(i)=='+' || s.charAt(i)=='-' || s.charAt(i)=='/' || s.charAt(i)=='*' ){
                    String s1="";
                    int j =i;
                    while(s.charAt(j-1)!=')' && s.charAt(j-1)!='(' && Character.isDigit(s.charAt(j-1))){  // Dar Inja Az Operator be Samte Parantez Miravim ta Adad samt Chap ra bedast Avarim
                        s1=s1+s.charAt(j-1);
                        j--;
                    }
                    if(s1!=""){
                        String ss=new StringBuilder(s1).reverse().toString(); // reverse chon Adad bar Aks Hast
                        TNode y=new TNode(ss);
                        st.push(y);
                    }
                    TNode x = new TNode(Character.toString(s.charAt(i)));   // Data = Operator 
                    st.push(x);
                    j=i;
                    String s2="";
                    while(s.charAt(j+1)!='(' && s.charAt(j+1)!=')' && Character.isDigit(s.charAt(j+1))){  // Ebarat Samt Rast Operator
                        s2=s2+s.charAt(j+1);
                        j++;
                    }
                    if(s2!=""){
                        TNode z=new TNode(s2);
                        st.push(z);
                    }
                }
                else if(s.charAt(i)==')'){
                    try{
                        TNode x = st.pop();
                        TNode y = st.pop();
                        TNode z = st.pop();
                        y.LC=z;
                        z.P=y;
                        y.RC=x;
                        x.P=y;
                        st.push(y);
                        bt.setroot(y);
                    }catch(java.util.EmptyStackException e){
                        System.out.println("Bad Expression!!");
                        break;
                    }
            }
        }   
        return bt;
    }
    public static int Evaluate(TNode x){
        if(x==null){
            return 0;
        }
        if(JSType.isNumber(x.Data)){ // Agar Data Yek Adad Bood
            return Integer.parseInt(x.Data);
        }
        else{
                switch(x.Data){
                    case "+":
                        return Evaluate(x.LC)+Evaluate(x.RC);
                    case "-":
                        return Evaluate(x.LC)-Evaluate(x.RC);
                    case "/":
                        return Evaluate(x.LC)/Evaluate(x.RC);
                    case "*":
                        return Evaluate(x.LC)*Evaluate(x.RC);
                    default:
                        return 0;
                }
            }
    }
}