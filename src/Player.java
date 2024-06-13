import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Player {
    public ArrayList<Piece> capturedPieces = new ArrayList<>();
    public Player() {
    }

    public void addCapturedPiece(Piece p) {
        capturedPieces.add(p);
    }

    public int getScore() {
        int count = 0;
        for(Piece p: capturedPieces) {
            count += p.getValue();
        }
        return count;
    }
}
