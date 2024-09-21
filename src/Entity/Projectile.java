package Entity;

import main.GamePanel;

public class Projectile extends Entity{
    Entity user;
    public Projectile(GamePanel gp) {
        super(gp);
    }
    public void set(int worldX,int worldY,String direction,boolean alive,Entity user){
        this.worldX=worldX;
        this.worldY=worldY;
        this.direction=direction;
        this.alive=alive;
        this.user=user;
        this.life=this.maxLife;
    }
    public void update(){
        if(user==gp.player){
            int monsterIndex=gp.cChecker.checkBump(this,gp.monster);
            if(monsterIndex!=999){
                //gp.player.damageMonster(monsterIndex,attack);
                if(gp.monster[gp.currentMap][monsterIndex].invisible==false){
                    int damage = gp.player.magic*attack - gp.monster[gp.currentMap][monsterIndex].defense;
                    if (damage < 0) {
                        damage = 0;
                    }

                    gp.monster[gp.currentMap][monsterIndex].life -= damage;
                    gp.ui.addMessage(damage + " damage!");
                    gp.monster[gp.currentMap][monsterIndex].invisible=true;
                    gp.monster[gp.currentMap][monsterIndex].dmgReaction();
                    if(gp.monster[gp.currentMap][monsterIndex].life<=0){
                        gp.monster[gp.currentMap][monsterIndex].dying=true;
                        gp.ui.addMessage("Killed "+gp.monster[gp.currentMap][monsterIndex].name+"!");
                        gp.player.exp+=gp.monster[gp.currentMap][monsterIndex].exp;
                        gp.ui.addMessage("Exp +"+gp.monster[gp.currentMap][monsterIndex].exp+"!");
                        gp.player.checkLvlUp();
                    }
                }
                alive=false;
            }
        }
        if(user!=gp.player){
            boolean contactPlayer=gp.cChecker.checkPlayer(this);
            if(gp.player.invisible==false&&contactPlayer==true){
                if(gp.player.invisible==false){

                    gp.player.life-=attack;
                    gp.player.invisible=true;
                }
                gp.ui.addMessage("Taken " + attack + " damage!");
                alive=false;
            }
        }

        switch (direction){
            case "up": worldY-=speed;break;
            case "down": worldY+=speed;break;
            case "left": worldX-=speed;break;
            case "right": worldX+=speed;break;
        }
        life--;
        if(life<=0){
            alive=false;
        }
        spriteCounter++;
        if(spriteCounter>12){
            if(spriteNum==1){
                spriteNum = 2;
            }
            else if(spriteNum==2){
                spriteNum=1;
            }
            spriteCounter=0;
        }
    }
    public boolean haveResource(Entity user){
      boolean haveResource=false;
      return haveResource;
    }
    public void subtractResource(Entity user){
    }
}
