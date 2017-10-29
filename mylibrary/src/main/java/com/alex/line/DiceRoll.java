package com.alex.line;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Alex on 10/28/2017.
 */

public class DiceRoll {

    public static void main(String[] args) {

        System.out.println("testVal 0 = " + Calculate(0).toString());
        System.out.println("testVal 1 = " + Calculate(1).toString());
        System.out.println("testVal 2 = " + Calculate(2).toString());
        System.out.println("testVal 3 = " + Calculate(3).toString());
        System.out.println("testVal 4 = " + Calculate(4).toString());
        System.out.println("testVal 5 = " + Calculate(5).toString());
        System.out.println("testVal 6 = " + Calculate(6).toString());
        System.out.println("testVal 7 = " + Calculate(7).toString());
        System.out.println("testVal 8 = " + Calculate(8).toString());
        System.out.println("testVal 9 = " + Calculate(9).toString());
//        CalculateVerbose(10);
        System.out.println("testVal 10 = " + Calculate(10).toString());
        System.out.println("testVal 610 = " + Calculate(610).toString());
    }

    public static BigInteger Calculate(int n) {
        BigInteger[] state = new BigInteger[Math.max(n+1, 7)];
        Arrays.fill(state, BigInteger.ZERO);

        //Prepopulate states with direct rolls
        for (int i = 1; i <= 6; i++) {
            state[i] = BigInteger.ONE;
        }

        //Calculate new state sum by adding previous dice roll combinations + difference
        //state[n-1] + 1 / state[n-2] + 2 / state[n-3] + 3 / etc.
        for (int i = 1; i < state.length; i++) {
            BigInteger sum = state[i];
            for (int j = Math.max(0, i-6); j < i; j++) {
                sum = sum.add(state[j]);
            }
            state[i] = sum;
        }
        return state[n];
    }

    static ArrayList<ArrayList<String>> moveListContainer = new ArrayList<>();
    public static void CalculateVerbose(int n) {

        if (n >= 30) {
            System.out.println("N too large for CalculateVerbose()");
            return;
        }

        //Initial move list
        moveListContainer.clear();
        moveListContainer.add(new ArrayList<String>());

        //Prepopulate states with direct rolls
        for (int i = 1; i <= 6; i++) {
            ArrayList<String> moveList = new ArrayList<String>();
            moveList.add(""+i);
            moveListContainer.add(moveList);
        }

        //Calculate new states based on the previous states + 1 new dice roll
        for (int i = 1; i < n+1; i++) {
            System.out.println("  i:" + i);
            if (i >= moveListContainer.size()) {
                moveListContainer.add(new ArrayList<String>());
            }

            //Purge old data to save memory
            if (i > 6) {
                moveListContainer.get(i-7).clear();
            }

            ArrayList<String> moveList = moveListContainer.get(i);

            //Append new dice rolls to all existing lists. n-1 + 1 / n-2 + 2 / n-3 + 3 / etc. Exponential growth
            for (int j = 1; j <= 6 && j < i; j++) {
                ArrayList<String> existingMoveList = moveListContainer.get(i-j);
                for (String existingMoves : existingMoveList) {
                    moveList.add(existingMoves+","+j);
                    System.out.println("   " + existingMoves+","+j);
                }
            }
            System.out.println("Added " + moveList.size() + " moves for i: " + i);
            System.out.println("------------------------");

        }
    }

}
