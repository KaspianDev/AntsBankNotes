package uk.co.anttheantster.antsbanknotes.commands;

import com.sun.istack.internal.NotNull;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uk.co.anttheantster.antsbanknotes.AntsBankNotes;

import java.util.ArrayList;
import java.util.Objects;

public class WithdrawCommand implements CommandExecutor {

    private AntsBankNotes antsBankNotes = AntsBankNotes.getInstance();
    private FileConfiguration config = antsBankNotes.getConfig();
    private Economy econ = AntsBankNotes.getEconomy();

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
            int amount = 0;
            try {
                amount = Integer.parseInt(args[0]);

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


            ItemStack moneyItem = null;
            ItemMeta moneyItemMeta = null;
            try {
                moneyItem = new ItemStack(Objects.requireNonNull(Material.getMaterial(config.getString("Money Item"))));
                moneyItemMeta = moneyItem.getItemMeta();
            } catch (Exception e){
                player.sendMessage(ChatColor.RED + "Invalid Item. Please ensure the 'Money Item' in the config is correct");
                return false;
            }

            ArrayList<String> lore = new ArrayList<String>();
            lore.add(ChatColor.AQUA + "Amount: " + ChatColor.GOLD + amount);
            lore.add(ChatColor.AQUA + "Signed: " + ChatColor.GOLD + player.getName());

            moneyItemMeta.setDisplayName(ChatColor.GOLD + "Banknote of: " + ChatColor.GREEN + amount);
            moneyItemMeta.setLore(lore);
            moneyItemMeta.setUnbreakable(true);
            moneyItemMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
            moneyItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            moneyItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            moneyItem.setItemMeta(moneyItemMeta);

            try {
                player.getInventory().addItem(moneyItem);
                player.sendMessage(ChatColor.GOLD + "You successfully withdrew:" + ChatColor.GREEN + amount);
            } catch (Exception e){
                player.sendMessage(ChatColor.RED + "No Inventory Space!");
                econ.depositPlayer(player, amount);
            }
        }

        return false;
    }
}
