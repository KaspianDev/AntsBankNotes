package uk.co.anttheantster.antsbanknotes.listeners;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import uk.co.anttheantster.antsbanknotes.AntsBankNotes;
import uk.co.anttheantster.antsbanknotes.commands.WithdrawCommand;
import uk.co.anttheantster.antsbanknotes.items.CreateBankNote;

public class PlayerWithdraw extends WithdrawCommand implements Listener {

    @EventHandler
    public void redeemBanknote(PlayerInteractEvent e){
        Economy econ = AntsBankNotes.getEconomy();

        if (e.hasItem() && e.getAction().equals(Action.RIGHT_CLICK_AIR)){
            Player p = e.getPlayer();
            if (p.getInventory().getItemInMainHand().equals(CreateBankNote.bankNote)){
                econ.depositPlayer(p, CreateBankNote.noteAmount);
                p.getInventory().setItemInMainHand(null);
                p.sendMessage(ChatColor.GOLD + "Wpłaciłeś/aś: " + ChatColor.GREEN + CreateBankNote.noteAmount + "$");
                e.setCancelled(true);
            }
        }
    }

}
