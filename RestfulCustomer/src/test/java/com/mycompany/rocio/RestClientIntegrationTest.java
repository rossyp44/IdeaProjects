package com.mycompany.rocio;

import com.mycompany.rocio.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by rociomac on 2/07/2016.
 * Studying Spring Boot
 */

public class RestClientIntegrationTest extends Assert{

    private RestTemplate template = new RestTemplate();

    private static final String BASE_URI = "http://localhost:8080/customers";

    @Test
    public void test_get_all_customers_getForObject(){
        List<Customer> list = template.getForObject(BASE_URI, List.class);
        assertNotNull(list);
    }

    @Test
    public void test_get_all_customers_getForEntity(){
        ResponseEntity<List> response = template.getForEntity(BASE_URI, List.class);
        assertEquals(response.getStatusCode().value(), 200);
    }

    @Test(expected = HttpClientErrorException.class)
    public void test_delete_operation_failed_exception(){
        ResponseEntity<String> response = template.exchange(BASE_URI + "/100", HttpMethod.DELETE, null, String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test(expected = HttpClientErrorException.class)
    public void test_delete_operation_success(){
        template.delete(BASE_URI + "/1");
        ResponseEntity<Customer> response = template.getForEntity(BASE_URI + "/1", Customer.class);
        assertEquals(response.getStatusCode().value(), 200);
    }

    @Test
    public void test_add(){
        Customer customer = new Customer(3L, "Rossy Pena");
        HttpEntity<Customer> newCustomer = new HttpEntity<Customer>(customer);
        ResponseEntity<String> response  = template.postForEntity(BASE_URI, newCustomer, String.class);
        assertEquals(response.getStatusCode().value(), 200);
    }


}
