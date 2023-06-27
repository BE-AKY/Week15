package pet.store.dao;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.store.entity.Customer;


public interface CustomerDao extends JpaRepository<Customer, Long> {

//	Optional<Customer> findByCustomerEmail(String customerEmail);

}
