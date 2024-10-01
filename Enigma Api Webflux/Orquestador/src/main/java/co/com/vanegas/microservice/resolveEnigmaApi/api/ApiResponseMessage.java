package co.com.vanegas.microservice.resolveEnigmaApi.api;

public class ApiResponseMessage {
    public static final int ERROR = 1;
    public static final int WARNING = 2;
    public static final int INFO = 3;
    public static final int OK = 4;
    public static final int TOO_BUSY = 5;

    private int code;
    private String type;
    private String message;

    public ApiResponseMessage() {}

    public ApiResponseMessage(int code, String message) {
        this.code = code;
        switch(code) {
            case ERROR:
                this.type = "error";
                break;
            case WARNING:
                this.type = "warning";
                break;
            case INFO:
                this.type = "info";
                break;
            case OK:
                this.type = "ok";
                break;
            case TOO_BUSY:
                this.type = "too busy";
                break;
            default:
                this.type = "unknown";
                break;
        }
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
