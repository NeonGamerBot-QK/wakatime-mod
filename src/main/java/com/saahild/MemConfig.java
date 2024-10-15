package com.saahild;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// TODO: (maybe) use like file config or something
// @see when ready to make file config = https://forums.minecraftforge.net/topic/64259-solved-config-files-189/
// this is being used cuz im not the greatest at java
public class MemConfig {

  private static String apiKey = "";
  private static String serverURL = "https://wakatime.com/api/v1";
  private static File configFile = new File(
    System.getProperty("user.home") + "/.wakatime.cfg"
  );

  public static void init() {
    if (!configFile.canRead()) {
      // we are COOKED
      WakatimeMod.LOGGER.error(
        "Cannot read config file! (either cant read file / does not exist) Exiting..."
      );
      System.exit(1);
    }
    if (!configFile.exists()) {
      // WakatimeMod.LOGGER.error("Does n", null);
      WakatimeMod.LOGGER.warn("Config file does not Exist");
    } else {
      // read cfg with scanner
      WakatimeMod.LOGGER.info("Reading cfg w/ scanner");
      try (Scanner reader = new Scanner(configFile)) {
        while (reader.hasNextLine()) {
          String data = reader.nextLine();
          WakatimeMod.LOGGER.info("Data: " + data);
          if (data.indexOf("api_key") != -1) {
            MemConfig.apiKey = data.split("=")[1].trim();
          }
        }
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public static String getApiKey() {
    return apiKey;
  }

  public static void setApiKey(String apiKey) {
    MemConfig.apiKey = apiKey;
    // set key in file asw
    try {
      FileWriter myWriter = new FileWriter(
        System.getProperty("user.home") + "/.wakatime.cfg"
      );
      myWriter.write("[settings]\n");
      myWriter.write("api_key = " + apiKey);
      myWriter.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static String getServerURL() {
    return serverURL;
  }
}
