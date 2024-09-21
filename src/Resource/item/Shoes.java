package item;

import main.GamePanel;
import main.SuperObj;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Shoes extends SuperObj {
    GamePanel gp;
    public Shoes(GamePanel gp){
        name="item.Shoes";
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/object/shoes.png"));
            utility.scale(image,gp.tileSize,gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
