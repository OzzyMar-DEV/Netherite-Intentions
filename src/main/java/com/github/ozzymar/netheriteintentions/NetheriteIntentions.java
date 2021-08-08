package com.github.ozzymar.netheriteintentions;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NetheriteIntentions extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers()
                        .stream()
                        .map((player) -> (Player) player)
                        .forEach(player -> {

                            boolean isWearingHelm = false;
                            boolean isWearingChest = false;
                            boolean isWearingLegs = false;
                            boolean isWearingBoots = false;

                            ItemStack[] armor = player.getInventory().getArmorContents();

                            for(ItemStack item : armor)
                            {
                                if(item==null)
                                    continue;

                                if(!isWearingHelm)
                                    isWearingHelm = item.getType().equals(Material.NETHERITE_HELMET);

                                if(!isWearingChest)
                                    isWearingChest = item.getType().equals(Material.NETHERITE_CHESTPLATE);

                                if(!isWearingLegs)
                                    isWearingLegs = item.getType().equals(Material.NETHERITE_LEGGINGS);

                                if(!isWearingBoots)
                                    isWearingBoots = item.getType().equals(Material.NETHERITE_BOOTS);
                            }

                            if(!isWearingHelm || !isWearingChest || !isWearingLegs|| !isWearingBoots)
                                return;

                            new PotionEffect(
                                    PotionEffectType.FIRE_RESISTANCE,
                                    35,
                                    0,
                                    false,
                                    false,
                                    false
                            ).apply(player);
                        });
            }
        }.runTaskTimer(this, 0, 1);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
