import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private final double Speed = 0.2;
    private BufferedImage right;
    private BufferedImage left;
    private BufferedImage down;
    private BufferedImage up;
    private String facing;
    private double xCoord;
    private double yCoord;
    private int score;
    private int[] runFrames;
    private int[] runTiming;
    private Animation runL;
    private Animation runR;
    public Player(String spriteL, String spriteR, String spriteD, String spriteU) {
         facing = "Down";
         xCoord = 50;
         yCoord = 435;
         score = 0;
         runFrames = new int[]{1, 2, 7, 4, 5, 8};
         runTiming = new int[]{80,55,125,80,55,125};
        try {
            left = ImageIO.read(new File(spriteL));
            right = ImageIO.read(new File(spriteR));
            down = ImageIO.read(new File(spriteD));
            up = ImageIO.read(new File(spriteU));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        ArrayList<BufferedImage> run_animation = new ArrayList<>();
        for (int i = 0; i < runTiming.length; i++) {
            String filename = "src/Sprites/tile0" + (48 + runFrames[i]) + ".png";
            try {
                run_animation.add(ImageIO.read(new File(filename)));
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        runR = new Animation(run_animation, runTiming);
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
        facing = "Right";
    }

    public void faceUp() {facing = "Up";}

    public void faceDown() {facing = "Down";}

    public void faceLeft() { facing = "Left";}
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
        if (Objects.equals(facing, "Down")) {
            return down;
        } else if (Objects.equals(facing, "Up")){
            return up;
        } else if (Objects.equals(facing, "Right")) {
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
