package com.alex.rakuten.Fibonacci;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Alex on 10/25/2017.
 */

public class Solution {

    static BigInteger modVal = new BigInteger("1000000007");
    static ArrayList<BigInteger> storedValues = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Starting tests");

        long testVal1 = GF(3, 4, 0);
        System.out.println("testVal1 = " + testVal1);

        long testVal2 = GF(3, 4, 1);
        System.out.println("testVal2 = " + testVal2);

        long testVal3 = GF(3, 4, 2);
        System.out.println("testVal3 = " + testVal3);

        long testVal4 = GF(3, 4, 3);
        System.out.println("testVal3 = " + testVal4);

        long testVal5 = GF(3, 4, 4);
        System.out.println("testVal5 = " + testVal5);

        long testVal6 = GF(3, 4, 5);
        System.out.println("testVal6 = " + testVal6);

        System.out.println("Test Complete");
    }

    public static int GF(int A, int B, int N) {

        //reset
        storedValues.clear();
        storedValues.add(new BigInteger(""+A));
        storedValues.add(new BigInteger(""+B));

        //Prepopulate values up to then
        for (int i = 0; i < N; i++) {
            GFLong(new BigInteger(""+A), new BigInteger(""+B), i);
        }

        //Get real answer
        BigInteger answer = GFLong(new BigInteger(""+A), new BigInteger(""+B), N);
        return answer.intValue();
    }

    public static BigInteger GFLong(BigInteger A, BigInteger B, int N) {

        //Load from cache if possible
        if (storedValues.size() > N) {
            return storedValues.get(N);
        }


        BigInteger firstVal = GFLong(A, B, N-1);
        BigInteger secondVal = GFLong(A, B, N-2);
        BigInteger tmpRval = firstVal.add(secondVal);
        BigInteger finalAnswer = tmpRval.mod(modVal);


        storedValues.add(finalAnswer);
        return finalAnswer;
    }
}
