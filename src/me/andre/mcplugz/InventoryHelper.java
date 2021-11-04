package me.andre.mcplugz;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.Map;

public class InvH {
    /**
     * Checks players Main-hand and Off-hand is Holding @param itemStack or not
     * */
    public static boolean isHolding(ItemStack itemStack, Player player){
        PlayerInventory inventory = player.getInventory();
        if(inventory.getItemInMainHand().isSimilar(itemStack)){
            return true;
        }
        return inventory.getItemInOffHand().isSimilar(itemStack);
    }

    // https://bukkit.org/threads/give-a-player-an-item-in-inventory-and-drop-when-full.122005/
    public static void giveItem(Player player, ItemStack itemStack){
        HashMap<Integer, ItemStack> nope = player.getInventory().addItem(itemStack);
        for(Map.Entry<Integer, ItemStack> entry : nope.entrySet())
        {
            player.getWorld().dropItemNaturally(player.getLocation().clone().add(0, .5, 0), entry.getValue());
        }
    }
}
