package com.alex.line;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Alex on 10/28/2017.
 */

public class DiceRoll {

    public static void main(String[] args) {

//        System.out.println("testVal 0 = " + Calculate(0).toString());
//        System.out.println("testVal 1 = " + Calculate(1).toString());
//        System.out.println("testVal 2 = " + Calculate(2).toString());
//        System.out.println("testVal 3 = " + Calculate(3).toString());
//        System.out.println("testVal 4 = " + Calculate(4).toString());
//        System.out.println("testVal 5 = " + Calculate(5).toString());
//        System.out.println("testVal 6 = " + Calculate(6).toString());
//        System.out.println("testVal 7 = " + Calculate(7).toString());
//        System.out.println("testVal 8 = " + Calculate(8).toString());
//        System.out.println("testVal 9 = " + Calculate(9).toString());
        CalculateVerbose(6);
//        System.out.println("testVal 610 = " + Calculate(610).toString());
    }

    public static BigInteger Calculate(int n) {
        BigInteger[] state = new BigInteger[Math.max(n+1, 7)];
        Arrays.fill(state, BigInteger.ZERO);
        //Prepopulate states with direct rolls
        for (int i = 1; i <= 6; i++) {
            state[i] = BigInteger.ONE;
        }
        System.out.println("N:" + n);
        //Calculate new states based on the previous states + 1 new dice roll
        for (int i = 1; i < state.length; i++) {
            System.out.println("i:" + i);
            BigInteger sum = state[i];  //take prev value
            System.out.println(" existing sum:" + sum.toString());
            for (int j = Math.max(0, i-6); j < i; j++) {
                sum = sum.add(state[j]);    //add results of past 6 states + 1 roll
                System.out.println("  adding to existing sum:" + state[j]);
            }
            System.out.println(" final sum:" + sum.toString());
            System.out.println("-------------------------");
            state[i] = sum;
        }
        return state[n];
    }

    static ArrayList<ArrayList<String>> moveListContainer = new ArrayList<>();
    public static void CalculateVerbose(int n) {



        //Prepopulate states with direct rolls
        for (int i = 1; i <= 6; i++) {
            ArrayList<String> moveList = new ArrayList<String>();
            moveList.add(""+i);
            moveListContainer.add(moveList);
        }

        //Calculate new states based on the previous states + 1 new dice roll
        for (int i = 1; i < n+1; i++) {


            ArrayList<String> moveList = new ArrayList<String>();

            for (int j = 1; j <= 6 && j < i; j++) {
                ArrayList<String> existingMoveList = moveListContainer.get(j);

                for (String existingMoves : existingMoveList) {
                    moveList.add(existingMoves+","+j);
                    System.out.println("i:   " + i +" " + existingMoves+","+j);
                }
            }
            System.out.println("------------------------");

        }
        return;
    }
}
