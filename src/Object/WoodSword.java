package Object;

import Entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WoodSword extends Entity {
    public WoodSword(GamePanel gp) {
        super(gp);

        name="Wooden Sword";
        type=type_sword;
        down1=setup("/Resource/image/object/woodsword");
        attackValue=2;
        atkSpeedValue=0;
        attackArea.width=gp.tileSize;
        attackArea.height=gp.tileSize;
        description="["+name+"]\nA practice sword.";
    }
}
