import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class Test {
    public static void main(String argv[]) {
        try {
            Socket s = new Socket(InetAddress.getByName("httpbin.org"), 80);
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            pw.print("GET / HTTP/1.1\r\n" + "Host: httpbin.org\r\n\r\n");
            pw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String t;
            while ((t = br.readLine()) != null) System.out.println(t);
            br.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
