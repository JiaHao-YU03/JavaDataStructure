//【例4.1】使用栈模拟语法检查中的括号等匹配问题
public class Match
{
    //检查target字符串中left、right表示的成份是否左右匹配，返回匹配结果字符串
    public static String check(String target, String left, String right)
    {
//        Stack<String> stack= new SeqStack<String>();
        //声明接口对象stack，引用实现Stack<T>接口的顺序栈类的实例，创建空栈
        Stack<String> stack= new LinkedStack<String>();
        int i= 0;
        char ch= ' ';
        while(i < target.length())
        {
            //遍历，从target串第i个字符开始，查找与left或right首字符相等的字符序号
            while(i < target.length() && (ch=target.charAt(i)) != left.charAt(0) && ch != right.charAt(0))
                i++;
            if(target.indexOf(left, i) == i)            //若与left匹配，入栈
            {
                stack.push(left);
                i += left.length();
            }
            else if(target.startsWith(right, i))        //若与right匹配，出栈，检查是否匹配，之后i再加上一个字符
            {
                //1.stack为空，无法出栈，2.stack此时出栈元素不等于left，手动输出错误
                if (stack.isEmpty() || !stack.pop().equals(left))
                    return "语法错误，i="+ i +"，多余"+ right;
                i += right.length();
            }
        }
        return (stack.isEmpty()) ? "匹配" : "语法错误，i="+ i +"，缺少"+ right;
    }

    public static void main(String args[])
    {
        String target= " ( (1+2)*3+4) (", left= "(", right= ")";
        System.out.println("\""+ target +"\"，"+ Match.check(target, left, right));
        //或者target="if () if ()  else else else"; left= "if"; right= "else";
//        System.out.println("\""+ target +"\"，"+Match.check(target, left, right));
    }
}