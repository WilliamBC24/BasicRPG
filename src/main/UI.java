import java.awt.*;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font didot40;
    Font didot80;
    public boolean msgOn = false;
    public String message = "";
    //BufferedImage key;
    int msgCount =0;
    public boolean win = false;
    public UI(GamePanel gp){
        this.gp=gp;
        didot40 = new Font("Didot", Font.PLAIN, 25);
        didot80 = new Font("Didot", Font.BOLD, 40);
        //Key keyImg = new Key(gp);
        //key=keyImg.image;
    }

    public void message(String text){
        message = text;
        msgOn=true;
    }
    public void draw(Graphics2D g2){
        this.g2=g2;
        g2.setFont(didot40);
        g2.setColor(Color.black);

        if(gp.gameState==gp.playState){

        }else if(gp.gameState==gp.pauseState){
            drawPause();
        }
    }
    public void drawPause(){
        String text = "Paused.";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80));
        int x=getCenter(text);
        int y=gp.screenHeight/2;
        g2.drawString(text,x,y);
    }
    public int getCenter(String text){
        int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=gp.screenWidth/2-length/2;
        return x;
    }
}
