package com.example.mylibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by Alex on 10/23/2017.
 */

public class CheckerSolver3 {

    static HashMap<String, Piece> allPiecesHashMap = null;
    static ArrayList<Piece> allPieces = null;
    static Piece ourPiece = null;

    static boolean DEBUG_MODE = true;

    public static void main(String[] args) {

        int[] X1    = {3, 5, 1, 6};
        int[] Y1    = {1, 3, 3, 8};
        String T1   = "Xpqp";
        int answer1 = Solve(X1, Y1, T1);
        int expectedAnswer1 = 10;
        if (answer1 != expectedAnswer1) {
            System.out.println("ANSWER 1 IS: " + answer1 + " and it should be: " + expectedAnswer1);
        }
//
//        int[] X2    = {0, 3, 5, 1, 6};
//        int[] Y2    = {4, 1, 3, 3, 8};
//        String T2   = "pXpqp";
//        int answer2 = Solve(X2, Y2, T2);
//        int expectedAnswer2 = 2;
//        if (answer2 != expectedAnswer2) {
//            System.out.println("ANSWER 2 IS: " + answer2 + " and it should be: " + expectedAnswer2);
//        }
//
//        int[] X3    = {0, 6, 2, 5, 3, 0};
//        int[] Y3    = {4, 8, 2, 3, 1, 6};
//        String T3   = "ppqpXp";
//        int answer3 = Solve(X3, Y3, T3);
//        int expectedAnswer3 = 12;
//        if (answer3 != expectedAnswer3) {
//            System.out.println("ANSWER 3 IS: " + answer3 + " and it should be: " + expectedAnswer3);
//        }
//
//        int[] X4    = {0, 1};
//        int[] Y4    = {0, 1};
//        String T4   = "Xp";
//        int answer4 = Solve(X4, Y4, T4);
//        int expectedAnswer4 = 1;
//        if (answer4 != expectedAnswer4) {
//            System.out.println("Custom 4: " + answer4 + " and it should be: " + expectedAnswer4);
//        }
//
//        int[] X5    = {0, 1, 2};
//        int[] Y5    = {0, 1, 2};
//        String T5   = "Xpp";
//        int answer5 =  Solve(X5, Y5, T5);
//        int expectedAnswer5 = 0;
//        if (answer5 != expectedAnswer5) {
//            System.out.println("Custom 5: " + answer5 + " and it should be: " + expectedAnswer5);
//        }
//
//        int[] X6    = {0, 1, 3};
//        int[] Y6    = {0, 1, 3};
//        String T6   = "Xpp";
//        int answer6 = Solve(X6, Y6, T6);
//        int expectedAnswer6 = 2;
//        if (answer6 != expectedAnswer6) {
//            System.out.println("Custom 6: " + answer6 + " and it should be: " + expectedAnswer6);
//        }
//
//        int[] X7    = {0, 1, 3, 4};
//        int[] Y7    = {0, 1, 3, 4};
//        String T7   = "Xppp";
//        int answer7 = Solve(X7, Y7, T7);
//        int expectedAnswer7 = 1;
//        if (answer7 != expectedAnswer7) {
//            System.out.println("Custom 7: " + answer7 + " and it should be: " + expectedAnswer7);
//        }
//
//        int[] X8    = {0, 1, 3, 4, 0};
//        int[] Y8    = {0, 1, 3, 4, 4};
//        String T8   = "Xpppq";
//        int answer8 = Solve(X8, Y8, T8);
//        int expectedAnswer8 = 11;
//        if (answer8 != expectedAnswer8) {
//            System.out.println("Custom 8: " + answer8 + " and it should be: " + expectedAnswer8);
//        }
//
//        int[] X9    = {0, 1, 3, 4, 0};
//        int[] Y9    = {0, 1, 3, 4, 10};
//        String T9   = "Xpppq";
//        int answer9 = Solve(X9, Y9, T9);
//        int expectedAnswer9 = 1;
//        if (answer9 != expectedAnswer9) {
//            System.out.println("Custom 9: " + answer9 + " and it should be: " + expectedAnswer9);
//        }
//
//        int[] X10    = {0, 1, 3, 0};
//        int[] Y10    = {0, 1, 3, 10};
//        String T10   = "Xppq";
//        int answer10 = Solve(X10, Y10, T10);
//        int expectedAnswer10 = 12;
//        if (answer10 != expectedAnswer10) {
//            System.out.println("Custom 10: " + answer10 + " and it should be: " + expectedAnswer10);
//        }
//
//        int[] X11    = {0, 1, 3, 1};
//        int[] Y11    = {0, 1, 3, 10};
//        String T11   = "Xppq";
//        int answer11 = Solve(X11, Y11, T11);
//        int expectedAnswer11 = 2;
//        if (answer11 != expectedAnswer11) {
//            System.out.println("Custom 11: " + answer11 + " and it should be: " + expectedAnswer11);
//        }
//
//        int[] X12    = {0, 1, 2, 1};
//        int[] Y12    = {0, 1, 2, 10};
//        String T12   = "Xppq";
//        int answer12 = Solve(X12, Y12, T12);
//        int expectedAnswer12 = 0;
//        if (answer12 != expectedAnswer12) {
//            System.out.println("Custom 12: " + answer12 + " and it should be: " + expectedAnswer12);
//        }
//
//        int[] X13    = {10, 12};
//        int[] Y13    = {10, 10};
//        String T13   = "Xp";
//        int answer13 = Solve(X13, Y13, T13);
//        int expectedAnswer13 = 0;
//        if (answer13 != expectedAnswer13) {
//            System.out.println("Custom 13: " + answer13 + " and it should be: " + expectedAnswer13);
//        }

    }

