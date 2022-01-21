package uk.co.anttheantster.antsbanknotes.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.anttheantster.antsbanknotes.AntsBankNotes;

public class WithdrawCommand implements CommandExecutor {

    private AntsBankNotes antsBankNotes = AntsBankNotes.getInstance();

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
        }

        return false;
    }
}
