package GUI;


import javax.swing.*;
import java.awt.*;


public class Duck implements DuckInterface{

    int x;
    int y;
    int yVelocity = (int) (Math.random() * 2 + 1);
    int xVelocity = (int) (Math.random() * 2 + 2);
    int counter = (int) (Math.random() * 30);
    boolean dSide = true;
    boolean isAlive = true;
    int respawnTimer = 0;
    int passCounter = 0;
    int time = 0;
    int difficulty = 0;


    Image duck = new ImageIcon("resources/images/duckRight0.png").getImage();

    public Duck(int x , int y){

        this.x = x;
        this.y = y;
    }

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
                    x = -100;
                    y = (int) (Math.random() * 250 + 60);
                }
                y += yVelocity;
                counter++;
                if (counter >= (Math.random() * 20 + 30)) {
                    yVelocity = yVelocity * -1;
                    counter = 0;
                    duck = new ImageIcon("resources/images/duckRight1.png").getImage();
                }
                if (counter >= 10) {
                    duck = new ImageIcon("resources/images/duckRight0.png").getImage();
                }
                x = x + xVelocity + difficulty + MyPanel.getDifficulty();

            } else {
                if (x <= -80) {
                    passCounter++;
                    x = 900;
                    y = (int) (Math.random() * 250 + 60);
                }
                y += yVelocity;
                counter++;
                if (counter >= (Math.random() * 20 + 30)) {
                    yVelocity = yVelocity * -1;
                    counter = 0;
                    duck = new ImageIcon("resources/images/duckLeft1.png").getImage();
                }
                if (counter >= 10) {
                    duck = new ImageIcon("resources/images/duckLeft0.png").getImage();
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


    public void render(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(duck, x, y, null);


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
