package Resource.item;

import main.GamePanel;
import Entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key extends Entity {
    public Key(GamePanel gp){
        super(gp);
        name="item.Key";
        image=setup("/object/key.png");
    }
}
