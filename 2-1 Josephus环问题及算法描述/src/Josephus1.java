//2-1 使用顺序表求解
public class Josephus1 {
    //n个人，n>0;从start开始计数，0<=start<n;每次数到distance的人出环，0<distance<n
    public Josephus1(int n, int start ,int distance)
    {
        //参数无效时，抛出无效参数异常
        if(n<= 0 || start< 0 || start>= n || distance<= 0 || distance>= n)
            throw new IllegalArgumentException("N="+ n +", start="+ start +",distance="+ distance);
        System.out.print("Josephus("+ n +","+ start +","+ distance +"),");
        //创建顺序表实例，元素类型是字符串，构造方法参数指定顺序表容量，省略时取默认值
        SeqList<String> list= new SeqList<String>(n);
        for(int i= 0; i< n; i++)                                     //顺序表尾插入n个元素
            list.insert((char)('A'+ i) +"");                      //从A开始代表人名
        System.out.println(list.toString());                        //输出顺序表
        while (n > 1)                                               //循环，每次计算删除一个元素
        {
            start= (start + distance -1) % n;                       //按环形方式技术
            //输出 删除的start位置对象和顺序表中剩余元素。
            System.out.println("删除"+ list.remove(start).toString() +", "+ list.toString());
            n--;                                            //一轮次数-1
        }
        System.out.println("被赦免者是"+ list.get(0).toString());    //get(0)获得元素
        //此步出错
    }

    public static void main(String[] args) {
        new Josephus1(5, 1, 3);
    }
}
