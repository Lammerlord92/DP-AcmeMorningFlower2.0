package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import repositories.AdditionalCostRepository;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.AdditionalCost;

@Transactional
@Service
public class AdditionalCostService {

	//	Managed repository -----------------------------------------	
	@Autowired
	private AdditionalCostRepository additionalCostRepository;
	//	Supporting services ----------------------------------------
	//	Constructor ------------------------------------------------
	//	Simple CRUD methods ----------------------------------------
//	public Administrator create(){
//		isAdmin(LoginService.getPrincipal());
//		Administrator result;
//		result=new Administrator();
//		
//		UserAccount userAccount=new UserAccount();
//		List<Authority> authorities=new ArrayList<Authority>();
//		Authority a=new Authority();
//		a.setAuthority(Authority.ADMIN);
//		authorities.add(a);
//		userAccount.setAuthorities(authorities);
//		
//		result.setUserAccount(userAccount);
//		
//		return result;
//	}
//	
//	public Collection<Administrator> findAll(){
//		Collection<Administrator> result = administratorRepository.findAll();
//		return result;
//	}
//	
//	public Administrator findOne(int administratorId){
//		Administrator result = administratorRepository.findOne(administratorId);
//		return result;
//	}
//	
	public void save(AdditionalCost additionalCost){
		isAdmin();
		additionalCostRepository.saveAndFlush(additionalCost);
	}
//	
//	public void delete(Administrator administrator){
//		administratorRepository.delete(administrator);
//	}
	
	//	Other business methods -------------------------------------
	public AdditionalCost findAdditionalCost(){
		AdditionalCost result=additionalCostRepository.findAll().get(0);
		return result;
	}

//	public Administrator reconstruct(AdministratorForm administratorForm) {
//		Assert.isTrue(administratorForm.getPassword().equals(administratorForm.getConfirmPassword()));
//		Assert.isTrue(administratorForm.isAccepConditions());
//		Administrator result;
//		result=create();
//		UserAccount userAccount=result.getUserAccount();
//		userAccount.setUsername(administratorForm.getUserName());
//		
//		Md5PasswordEncoder encoder;		
//		encoder= new Md5PasswordEncoder();
//		String password=encoder.encodePassword(administratorForm.getPassword(), null);
//		userAccount.setPassword(password);
//		
//		result.setUserAccount(userAccount);
//		
//		result.setName(administratorForm.getName());
//		result.setSurname(administratorForm.getSurname());
//		result.setEmailAddress(administratorForm.getEmailAddress());
//		result.setContactPhone(administratorForm.getContactPhone());
//		result.setUrl(administratorForm.getUrl());
//		
//		return result;
//	}
	
	
	public void isAdmin() {
		UserAccount account=LoginService.getPrincipal();
		Collection<Authority> authorities= account.getAuthorities();
		Boolean res=false;
		for(Authority a:authorities){
			if(a.getAuthority().equals("ADMIN")) res=true;
		}
		Assert.isTrue(res);
	}
}
