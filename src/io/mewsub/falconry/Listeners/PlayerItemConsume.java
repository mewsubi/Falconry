package io.mewsub.falconry.Listeners;

import java.util.UUID;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;

import org.bukkit.inventory.meta.ItemMeta;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import org.bukkit.event.player.PlayerItemConsumeEvent;

import io.mewsub.falconry.Falconry;
import io.mewsub.falconry.Data.UUIDDataType;

public class PlayerItemConsume implements Listener {
    
    @EventHandler
    public void onPlayerItemConsume( PlayerItemConsumeEvent evt ) {
    	Player p = evt.getPlayer();
    	UUID uuid = p.getUniqueId();
    	ItemStack item = evt.getItem();
    	if( item.getType() == Material.POTION ) {
    		ItemMeta meta = item.getItemMeta();
    		if( meta.getDisplayName().equals( "Falcon Potion" ) ) {
    			NamespacedKey key = new NamespacedKey( Falconry.plugin, "FALCONER" );
    			UUID uuidPot = meta.getPersistentDataContainer().get( key, new UUIDDataType() );
    			Player pot = Falconry.server.getPlayer( uuidPot );
    			if( uuid.equals( uuidPot ) ) {
    				p.sendMessage( "You can't consume your own Falcon Potion!" );
    			} else if( Falconry.pairs.containsKey( uuidPot ) ) {
					UUID uuidPartner = Falconry.pairs.get( uuidPot );
					Player partner = Falconry.server.getPlayer( uuidPartner );
					if( uuidPartner.equals( uuid ) ) {
						p.sendMessage( "You're already " + pot.getDisplayName() + "'s falcon!" );
					} else {
						p.sendMessage( pot.getDisplayName() + " already has a falcon (" + partner.getDisplayName() + ")!" );
					}
    			} else {
    				Falconry.pairs.put( uuidPot, uuid );
    				Falconry.falcons.add( uuid );
    				Scoreboard scoreboard = Falconry.server.getScoreboardManager().getMainScoreboard();
    				Team team = scoreboard.registerNewTeam( pot.getDisplayName() );
    				team.addEntry( pot.getDisplayName() );
    				team.addEntry( p.getDisplayName() );
    				team.setAllowFriendlyFire( false );
    				p.sendMessage( "You're now " + pot.getDisplayName() + "'s falcon!" );
    				pot.sendMessage( p.getDisplayName() + " is now your falcon!" );
    				p.setGliding( true );
    				p.getInventory().setItemInMainHand( new ItemStack( Material.FIREWORK_ROCKET, 2 ) );
    			}
    			evt.setCancelled( true );
    		}
    	}
    }

}