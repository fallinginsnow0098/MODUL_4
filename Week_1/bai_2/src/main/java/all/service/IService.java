package all.service;

import all.model.Customer;

import java.util.List;

public interface IService {
    List<Customer> findAll();
    Customer findById(int id);
    void save(Customer customer);
    void update(int id, Customer customer);
    void remove(int id);
}
