package SnakeGame;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    JFrame frame;
    Main()
    {
        frame =new JFrame("SNAKE GAME");
        frame.setBounds(10,10,905,700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        GamePanel panel=new GamePanel();
        panel.setBackground(Color.darkGray);
        frame.add(panel);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
       Main main=new Main();
    }
}