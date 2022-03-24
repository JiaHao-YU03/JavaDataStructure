//【例4.4】使用优先队列，按优先度调度管理进程
//进程类，由“(进程类，优先级)”组成，按优先级比较大小
public class Process implements Comparable<Process>{

    private String name;                        //进程名
    private int priority;                       //优先级
    private static int MAX_PRIORITY= 10;        //优先级最大值，优先级范围为1~MAX_PRIORITY
    //构造进程，参数name、priority分别指定进程名和优先级超出范围时抛出无效参数异常
    public Process(String name, int priority)
    {
        this.name= name;
        if(priority >= 1 && priority <= MAX_PRIORITY)
            this.priority= priority;
        else
            throw new IllegalArgumentException("priority= "+ priority);
    }
    public Process(String name)
    {
        this(name, 5);
    }


    public String toString()
    {
        return "("+ this.name +","+ this.priority +")";
    }
    //进程按优先级比较大小
    public int compareTo(Process process)
    {
        return this.priority - process.priority;
    }
}
