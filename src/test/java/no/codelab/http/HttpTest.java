package no.codelab.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

class HttpTest {
    private HttpServer server;
    private int actualPort;

    @BeforeEach
    public void initialize() throws IOException {
        this.server = startServer();
        this.actualPort = server.getPort();
    }

    @Test
    void shouldFindStatusCode200() throws IOException {
        HttpClient client = new HttpClient("localhost", actualPort,"/echo?status=200");
        client.executeRequest();
        assertEquals(200, client.getStatusCode());
    }

    @Test
    void shouldFindFailure402() throws IOException {
        HttpClient client = new HttpClient("localhost", actualPort,"/echo?status=402");
        client.executeRequest();
        assertEquals(402, client.getStatusCode());
    }

    @Test
    void shouldReturnResponseHeader() throws IOException {
        HttpClient client = new HttpClient("localhost", actualPort, "/echo?status=302&Location=https://www.google.com");
        client.executeRequest();
        assertEquals(302, client.getStatusCode());
        assertEquals("https://www.google.com", client.getResponseHeader("Location"));
    }

    @Test
    void shouldReturnMessageBody() throws IOException {
        HttpClient client = new HttpClient("localhost", actualPort, "/echo?status=200&body=Hello%20World");
        client.executeRequest();
        assertEquals(200, client.getStatusCode());
        assertEquals("Hello World",client.getBody());
    }

    @Test
    void shouldReturnFileFromDisk() throws IOException {
        server.setFileLocation("src/main/resources");
        HttpClient client = new HttpClient("localhost", actualPort, "/sample.txt");
        client.executeRequest();
        assertEquals("I am sampletext",client.getBody()); //TODO: DO THINGS HERE
    }
    private HttpServer startServer() throws IOException {
        HttpServer httpServer = new HttpServer(0);
        new Thread(() -> {
            try {
                httpServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        return httpServer;
    }
}