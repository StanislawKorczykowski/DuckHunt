package GUI;

import javax.swing.*;
import java.awt.*;

public class Cloud {

    int x;
    int y;
    int yVelocity = 0;
    int xVelocity = 2;
    int counter = 0;
    boolean cSide;

    Image cloud = new ImageIcon("resources/images/cloud.png").getImage();

    public Cloud(int x , int y, boolean cSide){
        this.cSide = cSide;
        this.x = x;
        this.y = y;
    }

    public void update() {
        if (cSide) {
            if (x >= 800) {
                x = -100;
                y = (int) (Math.random() * 50 + 60);
            }
            y += yVelocity;
            counter++;

            x = x + xVelocity;

            } else {
                if (x <= -80) {
                    x = 900;
                    y = (int) (Math.random() * 150 + 60);
                }
                y += yVelocity;
                counter++;
                if (counter >= (Math.random() * 20 + 30)) {
                    yVelocity = yVelocity * -1;
                    counter = 0;

                }
                x = x - xVelocity;

            }

    }


    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(cloud, x, y, null);
    }
}
