package world;

import java.util.Random;

public class PlayerE extends CreatureAI{
    private CreatureFactory factory;
    private PlayerAI myFather;

    public PlayerE(Creature creature,CreatureFactory factory,PlayerAI father) {
        super(creature);
        this.factory = factory;
        this.myFather=father;
    }

    @Override
    public  void onEnter(int x, int y, Tile tile) {
        if (tile.isGround()) {
            creature.setX(x);
            creature.setY(y);
        }
    }

    @Override
    public void run(){
        while(this.creature.hp()>0&&creature.getWorld().tile(creature.getX()+1,creature.getY())!=Tile.BOUNDS){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                this.creature.moveWall(1,0);
                if(creature.getWorld().tile(creature.getX()+1,creature.getY())==Tile.BOUNDS){
                    this.creature.getWorld().getCreatures().remove(this.creature);
                    this.myFather.myE=null;
                }
        }

    }
}

