package GUI;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame implements KeyListener {

    static boolean shift = false;
    static boolean ctrl = false;
    static boolean q = false;
    static boolean space = false;


    MyPanel panel;
    public MyFrame(){
        panel = new MyPanel();
        this.setSize(800,600);
        this.setTitle("Duck Hunt");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(panel);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addKeyListener(this);



        ImageIcon image = new ImageIcon("resources/images/icon.png");
        this.setIconImage(image.getImage());


    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == 17)
        ctrl = true;
    if(e.getKeyCode() == 16)
        shift = true;
    if(e.getKeyCode() == 81)
        q = true;
    if(e.getKeyCode() == 32)
        space = true;
    }


    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 17)
            ctrl = false;
        if(e.getKeyCode() == 16)
            shift = false;
        if(e.getKeyCode() == 81)
            q = false;
        if(e.getKeyCode() == 32)
            space = false;
    }

    public static boolean shortCut(){
        if(ctrl && shift && q ){
            return true;
        }
        if(space)
            MyPanel.gunUpgrade();

        return false;
    }
}
