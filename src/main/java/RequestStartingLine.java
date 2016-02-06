import java.net.URI;

/**
 * Created by Seven on 01.02.2016.
 */
public class RequestStartingLine {
    enum Method {
        GET,
        POST,
        HEAD,
        OPTIONS
    }

    private Method method;
    private URI uri;
    private double version;

    public RequestStartingLine(Method method, URI uri, double version) {
        this.method = method;
        this.uri = uri;
        this.version = version;
    }

    public RequestStartingLine(Method method, URI uri) {
        this.method = method;
        this.uri = uri;
        this.version = 1.1;
    }

    public RequestStartingLine(URI uri) {
        this.method = Method.GET;
        this.uri = uri;
        this.version = 1.1;
    }

    public String getMethod() {
        switch (method) {
            case GET:
                return "GET";
            case HEAD:
                return "HEAD";
            case POST:
                return "POST";
            case OPTIONS:
                return "OPTIONS";
        }
        return null;
    }

    public URI getUri() {
        return uri;
    }

    public double getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return getMethod() + " " + uri.toString() + " HTTP/" + version;
    }


}
