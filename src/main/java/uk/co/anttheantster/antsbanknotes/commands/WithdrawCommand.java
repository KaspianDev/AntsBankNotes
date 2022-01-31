package uk.co.anttheantster.antsbanknotes.commands;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import uk.co.anttheantster.antsbanknotes.AntsBankNotes;
import uk.co.anttheantster.antsbanknotes.items.CreateBankNote;

public class WithdrawCommand implements CommandExecutor {

    private final Economy econ = AntsBankNotes.getEconomy();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("withdraw")){
            if (!(sender instanceof Player)){
                sender.sendMessage(ChatColor.RED + "Only a player can do this command!");
                return false;
            }

            Player player = (Player) sender;
            if (!player.hasPermission("abn.withdraw")){
                player.sendMessage("No permission!");
                return false;
            }

            if (args.length < 1){
               player.sendMessage("You need to specify an amount like so: (/withdraw <Amount>)");
               return false;
            }

            if (args.length > 1){
                player.sendMessage(ChatColor.RED + "Usage: /withdraw <Amount>");
                return false;
            }
            float amount;
            try {
                amount = Float.parseFloat(args[0]);

            } catch (Exception e){
                player.sendMessage(ChatColor.RED + "Incorrect Usage!");
                player.sendMessage(ChatColor.RED + "Usage: /withdraw <Amount>");
                return false;
            }
            if (amount > econ.getBalance(player)){
                player.sendMessage(ChatColor.RED + "Insufficient Funds!");
                return false;
            }
            econ.withdrawPlayer(player, amount);


            CreateBankNote createBankNote = new CreateBankNote(amount, player, player.getName());
            ItemStack moneyItem = createBankNote.BankNote();

            try {
                player.getInventory().addItem(moneyItem);
                player.sendMessage(ChatColor.GOLD + "You have successfully withdrawn: " + ChatColor.GREEN + amount);
            } catch (Exception e){
                player.sendMessage(ChatColor.RED + "No Inventory Space!");
                econ.depositPlayer(player, amount);
            }
        }

        return false;
    }
}
