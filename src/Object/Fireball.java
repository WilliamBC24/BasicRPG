package Object;

import Entity.Entity;
import Entity.Projectile;
import main.GamePanel;

public class Fireball extends Projectile {
    GamePanel gp;
    public Fireball(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name="Fireball";
        speed=8;
        maxLife=30;
        life=maxLife;
        attack=5;
        useCost=1;
        alive=false;
        getImage();
    }
    public void getImage(){
        up1=setup("/Resource/image/object/fireball/fireballup1");
        up2=setup("/Resource/image/object/fireball/fireballup2");
        down1=setup("/Resource/image/object/fireball/fireballdown1");
        down2=setup("/Resource/image/object/fireball/fireballdown2");
        left1=setup("/Resource/image/object/fireball/fireballleft1");
        left2=setup("/Resource/image/object/fireball/fireballleft2");
        right1=setup("/Resource/image/object/fireball/fireballright1");
        right2=setup("/Resource/image/object/fireball/fireballright2");
    }
    public boolean haveResource(Entity user){
        boolean haveResource=false;
        if(user.mana>=useCost){
            haveResource=true;
        }
        return haveResource;
    }
    public void subtractResource(Entity user){
        user.mana-=useCost;
    }
}
