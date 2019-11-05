package io.VectoresSaldos.client.api;

import io.VectoresSaldos.client.ApiClient;
import io.VectoresSaldos.client.ApiException;
import io.VectoresSaldos.client.api.VectoresSaldosApi;
import io.VectoresSaldos.client.model.Domicilio;
import io.VectoresSaldos.client.model.Persona;
import io.VectoresSaldos.client.model.Response;
import io.VectoresSaldos.client.model.Domicilio.EstadoEnum;
import io.VectoresSaldos.interceptor.SignerInterceptor;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class VectoresSaldosApiTest {
    private final VectoresSaldosApi api = new VectoresSaldosApi();
    private ApiClient apiClient = new ApiClient();
    
    @Before()
    public void setUp() {        
		this.apiClient = api.getApiClient();
		this.apiClient.setBasePath("the_url");
    	OkHttpClient insecureClient = ApiClient.getClientNoSSLVerification();
    	OkHttpClient okHttpClient = insecureClient.newBuilder()
    			.readTimeout(60, TimeUnit.SECONDS)
    			.addInterceptor(new SignerInterceptor())
    			.build();
    	apiClient.setHttpClient(okHttpClient);
    }
    
    @Test
    public void VectoresSaldosTest() {
        String xApiKey = "your_api_key";
        String username = "your_username";
        String password = "your_password";
        Persona body = new Persona();
        body.setPrimerNombre("XXXXXXXX");
        body.segundoNombre("XXXXXXXX");
        body.setApellidoPaterno("XXXXXXXX");
        body.setApellidoMaterno("XXXXXXXX");
        body.setFechaNacimiento("YYYY-MM-DD");
        body.setRfc("XXXXXXXXXX");
        body.setCurp("XXXXXXXXXXXXXXX");
        Domicilio dom = new Domicilio();
        dom.setDireccion("XXXXXXXXXX XXX X");
        dom.setColonia("XXXXXXXXXX");
        dom.setCiudad(null);
        dom.codigoPostal("XXXXX");
        dom.setMunicipio("XXXXXXXXXX");
        dom.setEstado(EstadoEnum.CDMX);
        body.setDomicilio(dom);
        
        Response response;
      try {
        response = api.getVectoresSaldos(xApiKey, username, password, body);
        System.out.println(response.toString());
      } catch (ApiException e) {
        e.printStackTrace();
        System.out.println(e.getResponseBody());
      }
    }
    
}
