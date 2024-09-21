package main;

import Entity.Entity;
import Entity.NPC_Black;
import Entity.Merchant;
import Entity.Skeleton;
import Object.DarkSword;
import Object.DarkShield;
import Object.Heart;
import Object.Hermes;
import Object.ShieldOfTheLost;
import Object.Coin;
import Object.Teleport;

public class Setter {

    GamePanel gp;
    public Setter(GamePanel gp){
        this.gp=gp;
    }
    public void setObj(){
        //map0
        setter(gp.obj,0,23,23,new Coin(gp),gp.player.object);
        gp.player.object++;
        setter(gp.obj,0,24,24,new Coin(gp),gp.player.object);
        gp.player.object++;
        setter(gp.obj,0,23,24,new Coin(gp),gp.player.object);
        gp.player.object++;
        setter(gp.obj,0,24,23,new Coin(gp),gp.player.object);
        gp.player.object++;
        setter(gp.obj,0,22,23,new Coin(gp),gp.player.object);
        gp.player.object++;
        setter(gp.obj,0,23,15,new Coin(gp),gp.player.object);
        gp.player.object++;
        setter(gp.obj,0,23,14,new Coin(gp),gp.player.object);
        gp.player.object++;
        setter(gp.obj,0,23,16,new Coin(gp),gp.player.object);
        gp.player.object++;
        setter(gp.obj,0,23,17,new Coin(gp),gp.player.object);
        gp.player.object++;
        setter(gp.obj,0,39,36,new Teleport(gp),gp.player.object);
        gp.player.object++;
        //map1
        gp.player.object=0;
        setter(gp.obj,1,35,13,new Teleport(gp),gp.player.object);
        gp.player.object++;
    }
    public void setNPC(){
        //map0
        setter(gp.npc,0,25,20,new NPC_Black(gp),gp.player.npc);
        gp.player.npc++;
        //map1
        gp.player.npc=0;
        setter(gp.npc,1,14,8,new Merchant(gp),gp.player.npc);
    }
    public void setMonster(){
        //map0
        setter(gp.monster,0,23,23,new Skeleton(gp),gp.player.monster);
        gp.player.monster++;
        setter(gp.monster,0,23,13,new Skeleton(gp),gp.player.monster);
        gp.player.monster++;
        setter(gp.monster,0,13,23,new Skeleton(gp),gp.player.monster);
        gp.player.monster++;
        setter(gp.monster,0,13,13,new Skeleton(gp),gp.player.monster);
        gp.player.monster++;
        setter(gp.monster,0,33,33,new Skeleton(gp),gp.player.monster);
        gp.player.monster++;
        //map1
        gp.player.monster=0;
    }
    public void setter(Entity array[][],int mapNum,int X,int Y,Entity entity,int count){
        array[mapNum][count]=entity;
        array[mapNum][count].worldX=gp.tileSize*X;
        array[mapNum][count].worldY=gp.tileSize*Y;
    }
}
