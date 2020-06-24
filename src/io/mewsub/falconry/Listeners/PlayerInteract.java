package io.mewsub.falconry.Listeners;

import java.util.UUID;

import org.bukkit.NamespacedKey;

import org.bukkit.persistence.PersistentDataContainer;

import org.bukkit.Material;

import org.bukkit.block.Block;
import org.bukkit.block.Barrel;

import org.bukkit.block.data.BlockData;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.player.PlayerInteractEvent;

import org.bukkit.event.block.Action;

import org.bukkit.entity.Player;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import org.bukkit.inventory.meta.ItemMeta;

import io.mewsub.falconry.Falconry;

public class PlayerInteract implements Listener {
    
    @EventHandler
    public void onPlayerInteract( PlayerInteractEvent evt ) {
    	if( evt.hasItem() ) {
    		ItemStack item = evt.getItem();
    		if( item.getType() == Material.FIREWORK_ROCKET ) {
    			Player p = evt.getPlayer();
    			if( Falconry.falcons.contains( p.getUniqueId() ) ) {
    				p.getInventory().setItemInMainHand( new ItemStack( Material.FIREWORK_ROCKET, 2 ) );
    			}
    		}
    	}
    }

}