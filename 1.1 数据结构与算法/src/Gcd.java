//例1.3 求两个整数的最大公约数（greatest common divisor）
//(1)质因数分解法，未写
//(2)更相减损法+辗转相除法
public class Gcd {
    public static int gcd(int x,int y)
    {
        while(y != 0)
        {
            int temp = x % y;
            x = y;
            y = temp;
        }
        return x;
    }
    /*采用递归算法
    public static int recGcd(int x,int y)
    {
        if(y == 0)
        return x;
        if(x < 0)
        return recGcd(-x, y)

     */
//测试
    public static void main(String[] args) {
        int x= 91,y= 49;
        int c= 26460,d= 12375;
        System.out.println(gcd(x, y));
        System.out.println(gcd(c, d));
    }
}
