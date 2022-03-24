//【例4.6】 用递归算法对算术表达式求值,整数，不包括位运算。
public class ArithmeticExpression {

    private String infix;                           //中缀算术表达式字符串
    private int index;                              //当前字符序号
    //构造方法，infix指定中缀表达式
    public ArithmeticExpression(String infix)
    {
        this.infix= infix;
        this.index= 0;
    }
    public ArithmeticExpression()
    {
        this("");
    }

    //计算从index开始的 一个算术表达式（+-号分割），返回整数值，其中进行多 <项> 加减运算
    public int intValue()
    {
        int value1= term();                                     //value1收到来自term返回的<项>1的值
        //进行表达式中 其他多 <项> 的加减运算
        while(this.index < this.infix.length())
        {
            char op= this.infix.charAt(this.index);
            //+-<项> 一起进行
            if(op == '+' || op == '-')
            {
                this.index++;
                int value2= term();                             //value2收到来自term返回的<项>2~n的值
                switch(op)                                      //对<项>1和<项>2进行加减
                {
                    //value1储存运算结果
                    case '+': value1 += value2;break;
                    case '-': value1 -= value2;break;
                }
            }
            else
                break;                                          //遇到‘(’,<项>结束
        }
        return value1;
    }
    //计算从index开始的 一个<项>（*/%号分割），其中进行多 <因子> 乘除运算
    public int term()
    {
        int value1= factor();                                     //value1收到来自factor返回的<项>1的值
        //进行表达式中 其他多 <因子> 的加减运算
        while(this.index < this.infix.length())
        {
            char op= this.infix.charAt(this.index);
            //*/%<项> 一起进行
            if(op == '*' || op == '/' || op == '%')
            {
                this.index++;
                int value2= factor();                              //value2收到来自factor返回的<项>2~n的值
                switch(op)
                {
                    //value1储存运算结果
                    case '*': value1 *= value2;break;
                    case '/': value1 /= value2;break;             //除数为0时，抛出算术异常
                    case '%': value1 %= value2;break;
                }
            }
            else
                break;                                              ////遇到‘(’,'+','-'时,<因子>结束
        }
        return value1;
    }
    //计算从index开始的 一个<因子>，其中包含以（）为界的子表达式，或是整数
    private int factor()
    {
        //判断有界的子表达式
        if(this.infix.charAt(this.index) == '(')
        {
            this.index++;                                   //跳过'('
            int value= intValue();                          //value收到来自intvalue返回的子表达式的值
            this.index++;                                   //跳过'('
            return value;
        }
        return constant();
    }
    //返回从index开始的一个<常数>
    private int constant()
    {
        if(this.index < this.infix.length())
        {
            char op= this.infix.charAt(this.index);
            int sign= 1, value= 0;
            if(op == '+' || op == '-')
            {
                sign= (op == '-') ? -1 : 1;                 //符号位，sign标记正负号
                this.index++;                               //跳过符号位
            }
            while(this.index < this.infix.length())
            {
                char ch= this.infix.charAt(this.index);
                if(ch >= '0' && ch <= '9')
                {
                    value = value*10 + ch-'0';               //value记录整数值
                    this.index++;
                }
                else
                    break;
            }
            return value*sign;                              //返回有符号的整数值
        }
        throw new NumberFormatException("\""+ infix.substring(this.index -1) +"\"不能转换成整数");
    }

    public static void main(String[] args) {

        String infix= "+123+10*(-50+45+20)/((-25+35)%2+10)+(-11)+0";
        System.out.println(infix +"="+ new ArithmeticExpression(infix).intValue());
    }
}