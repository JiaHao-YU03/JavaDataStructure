//声明顺序表类
public class SeqList<T> {           //T表示数据元素的数据类型，默认继承Object

    protected int n;                                //顺序表元素个数（长度）
    protected Object[] element;                     //对象数组存储顺序表，object类保护成员
    public static final int MIN_CAPACITY= 16;       //常量，指定element数组容量的最小值
    public SeqList(int length)                  //构造空表，length指定数组容量，若length<MIN,则取最小值
    {
        if(length< MIN_CAPACITY)
            length= MIN_CAPACITY;
        this.element = new Object[length];                      //申请数组空间，元素为null
        this.n = 0;
    }
    public SeqList()                            //重载，创建默认容量的空表
    {
        this(MIN_CAPACITY);                     //调用本类已声明的指定参数列表的构造方法
    }
    public SeqList(T[] values)                  //顺序表由values数组提供元素，忽略其中空对象
    {
        //创建2倍的values数组容量的空表，若value==null，则抛出空对象异常
        this(values.length*2);
        for(int i= 0; i< values.length; i++)                    //复制非null的数组元素
            if(values[i] != null)
                this.element[this.n++] = values[i];             //对象引用赋值
    }
    //浅拷贝,复制对象
/*    public SeqList(SeqList<T> list)
    {
        this.n = list.n;                        //int 整数赋值，开辟新空间复制
        this.element = list.element;            //数组引用赋值、两个变量共用一个数组，不算真正的拷贝
    }*/
    //深拷贝。<? extends T>表示T及子类
    public SeqList(SeqList<? extends T> list)
    {
        this.copy(list);
    }
    public void copy(SeqList<? extends T> list)
    {
        this.element = new Object[list.element.length];         //重新申请一个数组，根类数组保护成员
        for(int i= 0; i< list.n; i++)                           //复制list所有元素
            this.element[i] = list.element[i];                  //对象引用赋值，没有创建新实例
        this.n = list.n;
    }
    public boolean isEmpty()                    //判断是否为空，若为空，则返回true
    {
        return this.n == 0;
    }
    public int size()                           //返回元素个数
    {
        return this.n;
    }
    public T get(int i)                         //若 0<=i<n,则返回第i个元素，否则返回null
    {
        if(i>= 0 && i< this.n)
            return (T)this.element;                             //返回数组元素应用的对象，传递对象引用
        //当i<=0||i>=this.element.length时，Java抛出 数组下标越界异常
        //  return this.element[i]
        return null;
        //else throw new java.lang.IndexOutOfBoundsException
    }

