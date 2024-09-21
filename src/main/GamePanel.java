import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //Screen settings
    final int originalTileSize = 16;//character size
    final int scale = 3;

    public int tileSize = originalTileSize*scale;//scale size
    public int screenColumn = 16;
    public int screenRow = 12;
    public int screenWidth = tileSize*screenColumn;
    public int screenHeight = tileSize*screenRow;

    //World settings
    public final int worldColumn=50;
    public final int worldRow=50;
    public final int worldWidth=tileSize*worldColumn;
    public final int worldHeight=tileSize*worldRow;

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyDetech keyH = new KeyDetech(this);
    Sound music = new Sound();
    Sound SE = new Sound();
    Thread gameThread;
    public Player player = new Player(this,keyH);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Setter set = new Setter(this);
    public SuperObj obj[] = new SuperObj[10];
    public Entity npc[] = new Entity[10];
    public UI ui = new UI(this);
    public int gameState;
    public final int playState=1;
    public final int pauseState=2;
    public final int dialogueState=3;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);//get key input
        this.setFocusable(true);
    }

    public void setGame(){
        music(5);
        set.setObj();
        set.setNPC();
        gameState=playState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDraw = System.nanoTime()+drawInterval;

        while(gameThread != null){
            //Update char position
            update();
            //Draw screen with updated info
            repaint();
            try{
            double remainingTime = nextDraw - System.nanoTime();
            remainingTime/=1000000;

            if(remainingTime<0){
                remainingTime=0;
            }

            nextDraw+=drawInterval;

                Thread.sleep((long) remainingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
        if(gameState==playState){
            player.update();
            for(int i=0;i< npc.length;i++){
                if(npc[i]!=null){
                    npc[i].update();
                }
            }
        }
        else if(gameState==pauseState){

        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        for(int i=0;i<obj.length;i++){
            if (obj[i]!=null){
                obj[i].draw(g2,this);
            }
        }
        for(int i =0;i<npc.length;i++){
            if(npc[i]!=null){
                npc[i].draw(g2);
            }
        }
        player.draw(g2);
        ui.draw(g2);
        g2.dispose();//delete graphics after use to save memory
    }
    public void music(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void musicstop(){
        music.stop();
    }
    public void playSE(int i){
        SE.setFile(i);
        SE.play();
    }
}
