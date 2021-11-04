package me.andre.mcplugz;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ScoreboardManager {
    private final String name;
    private final String displayName;
    private Scoreboard scoreboard;
    private Objective objective;

    private final List<Player> players;

    public ScoreboardManager(String name, String displayName){
        this.name = name;
        this.displayName = displayName;

        players = new ArrayList<>();

    }

    @SuppressWarnings("ConstantConditions")
    public void create(Player player){
        players.add(player);

        org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
        this.scoreboard = manager.getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective(this.name.replace(" ", ""), "dummy", this.displayName);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(scoreboard);
    }


    @SuppressWarnings("ConstantConditions")
    private void updateScore(Player player, String label, int newScore){
        if(label.length() > 39){
            return;
        }
        try {
            player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(label).setScore(newScore);
        }catch (Exception err){
            err.printStackTrace();
        }
    }

    public void put(String label, int score){
        for(Player player: getPlayers()){
            updateScore(player, label, score);
        }
    }

    @SuppressWarnings("ConstantConditions")
    public void remove(){
        for(Player player: getPlayers()){
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }
    }


    public Scoreboard getScoreboard(){
        return this.scoreboard;
    }

    public List<Player> getPlayers(){
        return this.players;
    }

}





