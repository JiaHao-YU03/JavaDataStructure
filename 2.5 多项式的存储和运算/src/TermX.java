//一元多项式的项类，实现可比较接口
public class TermX implements Comparable<TermX>, Addible<TermX>{

    protected int coef, xexp;

    public TermX(int coef, int xexp)
    {
        this.coef= coef;
        this.xexp= xexp;
    }
    public TermX(TermX term)
    {
        this(term.coef, term.xexp);
    }
    //以“系数x^指数”构造一元多项式的一项
    //getInt方法提取系数
    public TermX(String termstr)
    {
        //先是指数为0
//        int coef= 0;
        this.xexp= 0;
        this.coef= getparse(termstr, 0);
        int i= termstr.indexOf("x");
        //指数不为0
        if(i != -1)
        {
            this.xexp= 1;
            if(i+1 < termstr.length() && termstr.charAt(i +1) == '^')
                this.xexp= getparse(termstr, i +2);
        }
    }
    //转换数字
    public int getparse(String str,int begin)
    {
        int i= begin, value= 0, sign= 0;
        char ch= '0';

//        if(str.length() == 1)
//            throw
        //正负号
        if(str.charAt(i) == '-')
            sign= -1;
        else
            sign= 1;
        //跳过符号位
        if(str.charAt(i) == '+' || str.charAt(i) == '-')
          i+= 1;

        for(; i< str.length() && (ch= str.charAt(i)) >= '0' && ch <= '9'; i++)
            value= 10* value + ch - '0';
        if(value == 0)
            return sign;
        return value * sign;
    }
    //返回项的字符串：“系数x^指数”
    public String toString()
    {
        String s;
        if(this.coef > 0)
            s= "+";
        else
            s= "-";
        if(this.xexp == 0 || this.xexp > 0 && this.coef != 1 && this.coef != -1)
            s+= Math.abs(this.coef);
        if(this.xexp > 0)
            s+= "x";
        if(this.xexp > 1)
            s+= "^"+ this.xexp;
        return s;
    }
    //比较相等，系数和指数
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj instanceof TermX)
        {
            TermX term= (TermX)obj;
            if(this.coef == term.coef && this.xexp == term.xexp)
                return true;
        }
        return false;
    }
    //比较大小，按x指数
    public int compareTo(TermX term) {
        if (this.xexp == term.xexp)
            return 0;
        return (this.xexp < term.xexp) ? -1 : 1;
    }
    //实现接口，项系数的相加
    public void add(TermX term)
    {
        this.coef += term.coef;
    }
    //实现接口，项系数的删除
    public boolean removeable()
    {
        return this.coef == 0;
    }

}
