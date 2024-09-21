package Resource.item;

import main.GamePanel;
import Entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Door extends Entity {
    public Door(GamePanel gp){
        super(gp);
        name="item.Door";
        image= setup("/object/door.png");
    }
}
