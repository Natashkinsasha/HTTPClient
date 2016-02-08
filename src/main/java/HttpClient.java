import org.apache.commons.io.LineIterator;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpClient {
    HttpResponce execute(HttpRequest request) throws IOException, ResponceException {
        String host = (request.getStartingLine().getUri().getHost() == null) ? request.getHeaders().getHeader("Host") : request.getStartingLine().getUri().getHost();
        Socket s = new Socket(InetAddress.getByName(host), 80);
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        pw.print(request.toString());
        pw.flush();
        InputStream stream = s.getInputStream();
        LineIterator sourse = new LineIterator(new BufferedReader(new InputStreamReader(stream)));
        StringBuilder responce = new StringBuilder();
        while (sourse.hasNext()) {
            String line = sourse.nextLine();
            responce.append(new StringBuilder(line)).append(System.getProperty("line.separator"));
        }
        HttpResponceParser httpResponcePrser = new HttpResponceParser();
        HttpResponce httpResponce = httpResponcePrser.parse(responce.toString());
        stream.close();
        pw.close();
        s.close();
        return httpResponce;
    }

    public static void main(String argv[]) throws IOException, ResponceException, URISyntaxException {
        RequestStartingLine sl = new RequestStartingLine(RequestStartingLine.Method.GET, new URI("http://www.site.ru/news.html?login=Petya%20Vasechkin&password=qq"));
        Headers h = new Headers();
        h.addHeader("Host", "www.site.ru");
        h.addHeader("Connection", "close");
        HttpRequest request = new HttpRequest(sl, h);
        HttpClient hc = new HttpClient();
        HttpResponce response = hc.execute(request);
        System.out.println(request.toString());
        System.out.println(response.toString());
    }
}

