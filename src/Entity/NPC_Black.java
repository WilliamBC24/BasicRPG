package Entity;

import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_Black extends Entity {
    public NPC_Black(GamePanel gp){
        super(gp);
        type=type_npc;
        direction="down";
        speed=1;
        getImage();
        setDialog();
    }
    public void getImage(){
        up1=setup("/Resource/image/npc/black (10)");
        up2=setup("/Resource/image/npc/black (11)");
        up3=setup("/Resource/image/npc/black (12)");
        up4=setup("/Resource/image/npc/black (11)");
        up5=setup("/Resource/image/npc/black (10)");
        up6=setup("/Resource/image/npc/black (11)");
        up7=setup("/Resource/image/npc/black (12)");
        up8=setup("/Resource/image/npc/black (11)");
        up9=setup("/Resource/image/npc/black (12)");
        down1=setup("/Resource/image/npc/black (1)");
        down2=setup("/Resource/image/npc/black (2)");
        down3=setup("/Resource/image/npc/black (3)");
        down4=setup("/Resource/image/npc/black (2)");
        down5=setup("/Resource/image/npc/black (1)");
        down6=setup("/Resource/image/npc/black (2)");
        down7=setup("/Resource/image/npc/black (3)");
        down8=setup("/Resource/image/npc/black (2)");
        down9=setup("/Resource/image/npc/black (3)");
        left1=setup("/Resource/image/npc/black (4)");
        left2=setup("/Resource/image/npc/black (5)");
        left3=setup("/Resource/image/npc/black (6)");
        left4=setup("/Resource/image/npc/black (5)");
        left5=setup("/Resource/image/npc/black (4)");
        left6=setup("/Resource/image/npc/black (5)");
        left7=setup("/Resource/image/npc/black (6)");
        left8=setup("/Resource/image/npc/black (5)");
        left9=setup("/Resource/image/npc/black (6)");
        right1=setup("/Resource/image/npc/black (7)");
        right2=setup("/Resource/image/npc/black (8)");
        right3=setup("/Resource/image/npc/black (9)");
        right4=setup("/Resource/image/npc/black (8)");
        right5=setup("/Resource/image/npc/black (7)");
        right6=setup("/Resource/image/npc/black (8)");
        right7=setup("/Resource/image/npc/black (9)");
        right8=setup("/Resource/image/npc/black (8)");
        right9=setup("/Resource/image/npc/black (9)");
    }
    public void setDialog(){
        dialog[0]="Watah where you're going!";
        dialog[1]="Get away from me!";
        dialog[2]="Gulugulugulugulu";
        dialog[3]="I used to be a great person.\nNow I am black.";
    }
    public void speak(){
        super.speak();
    }
}
