package uk.co.anttheantster.antsbanknotes;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.anttheantster.antsbanknotes.commands.WithdrawCommand;

public class AntsBankNotes extends JavaPlugin {

    private static AntsBankNotes instance;

    private static Economy econ = null;

    @Override
    public void onEnable() {
        instance = this;

        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

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
        getCommand("withdraw").setExecutor(new WithdrawCommand());
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy(){
        return econ;
    }
}
