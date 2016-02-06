import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Seven on 31.01.2016.
 */
public class Test2 {

    public final static void main(String[] args) throws Exception {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            try {
                HttpGet httpget = new HttpGet("http://httpbin.org/get");

                System.out.println("Executing request " + httpget.getRequestLine());
                CloseableHttpResponse response = httpclient.execute(httpget);
                try {
                    System.out.println("----------------------------------------");
                    System.out.println(response.getStatusLine());

                    // Get hold of the response entity
                    HttpEntity entity = response.getEntity();

                    // If the response does not enclose an entity, there is no need
                    // to bother about connection release
                    if (entity != null) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));

                        try {

                            String t;
                            while((t = br.readLine()) != null) System.out.println(t);

                            // do something useful with the response
                        } catch (IOException ex) {
                            // In case of an IOException the connection will be released
                            // back to the connection manager automatically
                            throw ex;
                        } finally {
                            // Closing the input stream will trigger connection release
                            br.close();
                        }
                    }
                } finally {
                    response.close();
                }
            } finally {
                httpclient.close();
            }
        }

}
