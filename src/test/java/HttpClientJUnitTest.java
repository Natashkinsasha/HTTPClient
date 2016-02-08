import org.junit.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpClientJUnitTest extends Assert {
    @org.junit.Test
    public void executeGET() {
        RequestStartingLine sl = null;
        try {
            sl = new RequestStartingLine(RequestStartingLine.Method.GET, new URI("http://www.site.ru/news.html?login=Petya%20Vasechkin&password=qq"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Headers h = new Headers();
        h.addHeader("Host", "www.site.ru");
        h.addHeader("Connection", "close");
        HttpRequest request = new HttpRequest(sl, h);
        HttpClient hc = new HttpClient();
        HttpResponce response =null;
        try {
           response = hc.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResponceException e) {
            e.printStackTrace();
        }
        assertEquals("code must be 503", 503, response.getStartingLine().getStatusCode());
        assertEquals("version must be 1.1", 1,1, response.getStartingLine().getVersion());

    }
}
