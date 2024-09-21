package Object;

import Entity.Entity;
import main.GamePanel;

public class DarkShield extends Entity {
    public DarkShield(GamePanel gp) {
        super(gp);
        name="Dark Shield";
        type=type_shield;
        down1=setup("/Resource/image/object/darkshield");
        defenseValue=2;
        description="["+name+"]\nA shield made of\nobsidian.";
    }
}
