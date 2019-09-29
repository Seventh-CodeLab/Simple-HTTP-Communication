package no.codelab.http;

import java.io.IOException;
import java.net.Socket;

public class HttpClient {
  private final String host;
  private final int port;
  private final String requestTarget;
  private HttpClientResponse response;

  public HttpClient(String host, int port, String requestTarget) {
    this.host = host;
    this.port = port;
    this.requestTarget = requestTarget;
  }

  // Returns the response received from the Server after a request
  public HttpClientResponse executeRequest() throws IOException {

    //Establish connection with server on port
    Socket socket = new Socket(host, port);

    //Send a request to the server
    socket.getOutputStream().write(("GET " + requestTarget + " HTTP/1.1\r\n").getBytes());
    socket.getOutputStream().write(("Host: " + host + "\r\n").getBytes());
    socket.getOutputStream().write(("Connection: close\r\n").getBytes());
    socket.getOutputStream().write("\r\n".getBytes());
    socket.getOutputStream().flush();

    //Sets the server response and returns it for later use.
    HttpClientResponse httpClientResponse = new HttpClientResponse(socket);
    this.response = httpClientResponse;
    return httpClientResponse;
  }

  public int getStatusCode() {
    return response.getStatusCode();
  }

  public String getResponseHeader(String header) {
    return response.getResponseHeader(header);
  }

  public String getBody() {
    return response.getBody();
  }
}

