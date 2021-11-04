package me.andre.mcplugz;

import org.bukkit.block.BlockFace;

public class Loc {

    public static BlockFace getMovementDirection(float xFrom, float yFrom, float zFrom, float xTo, float yTo, float zTo){

        if(yFrom > yTo){
            return BlockFace.DOWN;
        }else if(yFrom < yTo){
            return BlockFace.UP;
        }
        if(xFrom > xTo){
            return BlockFace.WEST;
        }else if(xFrom < xTo){
            return BlockFace.EAST;
        }
        if(zFrom > zTo){
            return BlockFace.NORTH;
        }else if(zFrom < zTo){
            return BlockFace.SOUTH;
        }

        return BlockFace.SELF;
    }
}
