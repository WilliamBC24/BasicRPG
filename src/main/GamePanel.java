package main;

import Entity.Entity;
import Entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{
    //Screen settings
    final int originalTileSize = 16;//character size
    final int scale = 3;

    public int tileSize = originalTileSize*scale;//scale size
    public int screenColumn = 20;
    public int screenRow = 12;
    public int screenWidth = tileSize*screenColumn;
    public int screenHeight = tileSize*screenRow;

    //Fullscreen
    int screenWidth2=screenWidth;
    int screenHeight2=screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreen = false;

    //World settings
    public final int worldColumn=50;
    public final int worldRow=50;
    public final int worldWidth=tileSize*worldColumn;
    public final int worldHeight=tileSize*worldRow;
    public final int maxMap=10;
    public int currentMap=0;

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    public KeyDetech keyH = new KeyDetech(this);
    Sound music = new Sound();
    Sound SE = new Sound();
    Thread gameThread;
    public Event event = new Event(this);
    public UI ui = new UI(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Setter set = new Setter(this);

    public Player player = new Player(this,keyH);
    public Entity obj[][] = new Entity[maxMap][20];
    public Entity npc[][] = new Entity[maxMap][20];
    public Entity monster[][] = new Entity[maxMap][20];
    ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();


    public int gameState;
    public final int titleState=0;
    public final int playState=1;
    public final int pauseState=2;
    public final int dialogueState=3;
    public final int characterState=4;
    public final int optionsState=5;
    public final int gameoverState=6;
    public final int transitionState=7;
    public final int tradeState=8;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);//get key input
        this.setFocusable(true);
    }

    public void setGame(){
        set.setObj();
        set.setNPC();
        set.setMonster();
        gameState=titleState;
        music(6);

        tempScreen = new BufferedImage(screenWidth,screenHeight,BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();

        //setFullScreen();
    }
    public void setFullScreen(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Game.window);

        screenWidth2=Game.window.getWidth();
        screenHeight2=Game.window.getHeight();
        /*Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Game.window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        screenWidth2 = (int) width;
        screenHeight2 = (int) height;*/
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void reset(){
        player.setDefaultPosition();
        player.restoreStat();
        player.setItems();
        set.setObj();
        set.setNPC();
        set.setMonster();
    }
    public void retry(){
        player.restoreCheckpointPosition();
        player.restoreCheckpointStat();
        //player.restoreCheckpointItems();
        player.checkpointCurrentItems();
        //player.setCheckpointItems();
        set.setNPC();
        set.setMonster();
        //set.setObj();
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
            //drawTemp();
            //drawScreen();
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
            for(int i=0;i< npc[1].length;i++){
                if(npc[currentMap][i]!=null){
                    npc[currentMap][i].update();
                }
            }
            for(int i=0;i<monster[1].length;i++){
                if(monster[currentMap][i]!=null){
                    if(monster[currentMap][i].alive==true&&monster[currentMap][i].dying==false) {
                        monster[currentMap][i].update();
                    }
                    if(monster[currentMap][i].alive==false){
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i]=null;
                    }
                }
            }
            for(int i=0;i<projectileList.size();i++){
                if(projectileList.get(i)!=null){
                    if(projectileList.get(i).alive==true) {
                        projectileList.get(i).update();
                    }
                    if(projectileList.get(i).alive==false){
                        projectileList.remove(i);
                    }
                }
            }
        }
        else if(gameState==pauseState){

        }
    }
    public void drawTemp(){
        if(gameState==titleState){
            ui.draw(g2);
        }else {
            tileM.draw(g2);
            entityList.add(player);
            for(int i=0;i<npc[1].length;i++){
                if(npc[currentMap][i]!=null){
                    entityList.add(npc[currentMap][i]);
                }
            }
            for(int i=0;i<obj[1].length;i++){
                if(obj[currentMap][i]!=null){
                    entityList.add(obj[currentMap][i]);
                }
            }
            for(int i=0;i<monster[1].length;i++){
                if(monster[currentMap][i]!=null){
                    entityList.add(monster[currentMap][i]);
                }
            }
            for(int i=0;i<projectileList.size();i++){
                if(projectileList.get(i)!=null){
                    entityList.add(projectileList.get(i));
                }
            }
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result=Integer.compare(e1.worldY,e2.worldY);
                    return result;
                }
            });
            for(int i=0;i<entityList.size();i++){
                entityList.get(i).draw(g2);
            }
            entityList.clear();
            ui.draw(g2);
        }
    }
    public void drawScreen(){
        Graphics g = getGraphics() ;
        g.drawImage(tempScreen,0,0,screenWidth2,screenHeight2,null);
        g.dispose();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if(gameState==titleState){
            ui.draw(g2);
        }else {
            tileM.draw(g2);
            entityList.add(player);
            for(int i=0;i<npc[1].length;i++){
                if(npc[currentMap][i]!=null){
                    entityList.add(npc[currentMap][i]);
                }
            }
            for(int i=0;i<obj[1].length;i++){
                if(obj[currentMap][i]!=null){
                    entityList.add(obj[currentMap][i]);
                }
            }
            for(int i=0;i<monster[1].length;i++){
                if(monster[currentMap][i]!=null){
                    entityList.add(monster[currentMap][i]);
                }
            }
            for(int i=0;i<projectileList.size();i++){
                if(projectileList.get(i)!=null){
                    entityList.add(projectileList.get(i));
                }
            }
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result=Integer.compare(e1.worldY,e2.worldY);
                    return result;
                }
            });
            for(int i=0;i<entityList.size();i++){
                entityList.get(i).draw(g2);
            }
            entityList.clear();
            ui.draw(g2);//delete graphics after use to save memory
        }
        g2.dispose();
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
