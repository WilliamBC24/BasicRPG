package Object;

import Entity.Entity;
import main.GamePanel;

public class Money extends Entity {
    public Money(GamePanel gp) {
        super(gp);
        name="Money";
        type=type_consumable;
        down1=setup("/Resource/image/object/full");
    }
}
