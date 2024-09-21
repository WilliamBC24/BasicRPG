package Object;

import Entity.Entity;
import main.GamePanel;

public class Chest extends Entity {
    public Chest(GamePanel gp) {
        super(gp);
        name="Chest";
        down1=setup("/Resource/image/object/chest");
    }
}
