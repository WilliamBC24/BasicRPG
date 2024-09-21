import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Utility {
    GamePanel gp;
    TileManager tileM;
    public BufferedImage scale(BufferedImage original, int width, int height){

        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original,0,0,width,height,null);
        g2.dispose();
        return scaledImage;

    }
}
