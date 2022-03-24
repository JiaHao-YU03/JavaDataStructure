public class KMP
{
    private static int count=0;                  //记载比较次数
    private static int[] next;                   //模式串pattern改进的next数组
    private static int[] nextk;                  //模式串pattern未改进的next数组

    //返回target目标串中首个与pattern模式串匹配的子串序号，匹配失败时返回-1
    public static int indexOf(String target, String pattern)
    {
        return indexOf(target, pattern, 0);
    }

    //返回target从begin开始首个与pattern匹配的子串序号，匹配失败时返回-1。
    //0≤begin<target.length()。对begin容错，若begin<0，从0开始；若begin序号越界，则查找不成功。
    ////若target、pattern为null，Java抛出空对象异常。
    public static int indexOf(String target, String pattern, int begin)
    {
        int n=target.length(), m=pattern.length();
        if(begin<0)                              //对begin容错，若begin<0，从0开始
            begin = 0;
        if(n==0 || n<m || begin>=n)              //若目标串空、较短或begin越界，不需比较
            return -1;

        int count=0;                             //记载比较次数
        nextk = getNextk(pattern);               //返回pattern模式串的next数组，稍后给出
        print(pattern);
        System.out.print("nextk[]: ");  print(nextk);
        next = getNext(pattern);                 //返回模式串pattern改进的next数组，稍后给出
        System.out.print("next[]:  ");  print(next);

        int i=begin, j=0;                        //i、j分别为目标串、模式串比较字符下标
        while(i<n && j<m)
        {
            if(j!=-1) count++;
            if(j==-1 || target.charAt(i)==pattern.charAt(j))//若this两字符相等，则继续比较后续字符
            {
                if(j!=-1)
                    System.out.print("t"+i+"=p"+j+"，");
                i++;
                j++;
            }
            else                                 //否则，下次匹配，目标串下标i不回溯
            {
                System.out.println("t"+i+"!=p"+j+"，next["+j+"]="+next[j]);
                j=next[j];                       //模式串下标j退回到下次比较字符序号
                if(n-i+1<m-j+1)                  //若目标串剩余子串的长度不够，不再比较   //比第3版增加此句
                    break;
            }
        }
        System.out.println("\tKMP.count="+count);
        return  j==m ? i-m : -1;                 //若匹配成功，返回匹配子串序号；否则返回-1
    }

    private static int[] getNextk(String pattern)//返回模式串pattern的next数组
    {
        int j=0, k=-1, next[]=new int[pattern.length()];
        next[0]=-1;
        while(j<pattern.length()-1)
        {
            if(k==-1 || pattern.charAt(j)==pattern.charAt(k))
            {
                j++;
                k++;
                next[j]=k;                       //有待改进
            }
            else
                k=next[k];
        }
        return next;
    }

    private static int[] getNext(String pattern) //返回模式串pattern改进的next数组
    {
        int j=0, k=-1, next[]=new int[pattern.length()];
        next[0]=-1;
        while(j<pattern.length()-1)
        {
            if(k==-1 || pattern.charAt(j)==pattern.charAt(k))
            {
                j++;
                k++;
                if(pattern.charAt(j)==pattern.charAt(k))  //改进之处
                    next[j]=next[k];
                else
                    next[j]=k;
            }
            else
                k=next[k];
        }
        return next;
    }

    private static void print(int[] next)        //输出next[]数组
    {
        for(int i=0; i<next.length; i++)
            System.out.print(String.format("%3d", next[i]));
        System.out.println();
    }
    private static void print(String pattern)
    {
        System.out.print("pattern: ");
        for(int i=0; i<pattern.length(); i++)
            System.out.print("  "+pattern.charAt(i));
        System.out.println();
    }

    public static void main(String args[])
    {
// 	      String target="abcabb", pattern="abb";      //图3.15(a) 目标串不回溯
//        String target="aacabb", pattern="aab";      //图3.15(b) 目标串不回溯
//        String target="abcdabcabbabcabc", pattern="abcabc"; //图3.16～图3.18
//        String target="abcabdabcabcaa", pattern="abcabdabcabcaa";  //表3-2，表3-4

//        String target="aababcd", pattern="abcd";    //图3.12，BF用例
//        String target="aaaaa", pattern="aab";       //最坏情况，图3.13(b)BF；图3.19KMP

        //测试
        String target="aabaaa", pattern="aaa";       //??最坏情况，图3.13(b)BF；图3.19KMP

        //习题解答
//        String target="aaabaaaba", pattern="aaaa";  //习3-9(2)BF，习图3.5～习图3.6
//          String target="abcababcabababcababc", pattern="ababcababc"; //思考题3-6，习题解答3-10(6)，习图3.7

        System.out.println("KMP.indexOf(\""+target+"\", \""+pattern+"\")="+KMP.indexOf(target, pattern));
    }
}