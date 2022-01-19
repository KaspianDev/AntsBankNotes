package uk.co.anttheantster.antsbanknotes;

import org.bukkit.plugin.java.JavaPlugin;

public class AntsBankNotes extends JavaPlugin {

    private static AntsBankNotes instance;

    @Override
    public void onEnable() {
        instance = this;
        setup();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static AntsBankNotes getInstance(){return instance;}

    private void setup(){
        saveDefaultConfig();
        registerCommandsAndListeners();
    }

    private void registerCommandsAndListeners(){

    }
}
