package GUI;

import java.awt.*;

public class DifficultySelect {
    public Rectangle playButton = new Rectangle(360, 150, 100, 50);
    public Rectangle scoreButton = new Rectangle(350, 250, 120, 50);
    public Rectangle exitButton = new Rectangle(360, 350, 100, 50);

    public void render(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("impact", Font.PLAIN, 50);
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        g2.drawString("SELECT DIFFICULTY",250,100);
        Font font1 = new Font("impact", Font.PLAIN, 30);
        g2.setFont(font1);
        g2.drawString("EASY", playButton.x+23, playButton.y+36);
        g2.drawString("MEDIUM", scoreButton.x+12, scoreButton.y+36);
        g2.drawString("HARD", exitButton.x+20, exitButton.y+36);
        g2.draw(playButton);
        g2.draw(scoreButton);
        g2.draw(exitButton);

    }
}
