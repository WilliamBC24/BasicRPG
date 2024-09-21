package Object;

import main.GamePanel;
import Entity.Entity;


public class Health extends Entity {
    public Health(GamePanel gp){
        super(gp);
        name="Health";
        image1= setup("/Resource/image/object/full");
        image2= setup("/Resource/image/object/half");
        image3= setup("/Resource/image/object/none");
    }
}
