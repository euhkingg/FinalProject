import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GraphicsPanel extends JPanel implements MouseListener, MouseMotionListener {
    HashMap<Integer, Color> turnChecker= new HashMap<Integer, Color>();
    boolean gameOver = false, invalidMove, player1Won = false, player2Won = false;
    private int player = 1, selected = 0;
    int[] start = new int[2], end = new int[2];
    Player p1 = new Player(), p2 = new Player();
    Board b;
    public GraphicsPanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        b = new Board();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // just do this
        g.setColor(Color.white);
        g.setFont(new Font("Times New Roman", Font.BOLD, 50));
        g.drawString("Chess", 900, 100);
        for (Tile[] tiles : b.getBoard()) {
            for (int x = 0; x < b.getBoard()[0].length; x++) {
                Tile t = tiles[x];
                g.drawImage(t.getTileImage(), 50 + (t.getX() * 96), 70 + (t.getY() * 72), null);
                g.drawImage(b.getPieceImage(t.getX(), t.getY()), 56+ (t.getX() * 96), 18 + (t.getY() * 72), null);
            }
            b.getBoard()[0][0].changeIsWhite();
        }
        for (int i = 0; i < 8; i++) {
            g.drawImage(b.getBoard()[0][0].getEnd(), 44 + (i * 97), 648, null);
        }
        repaint();
    }

    // ----- MouseListener interface methods -----
    public void mouseMoved(MouseEvent e) {
        if(!gameOver) {
            for (Tile[] ts : b.getBoard()) {
                for (Tile t : ts) {
                    t.hovered = (e.getX() > t.getXPixel() && e.getX() < t.getXPixel() + t.getLength()) && (e.getY() > t.getYPixel() + t.getWidth() && e.getY() < t.getYPixel() + (t.getWidth() * 2));
                }
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        if (!gameOver) {
            for (Tile[] y : b.getBoard()) {
                for (Tile t : y) {
                    if ((e.getX() > t.getXPixel() && e.getX() < t.getXPixel() + t.getLength()) && (e.getY() > t.getYPixel() + t.getWidth() && e.getY() < t.getYPixel() + (t.getWidth() * 2))) {
                        t.setChosen(!t.getChosen());
                        if (t.chosen) {//If you chose a spot then add 1 to the selected count
                            if (selected < 2) {//If the total amount selected is less than 2 then add 1 to select
                                if (selected == 0) {
                                    start = new int[]{t.getX(), t.getY()};
                                } else {
                                    end = new int[]{t.getX(), t.getY()};
                                }
                                selected++;
                            } else {//If you chose more than 2 it will turn off what you just picked
                                t.chosen = false;
                            }
                        } else {// When you chose you pick the same spot you turn off the spot and make the amount selected to 1 less
                            selected--;
                        }

                        if (selected == 2) {
                            //System.out.println("Start: " + start[0] + ", " + start[1] + "\nEnd: " + end[0] + ", " + end[1]);
                            if (CheckMove.isValidMove(start, end, player)) { //CheckMove.isValidMove(start, end, player)
                                invalidMove = false;
                                //Updates the current tile to show that it was moved\\
                                Board.get(start).moved();

                                //Adds The Piece Captured to the captured piece list\\
                                if (Board.get(end).getPiece() != Piece.none) {
                                    if (player % 2 == 1) {
                                        p2.capturedPieces.add(Board.get(end).getPiece());
                                        if (Board.get(end).getPiece() == Piece.king) {
                                            player1Won = true;
                                            gameOver = true;
                                        }
                                        p1.capturedPieces.add(Board.get(end).getPiece());
                                    } else {
                                        p2.capturedPieces.add(Board.get(end).getPiece());
                                        if (Board.get(end).getPiece() == Piece.king) {
                                            player2Won = true;
                                            gameOver = true;
                                        }
                                    }
                                }

                                Board.get(end).change(Board.get(start));
                                Board.get(start).reset();
                                player++;
                                if (player == 3) {
                                    player = 1;
                                }
                            } else {
                                invalidMove = true;
                            }
                            Board.get(end).chosen = false;
                            Board.get(start).chosen = false;
                            selected = 0;
                        }
                    }
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) { } // unimplemented

    public void mouseExited(MouseEvent e) { } // unimplemented

    public void mouseDragged(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) { }
}