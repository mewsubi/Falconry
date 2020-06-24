package io.mewsub.falconry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import java.lang.reflect.Field;

import org.bukkit.plugin.Plugin;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.Server;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import org.bukkit.enchantments.Enchantment;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import io.mewsub.falconry.Enchantments.GlowEnchant;

import io.mewsub.falconry.Listeners.EntityToggleGlide;
import io.mewsub.falconry.Listeners.PlayerInteract;
import io.mewsub.falconry.Listeners.PlayerInteractEntity;
import io.mewsub.falconry.Listeners.PlayerItemConsume;
import io.mewsub.falconry.Listeners.PrepareItemCraft;

public class Falconry extends JavaPlugin {

    public static Plugin plugin;
    public static Server server;

    public static Map<UUID, UUID> pairs;
    public static Set<UUID> falcons;

    @Override
    public void onEnable() {
        Falconry.plugin = ( Plugin ) this;
        Falconry.server = this.getServer();
        Falconry.pairs = new HashMap<UUID, UUID>();
        Falconry.falcons = new HashSet<UUID>();

        Falconry.server.getPluginManager().registerEvents( new EntityToggleGlide(), this );
        Falconry.server.getPluginManager().registerEvents( new PlayerInteract(), this );
        Falconry.server.getPluginManager().registerEvents( new PlayerInteractEntity(), this );
        Falconry.server.getPluginManager().registerEvents( new PlayerItemConsume(), this );
        Falconry.server.getPluginManager().registerEvents( new PrepareItemCraft(), this );

        NamespacedKey key = new NamespacedKey( this, getDescription().getName() );

        try {
            Field f = Enchantment.class.getDeclaredField( "acceptingNew" );
            f.setAccessible( true );
            f.set( null, true );
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        try {
            GlowEnchant glowEnchant = new GlowEnchant( key );
            Enchantment.registerEnchantment( glowEnchant );
        } catch ( IllegalArgumentException e ){
        } catch( Exception e ){
            e.printStackTrace();
        }

        ItemStack falcPot = new ItemStack( Material.POTION );
        ItemMeta falcPotIMeta = ( ItemMeta ) falcPot.getItemMeta();

        PotionMeta falcPotPMeta = ( PotionMeta ) falcPotIMeta;
        falcPotPMeta.setBasePotionData( new PotionData( PotionType.WATER, false, false ) );
        falcPotPMeta.setColor( Color.WHITE );

        falcPotIMeta.setDisplayName( "Falcon Potion" );
        falcPotIMeta.addItemFlags( ItemFlag.HIDE_ENCHANTS );
        falcPotIMeta.addItemFlags( ItemFlag.HIDE_POTION_EFFECTS );

        GlowEnchant glowEnchant = new GlowEnchant( key );
        falcPotIMeta.addEnchant( glowEnchant, 1, true );

        falcPot.setItemMeta( falcPotIMeta );

        NamespacedKey falcPotKey = new NamespacedKey( this, "FALCON_POTION" );

        ShapedRecipe falcPotRecipe = new ShapedRecipe( falcPotKey, falcPot );
        falcPotRecipe.shape( " G ", "GFG", " G " );
        falcPotRecipe.setIngredient( 'G', Material.GLASS );
        falcPotRecipe.setIngredient( 'F', Material.FEATHER );

        Falconry.server.addRecipe( falcPotRecipe );
    }

    @Override
    public void onDisable() {
        Scoreboard scoreboard = Falconry.server.getScoreboardManager().getMainScoreboard();
        for( Team team : scoreboard.getTeams() ) {
            team.unregister();
        }
    }

}
