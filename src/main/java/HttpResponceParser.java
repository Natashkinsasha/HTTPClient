import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpResponceParser {


    public HttpResponce parse(String responce) throws ResponceException {
        String[] r = responce.split("\r\n\r\n");
        String sLineAndHeader = r[0];
        String body = r[1];
        body = body.substring(8);
        String startLineString = null;
        Headers headers = new Headers();
        double version = 0;
        int statusCode = 0;
        String reasonPhrase = "";
        String header;
        StringBuilder builderHeader = null;
        Pattern p = Pattern.compile("HTTP.*");
        Matcher m = p.matcher(sLineAndHeader);
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

        p = Pattern.compile("\\s[^\\d]+");
        m = p.matcher(startLineString);
        if (m.find()) {
            reasonPhrase = m.group();
        } else {
            throw new ResponceException();
        }
        p = Pattern.compile(".+:.+");
        m = p.matcher(sLineAndHeader);
        while (m.find()) {
            header = m.group();
            StringTokenizer t = new StringTokenizer(header, ": ");
            headers.addHeader(t.nextToken(), t.nextToken());
        }
        return new HttpResponce(new ResponceStartingLine(version, statusCode, reasonPhrase), headers, new MessageBody(body));
    }

    public static void main(String argv[]) throws Exception {
        HttpResponceParser parser = new HttpResponceParser();
        parser.parse("HTTP\\1.1 400 ddfgf egh \n" +
                "Start: fghg\n" +
                "Begin: df ,df fd g6 \n" +
                "\n" +
                "dsfdgf \n" +
                "gh \n" +
                "yrtjuyytuy  yt");
    }

}
