package me.andre.mcplugz;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class HashmapHelper {

    public static void registerIfNotIncluded(Map<String, Integer> map, String key, Integer value){

        if(!map.containsKey(key)){
            map.put(key, value);
        }
    }

    public static void registerIfNotIncluded(Map<Player, Integer> map, Player key, Integer value){

        if(!map.containsKey(key)){
            map.put(key, value);
        }
    }

    public static void registerIfNotIncluded(Map<Entity, Integer> map, Entity key, Integer value){

        if(!map.containsKey(key)){
            map.put(key, value);
        }
    }

    public static void putOrReplace(Map<String, Integer> map, String key, Integer value, @Nullable Boolean valueZeroAtFirst){

        if(valueZeroAtFirst != null){
            if(!valueZeroAtFirst){
                if(map.containsKey(key)){
                    map.replace(key, value);
                    return;
                }
                map.put(key, value);
                return;
            }
            if(map.containsKey(key)){
                map.replace(key, 0);
                return;
            }
            map.put(key, 0);
        }
    }
    public static void putOrReplace(Map<Player, Integer> map, Player key, Integer value, @Nullable Boolean valueZeroAtFirst){
        if(valueZeroAtFirst != null){
            if(!valueZeroAtFirst){
                if(map.containsKey(key)){
                    map.replace(key, value);
                    return;
                }
                map.put(key, value);
                return;
            }
            if(map.containsKey(key)){
                map.replace(key, 0);
                return;
            }
            map.put(key, 0);
        }
    }
    public static void putOrReplace(Map<String, Boolean> map, String key, Boolean value){
        if(map.containsKey(key)){
            map.replace(key, value);
            return;
        }
        map.put(key, value);
    }
    public static void putOrReplace(Map<Player, Boolean> map, Player key, Boolean value){
        if(map.containsKey(key)){
            map.replace(key, value);
            return;
        }
        map.put(key, value);
    }

    public static Map<String, Integer> sortByValue(Map<String, Integer> unSortMap, final boolean order)
    {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unSortMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    }
}
