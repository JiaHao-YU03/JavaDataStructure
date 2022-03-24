import javax.swing.*;

//单链表求解Josephus环问题
public class Josephus2 {

    //n个人，n>0;从start开始计数，0<=start<n;每次数到distance的人出环，0<distance<n
    public Josephus2(int n, int start, int distance)
    {
        //参数无效时，抛出无效参数异常
        if(n<= 0 || start< 0 || start>=n || distance<= 0 || distance>= n)
            throw new IllegalArgumentException("n="+ n +",start="+ start +",distance="+ distance);

        SinglyList<String> list= new SinglyList<String>();                  //构造空单链表
        for(int i= n-1; i>= 0; i--)
            //循环头插入从E~A，一个接一个向后移
            list.insert(0, (char)('A'+ i) +"");
        System.out.println("Josephus("+ n +","+ start +","+ distance +"),"+ list.toString());//输出单链表

        //求解Josephus环,循环次数。与顺序表不同，不能计算下标。
        Node<String> front= list.head;                      //front指向头结点
        for(int i= 0; front!= null && i< start; i++)
            front= front.next;                              //循环front指向到start-1结点位置
        while(n > 1)
        {
            //循环寻找删除结点。 少数一个，使front指向待删除结点的前驱
            for(int i= 1; i< distance; i++)
            {
                front= front.next;
                if(front == null)                           //实现循环计数。该条件不能写到for语句，会停止循环
                    front= list.head.next;                  //front指向超过了长度，更改指向到头结点
            }
            if(front.next == null)                          //此时front指向最后一个，则删除第0个结点
                front= list.head;
            System.out.print("删除"+ front.next.data.toString() +",");
            front.next= front.next.next;                    //删除front的后继结点，包括头删除，中间/尾删除
            n--;                                            //人数-1
            System.out.println(list.toString());
        }
        System.out.println("被赦免者"+ list.get(0).toString());
    }

    public static void main(String[] args) {
        new Josephus2(5,0,3);
    }
}
