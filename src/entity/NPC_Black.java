package Entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Guy extends Entity {
    public NPC_Guy(GamePanel gp){
        super(gp);
        direction="down";
        speed=1;
        getImage();
        setDialog();
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
    public void getImage(){
        up1=sprite("/image/chara/Walking sprites/boyup (1)");
        up2=sprite("/image/chara/Walking sprites/boyup (2)");
        up3=sprite("/image/chara/Walking sprites/boyup (3)");
        up4=sprite("/image/chara/Walking sprites/boyup (4)");
        down1=sprite("/image/chara/Walking sprites/boydown (1)");
        down2=sprite("/image/chara/Walking sprites/boydown (2)");
        down3=sprite("/image/chara/Walking sprites/boydown (3)");
        down4=sprite("/image/chara/Walking sprites/boydown (4)");;
        left1=sprite("/image/chara/Walking sprites/boyleft (1)");
        left2=sprite("/image/chara/Walking sprites/boyleft (2)");
        left3=sprite("/image/chara/Walking sprites/boyleft (3)");
        left4=sprite("/image/chara/Walking sprites/boyleft (4)");
        right1=sprite("/image/chara/Walking sprites/boyright (1)");
        right2=sprite("/image/chara/Walking sprites/boyright (2)");
        right3=sprite("/image/chara/Walking sprites/boyright (3)");
        right4=sprite("/image/chara/Walking sprites/boyright (4)");
    }
    public void setDialog(){
        dialog[0]="Watch where you're going!";
        dialog[1]="Get away from me!";
        dialog[2]=""
    }
    public void speak(){
        gp.ui.currentDialog=dialog[dialogIndex];
        dialogIndex++;
    }
}
