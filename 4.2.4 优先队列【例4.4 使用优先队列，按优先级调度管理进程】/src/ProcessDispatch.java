//进程调度管理类，使用优先队列，按优先级排队
public class ProcessDispatch
{
    public static void main(String args[])
    {
        Process[] process= {new Process("A", 4), new Process("B"), new Process("C"),
                            new Process("D", 1), new Process("E", 10),
                            new Process("F", 4)};
        //优先队列按优先级降序排列
        PriorityQueue<Process> que= new PriorityQueue<Process>(process, false);
        System.out.println("出队元素： ");
        while(!que.isEmpty())
            System.out.println(que.poll().toString() +" ");                 //元素出队
        System.out.println();
    }
}