    //若 0<=i<n且 x!=null, 则设置第i个元素为x,否则抛出序号越界异常或空对象异常
    public void set(int i, T x)
    {
        if(x == null)
            throw new NullPointerException("x == null");                //抛出空对象异常
        if(i>= 0 && i< this.n)
            this.element[i] = x;                                        //对象引用赋值
        else
            throw new java.lang.IndexOutOfBoundsException(i + "");      //抛出序号越界异常
    }
    //返回所有元素的描述字符串，形式纬”(,)“，覆盖Object类的toString()方法顺序表遍历算法
    public String toString()
    {
        String str= this.getClass().getName()+ "(";           //返回类名
        if(this.n> 0)
            str += this.element[0].toString();               //执行T类的toString方法，运行时多态
        for(int i= 1; i< this.n; i++)
            str += "," + this.element[i].toString();
        return str + ")";                                   //空表返回（）
    }
  /*
  返回所有元素的描述字符串，次序从后向前，未写方法体
  public String toPreviousString()
   */
    //插入：插入x为第i个元素，x!=null，返回插入元素序号。对i容错，若i<0,则头插入；若i>长度，则尾插入。
    public int insert(int i, T x)
    {
        //空数据元素（对象）不入表（栈）
        if(x == null)
            return -1;
        if(i < 0)
            i = 0;                              //插入位置i 使容错,插入在最前（头插入）
        if(i > this.n)
            i =  this.n;                                        //插入最后（尾插入）
        Object[] source = this.element;                         //数组变量引用赋值，source也引用element数组
        if(this.n == element.length)                            //若原数组满，则扩充顺序表的数组容量
        {
            this.element = new Object[source.length];           //再申请一个容量更大的数组
            for(int j= 0; j< i; j++)                            //复制当前数组前i-1个元素
                this.element[j] = source[j];                    //复制数组元素，传递对象引用
        }
        for(int j= this.n -1; j>= i; j--)                       //从i开始到表尾的元素向后移动，次序从后向前
            this.element[j+1] = source[j];                      //复制数组元素，传递对象引用
        this.element[i] = x;
        this.n++;                               //操作做完，容量+1
        return i;                               //返回插入元素序号
    }
    public int insert(T x)                      //顺序表尾插入x元素，重载
    {
        return this.insert(this.n, x);          //调用 insert(i, x)方法
    }
    //删除：删除第i个元素，0<= i<n,返回被删除元素。若i越界，则返回null
    public T remove(int i)
    {
        if(i >= 0 && i < this.n)
        {
            T x= (T)this.element[i];                               //x中储存 被删除元素
            for(int j= i; j< this.n -1; j++)
                this.element[j] = this.element[j + 1];             //元素前一一个位置
            this.element[this.n - 1] = null;                       //设置数组元素对象为空，释放原引用实例
            this.n--;
            return x;                                              //返回x局部变量引用的对象，传递对象引用
        }
        return null;
    }
    public void clear()                         //删除所有元素
    {
        this.n = 0;                             //重新设置长度 为0，但未释放原有数组空间
    }
    //查找：在this引用的顺序表中，顺序查找 首个与key相等的元素，返回元素i；查找不成功，返回-1
    public int search(T key)
    {
        for(int i= 0; i< this.n; i++)
            if(key.equals(this.element[i]))
                return i;
        return -1;
    }
    //顺序查找并删除 首个与key相等的元素，返回 被删除元素；查找不成功，返回-1
    public T remove(T key)
    {
        return this.remove(this.search(key));                       //不删除
    }
    //比较：比较obj和this引用的顺序表是否相等，方法覆盖
    public boolean equals(Object obj)
    {
        if(this == obj)                             //引用统一实例，相等
            return true;
        if(obj instanceof SeqList<?>)               //若obj引用顺序表实例。<?>表示是所有父类
        {
            SeqList<T> list= (SeqList<T>)obj;       //声明list 也也引用obj所引用的实例,根类强制类型转换
            if(this.n == list.n)                                    //比较两者长度是否相等
            {
                for(int i= 0; i< this.n; i++)                       //再比较两个顺序表的所有元素是否相等
                    //一旦发现有两个对应元素不相等，则可确定两个顺序表不相等。equal(Object)运行时多态
                    if(!(this.element[i].equals(list.element[i])))
                        return false;
                    return true;
            }
        }
        return false;
    }
    public String toPreviousString()
    {
        String str="";
        for(int i= 0; i< this.n; i++)
            str += this.element[this.n -i-1].toString();
        return str;
    }

    public static void main(String[] args) {

        String[] values = {"A", "B", "C", "D", "E"};
        SeqList<String> lista = new SeqList<String>(values);
//        SeqList<Integer> list1 = new SeqList<Integer>();
//        System.out.println(list1.toString());
//
//        Integer key= 1;
//        Integer result= list1.remove(key);              //单步调试
//        System.out.println("list1 删除元素" + result + ",list=" + list1.toString());
//
//        int i= key.intValue();
//        result= list1.remove(key);              //单步调试
//        System.out.println("list1 删除第" + i +"个元素"+ result + ",list=" + list1.toString());
//
//        SeqList<String> listb= new SeqList<String>(lista);
//        lista.remove(lista.size() -1);        //lista尾删除，会影响listb
//        listb.toString();                       //运行错，抛出空对象异常

        System.out.println(lista.toPreviousString());


    }
}
