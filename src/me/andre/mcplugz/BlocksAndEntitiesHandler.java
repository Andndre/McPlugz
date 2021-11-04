package me.andre.mcplugz;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class BlocksAndEntitiesHandler {

    /**
     * Replace a block with Falling block with same material
     *
     * @param block The block
     *
     * @return Falling Block
     * */
    @Deprecated
    public static FallingBlock block_SetFalling(Block block){
        FallingBlock fallingBlock = null;
        Location loc = new Location(block.getWorld(), block.getX() + .5, block.getY(), block.getZ() + .5);

        Location below = loc.clone().subtract(0, 1, 0);
        if(!below.getBlock().getType().isSolid()){
            Material material = block.getType();
            block.setType(Material.AIR);
            fallingBlock = block.getWorld().spawnFallingBlock(loc, material, block.getData());
        }

        return fallingBlock;
    }

    /**
     * Get all falling blocks (Sand, and Gravel).
     *
     * @param center Center location
     * @param distance Distance from the center
     * @return Falling blocks
     * */
    public static List<FallingBlock> getNearByFallingBlocks(Location center, int distance, boolean fromTopToBottom){
        List<FallingBlock> nearByFallingBlocks = new ArrayList<>();
        Location start = center.add(-distance, -distance, -distance);

        List<Location> fbLocs = Shapes.cube(center, distance, true, fromTopToBottom);
        for(Location loc: fbLocs){
            FallingBlock fallingBlock = block_SetFalling(loc.getBlock());
            nearByFallingBlocks.add(fallingBlock);
        }

        return nearByFallingBlocks;
    }
}
