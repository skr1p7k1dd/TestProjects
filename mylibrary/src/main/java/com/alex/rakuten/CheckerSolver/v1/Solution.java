package com.alex.rakuten.CheckerSolver.v1;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Alex on 10/22/2017.
 */

public class Solution {

    static char EMPTY_CHAR = '\u0000';
    static int UNREACHABLE = 0;
    static int STARTING_LOCATION_VAL = -1;
    static int NO_VALUE = -2;

    static boolean DEBUG_MODE = true;

    public static void main(String[] args) {

//        int[] X1    = {3, 5, 1, 6};
//        int[] Y1    = {1, 3, 3, 8};
//        String T1   = "Xpqp";
//        int answer1 = solution(X1, Y1, T1);
//        int expectedAnswer1 = 10;
//        if (answer1 != expectedAnswer1) {
//            System.out.println("ANSWER 1 IS: " + answer1 + " and it should be: " + expectedAnswer1);
//        }
//
//        int[] X2    = {0, 3, 5, 1, 6};
//        int[] Y2    = {4, 1, 3, 3, 8};
//        String T2   = "pXpqp";
//        int answer2 = solution(X2, Y2, T2);
//        int expectedAnswer2 = 2;
//        if (answer2 != expectedAnswer2) {
//            System.out.println("ANSWER 2 IS: " + answer2 + " and it should be: " + expectedAnswer2);
//        }
//
//        int[] X3    = {0, 6, 2, 5, 3, 0};
//        int[] Y3    = {4, 8, 2, 3, 1, 6};
//        String T3   = "ppqpXp";
//        int answer3 = solution(X3, Y3, T3);
//        int expectedAnswer3 = 12;
//        if (answer3 != expectedAnswer3) {
//            System.out.println("ANSWER 3 IS: " + answer3 + " and it should be: " + expectedAnswer3);
//        }
//
//        int[] X4    = {0, 1};
//        int[] Y4    = {0, 1};
//        String T4   = "Xp";
//        int answer4 = solution(X4, Y4, T4);
//        int expectedAnswer4 = 1;
//        if (answer4 != expectedAnswer4) {
//            System.out.println("Custom 4: " + answer4 + " and it should be: " + expectedAnswer4);
//        }
//
//        int[] X5    = {0, 1, 2};
//        int[] Y5    = {0, 1, 2};
//        String T5   = "Xpp";
//        int answer5 =  solution(X5, Y5, T5);
//        int expectedAnswer5 = 0;
//        if (answer5 != expectedAnswer5) {
//            System.out.println("Custom 5: " + answer5 + " and it should be: " + expectedAnswer5);
//        }
//
//        int[] X6    = {0, 1, 3};
//        int[] Y6    = {0, 1, 3};
//        String T6   = "Xpp";
//        int answer6 = solution(X6, Y6, T6);
//        int expectedAnswer6 = 2;
//        if (answer6 != expectedAnswer6) {
//            System.out.println("Custom 6: " + answer6 + " and it should be: " + expectedAnswer6);
//        }
//
//        int[] X7    = {0, 1, 3, 4};
//        int[] Y7    = {0, 1, 3, 4};
//        String T7   = "Xppp";
//        int answer7 = solution(X7, Y7, T7);
//        int expectedAnswer7 = 1;
//        if (answer7 != expectedAnswer7) {
//            System.out.println("Custom 7: " + answer7 + " and it should be: " + expectedAnswer7);
//        }
//
//        int[] X8    = {0, 1, 3, 4, 0};
//        int[] Y8    = {0, 1, 3, 4, 4};
//        String T8   = "Xpppq";
//        int answer8 = solution(X8, Y8, T8);
//        int expectedAnswer8 = 11;
//        if (answer8 != expectedAnswer8) {
//            System.out.println("Custom 8: " + answer8 + " and it should be: " + expectedAnswer8);
//        }
//
        int[] X9    = {0, 1, 3, 4, 0};
        int[] Y9    = {0, 1, 3, 4, 10};
        String T9   = "Xpppq";
        int answer9 = Solve(X9, Y9, T9);
        int expectedAnswer9 = 1;
        if (answer9 != expectedAnswer9) {
            System.out.println("Custom 9: " + answer9 + " and it should be: " + expectedAnswer9);
        }
//
//        int[] X10    = {0, 1, 3, 0};
//        int[] Y10    = {0, 1, 3, 10};
//        String T10   = "Xppq";
//        int answer10 = solution(X10, Y10, T10);
//        int expectedAnswer10 = 12;
//        if (answer10 != expectedAnswer10) {
//            System.out.println("Custom 10: " + answer10 + " and it should be: " + expectedAnswer10);
//        }
//
//        int[] X11    = {0, 1, 3, 1};
//        int[] Y11    = {0, 1, 3, 10};
//        String T11   = "Xppq";
//        int answer11 = solution(X11, Y11, T11);
//        int expectedAnswer11 = 2;
//        if (answer11 != expectedAnswer11) {
//            System.out.println("Custom 11: " + answer11 + " and it should be: " + expectedAnswer11);
//        }
//
//        int[] X12    = {0, 1, 2, 1};
//        int[] Y12    = {0, 1, 2, 10};
//        String T12   = "Xppq";
//        int answer12 = solution(X12, Y12, T12);
//        int expectedAnswer12 = 0;
//        if (answer12 != expectedAnswer12) {
//            System.out.println("Custom 12: " + answer12 + " and it should be: " + expectedAnswer12);
//        }
//
//        int[] X13    = {10, 12};
//        int[] Y13    = {10, 10};
//        String T13   = "Xp";
//        int answer13 = solution(X13, Y13, T13);
//        int expectedAnswer13 = 0;
//        if (answer13 != expectedAnswer13) {
//            System.out.println("Custom 13: " + answer13 + " and it should be: " + expectedAnswer13);
//        }

    }

