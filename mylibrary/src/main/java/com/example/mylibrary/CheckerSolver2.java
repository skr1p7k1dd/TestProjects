package com.example.mylibrary;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Alex on 10/22/2017.
 */

public class CheckerSolver2 {

    static char EMPTY_CHAR = '\u0000';

    public static void main(String[] args) {

        int[] X1    = {3, 5, 1, 6};
        int[] Y1    = {1, 3, 3, 8};
        String T1   = "Xpqp";
        System.out.println("ANSWER 1 IS: " + Solve(X1, Y1, T1) + " and it should be: 10");


        int[] X2    = {0, 3, 5, 1, 6};
        int[] Y2    = {4, 1, 3, 3, 8};
        String T2   = "pXpqp";
        System.out.println("ANSWER 2 IS: " + Solve(X2, Y2, T2) + " and it should be: 2");


        int[] X3    = {0, 6, 2, 5, 3, 0};
        int[] Y3    = {4, 8, 2, 3, 1, 6};
        String T3   = "ppqpXp";
        System.out.println("ANSWER 3 IS: " + Solve(X3, Y3, T3) + " and it should be: 12");

        int[] X4    = {0, 10000};
        int[] Y4    = {0, 10000};
        String T4   = "Xp";
        System.out.println("ANSWER 4 IS: " + Solve(X4, Y4, T4) + " and it should be: 1");


        int[] X5    = {0, 10001};
        int[] Y5    = {0, 10001};
        String T5   = "Xp";
        System.out.println("ANSWER 5 IS: " + Solve(X5, Y5, T5) + " and it should be: 0");
    }

    public static int Solve(int[] x, int[] y, String T) {

        //Build pieces
        ArrayList<Piece> allPieces = new ArrayList<Piece>();
        Piece ourPiece = null;
        for(int i = 0; i < x.length; i++) {

            Piece thisPiece = new Piece(x[i], y[i], T.charAt(i));
            if (thisPiece.type == 'X') {
                ourPiece = thisPiece;
            }
            allPieces.add(thisPiece);
        }

        //Error checking
        if (ourPiece == null) {
            System.out.println("ERROR - ourpiece not initialized!");
            return 0;
        }
        if (allPieces.size() == 0) {
            System.out.println("ERROR - no enemy pieces!");
            return 0;
        }

        //Sort enemy pieces by distance
        allPieces.sort(new Comparator<Piece>() {
            @Override
            public int compare(Piece o1, Piece o2) {
                if (o1.y == o2.y)
                    return 0;
                else if (o1.y > o2.y)
                    return 1;
                else return 0;
            }
        });


        int[] xCopy = x.clone();
        int[] yCopy = y.clone();
        java.util.Arrays.sort(xCopy);
        java.util.Arrays.sort(yCopy);

        int width = xCopy[xCopy.length-1] - xCopy[0] + 2;  //natural width of the board,
        int height = yCopy[yCopy.length-1] + 2;  //natural height of the board, +1 to offset array index, +1 for an extra row after the end
        int leftRightPadding = (height / 2) + 1;

        int finalWidth = width + (leftRightPadding+ leftRightPadding);    //Add possible height to left & right sides to bound horizontal movement. Initial piece placements must also have height added


        int[][] valueGraph = new int[height][finalWidth];
        char[][] enemyLocationGraph = new char[height][finalWidth];

        //Place enemies
        for (Piece piece: allPieces) {
            if (piece != ourPiece) {
                enemyLocationGraph[piece.y][piece.x + leftRightPadding] = piece.type;
            }
        }

        //DEBUG: Print enemy location graph
        PrintEnemyLocationGraph(enemyLocationGraph);

        //Set starting point value for building dynamic solution graph
        valueGraph[ourPiece.y][ourPiece.x + leftRightPadding] = 1;

        //Loop over Y value of solution graph and set point values
        for(int yi = ourPiece.y + 1; yi < valueGraph.length; yi++) {
            for (int xi = 0; xi < valueGraph[0].length; xi++) {
                try{
                    valueGraph[yi][xi] = GetValueOfPosition(valueGraph, enemyLocationGraph, xi, yi);
                } catch(Exception e) {
                    System.out.println("Caught exception at: " + yi + ", " + xi);
                }

            }
        }

        //DEBUG: Print value graph
        PrintValueGraph(valueGraph);

        int maxVal = 0;
        for (int i = 0; i < valueGraph[0].length; i++) {
            int thisVal = valueGraph[valueGraph.length-1][i];
            //System.out.println(thisVal);

            if (thisVal > maxVal) {
                maxVal = thisVal;
            }
        }
        return maxVal - 1;  //Remove initial 1 value

    }

