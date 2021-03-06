
public class HttpRequest {

    private RequestStartingLine startingLine;
    private Headers headers;
    private MessageBody messageBody;

    public HttpRequest(RequestStartingLine startingLine, Headers headers, MessageBody messageBody) {
        this.startingLine = startingLine;
        this.headers = headers;
        this.messageBody = messageBody;
    }

    public HttpRequest(RequestStartingLine startingLine, Headers headers) {
        this(startingLine, headers, new MessageBody(""));
    }

    public HttpRequest(RequestStartingLine startingLine) {
        this(startingLine, new Headers(), new MessageBody(""));
    }
    @Override
    public String toString() {
        String startingLineString = ((startingLine == null) ? "" : startingLine.toString()) + System.getProperty("line.separator");
        String headersString = ((headers == null) ? System.getProperty("line.separator") : headers.toString()) + System.getProperty("line.separator");
        String messageBodyString = ((messageBody == null) ? System.getProperty("line.separator") : messageBody.toString()+System.getProperty("line.separator"));
        return (new StringBuilder(startingLineString).append(new StringBuilder(headersString)).append(new StringBuilder(messageBodyString))).toString();
    }

    public RequestStartingLine getStartingLine() {
        return startingLine;
    }

    public Headers getHeaders() {
        return headers;
    }

    public MessageBody getMessageBody() {
        return messageBody;
    }
}
