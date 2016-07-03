package com.mycompany.rocio.controller;

import com.mycompany.rocio.exception.CustomerNotFoundException;
import com.mycompany.rocio.model.Customer;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rociomac on 2/07/2016.
 * Studying Spring Boot
 */

@Controller
@RequestMapping("/customers")
@Api(value = "/customers", description = "RESFul Service which provides CRUD operations for Customer Resources")

public class CustomerController {

    private static List<Customer> list = new ArrayList<Customer>();

    static {
        list.add(new Customer(1L, "Clark Kent"));
        list.add(new Customer(2L, "Bruce Wayne"));
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "/", notes = "Return ALL Customers", response = List.class, responseContainer = "List")
    public List<Customer> getAllCustomers(){
        return list;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Customer getCustomer(@PathVariable("id") Long id){
        for (Customer c : list){
            if(c.getId().equals(id)){
                return c;
            }
        }
        throw new CustomerNotFoundException();
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addCustomer(@RequestBody Customer customer){
        list.add(customer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Customer updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){
        for (Customer c : list){
            if(c.getId().equals(id)){
                c.setName(customer.getName());
                return customer;
            }
        }
        throw new CustomerNotFoundException();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteCustomer(@PathVariable("id") Long id){
        Customer rCust = null;
        for (Customer c : list){
            if(c.getId().equals(id)){
                rCust = c;
            }
        }
        if(rCust == null){
            throw new CustomerNotFoundException();
        } else {
            list.remove(rCust);

        }
    }

}

