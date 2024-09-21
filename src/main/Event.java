package main;

import java.awt.*;

public class Event {
    GamePanel gp;
    EventRect eventRect[][][];
    int previousEventX,previousEventY;
    boolean canTouch=true;
    boolean eventDone=false;
    int tempMap,tempCol,tempRow;
    public Event(GamePanel gp){
        this.gp=gp;
        eventRect=new EventRect[gp.maxMap][gp.worldColumn][gp.worldRow];
        int map=0;
        int col=0;
        int row=0;
        while(map<gp.maxMap&&col<gp.worldColumn&&row<gp.worldRow){
            eventRect[map][col][row]=new EventRect();
            eventRect[map][col][row].x=23;
            eventRect[map][col][row].y=23;
            eventRect[map][col][row].width=2;
            eventRect[map][col][row].height=2;
            eventRect[map][col][row].eventRectDefaultX=eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY=eventRect[map][col][row].y;
            col++;
            if(col==gp.worldColumn){
                col=0;
                row++;

                if(row==gp.worldRow){
                    row=0;
                    map++;
                }
            }
        }
    }
    public void checkEvent(){
        int xDistance=Math.abs(gp.player.worldX-previousEventX);
        int yDistance=Math.abs(gp.player.worldY-previousEventY);
        int distance=Math.max(xDistance,yDistance);
        if(distance>gp.tileSize){
            canTouch=true;
        }
        if(canTouch==true) {
            if (hit(0,34, 22, "any") == true) {
                dmgPit(gp.dialogueState);
            }
            else if (hit(0,19, 33, "down") == true) {
                heal(gp.dialogueState,19, 33);
            }
            else if (hit(0,39,36,"any")==true){
                teleport(1,35,13);
            }
            else if(hit(1,35,13,"any")==true){
                teleport(0,39,36);
            }
            else if(hit(0,18,33,"down")==true){
                checkpoint(gp.dialogueState);
            }
            else if(hit(1,40,9,"any")==true){
                die();
            }
        }
    }
    public boolean hit(int map,int col,int row,String reqDirection){
        boolean hit =false;
        if(map==gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;
            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.equals("any")) {
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
            return hit;
    }
    public void dmgPit(int gameState){
        gp.gameState=gameState;
        gp.ui.currentDialog="You got hit.";
        gp.player.life-=1;
        canTouch=false;
    }
    public void heal(int gameState,int col,int row) {
        if (gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.player.attackCanceled=true;
            gp.ui.currentDialog = "You rest for a while.";
            gp.player.life = gp.player.maxLife;
            eventRect[gp.currentMap][col][row].eventDone=true;
        }
    }
    public void teleport(int mapNum,int col,int row){
        gp.gameState=gp.transitionState;
        tempMap=mapNum;
        tempCol=col;
        tempRow=row;
    }
    public void checkpoint(int gameState){
        if (gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.player.attackCanceled=true;
            gp.player.haveCheckpoint=true;
            gp.ui.currentDialog = "You reached a checkpoint.";
            gp.player.checkpointX=gp.player.worldX;
            gp.player.checkpointY=gp.player.worldY;
            gp.player.checkpointLife=gp.player.life;
            gp.player.checkpointMana=gp.player.mana;
            gp.player.checkpointLvl=gp.player.level;
            gp.player.checkpointMoney=gp.player.money;
            gp.player.checkpointInventory=gp.player.inventory;
            gp.player.checkpointCurrentWeapon=gp.player.currentWeapon;
            gp.player.checkpointCurrentShield=gp.player.currentShield;
            gp.player.checkpointCurrentBoots=gp.player.currentBoots;
            for(int i=0;i<gp.player.object;i++){
                if(gp.obj[gp.currentMap][gp.player.object]!=null){
                    gp.player.checkpointItem.add(gp.obj[gp.currentMap][gp.player.object]);
                }
            }
        }
    }
    public void die(){
        gp.gameState=gp.gameoverState;
    }
}
