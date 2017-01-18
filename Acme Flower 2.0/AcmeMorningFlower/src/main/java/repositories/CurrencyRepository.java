package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import security.UserAccount;

import domain.Administrator;
import domain.Currency;
import domain.Customer;


@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Integer>{
	@Query("select c.name,c.changeValue*?1 from Currency c")
	Collection<Object[]> convert(double money);
	
	@Query("select c from Currency c where c.name=?1")
	Currency findByName(String currency);
	
}
