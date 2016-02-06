
public class HttpResponce {
    private ResponceStartingLine startingLine;
    private Headers headers;
    private MessageBody messageBody;
    private String plainResponce;

    public HttpResponce(ResponceStartingLine startingLine, Headers headers, MessageBody messageBody) {
        this.startingLine = startingLine;
        this.headers = headers;
        this.messageBody = messageBody;
    }

    public HttpResponce(ResponceStartingLine startingLine, Headers headers) {
        this(startingLine, headers, null);
    }

    public HttpResponce(ResponceStartingLine startingLine) {
        this(startingLine, null, null);
    }
    @Override
    public String toString() {
        String startingLineString = ((startingLine == null) ? "" : startingLine.toString()) + "\r\n";
        String headersString = ((headers == null) ? "" : headers.toString()) + "\r\n";
        String messageBodyString = ((messageBody == null) ? "" : messageBody.toString()) + "\r\n";
        return startingLineString + headersString + messageBodyString;
    }
}
