import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpResponceParser {


    public HttpResponce parse(String responc) throws ResponceException {
        String startLineString = null;
        Headers headers = new Headers();
        double version = 0;
        int statusCode = 0;
        String reasonPhrase = "";
        String header;
        StringBuilder builderHeader = null;
        Pattern p = Pattern.compile("HTTP.*");
        Matcher m = p.matcher(responc);
        if (m.find()) {
            startLineString = m.group();
        } else {
            throw new ResponceException();
        }
        p = Pattern.compile("\\d+.\\d+");
        m = p.matcher(startLineString);
        if (m.find()) {
            version = Double.parseDouble(m.group());
        } else {
            throw new ResponceException();
        }
        p = Pattern.compile("\\d{3}");
        m = p.matcher(startLineString);
        if (m.find()) {
            statusCode = Integer.parseInt(m.group());
        } else {
            throw new ResponceException();
        }
        p = Pattern.compile(".+:.+");
        m = p.matcher(responc);
        while (m.find()) {
            header = m.group();
            StringTokenizer t = new StringTokenizer(header, ": ");
            headers.addHeader(t.nextToken(), t.nextToken());
        }
        return new HttpResponce(new ResponceStartingLine(version, statusCode, reasonPhrase), headers);
    }

}
