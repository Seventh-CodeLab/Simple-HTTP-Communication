package no.codelab.http;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpServer{

    private final ServerSocket serverSocket;
    private final int port;
    private String fileLocation;

    //Server Constructor: Creates the socket and gives a listening port
    public HttpServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.port = serverSocket.getLocalPort();
    }

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = new HttpServer(8080);
        httpServer.setFileLocation("src/main/resources");
        httpServer.start();

    }

    public void start() throws IOException {
        System.out.println("Server Listening On Port: " + serverSocket.getLocalPort() + "\r\n"); //Prints out the listening port

        // Infinite loop of process ensures the server is able to keep accepting requests without having to be restarted
        while (true) {
            Socket socket = serverSocket.accept(); //Waits for a client to connect

            OutputStream outStream = socket.getOutputStream();

            StringBuilder request = new StringBuilder();
            String line;
            while (!(line = readLine(socket)).isEmpty()) {
                request.append(line);
                request.append("\r\n");
                System.out.println("Client Request: " + line);
            }
            System.out.println(" === CLIENT-REQUEST: Reading Complete\r\n");

            //Parse the incoming request
            Map<String, String> parsedRequest = new HashMap<>();

            String requestTarget = request.toString().split("\r\n")[0].split(" ")[1]; // /echo?requestTarget
            if(requestTarget.length() > 1){
                if(!(requestTarget.substring(0,5).equals("/echo"))){


                    //Assume user is requesting a file in the case of no echo request
                    try{
                        File file = new File(fileLocation + requestTarget);
                        outStream.write(("" +
                                "HTTP/1.1 200 OK\r\n" +
                                "Content-Length: " + file.length() + "\r\n" +
                                "Connection: close\r\n" +
                                "\r\n").getBytes());
                        new FileInputStream(file).transferTo(outStream);
                        outStream.flush();
                        outStream.close();
                    } catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                } else {
                    int questionPos = requestTarget.indexOf('?');
                    if (questionPos != -1) {
                        String[] requestParams = requestTarget.substring(questionPos + 1).split("&");
                        for (String parameter : requestParams) {
                            int equalsPos = parameter.indexOf('=');
                            if (equalsPos != -1) {
                                String parameterHeader = parameter.substring(0, equalsPos).trim();
                                String parameterValue = parameter.substring(equalsPos + 1).trim();
                                parsedRequest.put(parameterHeader, parameterValue);
                            }
                        }
                    }

                    //Respond to the client
                    int requestedStatusCode = Integer.parseInt(parsedRequest.getOrDefault("status", "200"));
                    outStream.write(("HTTP/1.1 " + requestedStatusCode + " " + HttpStatusCodes.statusCodeList.getOrDefault(requestedStatusCode, "OK") + "\r\n").getBytes());
                    outStream.write(("Content-Length: " + parsedRequest.getOrDefault("body", "xxxxxx").length() + "\r\n").getBytes());
                    outStream.write(("Content-Type: " + "text/html" + "\r\n").getBytes());

                    //This iterates through parsed map to respond accordingly
                    Iterator requestIterator =  parsedRequest.entrySet().iterator();
                    while (requestIterator.hasNext()){
                        Map.Entry pair = (Map.Entry)requestIterator.next();
                        String headerKey = pair.getKey().toString();
                        //Do Not respond with body or status, as they are their own lines and not headers
                        if(headerKey.equals("body") || headerKey.equals("status")){
                            continue;
                        }
                        outStream.write((headerKey + ": " + parsedRequest.getOrDefault(headerKey,null) + "\r\n").getBytes());
                        requestIterator.remove();
                    }

                    outStream.write(("\r\n").getBytes());
                    outStream.write((URLDecoder.decode(parsedRequest.getOrDefault("body", "Empty"), StandardCharsets.UTF_8) +"\r\n").getBytes());
                    outStream.close();
                    outStream.flush();
                }

            }
        }
    }


    private String readLine(Socket socket) throws IOException {
        int c;
        StringBuilder line = new StringBuilder();
        while((c = socket.getInputStream().read()) != -1){
            if(c == '\r'){
                c = socket.getInputStream().read();
                if(c != '\n'){
                    System.err.println("Unexpeced character: " + ((char) c));
                }
                return line.toString();
            }
            line.append((char)c);
        }
        return line.toString();
    }

    public int getPort(){
        return serverSocket.getLocalPort();
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }
}

