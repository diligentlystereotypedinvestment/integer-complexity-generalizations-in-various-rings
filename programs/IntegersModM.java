//

import java.util.ArrayList; // import the ArrayList class
import java.util.Arrays;
import java.util.Collections;
import java.io.*;

public class IntegersModM{
    public static void main(String[] args)
    {
        
        /*
        ArrayList<Integer> primes = new ArrayList<Integer>(); //creates an array list of primes
        for (int i = 3; i <= 500; i++)
        {
            if (isPrime(i))
                primes.add(i);
        }
        */
       
        int m = 7;
        
        printChart(m);
        

        for (int i = 3; i <= 200; i++)
        {
            //efficiency(i);

            /*
            ArrayList<Integer> ma = maxes(m);

            System.out.println("" + prime + ":     " + ma );
            int range = ma.get(ma.size()-1) - ma.get(0);
            System.out.println("range ratio :     " + (double)
            range/getGenerators(m).size()*100 ); // gives a sort of concentration of maxes
            System.out.println();
             */

        }

    }

    public static int GCD(int a, int b)
    {
        //precondition: a >= b;

        if (a % b == 0)
        {
            return b;
        }

        return GCD(b, a % b);
    }

    public static int phi(int[] a)
    {
        if (a[0] == 4 || a[0] ==2)
        {
            return a[0]/2;
        }

        if (a[2] == 1 && a[3] == 0)
            return (a[1] - 1);
        return (int) Math.pow(a[1], a[2] - 1) * (a[1] - 1);
    }

    public static boolean isPrime(int p)
    {
        if (p == 1 || p == 4)
            return false;
        if (p == 2 || p == 3)
            return true;
        for (int i = 2; i <= (int)Math.pow(p, 0.5); i++)
        {
            if (p % i == 0)
                return false;
        }
        return true;
    }

    public static int findGenerator(int[] m) // finds the first generator
    {
        boolean isGenerator;
        for (int i = 2; i <= m[0]; i++) // these numbers will be tested as generators
        {
            isGenerator = true;
            for (int j = 2; j <= (int)Math.pow(m[0], 0.5); j++)
            {
                if (isPrime(j) && (phi(m) % j == 0)) // if j is prime and divides phi(m)
                {
                    if (power(i, phi(m)/j, m[0]) == 1) // then try raising the potential generator to phi(m)/j
                    {
                        isGenerator = false;
                        break;
                    }
                }
            }
            if (isGenerator)
                return i;
        }
        return -1;
    }

    public static ArrayList<Integer> getGenerators(int[] a)
    {
        int g = findGenerator(a);
        ArrayList<Integer> gens = new ArrayList<Integer>();

        for (int i = 1; i < phi(a); i++)
        {
            if (GCD(i, phi(a)) == 1)
                gens.add(power(g, i, a[0]));
        }

        return gens;
    }

    public static int power(int a, int b, int m)
    {
        int r = 1;
        for (int i = 1; i <= b; i++)
        {
            r = (r * a) % m;
        }
        return r;
    }

    public static int order(int u, int m)
    {
        if (u == 1)
        {
            return 1;
        }
        int a = u;
        for (int i = 2; i < m; i++)
        {
            a = (a*u) % m;
            if (a == 1)
                return i;
        }
        return -1;
    }

