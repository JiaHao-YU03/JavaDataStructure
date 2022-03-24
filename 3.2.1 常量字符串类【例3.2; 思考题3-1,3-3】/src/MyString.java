//声明MyString常量字符串类，最终类，实现可比较接口和序列化接口
public final class MyString implements Comparable<MyString>, java.io.Serializable{

    private final char[] value;

    public MyString()
    {
        this.value= new char[0];
    }
    public MyString(java.lang.String s)                         //用常量字符串构造串，取长填数
    {
        this.value= new char[s.length()];
        for(int i= 0; i< s.length(); i++)
            this.value[i] = s.charAt(i);
    }
    //value数组从i开始的n个字符构造 新的串
    //小心数组 i和n 越界
    public MyString(char[] value, int i, int n)
    {
        if(i >= 0 && n >= 0 && i+n <= value.length)
        {
            this.value = new char[n];
            for(int j= 0; j< n; j++)
                this.value[j] = value[i+j];
        }
        else
            throw new StringIndexOutOfBoundsException("i= "+ i +",n= "+ n +",i+n= "+ (i+n));
    }
    public MyString(char[] value)                               //字符数组构造 串
    {
        this(value, 0, value.length);
    }
    //拷贝构造方法，深拷贝，复制字符
    public MyString(MyString s)
    {
        this(s.value);
    }
    public int length()
    {
        return this.value.length;
    }
    //返回this串删除所有空格的字符串
    public MyString trim()
    {
        char[] str= new char[this.value.length];
        for(int i= 0; i< this.value.length; i++)
            if(this.value[i] != ' ')
                str[i]= this.value[i];
        return new MyString(str);
    }

    //常量字符串输出，即对字符数组的输出
    public java.lang.String toString()
    {
        return new String(this.value);
    }
    //返回第i个字符。注意i越界
    public char charAt(int i)
    {
        if(i >= 0 && i < this.value.length)
            return this.value[i];
        else
            throw new StringIndexOutOfBoundsException("i= "+ i);
    }
    public MyString substring(int begin, int end)
    {
        if(begin == 0 && end == this.value.length)
            return this;
        return new MyString(this.value, begin, end-begin);
    }
    //连接串：返回this 和 s串连接生成的串
    public MyString concat(MyString s)
    {
        if(s == null || s.equals(""))
            s= new MyString(this.value);
        int i;
        char[] buffer= new char[this.value.length + s.value.length];
        for(i= 0; i< this.value.length; i++)
            buffer[i]= this.value[i];
        for(int j= 0; j< s.length(); j++)
            buffer[i + j]= s.value[j];
        return new MyString(buffer);
    }
    //比较相等：即引用，长度，数据元素相等
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj instanceof String)
        {
            MyString str= (MyString)obj;
            if(this.value.length == str.value.length)
            {
                for(int i= 0; i< this.value.length; i++)
                    if(this.value[i] != str.value[i])
                        return false;
                    return true;
            }
        }
        return false;
    }
    //比较串大小：长度相等 比较首个不等元素的差值；长度不等 比较长度差值
    public int compareTo(MyString s)
    {
        for(int i= 0; i< this.value.length && i< s.value.length; i++)
            if(this.value[i] != s.value[i])
                return this.value[i] - s.value[i];
            return this.value.length - s.value.length;
    }
    //比较this与s串的大小，返回两者差值，忽略字母大小写
    public int compareToIgnoreCase(MyString s)
    {
        for(int i= 0; i< this.value.length && i< s.value.length; i++)
            if(this.value[i] != s.value[i]) {
                //判断需改进
                if(this.value[i] > 'A' && this.value[i] < 'Z' && s.value[i] > 'a' && s.value[i] < 'z')
                    return (char)(this.value[i]+32) - s.value[i];
                else if(s.value[i] > 'A' && s.value[i] < 'Z' && this.value[i] > 'a' && this.value[i] < 'a')
                    return this.value[i] - (char)(s.value[i]+32);
                return this.value[i] - s.value[i];
            }
        return this.value.length - s.value.length;
    }
    //在this串(目标串)中查找首个与模式串pattern匹配的子串 并返回序号，匹配失败返回-1
    //index方法传递
    public int index(MyString pattern)
    {
        return this.index(pattern, 0);
    }
    //对begin容错，若其小于零，从零开始；若序号越界，则查找不成功
    //若pattern == null，抛出空对象异常
    public int index(MyString pattern, int begin)
    {
        int n= this.length(), m= pattern.length();                  //n=目标串长度，m=模式串长度
        if(begin < 0)                                               //begin容错
            begin= 0;
        if(n == 0 || n < m || begin >= n)                           //目标串空，模式串长，begin越界
            return -1;
        int i= begin, j= 0;                                         //i, j分别代表目标串和模式串 的字符下标
        while(i < n && j < m)
        {
            //发现两个比较字符相同，继续比较后面的
            if(this.charAt(i) == pattern.charAt(j))
            {
                i++;
                j++;
            }
            //一旦匹配不成功，i, j回到首个匹配位置，再接着进行下一次匹配
            //i返回到下一个匹配字符位置，j返回0
            //目标串长度不足以匹配下一次，直接退出
            else
            {
                i= i - j +1;
                j= 0;
                if(i > n-m)
                    break;
            }
        }
        return j == m ? i-m : -1;                                   //匹配成功，返回首个匹配字符(子串)序号
    }
}
class MyString_ex
{
    public static void main(String[] args) {
        MyString str1= new MyString();
        MyString str2= new MyString("abc");
        char[] letters={'a', 'b', 'c', 'd', 'e'};
        MyString str3= new MyString(letters);
        MyString str4= new MyString(str3);

        //例3.1  插入与删除串
        //插入
        String s1= "abedf", s2= "xyz";
        int i= 2;
        String s3= s1.substring(0, i) + s2 + s1.substring(i);
        System.out.println(s3.toString());
        //删除
        int begin= 3, end= 6;
        String s4= s3.substring(0, begin) + s3.substring(end);
        System.out.println(s4.toString());
    }
}
