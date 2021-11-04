package me.andre.mcplugz;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class CustomEnchants {

    private static final Random rand = new Random();

    public static void register(Enchantment enchantment){
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(enchantment);

        if(!registered){
            registerEnchantment(enchantment);
        }
    }

    @SuppressWarnings("ConstantConditions")
    public static void addEnchantment(ItemStack itemStack, Enchantment enchantment, int lvl){
        itemStack.addUnsafeEnchantment(enchantment, lvl);

        ItemMeta meta = itemStack.getItemMeta();

        // enchantment.toString --> "Enchantment[" + enchantmentKey + ", " + enchantmentName + "]"
        String name = enchantment.toString().split(", ")[1].replace("]", "");

        meta.setLore(Collections.singletonList(ChatColor.GRAY + name + " " + Mth.intToRoman(lvl)));
        itemStack.setItemMeta(meta);
    }

    public static int getRandomLevel(Enchantment enchantment){
        return Mth.random(enchantment.getStartLevel(), enchantment.getMaxLevel());
    }

    private static void registerEnchantment(Enchantment enchantment){
        boolean registered = true;

        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);

        }catch (Exception err){
            registered = false;
            err.printStackTrace();
        }

        if(registered){
            System.out.println(ChatColor.GREEN + enchantment.getKey().toString() + " registered!");
        }
    }


}
