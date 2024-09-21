package Object;

import Entity.Projectile;
import main.GamePanel;

public class DarkRock extends Projectile {
    public DarkRock(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name="DarkRock";
        speed=4;
        maxLife=60;
        life=maxLife;
        attack=6;
        useCost=1;
        alive=false;
        getImage();
    }
    public void getImage(){
        up1=setup("/Resource/image/object/darkshield");
        up2=setup("/Resource/image/object/darkshield");
        down1=setup("/Resource/image/object/darkshield");
        down2=setup("/Resource/image/object/darkshield");
        left1=setup("/Resource/image/object/darkshield");
        left2=setup("/Resource/image/object/darkshield");
        right1=setup("/Resource/image/object/darkshield");
        right2=setup("/Resource/image/object/darkshield");
    }
}
