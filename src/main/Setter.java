public class Setter {

    GamePanel gp;
    public Setter(GamePanel gp){
        this.gp=gp;
    }
    public void setObj(){

    }
    public void setNPC(){
        gp.npc[0]=new NPC_Girl(gp);
        gp.npc[0].worldX=gp.tileSize*25;
        gp.npc[0].worldY=gp.tileSize*20;
    }

}
