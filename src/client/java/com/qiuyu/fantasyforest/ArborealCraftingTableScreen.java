// ArborealCraftingTableScreen.java
package com.qiuyu.fantasyforest;

import com.mojang.blaze3d.systems.RenderSystem;
import com.qiuyu.fantasyforest.screen.ArborealCraftingTableScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ArborealCraftingTableScreen extends HandledScreen<ArborealCraftingTableScreenHandler> {

    // GUI纹理的路径，你需要准备一个png图片放在assets/fantasyforest/textures/gui/目录下
    private static final Identifier TEXTURE = new Identifier(FantasyForest.MOD_ID, "textures/gui/arboreal_crafting_table_gui.png");

    public ArborealCraftingTableScreen(ArborealCraftingTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        // 设置GUI的背景宽度和高度
        this.backgroundWidth = 176;
        this.backgroundHeight = 166;
        // 玩家物品栏标题的Y坐标，调整到GUI背景外
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        // 获取纹理的实际尺寸（需要先绑定纹理）
        RenderSystem.setShaderTexture(0, TEXTURE);

        context.drawTexture(TEXTURE,
                x, y,
                0, 0,
                this.backgroundWidth, this.backgroundHeight,
                176, 166
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // 绘制背景和背景暗化效果:cite[8]
        this.renderBackground(context);
        // 调用父类渲染方法，这会绘制槽位和物品
        super.render(context, mouseX, mouseY, delta);
        // 绘制鼠标悬停时的提示信息
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        // 可选：在这里添加按钮等GUI组件
        // 将标题居中显示:cite[1]
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
        // 调整标题的Y坐标
        this.titleY = 6;
    }
}