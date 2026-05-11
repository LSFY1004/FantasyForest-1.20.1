package com.qiuyu.fantasyforest;

import com.mojang.blaze3d.systems.RenderSystem;
import com.qiuyu.fantasyforest.effect.ModEffects;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class CupidKissOverlay implements HudRenderCallback {

    private static final Identifier OVERLAY_TEXTURE =
            new Identifier(FantasyForest.MOD_ID, "textures/misc/cupid_kiss_overlay.png");

    // 淡入/淡出速度：每 tick 变化 0.03，约 1.2 秒完成全程变化（20 tick = 1秒）
    private static final float FADE_SPEED = 0.03f;

    // 当前实际透明度（0 ~ 1）
    private float currentAlpha = 0.0f;

    /**
     * 根据效果等级计算目标透明度（等级越高越明显）
     * 等级0 -> 约0.4，等级3 -> 约0.85
     */
    private float computeTargetAlpha(int amplifier) {
        float alpha = 0.4f + (amplifier * 0.15f);
        return Math.min(alpha, 0.9f);
    }

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;

        // 无玩家时，仍可能残留淡出动画，需要持续降低透明度
        float targetAlpha = 0.0f;
        if (player != null) {
            StatusEffectInstance effect = player.getStatusEffect(ModEffects.CUPID_KISS);
            if (effect != null) {
                targetAlpha = computeTargetAlpha(effect.getAmplifier());
            }
        }

        // 更新当前透明度（淡入/淡出）
        if (currentAlpha < targetAlpha) {
            currentAlpha = Math.min(targetAlpha, currentAlpha + FADE_SPEED);
        } else if (currentAlpha > targetAlpha) {
            currentAlpha = Math.max(targetAlpha, currentAlpha - FADE_SPEED);
        }

        // 完全透明则跳过绘制，提高性能
        if (currentAlpha <= 0.01f) {
            return;
        }

        // 绘制遮罩纹理
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        // 设置纹理颜色乘数，alpha 使用当前动态值
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, currentAlpha);

        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();
        drawContext.drawTexture(OVERLAY_TEXTURE, 0, 0, 0, 0,
                screenWidth, screenHeight, screenWidth, screenHeight);

        // 恢复默认颜色
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.disableBlend();
    }
}