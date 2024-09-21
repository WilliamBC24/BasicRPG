package Object;

import Entity.Entity;
import main.GamePanel;

public class Door extends Entity {
    public Door(GamePanel gp) {
        super(gp);
        name="Key";
        down1=setup("/Resource/image/object/door");
    }
}
