import java.util.ArrayList; // import the ArrayList class

public class IntegerExpression{

    private int in;
    private ArrayList<Integer> e; //will be used to represent expression

    public IntegerExpression(int I, ArrayList<Integer> E)
    {
        in = I;
        e = E;
    }

    public IntegerExpression(IntegerExpression a, IntegerExpression b, int n) //concatonates two GaussExpressions
    {
        /* Rules:
         * positive numbers represent addition
         * negative numbers represent multiplication
         * magnitude represents the order in which operations need to be conductded
         * 
         * Examples:
         * i = []
         * (1 + 1) = [1]
         * (1 * 1) = [-1]
         * (1 * 1) + i = [-1, 2] 
         * ((1 * 1) + 1) * (1 + 1)) = [-2, 1, 4, 1]
         * note that the biggest number has a magnitude equal to the size of the array
         * 
         * note that if the biggest number is at the beginning or the end of the array, 
         * that indicates that a single i is being added or multiplied
         * 
         * 
         * n = 1 or -1
         * if n = 1, add
         * if n = -1, multiply
         */

        //combine the Gaussian integers with the appropriate operation
        if (n == 1)
            in = a.getI()+b.getI();
        if (n == -1)
            in = a.getI()*b.getI();

        //create e:
        e = new ArrayList<Integer>();
        //add the first number's elements to e
        for (int i = 0; i < a.getE().size(); i++)
        {
            e.add(a.getE().get(i));
        }

        //add the appropriate operation
        e.add(n*(a.getE().size() + b.getE().size() + 1)); // the magnitude of the number added is the total length of the array

        //add the second number's elements to e
        for (int i = 0; i < b.getE().size(); i++)
        {
            e.add(b.getE().get(i));
        }
    }

    //getters
    public int getI()
    {
        return in;
    }

    public ArrayList<Integer> getE()
    {
        return e;
    }

    //toString
    public String toString()
    {
        if (e.size() == 0)
            return "1 = 1";
        return "" + in + " = " + e + "\t = \t" + StringExpression(e);
        //return "" + i + " = " + e;
    }

    private String StringExpression(ArrayList<Integer> a)
    {
        if (a.size() == 1)
        {
            if (a.get(0) == 1)
                return "1 + 1";
            if (a.get(0) == -1)
                return "1 * 1";
        }

        int n = 0;
        for (int i = 0; i < a.size(); i++)
            if (Math.abs(a.get(i)) == a.size())
                n = i;

        if (n == 0)
        {
            ArrayList<Integer> b = new ArrayList<Integer>();
            for (int i = 1; i < a.size(); i++)
                b.add(a.get(i));
            if (a.get(n) > 0)
                return "1 + " + StringExpression(b);
            if (a.get(n) < 0)
                return "1 * (" + StringExpression(b) + ")";
        }

        if (n == 0)
        {
            ArrayList<Integer> c = new ArrayList<Integer>();
            for (int i = 0; i < a.size() - 1; i++)
                c.add(a.get(i));
            if (a.get(n) > 0)
                return StringExpression(c) + "1";
            if (a.get(n) < 0)
                return "(" + StringExpression(c) + ") * 1";
        }

        ArrayList<Integer> d = new ArrayList<Integer>();
        ArrayList<Integer> e = new ArrayList<Integer>();
        for (int i = 0; i < n; i++)
            d.add(a.get(i));
        for (int i = n + 1; i < a.size(); i++)
            e.add(a.get(i)); 
        if (a.get(n) > 0)
            return StringExpression(d) + " + " + StringExpression(e);
        if (a.get(n) < 0)
            return "(" + StringExpression(d) + ")(" + StringExpression(e) + ")";
            
        return "error";
    }
}