package uk.co.anttheantster.antsbanknotes.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandController implements CommandExecutor {
    HelpCommand help = new HelpCommand();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("abn")){
            if (!(sender instanceof Player)){
                sender.sendMessage(ChatColor.RED + "Only a player can use this command");
                return false;
            }
            Player player = (Player) sender;

            if (args.length < 1){
                player.sendMessage(ChatColor.RED + "Incorrect usage, use /abn help for more info!");
                return false;
            }

            if (args[0].equalsIgnoreCase("help")){
                help.helpMessage(player);
                return false;
            }

            if (args[0].equalsIgnoreCase("create")){
                try {

                } catch (Exception e){
                    player.sendMessage(ChatColor.RED + "Cannot Create Server BankNote");
                }
            }
        }


        return false;
    }
}
