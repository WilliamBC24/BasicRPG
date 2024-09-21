package Object;

import Entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WoodShield extends Entity {
    public WoodShield(GamePanel gp) {
        super(gp);

        name="Wooden Shield";
        type=type_shield;
        down1=setup("/Resource/image/object/woodshield");
        defenseValue=1;
        description="["+name+"]\nActually a big piece of \nbark.";
    }
}
