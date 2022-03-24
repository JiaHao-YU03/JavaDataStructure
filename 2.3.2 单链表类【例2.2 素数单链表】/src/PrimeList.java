//素数线性表(升序)，使用单链表存储，要求尾插入O（1）
public class PrimeList {

    private int range;                                      //素数范围上限
    private SinglyList<Integer> list;                       //用单链表 存储素数线性表
    //构造方法，存储2~range中所有素数
    public PrimeList(int range)
    {
        if(range <= 1)                                      //抛出无效参数异常
            throw new java.lang.IllegalArgumentException("range="+ range);
        this.range= range;
        this.list= new SinglyList<Integer>();               //创建空单链表
        this.list.insert(2);                             //添加已知最小素数2
        Node<Integer> rear= this.list.head.next;            //添加尾指针
        for(int key= 3; key<= range; key+= 2)               //测试奇数，不需测试其他偶数
        {
            if(this.isPrime(key))                           //若key是素数，则尾插入
            {
                rear.next= new Node<Integer>(key, null);
                rear= rear.next;
            }
        }
    }
    //判断key是否是素数，遍历this.list单链表,用已有的素数除余测试key。
    public boolean isPrime(int key)
    {
        if(key <= 1)                                        //无效参数异常
            throw new java.lang.IllegalArgumentException("key="+ key);
        int sqrt= (int)Math.sqrt(key);                      //Math.sqrt返回key的平方根值
        for(Node<Integer> p=this.list.head.next; p!= null && p.data<= sqrt; p= p.next)
            if(key % p.data == 0)
                return false;                               //除余素数为零，则key不是素数
        return true;
    }
    public String toString()
    {
        return "2~"+ range +"素数集合"+ list.toString() +", 共"+ list.size() +"个元素。";
    }

    public static void main(String[] args) {
        System.out.println(new PrimeList(50).toString());
    }
}
