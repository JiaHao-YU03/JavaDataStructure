//【例4.2】算术表达式求值，使用栈。
//声明Operators运算符集合类，包括算术和位运算符，约定每个运算符的优先级
//实现Comparator<T>比较器接口，提供compare(oper1,oper2)方法比较运算符的优先级大小
//分别使用顺序表、数组 储存运算符集合及其优先级，通过下标关联
public class Operators implements java.util.Comparator<String>{

    //运算符集合
    private String[] operator= {"*", "/", "%", "+", "-", "&", "^", "|"};
    //每个运算符的优先级，operator中相同下标，值小的优先级高
    private int[] priority= {3, 3, 3, 4, 4, 8, 9, 10};
    //使用顺序表存储运算符集合，调用查找算法
    private SeqList<String> operlist;

    public Operators()
    {
        this.operlist= new SeqList<String>(this.operator);
    }
    //比较oper1,oper2运算符的优先级大小
    public int compare(String oper1, String oper2)
    {
        int i= operlist.search(oper1), j= operlist.search(oper2);
        return this.priority[i] - this.priority[i];
    }
    //根据运算符 进行相应的计算操作
    public int operate(int x, int y, String oper)
    {
        int value= 0;
        switch(oper)
        {
            case "+": value= x + y; break;
            case "-": value= x - y; break;
            case "*": value= x * y; break;
            case "/": value= x / y; break;                      //整除，若除数为0，则抛出算术异常
            case "%": value= x % y; break;                      //取余，若除数为0，则抛出算术异常
            case "&": value= x & y; break;                      //位与
            case "^": value= x ^ y; break;                      //位异或
            case "|": value= x | y;                             //位或
        }
        return value;
    }
}
