
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
        this(startingLine, headers, new MessageBody(""));
    }

    public HttpResponce(ResponceStartingLine startingLine) {
        this(startingLine, new Headers(), new MessageBody(""));
    }


    @Override
    public String toString() {
        String startingLineString = ((startingLine == null) ? "" : startingLine.toString()) + System.getProperty("line.separator");
        String headersString = ((headers == null) ? "" : headers.toString()) + System.getProperty("line.separator");
        String messageBodyString = ((messageBody == null) ? "" : messageBody.toString()) + System.getProperty("line.separator");
        return startingLineString + headersString + messageBodyString;
    }

    public ResponceStartingLine getStartingLine() {
        return startingLine;
    }

    public Headers getHeaders() {
        return headers;
    }

    public MessageBody getMessageBody() {
        return messageBody;
    }

    public String getPlainResponce() {
        return plainResponce;
    }
}
