package com.saahild;
// TODO: (maybe) use like file config or something
// @see when ready to make file config = https://forums.minecraftforge.net/topic/64259-solved-config-files-189/
// this is being used cuz im not the greatest at java
public class MemConfig {
    private static String apiKey = "";
    public static String getApiKey() {
        return apiKey;
    }
    public static void setApiKey(String apiKey) {
        MemConfig.apiKey = apiKey;
    }
}
