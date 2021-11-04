package me.andre.mcplugz;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ConnectedBlocksHandler {

    private static final BlockFace[] faces = {
            BlockFace.DOWN,
            BlockFace.UP,
            BlockFace.NORTH,
            BlockFace.EAST,
            BlockFace.SOUTH,
            BlockFace.WEST
    };


    private static void getConnectedBlocks(Block block, Set<Block> results, List<Block> todo) {
        //Here I collect all blocks that are directly connected to variable 'block'.
        //(Shouldn't be more than 6, because a block has 6 sides)

        //Loop through all block faces (All 6 sides around the block)
        for (BlockFace face : faces) {
            Block b = block.getRelative(face);
            //Check if they're both of the same type
            if (b.getType() == block.getType()) {
                //Add the block if it wasn't added already
                if (results.add(b)) {

                    //Add this block to the list of blocks that are yet to be done.
                    todo.add(b);
                }
            }
        }
    }

    public static Set<Block> getConnectedBlocks(Block block) {
        Set<Block> set = new HashSet<>();
        LinkedList<Block> list = new LinkedList<>();

        list.add(block);

        while((block = list.poll()) != null) {
            getConnectedBlocks(block, set, list);
        }
        return set;
    }

    public static void removeConnectedBlocks(Location location, @Nullable Integer max) {
        if(max == null){
            Block bl = location.getBlock();
            Set<Block> blocks = getConnectedBlocks(bl);
            for(Block b: blocks){
                b.setType(Material.AIR);
            }
        }else {
            if(max <= 0){
                return;
            }
            Block bl = location.getBlock();
            Set<Block> blocks = getConnectedBlocks(bl);
            int i = 0;
            for(Block b: blocks){
                if(i >= max){
                    break;
                }
                i++;
                b.setType(Material.AIR);
            }
        }

    }
}
