package Object;

import Entity.Entity;
import main.GamePanel;

public class Teleport extends Entity {
    public Teleport(GamePanel gp) {
        super(gp);
        type=type_none;
        down1=setup("/Resource/image/object/teleport");
    }
}
