package item;

import main.GamePanel;
import main.SuperObj;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Door extends SuperObj {
    GamePanel gp;
    public Door(GamePanel gp){
        name="item.Door";
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/object/door.png"));
            utility.scale(image,gp.tileSize,gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
}
