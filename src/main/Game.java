import javax.swing.JFrame;

public class Game{

    public static void main(String[] args) {
    //Panel setting
    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setTitle("SimSimSalaBim");

    GamePanel gamePanel = new GamePanel();
    window.add(gamePanel);

    window.pack();

    window.setLocationRelativeTo(null);
    window.setVisible(true);

    gamePanel.setGame();
    gamePanel.startGameThread();

    }
}
