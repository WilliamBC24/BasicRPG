package main;

import Entity.Entity;
import Object.Health;
import java.awt.*;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font didot40,didot80,proxima25,chiller170,chiller420,exocet;
    public boolean msgOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean win = false;
    public String currentDialog="";
    public int commandNum=0;
    public int titleScreen=0;
    public int slotCol=0;
    public int slotRow=0;
    public int subState=0;
    public int counter=0;
    BufferedImage knight,archer,sorceress;
    BufferedImage heartFull,heartHalf,heartEmpty;
    public String character;
    Entity entity = new Entity(gp);
    public Entity npc;
    public UI(GamePanel gp){
        this.gp=gp;
        didot40 = new Font("Didot", Font.PLAIN, 25);
        didot80 = new Font("Didot", Font.BOLD, 40);
        proxima25 = new Font("Proxima Nova",Font.PLAIN,25);
        chiller170 = new Font("Chiller",Font.BOLD,170);
        chiller420 = new Font("Chiller",Font.BOLD,420);

        InputStream is = getClass().getResourceAsStream("/Resource/font/films.EXL_____.ttf");
        try {
            exocet = Font.createFont(Font.TRUETYPE_FONT, is);
        }catch(FontFormatException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

        Entity health = new Health(gp);
        heartFull=health.image1;
        heartHalf=health.image2;
        heartEmpty=health.image3;
    }



    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(didot40);
        g2.setColor(Color.black);

        if (gp.gameState == gp.titleState) {
            drawTitle();
        } else if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawPlayerMana();
            drawMessage();
        } else if (gp.gameState == gp.pauseState) {
            g2.setColor(new Color(0, 0, 0, 90));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            drawPause();
        } else if (gp.gameState == gp.dialogueState) {
            drawDialog();
        } else if (gp.gameState == gp.characterState) {
            drawCharacterMenu();
            drawInventory();
        } else if (gp.gameState == gp.optionsState) {
            drawOptionsMenu();
        } else if (gp.gameState == gp.gameoverState) {
            drawGameOver();
        } else if (gp.gameState == gp.transitionState) {
            drawTransition();
        }else if (gp.gameState == gp.tradeState) {
            drawTrade();
        }
    }
    public void drawPlayerLife(){
        /*int x=gp.tileSize/2;
        int y=gp.tileSize/2;
        int i;

        for(i=0;i<gp.player.maxLife/2;i++){
            g2.drawImage(heartEmpty,x,y,null);
            x+=gp.tileSize;
        }
        x=gp.tileSize/2;
        y=gp.tileSize/2;
        i=0;
        while(i<gp.player.life){
            g2.drawImage(heartHalf,x,y,null);
            i++;
            if(i<gp.player.life){
                g2.drawImage(heartFull,x,y,null);
            }
            i++;
            x+=gp.tileSize;
        }*/
        double oneScale = (double) gp.tileSize*7/ gp.player.maxLife;
        double hpBarValue = oneScale * gp.player.life;
        Color c=new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(gp.tileSize/2, gp.tileSize/2, gp.tileSize*7, gp.tileSize,30,30);
        g2.setColor(new Color(65, 65, 65));
        g2.fillRoundRect(gp.tileSize/2, gp.tileSize/2, gp.tileSize*7, gp.tileSize,30,30);
        g2.setColor(new Color(136, 8, 8));
        g2.fillRoundRect(gp.tileSize/2, gp.tileSize/2, (int)hpBarValue, gp.tileSize,30,30);
        g2.setColor(new Color(220,20,60));
        g2.setFont(exocet);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        g2.drawString(gp.player.life+"/"+gp.player.maxLife,(gp.tileSize/2)+12,(gp.tileSize/2)+32);
    }
    public void drawPlayerMana(){
        double oneScale = (double) gp.tileSize*4/ gp.player.maxMana;
        double manaBarValue = oneScale * gp.player.mana;
        Color c=new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(gp.tileSize/2, gp.tileSize+30, gp.tileSize*4, gp.tileSize/2,18,18);
        g2.setColor(new Color(65, 65, 65));
        g2.fillRoundRect(gp.tileSize/2, gp.tileSize+30, gp.tileSize*4, gp.tileSize/2,18,18);
        g2.setColor(new Color(0, 100, 220));
        g2.fillRoundRect(gp.tileSize/2, gp.tileSize+30, (int)manaBarValue, gp.tileSize/2,18,18);
        g2.setColor(new Color(70,50,150));
        g2.setFont(exocet);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,22F));
        g2.drawString(gp.player.mana+"/"+gp.player.maxMana,(gp.tileSize/2)+12,gp.tileSize*2);
    }
    public void drawTitle(){
        if(titleScreen==0) {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            Color c = new Color(220, 20, 60);
            g2.setFont(chiller420);
            String text = ("II");
            int x = getCenter(text);
            int y = gp.tileSize * 7;
            g2.setColor(Color.red);
            g2.drawString(text, x, y);
            g2.setFont(chiller170);
            text = ("Not Diablo");
            x = getCenter(text);
            y = gp.tileSize * 5;
            g2.setColor(Color.white);
            g2.drawString(text, x + 3, y + 3);
            g2.setColor(c);
            g2.drawString(text, x, y);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25));
            text = "New game";
            x = getCenter(text);
            y += gp.tileSize * 4;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.setFont(didot40);
                g2.drawString(">", x - gp.tileSize, y);
            }
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25));
            text = "Load game";
            x = getCenter(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.setFont(didot40);
                g2.drawString(">", x - gp.tileSize, y);
            }
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25));
            text = "Exit game";
            x = getCenter(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.setFont(didot40);
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
        else if(titleScreen==1){
            BufferedImage image,image1,image2,image3,image4,image5,image6;
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            g2.setColor(Color.red);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));
            String text = "Select your class";
            int x=getCenter(text);
            int y=gp.tileSize*3;
            g2.drawString(text,x,y);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25));
            text="Knight";
            x=getCenter(text);
            y+=gp.tileSize*3;
            g2.drawString(text,x,y);
            if (commandNum == 1) {
                character="knight";
                g2.setFont(didot40);
                g2.drawString(">", x - gp.tileSize, y);
                image=entity.setup("/Resource/image/npc/knight/6");
                g2.drawImage(image,gp.player.screenX+gp.tileSize*4,gp.player.screenY-gp.tileSize,gp.tileSize*3, gp.tileSize*3, null);
            }
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25));
            text="Archer";
            x=getCenter(text);
            y+=gp.tileSize;
            g2.drawString(text,x,y);
            if (commandNum == 2) {
                character="archer";
                g2.setFont(didot40);
                g2.drawString(">", x - gp.tileSize, y);
                image=entity.setup("/Resource/image/npc/archer/6");
                g2.drawImage(image,gp.player.screenX+gp.tileSize*4,gp.player.screenY-gp.tileSize,gp.tileSize*3, gp.tileSize*3, null);
            }
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25));
            text="Sorceress";
            x=getCenter(text);
            y+=gp.tileSize;
            g2.drawString(text,x,y);
            if (commandNum == 3) {
                character="sorceress";
                g2.setFont(didot40);
                g2.drawString(">", x - gp.tileSize, y);
                image=entity.setup("/Resource/image/npc/sorceress/6");
                g2.drawImage(image,gp.player.screenX+gp.tileSize*4,gp.player.screenY-gp.tileSize,gp.tileSize*3, gp.tileSize*3, null);
            }
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25));
            text="Back";
            x=getCenter(text);
            y+=gp.tileSize;
            g2.drawString(text,x,y);
            if (commandNum == 4) {
                g2.setFont(didot40);
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
    }
    public void drawPause(){
        String text = "Paused.";
        g2.setFont(didot80);
        int x=getCenter(text);
        int y=gp.screenHeight/2;
        g2.drawString(text,x,y);
    }
    public void drawDialog(){
        int x=gp.tileSize*2+3;
        int y=gp.tileSize/2;
        int width=gp.screenWidth-gp.tileSize*4;
        int height=gp.tileSize*4;
        drawSubWindow(x,y,width,height);

        g2.setFont(exocet);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20));
        x+=gp.tileSize;
        y+=gp.tileSize;
        for(String line :currentDialog.split("\n")){
            g2.drawString(line,x,y);
            y+=40;
        }
    }
    public void drawCharacterMenu(){
        final int frameX=gp.tileSize*2;
        final int frameY=gp.tileSize;
        final int frameWidth=gp.tileSize*7;
        final int frameHeight=gp.tileSize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);
        g2.setColor(Color.WHITE);
        g2.setFont(exocet);
        g2.setFont(g2.getFont().deriveFont(20F));

        int textX=frameX+20;
        int textY=frameY+35;
        final int lineHeight=30;

        g2.drawString("Level",textX,textY);
        textY+=lineHeight;
        g2.drawString("Life",textX,textY);
        textY+=lineHeight;
        g2.drawString("Mana",textX,textY);
        textY+=lineHeight;
        g2.drawString("Exp",textX,textY);
        textY+=lineHeight;
        g2.drawString("Money",textX,textY);
        textY+=lineHeight;
        g2.drawString("Strength",textX,textY);
        textY+=lineHeight;
        g2.drawString("Dexterity",textX,textY);
        textY+=lineHeight;
        g2.drawString("Attack",textX,textY);
        textY+=lineHeight;
        g2.drawString("Defense",textX,textY);
        textY+=lineHeight;
        g2.drawString("Magic",textX,textY);
        textY+=lineHeight;
        g2.drawString("Weapon",textX,textY);
        textY+=gp.tileSize;
        g2.drawString("Shield",textX,textY);
        textY+=gp.tileSize;
        g2.drawString("Boots",textX,textY);

        int tailX=(frameX+frameWidth)-30;
        textY=frameY+35;
        String value;

        value=String.valueOf(gp.player.level);
        textX=getRight(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
        value=String.valueOf(gp.player.life+"/"+gp.player.maxLife);
        textX=getRight(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
        value=String.valueOf(gp.player.mana+"/"+gp.player.maxMana);
        textX=getRight(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
        value=String.valueOf(gp.player.exp+"/"+gp.player.nextLvlExp);
        textX=getRight(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
        value=String.valueOf(gp.player.money);
        textX=getRight(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
        value=String.valueOf(gp.player.strength);
        textX=getRight(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
        value=String.valueOf(gp.player.dexterity);
        textX=getRight(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
        value=String.valueOf(gp.player.attack);
        textX=getRight(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
        value=String.valueOf(gp.player.defense);
        textX=getRight(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
        value=String.valueOf(gp.player.magic);
        textX=getRight(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
        g2.drawImage(gp.player.currentWeapon.down1,tailX-30,textY-20,null);
        textY+=lineHeight;
        g2.drawImage(gp.player.currentShield.down1,tailX-30,textY,null);
        textY+=lineHeight+20;
        g2.drawImage(gp.player.currentBoots.down1,tailX-30,textY,null);
    }
    public void drawInventory(){
        int frameX=gp.tileSize*12;
        int frameY=gp.tileSize;
        int frameWidth=gp.tileSize*6;
        int frameHeight=gp.tileSize*5;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        final int slotXstart=frameX+20;
        final int slotYstart=frameY+20;
        int slotX=slotXstart;
        int slotY=slotYstart;
        int slotSize=gp.tileSize+3;

        for(int i=0;i<gp.player.inventory.size();i++){
            if(gp.player.inventory.get(i).name==gp.player.currentWeapon.name
                    ||gp.player.inventory.get(i).name==gp.player.currentShield.name
                    ||gp.player.inventory.get(i).name==gp.player.currentBoots.name){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX,slotY,gp.tileSize,gp.tileSize,10,10);
            }
            g2.drawImage(gp.player.inventory.get(i).down1,slotX,slotY,null);
            slotX+=slotSize;
            if(i==4||i==9||i==14){
                slotX=slotXstart;
                slotY+=slotSize;
            }
        }

        int cursorX=slotXstart+(slotSize*slotCol);
        int cursorY=slotYstart+(slotSize*slotRow);
        int cursorWidth=gp.tileSize;
        int cursorHeight=gp.tileSize;

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,10,10);

        int dFrameX=frameX;
        int dFrameY=frameY+frameHeight+20;
        int dFrameWidth=frameWidth;
        int dFrameHeight=gp.tileSize*3;

        int textX=dFrameX+20;
        int textY=dFrameY+35;
        int itemIndex=getItemIndex();
        g2.setFont(g2.getFont().deriveFont(18F));
        if(itemIndex<gp.player.inventory.size()){
            drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);
            for(String line:gp.player.inventory.get(itemIndex).description.split("\n")){
                g2.drawString(line,textX,textY);
                textY+=32;
            }
        }

    }
    public void drawOptionsMenu(){
        g2.setColor(Color.WHITE);
        g2.setFont(exocet);
        g2.setFont(g2.getFont().deriveFont(35F));

        int frameX=gp.tileSize*3;
        int frameY=gp.tileSize;
        int frameWidth=gp.tileSize*14;
        int frameHeigth=gp.tileSize*8;
        drawSubWindow(frameX,frameY,frameWidth,frameHeigth);

        switch (subState){
            case 0:
                optionsTop(frameX,frameY);
                break;
            case 1:
                fullScreenNoti(frameX,frameY);
                break;
            case 2:
                controlsNoti(frameX,frameY);
                break;
            case 3:
                exitNoti(frameX,frameY);
                break;
        }
        gp.keyH.enterPressed=false;
    }
    public void optionsTop(int frameX,int frameY){
        int textX;
        int textY;

        String text="Options";
        textX=getCenter(text);
        textY=frameY+gp.tileSize;
        g2.drawString(text,textX,textY);

        g2.setFont(g2.getFont().deriveFont(24F));
        textX=frameX+gp.tileSize;
        textY+=gp.tileSize+24;
        g2.drawString("Full Screen",textX,textY);
        if(commandNum==1){
            g2.setFont(didot40);
            g2.drawString(">",textX-25,textY);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(24F));
            if(gp.keyH.enterPressed==true){
                if(gp.fullScreen==false){
                    gp.fullScreen=true;
                }else if(gp.fullScreen==true){
                    gp.fullScreen=false;
                }
                subState=1;
            }
        }

        textY+=gp.tileSize+24;
        g2.drawString("Music",textX,textY);
        if(commandNum==2){
            g2.setFont(didot40);
            g2.drawString(">",textX-25,textY);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(24F));
        }

        textY+=gp.tileSize+24;
        g2.drawString("Controls",textX,textY);
        if(commandNum==3){
            g2.setFont(didot40);
            g2.drawString(">",textX-25,textY);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(24F));
            if(gp.keyH.enterPressed==true){
                subState=2;
                commandNum=0;
            }
        }

        textY+=gp.tileSize+24;
        g2.drawString("To Title",textX,textY);
        if(commandNum==4){
            g2.setFont(didot40);
            g2.drawString(">",textX-25,textY);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(24F));
            if(gp.keyH.enterPressed==true){
                subState=3;
                commandNum=0;
            }
        }

        textX=frameX+gp.tileSize*12;
        textY=frameY+gp.tileSize*2;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX,textY,24,24);
        if(gp.fullScreen==true){
            g2.setColor(new Color(255,255,220));
            g2.fillRect(textX+4,textY+4,16,16);
            g2.setColor(Color.WHITE);
        }

        textY+=gp.tileSize+24;
        g2.drawRect(textX-gp.tileSize*2-4,textY,124,24);
        int volumeWidth=24*gp.music.volumeScale;
        g2.fillRect(textX-gp.tileSize*2-4,textY,volumeWidth+4,24);
    }
    public void fullScreenNoti(int frameX,int frameY){
        int textX=frameX+gp.tileSize+12;
        int textY=frameY+gp.tileSize+24;
        currentDialog="Changes will take effect \n        after the game\n          is restarted.";
        for(String line : currentDialog.split("\n")){
            g2.drawString(line,textX,textY);
            textY+=40;
        }
        if(gp.keyH.enterPressed==true){
            subState=0;
        }
    }
    public void controlsNoti(int frameX,int frameY){
        int textX;
        int textY;

        String text = "Controls";
        textX=getCenter(text);
        textY=frameY+gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(30F));
        g2.drawString(text,textX,textY);
        g2.setFont(g2.getFont().deriveFont(24F));

        textX=frameX+gp.tileSize;
        textY+=gp.tileSize+24;

        g2.drawString("Up",textX,textY);
        if(commandNum==1){
            g2.setFont(didot40);
            g2.drawString(">",textX-25,textY);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(24F));
        }
        textY+=27;
        g2.drawString("Down",textX,textY);
        if(commandNum==2){
            g2.setFont(didot40);
            g2.drawString(">",textX-25,textY);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(24F));
        }
        textY+=27;
        g2.drawString("Left",textX,textY);
        if(commandNum==3){
            g2.setFont(didot40);
            g2.drawString(">",textX-25,textY);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(24F));
        }
        textY+=27;
        g2.drawString("Right",textX,textY);
        if(commandNum==4){
            g2.setFont(didot40);
            g2.drawString(">",textX-25,textY);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(24F));
        }
        textY+=27;
        g2.drawString("Attack/Confirm",textX,textY);
        if(commandNum==5){
            g2.setFont(didot40);
            g2.drawString(">",textX-25,textY);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(24F));
        }
        textY+=27;
        g2.drawString("Shoot",textX,textY);
        if(commandNum==6){
            g2.setFont(didot40);
            g2.drawString(">",textX-25,textY);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(24F));
        }
        textY+=27;
        g2.drawString("Pause",textX,textY);
        if(commandNum==7){
            g2.setFont(didot40);
            g2.drawString(">",textX-25,textY);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(24F));
        }
        textY+=27;
        g2.drawString("Stats",textX,textY);
        if(commandNum==8){
            g2.setFont(didot40);
            g2.drawString(">",textX-25,textY);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(24F));
        }
        textY+=27;
        g2.drawString("Options",textX,textY);
        if(commandNum==9){
            g2.setFont(didot40);
            g2.drawString(">",textX-25,textY);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(24F));
        }
        textY+=27;
        g2.drawString("Return",textX,textY);
        if(commandNum==10){
            g2.setFont(didot40);
            g2.drawString(">",textX-25,textY);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(24F));
            if(gp.keyH.enterPressed==true){
                subState=0;
                commandNum=3;
            }
        }

        textX=frameX+gp.tileSize*12+24;
        textY=frameY+gp.tileSize*2+24;
        g2.drawString("W",textX,textY);
        textY+=27;
        g2.drawString("A",textX,textY);
        textY+=27;
        g2.drawString("S",textX,textY);
        textY+=27;
        g2.drawString("D",textX,textY);
        textY+=27;
        g2.drawString("J",textX,textY);
        textY+=27;
        g2.drawString("U",textX,textY);
        textY+=27;
        g2.drawString("P",textX,textY);
        textY+=27;
        g2.drawString("Q",textX,textY);
        textY+=27;
        g2.drawString("ESC",textX,textY);
    }
    public void exitNoti(int frameX,int frameY){
        int textX=frameX+gp.tileSize*3;
        int textY=frameY+gp.tileSize+24;
        currentDialog="Quit the game and\n return to title?";
        for(String line : currentDialog.split("\n")){
            textX=getCenter(line);
            g2.drawString(line,textX,textY);
            textY+=40;
        }
        String text="Yes";
        textX=getCenter(text);
        textY+=gp.tileSize*2;
        g2.drawString(text,textX,textY);
        if(commandNum==1){
            g2.setFont(didot40);
            g2.drawString(">",textX-25,textY);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(35F));
            if(gp.keyH.enterPressed==true){
                subState=0;
                gp.ui.titleScreen=0;
                gp.player.object = 0;
                gp.player.npc = 0;
                gp.player.monster = 0;
                gp.musicstop();
                gp.music(6);
                gp.gameState = gp.titleState;
                gp.reset();
                gp.ui.message.clear();
                gp.ui.titleScreen = 0;
            }
        }
        text="No";
        textX=getCenter(text);
        textY+=gp.tileSize;
        g2.drawString(text,textX,textY);
        if(commandNum==2){
            g2.setFont(didot40);
            g2.drawString(">",textX-25,textY);
            g2.setFont(exocet);
            g2.setFont(g2.getFont().deriveFont(24F));
            if(gp.keyH.enterPressed==true){
                subState=0;
                commandNum=4;
            }
        }
    }
    public void drawGameOver(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(exocet);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,100F));
        text="Game Over";

        g2.setColor(Color.BLACK);
        x=getCenter(text);
        y=gp.tileSize*4;
        g2.drawString(text,x,y);

        g2.setColor(Color.WHITE);
        g2.drawString(text,x-4,y-4);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
        text="Retry";
        x=getCenter(text);
        y+=gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum==0){
            g2.setFont(didot40);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 45F));
            g2.drawString(">", x - gp.tileSize, y);
        }
        g2.setFont(exocet);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50F));

        text="Quit";
        x=getCenter(text);
        y+=55;
        g2.drawString(text,x,y);
        if(commandNum==1){
            g2.setFont(didot40);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 45F));
            g2.drawString(">", x - gp.tileSize, y);
        }
        g2.setFont(exocet);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50F));
    }
    public void drawTransition(){
        counter++;
        g2.setColor(new Color(0,0,0,counter*5));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        if(counter==50){
            counter=0;
            gp.gameState=gp.playState;
            gp.currentMap=gp.event.tempMap;
            gp.player.worldX=gp.tileSize*gp.event.tempCol;
            gp.player.worldY=gp.tileSize*gp.event.tempRow;
            gp.event.previousEventX=gp.player.worldX;
            gp.event.previousEventY=gp.player.worldY;
        }
    }
    public void drawTrade(){
        switch (subState){
            case 0: tradeSelect();
            case 1: tradeBuy();
            case 2: tradeSell();
        }
        gp.keyH.enterPressed=false;
    }
    public void tradeSelect(){
        drawDialog();
        int x=gp.tileSize*15;
        int y=gp.tileSize*4+20;
        int width=gp.tileSize*3;
        int height=(int)(gp.tileSize*3.5);
    }
    public void tradeBuy(){

    }
    public void tradeSell(){

    }
    public void drawMessage(){
        int messageX=gp.tileSize;
        int messageY=gp.tileSize*3;
        g2.setFont(exocet);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,25F));
        for(int i=0;i<message.size();i++){
            if(message.get(i)!=null){
                g2.setColor(Color.BLACK);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);
                g2.setColor(new Color(178, 0, 32));
                g2.drawString(message.get(i), messageX, messageY);
                int counter=messageCounter.get(i)+1;
                messageCounter.set(i,counter);
                messageY+=30;
                if(messageCounter.get(i)>180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
    public void drawSubWindow(int x,int y,int width,int height){
        Color c=new Color(0,0,0,190);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);
        c=new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x,y,width,height,35,35);
    }
    public int getCenter(String text){
        int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=gp.screenWidth/2-length/2;
        return x;
    }
    public int getRight(String text,int tailX){
        int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=tailX-length;
        return x;
    }
    public int getItemIndex(){
        return slotCol+(slotRow*5);
    }
}
