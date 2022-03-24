import java.util.Scanner;

public class ex1 {

//    public static void most(String scan)
//    {
//        int[] tong= new int[1000];
//
//        for(int i= 0; i< scan.length(); i++)
//            for(int j= 0; j< 25; j++)
//                if(scan.charAt(i) == (char)('a'+j))
//                    tong[j]++;
//
//                char maxch= 'a';
//                int max= 0;
//        for(int i= 0; i< 26; i++)
//            if(tong[i] != 0)
//                if(tong[i] > max) {
//                    max = tong[i];
//                    maxch = (char) (i +'a');
//                }
//        System.out.println(maxch);
//        System.out.println(max);
//    }

    public static void percent(double[] m1, int m)
    {


        double j= 0, k= 0;
        for(int i= 0; i< m; i++)
        {
            if(m1[i] >= 60)
            {
                if(m1[i] >= 85)
                    j++;
                k++;
            }
        }



        System.out.println(String.format("%d%%", Math.round(k*100 / m)));

        System.out.println(String.format("%d%%", Math.round(j*100 / m)));


    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

//        //在此输入您的代码...
        //考试人数
        int m = scan.nextInt();
        double[] m1= new double[m];
        scan.nextLine();

        for(int i= 0; i< m; i++)
            m1[i]= scan.nextInt();

        percent(m1, m);
//        System.out.println(String.valueOf(m1).toString());
        scan.close();


    }

}
