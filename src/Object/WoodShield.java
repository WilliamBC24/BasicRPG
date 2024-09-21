package Object;

import Entity.Entity;
import main.GamePanel;

public class WoodShield extends Entity {
    public WoodShield(GamePanel gp) {
        super(gp);

        name="Wooden Shield";
        down1=setup("");
        defenseValue=2;
    }
}
