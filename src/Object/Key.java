package Object;

import Entity.Entity;
import main.GamePanel;

public class Key extends Entity {
    public Key(GamePanel gp) {
        super(gp);
        name="Key";
        down1=setup("/Resource/image/object/key");
        description="["+name+"]\nIt can open anything!";
    }
}
