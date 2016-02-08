/**
 * Created by Seven on 01.02.2016.
 */
public class ResponceStartingLine {
    private double version;
    private int statusCode;
    private String reasonPhrase;

    public ResponceStartingLine(double version, int statusCode, String reasonPhrase) {
        this.version = version;
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
    }

    @Override
    public String toString() {
        return "HTTP/" + version + " " + statusCode + " " + reasonPhrase;
    }

    public double getVersion() {
        return version;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
