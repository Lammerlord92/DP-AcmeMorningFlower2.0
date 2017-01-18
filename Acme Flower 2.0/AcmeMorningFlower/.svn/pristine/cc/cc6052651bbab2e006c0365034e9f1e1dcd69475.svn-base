package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import repositories.FlowerRepository;
import domain.Flower;
import domain.FlowerOrder;

@Transactional
@Service
public class FlowerService {

	//	Managed repository -----------------------------------------	
	@Autowired
	private FlowerRepository flowerRepository;
	//	Supporting services ----------------------------------------
	@Autowired
	private AdministratorService administratorService;
	//	Constructor ------------------------------------------------
	//	Simple CRUD methods ----------------------------------------
	//Create by an admin
	public Flower create(){
		administratorService.isAdmin();
		
		Flower result;
		result=new Flower();
		Collection<FlowerOrder> flowerOrders=new ArrayList<FlowerOrder>();
		result.setFlowerOrders(flowerOrders);
		
		return result;
	}
	
	//All catalogue
	public Collection<Flower> findAll(){
		Collection<Flower> result = flowerRepository.findAll();
		return result;
	}
//	
	public Flower findOne(int flowerId){
		Flower result = flowerRepository.findOne(flowerId);
		return result;
	}
	
	//Saving as an admin	
	public void save(Flower flower){
		administratorService.isAdmin();
		flowerRepository.saveAndFlush(flower);
	}
	//Deleting as an admin
	public void delete(int flowerId){
		administratorService.isAdmin();
		Flower flower=findOne(flowerId);
		flower.setDeleted(true);
		save(flower);
	}
	
	//	Other business methods -------------------------------------
	//Search by keywors
	public Collection<Flower> findByKeyword(String key){
		Collection<Flower> result = flowerRepository.findByKeyword("%"+key+"%");
		return result;
	}
	//Search by keywors(Admin)
	public Collection<Flower> findAllByKeyword(String key) {
		Collection<Flower> result = flowerRepository.findAllByKeyword("%"+key+"%");
		return result;
	}
	//Search all undeleted
	public Collection<Flower> findAllUndeleted(){
		Collection<Flower> result = flowerRepository.findAllUndeleted();
		return result;
	}
	//Dashboard:Flowers that never have been ordered
	public Collection<Flower> notOrderedFlowers(){
		administratorService.isAdmin();
		Collection<Flower> result = flowerRepository.unorderedFlowers();
		return result;
	}
	//Dashboard:Best-Seller flowers (flower orders>2'5%)
	public Collection<Flower> bestSelledFlowers(){
		administratorService.isAdmin();
		Collection<Flower> result = flowerRepository.bestSelledFlowers();
		return result;
	}
	//Undeleting as an admin
	public void undelete(int flowerId){
		administratorService.isAdmin();
		Flower flower=findOne(flowerId);
		flower.setDeleted(false);
		save(flower);
	}
//	public Flower reconstruct(FlowerForm FlowerForm) {
//		Assert.isTrue(FlowerForm.getPassword().equals(FlowerForm.getConfirmPassword()));
//		Assert.isTrue(FlowerForm.isAccepConditions());
//		Flower result;
//		result=create();
//		UserAccount userAccount=result.getUserAccount();
//		userAccount.setUsername(FlowerForm.getUserName());
//		
//		Md5PasswordEncoder encoder;		
//		encoder= new Md5PasswordEncoder();
//		String password=encoder.encodePassword(FlowerForm.getPassword(), null);
//		userAccount.setPassword(password);
//		
//		result.setUserAccount(userAccount);
//		
//		result.setName(FlowerForm.getName());
//		result.setSurname(FlowerForm.getSurname());
//		result.setEmailAddress(FlowerForm.getEmailAddress());
//		result.setContactPhone(FlowerForm.getContactPhone());
//		result.setUrl(FlowerForm.getUrl());
//		
//		return result;
//	}

	
}
