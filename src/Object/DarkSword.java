package Object;

import Entity.Entity;
import main.GamePanel;

public class DarkSword extends Entity {
    public DarkSword(GamePanel gp) {
        super(gp);
        name="Dark Sword";
        type=type_sword;
        down1=setup("/Resource/image/object/darksword");
        attackValue=4;
        atkSpeedValue=2;
        attackArea.width=gp.tileSize;
        attackArea.height=gp.tileSize;
        description="["+name+"]\nA sword made of \nobsidian.";
    }
}
