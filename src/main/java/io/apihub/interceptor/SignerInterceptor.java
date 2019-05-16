package io.apihub.interceptor;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import io.apihub.client.model.Error;
import io.apihub.client.model.Errores;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okio.Buffer;


public class SignerInterceptor implements Interceptor{

	private Logger logger = LoggerFactory.getLogger(Signer.class.getName());
	private Signer signer;
	private Request newRequest;
	private Response newResponse;
	
	@Override
	public Response intercept(Chain chain) throws IOException {
		this.signer = Signer.getInstance();
		Request originalRequest = chain.request();
		this.newRequest = this.procesarFirmado(originalRequest);
		Response response = chain.proceed(newRequest);
		this.newResponse = this.procesarVerificado(response, newRequest);
		
		return newResponse;
	}
	
	private static String bodyToString(final Request request){

	    try {
	        final Request copy = request.newBuilder().build();
	        final Buffer buffer = new Buffer();
	        copy.body().writeTo(buffer);
	        return buffer.readUtf8();
	    } catch (final IOException e) {
	        return null;
	    }
	}
	
	private Request procesarFirmado(Request originalRequest) {
		logger.debug("Generando firmado...");
		String payload = bodyToString(originalRequest);
		String signature = this.signer.signPayload(payload);
		logger.debug("Firma: "+signature);
		Request newRequest = originalRequest.newBuilder()
	            .header("x-signature", signature)
	            .method(originalRequest.method(), originalRequest.body())
	            .build();
		
		return newRequest;
	}
	
	private Response procesarVerificado(Response response, Request newRequest) {
		logger.debug("Verificando firmado...");
		Response outResponse = null;
		MediaType contentType = null;
		String signature = null;
		String payload = null;
		byte[] content = null;
		
		try {
			List<String> values = response.headers().values("x-signature");
			signature = values.get(0);
			contentType = response.body().contentType();
			content = response.body().bytes();
			payload = ResponseBody.create(contentType , content).string();
		}
		catch (IndexOutOfBoundsException e) {
			logger.debug("No se pudo recuperar la firma");
		}
		catch (IOException e) {
			logger.debug("Error inesperado");
			e.printStackTrace();
		}
		if( signature == null) {
			outResponse = buildResponseBody(500, "No se recibió la firma", response, contentType, null);
		}
		else {
			if(this.signer.verifyPayload(payload, signature)) {
				logger.debug("Verificación satisfactoria");
				outResponse = buildResponseBody(response.code(), null, response, contentType, content);
			}
			else {
				logger.debug("No se pudo verificar la firma");
				outResponse = buildResponseBody(403, "No se pudo verificar la firma", response, contentType, null);
			}
		}
		
		return outResponse;
	}
	
	private String generateError(String code, String message) {
		logger.debug("Generando error");
		Errores errs = new Errores();
		Error err = new Error();
		err.setCode(code);
		err.setMessage(message);
		errs.addErrorsItem(err);
		
		Gson gson = new Gson();
		
		return gson.toJson(errs);
		
	}
	
	private Response buildResponseBody(Integer statusCode, String message, Response response, MediaType contentType, byte[] content) {
		ResponseBody responseBody = null;
		String responseMessage = HttpStatus.getStatusText(statusCode);
		if(message != null)
			responseBody = ResponseBody.create(contentType, generateError(String.valueOf(statusCode), message));
		else
			responseBody = ResponseBody.create(contentType, content);
		Response newResponse = new Builder()
				.code(statusCode)
				.protocol(Protocol.HTTP_1_1)
				.message(responseMessage)
				.request(this.newRequest)
				.body(responseBody)
				.headers(response.headers())
				.build();
		
		return newResponse;
	}
}
