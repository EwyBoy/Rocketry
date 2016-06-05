package com.ewyboy.rocketry.common.utility;

public class Reference {

    public static final class ModInfo {
        public static final String ModID = "rocketry";
        public static final String ModName = "Rocketry";
        static final String VersionMajor = "1";
        static final String VersionMinor = "0";
        static final String VersionPatch = "0";
        public static final String BuildVersion = VersionMajor + "." + VersionMinor + "." + VersionPatch;
        public static final String MinecraftVersion = "1.9.4";
    }

    public static final class Path {
        public static final String wailaPath = "com.ewyboy.rocketry.dependencies.Waila.onWailaCall";
        public static final String clientProxyPath = "com.ewyboy.rocketry.proxy.ClientProxy";
        public static final String commonProxyPath = "com.ewyboy.rocketry.proxy.CommonProxy";
    }

    public static final class Blocks {
        public static final String refinery = "refinery";
        public static final String compressor = "compressor";
        public static final String platingPress = "platingpress";
        public static final String engine = "engine";
        public static final String tank = "tank";
    }
}
