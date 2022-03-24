import java.sql.SQLOutput;

//【例4.3】使用队列求解素数环问题
/*
    队列que:存储自然数序列
    素数表prime:只是为了用isPrime()判断 last+key 是否为素数
    单链表ring:存储素数环数据元素
 */
public class PrimeRing {

    //求解1-n素数环问题，n= values.length, value[]提供1~n初始序列
    public PrimeRing(Integer[] values)
    {
        Queue<Integer> que= new SeqQueue<Integer>(values.length +1);            //创建空队列，顺序或链式
        for(int i= 0; i< values.length; i++)                                         //values[]元素全部入队
            que.add(values[i]);
        System.out.println("初始队列："+ que.toString());

        PrimeList prime= new PrimeList(values.length);                              //创建素数单链表
        System.out.println(prime.toString());
        //使用单链表储存素数环。单链表、循环单链表都可以
        SinglyList<Integer> ring= new SinglyList<Integer>();
        //last= que.roll();也可;; 方法 'intValue'可能产生'NullPointerException'
        int last= que.poll().intValue();                                            //现将数1出队
        ring.insert(last);                                                          //素数环插入

        while(!que.isEmpty())
        {
            int key= que.poll().intValue();                     //出队一个元素
            if(prime.isPrime(last + key))                   //判断last+key是素数
            {
                ring.insert(key);
                last= key;
            }
            else
                que.add(key);                                   //否则，key再次入队
        }
        System.out.println("1~ "+ values.length +"素数环： "+ ring.toString());
    }

    public static void main(String[] args) {
        Integer[] values= {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        new PrimeRing(values);
    }
}
