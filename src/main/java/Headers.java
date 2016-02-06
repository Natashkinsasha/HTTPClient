import java.util.HashMap;
import java.util.Map;

/**
 * Created by Seven on 01.02.2016.
 */
public class Headers {
    Map<String,String> headers;

    public Headers() {
        this.headers = new HashMap<String, String>();
    }
    public void addHeader(String parameter, String value){
        headers.put(parameter,value);
    }
    public String toString(){
        StringBuffer headerStringBuffer = new StringBuffer();
        for (String parameter: headers.keySet()) {
            String value = headers.get(parameter);
            headerStringBuffer.append(parameter).append(": ").append(value).append("\n");
        }
        return new String(headerStringBuffer);
    }

    public String getHeader(String parametr) {
        return headers.get(parametr);
    }
}
