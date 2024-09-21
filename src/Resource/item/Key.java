package item;

import main.GamePanel;
import main.SuperObj;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key extends SuperObj {
    GamePanel gp;
    public Key(GamePanel gp){
        name="item.Key";
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/object/key.png"));
            utility.scale(image,gp.tileSize,gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
