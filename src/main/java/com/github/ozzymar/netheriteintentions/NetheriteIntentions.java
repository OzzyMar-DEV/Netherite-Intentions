package com.github.ozzymar.netheriteintentions;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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
                            ItemStack[] armor = player.getInventory().getArmorContents();
                            boolean isWearingNetheriteHelm =
                                    Arrays.stream(armor).collect(Collectors.toList()).contains(new ItemStack(Material.NETHERITE_HELMET));
                            boolean isWearingNetheriteChest =
                                    Arrays.stream(armor).collect(Collectors.toList()).contains(new ItemStack(Material.NETHERITE_CHESTPLATE));
                            boolean isWearingNetheriteLegs =
                                    Arrays.stream(armor).collect(Collectors.toList()).contains(new ItemStack(Material.NETHERITE_LEGGINGS));
                            boolean isWearingNetheriteBoots =
                                    Arrays.stream(armor).collect(Collectors.toList()).contains(new ItemStack(Material.NETHERITE_BOOTS));
                            boolean isWearingFullNetherite =
                                    isWearingNetheriteHelm && isWearingNetheriteChest && isWearingNetheriteLegs && isWearingNetheriteBoots;
                            if (!isWearingFullNetherite) return;
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
