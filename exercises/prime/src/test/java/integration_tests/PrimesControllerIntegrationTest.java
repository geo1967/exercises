package integration_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.AfterClass;
import org.junit.Test;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import static java.net.HttpURLConnection.HTTP_OK;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.junit.BeforeClass;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import webservice.Converter;

public class PrimesControllerIntegrationTest {

    private static  ConfigurableApplicationContext appContext;

    private final String baseUri = "http://localhost:8080";
    private Client client;
  
    @BeforeClass
    public static void startWebService()
    {
        appContext = SpringApplication.run(webservice.PrimesController.class, new String[]{});
    }
    @AfterClass
    public static void stopWebService()
    {
        appContext.close();
    }
    
    
    @Before
    public void onSetup() throws Exception {

        client = Client.create();
    }

    @Test
    public void testGetPrimes_SieveOfSundaram() throws IOException {
    	String algoName= "SieveOfSundaram";
        int max_limit = 10;
    	URI uri = UriBuilder.fromPath(baseUri).path("prime").path(algoName).path(Integer.toString(max_limit)).build();
	WebResource webResource = client.resource(uri);
	ClientResponse response = webResource.accept("application/octet-stream").get(ClientResponse.class);		
	assertEquals(HTTP_OK, response.getStatus());       
       
        String actual = response.getEntity(String.class);
        Converter cv = new Converter();
        int[] actualPrimes = cv.deSerialize(actual); 
        assertArrayEquals(new int[]{2,3,5,7}, actualPrimes);
    }

    
     @Test
    public void testGetPrimes_TrialDivision() throws IOException {
    	String algoName= "TrialDivision";
        int max_limit = 10;
    	URI uri = UriBuilder.fromPath(baseUri).path("prime").path(algoName).path(Integer.toString(max_limit)).build();
	WebResource webResource = client.resource(uri);
	ClientResponse response = webResource.accept("application/octet-stream").get(ClientResponse.class);		
	assertEquals(HTTP_OK, response.getStatus());       
       
        String actual = response.getEntity(String.class);
        Converter cv = new Converter();
        int[] actualPrimes = cv.deSerialize(actual); 
        assertArrayEquals(new int[]{2,3,5,7}, actualPrimes);
    }
}
