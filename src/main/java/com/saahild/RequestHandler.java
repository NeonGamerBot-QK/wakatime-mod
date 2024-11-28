package com.saahild;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.mojang.authlib.minecraft.client.MinecraftClient;

public class RequestHandler {
  private static HttpRequest.BodyPublisher buildJsonBody(String serverName, String projectName) {
    String clientName = MinecraftClient.class.getSimpleName();
    WakatimeMod.LOGGER.info("Client name: " + clientName);
    return HttpRequest.BodyPublishers.ofString(
        "{\"time\":" +
            System.currentTimeMillis() / 1000 +
            ",\"entity\":\"" +
            serverName +
            "\", \"type\":\"app\", \"plugin\":\"minecraft/1.21 wakatime-mod/1.0.0\", \"project\":\"" +
            projectName +
            "\", \"branch\": \"1.21\", \"language\": \"java\",\"editor\":\"" + clientName + "\"}");
  }

  public static void sendHeartbeat(
      String apiKey,
      String projectName,
      String serverURL,
      String serverName) throws URISyntaxException {
    var client = HttpClient.newHttpClient();

    var request = HttpRequest.newBuilder(
        new URI(serverURL + "/users/current/heartbeats"))
        .header("Content-Type", "application/json")
        .header("Authorization", "Basic " + apiKey.trim())
        .POST(buildJsonBody(serverName, projectName))
        .build();

    client
        .sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenApply(responseBody -> {
          WakatimeMod.LOGGER.info("Status: " + responseBody.statusCode());
          WakatimeMod.LOGGER.info(responseBody.body().toString());
          return 0;
        });
    // log req result output

    WakatimeMod.LOGGER.info("Heartbeat sent!");
  }
}
