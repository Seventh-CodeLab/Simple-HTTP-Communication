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

    /*
    //The response is re-built into it's full setup in order to be parsed in the HttpClientResponse class
    StringBuilder response = new StringBuilder();
    String responseLine;

    //Recieve a response from server
    while(!(responseLine = readLine(socket)).isEmpty()){
      System.out.println("Server Response: " + responseLine);
      response.append(responseLine);
      response.append("\r\n");
    }
    System.out.println( "=== SERVER-RESPONSE: Reading Complete\r\n");
     */
        //Sets the server response and returns it for later use.
        HttpClientResponse httpClientResponse = new HttpClientResponse(socket);
        this.response = httpClientResponse;
        return httpClientResponse;
    }
  /*
  //Reads individual lines of the input stream and finds end of stream appropriately
  public String readLine(Socket socket) throws IOException {
    int c;
    StringBuilder responseLine = new StringBuilder();
    while((c = socket.getInputStream().read()) != -1){
      if(c == '\r'){
        c = socket.getInputStream().read();
        if(c != '\n'){
          System.err.println("Unexpected character: " + ((char) c));
        }
        return responseLine.toString();
      }
      responseLine.append((char) c);
    }
    return responseLine.toString();
  }*/

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

