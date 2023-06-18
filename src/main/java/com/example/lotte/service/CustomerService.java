package com.example.lotte.service;

import com.example.lotte.DTO.CustomerDTO;
import com.example.lotte.DTO.RankDTO;
import com.example.lotte.model.Customer;
import com.example.lotte.model.Rank;
import com.example.lotte.repository.BillRepository;
import com.example.lotte.repository.CustomerRepository;
import com.example.lotte.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RankRepository rankRepository;

    @Autowired
    BillRepository billRepository;

    public Customer addCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setGender(customerDTO.getGender());
        customer.setDob(customerDTO.getDob());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        Rank rank = rankRepository.findById(Long.valueOf(4)).get();
        customer.setRank(rank);
        customer.setTotalPoint(0);

        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    public ResponseEntity<Customer> getCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> updateCustomer( Long id, CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setName(customerDTO.getName());
            customer.setGender(customerDTO.getGender());
            customer.setDob(customerDTO.getDob());
            customer.setAddress(customerDTO.getAddress());
            customer.setPhoneNumber(customerDTO.getPhoneNumber());

            customerRepository.save(customer);
            return ResponseEntity.ok("Customer updated successfully.");
        } else {
            return ResponseEntity.ok("Customer id not found");
        }
    }

    public ResponseEntity<String> deleteCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if(billRepository.findAllByCustomerId(customer.getId()).size() > 0) {
                return ResponseEntity.ok("Customer  has  a bill ");
            }
            else {
                customerRepository.delete(customer);
                return ResponseEntity.ok("Customer deleted successfully.");
            }
        } else {
            return ResponseEntity.ok("Customer id not found");
        }
    }

    public List<CustomerDTO> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();

        List<CustomerDTO> customerDTOs = customers.stream()
                .map(customer -> {
                    RankDTO rankDTO = new RankDTO();
                    if (customer.getRank() != null ) {
                        Optional<Rank> rank = rankRepository.findById(customer.getRank().getId());
                        if(rank.isPresent()) {
                            rankDTO = new RankDTO(rank.get().getId(), rank.get().getName());
                        }
                    }
                    CustomerDTO dto = new CustomerDTO();
                    dto.setId(customer.getId());
                    dto.setName(customer.getName());
                    dto.setGender(customer.getGender());
                    dto.setDob(customer.getDob());
                    dto.setAddress(customer.getAddress());
                    dto.setPhoneNumber(customer.getPhoneNumber());
                    dto.setTotalPoint(customer.getTotalPoint());
                    dto.setRank(rankDTO);
                    return dto;
                })
                .collect(Collectors.toList());
        return customerDTOs;
    }
}
