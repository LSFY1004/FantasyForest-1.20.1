package com.qiuyu.fantasyforest.item.customtool;

import com.qiuyu.fantasyforest.KeyHelper;
import com.qiuyu.fantasyforest.item.ModToolMaterials;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.qiuyu.fantasyforest.item.customtool.ModSwordItem.hitEffect;

public class ModHoeItem extends HoeItem {
    private final ModToolMaterials material;
    public ModHoeItem(ModToolMaterials material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
        this.material = material;
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (!KeyHelper.isShiftKeyDown()) tooltip.add(Text.translatable("tooltip.arboreal"));
        else tooltip.add(Text.translatable("tooltip.arboreal.shift"));
        tooltip.add(Text.translatable(ModSwordItem.info(material)));
        if (!KeyHelper.isShiftKeyDown()) tooltip.add(Text.translatable("tooltip.fantasyforest.more"));
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        hitEffect(material, target);
        return super.postHit(stack, target, attacker);
    }
}
