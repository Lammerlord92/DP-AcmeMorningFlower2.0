package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Doughnut;


@Repository
public interface DoughnutRepository extends JpaRepository<Doughnut,Integer>{

	@Query("select d from Doughnut d where d.deleted is false")
	Collection<Doughnut> findNotDeleted();

	@Query("select count(d) from Doughnut d where d.deleted is false")
	Double notAvaliable();
}
