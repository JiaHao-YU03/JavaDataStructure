//【例4.2】 算术表达式求值，使用栈。//StringBuffer
//声明ArithExpression算术表达式类
//算术表达式求值。整数；算术运算和位运算，双目、单字符运算符，没有正负号，忽略空格
public class ArithExpression
{
    private static Operators operators;          //使用运算符集合

    static
    {
        operators= new Operators();
    }
    //由infix中缀表达式构造  后缀表达式
    public ArithExpression(String infix)
    {
        StringBuffer postfix= toPostfix(infix);                            //转换成后缀表达式
        System.out.println("infix=\""+ infix +"\"\npostfix=\""+ postfix +"\"");
//        System.out.println("infix=\""+ infix +"\"，length()="+ infix.length());
//        System.out.println("postfix=\""+ postfix +"\"，length()="+ postfix.length());
        System.out.println("value="+ toValue(postfix));
    }
    //空构造
    public ArithExpression() {}

    //toPostfix方法 返回将infix中缀表达式转换成的后缀表达式
    public StringBuffer toPostfix(String infix)
    {
        Stack<String> stack= new SeqStack<String>(infix.length());                 //运算符栈，顺序栈
        //后缀表达式字符串，由于为了区分数字与数字，所以之间要加空格，容量扩大
        StringBuffer postfix= new StringBuffer(infix.length()*2);

        int i= 0;
        while(i < infix.length())
        {
            char ch= infix.charAt(i);
            //对操作数或变量，添加到后缀表达式，没有正负符号
            if(ch >= '0' && ch <= '9' || ch >= 'A' && ch <= 'Z')
            {
                while(i < infix.length() && (ch=infix.charAt(i)) >= '0' && ch <= '9' || ch >= 'A' && ch <= 'Z')
                {
                    postfix.append(ch +"");
                    i++;                                            //注意：i++;不能写在while条件中，因为会多加1次
                }
                postfix.append(" ");                                //添加空格作为数值之间的分隔符
            }
            //对运算符
            else
            {
                switch(ch)
                {
                    case ' ': i++; break;                                   //跳过空格 i++;  break;
                    case '(':                                               //左括号，入栈
                        stack.push(ch +""); i++;  break;
                    case ')':                                               //右括号，出栈，直到出栈运算符为左括号，匹配
                        String out= "";
                        while ((out=stack.pop()) != null && !out.equals("("))
                            postfix.append(out);
                        i++;  break;

                    default:
                        //遇到所有运算符{"*","/","%","+","-","&","^","|"}，
                        //将ch运算符的优先级与栈顶运算符优先级比较大小，若栈顶运算符优先级高，则出栈。
                        //使用Comparator<T>比较器的compare()方法比较对象大小
                        while(!stack.isEmpty() && !stack.peek().equals("(")
                                && operators.compare(ch+"", stack.peek()) >=0)
                            postfix.append(stack.pop());                         //出栈运算符添加到后缀表达式串
                        stack.push(ch +"");                                    //当前运算符入栈
                        i++;
                }
            }
        }

        while(!stack.isEmpty())                                                  //所有运算符出栈
            postfix.append(stack.pop());                                        //添加到postfix串之后
        return postfix;
    }
    //计算后缀表达式的值
    public int toValue(StringBuffer postfix)
    {
//        LinkedStack<int> stack= new LinkedStack<int>();                       //语法错
//        Stack<Integer> stack= new SeqStack<Integer>();                          //操作数栈，顺序栈
        Stack<Integer> stack= new LinkedStack<Integer>();                     //操作数栈，链式栈
        int value= 0;
        for(int i= 0; i < postfix.length(); i++)                              //逐个检查后缀表达式中的字符
        {
            char ch= postfix.charAt(i);
            if(ch >= '0' && ch <= '9')                                        //遇到数字字符
            {
                value= 0;
                while(ch >= '0' && ch <= '9')                                 //将整数字符串转换为整数值，没有符号，以空格结束
                {
                    value = value*10 + ch-'0';
                    ch = postfix.charAt(++i);
                }
                stack.push(value);
                //new Integer(value)整数对象入栈，Java自动将int整数封装成Integer对象
            }
            else
            if(ch!=' ')                                                       //约定操作数后有一个空格分隔
            {   //出栈两个操作数，注意出栈次序。Java自动调用intValue()方法将Integer对象转换成int整数
                int y=stack.pop(), x=stack.pop();
                value = operators.operate(x,y,ch+"");                   //根据运算符分别计算
                System.out.print(x+(ch+"")+y+"="+value+"，");                //展示运算过程
                stack.push(value);                                          //运算结果入栈
            }
        }
        return stack.pop();                                                 //返回运算结果
    }

    public static void main(String args[])
    {
//      String infix="123";
//      String infix="123+10";
//        String infix="1+2*(3-4)+5";                          //图4.5

        //第5版例4.2 中缀表达式，整数；算术运算和位运算，双目、单字符运算符，没有正负号，忽略空格
//        String infix="123+20*(3|12^15&4+6)/((35-20)%10+5)-11"; //123+20*7/10-11=126
        String infix="123+(3+6+7+8)/((35-20)%10+5)-11";
        int value=123+20*(3|12^15&4+6)/((35-20)%10+5)-11;
//        String infix="(13+17)*((45-50+20)/((35-20)%10+5))"; //图6.27(a) 表达式二叉树，
//        String infix="((6^(4|9))&5)+20";                      //图6.27(b) 表达式二叉树，

//      String infix="45+(10-15)*((25+35)/(60-40))-11";      //图6.21 表达式二叉树，

        new ArithExpression(infix);
        System.out.println(infix+"="+value);
    }
}