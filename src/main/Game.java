package main;

import javax.swing.JFrame;
import java.awt.*;

public class Game{

    public static JFrame window;

    public static void main(String[] args) {
        //Panel setting
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Not Diablo II");
        //window.setUndecorated(true);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setGame();
        gamePanel.startGameThread();

    }
}
