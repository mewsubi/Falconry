package io.mewsub.falconry.Listeners;

import java.util.UUID;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.player.PlayerInteractEntityEvent;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import io.mewsub.falconry.Falconry;

public class PlayerInteractEntity implements Listener {
    
    @EventHandler
    public void onPlayerInteractEntity( PlayerInteractEntityEvent evt ) {
    	Player p1 = evt.getPlayer();
    	Entity e = evt.getRightClicked();
        if( e instanceof Player ) {
            UUID uuidP1 = p1.getUniqueId();
            Player p2 = ( Player ) e;
            UUID uuidP2 = p2.getUniqueId();
            boolean summon = false;
            if( Falconry.pairs.containsKey( uuidP1 ) ) {
                if( Falconry.pairs.get( uuidP1 ).equals( uuidP2 ) ) {
                    summon = true;
                }
            } else if( Falconry.falcons.contains( uuidP1 ) ) {
                if( Falconry.pairs.containsKey( uuidP2 ) ) {
                    if( Falconry.pairs.get( uuidP2 ).equals( uuidP1 ) ) {
                        summon = true;
                    }
                }
            }
            if( summon ) {
                p1.setPassenger( p2 );
            }
        }
    }

}