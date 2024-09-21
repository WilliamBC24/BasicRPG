package Object;

import Entity.Entity;
import main.GamePanel;

public class Shoes extends Entity {
    public Shoes(GamePanel gp) {
        super(gp);
        type=type_shoes;
        name="Shoes";
        speedValue=1;
        down1=setup("/Resource/image/object/shoes");
        description="["+name+"]\nSneakes by pumba.";
    }
}
