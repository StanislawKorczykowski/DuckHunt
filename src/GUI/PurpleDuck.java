package GUI;

import javax.swing.*;
import java.awt.*;

public class PurpleDuck implements DuckInterface{


    int x;
    int y;
    int yVelocity = (int) (Math.random() * 2 + 1);
    int xVelocity = (int) (Math.random() * 2 + 2);
    int counter = (int) (Math.random() * 30);
    boolean dSide = true;
    boolean isAlive = true;
    int respawnTimer = 0;
    int passCounter = 0;
    int hp;
    int time = 0;
    int difficulty = 0;


    Image duck = new ImageIcon("resources/images/duckRight0purple.png").getImage();

    public PurpleDuck(int x , int y, int hp){
        this.hp = hp;
        this.x = x;
        this.y = y;
    }
    @Override
    public void update() {
        time++;
        if(time >= 333){
            difficulty++;
            time = 0;
        }
        if (isAlive) {
            if (dSide) {
                if (x >= 800) {
                    passCounter++;
                    hp = 5;
                    x = -100;
                    y = (int) (Math.random() * 250 + 60);
                }
                y += yVelocity;
                counter++;
                if (counter >= (Math.random() * 20 + 30)) {
                    yVelocity = yVelocity * -1;
                    counter = 0;
                    duck = new ImageIcon("resources/images/duckRight1purple.png").getImage();
                }
                if (counter >= 10) {
                    duck = new ImageIcon("resources/images/duckRight0purple.png").getImage();
                }

                x = x + xVelocity + difficulty + MyPanel.getDifficulty();

            } else {
                if (x <= -80) {
                    passCounter++;
                    hp = 5;
                    x = 900;
                    y = (int) (Math.random() * 250 + 60);
                }
                y += yVelocity;
                counter++;
                if (counter >= (Math.random() * 20 + 30)) {
                    yVelocity = yVelocity * -1;
                    counter = 0;
                    duck = new ImageIcon("resources/images/duckLeft1purple.png").getImage();
                }
                if (counter >= 10) {
                    duck = new ImageIcon("resources/images/duckLeft0purple.png").getImage();
                }

                x = x - xVelocity - difficulty - MyPanel.getDifficulty();

            }

        }
        if(!isAlive){

            respawnTimer++;

            if(respawnTimer >= 100){
                isAlive = true;
                respawnTimer = 0;
            }
        }
    }

    @Override
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(duck, x, y, null);
    }

    public int getPassCounter() {
        return passCounter;
    }
    public void setPassCounter() {
        this.passCounter = 0;
    }

    public void setDifficulty() {
        this.difficulty = 0;
    }
}

