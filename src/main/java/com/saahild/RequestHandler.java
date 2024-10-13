package com.saahild;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestHandler {

  public static void sendHeartbeat(
    String apiKey,
    String projectName,
    String serverURL
  ) throws URISyntaxException {
    var client = HttpClient.newHttpClient();

    var request = HttpRequest.newBuilder(
      new URI(serverURL + "/users/current/heartbeats")
    )
      .header("Content-Type", "application/json")
      .header("Authorization", "Basic " + apiKey)
      .build();

    client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
  }
}
