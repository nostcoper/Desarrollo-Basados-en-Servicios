package co.com.vanegas.microservice.resolveEnigmaApi.api;

public class ApiException extends Exception{
	private static final long serialVersionUID = 1L;
	public ApiException (int code, String msg) {
        super(msg);
    }
}
