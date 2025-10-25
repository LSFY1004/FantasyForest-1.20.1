package com.qiuyu.fantasyforest;

public class KeyHelper {
    private static KeyChecker checker = new DefaultShiftKeyChecker();

    public static void register(KeyChecker checkerImpl) {
        checker = checkerImpl;
    }

    public static boolean isShiftKeyDown() {
        return checker.isShiftKeyDown(); // 调用接口实现，避免递归
    }

    // 默认实现（服务端使用）
    private static class DefaultShiftKeyChecker implements KeyChecker {
        @Override
        public boolean isShiftKeyDown() {
            return false; // 服务端总是返回 false
        }
    }
}
