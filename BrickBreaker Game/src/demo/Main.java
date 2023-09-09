package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main
{
    static Container glassPane;
    Main(){

        JFrame f= new JFrame();
        f.setTitle("Brick Breaker");
        f.setSize(700, 580);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setResizable(false);

        GamePlay gamePlay=new GamePlay();
        f.add(gamePlay);

        f.setGlassPane(new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(255, 255, 255, 150));
                g.fillRect(0,0,getWidth(),getHeight());
            }
        });

        glassPane=(Container)f.getGlassPane();
        glassPane.setLayout(null);

        JLabel msg=new JLabel("Choose the Level :");
        msg.setBounds(120,290,200,20);
        msg.setFont(new Font("serif",Font.BOLD,20));
        msg.setForeground(Color.BLACK);
        glassPane.add(msg);

        JButton easy=new JButton("Easy");
        JButton medium=new JButton("Medium");
        JButton hard=new JButton("Hard");

        easy.setBounds(300,250,100,25);
        medium.setBounds(300,290,100,25);
        hard.setBounds(300,330,100,25);


        easy.setForeground(Color.BLACK);
        medium.setForeground(Color.BLACK);
        hard.setForeground(Color.BLACK);

        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                glassPane.setVisible(false);
                GamePlay.ballXDir=-1;
                GamePlay.ballYDir=-2;
                //GamePlay.s="easy";
            }
        });

        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                glassPane.setVisible(false);
                GamePlay.ballXDir=-2;
                GamePlay.ballYDir=-3;
                //GamePlay.s="medium";
            }
        });

        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                glassPane.setVisible(false);
                GamePlay.ballXDir=-3;
                GamePlay.ballYDir=-4;
                //GamePlay.s="hard";
            }
        });

        glassPane.add(easy);
        glassPane.add(medium);
        glassPane.add(hard);

        glassPane.setVisible(true);


    }

    public static void main(String[] args){
        String str="javax.swing.plaf.nimbus.NimbusLookAndFeel";
        try{
            UIManager.setLookAndFeel(str);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        new Main();

    }


}
