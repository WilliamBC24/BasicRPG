package Entity;

import main.GamePanel;
import main.KeyDetech;
import Object.WoodSword;
import Object.WoodShield;
import Object.Key;
import Object.Shoes;
import Object.Fireball;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Player extends Entity {

    GamePanel gp;
    KeyDetech keyH;
    public final int screenX;//place char in middle of screen
    public final int screenY;
    public boolean attackCanceled=false;
    public BufferedImage knightup1,knightup2,knightup3,knightup4,knightup5,knightup6,knightdown1,knightdown2,knightdown3,knightdown4,knightdown5,knightdown6,
            knightleft1,knightleft2,knightleft3,knightleft4,knightleft5,knightleft6,knightright1,knightright2,knightright3,knightright4,knightright5,knightright6;
    public BufferedImage archerup1,archerup2,archerup3,archerup4,archerup5,archerup6,archerdown1,archerdown2,archerdown3,archerdown4,archerdown5,archerdown6,
            archerleft1,archerleft2,archerleft3,archerleft4,archerleft5,archerleft6,archerright1,archerright2,archerright3,archerright4,archerright5,archerright6;
    public BufferedImage sorceressup1,sorceressup2,sorceressup3,sorceressup4,sorceressup5,sorceressup6,sorceressdown1,sorceressdown2,sorceressdown3,sorceressdown4,sorceressdown5,sorceressdown6,
            sorceressleft1,sorceressleft2,sorceressleft3,sorceressleft4,sorceressleft5,sorceressleft6,sorceressright1,sorceressright2,sorceressright3,sorceressright4,sorceressright5,sorceressright6;
    public BufferedImage knightatkup1,knightatkup2,knightatkup3,knightatkup4,knightatkup5,knightatkdown1,knightatkdown2,knightatkdown3,knightatkdown4,knightatkdown5,
            knightatkleft1,knightatkleft2,knightatkleft3,knightatkleft4,knightatkleft5,knightatkright1,knightatkright2,knightatkright3,knightatkright4,knightatkright5;
    public BufferedImage archeratkup1,archeratkup2,archeratkup3,archeratkup4,archeratkup5,archeratkdown1,archeratkdown2,archeratkdown3,archeratkdown4,archeratkdown5,
            archeratkleft1,archeratkleft2,archeratkleft3,archeratkleft4,archeratkleft5,archeratkright1,archeratkright2,archeratkright3,archeratkright4,archeratkright5;
    public BufferedImage sorceressatkup1,sorceressatkup2,sorceressatkup3,sorceressatkup4,sorceressatkup5,sorceressatkdown1,sorceressatkdown2,sorceressatkdown3,sorceressatkdown4,sorceressatkdown5,
            sorceressatkleft1,sorceressatkleft2,sorceressatkleft3,sorceressatkleft4,sorceressatkleft5,sorceressatkright1,sorceressatkright2,sorceressatkright3,sorceressatkright4,sorceressatkright5;
    public int object =0;
    public int monster=0;
    public int npc = 0;
    public int checkpointX,checkpointY,checkpointLife,checkpointMana,checkpointLvl,checkpointMoney;
    public Entity checkpointCurrentWeapon,checkpointCurrentShield,checkpointCurrentBoots;
    public ArrayList<Entity> checkpointInventory = new ArrayList<>();
    public ArrayList<Entity> checkpointItem = new ArrayList<>();
    public boolean haveCheckpoint=false;
    //public boolean hasShoes = false;

    public Player(GamePanel gp,KeyDetech keyH){
        super(gp);
        this.gp=gp;
        this.keyH=keyH;
        screenX=gp.screenWidth/2-(gp.tileSize/2);
        screenY=gp.screenHeight/2-(gp.tileSize/2);
        solidArea = new Rectangle(30,30,14,25);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        //attackArea.width=gp.tileSize;
        //attackArea.height=gp.tileSize;
        setDefault();
        getPlayerImage();
        getAttackImage();
        setItems();
    }
    public void setDefault(){
        worldX= gp.tileSize*25;
        worldY=gp.tileSize*17;
        direction = "down";
        level = 1;
        strength = 2;
        dexterity = 1;
        exp=0;
        nextLvlExp=10;
        money=0;
        currentWeapon=new WoodSword(gp);
        currentShield=new WoodShield(gp);
        currentBoots=new Shoes(gp);
        projectile= new Fireball(gp);
        key=new Key(gp);
        baseAttack=2;
        attack=getAttack();
        baseDefense=1;
        defense=getDefense();
        magic=2;
        baseSpeed = 6;
        speed = getSpeed();
        baseAtkSpeed = 7;
        atkSpeed = getAtkSpeed();
        maxLife=25;
        life=maxLife;
        maxMana=5;
        mana=maxMana;
    }
    public void setItems(){
        inventory.clear();
        inventory.add(new WoodSword(gp));
        inventory.add(new WoodShield(gp));
        inventory.add(new Shoes(gp));
        inventory.add(key);
    }
    public void setDefaultPosition(){
        worldX= gp.tileSize*25;
        worldY=gp.tileSize*17;
        direction="down";
    }
    public void restoreCheckpointPosition(){
        worldX= checkpointX;
        worldY= checkpointY;
        direction="down";
    }
    public void restoreStat(){
        life=maxLife;
        mana=maxMana;
        level=1;
        money=0;
        invisible=false;
    }
    public void restoreCheckpointStat(){
        life=checkpointLife;
        mana=checkpointMana;
        level=checkpointLvl;
        money=checkpointMoney;
        invisible=false;
    }
    public void restoreCheckpointItems(){
        inventory=checkpointInventory;
    }
    public void setCheckpointItems(){
        for(int i=0;i<checkpointItem.size();i++) {
            gp.set.setter(gp.obj,gp.currentMap,checkpointItem.get(i).worldX,checkpointItem.get(i).worldY,
                    checkpointItem.get(i),object);
        }
    }
    public void checkpointCurrentItems(){
        currentWeapon=checkpointCurrentWeapon;
        currentShield=checkpointCurrentShield;
        currentBoots=checkpointCurrentBoots;
    }

    public int getAttack(){
        attackArea=currentWeapon.attackArea;
        return attack=baseAttack+(strength*currentWeapon.attackValue);
    }
    public int getDefense(){
        return defense=baseDefense+(dexterity*currentShield.defenseValue);
    }
    public int getSpeed() {
        return speed=baseSpeed+currentBoots.speedValue;
    }
    public int getAtkSpeed(){
        return atkSpeed=baseAtkSpeed-currentWeapon.atkSpeedValue;
    }

    public void getPlayerImage(){
        knightup1=setup("/Resource/image/chara/knight/walk/knightup/1");
        knightup2=setup("/Resource/image/chara/knight/walk/knightup/2");
        knightup3=setup("/Resource/image/chara/knight/walk/knightup/3");
        knightup4=setup("/Resource/image/chara/knight/walk/knightup/4");
        knightup5=setup("/Resource/image/chara/knight/walk/knightup/5");
        knightup6=setup("/Resource/image/chara/knight/walk/knightup/6");
        knightdown1=setup("/Resource/image/chara/knight/walk/knightdown/1");
        knightdown2=setup("/Resource/image/chara/knight/walk/knightdown/2");
        knightdown3=setup("/Resource/image/chara/knight/walk/knightdown/3");
        knightdown4=setup("/Resource/image/chara/knight/walk/knightdown/4");
        knightdown5=setup("/Resource/image/chara/knight/walk/knightdown/5");
        knightdown6=setup("/Resource/image/chara/knight/walk/knightdown/6");
        knightleft1=setup("/Resource/image/chara/knight/walk/knightleft/1");
        knightleft2=setup("/Resource/image/chara/knight/walk/knightleft/2");
        knightleft3=setup("/Resource/image/chara/knight/walk/knightleft/3");
        knightleft4=setup("/Resource/image/chara/knight/walk/knightleft/4");
        knightleft5=setup("/Resource/image/chara/knight/walk/knightleft/5");
        knightleft6=setup("/Resource/image/chara/knight/walk/knightleft/6");
        knightright1=setup("/Resource/image/chara/knight/walk/knightright/1");
        knightright2=setup("/Resource/image/chara/knight/walk/knightright/2");
        knightright3=setup("/Resource/image/chara/knight/walk/knightright/3");
        knightright4=setup("/Resource/image/chara/knight/walk/knightright/4");
        knightright5=setup("/Resource/image/chara/knight/walk/knightright/5");
        knightright6=setup("/Resource/image/chara/knight/walk/knightright/6");

        archerup1=setup("/Resource/image/chara/archer/walk/archerup/1");
        archerup2=setup("/Resource/image/chara/archer/walk/archerup/2");
        archerup3=setup("/Resource/image/chara/archer/walk/archerup/3");
        archerup4=setup("/Resource/image/chara/archer/walk/archerup/4");
        archerup5=setup("/Resource/image/chara/archer/walk/archerup/5");
        archerup6=setup("/Resource/image/chara/archer/walk/archerup/6");
        archerdown1=setup("/Resource/image/chara/archer/walk/archerdown/1");
        archerdown2=setup("/Resource/image/chara/archer/walk/archerdown/2");
        archerdown3=setup("/Resource/image/chara/archer/walk/archerdown/3");
        archerdown4=setup("/Resource/image/chara/archer/walk/archerdown/4");
        archerdown5=setup("/Resource/image/chara/archer/walk/archerdown/5");
        archerdown6=setup("/Resource/image/chara/archer/walk/archerdown/6");
        archerleft1=setup("/Resource/image/chara/archer/walk/archerleft/1");
        archerleft2=setup("/Resource/image/chara/archer/walk/archerleft/2");
        archerleft3=setup("/Resource/image/chara/archer/walk/archerleft/3");
        archerleft4=setup("/Resource/image/chara/archer/walk/archerleft/4");
        archerleft5=setup("/Resource/image/chara/archer/walk/archerleft/5");
        archerleft6=setup("/Resource/image/chara/archer/walk/archerleft/6");
        archerright1=setup("/Resource/image/chara/archer/walk/archerright/1");
        archerright2=setup("/Resource/image/chara/archer/walk/archerright/2");
        archerright3=setup("/Resource/image/chara/archer/walk/archerright/3");
        archerright4=setup("/Resource/image/chara/archer/walk/archerright/4");
        archerright5=setup("/Resource/image/chara/archer/walk/archerright/5");
        archerright6=setup("/Resource/image/chara/archer/walk/archerright/6");

        sorceressup1=setup("/Resource/image/chara/sorceress/walk/sorceressup/1");
        sorceressup2=setup("/Resource/image/chara/sorceress/walk/sorceressup/2");
        sorceressup3=setup("/Resource/image/chara/sorceress/walk/sorceressup/3");
        sorceressup4=setup("/Resource/image/chara/sorceress/walk/sorceressup/4");
        sorceressup5=setup("/Resource/image/chara/sorceress/walk/sorceressup/5");
        sorceressup6=setup("/Resource/image/chara/sorceress/walk/sorceressup/6");
        sorceressdown1=setup("/Resource/image/chara/sorceress/walk/sorceressdown/1");
        sorceressdown2=setup("/Resource/image/chara/sorceress/walk/sorceressdown/2");
        sorceressdown3=setup("/Resource/image/chara/sorceress/walk/sorceressdown/3");
        sorceressdown4=setup("/Resource/image/chara/sorceress/walk/sorceressdown/4");
        sorceressdown5=setup("/Resource/image/chara/sorceress/walk/sorceressdown/5");
        sorceressdown6=setup("/Resource/image/chara/sorceress/walk/sorceressdown/6");
        sorceressleft1=setup("/Resource/image/chara/sorceress/walk/sorceressleft/1");
        sorceressleft2=setup("/Resource/image/chara/sorceress/walk/sorceressleft/2");
        sorceressleft3=setup("/Resource/image/chara/sorceress/walk/sorceressleft/3");
        sorceressleft4=setup("/Resource/image/chara/sorceress/walk/sorceressleft/4");
        sorceressleft5=setup("/Resource/image/chara/sorceress/walk/sorceressleft/5");
        sorceressleft6=setup("/Resource/image/chara/sorceress/walk/sorceressleft/6");
        sorceressright1=setup("/Resource/image/chara/sorceress/walk/sorceressright/1");
        sorceressright2=setup("/Resource/image/chara/sorceress/walk/sorceressright/2");
        sorceressright3=setup("/Resource/image/chara/sorceress/walk/sorceressright/3");
        sorceressright4=setup("/Resource/image/chara/sorceress/walk/sorceressright/4");
        sorceressright5=setup("/Resource/image/chara/sorceress/walk/sorceressright/5");
        sorceressright6=setup("/Resource/image/chara/sorceress/walk/sorceressright/6");
    }
    public void getAttackImage(){
        knightatkup1=setup("/Resource/image/chara/knight/atk/knightup/1");
        knightatkup2=setup("/Resource/image/chara/knight/atk/knightup/2");
        knightatkup3=setup("/Resource/image/chara/knight/atk/knightup/3");
        knightatkup4=setup("/Resource/image/chara/knight/atk/knightup/4");
        knightatkup5=setup("/Resource/image/chara/knight/atk/knightup/5");
        knightatkdown1=setup("/Resource/image/chara/knight/atk/knightdown/1");
        knightatkdown2=setup("/Resource/image/chara/knight/atk/knightdown/2");
        knightatkdown3=setup("/Resource/image/chara/knight/atk/knightdown/3");
        knightatkdown4=setup("/Resource/image/chara/knight/atk/knightdown/4");
        knightatkdown5=setup("/Resource/image/chara/knight/atk/knightdown/5");
        knightatkleft1=setup("/Resource/image/chara/knight/atk/knightleft/1");
        knightatkleft2=setup("/Resource/image/chara/knight/atk/knightleft/2");
        knightatkleft3=setup("/Resource/image/chara/knight/atk/knightleft/3");
        knightatkleft4=setup("/Resource/image/chara/knight/atk/knightleft/4");
        knightatkleft5=setup("/Resource/image/chara/knight/atk/knightleft/5");
        knightatkright1=setup("/Resource/image/chara/knight/atk/knightright/1");
        knightatkright2=setup("/Resource/image/chara/knight/atk/knightright/2");
        knightatkright3=setup("/Resource/image/chara/knight/atk/knightright/3");
        knightatkright4=setup("/Resource/image/chara/knight/atk/knightright/4");
        knightatkright5=setup("/Resource/image/chara/knight/atk/knightright/5");

        archeratkup1=setup("/Resource/image/chara/archer/atk/archerup/1");
        archeratkup2=setup("/Resource/image/chara/archer/atk/archerup/2");
        archeratkup3=setup("/Resource/image/chara/archer/atk/archerup/3");
        archeratkup4=setup("/Resource/image/chara/archer/atk/archerup/4");
        archeratkup5=setup("/Resource/image/chara/archer/atk/archerup/5");
        archeratkdown1=setup("/Resource/image/chara/archer/atk/archerdown/1");
        archeratkdown2=setup("/Resource/image/chara/archer/atk/archerdown/2");
        archeratkdown3=setup("/Resource/image/chara/archer/atk/archerdown/3");
        archeratkdown4=setup("/Resource/image/chara/archer/atk/archerdown/4");
        archeratkdown5=setup("/Resource/image/chara/archer/atk/archerdown/5");
        archeratkleft1=setup("/Resource/image/chara/archer/atk/archerleft/1");
        archeratkleft2=setup("/Resource/image/chara/archer/atk/archerleft/2");
        archeratkleft3=setup("/Resource/image/chara/archer/atk/archerleft/3");
        archeratkleft4=setup("/Resource/image/chara/archer/atk/archerleft/4");
        archeratkleft5=setup("/Resource/image/chara/archer/atk/archerleft/5");
        archeratkright1=setup("/Resource/image/chara/archer/atk/archerright/1");
        archeratkright2=setup("/Resource/image/chara/archer/atk/archerright/2");
        archeratkright3=setup("/Resource/image/chara/archer/atk/archerright/3");
        archeratkright4=setup("/Resource/image/chara/archer/atk/archerright/4");
        archeratkright5=setup("/Resource/image/chara/archer/atk/archerright/5");

        sorceressatkup1=setup("/Resource/image/chara/sorceress/atk/sorceressup/1");
        sorceressatkup2=setup("/Resource/image/chara/sorceress/atk/sorceressup/2");
        sorceressatkup3=setup("/Resource/image/chara/sorceress/atk/sorceressup/3");
        sorceressatkup4=setup("/Resource/image/chara/sorceress/atk/sorceressup/4");
        sorceressatkup5=setup("/Resource/image/chara/sorceress/atk/sorceressup/5");
        sorceressatkdown1=setup("/Resource/image/chara/sorceress/atk/sorceressdown/1");
        sorceressatkdown2=setup("/Resource/image/chara/sorceress/atk/sorceressdown/2");
        sorceressatkdown3=setup("/Resource/image/chara/sorceress/atk/sorceressdown/3");
        sorceressatkdown4=setup("/Resource/image/chara/sorceress/atk/sorceressdown/4");
        sorceressatkdown5=setup("/Resource/image/chara/sorceress/atk/sorceressdown/5");
        sorceressatkleft1=setup("/Resource/image/chara/sorceress/atk/sorceressleft/1");
        sorceressatkleft2=setup("/Resource/image/chara/sorceress/atk/sorceressleft/2");
        sorceressatkleft3=setup("/Resource/image/chara/sorceress/atk/sorceressleft/3");
        sorceressatkleft4=setup("/Resource/image/chara/sorceress/atk/sorceressleft/4");
        sorceressatkleft5=setup("/Resource/image/chara/sorceress/atk/sorceressleft/5");
        sorceressatkright1=setup("/Resource/image/chara/sorceress/atk/sorceressright/1");
        sorceressatkright2=setup("/Resource/image/chara/sorceress/atk/sorceressright/2");
        sorceressatkright3=setup("/Resource/image/chara/sorceress/atk/sorceressright/3");
        sorceressatkright4=setup("/Resource/image/chara/sorceress/atk/sorceressright/4");
        sorceressatkright5=setup("/Resource/image/chara/sorceress/atk/sorceressright/5");

    }

    public void update(){
        if(attacking==true){
            attacking();
        }
        else if(keyH.upPressed==true|| keyH.downPressed==true|| keyH.leftPressed==true
                || keyH.rightPressed==true||keyH.enterPressed==true){
            if(keyH.upPressed){
                direction="up";
            }else if(keyH.downPressed){
                direction="down";
            }else if(keyH.leftPressed){
                direction="left";
            }else if(keyH.rightPressed){
                direction="right";
            }
            //Check collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            int objIndex = gp.cChecker.checkObj(this,true);
            getObj(objIndex);
            int npcIndex = gp.cChecker.checkBump(this,gp.npc);
            interactNPC(npcIndex);
            int monsterIndex = gp.cChecker.checkBump(this,gp.monster);
            contactMonster(monsterIndex);
            gp.event.checkEvent();
            //Can move if collision = false
            if(collisionOn==false&&keyH.enterPressed==false){
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
            if(keyH.enterPressed==true&&attackCanceled==false){
                attacking=true;
                spriteCounter=0;
            }
            attackCanceled=false;
            gp.keyH.enterPressed=false;
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
                    spriteNum=1;
                }
                spriteCounter=0;
            }
        }
        if(gp.keyH.shootPressed==true&&projectile.alive==false
                &&shotAvailable==30&& projectile.haveResource(this)==true){
            switch (direction){
                case "up":
                    projectile.set(worldX+12,worldY-15,direction,true,this);
                    break;
                case "down":
                    projectile.set(worldX+12,worldY+25,direction,true,this);
                    break;
                case "left":
                    projectile.set(worldX-10,worldY+12,direction,true,this);
                    break;
                case "right":
                    projectile.set(worldX+35,worldY+12,direction,true,this);
                    break;
            }
            projectile.subtractResource(this);
            gp.projectileList.add(projectile);
            shotAvailable=0;
        }
        if(invisible==true){
            invisibleCount++;
            if(invisibleCount>60){
                invisible=false;
                invisibleCount=0;
            }
        }
        if(mana<maxMana){
            manaCount++;
            if(manaCount>180){
                mana++;
                manaCount=0;
            }
        }
        if(shotAvailable<30){
            shotAvailable++;
        }
        if(throwObject==true){
            //throwObject();
        }
        if(life<=0){
            gp.gameState=gp.gameoverState;
        }
    }

    public void attacking(){
        spriteCounter++;
        if(spriteCounter<=atkSpeed){
            spriteNum=1;
        }
        if(spriteCounter>atkSpeed&&spriteCounter<=atkSpeed*2){
            spriteNum=2;
            int currentWorldX=worldX;
            int currentWorldY=worldY;
            int solidAreaWidth=solidArea.width;
            int solidAreaHeight=solidArea.height;

            switch (direction){
                case "up":worldY-=attackArea.height;break;
                case "down":worldY+=attackArea.height;break;
                case "left":worldX-=attackArea.width;break;
                case "right":worldX+=attackArea.width;break;
            }
            solidArea.width=attackArea.width;
            solidArea.height=attackArea.height;

            int monsterIndex=gp.cChecker.checkBump(this,gp.monster);
            damageMonster(monsterIndex,attack);

            worldX=currentWorldX;
            worldY=currentWorldY;
            solidArea.width=solidAreaWidth;
            solidArea.height=solidAreaHeight;
        }
        if(spriteCounter>atkSpeed*2&&spriteNum<=atkSpeed*3){
            spriteNum=3;
        }
        if(spriteCounter>atkSpeed*3&&spriteCounter<=atkSpeed*4){
            spriteNum=4;
        }
        if(spriteCounter>atkSpeed*4&&spriteCounter<=atkSpeed*5){
            spriteNum=5;
        }
        if(spriteCounter>atkSpeed*5){
            spriteNum=1;
            spriteCounter=0;
            attacking=false;
        }
    }
    public void getObj(int i){
        if(i!=999){
            if(gp.obj[gp.currentMap][i].type==type_pickup) {
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i]=null;
            }
            else if(gp.obj[gp.currentMap][i].type==type_none){

            }
            else{
                String text;
                if(inventory.size()!=inventorySize) {
                    inventory.add(gp.obj[gp.currentMap][i]);
                    text="Got "+gp.obj[gp.currentMap][i].name+"!";
                }else{
                    text="Your bag is full.";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i]=null;
            }
        }
    }
    public void interactNPC(int i){
        if(gp.keyH.enterPressed==true) {
            if (i != 999) {
                    attackCanceled=true;
                    gp.gameState = gp.dialogueState;
                    gp.npc[gp.currentMap][i].speak();
            }
        }
    }
    public void contactMonster(int i){
        if(i!=999){
            if(invisible==false&&gp.monster[gp.currentMap][i].dying==false) {
                int damage=gp.monster[gp.currentMap][i].attack-defense;
                if(damage<0){
                    damage=0;
                }
                gp.player.life-=damage;
                if(life<0){
                    life=0;
                }
                if(damage>0) {
                    gp.ui.addMessage("Taken " + damage + " damage!");
                }
                else{
                    gp.ui.addMessage("Dodged!");
                }
                invisible=true;
            }
        }
    }
    public void damageMonster(int i,int attack){
        if(i!=999){
            if(gp.monster[gp.currentMap][i].invisible==false){
                    if (backStab(i) == false) {
                        int damage = attack - gp.monster[gp.currentMap][i].defense;
                        if (damage < 0) {
                            damage = 0;
                        }

                        gp.monster[gp.currentMap][i].life -= damage;
                        gp.ui.addMessage(damage + " damage!");
                    } else {
                        int damage = attack;
                        if (damage < 0) {
                            damage = 0;
                        }

                        gp.monster[gp.currentMap][i].life -= damage;
                        gp.ui.addMessage("Critical!");
                        gp.ui.addMessage(damage + " damage!");
                    }
                gp.monster[gp.currentMap][i].invisible=true;
                gp.monster[gp.currentMap][i].dmgReaction();
                if(gp.monster[gp.currentMap][i].life<=0){
                    gp.monster[gp.currentMap][i].dying=true;
                    gp.ui.addMessage("Killed "+gp.monster[gp.currentMap][i].name+"!");
                    exp+=gp.monster[gp.currentMap][i].exp;
                    gp.ui.addMessage("Exp +"+gp.monster[gp.currentMap][i].exp+"!");
                    checkLvlUp();
                }
            }
        }
    }
    public boolean backStab(int i){
        boolean backStab=false;
        if(gp.player.direction.equals(gp.monster[gp.currentMap][i].direction)){
            backStab=true;
        }
        return backStab;
    }
    public void checkLvlUp(){
        while(exp>=nextLvlExp){
            level++;
            exp=abs(nextLvlExp-exp);
            nextLvlExp*=1.5;
            maxLife+=2;
            life=maxLife;
            strength++;
            dexterity++;
            magic++;
            attack=getAttack();
            defense=getDefense();
            gp.ui.addMessage("Level up!");
            gp.ui.addMessage("You feel stronger.");
        }
    }
    public void selectItem(){
        int itemIndex=gp.ui.getItemIndex();
        if(itemIndex<inventory.size()){
            Entity selectedItem=inventory.get(itemIndex);
            if(selectedItem.type==type_sword){
                currentWeapon=selectedItem;
                attack=getAttack();
                atkSpeed=getAtkSpeed();
            }
            if(selectedItem.type==type_shield){
                currentShield=selectedItem;
                defense=getDefense();
            }
            if(selectedItem.type==type_shoes){
                currentBoots=selectedItem;
                speed=getSpeed();
            }
            if(selectedItem.type==type_consumable){
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }
    /*public void throwObject(){
        final int type_sword=3;
        final int type_shield=4;
        final int type_shoes=5;
        final int type_consumable=6;
        final int type_item=6;
        int i=object;
        int itemIndex=gp.ui.getItemIndex();
        if(itemIndex<gp.player.inventory.size()){
            Entity selectedItem=gp.player.inventory.get(itemIndex);
            if(selectedItem.type==type_sword||selectedItem.type==type_shield||selectedItem.type==type_shoes
                    ||selectedItem.type==type_consumable||selectedItem.type==type_item){
                gp.obj[i]=selectedItem;
                switch(gp.player.direction) {
                    case "up":
                        gp.obj[i].worldX = gp.player.worldX;
                        gp.obj[i].worldY = gp.player.worldY - gp.tileSize;
                    case "down":
                        gp.obj[i].worldX = gp.player.worldX;
                        gp.obj[i].worldY = gp.player.worldY + gp.tileSize;
                    case "left":
                        gp.obj[i].worldX = gp.player.worldX - gp.tileSize;
                        gp.obj[i].worldY = gp.player.worldY;
                    case "right":
                        gp.obj[i].worldX = gp.player.worldX + gp.tileSize;
                        gp.obj[i].worldY = gp.player.worldY;
                }
                for(int x=0;x<object;x++){
                    if(Math.abs(gp.obj[i].worldX-gp.obj[x].worldX)<gp.tileSize
                            &&Math.abs(gp.obj[i].worldY-gp.obj[x].worldY)<gp.tileSize){
                        gp.obj[i].worldX += gp.tileSize;
                        gp.obj[i].worldY += gp.tileSize;
                    }
                }
                gp.player.inventory.remove(itemIndex);
                object++;
            }
        }
        throwObject=false;
    }*/
    public void draw(Graphics2D g2) {
        //g2.setColor(Color.white);
        //g2.fillRect(gp.player.worldX+gp.player.solidArea.x, gp.player.worldY+gp.player.solidArea.y, 16, 27);
        //g2.fillRect(gp.player.worldX,gp.player.worldY,16*3, 23*3);

        BufferedImage image = null;
        switch (gp.ui.character) {
            case "knight":
                switch (direction) {
                    case "up":
                        if(attacking==false){
                            if (spriteNum == 1) {image = knightup1;}
                            if (spriteNum == 2) {image = knightup2;}
                            if (spriteNum == 3) {image = knightup3;}
                            if (spriteNum == 4) {image = knightup4;}
                            if (spriteNum == 5) {image = knightup5;}
                            if (spriteNum == 6) {image = knightup6;}
                        }else{
                            if (spriteNum == 1) {image = knightatkup1;}
                            if (spriteNum == 2) {image = knightatkup2;}
                            if (spriteNum == 3) {image = knightatkup3;}
                            if (spriteNum == 4) {image = knightatkup4;}
                            if (spriteNum == 5) {image = knightatkup5;}
                        }
                        break;
                    case "down":
                        if(attacking==false){
                            if (spriteNum == 1) {image = knightdown1;}
                            if (spriteNum == 2) {image = knightdown2;}
                            if (spriteNum == 3) {image = knightdown3;}
                            if (spriteNum == 4) {image = knightdown4;}
                            if (spriteNum == 5) {image = knightdown5;}
                            if (spriteNum == 6) {image = knightdown6;}
                        }else{
                            if (spriteNum == 1) {image = knightatkdown1;}
                            if (spriteNum == 2) {image = knightatkdown2;}
                            if (spriteNum == 3) {image = knightatkdown3;}
                            if (spriteNum == 4) {image = knightatkdown4;}
                            if (spriteNum == 5) {image = knightatkdown5;}
                        }
                        break;
                    case "left":
                        if(attacking==false){
                            if (spriteNum == 1) {image = knightleft1;}
                            if (spriteNum == 2) {image = knightleft2;}
                            if (spriteNum == 3) {image = knightleft3;}
                            if (spriteNum == 4) {image = knightleft4;}
                            if (spriteNum == 5) {image = knightleft5;}
                            if (spriteNum == 6) {image = knightleft6;}
                        }else{
                            if (spriteNum == 1) {image = knightatkleft1;}
                            if (spriteNum == 2) {image = knightatkleft2;}
                            if (spriteNum == 3) {image = knightatkleft3;}
                            if (spriteNum == 4) {image = knightatkleft4;}
                            if (spriteNum == 5) {image = knightatkleft5;}
                        }
                        break;
                    case "right":
                        if(attacking==false){
                            if (spriteNum == 1) {image = knightright1;}
                            if (spriteNum == 2) {image = knightright2;}
                            if (spriteNum == 3) {image = knightright3;}
                            if (spriteNum == 4) {image = knightright4;}
                            if (spriteNum == 5) {image = knightright5;}
                            if (spriteNum == 6) {image = knightright6;}
                        }else{
                            if (spriteNum == 1) {image = knightatkright1;}
                            if (spriteNum == 2) {image = knightatkright2;}
                            if (spriteNum == 3) {image = knightatkright3;}
                            if (spriteNum == 4) {image = knightatkright4;}
                            if (spriteNum == 5) {image = knightatkright5;}
                        }
                        break;
                }
                break;
            case "archer":
                switch (direction) {
                    case "up":
                        if (attacking == false) {
                            if (spriteNum == 1) {image = archerup1;}
                            if (spriteNum == 2) {image = archerup2;}
                            if (spriteNum == 3) {image = archerup3;}
                            if (spriteNum == 4) {image = archerup4;}
                            if (spriteNum == 5) {image = archerup5;}
                            if (spriteNum == 6) {image = archerup6;}
                        }
                        else{
                            if (spriteNum == 1) {image = archeratkup1;}
                            if (spriteNum == 2) {image = archeratkup2;}
                            if (spriteNum == 3) {image = archeratkup3;}
                            if (spriteNum == 4) {image = archeratkup4;}
                            if (spriteNum == 5) {image = archeratkup5;}
                        }
                        break;
                    case "down":
                        if (attacking == false) {
                            if (spriteNum == 1) {image = archerdown1;}
                            if (spriteNum == 2) {image = archerdown2;}
                            if (spriteNum == 3) {image = archerdown3;}
                            if (spriteNum == 4) {image = archerdown4;}
                            if (spriteNum == 5) {image = archerdown5;}
                            if (spriteNum == 6) {image = archerdown6;}
                        }
                        else{
                            if (spriteNum == 1) {image = archeratkdown1;}
                            if (spriteNum == 2) {image = archeratkdown2;}
                            if (spriteNum == 3) {image = archeratkdown3;}
                            if (spriteNum == 4) {image = archeratkdown4;}
                            if (spriteNum == 5) {image = archeratkdown5;}
                        }
                        break;
                    case "left":
                        if (attacking == false) {
                            if (spriteNum == 1) {image = archerleft1;}
                            if (spriteNum == 2) {image = archerleft2;}
                            if (spriteNum == 3) {image = archerleft3;}
                            if (spriteNum == 4) {image = archerleft4;}
                            if (spriteNum == 5) {image = archerleft5;}
                            if (spriteNum == 6) {image = archerleft6;}
                        }
                        else{
                            if (spriteNum == 1) {image = archeratkleft1;}
                            if (spriteNum == 2) {image = archeratkleft2;}
                            if (spriteNum == 3) {image = archeratkleft3;}
                            if (spriteNum == 4) {image = archeratkleft4;}
                            if (spriteNum == 5) {image = archeratkleft5;}
                        }
                        break;
                    case "right":
                        if (attacking == false) {
                            if (spriteNum == 1) {image = archerright1;}
                            if (spriteNum == 2) {image = archerright2;}
                            if (spriteNum == 3) {image = archerright3;}
                            if (spriteNum == 4) {image = archerright4;}
                            if (spriteNum == 5) {image = archerright5;}
                            if (spriteNum == 6) {image = archerright6;}
                        }
                        else{
                            if (spriteNum == 1) {image = archeratkright1;}
                            if (spriteNum == 2) {image = archeratkright2;}
                            if (spriteNum == 3) {image = archeratkright3;}
                            if (spriteNum == 4) {image = archeratkright4;}
                            if (spriteNum == 5) {image = archeratkright5;}
                        }
                        break;
                }
                break;
            case "sorceress":
                switch (direction) {
                    case "up":
                        if (attacking == false) {
                            if (spriteNum == 1) {image = sorceressup1;}
                            if (spriteNum == 2) {image = sorceressup2;}
                            if (spriteNum == 3) {image = sorceressup3;}
                            if (spriteNum == 4) {image = sorceressup4;}
                            if (spriteNum == 5) {image = sorceressup5;}
                            if (spriteNum == 6) {image = sorceressup6;}
                        }
                        else{
                            if (spriteNum == 1) {image = sorceressatkup1;}
                            if (spriteNum == 2) {image = sorceressatkup2;}
                            if (spriteNum == 3) {image = sorceressatkup3;}
                            if (spriteNum == 4) {image = sorceressatkup4;}
                            if (spriteNum == 5) {image = sorceressatkup5;}
                        }
                        break;
                    case "down":
                        if (attacking == false) {
                            if (spriteNum == 1) {image = sorceressdown1;}
                            if (spriteNum == 2) {image = sorceressdown2;}
                            if (spriteNum == 3) {image = sorceressdown3;}
                            if (spriteNum == 4) {image = sorceressdown4;}
                            if (spriteNum == 5) {image = sorceressdown5;}
                            if (spriteNum == 6) {image = sorceressdown6;}
                        }
                        else{
                            if (spriteNum == 1) {image = sorceressatkdown1;}
                            if (spriteNum == 2) {image = sorceressatkdown2;}
                            if (spriteNum == 3) {image = sorceressatkdown3;}
                            if (spriteNum == 4) {image = sorceressatkdown4;}
                            if (spriteNum == 5) {image = sorceressatkdown5;}
                        }
                        break;
                    case "left":
                        if (attacking == false) {
                            if (spriteNum == 1) {image = sorceressleft1;}
                            if (spriteNum == 2) {image = sorceressleft2;}
                            if (spriteNum == 3) {image = sorceressleft3;}
                            if (spriteNum == 4) {image = sorceressleft4;}
                            if (spriteNum == 5) {image = sorceressleft5;}
                            if (spriteNum == 6) {image = sorceressleft6;}
                        }
                        else{
                            if (spriteNum == 1) {image = sorceressatkleft1;}
                            if (spriteNum == 2) {image = sorceressatkleft2;}
                            if (spriteNum == 3) {image = sorceressatkleft3;}
                            if (spriteNum == 4) {image = sorceressatkleft4;}
                            if (spriteNum == 5) {image = sorceressatkleft5;}
                        }
                        break;
                    case "right":
                        if (attacking == false) {
                        if (spriteNum == 1) {image = sorceressright1;}
                        if (spriteNum == 2) {image = sorceressright2;}
                        if (spriteNum == 3) {image = sorceressright3;}
                        if (spriteNum == 4) {image = sorceressright4;}
                        if (spriteNum == 5) {image = sorceressright5;}
                        if (spriteNum == 6) {image = sorceressright6;}
                    }
                    else{
                        if (spriteNum == 1) {image = sorceressatkright1;}
                        if (spriteNum == 2) {image = sorceressatkright2;}
                        if (spriteNum == 3) {image = sorceressatkright3;}
                        if (spriteNum == 4) {image = sorceressatkright4;}
                        if (spriteNum == 5) {image = sorceressatkright5;}
                    }
                        break;
                }
                break;
        }
        if(invisible==true){
            changeVis(g2,0.5f);
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize + 24, gp.tileSize + 24, null);
        changeVis(g2,1f);
    }
}
