package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private int[]xpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[]ypos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    private Random random=new Random();
    private int foodx,foody;
    boolean gameover=false;
private int score=0;
    private int[] snakexlength=new int[750];
    private int[] snakeylength=new int[750];
    private int lengthOfsnake=3;
    private boolean left=false;
    private boolean right=true;
    private boolean up=false;
    private boolean down=false;
    int moves=0;
    private ImageIcon snakeTitle=new ImageIcon(getClass().getResource("title.jpg"));
    private ImageIcon leftmouth=new ImageIcon(getClass().getResource("leftmouth.jpg"));
    private ImageIcon rightmouth=new ImageIcon(getClass().getResource("rightmouth.jpg"));
    private ImageIcon upmouth=new ImageIcon(getClass().getResource("upmouth.jpg"));
    private ImageIcon downmouth=new ImageIcon(getClass().getResource("downmouth.jpg"));
    private ImageIcon snakeimg=new ImageIcon(getClass().getResource("snakebody.jpg"));
    private ImageIcon enemy=new ImageIcon(getClass().getResource("enemy.jpg"));
    private Timer timer;
    private int delay =100;
    GamePanel()
    {
timer=new Timer(delay,this);
addKeyListener(this);
setFocusable(true);
setFocusTraversalKeys(true);
timer.start();
newfood();
    }

    private void newfood() {
        foodx = xpos[random.nextInt(34)];
        foody = ypos[random.nextInt(23)];
        for (int i = lengthOfsnake - 1; i >= 0; i--) {
            if (foodx == snakexlength[i] && foody==snakeylength[i])
            {
                newfood();
            }
        }
    }
    private void setFocusTraversalKeys(boolean b) {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);
        g.drawRect(24,74,851,576);
        snakeTitle.paintIcon(this,g,25,11);
        g.setColor(Color.white);
        g.fillRect(25,75,850,575);
        if(moves==0)
        {
            snakexlength[0]=100;
            snakexlength[1]=75;
            snakexlength[2]=50;
            snakeylength[0]=100;
            snakeylength[1]=100;
            snakeylength[2]=100;
            moves++;
        }
        if(left)
        {
            leftmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
        }
        if(right)
        {
            rightmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
        }
        if(up)
        {
            upmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
        }
        if(down)
        {
            downmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
        }
        for(int i=1;i<lengthOfsnake;i++)
        {
            snakeimg.paintIcon(this,g,snakexlength[i],snakeylength[i]);
        }
        enemy.paintIcon(this,g,foodx,foody);
        if(gameover==true)
        {
            g.setColor(Color.black);
            g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
            g.drawString("GAME OVER",300,300);
            g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,10));
            g.drawString("PRESS SPACE TO RESTART",350,350);
        }
        g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
        g.drawString("Score:"+score,750,30);
        g.drawString("length of snake"+lengthOfsnake,750,50);
        g.dispose();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=lengthOfsnake-1;i>0;i--)
        {
            snakexlength[i]=snakexlength[i-1];
            snakeylength[i]=snakeylength[i-1];
        }
        if(left)
        {
            snakexlength[0]=snakexlength[0]-25;
        }
        if(right)
        {
            snakexlength[0]=snakexlength[0]+25;
        }
        if(up)
        {
            snakeylength[0]=snakeylength[0]-25;
        }
        if(down)
        {
            snakeylength[0]=snakeylength[0]+25;
        }
        if(snakexlength[0]>850)
            snakexlength[0]=25;
        if(snakexlength[0]<25)
            snakexlength[0]=850;

        if(snakeylength[0]>625)
            snakeylength[0]=75;
        if(snakeylength[0]<75)
            snakeylength[0]=625;
             collideswithenemy();
             collideswithbody();

        repaint();
            }

    private void collideswithbody() {
        for(int i=1;i<lengthOfsnake;i++)
        {
            if(snakexlength[i]==snakexlength[0] && snakeylength[i]==snakeylength[0])
            {
                timer.stop();
                gameover=true;

            }
        }
    }

    private void collideswithenemy() {
        if(snakexlength[0]==foodx && snakeylength[0]==foody) {
            newfood();
            lengthOfsnake++;
            snakexlength[lengthOfsnake-1]=snakexlength[lengthOfsnake-2];
            snakeylength[lengthOfsnake-1]=snakeylength[lengthOfsnake-2];
            score++;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode()==KeyEvent.VK_SPACE) && gameover)
        {
            restart();
        }
        if (e.getKeyCode()==KeyEvent.VK_RIGHT && (!right)){
            left=false;
            right=true;
            up=false;
            down=false;
        }
        if (e.getKeyCode()==KeyEvent.VK_UP && (!down)){
            left=false;
            right=false;
            up=true;
            down=false;
        }
        if (e.getKeyCode()==KeyEvent.VK_DOWN && (!up)){
            left=false;
            right=false;
            up=false;
            down=true;
        }
        if (e.getKeyCode()==KeyEvent.VK_LEFT && (!right )){
            left=true;
            right=false;
            up=false;
            down=false;
        }

    }

    private void restart() {
        gameover=false;
        moves=0;
        score=0;
        lengthOfsnake=3;
        left=false;
        right=true;
        up=false;
        down=false;
        timer.start();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}




