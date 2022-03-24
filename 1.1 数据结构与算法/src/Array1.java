import java.util.Arrays;

//例1.4 随机数序列
public class Array1 {

    public static void print(Object[] values)
    {
        for(Object obj : values)
            System.out.print(obj == null?"null " : " "+obj.toString());
        System.out.println();
    }
    //生成n个随机数（可重复），范围是0~size-1，返回整数对象数组
    public static Integer[] random1(int n, int size)
    {
        Integer[] values = new Integer[n];
        for (int i= 0; i< values.length; i++)       //遍历数组，访问每个元素仅一次
            //java.lang.Math.random()方法生成一个0~1之间的double类型的随机数
            values[i] = new Integer((int)(Math.random()*size));
        return values;                              //返回数组引用
    }
    // 思考题1-12  输出包含“{“，“,”，“}”
    public static void printBracket(Object[] values)
    {
        System.out.print("{");
        if(values.length > 0)
            System.out.print(values[0].toString());
        for(int i= 1;i< values.length;  i++)
            //obj.toString()运行时多态
            System.out.print("," +(values[i] == null?"null ": values[i].toString()));
        System.out.print("} ");
        System.out.println();
    }
    //思考题1-12(未写)  生成n个随机数， 不包含0，返回整数数组
    public static Integer[] random2(int n, int size)
    {
        Integer[] values = new Integer[n];
        return values;
    }

    public static void main(String[] args) {
        int n= 10, size= 100;
        Integer[] values = Array1.random1(n, size);      //调用类的静态方法，使用类名调用
        System.out.print(n + "个元素0~" + size + "之间的随机数序列：");
        //Array1.print(values);
        Array1.printBracket(values);
    }
}
