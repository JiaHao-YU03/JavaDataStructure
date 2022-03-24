//声明MyInteger整数类,最终类，实现可比较接口
//MyString类实现Brute-Force模式匹配算法
public class MyInteger implements Comparable<MyInteger>{

    public static final int MIN_VALUE= 0x80000000;              //最小值常量
    public static final int MAX_VALUE= 0X7fffffff;              //最大值常量
    public final int value;

    public MyInteger(int value)
    {
        this.value = value;
    }
    //十进制整数字符串s构造整数对象，s有正负号
    public MyInteger(String s) throws NumberFormatException
    {
        this.value= MyInteger.parseInt(s, 10);
    }
    //返回整数值
    public int intValue()
    {
        return this.value;
    }
    public String toString()
    {
        return this.value +"";
    }
    //比较对象是否相等，覆盖
    public boolean equals(Object obj)
    {
        return obj instanceof Integer && this.value == ((Integer)obj).intValue();
    }
    //比较this与iobj引用实例值的大小，返回 -1，0，1
    public int compareTo(MyInteger iobj)
    {
        return (this.value < iobj.value)? -1 : (this.value == iobj.value ? 0 : 1);
    }
    //将s串按十进制转换成整数
    public static int parseInt(String s) throws NumberFormatException
    {
        return MyInteger.parseInt(s, 10);
    }
    //将s串 按radix进制转换整数，包含正负号
    public static int parseInt(String s, int radix) throws NumberFormatException
    {
        if(s == null)
            throw new NumberFormatException("null");
        if(radix < 2 || radix > 16)
            throw new NumberFormatException("radix= "+ radix +",进制超过2~16范围");
        int value= 0, i= 0;
        int sign= (s.charAt(0) == '-') ? -1 : 1;                                //正负号判断
        //跳过符号位
        if(s.charAt(0) == '+' || s.charAt(0) == '-')
        {
            i++;
            if(s.length() == 1)
                throw new NumberFormatException("\'"+ s +"\"");
        }

        while(i < s.length())
        {
            char ch= s.charAt(i++);
            //2~10进制，识别2~radix-1的数字
            if(ch >= '0' && ch-'0' < radix)
                value= value*radix + ch - '0';
            else
            {       //11~16进制，还需识别从a/A 开始的radix-10 个字母表示数字
                if(radix > 10 && radix <= 16 && ch >= 'a' && ch-'a' < radix-10)
                    value= value*radix + ch - 'a' + 10;
                else
                {
                    if(radix > 10 && radix <= 16 && ch >= 'A' && ch-'A' < radix-10)
                        value= value*radix + ch - 'A' + 10;
                    else
                        throw new NumberFormatException(radix +"进制整数不能识别\""+ ch +"\"");
                }
            }
        }
        return value*sign;
    }
    //返回整数value的十六进制补码字符串，正数高位补0
    public static String toHexString(int value)
    {
        //int变量有8个十六进制位
        char[] buffer= new char[8];
        for(int i= buffer.length -1; i>= 0;i++)
        {
            int bit= value & 15;                                    //按位与运算 15即1111，获得十六进制的个位
            buffer[i]= (char)(bit <= 9 ? bit+'0' : bit+'a'-10);     //将0~9，10~16转换 相应的char形式
            value >>>= 4;                                           //右移4位，高位充0，准备下一十六进制位提取
        }
        return new String(buffer);
    }

    public static void main(String[] args) {
        String[] str16={"-80", "-1", "+7f", "3e8"};                 //整数十六进制原码
        for(int i= 0; i< str16.length; i++)
        {
            int value= MyInteger.parseInt(str16[i], 16);
            System.out.println(value + ",0x"+ MyInteger.toHexString(value) +";");
        }
    }
}
