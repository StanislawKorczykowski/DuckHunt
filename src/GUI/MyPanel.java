package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class MyPanel extends JPanel implements ActionListener {


    public final int PANEL_WIDTH = 800;
    final int PANEL_HEIGHT = 600;
    Image image;
    Image gameOver;
    Image tree;
    Timer timer;

    public static int scoreMultiplier = 0;
    static int gunDmg = 1;
    Font font = new Font("impact", Font.PLAIN, 20);
    int kCounter = 0;
    public static int difficulty = 1;
    public static int upgradeCost = 10;
    Rectangle rect = new Rectangle(609, 506, 180, 30);
    int time = 0;
    static int timeScore = 0;
    public static int score = 0;
    int seconds = 0;
    int minutes = 0;




    public enum STATE{
        MENU,
        GAME,
        DIFFICULTY,
        OVER,
        //SCORE
    }



    public static STATE state = STATE.MENU;


    private final Duck d1 = new Duck(-200,100);
    private final PurpleDuck d2 = new PurpleDuck(-600,150,5);
    private final RedDuck d3 = new RedDuck(-100,200, 3);
    private final PinkDuck d4 = new PinkDuck(-1000,250, 8);
    private final Duck d5 = new Duck(-1500,300);
    private final Cloud c1 = new Cloud(-500, 80,true);
    private final Cloud c2 = new Cloud(820, 30,false);

    private final Menu menu = new Menu();
    private final DifficultySelect diffS = new DifficultySelect();
    private final GameOver gOver = new GameOver();
    TimeSimulation ts = new TimeSimulation();


    public MyPanel(){
        image = new ImageIcon("resources/images/gameBackground.png").getImage();
        tree = new ImageIcon("resources/images/tree2.png").getImage();
        gameOver = new ImageIcon("resources/images/gameOver.png").getImage();
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        timer = new Timer(15,this);
        timer.start();



        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x1 = e.getX();
                int y1 = e.getY();

            if (state == STATE.MENU) {

                if(x1 >= 360 && x1 <=460) {
                    if (y1 >= 150 && y1 <= 200) {
                        state = STATE.DIFFICULTY;
                    }
                    if (y1 >= 250 && y1 <= 300) {
                        ArrayList<String> list = new ArrayList<>();

                        FileReader fileReader = null;
                        try {
                            fileReader = new FileReader("scoreBoard.txt");
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        assert fileReader != null;
                        Scanner scanner = new Scanner(fileReader);

                        while(scanner.hasNextLine()) {
                            list.add(scanner.nextLine());
                        }

                        JList<Object> highScores = new JList<>(list.toArray());
                        JScrollPane jcp = new JScrollPane(highScores);

                        JFrame frame = new JFrame();
                        frame.setSize(new Dimension(200,500));
                        frame.setLocationRelativeTo(null);

                        frame.add(jcp);
                        frame.setVisible(true);

                    }
                    if (y1 >= 350 && y1 <= 400) {

                        System.exit(0);
                    }
                }
            }
                if (state == STATE.DIFFICULTY) {
                    if(x1 >= 360 && x1 <=460) {
                        if (y1 >= 150 && y1 <= 200) {
                            kCounter++;
                            if(kCounter >= 2){
                                state = STATE.GAME;
                                difficulty = -1;
                                kCounter = 0;
                                scoreMultiplier = 1;
                            }

                        }
                    if (y1 >= 250 && y1 <= 300) {
                        difficulty = 0;
                        state = STATE.GAME;
                        scoreMultiplier = 2;
                    }
                        if (y1 >= 350 && y1 <= 400) {
                            difficulty = 2;
                            state = STATE.GAME;
                            scoreMultiplier = 3;
                        }
                    }
                }

                if (state == STATE.OVER) {
                    ts.setCounter();
                    if(x1 >= 320 && x1 <=460) {
                        if (y1 >= 170 && y1 <= 220) {
                            JFrame tf = new JFrame();
                            tf.setSize(100,100);
                            tf.setTitle("Duck Hunt");
                            tf.setVisible(true);
                            tf.setLocationRelativeTo(null);
                            JTextField text = new JTextField();
                            text.setPreferredSize(new Dimension(100,60));
                            tf.add(text);
                            text.addActionListener(e1 -> {
                                String x = text.getText();
                                System.out.println(x);
                                tf.setVisible(false);
                                BufferedWriter output = null;
                                try {
                                    output = new BufferedWriter(new FileWriter("scoreBoard.txt",true));
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                try {
                                    assert output != null;
                                    output.newLine();
                                    int fScore = (score * scoreMultiplier + timeScore);
                                    output.append(x).append(" | ").append(String.valueOf(fScore));
                                    output.close();
                                } catch (IOException ignored) {}

                            });

                        }
                    if (y1 >= 250 && y1 <= 300) {

                        d1.x = -200;
                        d1.dSide = true;
                        d2.x = -600;
                        d2.dSide = true;
                        d3.x = -100;
                        d3.dSide = true;
                        d4.x = -1000;
                        d4.dSide = true;
                        d5.x = -1500;
                        d5.dSide = true;

                        gunDmg = 1;
                        upgradeCost = 10;

                        setPassCountersToZero();
                        setDifficultyToZero();
                        score = 0;
                        timeScore = 0;
                        state = STATE.MENU;


                    }
                        if (y1 >= 350 && y1 <= 400) {
                            System.exit(0);
                        }
                    }
                }

                if (state == STATE.GAME) {

                    if(x1 >= 609 && x1 <= 800 && y1 >= 506 && y1 <= 536){
                        gunUpgrade();
                    }


                double side = Math.random();
                    if (!((x1 >= 197 && x1 <= 311 && y1 >= 183 && y1 <= 427) || (x1 >= c1.x && x1 <= c1.x+73 && y1 >= c1.y && y1 <= c1.y+46) || (x1 >= c2.x && x1 <= c2.x+73 && y1 >= c2.y && y1 <= c2.y+46))) {


                        if (x1 - d1.x < 80 && x1 - d1.x > 0 && y1 - d1.y < 60 && y1 - d1.y > 0) {
                            d1.isAlive = false;
                            score+=1;
                            if (side < 0.5) {
                                d1.x = -100;
                                d1.dSide = true;
                            } else {
                                d1.x = 900;
                                d1.dSide = false;
                            }
                        }
                        if (x1 - d2.x < 80 && x1 - d2.x > 0 && y1 - d2.y < 60 && y1 - d2.y > 0) {
                            d2.hp -= gunDmg;
                            if (d2.hp <= 0) {
                                score += 3;
                                d2.hp = 5;
                                d2.isAlive = false;
                                if (side < 0.5) {
                                    d2.x = -100;
                                    d2.dSide = true;
                                } else {
                                    d2.x = 900;
                                    d2.dSide = false;
                                }
                            }
                        }
                        if (x1 - d3.x < 80 && x1 - d3.x > 0 && y1 - d3.y < 60 && y1 - d3.y > 0) {
                            d3.hp -= gunDmg;
                            if (d3.hp <= 0) {
                                score += 2;
                                d3.isAlive = false;
                                d3.hp = 3;
                                if (side < 0.5) {
                                    d3.x = -100;
                                    d3.dSide = true;
                                } else {
                                    d3.x = 900;
                                    d3.dSide = false;
                                }
                            }
                        }
                        if (x1 - d4.x < 80 && x1 - d4.x > 0 && y1 - d4.y < 60 && y1 - d4.y > 0) {
                            d4.hp -= gunDmg;
                            if (d4.hp <= 0) {
                                score += 4;
                                d4.isAlive = false;
                                d4.hp = 8;
                                if (side < 0.5) {
                                    d4.x = -100;
                                    d4.dSide = true;
                                } else {
                                    d4.x = 900;
                                    d4.dSide = false;
                                }
                            }
                        }
                        if (x1 - d5.x < 80 && x1 - d5.x > 0 && y1 - d5.y < 60 && y1 - d5.y > 0) {
                            d5.isAlive = false;
                            score += 1;
                            if (side < 0.5) {
                                d5.x = -100;
                                d5.dSide = true;
                            } else {
                                d5.x = 900;
                                d5.dSide = false;
                            }
                        }
                    }
                }
            }
        });

    }
    private void DrawScore(Graphics2D g2){

        g2.setColor(Color.WHITE);
        g2.setFont(font);
        g2.drawString("SCORE: "+ score,400, 30);

    }
    private void DrawHp(Graphics2D g2){

        g2.setColor(Color.WHITE);
        g2.setFont(font);
        g2.drawString("LIFE: "+ (10 - passCounter()) ,600, 30);
    }

    private void DrawUpgrades(Graphics2D g2){
        g2.setColor(Color.WHITE);
        g2.setFont(font);
        g2.draw(rect);
        g2.drawString("UPGRADE COST: "+ upgradeCost,630, 530);
        g2.drawString("GUN DMG: "+ gunDmg,630, 590);
    }




    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        if(state == STATE.GAME) {
            g2.drawImage(image, 0, 0, null);
            d1.render(g2);
            d2.render(g2);
            d3.render(g2);
            d4.render(g2);
            d5.render(g2);
            c1.render(g2);
            c2.render(g2);
            g2.drawImage(tree, 0,0,null);
            DrawScore(g2);
            DrawUpgrades(g2);
            DrawHp(g2);
            DrawTime(g2);
            }
        else if (state == STATE.MENU){
            g2.drawImage(image, 0, 0, null);
            menu.render(g2);
            }
        else if (state == STATE.DIFFICULTY){
            g2.drawImage(image, 0,0,null);
            diffS.render(g2);
        }
        else if (state == STATE.OVER){
            Font font1 = new Font("impact", Font.PLAIN, 30);
            g2.drawImage(image, 0, 0, null);
            gOver.render(g2);
            g2.setColor(Color.WHITE);
            g2.setFont(font1);
            int fScore = (score * scoreMultiplier + timeScore);
            g2.drawString("FINAL SCORE: "+ fScore,320, 150);
            seconds = 0;
            minutes = 0;
        }


        g2.dispose();


    }

    private void DrawTime(Graphics2D g2){

        g2.setColor(Color.WHITE);
        g2.setFont(font);
        g2.drawString("TIME: " + minutes + " : " + seconds ,200, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    if (state == STATE.GAME) {
        d1.update();
        d2.update();
        d3.update();
        d4.update();
        d5.update();
        c1.update();
        c2.update();

        time++;
        if(time >= 60) {
            timeScore++;
            time = 0;
            seconds++;
            if(seconds >= 60){
                seconds = 0;
                minutes = 1;
            }
        }

        if(MyFrame.shortCut())
            state = STATE.OVER;
        if (passCounter() >= 10)
            state = STATE.OVER;

    }


    repaint();



    }
    public int passCounter(){


        return d1.getPassCounter() + d2.getPassCounter() + d3.getPassCounter() + d4.getPassCounter() + d5.getPassCounter();
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public void setPassCountersToZero(){
        d1.setPassCounter();
        d2.setPassCounter();
        d3.setPassCounter();
        d4.setPassCounter();
        d5.setPassCounter();
    }

    public static void gunUpgrade(){
        if(score >= upgradeCost) {
            gunDmg++;
            score -= upgradeCost;
            upgradeCost += 5;
        }

    }
    public void setDifficultyToZero(){
        d1.setDifficulty();
        d2.setDifficulty();
        d3.setDifficulty();
        d4.setDifficulty();
        d5.setDifficulty();
    }




}