    public static void printChart(int m)
    {
        ArrayList<Integer> units = new ArrayList<Integer>();
        for (int i = 1; i < m; i++)
        {
            if (GCD(i, m) == 1)
                units.add(i);
        }
        

        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> cumulative = new ArrayList<Integer>();

        int toBeAdded;
        boolean add;

        int g; //(will take the role of a generator in question)

        for (int h = 0; h < units.size(); h++)
        {
            g = units.get(h);

            lists = new ArrayList<ArrayList<Integer>>();
            cumulative = new ArrayList<Integer>();
            for (int a = 0; a <= m; a++)
                lists.add(new ArrayList<Integer>());

            lists.get(1).add(g);
            cumulative.add(g);

            for (int i = 2; i < m; i++)
            {
                for (int j = 1; j <= i/2; j++)
                    for (int k = 0; k < lists.get(j).size(); k++)
                    {
                        for (int l = 0; l < lists.get(i-j).size(); l++)
                        {
                            toBeAdded = (lists.get(j).get(k) * lists.get(i-j).get(l)) % m;
                            //check if toBeAdded is in cumulative
                            add = true;
                            for (int n = 0; n < cumulative.size(); n++)
                            {
                                if (toBeAdded == cumulative.get(n))
                                    add = false;
                            }
                            if (add)
                            {
                                cumulative.add(toBeAdded);
                                lists.get(i).add(toBeAdded);
                            }

                            toBeAdded = (lists.get(j).get(k) + lists.get(i-j).get(l)) % m;
                            //check if toBeAdded is in cumulative
                            add = true;
                            for (int n = 0; n < cumulative.size(); n++)
                            {
                                if (toBeAdded == cumulative.get(n))
                                    add = false;
                            }
                            if (add)
                            {
                                cumulative.add(toBeAdded);
                                lists.get(i).add(toBeAdded);
                            }

                        }
                    }
            }

            System.out.println("generator = " + g + ":");
            for (int p = 1; p < lists.size(); p++)
            {
                System.out.println("complexity = " + p + ":   " + lists.get(p));
            }
            System.out.println();
            System.out.println();
        }
    }

    public static ArrayList<Integer> maxes(int m)
    {
        ArrayList<Integer> units = new ArrayList<Integer>();
        for (int i = 1; i < m; i++)
        {
            if (GCD(i, m) == 1)
                units.add(i);
        }

        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> cumulative = new ArrayList<Integer>();

        ArrayList<Integer> max = new ArrayList<Integer>();
        int theMax = -1; // the maximum complexity for each starting value

        int toBeAdded;
        boolean add;

        int g; //(will take the role of a generator in question)

        for (int h = 0; h < units.size(); h++)
        {
            g = units.get(h);

            lists = new ArrayList<ArrayList<Integer>>();
            cumulative = new ArrayList<Integer>();
            for (int a = 0; a <= m; a++)
                lists.add(new ArrayList<Integer>());

            lists.get(1).add(g);
            cumulative.add(g);

            for (int i = 2; i < m; i++)
            {
                for (int j = 1; j <= i/2; j++)
                    for (int k = 0; k < lists.get(j).size(); k++)
                    {
                        for (int l = 0; l < lists.get(i-j).size(); l++)
                        {
                            toBeAdded = (lists.get(j).get(k) * lists.get(i-j).get(l)) % m;
                            //check if toBeAdded is in cumulative
                            add = true;
                            for (int n = 0; n < cumulative.size(); n++)
                            {
                                if (toBeAdded == cumulative.get(n))
                                    add = false;
                            }
                            if (add)
                            {
                                cumulative.add(toBeAdded);
                                lists.get(i).add(toBeAdded);
                            }

                            toBeAdded = (lists.get(j).get(k) + lists.get(i-j).get(l)) % m;
                            //check if toBeAdded is in cumulative
                            add = true;
                            for (int n = 0; n < cumulative.size(); n++)
                            {
                                if (toBeAdded == cumulative.get(n))
                                    add = false;
                            }
                            if (add)
                            {
                                cumulative.add(toBeAdded);
                                lists.get(i).add(toBeAdded);
                            }

                        }
                    }
            }

            for (int i = 1; i < lists.size(); i++)
            {
                if (lists.get(i).size() != 0)
                {
                    theMax = i;
                }
            }

            max.add(theMax);
        }

        Collections.sort(max);
        return max;
    }

