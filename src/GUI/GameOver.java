package GUI;

import java.awt.*;

public class GameOver {
    public Rectangle playButton = new Rectangle(320, 170, 180, 50);
    public Rectangle scoreButton = new Rectangle(320, 250, 180, 50);
    public Rectangle exitButton = new Rectangle(360, 350, 100, 50);

    public void render(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        Font font = new Font("impact", Font.PLAIN, 50);
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        g2.drawString("GAME OVER",300,100);
        Font font1 = new Font("impact", Font.PLAIN, 30);
        g2.setFont(font1);
        g2.drawString("SAVE SCORE", playButton.x+18, playButton.y+ 36);
        g2.drawString("PLAY AGAIN", scoreButton.x+15, scoreButton.y+36);
        g2.drawString("EXIT", exitButton.x+26, exitButton.y+36);
        g2.draw(playButton);
        g2.draw(exitButton);
        g2.draw(scoreButton);
    }
}
