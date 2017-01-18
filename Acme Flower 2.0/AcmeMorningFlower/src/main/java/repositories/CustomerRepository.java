package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import security.UserAccount;

import domain.Administrator;
import domain.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
	@Query("select c from Customer c where c.emailAddress=?1")
	Administrator findByEmail(String emailAddress);

	@Query("select c.emailAddress from FlowerOrder fo join fo.sender c ")
	Collection<String> numTotal();
	
}
