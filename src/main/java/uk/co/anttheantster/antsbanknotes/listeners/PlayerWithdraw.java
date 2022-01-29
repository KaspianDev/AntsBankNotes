package uk.co.anttheantster.antsbanknotes.listeners;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import uk.co.anttheantster.antsbanknotes.AntsBankNotes;
import uk.co.anttheantster.antsbanknotes.commands.WithdrawCommand;

public class PlayerWithdraw extends WithdrawCommand implements Listener {

    @EventHandler
    public void redeemBanknote(PlayerInteractEvent e){
        Economy econ = AntsBankNotes.getEconomy();

        if (e.hasItem()){
            Player p = e.getPlayer();
            if (p.getInventory().getItemInMainHand().equals(WithdrawCommand.bankNote)){
                econ.depositPlayer(p, WithdrawCommand.noteAmount);
                p.getInventory().removeItem(WithdrawCommand.bankNote);
                p.sendMessage(ChatColor.GOLD + "You have successfully deposited: " + ChatColor.GREEN + WithdrawCommand.noteAmount);
            }
        }
    }

}
