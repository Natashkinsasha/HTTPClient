

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;

public class HttpClient {
    HttpResponce execute(HttpRequest request) throws IOException, ResponceException {
        String host = (request.getStartingLine().getUri().getHost() == null) ? request.getHeaders().getHeader("Host") : request.getStartingLine().getUri().getHost();
        Socket s = new Socket(InetAddress.getByName(host), 80);
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        System.out.print(request.toString());
        pw.print(request.toString());
        pw.flush();
        return new HttpResponceParser(s.getInputStream()).parse();
    }
    public static void main(String argv[])throws Exception{
        RequestStartingLine sl = new RequestStartingLine(RequestStartingLine.Method.GET,new URI("/"));
        Headers h = new Headers();
        h.addHeader("Host","httpbin.org");
        HttpRequest request = new HttpRequest(sl,h);
        HttpClient hc = new HttpClient();
        HttpResponce response = hc.execute(request);
        System.out.println(response.toString());
    }
}

