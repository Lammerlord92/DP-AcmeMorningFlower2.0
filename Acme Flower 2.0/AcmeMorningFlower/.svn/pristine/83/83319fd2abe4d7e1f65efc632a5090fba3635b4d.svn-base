package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.FlowerOrder;


@Repository
public interface FlowerOrderRepository extends JpaRepository<FlowerOrder,Integer>{
	@Query("select fo from FlowerOrder fo where fo.ticker is ?1")
	FlowerOrder findByTicker(String ticker);
	
	@Query("select count(fo) from FlowerOrder fo")
	int numNumberOrders();
	
	@Query("select fo.sender.emailAddress,count(fo) from FlowerOrder fo group by fo.sender.emailAddress")
	Collection<Object[]> numOrdersEmail();
	
	@Query("select fo.flower.vulgarName,count(fo) from FlowerOrder fo group by fo.flower")
	Collection<Object[]> numOrdersFlowers();
	
}
