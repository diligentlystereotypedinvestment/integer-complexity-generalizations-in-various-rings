
public class Gauss
{
    private int a;
    private int b;

    // s.t. n = a + bi

    public Gauss(int A, int B)
    {
        a = A;
        b = B;
    }

    public Gauss(int A) // for our regular good old fashioned integers
    {
        a = A;
        b = 0;
    }

    //getters
    public int getA()
    {
        return a;
    }

    public int getB()
    {
        return b;
    }

    //operations

    public static Gauss add(Gauss x, Gauss y)
    {
        return new Gauss(x.getA() + y.getA(), x.getB() + y.getB());
    }

    public static Gauss multiply(Gauss x, Gauss y)
    {
        int A = x.getA() * y.getA() - x.getB() * y.getB();
        int B = x.getA() * y.getB() + x.getB() * y.getA();

        return new Gauss(A, B);
    }

    public boolean equals(Gauss x)
    {
        boolean r = (a == x.getA()) && (b == x.getB());
        return r;
    }
    
    //norm
    public int norm(){
        return a * a + b * b;
    }

    // toString method
    public String toString()
    {
        if (b == 0)
        {
            return "" + a;
        }

        if (a == 0)
        {
            if (b == 1)
            {
                return "i";
            }
            if (b == -1)
            {
                return "-i";
            }
            return "" + b + "i";
        }
        
        if (b == 1)
        {
            return "" + a + " + " + "i";
        }
        
        if (b == -1)
        {
            return "" + a + " - " + "i";
        }

        if (b < 0)
        {
            return "" + a + " - " + Math.abs(b) + "i";
        }
        
        return "" + a + " + " + b + "i";
    }
}

