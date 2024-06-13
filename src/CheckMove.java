import java.util.Arrays;
import java.awt.*;

public class CheckMove {

    private static int changeY, changeX, Turn;
    private static int[] Start, End;

    public static boolean isValidMove(int[] start, int[] end, int turn){
        Start = start;
        End = end;
        Turn = turn;

        //System.out.println("Same Color Check: " + (Board.get(start).getColor() == Board.get(end).getColor()));
        if(Board.get(start).getPieceColor() == Board.get(end).getPieceColor()) {
            return false;
        }
        if(checkColor(start, Color.white, 1) || checkColor(start, Color.black, 2)) {
            return false;
        }
        changeY = end[1] - start[1];
        changeX = end[0] - start[0];
        //System.out.println("ChangeX: " + changeX + "\nChangeY: " + changeY);

        switch(Board.get(start).getPiece()){
            case pawn:
                if((turn == 1 && changeY > 0) || (turn == 2 && changeY < 0))
                    return false;
                //System.out.println("Pawn special check: " + (VerticalCheck(start, end, turn)));
                if((Math.abs(changeX) == 1 && Math.abs(changeY) == 1) && (turn == 1 && checkPiece(end, Color.black) || turn == 2 && checkPiece(end, Color.white)))
                    return DiagonalCheck(start, end, turn);
                if(changeX == 0 && Math.abs(changeY) < 3 && Board.get(start).getTimesMoved() == 0)
                    return VerticalCheck(start, end, turn);
                if(changeX == 0 && Math.abs(changeY) == 1 && ((checkColor(new int[]{start[0] + changeY, start[1]}, Color.black, 1)) || (checkColor(new int[]{start[0] + changeY, start[1]}, Color.white, 2))))
                    return VerticalCheck(start, end, turn);
                return false;
            case queen:
                //if(!(Math.abs(changeX) != Math.abs(changeY) && Math.abs(changeX) + Math.abs(changeY) == 3)) {
                    if (changeY == 0)
                        return HorizontalCheck(start, end, turn);
                    else if (changeX == 0)
                        return VerticalCheck(start, end, turn);
                    else if (Math.abs(changeX) == Math.abs(changeY))
                        return DiagonalCheck(start, end, turn);
                //}
                return false;
            case king:
                if (changeX == 0 && Math.abs(changeY) == 1)
                    return VerticalCheck(start, end, turn);
                if(changeY == 0 && Math.abs(changeX) == 1)
                    return HorizontalCheck(start, end, turn);
                if(Math.abs(changeX) == 1 && Math.abs(changeY) == 1)
                    return DiagonalCheck(start, end, turn);
                return false;
            case bishop:
                if(Math.abs(changeX) == Math.abs(changeY))
                    return DiagonalCheck(start, end, turn);
                return false;
            case knight:
                if(Math.abs(changeX) != Math.abs(changeY) && Math.abs(changeX) + Math.abs(changeY) == 3)
                    return KnightCheck(end, turn);
                return false;
            case rook:
                if(changeY == 0)
                    return HorizontalCheck(start, end, turn);
                if(changeX == 0)
                    return VerticalCheck(start, end, turn);
                return false;
            default:
                return false;
        }

    }

    public static boolean KnightCheck(int[] end, int player){
        return checkColor(end, Color.white, 1) || checkColor(end, Color.black, 2);
    }

    public static boolean HorizontalCheck(int[] start, int[] end, int player){
        //If the piece moves all the way to the end it is a true move
        if(Arrays.equals(start, end))
            return true;

        //Checks to see if the next start spot is available
        if(!checkPiece(start, Color.red) && !Arrays.equals(Start, start))
            return false;

        //Left [0,-]
        if(changeX < 0)
            return HorizontalCheck(new int[]{start[0] - 1, start[1]}, end, player);
        //Right [0,+]
        if(changeX > 0)
            return HorizontalCheck(new int[]{start[0] + 1, start[1]}, end, player);

        return false;
    }

    public static boolean VerticalCheck(int[] start, int[] end, int player){
        //If the piece moves all the way to the end it is a true move
        if(Arrays.equals(start, end))
            return true;

        //Checks to see if the next start spot is available
        if(!checkPiece(start, Color.red) && !Arrays.equals(Start, start))
            return false;

        //Up [-, 0]
        if(changeY < 0)
            return VerticalCheck(new int[]{start[0], start[1] - 1}, end, player);
        //Down [+, 0]
        if(changeY > 0)
            return VerticalCheck(new int[]{start[0], start[1] + 1}, end, player);
        return false;
    }

    public static boolean DiagonalCheck(int[] start, int[] end, int player){
        //If the piece moves all the way to the end it is a true move
        if(Arrays.equals(start, end)) //(x, y) but thinks (y, x)
            return true;

        //Checks to see if the next start spot is available
        if(!checkPiece(start, Color.red) && !Arrays.equals(Start, start))
            return false;

        //Top Right [-,+]
        if(changeY < 0 && changeX > 0) {
            return DiagonalCheck(new int[]{start[0] + 1, start[1] - 1}, end, player);
        }
        //Top Left [-,-]
        if(changeY < 0 && changeX < 0) {
            return DiagonalCheck(new int[]{start[0] - 1, start[1] - 1}, end, player);
        }
        //Bottom Left [+, -]
        if(changeY > 0 && changeX < 0) {
            return DiagonalCheck(new int[]{start[0] - 1, start[1] + 1}, end, player);
        }
        //Bottom Right [+,+]
        if(changeY > 0 && changeX > 0) {
            return DiagonalCheck(new int[]{start[0] + 1, start[1] + 1}, end, player);
        }

        return false;
    }

    public static boolean checkPiece(int[] location, Color c){
        return Board.board[location[1]][location[0]].getPieceColor() == c;
    }

    public static boolean checkColor(int[] location, Color c, int turn){
        return Turn == turn && !checkPiece(location, c);
    }
}