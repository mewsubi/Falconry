package io.mewsub.falconry.Enchantments;

import org.bukkit.NamespacedKey;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;

import org.bukkit.inventory.ItemStack;

public class GlowEnchant extends Enchantment {

    public GlowEnchant( NamespacedKey key ) {
        super( key );
    }

    @Override
    public boolean canEnchantItem( ItemStack item ) {
        return false;
    }

    @Override
    public boolean conflictsWith( Enchantment e ) {
        return false;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

}