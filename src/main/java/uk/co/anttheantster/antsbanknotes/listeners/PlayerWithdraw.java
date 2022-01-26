package uk.co.anttheantster.antsbanknotes.listeners;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import uk.co.anttheantster.antsbanknotes.AntsBankNotes;
import uk.co.anttheantster.antsbanknotes.commands.WithdrawCommand;

public class PlayerWithdraw implements Listener {

    public void redeemBanknote(PlayerInteractEvent e){
        Economy econ = AntsBankNotes.getEconomy();

        if (e.hasItem()){
            Player p = e.getPlayer();
        }
    }

}
