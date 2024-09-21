import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyDetech implements KeyListener {

    public boolean upPressed,downPressed,leftPressed,rightPressed;
    GamePanel gp;
    public KeyDetech(GamePanel gp){
        this.gp=gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    //No use for this
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                upPressed=true;
                break;
            case KeyEvent.VK_A:
                leftPressed=true;
                break;
            case KeyEvent.VK_S:
                downPressed=true;
                break;
            case KeyEvent.VK_D:
                rightPressed=true;
                break;
            case KeyEvent.VK_ESCAPE:
                if(gp.gameState==gp.playState){
                    gp.gameState=gp.pauseState;
                }else if(gp.gameState==gp.pauseState){
                    gp.gameState=gp.playState;
                }
                break;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                upPressed=false;
                break;
            case KeyEvent.VK_A:
                leftPressed=false;
                break;
            case KeyEvent.VK_S:
                downPressed=false;
                break;
            case KeyEvent.VK_D:
                rightPressed=false;
                break;
        }
    }
}
