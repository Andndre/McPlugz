package me.andre.mcplugz;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class ConfigManager {

    private final String fileName;
    private final Plugin plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    public ConfigManager(Plugin plugin, String fileName){
        this.plugin = plugin;
        this.fileName = fileName;
        saveDefaultConfig();
    }

    public void reloadConfig(){
        if(this.configFile == null){
            this.configFile = new File(this.plugin.getDataFolder(), this.fileName);
        }
        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
        InputStream defaultStream = this.plugin.getResource(this.fileName);
        if(defaultStream != null){
            YamlConfiguration defaultConfiguration = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfiguration);
        }
    }

    public FileConfiguration getConfig(){
        if(this.dataConfig == null){
            reloadConfig();
        }

        return this.dataConfig;
    }

    public void saveConfig(){
        if(this.dataConfig == null || this.configFile == null){
            return;
        }
        try {
            this.getConfig().save(this.configFile);
        }catch (IOException err){
            this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, err);
        }
    }

    public void saveDefaultConfig(){
        if(configFile == null){
            this.configFile = new File(this.plugin.getDataFolder(), this.fileName);
        }

        if(!this.configFile.exists()){
            this.plugin.saveResource(this.fileName, false);
        }
    }
}