    public static void efficiency(int m)
    {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();

        for (int i = 1; i <= m+1; i++)
        {
            lists.add(new ArrayList<Integer>());
        }

        ArrayList<Integer> cumulative = new ArrayList<Integer>();

        boolean add = true;
        int toBeAdded = -1;

        int maxForS = -1;

        ArrayList<Integer> maxes = new ArrayList<Integer>(); // has the maxes for each value of s

        for (int s = 1; s < m; s++) // for each starting value;
        {
            if (GCD(s, m) == 1)
            // we need s to be a unit
            //otherwise, s would not span
            {
                lists = new ArrayList<ArrayList<Integer>>();
                cumulative = new ArrayList<Integer>();
                for (int a = 0; a <= m; a++)
                    lists.add(new ArrayList<Integer>());

                lists.get(1).add(s);
                cumulative.add(s);

                for (int i = 2; i <= m; i++) // for each complexity above 1
                {
                    for (int j = 1; j <= i/2; j++)
                    {
                        for (int k = 0; k < lists.get(j).size(); k++)
                        {
                            for (int l = 0; l < lists.get(i - j).size(); l++)
                            {

                                toBeAdded = (lists.get(j).get(k) + lists.get(i - j).get(l)) % m;

                                add = true;

                                for (int o = 0; o < cumulative.size(); o++)
                                {
                                    if (toBeAdded == cumulative.get(o))
                                        add = false;
                                }

                                if (add)
                                {
                                    lists.get(i).add(toBeAdded); // add the number
                                    cumulative.add(toBeAdded);
                                    maxForS = i; // this is our maximum complexity for now
                                }

                                toBeAdded = (lists.get(j).get(k) * lists.get(i - j).get(l)) % m;

                                add = true;

                                for (int o = 0; o < cumulative.size(); o++)
                                {
                                    if (toBeAdded == cumulative.get(o))
                                        add = false;
                                }

                                if (add)
                                {
                                    lists.get(i).add(toBeAdded); // add the number
                                    cumulative.add(toBeAdded);
                                    maxForS = i; // this is our maximum complexity for now
                                }

                            }
                        }
                    }
                }
                    
                System.out.println(m + ", " + maxForS);
                //maxes.add(maxForS);
            }
        }
        //Collections.sort(maxes);

        //System.out.println(m + ", " + maxes.get(0));
    }
    
    public static void inefficiencyFunction(int m)
    {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>(); //creates a list of lists of numbers for each complexity

        for (int i = 1; i <= m+1; i++) // fills up lists
        {
            lists.add(new ArrayList<Integer>());
        }

        ArrayList<Integer> cumulative = new ArrayList<Integer>(); //keeps track of complexities we've already gotten

        boolean add = true;
        int toBeAdded = -1;

        int inefficiency = -1; // this is our y variable
        
        // our x variables: size, order, resilience
        int order = -1;
        int resilience = -1;
        // size is already just equal to s

        ArrayList<Integer> maxes = new ArrayList<Integer>(); // has the maxes for each value of s

        for (int s = 1; s < m; s++) // for each starting value;
        {
            if (GCD(s, m) == 1)
            // we need s to be a unit
            //otherwise, s would not span
            {
                lists = new ArrayList<ArrayList<Integer>>();
                cumulative = new ArrayList<Integer>();
                for (int a = 0; a <= m; a++)
                    lists.add(new ArrayList<Integer>());

                lists.get(1).add(s);
                cumulative.add(s);
                
                order = order(s, m);

                for (int i = 2; i <= m; i++) // for each complexity above 1
                {
                    for (int j = 1; j <= i/2; j++)
                    {
                        for (int k = 0; k < lists.get(j).size(); k++)
                        {
                            for (int l = 0; l < lists.get(i - j).size(); l++)
                            {

                                toBeAdded = (lists.get(j).get(k) + lists.get(i - j).get(l)) % m;

                                add = true;

                                for (int o = 0; o < cumulative.size(); o++)
                                {
                                    if (toBeAdded == cumulative.get(o))
                                    {
                                        add = false;
                                        if (resilience == -1)
                                            resilience = i - 1;
                                        // records the last complexity without repeats of not already recorded
                                    }
                                }

                                if (add)
                                {
                                    lists.get(i).add(toBeAdded); // add the number
                                    cumulative.add(toBeAdded);
                                    inefficiency = i; // this is our maximum complexity for now
                                }

                                toBeAdded = (lists.get(j).get(k) * lists.get(i - j).get(l)) % m;

                                add = true;

                                for (int o = 0; o < cumulative.size(); o++)
                                {
                                    if (toBeAdded == cumulative.get(o))
                                    {
                                        add = false;
                                        if (resilience == -1)
                                            resilience = i - 1;
                                        // records the last complexity without repeats of not already recorded
                                    }
                                }

                                if (add)
                                {
                                    lists.get(i).add(toBeAdded); // add the number
                                    cumulative.add(toBeAdded);
                                    inefficiency = i; // this is our maximum complexity for now
                                }

                            }
                        }
                    }
                }

                System.out.println("" + resilience + ", " + inefficiency);
                inefficiency = -1;
                resilience = -1;
            }
        }
    }
    

}
