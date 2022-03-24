//多项式类
public class Polynomial {

    private PolySinglyList<TermX> listx;


    public Polynomial()
    {
        this(true);
    }
    public Polynomial(boolean asc)
    {
        this.listx= new PolySinglyList<>(asc);
    }
    public Polynomial(TermX[] terms, Boolean asc)
    {
        this.listx= new PolySinglyList<>(terms, asc);
    }
    //字符串构造多项式，分解成项
    public Polynomial(String str)
    {
        this();
        if(str == null)
            return;

        Node<TermX> front= this.listx.head;
        int begin= 0, end= 0;
        while(begin < str.length() && end < str.length())
        {
            //从end位置开始找符号
            //不是从0位开始找，跳过第一位符号位
            int i= str.indexOf('+', end+1);
            if(i == -1)
                i= str.length();
            int j= str.indexOf('-', end+1);
            if(j == -1)
                j= str.length();
            //end记录较小的位置的下一个符号
            if(i < j)
                end= i;
            else
                end= j;

            front.next= new Node<TermX>(new TermX(str.substring(begin, end)), null);
            front= front.next;
            begin= end;
        }
    }

    public Polynomial(Polynomial poly)
    {
        this(poly.listx.asc);
        Node<TermX> rear= this.listx.head;
        for(Node<TermX> p= poly.listx.head.next; p != null; p= p.next)
        {
            rear= new Node<TermX>(new TermX(p.data), null);
            rear= rear.next;
        }
    }
    public String toString()
    {
        String asc= this.listx.asc ? "升序" : "降序";
        String s= "多项式为"+ asc + ",得 ";
        for(Node<TermX> p= this.listx.head.next; p != null ; p= p.next)
        {
//            int i= p.data.toString().indexOf("0");
//            if(i != -1)
                s += p.data.toString();
//            else
        }
        return s;
    }
    //比较两个多项式相等object
//    public boolean equals(Object obj)
//    {
//        if(this == obj)
//            return  true;
//        if(obj instanceof Polynomial)
//            return this.list.equals(((Polynomial) obj).list);
//        return false;
//    }
    //比较两个多项式相等polynomial
//    public boolean equals(Polynomial poly)
//    {
//        if(this == poly)
//            return true;
//        return this.list.equals(poly.list);
//    }
    public boolean equals(Polynomial poly)
    {
        if(this == poly)
            return true;
        Node<TermX> p= this.listx.head.next;
        Node<TermX> q= poly.listx.head.next;
        while(p != null && q != null && p.data.equals(q.data))
        {
            p= p.next;
            q= q.next;
        }
        return q == null && p == null;
    }
    //
    public PolySinglyList<TermX> multi(PolySinglyList<TermX> list) {              //深拷贝，不改变list的值，不改变this的值，返回一个新的多项式单链表
        PolySinglyList<TermX> poly = new PolySinglyList<TermX>(this.listx.asc);                      //构造一个结果单链表，顺序同this
        Node<TermX> p = (Node<TermX>) this.listx.head.next;
        while(p!=null){
            Node<TermX> q =list.head.next;
            while(q!=null){
                poly.insert(new TermX(p.data.coef*q.data.coef,p.data.xexp+q.data.xexp));
                q = q.next;
            }
            p = p.next;
        }
        return poly;
    }
    public static void main(String[] args) {

//        PolynomialXY apoly= new PolynomialXY("x^2-2xy+2y^2");
//        System.out.println("A= "+ apoly.toString());
//        PolynomialXY bpoly = new PolynomialXY("x+y");//默认升序
////      PolynomialXY bpoly= new PolynomialXY("2-2x+xy^2-9x^3y+2x^6y^2");
//        System.out.println("\nB="+bpoly.toString());
//
//        PolynomialXY cpoly = apoly.multi(bpoly);
//        System.out.println("C=A*B，C="+cpoly.toString());

        Polynomial apoly= new Polynomial("+1-x+2x^2-9x^3+2x^6");
        System.out.println("A= "+ apoly.toString());
//        Polynomial bpoly = new Polynomial("-1+x-x^2+10x^4-3x^5+5x^7+9x^8");//默认升序
        Polynomial bpoly= new Polynomial("+1-x+2x^2-9x^3+2x^6");
        System.out.println("\nB="+bpoly.toString());
//
//        Polynomial cpoly = new Polynomial();
//        cpoly.listx= apoly.listx.union(bpoly.listx);
//        System.out.println("C=A+B，C="+cpoly.toString());
//
        System.out.println((apoly.equals(bpoly)) ? "相同" : "不相同");
    }
}
