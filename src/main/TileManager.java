package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[50];
        mapTileNum = new int[gp.maxMap][gp.worldColumn][gp.worldRow];

        getTileImage();
        loadMap("/Resource/image/map/new.txt",0);
        loadMap("/Resource/image/map/next.txt",1);
    }


    public void getTileImage() {
            setupImage(0,"000",false);
            setupImage(1,"000",false);
            setupImage(2,"000",false);
            setupImage(3,"000",false);
            setupImage(4,"000",false);
            setupImage(5,"000",false);
            setupImage(6,"000",false);
            setupImage(7,"000",false);
            setupImage(8,"000",false);
            setupImage(9,"000",false);
            setupImage(10,"000",false);
            setupImage(11,"001",false);
            setupImage(12,"002",false);
            setupImage(13,"003",false);
            setupImage(14,"004",false);
            setupImage(15,"005",false);
            setupImage(16,"006",false);
            setupImage(17,"007",false);
            setupImage(18,"008",false);
            setupImage(19,"009",false);
            setupImage(20,"010",false);
            setupImage(21,"011",false);
            setupImage(22,"012",false);
            setupImage(23,"013",false);
            setupImage(24,"014",false);
            setupImage(25,"015",false);
            setupImage(26,"016",true);
            setupImage(27,"017",false);
            setupImage(28,"018",true);
            setupImage(29,"019",true);
            setupImage(30,"020",true);
            setupImage(31,"021",true);
            setupImage(32,"022",true);
            setupImage(33,"023",true);
            setupImage(34,"024",true);
            setupImage(35,"025",true);
            setupImage(36,"026",true);
            setupImage(37,"027",true);
            setupImage(38,"028",true);
            setupImage(39,"029",true);
            setupImage(40,"030",true);
            setupImage(41,"031",true);
            setupImage(42,"032",true);
            setupImage(43,"033",true);
            setupImage(44,"034",false);
            setupImage(45,"035",true);
            setupImage(46,"036",false);
            setupImage(47,"037",false);
    }

    public void setupImage(int index, String name, boolean collision){
        Utility utility = new Utility();
        try{
             tile[index]=new Tile();
             tile[index].image=ImageIO.read(getClass().getResourceAsStream("/Resource/image/tile/" +name+".png"));
             tile[index].image=utility.scale(tile[index].image,gp.tileSize,gp.tileSize);
             tile[index].collision=collision;
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath,int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int row = 0;
            int col = 0;
            while (col < gp.worldColumn && row < gp.worldRow) {
                String line = br.readLine();
                while (col < gp.worldColumn) {
                    String number[] = line.split(" ");
                    int num = Integer.parseInt(number[col]);
                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.worldColumn) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;
        while (worldRow < gp.worldRow && worldCol < gp.worldColumn) {
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            //draw only part we see
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;
            if (worldCol == gp.worldColumn) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
