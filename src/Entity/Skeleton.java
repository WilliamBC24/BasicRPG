package Entity;

import main.GamePanel;
import Object.DarkRock;
import Object.Coin;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Skeleton extends Entity{
    public Skeleton(GamePanel gp) {
        super(gp);
        getImage();
        type=type_monster;
        name="Skeleton";
        speed=1;
        maxLife=50;
        life=maxLife;
        attack=10;
        defense=3;
        exp=6;
        projectile = new DarkRock(gp);
        solidArea.x=0;
        solidArea.y=0;
        solidArea.width=40;
        solidArea.height=35;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
    }
    public void getImage(){
        up1=setup("/Resource/image/monster/skeleton/walk/7");
        up2=setup("/Resource/image/monster/skeleton/walk/8");
        down1=setup("/Resource/image/monster/skeleton/walk/1");
        down2=setup("/Resource/image/monster/skeleton/walk/2");
        left1=setup("/Resource/image/monster/skeleton/walk/3");
        left2=setup("/Resource/image/monster/skeleton/walk/4");
        right1=setup("/Resource/image/monster/skeleton/walk/5");
        right2=setup("/Resource/image/monster/skeleton/walk/6");
    }
    public void update(){
        setAction();
        collisionOn=false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObj(this, false);
        boolean contactPlayer=gp.cChecker.checkPlayer(this);
        if(this.type==2&&contactPlayer==true){
            if(gp.player.invisible==false){
                int damage=attack-gp.player.defense;
                if(damage<0){
                    damage=0;
                }
                gp.player.life-=damage;
                if(damage>0) {
                    gp.ui.addMessage("Taken " + damage + " damage!");
                }
                else{
                    gp.ui.addMessage("Dodged!");
                }
                gp.player.invisible=true;
            }
        }
        if(collisionOn==false){
            switch(direction){
                case "up":
                    worldY-=speed;
                    break;
                case "down":
                    worldY+=speed;
                    break;
                case "left":
                    worldX-=speed;
                    break;
                case "right":
                    worldX+=speed;
                    break;
            }
        }
        spriteCounter++;
        if(spriteCounter > 10){
            if(spriteNum==1){
                spriteNum=2;
            }else if(spriteNum==2) {
                spriteNum = 1;
            }
            spriteCounter=0;
        }
        if(invisible==true){
            invisibleCount++;
            if(invisibleCount>40){
                invisible=false;
                invisibleCount=0;
            }
        }
        if(shotAvailable<30){
            shotAvailable++;
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        //draw only part we see
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch(direction) {
                case "up":
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                    break;
                case "down":
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                    break;
                case "left":
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                    break;
                case "right":
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                    break;
            }
            if(hpBarOn==true) {
                double oneScale = (double) gp.tileSize / maxLife;
                double hpBarValue = oneScale * life;
                g2.setColor(new Color(65, 65, 65));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);
                g2.setColor(new Color(136, 8, 8));
                g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);
                if(hpBarCounter>300){
                    hpBarCounter=0;
                    hpBarOn=false;
                }
            }
            if(invisible==true){
                hpBarOn=true;
                changeVis(g2,0.5f);
            }
            if(dying==true){
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            changeVis(g2,1f);
        }
    }
    public void dmgReaction(){
        actionCount=0;
        direction=gp.player.direction;
    }
    public void checkDrop(){
        int i=new Random().nextInt(100)+1;
        if(i<35){

        }else{
            dropItem(new Coin(gp));
        }
    }
    public void setAction(){
        actionCount++;
        if(actionCount==120){
            Random random = new Random();
            int movement= random.nextInt(100)+1;
            if(movement<=25){
                direction="up";
            }else if(movement>25&&movement<=50){
                direction="down";
            }else if(movement>50&&movement<=75){
                direction="left";
            }else if(movement>75&&movement<=100){
                direction="right";
            }
            actionCount=0;
        }
        int i=new Random().nextInt(100)+1;
        if(i>99&&projectile.alive==false&&shotAvailable==30){
            projectile.set(worldX,worldY,direction,true,this);
            gp.projectileList.add(projectile);
            shotAvailable=0;
        }
    }
}
