

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class Board extends JPanel {
    public static Tile[][] board;
    private final HashMap<String, BufferedImage> pieceReference;

    public Board() {
        board = new Tile[8][8];
        pieceReference = new HashMap<>();

        String[] pieces = {"king", "queen", "rook", "bishop", "knight", "pawn"};
        String[] colors = {"w", "b", "r"};
        for (String piece : pieces) {
            for (String color : colors) {
                String x = color + piece;
                try {
                    pieceReference.put(x, ImageIO.read(new File("/Users/euhan/IdeaProjects/FinalProject/src/ChessPieces/" + x + ".png")));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        initializeBoard();
    }
    private void initializeBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new Tile(Piece.none, Color.red, col, row);
            }
        }

        for (int col = 0; col < 8; col++) {
            board[1][col].setPiece(Piece.pawn);
            board[1][col].setPieceColor(Color.black);
            board[6][col].setPiece(Piece.pawn);
            board[6][col].setPieceColor(Color.white);
        }

        Piece[] pieceArrangement = {
                Piece.rook, Piece.knight, Piece.bishop,Piece.queen,
                Piece.king, Piece.bishop, Piece.knight, Piece.rook
        };

        for (int col = 0; col < 8; col++) {
            board[0][col].setPiece(pieceArrangement[col]);
            board[0][col].setPieceColor(Color.black);
            board[7][col].setPiece(pieceArrangement[col]);
            board[7][col].setPieceColor(Color.white);
        }
    }

    public void set(int x, int y, Piece p, Color c) {
        board[y][x].setPiece(p);
        board[y][x].setPieceColor(c);
    }

    public static Tile get(int[] x) {
        return board[x[1]][x[0]];
    }

    public BufferedImage getPieceImage(int x, int y) {
        return pieceReference.get(board[y][x].getPieceImageKey());
    }

    public static Color getColor(int x, int y) {
        return board[y][x].getPieceColor();
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Tile[] tiles : board) {
            for (int x = 0; x < board[0].length; x++) {
                Tile t = tiles[x];
                g.drawImage(t.getTileImage(), 50 + (t.getX() * 96), 50 + (t.getY() * 77), null);
                g.drawImage(getPieceImage(t.getX(), t.getY()), 62 + (t.getX() * 96), 14 + (t.getY() * 77), null);
            }
        }
        for (int i = 0; i < 8; i++) {
            g.drawImage(board[0][0].getEnd(), 50 + (i * 96), 666, null);
        }
        repaint();
    }
}
