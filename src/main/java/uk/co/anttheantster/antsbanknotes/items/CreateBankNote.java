package uk.co.anttheantster.antsbanknotes.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uk.co.anttheantster.antsbanknotes.AntsBankNotes;

import java.util.ArrayList;
import java.util.Objects;

public class CreateBankNote {
    public static ItemStack bankNote;
    public static float noteAmount;

    private FileConfiguration config = AntsBankNotes.getInstance().getConfig();

    public CreateBankNote(float amount, Player player, String name) {
        ItemStack moneyItem = null;
        ItemMeta moneyItemMeta = null;
        try {
            moneyItem = new ItemStack(Objects.requireNonNull(Material.getMaterial(config.getString("Money Item"))));
            moneyItemMeta = moneyItem.getItemMeta();
        } catch (Exception e){
            player.sendMessage(ChatColor.RED + "Invalid Item. Please ensure the 'Money Item' in the config is correct");
        }

        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.AQUA + "Wartość: " + ChatColor.GREEN + amount + "$");

        moneyItemMeta.setDisplayName(ChatColor.GREEN + String.valueOf(amount) + "$");
        moneyItemMeta.setLore(lore);
        moneyItemMeta.setUnbreakable(true);
        moneyItemMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        moneyItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        moneyItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        moneyItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        moneyItem.setItemMeta(moneyItemMeta);
        bankNote = moneyItem;
        noteAmount = amount;
    }

    public ItemStack BankNote(){
        return bankNote;
    }

}
