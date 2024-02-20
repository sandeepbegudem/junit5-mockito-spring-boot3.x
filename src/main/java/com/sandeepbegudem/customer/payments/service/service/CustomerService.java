package com.sandeepbegudem.customer.payments.service.service;import com.sandeepbegudem.customer.payments.service.dto.CustomerPaymentsRequest;import com.sandeepbegudem.customer.payments.service.dto.CustomerResponse;import com.sandeepbegudem.customer.payments.service.entity.Customer;import com.sandeepbegudem.customer.payments.service.mapper.CustomerMapper;import com.sandeepbegudem.customer.payments.service.repository.CustomerRepository;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.List;import java.util.stream.Collectors;@Servicepublic class CustomerService {    @Autowired    private CustomerRepository customerRepository;    @Autowired    private CustomerMapper mapper;    // retrieve all customers//    public List<CustomerPaymentsRequest> getAllCustomers(){//        List<CustomerPaymentsRequest> customerPaymentsRequests = customerRepository//                .findAll()//                .parallelStream()//                .map(m -> mapper.entityToDto(m))//                .collect(Collectors.toList());//        return customerPaymentsRequests;//    }    // save a customer    public CustomerResponse saveCustomer(CustomerPaymentsRequest customerPaymentsRequest) {        Customer customerList = mapper.dtoToEntity(customerPaymentsRequest);        Customer savedCustomer = customerRepository.save(customerList);        CustomerPaymentsRequest req = mapper.entityToDto(savedCustomer);        CustomerResponse customerResponse = mapper.dtoToResponse(req);        return customerResponse;    }    public CustomerResponse customerById(int id){        Customer customer = customerRepository.findCustomerById(id);        CustomerPaymentsRequest dto = mapper.entityToDto(customer);        return mapper.dtoToResponse(dto);    }    // update customer    public Customer updateCustomer(CustomerPaymentsRequest customerPaymentsRequest, Integer id) {       // Customer customer = mapper.dtoToEntity(customerPaymentsRequest);        Customer retrievedCustomer = customerRepository.findById(id).orElse(null);        return customerRepository.save(retrievedCustomer);    }    // delete customer    public void deleteCustomerById(int id) {        Customer customer = customerRepository.findById(id).orElse(null);        if ((customer != null ? customer.getId() : null) == null) {            throw new RuntimeException("resource: not found" + id);        }        else            customerRepository.deleteCustomerById(id);    }//    public List<CustomerPaymentsRequest> getAllCustomersUsingCustomJPQL() {////       return customerRepository.findAllCustomers()//               .stream()//               .map(customer -> mapper.entityToDto(customer))//               .collect(Collectors.toList());//    }    public List<CustomerResponse> getAllCustomers() {        return customerRepository.findAllCustomers()                .stream()                .map(customer -> new CustomerResponse(                        customer.getId(),                        customer.getFirstname(),                        customer.getLastname(),                        customer.getAge(),                        customer.getAddress(),                        customer.getCity(),                        customer.getState(),                        customer.getPayments()                ))                .collect(Collectors.toList());    }}