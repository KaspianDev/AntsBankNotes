package uk.co.anttheantster.antsbanknotes.commands;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uk.co.anttheantster.antsbanknotes.AntsBankNotes;
import uk.co.anttheantster.antsbanknotes.items.CreateBankNote;

import java.util.ArrayList;
import java.util.Objects;

public class CommandController implements CommandExecutor {
    HelpCommand help = new HelpCommand();

    private FileConfiguration config = AntsBankNotes.getInstance().getConfig();
    private Economy econ = AntsBankNotes.getEconomy();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("abn")){
            if (!sender.hasPermission("abn.admin")){
                sender.sendMessage(ChatColor.RED + "No Permission!");
            }
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
                float amount = 0;
                try {
                    amount = Float.parseFloat(args[1]);

                } catch (Exception e){
                    player.sendMessage(ChatColor.RED + "Incorrect Usage!");
                    player.sendMessage(ChatColor.RED + "Usage: /abn create <amount>");
                    return false;
                }

                ArrayList<String> lore = new ArrayList<String>();
                lore.add(ChatColor.AQUA + "Amount: " + ChatColor.GOLD + amount);
                lore.add(ChatColor.AQUA + "Signed: " + ChatColor.GOLD + "Server");

                try {
                    CreateBankNote createBankNote = new CreateBankNote(amount, player, "Server");
                    ItemStack moneyItem = createBankNote.BankNote();

                    ItemMeta moneyItemMeta = moneyItem.getItemMeta();
                    moneyItemMeta.setLore(lore);
                    moneyItem.setItemMeta(moneyItemMeta);

                    player.getInventory().addItem(moneyItem);

                } catch (Exception e){
                    player.sendMessage(ChatColor.RED + "Cannot Create Server BankNote");
                }
                return false;
            }

            if (args[0].equalsIgnoreCase("reload")){
                AntsBankNotes.getInstance().reloadConfig();
                return false;
            }
        }


        return false;
    }
}
