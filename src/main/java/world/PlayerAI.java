/*
 * Copyright (C) 2015 Aeranythe Echosong
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package world;

import java.util.List;

/**
 *
 * @author Aeranythe Echosong
 */
public class PlayerAI extends CreatureAI {

    private List<String> messages;
    private CreatureFactory factory;
    public Creature myE=null;
    public int cd=0;
    public int visionCd=0;

    public PlayerAI(Creature creature, List<String> messages, CreatureFactory factory) {
        super(creature);
        this.messages = messages;
        this.factory=factory;
    }

    public void onEnter(int x, int y, Tile tile) {
        if (tile.isGround()) {
            if (tile.isDiggable()) {
                if(tile==Tile.VISION) {
                    visionCd=100;
                    this.creature.setVisionRadius(10);
                }
                else if(tile==Tile.BLOOD && creature.maxHP()> creature.hp()) {
                    this.creature.setHp(creature.hp()+1);
                }
                creature.dig(x, y);
            }
            creature.setX(x);
            creature.setY(y);
        }
    }

    public void E(){
        if(cd==100) {
            Creature creature = this.factory.newPlayerE(this);
            myE = creature;
            Thread attack = new Thread(creature.getAI());
            attack.start();
            cd=0;
        }
    }

//    public void onNotify(String message) {
//        this.messages.add(message);
//    }
}
