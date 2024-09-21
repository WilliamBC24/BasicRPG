package Entity;

import main.GamePanel;
import Object.Heart;
import Object.Hermes;
import Object.ShieldOfTheLost;
import Object.DarkSword;
import Object.DarkShield;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Merchant extends Entity{
    public Merchant(GamePanel gp) {
        super(gp);
        type=type_npc;
        direction="down";
        speed=1;
        solidArea=new Rectangle();
        solidArea.x=0;
        solidArea.y=0;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=48;
        solidArea.height=48;
        getImage();
        setDialog();
        setItem();
    }
    public void getImage(){
            down1=setup("/Resource/image/npc/merchant");
    }
    public void setDialog(){
            dialog[0]="What you want?";
            dialog[1]="I got everything you will ever need";
    }
    public void setAction(){
    }
    public void draw(Graphics2D g2) {
            BufferedImage image = null;

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            //draw only part we see
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                image=down1;
                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                changeVis(g2, 1f);
            }
    }
    public void setItem() {
        inventory.add(new Heart(gp));
        inventory.add(new DarkSword(gp));
        inventory.add(new DarkShield(gp));
        inventory.add(new Hermes(gp));
        inventory.add(new ShieldOfTheLost(gp));
    }
    public void speak() {
        Random random = new Random();
        gp.ui.currentDialog = dialog[random.nextInt(2)];
        switch (gp.player.direction) {
            case "up":
            case "down":
            case "left":
            case "right":
                direction = "down";
                break;
        }
        gp.gameState=gp.tradeState;
        gp.ui.npc=this;
    }
}

