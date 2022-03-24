//声明变量字符串类，最终类，实现序列化接口
public final class MyStringBuffer implements java.io.Serializable {

    private char[] value;                                    //String 以字符数组储存
    private int n;                                           //串长度

    //以下是构造方法
    public MyStringBuffer(int capacity)                      //构造空串
    {
        this.value = new char[capacity];
        this.n = 0;
    }
    public MyStringBuffer()                                  //构造空串，按自设长度
    {
        this(16);
    }
    public MyStringBuffer(String s)                          //以字符串常量构造串
    {
        this(s.length()+16);
        this.n = s.length();
        for(int i=0; i<this.n; i++)                          //复制s串所有字符元素
            this.value[i] = s.charAt(i);
    }

    //返回字符串长度
    public int length()
    {
        return this.n;
    }
    //返回字符数组容量
    public int capacity()
    {
        return this.value.length;
    }
    //返回第i个字符
    public synchronized char charAt(int i)
    {
        if(i >= 0 && i < this.n)
            return this.value[i];
        throw new StringIndexOutOfBoundsException(i);
    }
    //设置第i个字符为ch
    public void setCharAt(int i, char ch)
    {
        if(i >= 0 && i < this.n)
            this.value[i] = ch;
        else throw new StringIndexOutOfBoundsException(i);
    }
    //toString：以value数组从0至n字符构造String串
    public synchronized String toString()
    {
        return new String(this.value, 0, this.n);
    }
    //设置串长度为n。若数组容量不足，则扩充长度
    public synchronized void setLength(int n)
    {
        if(n > 0)
        {
            //以下原长大于 数组长度就扩充
            if(n > this.value.length)
            {
                char[] temp= this.value;
                this.value= new char[n*2];                       //重新申请字符数组空间
                for(int i= 0;  i< temp.length;  i++)            //将原串的字符元素复制到新串中
                    this.value[i]= temp[i];
            }
            for(int i= this.n;  i< this.value.length;  i++)        //多余数组位置补空格
                this.value[i]= ' ';
            this.n= n;                                          //更新串长度
        }
        else throw new NegativeArraySizeException("n="+n);       //抛出负数组长度异常
    }


    //插入操作：在第i个字符处插入s串。若i序号越界，则抛出字符串序号越界异常，一定注意插入顺序
    public synchronized MyStringBuffer insert(int i, String s)
    {
        if(this.n == 0 && i == 0  ||  this.n > 0 && i >= 0 && i <= this.n)
        {
            //若s==null，则插入""
            if(s == null)
                s= "";
            char[] temp= this.value;
            //若数组空间不够，扩充数组长度
            if(this.value.length < this.n + s.length())
            {
                this.value = new char[(this.value.length + s.length())*2];          //重新申请空间，大于原串和插入串
                for(int j= 0;  j< i;  j++)                                          //复制当前串前i-1个字符
                    this.value[j]= temp[j];
            }
            for(int j= this.n-1;  j>= i;  j--)                      //从i开始至串尾的子串向后移动，次序 从后向前
                this.value[j + s.length()]= temp[j];
            for(int j= 0;  j< s.length();  j++)                     //再插入s串
                this.value[i+j]= s.charAt(j);
            this.n += s.length();
            return this;
        }
        else
            throw new StringIndexOutOfBoundsException("i="+i);      //抛出字符串序号越界异常
    }
    //算法同上
    public synchronized MyStringBuffer insert(int i, MyStringBuffer sbuf)
    {
        if(this.n == 0 && i == 0  ||  this.n > 0 && i >= 0 && i <= this.n)
        {
            //若s==null，则插入""
            if(sbuf == null)
                sbuf= null;
            char[] temp= this.value;
            //若数组空间不够，扩充数组长度
            if(this.value.length < this.n + sbuf.length())
            {
                this.value = new char[(this.value.length + sbuf.length())*2];          //重新申请空间，大于原串和插入串
                for(int j= 0;  j< i;  j++)                                          //复制当前串前i-1个字符
                    this.value[j]= temp[j];
            }
            for(int j= this.n-1;  j>= i;  j--)                      //从i开始至串尾的子串向后移动，次序 从后向前
                this.value[j + sbuf.length()]= temp[j];
            for(int j= 0;  j< sbuf.length();  j++)                     //再插入s串
                this.value[i+j]= sbuf.charAt(j);
            this.n += sbuf.length();
            return this;
        }
        else
            throw new StringIndexOutOfBoundsException("i="+i);      //抛出字符串序号越界异常
    }
    //插入：在i处插入变量值转换成的串
    public synchronized MyStringBuffer insert(int i, boolean b)
    {
        return this.insert(i, b ? "true" : "false");
    }
    //添加s串
    public synchronized MyStringBuffer append(String s)
    {
        return this.insert(this.n, s);
    }

