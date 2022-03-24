//广义表操作类
//注意：只能声明i是全局变量。
//如果声明i是局部变量，递归参数传递子串，问题在于，遇到')'返回，无法带回改变了的i值。
public class GenLists {

    private static int i= 0;                                            //静态成员变量
    //返回以gliststr表示创建的广义表，gliststr语法正确，不是null、空串""
    public static GenList<String> create(String gliststr)
    {
        i= 0;
        return createsub(gliststr, null);                           //构造树，没有共享子表
    }

    //返回从gliststr[i]开始的子串创建的子广义表，data指定表名。原子和表名都是字符串，递归方法。
    //由于i 从0开始线性递增，所有递归方法共用一个i，所以只能声明i是成员变量，不能是局部变量
    //不能创建共享子表。
    private static GenList<String> createsub(String gliststr, String data)
    {
        //表或子表的头结点构造
        if(data == null)
        {
            i= gliststr.indexOf('(');                                  //返回指定字符在串中位置
            data= gliststr.substring(0, i);                             //'('前的字符串是表名
        }
        GenList<String> genlist= new GenList<String>(data);            //构造空表，data指定表名
        GenNode<String> p= genlist.head;                               //指向头结点
        i++;                                                           //跳过'('
        //表身的构造
        while(i < gliststr.length())
        {
            switch(gliststr.charAt(i))
            {
                case ',':  i++;  break;                                 //遇到‘,’,跳过，继续循环找下一个元素字符
                case ')':  i++;  return genlist;                        //遇到‘)’,直接返回，因为已经将一个子表或表构造完。
                //字符
                default :
                {
                    //以下循环从第i个字符开始寻找原子/表名子串，以"(,)"之一作为分割符
                    int end= i;
                    while(end < gliststr.length()  &&  "(,)".indexOf(gliststr.charAt(end)) ==  -1)
                        end++;
                    data= gliststr.substring(i, end);                  //i～end-1的子串是原子/表名
                    i= end;                                              //改变i到下个分割符位置

                    //下句创建原子/子表结点；子表结点有表名，无表名时为空串
                    p.next= new GenNode<String>(data, null, null);
                    p= p.next;
                    if(i < gliststr.length() && gliststr.charAt(i) == '(')
                        p.child= createsub(gliststr, data);//创建子表，递归调用
                }
            }
        }
        return genlist;
    }

    public static void main(String args[])
    {
        //【例5.3】 广义表的基本操作，包括构造、遍历、插入、删除。
        String gliststr="G(d,L(a,b),T(c,L(a,b)))";                          //带表名的广义表表示
        GenList<String> graph= GenLists.create(gliststr);                   //构造广义表，没有共享子表
        System.out.println("构造，\t\t"+ graph.toString() +"，size="+ graph.size() +"，depth="+ graph.depth());

        //以下执行插入、删除操作，说明是否共享子表
        GenList<String> list= graph.get(1).child;                           //返回子表L(a,b)
        graph.insert(0, list);                                            //G头插入子表list，list成为共享子表
        System.out.println(graph.getName() +"头插入"+ list.toString() +"，\t"+ graph.toString());

        GenNode<String> p= list.remove(0);                                //共享子表list头删除原子
        System.out.println(list.getName() +"头删除"+ (p != null ? list.toString(p) : "") +"，\t"+ graph.toString());

        p= list.insert(0, "e");                                         //头插入原子
        System.out.println(list.getName() +"头插入"+ (p != null ? list.toString(p) : "") +"，\t"+ graph.toString());
    }
}
