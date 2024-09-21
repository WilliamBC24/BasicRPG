package Object;

import Entity.Entity;
import main.GamePanel;

public class Coin extends Entity {
    GamePanel gp;
    public Coin(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name="Coin";
        type=type_pickup;
        value=1;
        down1=setup("/Resource/image/object/coin");
    }
    public void use(Entity entity){
        gp.ui.addMessage("Got "+value+"G!");
        gp.player.money+=value;
    }
}
