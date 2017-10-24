//package com.example.mylibrary;
//
//import java.sql.Array;
//import java.util.ArrayList;
//import java.util.Comparator;
//
///**
// * Created by Alex on 10/22/2017.
// */
//
//public class CheckerSolver {
//
//    static char EMPTY_CHAR = '\u0000';
//
//    public static void main(String[] args) {
//        int[] X1    = {3, 5, 1, 6};
//        int[] Y1    = {1, 3, 3, 8};
//        String T1   = "Xpqp";
//        System.out.println("ANSWER1 IS: " + solution(X1, Y1, T1));
//
//
//
//        int[] X2    = {0, 3, 5, 1, 6};
//        int[] Y2    = {4, 1, 3, 3, 8};
//        String T2   = "pXpqp";
//        //System.out.println("ANSWER2 IS: " + solution(X2, Y2, T2));
//
//    }
//
//    public static int solution(int[] x, int[] y, String T) {
//
//        //Build pieces
//        ArrayList<Piece> enemyPieces = new ArrayList<Piece>();
//        Piece ourPiece = null;
//        for(int i = 0; i < x.length; i++) {
//
//            Piece thisPiece = new Piece(x[i], y[i], T.charAt(i));
//            if (thisPiece.type == 'X') {
//                ourPiece = thisPiece;
//                //System.out.println("Added our piece at: " + thisPiece.x + ", " + thisPiece.y);
//            } else {
//                enemyPieces.add(thisPiece);
//                //System.out.println("Added enemy piece at: " + thisPiece.x + ", " + thisPiece.y + " of type:" + thisPiece.type);
//            }
//        }
//
//        //Error checking
//        if (ourPiece == null) {
//            System.out.println("ERROR - ourpiece not initialized!");
//            return 0;
//        }
//        if (enemyPieces.size() == 0) {
//            System.out.println("ERROR - no enemy pieces!");
//            return 0;
//        }
//
//        //Sort enemy pieces by distance
//        enemyPieces.sort(new Comparator<Piece>() {
//            @Override
//            public int compare(Piece o1, Piece o2) {
//                if (o1.y == o2.y)
//                    return 0;
//                else if (o1.y > o2.y)
//                    return 1;
//                else return 0;
//            }
//        });
//
//
//        int[] xCopy = x.clone();
//        int[] yCopy = y.clone();
//        java.util.Arrays.sort(xCopy);
//        java.util.Arrays.sort(yCopy);
//
//        int width = xCopy[xCopy.length-1] - xCopy[0] + 1;  //natural width of the board,
//        //int height = yCopy[yCopy.length-1] - yCopy[0];  //natural height of the board,
//        int height = yCopy[yCopy.length-1] + 2;  //natural height of the board,
//
//        int finalWidth = width + (height + height);    //Add possible height to left & right sides to bound horizontal movement. Initial piece placements must also have height added
//
//        //System.out.println("natural size:" + width + ", " + height);
//        //System.out.println("finalWidth:" + finalWidth);
//
//        int[][] valueGraph = new int[height][finalWidth];
//        char[][] enemyLocationGraph = new char[height][finalWidth];
//
//        //Place enemies
//        for (Piece piece: enemyPieces) {
//            //System.out.println("placing enemy at location: " + piece.x + "+" + height + "" + ", " + piece.y);
//            enemyLocationGraph[piece.y][piece.x + height] = piece.type;
//        }
//
//        //DEBUG: Print enemy location graph
//        PrintEnemyLocationGraph(enemyLocationGraph);
//
//        //Set starting point value for building dynamic solution graph
//        int relativeX = ourPiece.x - xCopy[0];
//        valueGraph[0][relativeX + height] = 1;
//
//        //Loop over Y value of solution graph and set point values
//        for(int yi = 1; yi < valueGraph.length; yi++) {
//            for (int xi = 0; xi < valueGraph[0].length; xi++) {
//
//                try{
//                    valueGraph[yi][xi] = GetValueOfPosition(valueGraph, enemyLocationGraph, xi, yi);
//                } catch(Exception e) {
//                    System.out.println("Caught exception at: " + yi + ", " + xi);
//                }
//
//            }
//        }
//        System.out.println("DONE!");
//        //System.out.println("length was:" + valueGraph.length);
//        //System.out.println("width was:" + valueGraph[0].length);
//
//        //DEBUG: Print value graph
//        PrintValueGraph(valueGraph);
//
//        //System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
//        System.out.println(" ");
//        int maxVal = 0;
//        for (int i = 0; i < valueGraph[0].length; i++) {
//            int thisVal = valueGraph[valueGraph.length-1][i];
//            //System.out.println(thisVal);
//
//            if (thisVal > maxVal) {
//                maxVal = thisVal;
//            }
//        }
//        return maxVal;
//
//    }
//
//    public static int GetValueOfPosition(int[][] valueGraph, char[][] enemyLocationGraph, int x, int y) {
//
//
//        if (y == 1 && x == 11) {
//            System.out.print("");
//        }
//
//
//        //If theres an enemy here, or we are at the first row, this position has no value
//        if (enemyLocationGraph[y][x] != '\u0000' || y == 0) {
//            return 0;
//        }
//
//        int leftVal = 0;
//        int rightVal = 0;
//        char leftEnemyType = EMPTY_CHAR;
//        char rightEnemyType = EMPTY_CHAR;
//
//        //Back Left
//        if (x > 0) {
//            leftEnemyType = enemyLocationGraph[y - 1][x - 1];
//
//            //If enemy piece is not there, propagate value
//            if (leftEnemyType == EMPTY_CHAR) {
//                leftVal = valueGraph[y-1][x-1];
//            }
//            //Else, see if there is a gap so we can capture it
//            else if (enemyLocationGraph[y-2][x-2] == EMPTY_CHAR && valueGraph[y-2][x-2] > 0) {
//                leftVal = valueGraph[y-2][x-2] + getValueForType(leftEnemyType);
//            }
//        }
//
//        //Back Right
//        //If enemy piece is not there, propagate value
//        if(x < enemyLocationGraph[y - 1].length - 1) {
//            rightEnemyType = enemyLocationGraph[y - 1][x + 1];
//            if (rightEnemyType == EMPTY_CHAR) {
//                rightVal = valueGraph[y - 1][x + 1];
//            }
//        }
//        //If enemy piece is there, append value to position value before that
//        if(y >= 2 && x < enemyLocationGraph[y - 2].length - 2) {
//            if (enemyLocationGraph[y - 1][x + 1] != EMPTY_CHAR && valueGraph[y - 2][x + 2] > 0) {
//                rightVal = valueGraph[y - 2][x + 2] + getValueForType(rightEnemyType);
//            }
//        }
//
//
//        return Math.max(leftVal, rightVal);
//    }
//
//    public static int getValueForType(char type) {
//        if (type == 'p')
//            return 1;
//        else if (type == 'q')
//            return 3;
//
//        System.out.println("CRITICAL ERROR in getValueForType(). type:" + type);
//        return 0;
//    }
//
//    public static void PrintEnemyLocationGraph(char[][] enemyLocationGraph) {
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
//    }
//
//    public static void PrintValueGraph(int[][] valueGraph) {
//        System.out.println("======================================");
//        for(int yi = 0; yi < valueGraph.length; yi++) {
//            for (int xi = 0; xi < valueGraph[0].length; xi++) {
//
//                int valToPrint = valueGraph[yi][xi];
//                System.out.print(valToPrint);
//            }
//            System.out.println();
//        }
//        System.out.println("======================================");
//    }
//}
//
//class Piece {
//    int x;
//    int y;
//    char type;
//
//    public Piece(int x, int y, char type) {
//        this.x = x;
//        this.y = y;
//        this.type = type;
//    }
//}