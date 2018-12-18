package sort;

//
//Java不使用Math.sqrt方法实现的求平方根
public class SquareRoot {
    //采用二分法求某个数的平方根
    public static double sqrt(double t,Double percise){
        double low=0;
        double high=t;
        double middle=0;
        double squre=0;
        double prec = percise!=null ? percise:1e-7;//最终结果的小数范围
        if(t<0){
            return 0;
        }
        while (high-low>prec){
            middle=(low+high)/2;
            squre=middle* middle;
            if(squre>t){
                high=middle;
            }else {
                low=middle;
            }
        }
        return (low+high)/2;
    }
    //牛顿迭代法
    public static double sqrt1(double t,Double percise){
        double x0=t;
        double x1=0;
        double differ=0;

        double prec = percise!=null ? percise:1e-7;//最终结果的小数范围
        while (true){
            x1=(x0*x0+t)/(2*x0);
            differ=x1*x1-t;
            if(differ<=prec&&differ>=-prec){
                return x1;
            }
            x0=x1;
        }
    }
    //牛顿迭代法优化
    public static float sqrt2(float m) {
        if ( m == 0 ) {
            return 0;
        }

        float i = 0;
        float x1, x2 = 0;
        while ( ( i * i ) <= m ) {
            i += 0.1;
        }
        x1 = i;
        for ( int j = 0; j < 10; j++) {
            x2 = m;
            x2 /= x1;
            x2 += x1;
            x2 /= 2;
            x1 = x2;
        }
        return x2;
    }

    public static void main(String[] args) {
        double result=sqrt(7,null);
        double result1=sqrt1(7,null);
        double result2=sqrt1(7,null);

        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);
    }
}
