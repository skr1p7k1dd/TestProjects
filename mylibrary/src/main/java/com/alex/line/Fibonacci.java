package com.alex.line;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Alex on 10/28/2017.
 */

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println("testVal 0 = " + Calculate(0).toString());
        System.out.println("testVal 1 = " + Calculate(1).toString());
        System.out.println("testVal 2 = " + Calculate(2).toString());
        System.out.println("testVal 3 = " + Calculate(3).toString());
        System.out.println("testVal 4 = " + Calculate(4).toString());
        System.out.println("testVal 5 = " + Calculate(5).toString());
        System.out.println("testVal 8181 = " + Calculate(8181).toString());
    }

    static ArrayList<BigInteger> storedValues = new ArrayList<>();

    public static BigInteger Calculate(int n) {

        storedValues.clear();
        storedValues.add(new BigInteger("0"));
        storedValues.add(new BigInteger("1"));

        for (int i = 2; i <= n; i++) {
            storedValues.add(storedValues.get(i-2).add(storedValues.get(i-1)));
        }
        return storedValues.get(n);
    }
}
