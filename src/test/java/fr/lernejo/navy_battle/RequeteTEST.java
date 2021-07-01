package fr.lernejo.navy_battle;

import com.sun.net.httpserver.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLSession;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

class RequeteTEST {

    @Test
    void GoodStartrequest(){
        MyServer s = null;
        try {
            s = MyServerTest.StartTestServer("9879");
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertEquals(0,1,"Number of exception on server");
        }
        HttpExchange exchange = new HttpsExchange() {
            @Override
            public SSLSession getSSLSession() {
                return null;
            }

            @Override
            public Headers getRequestHeaders() {
                return null;
            }

            @Override
            public Headers getResponseHeaders() {
                return null;
            }

            @Override
            public URI getRequestURI() {
                return null;
            }

            @Override
            public String getRequestMethod() {
                return null;
            }

            @Override
            public HttpContext getHttpContext() {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getRequestBody() {
                return new ByteArrayInputStream(Charset.forName("UTF-8").encode("{\"id\": \"2aca7611-0ae4-49f3-bf63-75bef4769028\",\"url\": \"http://localhost:9876\",\"message\": \"May the best code win\"}").array());
            }

            @Override
            public OutputStream getResponseBody() {
                return new ByteArrayOutputStream(512);
            }

            @Override
            public void sendResponseHeaders(int i, long l) throws IOException {

            }

            @Override
            public InetSocketAddress getRemoteAddress() {
                return null;
            }

            @Override
            public int getResponseCode() {
                return 0;
            }

            @Override
            public InetSocketAddress getLocalAddress() {
                return null;
            }

            @Override
            public String getProtocol() {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void setStreams(InputStream inputStream, OutputStream outputStream) {

            }

            @Override
            public HttpPrincipal getPrincipal() {
                return null;
            }
        };
        RequestHandler handler = new RequestHandler(s);
        try {
            handler.StartHandler(exchange,true);
        } catch (IOException | InterruptedException e) {
            Assertions.assertEquals(0,1,"Number of exception on request");
        }
        MyServerTest.DestroyTestServer(s);
    }

    @Test
    void BadStartrequest(){
        MyServer s = null;
        try {
            s = MyServerTest.StartTestServer("9880");
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertEquals(0,1,"Number of exception on server");
        }
        HttpExchange exchange = new HttpsExchange() {
            @Override
            public SSLSession getSSLSession() {
                return null;
            }

            @Override
            public Headers getRequestHeaders() {
                return null;
            }

            @Override
            public Headers getResponseHeaders() {
                return null;
            }

            @Override
            public URI getRequestURI() {
                return null;
            }

            @Override
            public String getRequestMethod() {
                return null;
            }

            @Override
            public HttpContext getHttpContext() {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getRequestBody() {
                return new ByteArrayInputStream(Charset.forName("UTF-8").encode("{\"url\": \"http://localhost:9876\",\"message\": \"May the best code win\"}").array());
            }

            @Override
            public OutputStream getResponseBody() {
                return new ByteArrayOutputStream(512);
            }

            @Override
            public void sendResponseHeaders(int i, long l) throws IOException {

            }

            @Override
            public InetSocketAddress getRemoteAddress() {
                return null;
            }

            @Override
            public int getResponseCode() {
                return 0;
            }

            @Override
            public InetSocketAddress getLocalAddress() {
                return null;
            }

            @Override
            public String getProtocol() {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void setStreams(InputStream inputStream, OutputStream outputStream) {

            }

            @Override
            public HttpPrincipal getPrincipal() {
                return null;
            }
        };
        RequestHandler handler = new RequestHandler(s);
        try {
            handler.StartHandler(exchange,true);
        } catch (IOException | InterruptedException e) {
            Assertions.assertEquals(0,1,"Number of exception on request");
        }
        MyServerTest.DestroyTestServer(s);
    }

    @Test
    void NoMoreShipFire() throws IOException {
        MyServer s = null;
        try {
            s = MyServerTest.StartTestServer("9899");
            s.game.ingame[0] = false;
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertEquals(0,1,"Number of exception on server");
        }

        HttpExchange exchange = new HttpsExchange() {
            @Override
            public SSLSession getSSLSession() {
                return null;
            }

            @Override
            public Headers getRequestHeaders() {
                return null;
            }

            @Override
            public Headers getResponseHeaders() {
                return null;
            }

            @Override
            public URI getRequestURI() {
                try {
                    return new URI("http://localhost:9876?cell=A1");
                } catch (URISyntaxException e) {
                    return null;
                }
            }

            @Override
            public String getRequestMethod() {
                return null;
            }

            @Override
            public HttpContext getHttpContext() {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getRequestBody() {
                return new ByteArrayInputStream(Charset.forName("UTF-8").encode("{\"consequence\": \"sunk\",\"shipLeft\": false}").array());
            }

            @Override
            public OutputStream getResponseBody() {
                return new ByteArrayOutputStream(512);
            }

            @Override
            public void sendResponseHeaders(int i, long l) throws IOException {

            }

            @Override
            public InetSocketAddress getRemoteAddress() {
                return null;
            }

            @Override
            public int getResponseCode() {
                return 0;
            }

            @Override
            public InetSocketAddress getLocalAddress() {
                return null;
            }

            @Override
            public String getProtocol() {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void setStreams(InputStream inputStream, OutputStream outputStream) {

            }

            @Override
            public HttpPrincipal getPrincipal() {
                return null;
            }
        };
        RequestHandler handler = new RequestHandler(s);
        try {
            handler.FireHandler(exchange,true);
        } catch (IOException e) {
            Assertions.assertEquals(0,1,"Number of exception on request");
        }
        s.game.ingame[0] = true;
        exchange = new HttpsExchange() {
            @Override
            public SSLSession getSSLSession() {
                return null;
            }

            @Override
            public Headers getRequestHeaders() {
                return null;
            }

            @Override
            public Headers getResponseHeaders() {
                return null;
            }

            @Override
            public URI getRequestURI() {
                try {
                    return new URI("http://localhost:9876?cell=A1");
                } catch (URISyntaxException e) {
                    return null;
                }
            }

            @Override
            public String getRequestMethod() {
                return null;
            }

            @Override
            public HttpContext getHttpContext() {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getRequestBody() {
                return new ByteArrayInputStream(Charset.forName("UTF-8").encode("{\"consequence\": \"sunk\",\"shipLeft\": false}").array());
            }

            @Override
            public OutputStream getResponseBody() {
                return new ByteArrayOutputStream(512);
            }

            @Override
            public void sendResponseHeaders(int i, long l) throws IOException {

            }

            @Override
            public InetSocketAddress getRemoteAddress() {
                return null;
            }

            @Override
            public int getResponseCode() {
                return 0;
            }

            @Override
            public InetSocketAddress getLocalAddress() {
                return null;
            }

            @Override
            public String getProtocol() {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void setStreams(InputStream inputStream, OutputStream outputStream) {

            }

            @Override
            public HttpPrincipal getPrincipal() {
                return null;
            }
        };
        handler = new RequestHandler(s);
        try {
            handler.FireHandler(exchange,true);
        } catch (IOException e) {
            Assertions.assertEquals(0,1,"Number of exception on request");
        }

        final int[] coord = s.game.yourboard.get(4).get(0);
        final int[] coord2 = s.game.yourboard.get(4).get(1);
        exchange = new HttpsExchange() {
            @Override
            public SSLSession getSSLSession() {
                return null;
            }

            @Override
            public Headers getRequestHeaders() {
                return null;
            }

            @Override
            public Headers getResponseHeaders() {
                return null;
            }

            @Override
            public URI getRequestURI() {
                try {
                    return new URI(String.format("http://localhost:9876?cell=%s%s", (char) (coord[1] + 'A'), coord[0] + 1));
                } catch (URISyntaxException e) {
                    return null;
                }
            }

            @Override
            public String getRequestMethod() {
                return null;
            }

            @Override
            public HttpContext getHttpContext() {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getRequestBody() {
                return new ByteArrayInputStream(Charset.forName("UTF-8").encode("{\"consequence\": \"sunk\",\"shipLeft\": false}").array());
            }

            @Override
            public OutputStream getResponseBody() {
                return new ByteArrayOutputStream(512);
            }

            @Override
            public void sendResponseHeaders(int i, long l) throws IOException {

            }

            @Override
            public InetSocketAddress getRemoteAddress() {
                return null;
            }

            @Override
            public int getResponseCode() {
                return 0;
            }

            @Override
            public InetSocketAddress getLocalAddress() {
                return null;
            }

            @Override
            public String getProtocol() {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void setStreams(InputStream inputStream, OutputStream outputStream) {

            }

            @Override
            public HttpPrincipal getPrincipal() {
                return null;
            }
        };
        handler = new RequestHandler(s);
        try {
            handler.FireHandler(exchange,true);
        } catch (IOException e) {
            Assertions.assertEquals(0,1,"Number of exception on request");
        }

        exchange = new HttpsExchange() {
            @Override
            public SSLSession getSSLSession() {
                return null;
            }

            @Override
            public Headers getRequestHeaders() {
                return null;
            }

            @Override
            public Headers getResponseHeaders() {
                return null;
            }

            @Override
            public URI getRequestURI() {
                try {
                    return new URI(String.format("http://localhost:9876?cell=%s%s", (char) (coord2[1] + 'A'), coord2[0] + 1));
                } catch (URISyntaxException e) {
                    return null;
                }
            }

            @Override
            public String getRequestMethod() {
                return null;
            }

            @Override
            public HttpContext getHttpContext() {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getRequestBody() {
                return new ByteArrayInputStream(Charset.forName("UTF-8").encode("{\"consequence\": \"sunk\",\"shipLeft\": false}").array());
            }

            @Override
            public OutputStream getResponseBody() {
                return new ByteArrayOutputStream(512);
            }

            @Override
            public void sendResponseHeaders(int i, long l) throws IOException {

            }

            @Override
            public InetSocketAddress getRemoteAddress() {
                return null;
            }

            @Override
            public int getResponseCode() {
                return 0;
            }

            @Override
            public InetSocketAddress getLocalAddress() {
                return null;
            }

            @Override
            public String getProtocol() {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void setStreams(InputStream inputStream, OutputStream outputStream) {

            }

            @Override
            public HttpPrincipal getPrincipal() {
                return null;
            }
        };
        handler = new RequestHandler(s);
        try {
            handler.FireHandler(exchange,true);
        } catch (IOException e) {
            Assertions.assertEquals(0,1,"Number of exception on request");
        }

        exchange = new HttpsExchange() {
            @Override
            public SSLSession getSSLSession() {
                return null;
            }

            @Override
            public Headers getRequestHeaders() {
                return null;
            }

            @Override
            public Headers getResponseHeaders() {
                return null;
            }

            @Override
            public URI getRequestURI() {
                try {
                    return new URI("http://localhost:9876?cell=A90");
                } catch (URISyntaxException e) {
                    return null;
                }
            }

            @Override
            public String getRequestMethod() {
                return null;
            }

            @Override
            public HttpContext getHttpContext() {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getRequestBody() {
                return new ByteArrayInputStream(Charset.forName("UTF-8").encode("{\"consequence\": \"sunk\",\"shipLeft\": false}").array());
            }

            @Override
            public OutputStream getResponseBody() {
                return new ByteArrayOutputStream(512);
            }

            @Override
            public void sendResponseHeaders(int i, long l) throws IOException {

            }

            @Override
            public InetSocketAddress getRemoteAddress() {
                return null;
            }

            @Override
            public int getResponseCode() {
                return 0;
            }

            @Override
            public InetSocketAddress getLocalAddress() {
                return null;
            }

            @Override
            public String getProtocol() {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void setStreams(InputStream inputStream, OutputStream outputStream) {

            }

            @Override
            public HttpPrincipal getPrincipal() {
                return null;
            }
        };
        handler = new RequestHandler(s);
        try {
            handler.FireHandler(exchange,true);
        } catch (IOException e) {
            Assertions.assertEquals(0,1,"Number of exception on request");
        }
        MyServerTest.DestroyTestServer(s);
    }

}
