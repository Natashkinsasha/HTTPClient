import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Seven on 02.02.2016.
 */
public class HttpResponceParser{

    InputStream stream;

    public HttpResponceParser(InputStream stream) {
        this.stream = stream;
    }

    public HttpResponce parse() throws IOException, ResponceException {
        BufferedReader sourse = new BufferedReader(new InputStreamReader(this.stream));
        String startLine = sourse.readLine();
        ResponceStartingLine responceStartingLine = parserStartLine(startLine);
        String line;
        Headers headers = new Headers();
        while ((line = sourse.readLine()) != null) {
            Matcher matcher = Pattern.compile("([^:]+):(.+)\\n").matcher(line);
            while (matcher.find()) {
                matcher = Pattern.compile("^\\S+").matcher(line);
                String parametr="";
                if (matcher.find()) {
                    parametr = matcher.group();
                } else {
                    throw new ResponceException();
                }
                matcher = Pattern.compile("\\S+$").matcher(line);
                String value ="";
                if (matcher.find()) {
                    value = matcher.group();
                } else {
                    throw new ResponceException();
                }
                headers.addHeader(parametr,value);
            }
            break;
        }

        while ((line = sourse.readLine())!=null){

            System.out.println(line);
        }

        stream.close();
        return new HttpResponce(responceStartingLine);
    }

    private ResponceStartingLine parserStartLine(String startLine) throws ResponceException{
        int statusCode=0;
        Pattern pattrn = Pattern.compile("\\d{3}");
        Matcher matcher = pattrn.matcher(startLine);
        if (matcher.find()) {
            statusCode = Integer.parseInt(matcher.group());
        } else {
            throw new ResponceException();
        }
        double version=0;
        pattrn = Pattern.compile("\\d.\\d");
        matcher = pattrn.matcher(startLine);
        if (matcher.find()) {
            version = Double.parseDouble(matcher.group());
        } else {
            throw new ResponceException();
        }
        return new ResponceStartingLine(version, statusCode, "");
    }


}
