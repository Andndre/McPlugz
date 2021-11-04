package me.andre.mcplugz;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import org.bukkit.craftbukkit.v1_15_R1.CraftServer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_15_R1.IRecipe;
import net.minecraft.server.v1_15_R1.MinecraftKey;

import java.util.Arrays;
import java.util.Random;

public class ItemMatH {
    static Random random = new Random();

    /**
     * @param name The name to be Compared
     * @return Material with similar name
     *
     * */
    public static Material getRandomMatContainsName(String name){
        Material res = Material.values()[random.nextInt(Material.values().length)];

        int i = 0;
        while (!res.name().contains(name)){
            if(i > 2000){
                break;
            }
            i++;
            res = Material.values()[random.nextInt(Material.values().length)];
        }

        return res;
    }

    /**
     * Get random Solid Block
     *
     * */
    public static Material getRandomSolidBlock(){
        Material res = Material.values()[random.nextInt(Material.values().length)];
        while (!res.isSolid()){
            res = Material.values()[random.nextInt(Material.values().length)];
        }
        return res;
    }

    /**
     * Get random Block that survival Players can get without Cheat
     * */
    public static Material getRandomSolidLegalBlock(){
        Material res = Material.values()[random.nextInt(Material.values().length)];
        while (!res.isSolid()
                || !res.isAir()
                || res.name().contains("COMMAND")
                || res.name().contains("PORTAL")
                || res.name().contains("HEAD")
                || res.name().contains("BEDROCK")
                || res.name().contains("STRUCTURE")
                || res.name().contains("JIGSAW")){
            res = Material.values()[random.nextInt(Material.values().length)];
        }
        return res;
    }

    public static ItemStack getRandomDiamondItems(boolean addEnchantEffect, String... lore){
        Material mat = Material.values()[random.nextInt(Material.values().length)];

        while (!mat.name().contains("DIAMOND")){
            mat = Material.values()[random.nextInt(Material.values().length)];
        }

        ItemStack result = new ItemStack(mat, 1);
        ItemMeta meta = result.getItemMeta();

        if(addEnchantEffect){
            meta.addEnchant(Enchantment.LUCK, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        if(lore.length != 0){
            meta.setLore(Arrays.asList(lore));
        }

        result.setItemMeta(meta);
        return result;
    }

    public static Material getSapling(Material wood){
        if(wood.name().contains("OAK")){
            return Material.OAK_SAPLING;
        }
        else if(wood.name().contains("ACACIA")){
            return Material.ACACIA_SAPLING;
        }
        else if(wood.name().contains("JUNGLE")){
            return Material.JUNGLE_SAPLING;
        }
        else if(wood.name().contains("BIRCH")){
            return Material.BIRCH_SAPLING;
        }
        else if(wood.name().contains("DARK")){
            return Material.DARK_OAK_SAPLING;
        }
        else if(wood.name().contains("SPRUCE")){
            return Material.SPRUCE_SAPLING;
        }
        return Material.OAK_SAPLING;
    }

    public static TreeType getTreeType(Material sapling){
        if(sapling.name().contains("OAK")){
            return TreeType.TREE;
        }
        else if(sapling.name().contains("BIRCH")){
            return TreeType.BIRCH;
        }
        else if(sapling.name().contains("DARK")){
            return TreeType.DARK_OAK;
        }
        else if(sapling.name().contains("ACACIA")){
            return TreeType.ACACIA;
        }
        else if(sapling.name().contains("JUNGLE")){
            return TreeType.JUNGLE;
        }
        else if(sapling.name().contains("SPRUCE")){
            return TreeType.REDWOOD;
        }
        return TreeType.TREE;
    }

    // ! Resource leak: '<unassigned Closeable value>' is never closed <-> Java(536871799)
    @SuppressWarnings("all")
    public static void removeRecipeByKey(String recipeKey) {
        MinecraftKey key = new MinecraftKey(recipeKey);
        for (Object2ObjectLinkedOpenHashMap<MinecraftKey, IRecipe<?>> recipes : ((CraftServer) Bukkit.getServer()).getServer().getCraftingManager().recipes.values()) {
            recipes.remove(key);
        }
    }
}
