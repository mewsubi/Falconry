package io.mewsub.falconry.Listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.inventory.PrepareItemCraftEvent;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

import org.bukkit.inventory.meta.ItemMeta;

import org.bukkit.persistence.PersistentDataContainer;

import io.mewsub.falconry.Falconry;
import io.mewsub.falconry.Data.UUIDDataType;

public class PrepareItemCraft implements Listener {
    
    @EventHandler
    public void onPrepareItemCraft( PrepareItemCraftEvent evt ) {
    	CraftingInventory inv = evt.getInventory();
    	ItemStack potion = inv.getResult();
    	if( potion != null && potion.getType() == Material.POTION ) {
    		ItemMeta meta = potion.getItemMeta();
    		if( meta.getDisplayName().equals( "Falcon Potion" ) ) {
    			List<HumanEntity> players = evt.getViewers();
    			HumanEntity human = null;
    			for( HumanEntity p : players ) {
    				human = p;
    			}
    			Player player = Falconry.server.getPlayer( human.getName() );
    			NamespacedKey key = new NamespacedKey( Falconry.plugin, "FALCONER" );
                PersistentDataContainer data = meta.getPersistentDataContainer();
                UUID uuid = player.getUniqueId();
                data.set( key, new UUIDDataType(), uuid );
                List<String> lore = new ArrayList<String>();
                lore.add( "Falconer: " + player.getDisplayName() );
                meta.setLore( lore );
                potion.setItemMeta( meta );
    		}
    	}
    }

}