package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyDetech implements KeyListener {

    public boolean upPressed,downPressed,leftPressed,rightPressed,enterPressed,shootPressed;
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
        int code=e.getKeyCode();
        if(gp.gameState==gp.titleState) {
            if(gp.ui.titleScreen==0) {
                title(code);
            }else if(gp.ui.titleScreen==1){
                charSelect(code);
            }
        }
        else if(gp.gameState==gp.playState) {
            playState(code);
        }
        else if(gp.gameState==gp.pauseState){
            pause(code);
        }
        else if(gp.gameState==gp.dialogueState){
            dialogue(code);
        }
        else if(gp.gameState==gp.characterState){
            charMenu(code);
        }
        else if(gp.gameState==gp.optionsState){
            optionsMenu(code);
        }
        else if(gp.gameState==gp.gameoverState){
            gameOverState(code);
        }
    }

    public void title(int code){
        switch (code) {
            case KeyEvent.VK_W:
                if (gp.ui.commandNum == 1) {
                    gp.ui.commandNum = 3;
                } else {
                    gp.ui.commandNum--;
                }
                break;
            case KeyEvent.VK_S:
                if (gp.ui.commandNum == 3) {
                    gp.ui.commandNum = 1;
                } else {
                    gp.ui.commandNum++;
                }
                break;
            case KeyEvent.VK_J:
            case KeyEvent.VK_ENTER:
                if (gp.ui.commandNum == 1) {
                    gp.ui.titleScreen=1;
                    gp.ui.commandNum=0;
                } else if (gp.ui.commandNum == 2) {

                } else if (gp.ui.commandNum == 3) {
                    System.exit(0);
                }
        }
    }
    public void charSelect(int code){
        switch (code) {
            case KeyEvent.VK_W:
                if (gp.ui.commandNum == 1) {
                    gp.ui.commandNum = 4;
                } else {
                    gp.ui.commandNum--;
                }
                break;
            case KeyEvent.VK_S:
                if (gp.ui.commandNum == 4) {
                    gp.ui.commandNum = 1;
                } else {
                    gp.ui.commandNum++;
                }
                break;
            case KeyEvent.VK_J:
            case KeyEvent.VK_ENTER:
                if (gp.ui.commandNum == 1) {
                    gp.gameState=gp.playState;
                    gp.musicstop();
                    gp.music(5);
                    gp.ui.commandNum=0;
                } else if (gp.ui.commandNum == 2) {
                    gp.gameState=gp.playState;
                    gp.musicstop();
                    gp.music(5);
                    gp.ui.commandNum=0;
                } else if (gp.ui.commandNum == 3) {
                    gp.gameState=gp.playState;
                    gp.musicstop();
                    gp.music(5);
                    gp.ui.commandNum=0;
                } else if (gp.ui.commandNum == 4) {
                    gp.ui.titleScreen=0;
                    gp.ui.commandNum=0;
                }
        }
    }
    public void playState(int code){
        switch (code) {
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
            case KeyEvent.VK_U:
                shootPressed=true;
                break;
            case KeyEvent.VK_P:
                gp.gameState=gp.pauseState;
                break;
            case KeyEvent.VK_Q:
                gp.gameState=gp.characterState;
                break;
            case KeyEvent.VK_ESCAPE:
                gp.gameState=gp.optionsState;
                break;
            case KeyEvent.VK_J:
            case KeyEvent.VK_ENTER:
                enterPressed=true;
                break;
        }
    }
    public void pause(int code){
        if(code==KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }
    public void dialogue(int code){
        if(code==KeyEvent.VK_ENTER){
            gp.gameState=gp.playState;
        }
    }
    public void charMenu(int code){
        if(code==KeyEvent.VK_Q||code==KeyEvent.VK_ESCAPE){
            gp.gameState=gp.playState;
        }
        if(code==KeyEvent.VK_W){
            if(gp.ui.slotRow<=0) {
                gp.ui.slotRow=3;
            }else{
                gp.ui.slotRow--;
            }
        }
        if(code==KeyEvent.VK_A){
            if(gp.ui.slotCol<=0) {
                gp.ui.slotCol=4;
            }else{
                gp.ui.slotCol--;
            }
        }
        if(code==KeyEvent.VK_S){
            if(gp.ui.slotRow>=3) {
                gp.ui.slotRow=0;
            }else{
                gp.ui.slotRow++;
            }
        }
        if(code==KeyEvent.VK_D){
            if(gp.ui.slotCol>=4) {
                gp.ui.slotCol=0;
            }else{
                gp.ui.slotCol++;
            }
        }
        if(code==KeyEvent.VK_F) {
            gp.player.throwObject = true;
        }
        if(code==KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }
    }
    public void optionsMenu(int code){
        if(code==KeyEvent.VK_ESCAPE){
            if(gp.ui.subState==0){
                gp.gameState = gp.playState;
                gp.ui.commandNum=0;
            }
            else if(gp.ui.subState==1||gp.ui.subState==2||gp.ui.subState==3){
                gp.ui.subState=0;
            }
        }
        if(code==KeyEvent.VK_ENTER||code==KeyEvent.VK_J){
            enterPressed=true;
        }
        int maxCommandNum=0;
        switch (gp.ui.subState){
            case 0: maxCommandNum=4;break;
            case 2: maxCommandNum=10;break;
            case 3: maxCommandNum=2;break;
        }
        if(code==KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum<1){
                gp.ui.commandNum=maxCommandNum;
            }
        }
        if(code==KeyEvent.VK_S){
            gp.ui.commandNum++;
            if(gp.ui.commandNum>maxCommandNum){
                gp.ui.commandNum=1;
            }
        }
        if(code==KeyEvent.VK_A){
            if(gp.ui.subState==0){
                if(gp.ui.commandNum==2&&gp.music.volumeScale>0){
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                }
            }
        }
        if(code==KeyEvent.VK_D){
            if(gp.ui.subState==0){
                if(gp.ui.commandNum==2&&gp.music.volumeScale<5){
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                }
            }
        }
    }
    public void gameOverState(int code){
        if(code==KeyEvent.VK_W) {
            if (gp.ui.commandNum == 0) {
                gp.ui.commandNum = 1;
            } else {
                gp.ui.commandNum--;
            }
        }
       if(code==KeyEvent.VK_S) {
           if (gp.ui.commandNum == 1) {
               gp.ui.commandNum = 0;
           } else {
               gp.ui.commandNum++;
           }
       }
       if(code==KeyEvent.VK_J||code==KeyEvent.VK_ENTER) {
           if (gp.ui.commandNum == 0) {
               if(gp.player.haveCheckpoint==true) {
                   gp.player.object = 0;
                   gp.player.npc = 0;
                   gp.player.monster = 0;
                   gp.retry();
                   gp.musicstop();
                   gp.music(5);
                   gp.ui.message.clear();
                   gp.gameState = gp.playState;
               }else{
                   gp.player.object = 0;
                   gp.player.npc = 0;
                   gp.player.monster = 0;
                   gp.musicstop();
                   gp.music(5);
                   gp.reset();
                   gp.ui.message.clear();
                   gp.gameState = gp.playState;
               }
           } else if (gp.ui.commandNum == 1) {
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
            case KeyEvent.VK_U:
                shootPressed=false;
                break;
        }
    }
}
