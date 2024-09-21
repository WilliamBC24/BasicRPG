package Object;

import Entity.Entity;
import main.GamePanel;

public class Heart extends Entity {

    public Heart(GamePanel gp) {
        super(gp);
        name="Magic Heart";
        value=5;
        type=type_consumable;
        down1=setup("/Resource/image/object/full");
        description="Heal by "+value+".";
    }
    public void use(Entity entity){
        gp.gameState=gp.dialogueState;
        gp.ui.currentDialog=("You used the magic heart.");
        gp.player.life+=value;
        if(gp.player.life>gp.player.maxLife){
            gp.player.life=gp.player.maxLife;
        }
    }
}
