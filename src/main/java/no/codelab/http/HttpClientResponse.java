package no.codelab.http;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/*

  This class pases the response the client recieves from the server.
  - It handles separation of response header, headers and their values in a map, and the body

*/

public class HttpClientResponse {
  private String response ;
  private int statusCode;
  private Map<String, String> responseHeaders = new HashMap<>();
  private String responseBody;

  public HttpClientResponse(Socket socket) throws IOException {

    //The response is re-built into it's full setup in order to be parsed
    StringBuilder response = new StringBuilder();

    //Reads the message body
    int c;
    while ((c = socket.getInputStream().read()) != -1){
      response.append((char) c);
    }

    System.out.println(response.toString());

    System.out.println( "=== SERVER-RESPONSE: Reading Complete\r\n");
    System.out.println("=== CLIENT - Parsing Server Response: \r\n" + response);


    this.response = response.toString();
    String[] responseLines = this.response.split("\r\n");

    //Select out status code from first line
    statusCode = Integer.parseInt(responseLines[0].split(" ")[1]);

    //Put all other lines into HashMap
    for (int i = 1; i < responseLines.length; i++) {
      String line = responseLines[i];

      int colonPos = line.indexOf(':');
      if(colonPos != -1){
        String requestLineHeader = line.substring(0,colonPos).trim();
        String requestLineContent = line.substring(colonPos+1).trim();

        responseHeaders.put(requestLineHeader,requestLineContent);
      }

      //This throws the response body into a variable
      if(i == responseLines.length-1){
        responseBody = responseLines[i];
      }
    }
  }

  public String getResponse() {
    return response;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getResponseHeader(String key) {
    return responseHeaders.getOrDefault(key,"Undefined");
  }

  public String getBody() {
    return responseBody;
  }
}

