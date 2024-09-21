package Entity;

import main.GamePanel;
import main.Utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Entity {

    protected GamePanel gp;

    public int worldX,worldY;//position on map
    public BufferedImage up1,up2,up3,up4,up5,up6,up7,up8,up9,down1,down2,down3,down4
            ,down5,down6,down7,down8,down9,left1,left2,left3,left4,left5,left6,left7
            ,left8,left9,right1,right2,right3,right4,right5,right6,right7,right8,right9;
    public String direction="down";

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea=new Rectangle(0,0,48,48);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public Rectangle attackArea=new Rectangle(0,0,0,0);
    public boolean collisionOn = false;
    boolean attacking = false;
    public boolean alive=true;
    public boolean dying=false;
    boolean hpBarOn=false;
    public boolean invisible=false;
    public boolean throwObject=false;
    public int invisibleCount=0;
    public int manaCount=0;
    int dyingCounter=0;
    int hpBarCounter=0;
    public int shotAvailable=0;
    public int actionCount=0;
    String dialog[]= new String[20];
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int inventorySize=20;

    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int level;
    public int strength;
    public int dexterity;
    public int speed;
    public int baseSpeed;
    public int atkSpeed;
    public int baseAtkSpeed;
    public int attack;
    public int baseAttack;
    public int defense;
    public int baseDefense;
    public int magic;
    public int exp;
    public int nextLvlExp;
    public int money;
    public int value;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentBoots;
    public Entity key;
    public Projectile projectile;

    public int attackValue;
    public int defenseValue;
    public int speedValue;
    public int atkSpeedValue;
    public int useCost;
    public String description;

    public BufferedImage image,image1,image2,image3;
    public String name;
    public boolean collision = false;
    public int type;
    public final int type_player=0;
    public final int type_npc=1;
    public final int type_monster=2;
    public final int type_sword=3;
    public final int type_shield=4;
    public final int type_shoes=5;
    public final int type_consumable=6;
    public final int type_pickup=7;
    public final int type_none=8;

    public Entity(GamePanel gp){
        this.gp=gp;
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
    }
    public void dmgReaction(){

    }
    public void speak(){
        Random random = new Random();
        gp.ui.currentDialog=dialog[random.nextInt(4)];
        switch (gp.player.direction){
            case "up":
                direction="down";
                break;
            case "down":
                direction="up";
                break;
            case "left":
                direction="right";
                break;
            case "right":
                direction="left";
                break;
        }
    }
    public void checkDrop(){}
    public void dropItem(Entity droppedItem){
        for(int i=0;i<gp.obj[1].length;i++){
            if(gp.obj[gp.currentMap][i]==null){
                gp.obj[gp.currentMap][i]=droppedItem;
                gp.obj[gp.currentMap][i].worldX=worldX;
                gp.obj[gp.currentMap][i].worldY=worldY;
                break;
            }
        }
    }
    public void update(){
        setAction();
        collisionOn=false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObj(this, false);
        gp.cChecker.checkBump(this,gp.npc);
        gp.cChecker.checkBump(this,gp.monster);
        boolean contactPlayer=gp.cChecker.checkPlayer(this);
        if(this.type==type_monster&&contactPlayer==true){
            damagePlayer(attack);
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
            }else if(spriteNum==2){
                spriteNum=3;
            }else if(spriteNum==3){
                spriteNum=4;
            }else if(spriteNum==4){
                spriteNum=5;
            }else if(spriteNum==5){
                spriteNum=6;
            }else if(spriteNum==6){
                spriteNum=7;
            }else if(spriteNum==7){
                spriteNum=8;
            }else if(spriteNum==8){
                spriteNum=9;
            }else if(spriteNum==9){
                spriteNum=1;
            }
            spriteCounter=0;
        }
        if(invisible==true){
            invisibleCount++;
            if(invisibleCount>60){
                invisible=false;
                invisibleCount=0;
            }
        }
        if(shotAvailable<30){
            shotAvailable++;
        }
    }
    public void damagePlayer(int attack){
        if(gp.player.invisible==false){
            int damage=attack-gp.player.defense;
            if(damage<0){
                damage=0;
            }
            gp.player.life-=damage;
            gp.player.invisible=true;
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        //draw only part we see
        if (    worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch(direction) {
                case "up":
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                    if (spriteNum == 3) {image = up3;}
                    if (spriteNum == 4) {image = up4;}
                    if (spriteNum == 5) {image = up5;}
                    if (spriteNum == 6) {image = up6;}
                    if (spriteNum == 7) {image = up7;}
                    if (spriteNum == 8) {image = up8;}
                    if (spriteNum == 9) {image = up9;}
                    break;
                case "down":
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                    if (spriteNum == 3) {image = down3;}
                    if (spriteNum == 4) {image = down4;}
                    if (spriteNum == 5) {image = down5;}
                    if (spriteNum == 6) {image = down6;}
                    if (spriteNum == 7) {image = down7;}
                    if (spriteNum == 8) {image = down8;}
                    if (spriteNum == 9) {image = down9;}
                    break;
                case "left":
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                    if (spriteNum == 3) {image = left3;}
                    if (spriteNum == 4) {image = left4;}
                    if (spriteNum == 5) {image = left5;}
                    if (spriteNum == 6) {image = left6;}
                    if (spriteNum == 7) {image = left7;}
                    if (spriteNum == 8) {image = left8;}
                    if (spriteNum == 9) {image = left9;}
                    break;
                case "right":
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                    if (spriteNum == 3) {image = right3;}
                    if (spriteNum == 4) {image = right4;}
                    if (spriteNum == 5) {image = right5;}
                    if (spriteNum == 6) {image = right6;}
                    if (spriteNum == 7) {image = right7;}
                    if (spriteNum == 8) {image = right8;}
                    if (spriteNum == 9) {image = right9;}
                    break;
            }
            if(type==2&&hpBarOn==true) {
                double oneScale=(double)gp.tileSize/maxLife;
                double hpBarValue=oneScale*life;
                g2.setColor(new Color(65,65,65));
                g2.fillRect(screenX-1, screenY - 16, gp.tileSize+2, 12);
                g2.setColor(new Color(136, 8, 8));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);
                hpBarCounter++;
                if(hpBarCounter>300){
                    hpBarCounter=0;
                    hpBarOn=false;
                }
            }
            if(invisible==true){
                hpBarOn=true;
                hpBarCounter=0;
                changeVis(g2,0.5f);
            }
            if(dying==true){
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            changeVis(g2,1);
        }
    }
    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        int i=10;
        if(dyingCounter<=i){changeVis(g2,0.7f);}
        if(dyingCounter>=i&&dyingCounter<=i*2){changeVis(g2,1f);}
        if(dyingCounter>=i*2&&dyingCounter<=i*3){changeVis(g2,0.5f);}
        if(dyingCounter>=i*3&&dyingCounter<=i*4){changeVis(g2,1f);}
        if(dyingCounter>=i*4&&dyingCounter<=i*5){changeVis(g2,0.3f);}
        if(dyingCounter>=i*5&&dyingCounter<=i*6){changeVis(g2,0f);}
        if(dyingCounter>i*6){
            //dying=false;
            alive=false;
        }
    }
    public void use(Entity entity){}
    public void changeVis(Graphics2D g2,float alpha){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
    }
    public BufferedImage setup(String name){
        Utility utility = new Utility();
        BufferedImage scaled=null;
        try{
            scaled = ImageIO.read(getClass().getResourceAsStream(name +".png"));
            //scaled = utility.scale(scaled,gp.tileSize,gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        return scaled;
    }
}
