package com.qiuyu.fantasyforest;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class ClientKeyChecker implements KeyChecker {
    @Override
    public boolean isShiftKeyDown() {
        MinecraftClient client = MinecraftClient.getInstance();
        // 获取当前游戏窗口的句柄
        long windowHandle = client.getWindow().getHandle();

        // 直接查询GLFW中左右Shift键的实时状态
        boolean leftShiftPressed = GLFW.glfwGetKey(windowHandle, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS;
        boolean rightShiftPressed = GLFW.glfwGetKey(windowHandle, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;

        // 任意一个Shift键按下即视为按下
        return leftShiftPressed || rightShiftPressed;
    }
}
