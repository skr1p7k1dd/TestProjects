package com.alex.rakuten.CheckerSolver.v2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Predicate;

/**
 * Created by Alex on 10/23/2017.
 */

public class Solution {

    static HashMap<String, Piece> allPiecesHashMap = null;
    static ArrayList<Piece> allPieces = null;
    static Piece ourPiece = null;

//    static boolean DEBUG_MODE = true;
    static boolean DEBUG_MODE = false;

    public static void main(String[] args) {

        System.out.println("STARTING TESTS");

        int[] X1    = {3, 5, 1, 6};
        int[] Y1    = {1, 3, 3, 8};
        String T1   = "Xpqp";
        int answer1 = solution(X1, Y1, T1);
        int expectedAnswer1 = 10;
        if (answer1 != expectedAnswer1) {
            System.out.println("ANSWER 1 IS: " + answer1 + " and it should be: " + expectedAnswer1);
        }

        int[] X2    = {0, 3, 5, 1, 6};
        int[] Y2    = {4, 1, 3, 3, 8};
        String T2   = "pXpqp";
        int answer2 = solution(X2, Y2, T2);
        int expectedAnswer2 = 2;
        if (answer2 != expectedAnswer2) {
            System.out.println("ANSWER 2 IS: " + answer2 + " and it should be: " + expectedAnswer2);
        }

        int[] X3    = {0, 6, 2, 5, 3, 0};
        int[] Y3    = {4, 8, 2, 3, 1, 6};
        String T3   = "ppqpXp";
        int answer3 = solution(X3, Y3, T3);
        int expectedAnswer3 = 12;
        if (answer3 != expectedAnswer3) {
            System.out.println("ANSWER 3 IS: " + answer3 + " and it should be: " + expectedAnswer3);
        }

        int[] X4    = {0, 1};
        int[] Y4    = {0, 1};
        String T4   = "Xp";
        int answer4 = solution(X4, Y4, T4);
        int expectedAnswer4 = 1;
        if (answer4 != expectedAnswer4) {
            System.out.println("Custom 4: " + answer4 + " and it should be: " + expectedAnswer4);
        }

        int[] X5    = {0, 1, 2};
        int[] Y5    = {0, 1, 2};
        String T5   = "Xpp";
        int answer5 =  solution(X5, Y5, T5);
        int expectedAnswer5 = 0;
        if (answer5 != expectedAnswer5) {
            System.out.println("Custom 5: " + answer5 + " and it should be: " + expectedAnswer5);
        }

        int[] X6    = {0, 1, 3};
        int[] Y6    = {0, 1, 3};
        String T6   = "Xpp";
        int answer6 = solution(X6, Y6, T6);
        int expectedAnswer6 = 2;
        if (answer6 != expectedAnswer6) {
            System.out.println("Custom 6: " + answer6 + " and it should be: " + expectedAnswer6);
        }

        int[] X7    = {0, 1, 3, 4};
        int[] Y7    = {0, 1, 3, 4};
        String T7   = "Xppp";
        int answer7 = solution(X7, Y7, T7);
        int expectedAnswer7 = 1;
        if (answer7 != expectedAnswer7) {
            System.out.println("Custom 7: " + answer7 + " and it should be: " + expectedAnswer7);
        }

        int[] X8    = {0, 1, 3, 4, 0};
        int[] Y8    = {0, 1, 3, 4, 4};
        String T8   = "Xpppq";
        int answer8 = solution(X8, Y8, T8);
        int expectedAnswer8 = 11;
        if (answer8 != expectedAnswer8) {
            System.out.println("Custom 8: " + answer8 + " and it should be: " + expectedAnswer8);
        }

        int[] X9    = {0, 1, 3, 4, 0};
        int[] Y9    = {0, 1, 3, 4, 10};
        String T9   = "Xpppq";
        int answer9 = solution(X9, Y9, T9);
        int expectedAnswer9 = 1;
        if (answer9 != expectedAnswer9) {
            System.out.println("Custom 9: " + answer9 + " and it should be: " + expectedAnswer9);
        }

        int[] X10    = {0, 1, 3, 0};
        int[] Y10    = {0, 1, 3, 10};
        String T10   = "Xppq";
        int answer10 = solution(X10, Y10, T10);
        int expectedAnswer10 = 12;
        if (answer10 != expectedAnswer10) {
            System.out.println("Custom 10: " + answer10 + " and it should be: " + expectedAnswer10);
        }

        int[] X11    = {0, 1, 3, 1};
        int[] Y11    = {0, 1, 3, 10};
        String T11   = "Xppq";
        int answer11 = solution(X11, Y11, T11);
        int expectedAnswer11 = 2;
        if (answer11 != expectedAnswer11) {
            System.out.println("Custom 11: " + answer11 + " and it should be: " + expectedAnswer11);
        }

        int[] X12    = {0, 1, 2, 1};
        int[] Y12    = {0, 1, 2, 10};
        String T12   = "Xppq";
        int answer12 = solution(X12, Y12, T12);
        int expectedAnswer12 = 0;
        if (answer12 != expectedAnswer12) {
            System.out.println("Custom 12: " + answer12 + " and it should be: " + expectedAnswer12);
        }

        int[] X13    = {10, 12};
        int[] Y13    = {10, 10};
        String T13   = "Xp";
        int answer13 = solution(X13, Y13, T13);
        int expectedAnswer13 = 0;
        if (answer13 != expectedAnswer13) {
            System.out.println("Custom 13: " + answer13 + " and it should be: " + expectedAnswer13);
        }

        int[] X14    = {5,4,3,3,2,0,2};
        int[] Y14    = {5,2,3,5,4,0,6};
        String T14   = "pppqpXp";
        int answer14 = solution(X14, Y14, T14);
        int expectedAnswer14 = 2;
        if (answer14 != expectedAnswer14) {
            System.out.println("Custom 14: " + answer14 + " and it should be: " + expectedAnswer14);
        }

        int[] X15    = {5,4,3,3,2,0};
        int[] Y15    = {5,2,3,5,4,0};
        String T15   = "pppqpXp";
        int answer15 = solution(X15, Y15, T15);
        int expectedAnswer15 = 11;
        if (answer15 != expectedAnswer15) {
            System.out.println("Custom 15: " + answer15 + " and it should be: " + expectedAnswer15);
        }

        int[] X16    = {2,3,4,5,5,6,6,7,8,9};
        int[] Y16    = {6,5,4,7,9,6,8,5,8,9};
        String T16   = "Xpppppppppppppppppppppppppppppppppp";
        int answer16 = solution(X16, Y16, T16);
        int expectedAnswer16 = 1;
        if (answer16 != expectedAnswer16) {
            System.out.println("Custom 16: " + answer16 + " and it should be: " + expectedAnswer16);
        }

        int[] X17    = {4,5,7,9};
        int[] Y17    = {0,1,3,5};
        String T17   = "Xpppppppppppppppppppppppppppppppppp";
        int answer17 = solution(X17, Y17, T17);
        int expectedAnswer17 = 3;
        if (answer17 != expectedAnswer17) {
            System.out.println("Custom 17: " + answer17 + " and it should be: " + expectedAnswer17);
        }

        int[] X18    = {4,5,7,9,9,9,7,10};
        int[] Y18    = {0,1,3,5,7,9,11,10};
        String T18   = "Xpppppppppppppppppppppppppppppppppp";
        int answer18 = solution(X18, Y18, T18);
        int expectedAnswer18 = 5;
        if (answer18 != expectedAnswer18) {
            System.out.println("Custom 18: " + answer18 + " and it should be: " + expectedAnswer18);
        }

        int[] X19    = {4,4,5,7,9,9,9,7};
        int[] Y19    = {0,10,1,3,5,7,9,11};
        String T19   = "Xqppppppppppppp";
        int answer19 = solution(X19, Y19, T19);
        int expectedAnswer19 = 5;
        if (answer19 != expectedAnswer19) {
            System.out.println("Custom 19: " + answer19 + " and it should be: " + expectedAnswer19);
        }

        int[] X20    = {4,1,2,2,3,5,5,5,5,6,6,7,8,7,10,11};
        int[] Y20    = {0,7,6,2,1,1,3,7,9,6,8,3,4,9,10,11};
        String T20   = "Xqppppppppppppppppppppppppppp";
        int answer20 = solution(X20, Y20, T20);
        int expectedAnswer20 = 4;
        if (answer20 != expectedAnswer20) {
            System.out.println("Custom 20: " + answer20 + " and it should be: " + expectedAnswer20);
        }

        System.out.println("ALL TESTS FINISHED");
    }

