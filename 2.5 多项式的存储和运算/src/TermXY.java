//二元项类
public class TermXY extends TermX{

    protected int yexp;

    public TermXY(int coef, int xexp, int yexp)
    {
        super(coef, xexp);
        this.yexp= yexp;
    }
    //以“系数x^指数y^指数”构造
    public TermXY(String termstr)
    {
        super(termstr);
        this.yexp= 0;
        int i= termstr.indexOf("y");
        if(i != -1)
        {
            this.yexp= 1;
            int j= this.coef;
//            if(this.xexp == 0 )
//                this.coef = String.valueOf(j).charAt(0)
            //重新将造好的x项，尾部插入y项次方
            if(i +1< termstr.length() && termstr.charAt(i +1) == '^')
                this.yexp=  getparse(termstr, i +2);
        }
    }

    public TermXY(TermXY term)
    {
        this(term.coef, term.xexp, term.yexp);
    }
    //加一个y的字符串描述
    public String toString()
    {
        //先写好x之前的部分
        String s= super.toString();
        if(this.yexp > 0)
            s+= "y";
        if(this.yexp > 1)
            s+= "^"+ this.yexp;
        return s;
    }
    //判断二元项是否相等
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj instanceof TermXY)
        {
            //super将x项以前的先判断相等
            if(super.equals(obj) && this.yexp == ((TermXY)obj).yexp)
                return true;
        }
        return false;
    }
    public void multi(TermXY term)
    {
        this.coef *= term.coef;
        this.xexp += term.xexp;
        this.yexp += term.yexp;
    }
    //比较大小，指数判断
    public int compareTo(TermXY term)
    {
        int i= super.compareTo(term);
        if(i == 0 && this.yexp == term.yexp)
            return 0;
        if(i < 0)
            return -1;
        return 1;
    }
    public boolean removeblex()
    {
        return this.xexp == 0;
    }
    public boolean removebley()
    {
        return this.yexp == 0;
    }

}
