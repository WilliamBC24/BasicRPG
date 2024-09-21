import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyDetech keyH;
    public final int screenX;//place char in middle of screen
    public final int screenY;

    public Player(GamePanel gp,KeyDetech keyH){
        this.gp=gp;
        this.keyH=keyH;
        screenX=gp.screenWidth/2-(gp.tileSize/2);
        screenY=gp.screenHeight/2-(gp.tileSize/2);
        solidArea = new Rectangle(8,16,32,18);
        setDefault();
        getPlayerImage();
    }
    public void setDefault(){
        worldX= gp.tileSize*23;
        worldY=gp.tileSize*21;
        speed=5;
        direction="down";
    }

    public void getPlayerImage(){
        try{
            up1= ImageIO.read(getClass().getResourceAsStream("/chara/Walking sprites/boy_up_1.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/chara/Walking sprites/boy_up_2.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("/chara/Walking sprites/boy_down_1.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/chara/Walking sprites/boy_down_2.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/chara/Walking sprites/boy_left_1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/chara/Walking sprites/boy_left_2.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/chara/Walking sprites/boy_right_1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/chara/Walking sprites/boy_right_2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        //update movement
        if(keyH.upPressed==true|| keyH.downPressed==true|| keyH.leftPressed==true|| keyH.rightPressed==true){
            if(keyH.upPressed){
                direction="up";
            }else if(keyH.downPressed){
                direction="down";
            }else if(keyH.leftPressed){
                direction="left";
            }else if(keyH.rightPressed){
                direction="right";
            }
            //Check collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            //Can move if collision = false
            if(collisionOn==false){
                switch(direction){
                    case "up":
                        worldY-=speed;
                        break;
                    case "down":
                        worldY+=speed;
                        break;
                    case "left":
                        worldX-=speed;
                        break;
                    case "right":
                        worldX+=speed;
                        break;
                }
            }
            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum==1){
                    spriteNum=2;
                }else if(spriteNum==2){
                    spriteNum=1;
                }
                spriteCounter=0;
            }
        }
    }
    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(x,y,gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum==1){
                    image=up1;
                }
                if(spriteNum==2){
                    image=up2;
                }
                break;
            case "down":
                if(spriteNum==1){
                    image=down1;
                }
                if(spriteNum==2){
                    image=down2;
                }
                break;
            case "left":
                if(spriteNum==1){
                    image=left1;
                }
                if(spriteNum==2){
                    image=left2;
                }
                break;
            case "right":
                if(spriteNum==1){
                    image=right1;
                }
                if(spriteNum==2){
                    image=right2;
                }
                break;
        }
        g2.drawImage(image,screenX,screenY,gp.tileSize, gp.tileSize, null);
    }

}
