package io.apihub.client.api;

import io.apihub.client.ApiClient;
import io.apihub.client.ApiException;
import io.apihub.client.api.VectoresSaldosApi;
import io.apihub.client.model.Domicilio;
import io.apihub.client.model.Persona;
import io.apihub.client.model.Response;
import io.apihub.client.model.Domicilio.EstadoEnum;
import io.apihub.interceptor.SignerInterceptor;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;


/**
 * API tests for VectoresSaldosApi
 */
public class VectoresSaldosApiTest {

    private final VectoresSaldosApi api = new VectoresSaldosApi();
    private ApiClient apiClient = new ApiClient();
    
    @Before()
    public void setUp() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new SignerInterceptor())
                .build();
        apiClient.setHttpClient(okHttpClient);
    }
    
    @Test
    public void VectoresSaldosTest() {
        String xApiKey = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
        String username = "XXXXXXXXXX";
        String password = "XXXXXXXXXX";
        Persona body = new Persona();
        body.setPrimerNombre("XXXXXXXXXX");
        body.segundoNombre(null);
        body.setApellidoPaterno("XXXXXXXXXX");
        body.setApellidoMaterno("XXXXXXXXXX");
        body.setFechaNacimiento("YYYY-MM-DD");
        body.setRfc("XXXXXXXXXX");
        body.setCurp("XXXXXXXXXXXXXXX");
        Domicilio dom = new Domicilio();
        dom.setDireccion("XXXXXXXXXX");
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
