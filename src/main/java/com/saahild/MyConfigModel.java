package com.saahild;

import io.*;

@Modmenu(modId = WakatimeMod.MOD_ID)
@Config(name = "wakatimeconfig", wrapperName = "MyConfig")
public class MyConfigModel {

  @SectionHeader("Wakatime Configuration")
  public String apiKey = "keyhere";

  public String projectName = "projectname";

  @RestartRequired
  public String serverURL = "https://wakatime.com/api/v1";
}
