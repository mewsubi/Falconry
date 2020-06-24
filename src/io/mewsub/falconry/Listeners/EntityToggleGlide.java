package io.mewsub.falconry.Listeners;

import java.util.UUID;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;

import org.bukkit.inventory.meta.ItemMeta;

import org.bukkit.event.entity.EntityToggleGlideEvent;

import io.mewsub.falconry.Falconry;
import io.mewsub.falconry.Data.UUIDDataType;

public class EntityToggleGlide implements Listener {
    
    @EventHandler
    public void onEntityToggleGlide( EntityToggleGlideEvent evt ) {
        Entity e = evt.getEntity();
        if( e instanceof Player ) {
            Player p = ( Player ) e;
            UUID uuid = p.getUniqueId();
            boolean canFly = Falconry.falcons.contains( uuid );
            if( evt.isGliding() != canFly ) {
                evt.setCancelled( true );
            }
        }
    }

}