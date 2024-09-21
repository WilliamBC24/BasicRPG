package Object;

import Entity.Entity;
import main.GamePanel;

public class WoodSword extends Entity {
    public WoodSword(GamePanel gp) {
        super(gp);

        name="Wooden Sword";
        down1=setup("a");
        attackValue=2;
    }
}