    //删除操作：删除从begin到end-1的子串
    //若end≥length()，则删除到串尾；若begin越界或begin>end，则抛出字符串序号越界异常
    public synchronized MyStringBuffer delete(int begin, int end)
    {
        if(begin>=0 && begin<this.n  &&  end>=0 && begin<=end)
        {
            if(end > this.n)                                            //end超出长度，容错
                end= this.n;
            for(int i= 0;  i< this.n - end;  i++)                       //从end开始至串尾向前移动
                this.value[begin + i]= this.value[end + i];
            this.n -= end - begin;
            return this;
        }
        else
            throw new StringIndexOutOfBoundsException("begin="+ begin +"，end="+ end +"，end-begin="+ (end-begin));
    }

    //逆转字符串：逆转字符串
    public MyStringBuffer reverse()
    {
        for(int i= 0;  i< this.n /2;  i++)
        {
            char temp= value[i];
            value[i]= value[this.n - i-1];
            value[this.n - i-1]= temp;
        }
        return this;
    }

    //【实验题3-4】将从begin到end-1的子串替换为s串。可行，效率较低
    public MyStringBuffer replace(int begin, int end, String s)
    {
        this.delete(begin, end);
        this.insert(begin, s);
        return this;
    }
    //【思考 3-4】将s中所有空格删除，返回操作后的s串
    //public static  StringBuffer trim(StringBuffer s)

    public static void main(String[] args) {

        MyStringBuffer sbuf = new MyStringBuffer(8);              //默认16
        System.out.println("空串，\""+sbuf+"\"，length()="+sbuf.length()+"，capacity()="+sbuf.capacity());

        sbuf.insert(0, "abcdef");                                    //插入串，没有用到返回值
        System.out.println("插入，\""+sbuf+"\"，length()="+sbuf.length()+"，capacity()="+sbuf.capacity());

        String[] str = {"xy", null};
        int i=2;                                                             //序号i不容错，抛出异常
        for (int j=0; j<str.length; j++)
            System.out.println("插入，\""+sbuf+"\".insert("+i+",\""+str[j]+"\")=\""+
                    sbuf.insert(i,str[j])+"\"，length()="+sbuf.length()+"，capacity()="+sbuf.capacity());
//      sb1.append(null);                  //编译错，参数的数据类型Object是String或不明确

        int[] begin={2,4,2}, end={6,10,2};
        for(int j=0;  j<begin.length;  j++)
            System.out.println("删除，\""+sbuf+"\".delete("+begin[j]+","+end[j]+")=\""+
                    sbuf.delete(begin[j],end[j])+"\"，length()="+sbuf.length()+"，capacity()="+sbuf.capacity());
        
        sbuf.setLength(30);                      //加长字符串，补充' '，扩充容量
        System.out.println("加长，\""+sbuf+"\"，length()="+sbuf.length()+"，capacity()="+sbuf.capacity());
        sbuf.setLength(10);                      //缩短字符串
        System.out.println("缩短，\""+sbuf+"\"，length()="+sbuf.length()+"，capacity()="+sbuf.capacity());

    }
}





/*
//将从begin到end-1的子串替换为s串。每字符一次移动到位。效率较高？？
MyStringBuffer replace(int begin, int end, String s)
{
    if (begin>=0 && begin<this.n && end>=0 && begin<=end)
    {
        if (end>this.n)                                //end超长容错
            end=this.n;
        for(int i=0; i<this.n-end; i++)                //从end开始至串尾的子串向前移动
            this.value[begin+i] = this.value[end+i];

        this.n -= end-begin;
        return this;
    }
    else throw new StringIndexOutOfBoundsException("begin="+begin+"，end="+end+"，end-begin="+(end-begin));

    System.arraycopy(value, end, value, begin + n, n - end);
    s.getChars(value, begin);
    n = newCount;
    return this;

    char temp[]=this.value;
    if (this.value.length-this.n < s.length() - (end - begin))     //若当前串的数组空间不足，则扩充容量
    {
        this.value = new char[this.value.length+s.length()*2]; //重新申请字符数组空间
        for (int j=0; j<i; j++)                        //复制当前串前i-1个字符
            this.value[j] = temp[j];
    }
    for (int j=this.n-1; j>=i; j--)
        this.value[s.length()+j] = temp[j];          //从i开始向后移动n个字符
    for (int j=0; j<s.length(); j++)                 //复制字符串s
        this.value[i+j] = s.charAt(j);
    this.n += s.length();
    return this;
}*/
