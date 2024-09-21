package Resource.item;

import main.GamePanel;
import Entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Shoes extends Entity {
    public Shoes(GamePanel gp){
        super(gp);
        name="item.Shoes";
        image=setup("/object/shoes.png");
    }
}