    static ArrayList<Piece> allPieces = null;
    static Piece ourPiece = null;
    static int topBottomPadding = 1;    //stays at 1
    static int leftRightPadding = -10000;   //should be set elsewhere

    public static int Solve(int[] x, int[] y, String T) {

        //////////////////////////////
        /////BEGIN INPUT CHECKING/////
        //////////////////////////////

        //Build pieces
        allPieces = new ArrayList<Piece>();
        ourPiece = null;

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

        int width = xCopy[xCopy.length-1] + 1;
        int height = yCopy[yCopy.length-1] + 1 + (2 * topBottomPadding);  //natural height of the board, +1 to offset array index, +1 for an extra row after the end
        leftRightPadding = (height / 2) + 1;


        int finalWidth = width + (leftRightPadding+ leftRightPadding);    //Add possible height to left & right sides to bound horizontal movement. Initial piece placements must also have height added

        if (DEBUG_MODE) {
            System.out.println("Width: " + width + ", Height: " + height + ", FinalWidth:" + finalWidth);
        }

        //////////////////////////////
        /////BEGIN GRAPH CREATION/////
        //////////////////////////////


        int[][] valueGraph = new int[height][finalWidth];
        char[][] enemyLocationGraph = new char[height][finalWidth];

        //Place enemies
        for (Piece piece: allPieces) {
            if (piece != ourPiece) {
                if (DEBUG_MODE) {
                    System.out.println("Placing " + piece.type + " at " + (piece.x + leftRightPadding) + ", " + (piece.y + topBottomPadding));
                }
                enemyLocationGraph[piece.y + topBottomPadding][piece.x + leftRightPadding] = piece.type;
            }
        }

        //DEBUG: Print enemy location graph
        PrintEnemyLocationGraph(enemyLocationGraph);

        //Set starting point values for building dynamic solution graph
        valueGraph[ourPiece.y + topBottomPadding    ][ourPiece.x + leftRightPadding    ] = STARTING_LOCATION_VAL;
        valueGraph[ourPiece.y + topBottomPadding - 1][ourPiece.x + leftRightPadding - 1] = STARTING_LOCATION_VAL;
        valueGraph[ourPiece.y + topBottomPadding - 1][ourPiece.x + leftRightPadding + 1] = STARTING_LOCATION_VAL;

        //Loop over Y value of solution graph and set point values
        for(int yi = ourPiece.y + topBottomPadding + 1; yi < valueGraph.length; yi++) {
            for (int xi = 0; xi < valueGraph[0].length; xi++) {
                try{
                    int calculatedValue = GetValueOfPosition(valueGraph, enemyLocationGraph, xi, yi);
                    if (calculatedValue != NO_VALUE) {
                        valueGraph[yi][xi] =  calculatedValue;
                    }
                } catch(Exception e) {
                    System.out.println("Caught exception at: " + yi + ", " + xi);
                }

            }
        }

        //DEBUG: Print value graph
        PrintValueGraph(valueGraph);


        //The best move is the one with the most points thus far
        int maxVal = 0;
        for (int i = 0; i < valueGraph[0].length; i++) {
            int thisVal = valueGraph[valueGraph.length-1][i];
            if (thisVal > maxVal) {
                maxVal = thisVal;
            }
        }
        return maxVal;

    }

