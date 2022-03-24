import java.util.List;

//2-1 算法描述：求解约瑟夫环问题，n个人，从start开始计数，每次数到distance的人出环
public class Josephus {

    public Josephus(int n, int start, int distance)
    {
        List list = null;  //创建一个线性表对象list(空),插入从‘A‘开始的n个元素；
        for(int i= 0;i< n;i++)
            list.add(i, 'A'+1);
        while(n> 1)
        {
            while(true)
            {
                start++;
                if(start == distance) {
                    list.remove(start);
                    for (; start < n; start++) {
                        list.add(start + 1);
                        list.remove(start + 1);
                    }
                }
                n--;
            }
        }
        System.out.println(list.toString());
    }

}