    public static int solution(int[] x, int[] y, String T) {

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
                else
                    return -1;
            }
        });


        //Remove unreachable pieces
        allPieces.removeIf(new Predicate<Piece>() {
            @Override
            public boolean test(Piece otherPiece) {
                return !ourPiece.validPathExistsTo(otherPiece);
            }
        });

        //Set index for faster finding
        for(int i = 0; i < allPieces.size(); i++) {
            Piece p = allPieces.get(i);
            p.pieceIndex = i;
        }


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
            //
            else if ((ourPiece.x - piece.x) == (piece.y - ourPiece.y)) {
                if (piece.x < ourPiece.x) {
                    if (firstLeftPiece == null) {
                        firstLeftPiece = piece;
                    }
                }
            }
        }

        if (firstLeftPiece == null && firstRightPiece == null) {
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
            maxLeftPathValue = firstLeftPiece.getMaxPathValue(true);
        }
        if (firstRightPiece!= null) {
            maxRightPathValue = firstRightPiece.getMaxPathValue(false);
        }

        return Math.max(maxLeftPathValue, maxRightPathValue);
    }

    public static boolean PieceExistsAt(int x, int y) {

        Piece p = allPiecesHashMap.get(getHashForPosition(x, y));
        boolean exists = p != null;
        DebugPrint("PieceExistsAt: " + x + "," + y + " = " + exists);
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
        for (int i = p.pieceIndex + 1; i < allPieces.size(); i++) {
            Piece thisPiece = allPieces.get(i);
            if (thisPiece.y > p.y) {
                rList.add(thisPiece);
                DebugPrint("GetAllPiecesAfter " + p.getGridPoint().desc() + " is including " + thisPiece.getGridPoint().desc());
            } else {
                DebugPrint("GetAllPiecesAfter " + p.getGridPoint().desc() + " is EXCLUDING " + thisPiece.getGridPoint().desc());
            }
        }
        return rList;
    }

    public static boolean PieceObstructsPath(GridPoint start, GridPoint end, Piece pieceToIgnore) {

        GridPoint a = start;
        GridPoint b = end;
        if (start.y > end.y) {
            a = end;
            b = start;
        }
        DebugPrint(" Checking for obstruction between " + a.desc() + " and " + b.desc());

        if (b.isToTheRightOf(a)) {
            if (b.y - a.y != b.x - a.x) {
                DebugPrint("CRITICAL ERROR IN PieceObstructsPath-a. No path between " + a.desc() + " and " + b.desc());
                return false;
            }

            for (int xi = 1; xi <= b.x - a.x; xi++) {

                Piece possibleExistingPiece = allPiecesHashMap.get(getHashForPosition(a.x + xi, a.y + xi));
                if (possibleExistingPiece != null && possibleExistingPiece != pieceToIgnore) {
                    DebugPrint("  Checking pt " + (a.x + xi) + " and " + (a.y + xi) + " OBSTRUCTION");
                    return true;
                } else {
                    DebugPrint("  Checking pt " + (a.x + xi) + " and " + (a.y + xi) + " clear");
                }
            }
        } else {

            if (b.y - a.y != a.x - b.x) {
                DebugPrint("CRITICAL ERROR IN PieceObstructsPath-b. No path between " + a.desc() + " and " + b.desc());
                return false;
            }

            for (int xi = 1; xi <= a.x - b.x; xi++) {

                Piece possibleExistingPiece = allPiecesHashMap.get(getHashForPosition(a.x - xi, a.y + xi));
                if (possibleExistingPiece != null && possibleExistingPiece != pieceToIgnore) {
                    DebugPrint("  Checking pt " + (a.x - xi) + " and " + (a.y + xi) + " OBSTRUCTON");
                    return true;
                } else {
                    DebugPrint("  Checking pt " + (a.x - xi) + " and " + (a.y + xi) + " clear");
                }
            }
        }
        DebugPrint(" No obstruction in path between " + start.desc() + " and " + end.desc());
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
        return Solution.getHashForPosition(this.x, this.y);
    }


    public boolean pieceExistsBehindLeft() {
        return Solution.PieceExistsAt(this.x-1, this.y+1);
    }
    public boolean pieceExistsBehindRight() {
        return Solution.PieceExistsAt(this.x+1, this.y+1);
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
    public int getMaxPathValue(final boolean goingLeft) {

        //Get list of pieces we will try to path to
        ArrayList<Piece> pathCandidates = Solution.GetAllPiecesAfter(this);

        //Check reachable & not blocked behind & not path blocked
        pathCandidates.removeIf(new Predicate<Piece>() {
            @Override
            public boolean test(Piece otherPiece) {

                if (goingLeft) {

                    //Check to make sure this piece isn't blocked on this side
                    if (Piece.this.pieceExistsBehindLeft()) {
                        return true;
                    }

                    GridPoint halfWayPoint = Piece.this.getPathHalfwayPoint(otherPiece, true);

                    if (halfWayPoint == null) {
                        return true;
                    }
                    Solution.DebugPrint("LEFT half way pt between " + Piece.this.getGridPoint().desc() + " and " + otherPiece.getGridPoint().desc() + " is " + halfWayPoint.desc());

                    //If straight shot, check left side. Else check right
                    if (halfWayPoint.isEqualTo(otherPiece.getGridPoint())) {
                        if (otherPiece.pieceExistsBehindLeft()) {
                            return true;
                        }
                    } else {
                        if (otherPiece.pieceExistsBehindRight()) {
                            return true;
                        }
                    }

                    //Check for obstructions on the way
                    if (Solution.PieceObstructsPath(Piece.this.getGridPoint(), halfWayPoint, otherPiece)) {
                        return true;
                    }
                    if (Solution.PieceObstructsPath(halfWayPoint, otherPiece.getGridPoint(), otherPiece)) {
                        return true;
                    }

                    Solution.DebugPrint("     " + Piece.this.getGridPoint().desc() + " CAN reach " + otherPiece.getGridPoint().desc() + " going left");
                    return false;

                } else {

                    //Check to make sure this piece isn't blocked on this side
                    if (Piece.this.pieceExistsBehindRight()) {
                        return true;
                    }

                    GridPoint halfWayPoint = Piece.this.getPathHalfwayPoint(otherPiece, false);

                    if (halfWayPoint == null) {
                        return true;
                    }
                    Solution.DebugPrint("RIGHT half way pt between " + Piece.this.getGridPoint().desc() + " and " + otherPiece.getGridPoint().desc() + " is " + halfWayPoint.desc());

                    //If straight shot, check left side. Else check right
                    if (halfWayPoint.isEqualTo(otherPiece.getGridPoint())) {
                        if (otherPiece.pieceExistsBehindRight()) {
                            return true;
                        }
                    } else {
                        if (otherPiece.pieceExistsBehindLeft()) {
                            return true;
                        }
                    }

                    //Check for obstructions on the way. Pieces cant obstruct themselves
                    if (Solution.PieceObstructsPath(Piece.this.getGridPoint(), halfWayPoint, otherPiece)) {
                        return true;
                    }
                    if (Solution.PieceObstructsPath(halfWayPoint, otherPiece.getGridPoint(), otherPiece)) {
                        return true;
                    }

                    Solution.DebugPrint("     " + Piece.this.getGridPoint().desc() + " CAN reach " + otherPiece.getGridPoint().desc() + " going right");
                    return false;

                }
            }
        });


        //max value is max value of any viable candidate plus its value
        int maxPathValue = 0;
        //Enumerate candidates to find the max path value
        for(Piece candidate : pathCandidates) {

            //If the path curved, switch sides when calculating sub max path value
            int candidateMaxPathValue;
            GridPoint halfWayPt = this.getPathHalfwayPoint(candidate, goingLeft);
            if (halfWayPt.isEqualTo(candidate.getGridPoint()) || halfWayPt.isEqualTo(this.getGridPoint())) {
                candidateMaxPathValue = candidate.getMaxPathValue(goingLeft);
            } else {
                candidateMaxPathValue = candidate.getMaxPathValue(!goingLeft);
            }

            if (candidateMaxPathValue > maxPathValue) {
                maxPathValue = candidateMaxPathValue;
            }
        }

        return maxPathValue + this.getValue();
    }

    //1,3 -> 6,3    --> none!
    public GridPoint getPathHalfwayPoint(Piece otherPiece, boolean fromLeft) {
        int diffX = otherPiece.x - this.x;
        int diffY = otherPiece.y - this.y;

        if (diffY < 0) {
            Solution.DebugPrint("Critical error in getPathHalfwayPoint() - otherPiece is BELOW this one!");
        }

        //If there is no path, return null
        if (!this.validPathExistsTo(otherPiece)) {
            return null;
        }

        if (fromLeft) {
            int leftCount = (diffY - diffX) / 2;
            int rightCount = diffY - leftCount;

            if (leftCount == 0) {
                return null;
            }

            int midX = this.x - leftCount;
            int midY = this.y + leftCount;
            return new GridPoint(midX, midY);
        } else {
            int rightCount = (diffY + diffX) / 2;
            int leftCount = diffY - rightCount;

            if (rightCount == 0) {
                return null;
            }

            int midX = this.x + rightCount;
            int midY = this.y + rightCount;
            return new GridPoint(midX, midY);
        }
    }

    public boolean validPathExistsTo(Piece otherPiece) {
        //Lateral or behind...
        if (otherPiece.y <= this.y) {
            Solution.DebugPrint("No valid path (a) exists from " + this.getGridPoint().desc() + " to " + otherPiece.getGridPoint().desc());
            return false;
        }

        //Make sure its on the right grid system (black & black or white & white)
        if ((this.x + this.y) % 2 != (otherPiece.x + otherPiece.y) % 2) {
            return false;
        }

        //remove if its outside the left or right bound of this piece
        if (Math.abs(otherPiece.x - this.x) > (otherPiece.y - this.y)) {
            Solution.DebugPrint("No valid path (b) exists from " + this.getGridPoint().desc() + " to " + otherPiece.getGridPoint().desc());
            return false;
        }

        return true;
    }

//    public boolean isImmediatelyAdjacentTo(Piece otherPiece) {
//        if (this.y + 1 == otherPiece.y || this.y - 1 == otherPiece.y) {
//            if (this.x - 1 == otherPiece.x || this.x + 1 == otherPiece.x) {
//                return true;
//            }
//        }
//        return false;
//    }

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
        return Solution.getHashForPosition(x, y);
    }

    public boolean isToTheRightOf(GridPoint otherPoint) {
        return this.x > otherPoint.x;
    }

    public boolean isEqualTo(GridPoint otherPt) {
        return this.x == otherPt.x && this.y == otherPt.y;
    }
}