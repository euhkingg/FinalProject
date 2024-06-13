import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile {
    private static boolean isWhite;
    private Piece piece;
    private Color pieceColor;
    private BufferedImage black, white, red, end;
    private int x, y, timesMoved;
    public boolean hovered = false, chosen = false;
    public Tile(Piece piece, Color pieceColor, int x, int y) {
        isWhite = true;
        this.piece = piece;
        this.pieceColor = pieceColor;
        this.x = x;
        this.y = y;
        try {
            this.black = ImageIO.read(new File("/Users/euhan/IdeaProjects/FinalProject/src/ChessBoard/tile018.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            this.white = ImageIO.read(new File("/Users/euhan/IdeaProjects/FinalProject/src/ChessBoard/tile023.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            this.red = ImageIO.read(new File("/Users/euhan/IdeaProjects/FinalProject/src/ChessBoard/tile022.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            this.end = ImageIO.read(new File("/Users/euhan/IdeaProjects/FinalProject/src/ChessBoard/tile030.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setChosen(boolean x) {chosen = x;}

    public boolean getChosen() { return chosen;}

    public void setPiece(Piece p) {
        piece = p;
    }

    public Piece getPiece() {return piece;}

    public void setPieceColor(Color c) {
        pieceColor = c;
    }

    public int getX() {
        return x;
    }

    public int getXPixel() {
        return 50 + (x * 96) ;
    }

    public int getYPixel() {
        return 70 + (y * 72) ;
    }

    public int getY() {
        return y;
    }

    public int getLength() {
        return 96;}
    public int getWidth() {
        return 72;}

    public int getTimesMoved(){
        return timesMoved;
    }
    public void moved(){
        timesMoved++;
    }

    public String getPieceImageKey() {
        return pieceColor.name().charAt(0) + piece.name();
    }

    public Color getPieceColor() {
        return  pieceColor;
    }

    public void changeIsWhite() { isWhite = !isWhite;}

    public void reset(){
        this.timesMoved = 0;
        this.pieceColor = Color.red;
        this.piece = Piece.none;
    }

    public void change(Tile t){
        this.timesMoved = t.timesMoved;
        this.pieceColor = t.pieceColor;
        this.piece = t.piece;
    }

    public BufferedImage getTileImage() {
        if (hovered || chosen) {
            changeIsWhite();
            return red;
        }
        if (isWhite) {
            changeIsWhite();
            return white;
        }
        changeIsWhite();
        return black;
    }

    public BufferedImage getEnd() {
        return end;
    }

}
