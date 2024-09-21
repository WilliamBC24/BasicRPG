package Object;

import Entity.Entity;
import main.GamePanel;

public class Hermes extends Entity {
    public Hermes(GamePanel gp) {
        super(gp);
        type=type_shoes;
        name="Hermes Shoes";
        speedValue=3;
        down1=setup("/Resource/image/object/hermes");
        description="["+name+"]\nBlessed by Hermes!";
    }
}
