import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Player {
    private final double Speed = 0.2;
    private BufferedImage right;
    private BufferedImage left;
    private boolean facingRight;
    private double xCoord;
    private double yCoord;
    private int score;
    public Player(String spriteL, String spriteR) {
        facingRight = true;
         xCoord = 50;
         yCoord = 435;
         score = 0;
        try {
            left = ImageIO.read(new File(spriteL));
            right = ImageIO.read(new File(spriteR));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    public int getxCoord() {
        return (int) xCoord;
    }

    public int getyCoord() {
        return (int) yCoord;
    }

    public int getScore() {
        return score;
    }

    public void faceRight() {
        facingRight = true;
    }

    public void faceLeft() {
        facingRight = false;
    }

    public void moveRight() {
        if (xCoord + Speed <= 920) {
            xCoord += Speed;
        }
    }

    public void moveLeft() {
        if (xCoord - Speed >= 0) {
            xCoord -= Speed;
        }
    }

    public void moveUp() {
        if (yCoord - Speed >= 0) {
            yCoord -= Speed;
        }
    }

    public void moveDown() {
        if (yCoord + Speed <= 435) {
            yCoord += Speed;
        }
    }

    public void collectCoin() {
        score++;
    }

    public BufferedImage getPlayerImage() {
        if (facingRight) {
            return right;
        } else {
            return left;
        }
    }

    // we use a "bounding Rectangle" for detecting collision
    public Rectangle playerRect() {
        int imageHeight = getPlayerImage().getHeight();
        int imageWidth = getPlayerImage().getWidth();
        Rectangle rect = new Rectangle((int) xCoord, (int) yCoord, imageWidth, imageHeight);
        return rect;
    }
    
}