    public static int GetValueOfPosition(int[][] valueGraph, char[][] enemyLocationGraph, int x, int y) {


        if (y == 12 && x == 20) {
            System.out.print("");
        }


        //Early returns
        //...If theres an enemy here, or we are at the first row, or this is already marked a starting spot
        if (enemyLocationGraph[y][x] != EMPTY_CHAR || y == 0 || valueGraph[y][x] == STARTING_LOCATION_VAL) {
            return NO_VALUE;
        }

        int leftVal = UNREACHABLE;
        int rightVal = UNREACHABLE;
        char leftEnemyType = EMPTY_CHAR;
        char rightEnemyType = EMPTY_CHAR;

        //Back Left
        if (x > 0) {
            leftEnemyType = enemyLocationGraph[y - 1][x - 1];

            //If enemy piece is not there, propagate value (starting or existing)
            if (leftEnemyType == EMPTY_CHAR) {
                //Try to propagate left value, (excluding starting case)
                int tmpLeftVal = valueGraph[y-1][x-1];
                if (tmpLeftVal != STARTING_LOCATION_VAL) {
                    leftVal = valueGraph[y-1][x-1];
                }//If its a starting point value, only propagate in the same direction it was going
                else if (y>=2 && x>=2 && valueGraph[y-2][x-2] == STARTING_LOCATION_VAL) {
                    leftVal = STARTING_LOCATION_VAL;
                }
            }
            //Else, there is an enemy. See if the double back-left value was reachable
            else if (y >= 2 && enemyLocationGraph[y-2][x-2] == EMPTY_CHAR && valueGraph[y-2][x-2] != UNREACHABLE) {

                int existingDoubleLeftVal = valueGraph[y-2][x-2];
                if (existingDoubleLeftVal == STARTING_LOCATION_VAL) {
                    existingDoubleLeftVal = 0;  //Hotswap proper value in before doing calculation
                }

                //Prevent capture if this row is just 1 higher than OUR PIECE (capture begins on 2 rows higher) (prevents edge case)
                if (y > ourPiece.y + topBottomPadding + 1) {
                    leftVal = existingDoubleLeftVal + getValueForType(leftEnemyType);
                }
            }
        }

        //Back Right
        //If enemy piece is not there, propagate value
        if(x < enemyLocationGraph[y - 1].length - 1) {
            rightEnemyType = enemyLocationGraph[y - 1][x + 1];

            //If enemy piece is not there, propagate value (starting or existing)
            if (rightEnemyType == EMPTY_CHAR) {
                //Try to propagate left value, (excluding starting case)
                int tmpRightVal = valueGraph[y-1][x+1];
                if (tmpRightVal != STARTING_LOCATION_VAL) {
                    rightVal = valueGraph[y-1][x+1];
                }//If its a starting point value, only propagate in the same direction it was going
                else if (y>=2 && x < enemyLocationGraph[y - 2].length - 2 && valueGraph[y-2][x+2] == STARTING_LOCATION_VAL) {
                    rightVal = STARTING_LOCATION_VAL;
                }
            }
        }
        //If enemy piece is there, append value to position value before that
        if(y >= 2 && x < enemyLocationGraph[y - 2].length - 2) {
            if (enemyLocationGraph[y - 1][x + 1] != EMPTY_CHAR && valueGraph[y - 2][x + 2] != UNREACHABLE) {

                int existingDoubleRightVal = valueGraph[y - 2][x + 2];
                if (existingDoubleRightVal == STARTING_LOCATION_VAL) {
                    existingDoubleRightVal = 0;  //Hotswap proper value in before doing calculation
                }

                //Prevent capture if this row is just 1 higher than OUR PIECE (capture begins on 2 rows higher) (prevents edge case)
                if (y > ourPiece.y + topBottomPadding + 1) {
                    rightVal = existingDoubleRightVal + getValueForType(rightEnemyType);
                }
            }
        }


        //Special check to make it prefer STARTING_LOCATION_VAL over UNREACHABLE
        int maxRVal = Math.max(leftVal, rightVal);
        if (maxRVal == UNREACHABLE && (leftVal == STARTING_LOCATION_VAL || rightVal == STARTING_LOCATION_VAL)) {
            return STARTING_LOCATION_VAL;
        }
        return maxRVal;
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

        if (!DEBUG_MODE) {
            return;
        }

        System.out.println("------------------------------------");
        for(int yi = enemyLocationGraph.length - 1; yi > 0 ; yi--) {
            for (int xi = 0; xi < enemyLocationGraph[0].length; xi++) {

                char charToPrint =  enemyLocationGraph[yi][xi];
                if (charToPrint == EMPTY_CHAR) {
                    charToPrint = '.';
                }
                //Insert our piece into the printout
                if (xi == (ourPiece.x + leftRightPadding) && yi == (ourPiece.y + topBottomPadding)) {
                    charToPrint = 'X';
                }

                System.out.print(charToPrint);
            }
            System.out.println();
        }
        System.out.println("------------------------------------");
    }

    public static void PrintValueGraph(int[][] valueGraph) {

        if (!DEBUG_MODE) {
            return;
        }

        System.out.println("======================================");
        for(int yi = valueGraph.length - 1; yi > 0 ; yi--) {
            for (int xi = 0; xi < valueGraph[0].length; xi++) {

                String valToPrint = "" + valueGraph[yi][xi];
                if (valToPrint.equalsIgnoreCase("10"))
                    valToPrint = "A";
                else if (valToPrint.equalsIgnoreCase("11"))
                    valToPrint = "B";
                else if (valToPrint.equalsIgnoreCase("12"))
                    valToPrint = "C";
                else if (valToPrint.equalsIgnoreCase("13"))
                    valToPrint = "D";
                else if (valToPrint.equalsIgnoreCase("14"))
                    valToPrint = "E";
                else if (valToPrint.equalsIgnoreCase("-1"))
                    valToPrint = "";


                System.out.print(valToPrint);
            }
            System.out.println();
        }
        System.out.println("======================================");
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