    public static int Solve(int[] x, int[] y, String T) {

        /////////////////////////////
        /////BEGIN INPUT PARSING/////
        /////////////////////////////

        //Build pieces
        allPieces = new ArrayList<Piece>();
        allPiecesHashMap = new HashMap<String, Piece>();
        ourPiece = null;

        for(int i = 0; i < x.length; i++) {

            Piece thisPiece = new Piece(x[i], y[i], T.charAt(i));
            if (thisPiece.type == 'X') {
                ourPiece = thisPiece;
            } else {
                allPieces.add(thisPiece);
                String hashKey = thisPiece.getHashKey();
                allPiecesHashMap.put(hashKey, thisPiece);
                DebugPrint("Inserted piece " + thisPiece.type + " with hashKey: " + hashKey);
            }
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


        //Remove unreachable pieces
        allPieces.removeIf(new Predicate<Piece>() {
            @Override
            public boolean test(Piece piece) {

                //Lateral or behind...
                if (piece.y <= ourPiece.y) {
                    return true;
                }

                //remove if its outside the left or right bound of OUR piece
                if ((piece.x - ourPiece.x) > (piece.y - ourPiece.y)) {
                    return true;
                }

                return false;
            }
        });

        //Set index for faster finding
        for(int i = 0; i < allPieces.size(); i++) {
            Piece p = allPieces.get(i);
            p.pieceIndex = i;
        }
        DebugPrint("Set indexes");


        ////////////////////////
        /////BEGIN PHASE 1/////
        ///////////////////////
        //Manually get the first immediate LEFT and RIGHT pieces

        //Beginning with ourpiece, begin calculating piece value by doing MAX (leftVal, rightVal) from the first immediate left/right piece
        //Manually get those 2
        Piece firstLeftPiece = null;
        Piece firstRightPiece = null;

        for(int i = 0; i < allPieces.size() && (firstLeftPiece == null || firstRightPiece == null); i++) {
            Piece piece = allPieces.get(i);
            if ((piece.x - ourPiece.x) == (piece.y - ourPiece.y)) {
                if (piece.x > ourPiece.x) {
                    if (firstRightPiece== null) {
                        firstRightPiece = piece;
                    }
                }
            }
            else if ((ourPiece.x - piece.x) == (piece.y - ourPiece.y)) {
                if (piece.x < ourPiece.x) {
                    if (firstLeftPiece == null) {
                        firstLeftPiece = piece;
                    }
                }
            }
        }

        if (firstLeftPiece == null && firstRightPiece == null) {
            DebugPrint("CRITICAL ERROR - Phase 1.1 - Initial firstLeftPiece & firstRightPiece null");
            return 0;
        }

        //Check LEFT and RIGHT pieces to make sure nothing is behind it. Mark null if so
        if (firstLeftPiece != null && firstLeftPiece.pieceExistsBehindLeft()) {
            DebugPrint("removing firstLeftPiece as something exists behind it");
            firstLeftPiece = null;
        }
        if (firstRightPiece != null && firstRightPiece.pieceExistsBehindRight()) {
            DebugPrint("removing firstRightPiece as something exists behind it");
            firstRightPiece = null;
        }

        if (firstLeftPiece == null && firstRightPiece == null) {
            DebugPrint("CRITICAL ERROR - Phase 1.2 - firstLeftPiece null & firstRightPiece null after check");
            return 0;
        }


        if (DEBUG_MODE) {
            if (firstLeftPiece != null) {
                DebugPrint("firstLeftPiece: " + firstLeftPiece.fullDesc());
            } else {
                DebugPrint("firstLeftPiece: null");
            }
            if (firstRightPiece != null) {
                DebugPrint("firstRightPiece: " + firstRightPiece.fullDesc());
            } else {
                DebugPrint("firstRightPiece: null");
            }
        }


        ////////////////////////
        /////BEGIN PHASE 2/////
        ///////////////////////

        //Recursively calculate max value from pieces
        //phase2: get list of candidate pieces, filter if unreachable, call max value of remaining pieces. do NOT assign values to pieces!

        int maxLeftPathValue = 0;
        int maxRightPathValue = 0;
        if (firstLeftPiece != null) {
            maxLeftPathValue = firstLeftPiece.getMaxPathValue();
        }
        if (firstRightPiece!= null) {
            maxRightPathValue = firstRightPiece.getMaxPathValue();
        }

        return Math.max(maxLeftPathValue, maxRightPathValue);
    }

    public static boolean PieceExistsAt(int x, int y) {

        Piece p = allPiecesHashMap.get(getHashForPosition(x, y));
        boolean exists = p != null;
//        DebugPrint("PieceExistsAt: " + x + "," + y + " = " + exists);
        return exists;
    }

    public static void DebugPrint(String s) {
        if (DEBUG_MODE) {
            System.out.println(s);
        }
    }

    public static String getHashForPosition(int x, int y) {
        return x + "," + y;
    }

    public static ArrayList<Piece> GetAllPiecesAfter(Piece p) {
        ArrayList<Piece> rList = new ArrayList<>();
        for (int i = p.pieceIndex; i < allPieces.size(); i++) {
            Piece thisPiece = allPieces.get(i);
            if (thisPiece.y > p.y) {
                rList.add(thisPiece);
            }
        }
        return rList;
    }

    public static boolean PieceObstructsPath(GridPoint start, GridPoint end) {

        GridPoint a = start;
        GridPoint b = end;
        if (start.y > end.y) {
            a = end;
            b = start;
        }

        if (b.isToTheRightOf(a)) {
            if (b.y - a.y != b.x - a.x) {
                DebugPrint("CRITICAL ERROR IN PieceObstructsPath-a. No path between " + a.desc() + " and " + b.desc());
                return false;
            }

            for (int xi = 0; (a.x + xi) < b.x; xi++) {
                if (allPiecesHashMap.get(getHashForPosition(a.x + xi, a.y + xi)) != null) {
                    return true;
                }
            }
        } else {

            if (b.y - a.y != a.x - b.x) {
                DebugPrint("CRITICAL ERROR IN PieceObstructsPath-b. No path between " + a.desc() + " and " + b.desc());
                return false;
            }

            for (int xi = 0; (a.x - xi) < b.x; xi++) {
                if (allPiecesHashMap.get(getHashForPosition(a.x - xi, a.y + xi)) != null) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Piece {
    int x;
    int y;
    char type;
    int pieceIndex = -1;

    public Piece(int x, int y, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public String getHashKey() {
        return CheckerSolver3.getHashForPosition(this.x, this.y);
    }


    public boolean pieceExistsBehindLeft() {
        return CheckerSolver3.PieceExistsAt(this.x-1, this.y+1);
    }
    public boolean pieceExistsBehindRight() {
        return CheckerSolver3.PieceExistsAt(this.x+1, this.y+1);
    }

    public int getValue() {
        if (type == 'p') {
            return 1;
        }
        if (type == 'q') {
            return 10;
        }
        return 0;
    }

    //Recursively get max path value by filtering to possible path candidates, filtering unreachable paths, then keeping max value of remaining paths
    //Depth First Search
    public int getMaxPathValue() {

        //max value is max value of any viable candidate plus its value
        int maxPathValue = 0;
        ArrayList<Piece> pathCandidates = CheckerSolver3.GetAllPiecesAfter(this);

        //Check reachable & not blocked behind & not path blocked
        pathCandidates.removeIf(new Predicate<Piece>() {
            @Override
            public boolean test(Piece otherPiece) {

                //Left not blocked & path exists from left
                if (!otherPiece.pieceExistsBehindLeft()) {
                    GridPoint halfWayPoint = getPathHalfwayPoint(otherPiece, true);

                    //@@TODO: FIGURE OUT WHATS WRONG WITH HALF WAY POINT!!

                    if (halfWayPoint == null) {
                        return true;
                    }
                    if (CheckerSolver3.PieceObstructsPath(Piece.this.getGridPoint(), halfWayPoint)) {
                        return true;
                    }
                    if (CheckerSolver3.PieceObstructsPath(halfWayPoint, otherPiece.getGridPoint())) {
                        return true;
                    }
                    return false;
                }
                if (!otherPiece.pieceExistsBehindRight()) {
                    GridPoint halfWayPoint = getPathHalfwayPoint(otherPiece, false);
                    if (halfWayPoint == null) {
                        return true;
                    }
                    if (CheckerSolver3.PieceObstructsPath(Piece.this.getGridPoint(), halfWayPoint)) {
                        return true;
                    }
                    if (CheckerSolver3.PieceObstructsPath(halfWayPoint, otherPiece.getGridPoint())) {
                        return true;
                    }
                    return false;
                }
                return true;
            }
        });

        return maxPathValue + this.getValue();
    }

    public GridPoint getPathHalfwayPoint(Piece otherPiece, boolean fromLeft) {
        int diffX = this.x - otherPiece.x;
        int diffY = this.y - otherPiece.y;
        int leftCount = (diffY - diffX) / 2;
        int rightCount = diffY - leftCount;

        if (fromLeft) {
            int midX = this.x - leftCount;
            int midY = this.y + leftCount;
            return new GridPoint(midX, midY);
        } else {
            int midX = this.x + rightCount;
            int midY = this.y + rightCount;
            return new GridPoint(midX, midY);
        }
    }

    public GridPoint getGridPoint() {
        return new GridPoint(x, y);
    }

    public String fullDesc() {
        return "Piece " + type + " at " + getGridPoint().desc();
    }
}

class GridPoint {
    int x;
    int y;
    public GridPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String desc() {
        return CheckerSolver3.getHashForPosition(x, y);
    }

    public boolean isToTheRightOf(GridPoint otherPoint) {
        return this.x > otherPoint.x;
    }
}