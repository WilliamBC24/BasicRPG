package Object;

import Entity.Entity;
import main.GamePanel;

public class ShieldOfTheLost extends Entity {
    public ShieldOfTheLost(GamePanel gp) {
        super(gp);
        name="Shield Of The Lost";
        type=type_shield;
        down1=setup("/Resource/image/object/lostshield");
        defenseValue=4;
        description="["+name+"]\nA shield recovered\nfrom an archaeologist\nsite.";
    }
}
