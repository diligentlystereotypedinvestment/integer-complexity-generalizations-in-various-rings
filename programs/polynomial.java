import java.util.ArrayList; // import the ArrayList class
import java.util.Arrays;
import java.util.Collections;
import java.io.*;

public class polynomial // gives us complexity expressions
{
    private ArrayList<Integer> coefficients;

    public polynomial(ArrayList<Integer> c)
    {
        coefficients = c;
    }

    public polynomial() // creates the polynomial x
    {
        coefficients = new ArrayList<Integer>();
        coefficients.add(0);
        coefficients.add(1);
    }

    public ArrayList<Integer> getCoefficients()
    {
        return coefficients;
    }
    
    public int getSum()
    {
        int r = 0;
        for (int i = 0; i < coefficients.size(); i++)
        {
            r += coefficients.get(i);
        }
        
        return r;
    }

    public static polynomial addition(polynomial a, polynomial b)
    {
        if (b.getCoefficients().size() > a.getCoefficients().size()) // ensures that a is the larger degree polynomial
        {
            polynomial c = b;
            b = a;
            a = c;
        }

        ArrayList<Integer> bCoeff = b.getCoefficients();
        ArrayList<Integer> sum = new ArrayList<Integer>();

        for (int i = 0; i < bCoeff.size(); i++)
        {
            sum.add(a.getCoefficients().get(i) + bCoeff.get(i));
        }

        if (a.getCoefficients().size() != bCoeff.size())
        {
            for (int i = bCoeff.size(); i < a.getCoefficients().size(); i++)
            {
                sum.add(a.getCoefficients().get(i));
            }
        }

        return new polynomial(sum).scrapZeros();
    }

    public polynomial scrapZeros()
    {
        ArrayList<Integer> c = this.getCoefficients();
        for (int i = 0; i <= this.getCoefficients().size(); i++)
        {
            if (c.size() == 0)
                break;
            if (c.get(c.size() - 1) == 0)
                c.remove(c.size() - 1);
        }
        return new polynomial(c);
    }

    public static polynomial multiplication(polynomial a, polynomial b)
    {
        ArrayList<Integer> product = new ArrayList<Integer>();

        ArrayList<Integer> aCoeff = a.getCoefficients();
        ArrayList<Integer> bCoeff = b.getCoefficients();

        for (int i = 0; i < aCoeff.size() + bCoeff.size() - 1; i++)
        {
            product.add(0);
        }

        for (int i = 0; i < aCoeff.size(); i++)
        {
            for (int j = 0; j < bCoeff.size(); j++)
            {
                product.set(   (i+j),    product.get(i+j) + (aCoeff.get(i) * bCoeff.get(j))   );
            }
        }
        return new polynomial(product).scrapZeros();
    }

    public String toString()
    {

        String r = "";

        for (int i = 0; i < coefficients.size(); i++)
        {
            if (coefficients.get(i) != 0) // if the coefficient is not equal to 0
            {
                if (i == 0) //make it a constant if no x
                {
                    r += "" + coefficients.get(i);
                }
                else if (i == 1) // don't have an exponent for x
                {
                    if (coefficients.get(i) == 1) // dont have a leading term for 1
                        r += "x";
                    else
                        r += "" + coefficients.get(i) + "x";
                }
                else 
                {
                    if (coefficients.get(i) == 1)
                        r += "x^" + i;
                    else
                        r += "" + coefficients.get(i) + "x^" + i;
                }

                if (i < coefficients.size() - 1)
                {
                    r += " + ";
                }
            }
        }

        return r;
    }

}