package uk.co.anttheantster.antsbanknotes.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HelpCommand {

    public void helpMessage(Player player){

        player.sendMessage(ChatColor.GOLD + "--(AntsBankNotes Help)--");
        player.sendMessage(ChatColor.GOLD + "/abn help " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Displays this Message");

    }

}
