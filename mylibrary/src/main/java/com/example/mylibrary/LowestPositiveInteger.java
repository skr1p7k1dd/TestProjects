package com.example.mylibrary;

/**
 * Created by Alex on 10/22/2017.
 */

// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class LowestPositiveInteger {

    static int TestNumber = 0;

    public static void main(String[] args) {

        int[] test1 = {0,1,2,3,4};
        RunTest(test1);
        int[] test2 = {0,-1,-2,-3,-4};
        RunTest(test2);
        int[] test3 = {0,-1,-2,-3,-4,2,3,4,5,6,7};
        RunTest(test3);
        int[] test4 = {0,-1,-2,-3,-4,2,3,4,5,6,7,1,9,10,11};
        RunTest(test4);
    }

    public static void RunTest(int[] A) {
        TestNumber++;
        System.out.println("Test " + TestNumber + " : " + PrintArray(A) + " answer: " + solution(A));
    }

    public static int solution(int[] A) {
        // write your code in Java SE 8
        int index = 0;
        int rVal = 1;

        Arrays.sort(A);

        while (index < A.length) {
            int thisVal = A[index];

            if (thisVal > 0 && thisVal == rVal) {
                rVal++;
            }

            index++;
        }

        return rVal;
    }

    public static String PrintArray(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);

            if (i < arr.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}