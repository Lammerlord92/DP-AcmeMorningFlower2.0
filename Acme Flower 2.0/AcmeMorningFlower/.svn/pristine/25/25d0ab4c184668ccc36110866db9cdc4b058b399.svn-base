package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Flower;


@Repository
public interface FlowerRepository extends JpaRepository<Flower,Integer>{
	@Query("select distinct f from Flower f join f.tag t where f.deleted=false and (f.vulgarName like ?1 or f.scientificName like ?1 or t.keywords like ?1)")
	Collection<Flower> findByKeyword(String key);
	
	@Query("select distinct f from Flower f where f.flowerOrders.size=0")
	Collection<Flower> unorderedFlowers();

	@Query("select distinct f from Flower f where f.flowerOrders.size/(select count(fo) from FlowerOrder fo)>0.025")
	Collection<Flower> bestSelledFlowers();

	@Query("select distinct f from Flower f where f.deleted=false")
	Collection<Flower> findAllUndeleted();

	@Query("select distinct f from Flower f join f.tag t where f.vulgarName like ?1 or f.scientificName like ?1 or t.keywords like ?1")
	Collection<Flower> findAllByKeyword(String string);
}