    public static int GetValueOfPosition(int[][] valueGraph, char[][] enemyLocationGraph, int x, int y) {


//        if (y == 2 && x == 11) {
//            System.out.print("");
//        }


        //If theres an enemy here, or we are at the first row, this position has no value
        if (enemyLocationGraph[y][x] != EMPTY_CHAR || y == 0) {
            return 0;
        }

        int leftVal = 0;
        int rightVal = 0;
        char leftEnemyType = EMPTY_CHAR;
        char rightEnemyType = EMPTY_CHAR;

        //Back Left
        if (x > 0) {
            leftEnemyType = enemyLocationGraph[y - 1][x - 1];

            //If enemy piece is not there, propagate value
            if (leftEnemyType == EMPTY_CHAR) {
                leftVal = valueGraph[y-1][x-1];
            }
            //Else, see if there is a gap so we can capture it
            else if (enemyLocationGraph[y-2][x-2] == EMPTY_CHAR && valueGraph[y-2][x-2] > 0) {
                leftVal = valueGraph[y-2][x-2] + getValueForType(leftEnemyType);
            }
        }

        //Back Right
        //If enemy piece is not there, propagate value
        if(x < enemyLocationGraph[y - 1].length - 1) {
            rightEnemyType = enemyLocationGraph[y - 1][x + 1];
            if (rightEnemyType == EMPTY_CHAR) {
                rightVal = valueGraph[y - 1][x + 1];
            }
        }
        //If enemy piece is there, append value to position value before that
        if(y >= 2 && x < enemyLocationGraph[y - 2].length - 2) {
            if (enemyLocationGraph[y - 1][x + 1] != EMPTY_CHAR && valueGraph[y - 2][x + 2] > 0) {
                rightVal = valueGraph[y - 2][x + 2] + getValueForType(rightEnemyType);
            }
        }


        return Math.max(leftVal, rightVal);
    }

    public static int getValueForType(char type) {
        if (type == 'p')
            return 1;
        else if (type == 'q')
            return 10;
        else if (type == 'X')
            return 0;

        System.out.println("CRITICAL ERROR in getValueForType(). type:" + type);
        return 0;
    }

    public static void PrintEnemyLocationGraph(char[][] enemyLocationGraph) {
//        System.out.println("------------------------------------");
//        for(int yi = 0; yi < enemyLocationGraph.length; yi++) {
//            for (int xi = 0; xi < enemyLocationGraph[0].length; xi++) {
//
//                char charToPrint =  enemyLocationGraph[yi][xi];
//                if (charToPrint == EMPTY_CHAR) {
//                    charToPrint = '.';
//                }
//                System.out.print(charToPrint);
//            }
//            System.out.println();
//        }
//        System.out.println("------------------------------------");
    }

    public static void PrintValueGraph(int[][] valueGraph) {
//        System.out.println("======================================");
//        for(int yi = 0; yi < valueGraph.length; yi++) {
//            for (int xi = 0; xi < valueGraph[0].length; xi++) {
//
//                String valToPrint = "" + valueGraph[yi][xi];
//                if (valToPrint.equalsIgnoreCase("10"))
//                    valToPrint = "A";
//                else if (valToPrint.equalsIgnoreCase("11"))
//                    valToPrint = "B";
//                else if (valToPrint.equalsIgnoreCase("12"))
//                    valToPrint = "C";
//                else if (valToPrint.equalsIgnoreCase("13"))
//                    valToPrint = "D";
//                else if (valToPrint.equalsIgnoreCase("14"))
//                    valToPrint = "E";
//
//
//                System.out.print(valToPrint);
//            }
//            System.out.println();
//        }
//        System.out.println("======================================");
    }
}

class Piece {
    int x;
    int y;
    char type;

    public Piece(int x, int y, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}