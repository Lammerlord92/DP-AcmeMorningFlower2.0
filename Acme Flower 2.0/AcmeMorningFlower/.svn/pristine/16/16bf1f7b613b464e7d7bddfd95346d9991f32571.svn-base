package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import repositories.TagRepository;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Flower;
import domain.Tag;

@Transactional
@Service
public class TagService {

	//	Managed repository -----------------------------------------	
	@Autowired
	private TagRepository tagRepository;
	//	Supporting services ----------------------------------------
	//	Constructor ------------------------------------------------
	//	Simple CRUD methods ----------------------------------------
	public Tag create(){
		isAdmin();
		Tag result;
		result=new Tag();
		
		Collection<Flower> flowers=new ArrayList<Flower>();
		
		result.setCancelled(false);
		result.setFlowers(flowers);
		
		return result;
	}
	
	public Collection<Tag> findAll(){
		Collection<Tag> result = tagRepository.findAll();
		return result;
	}
	
	public Tag findOne(int tagId){
		Tag result = tagRepository.findOne(tagId);
		return result;
	}
	
	public void save(Tag tag){
		isAdmin();
		tagRepository.saveAndFlush(tag);
	}
	
	public void delete(int tagId){
		Tag tag=findOne(tagId);
		tag.setCancelled(true);
		tagRepository.save(tag);
	}
	
	//	Other business methods -------------------------------------
	public Collection<Tag> findActives() {
		Collection<Tag> result;
		result=tagRepository.findActives();
		return result;
	}
	
	public void unDelete(int tagId){
		Tag tag=findOne(tagId);
		tag.setCancelled(false);
		tagRepository.save(tag);
	}
//	public Tag reconstruct(TagForm TagForm) {
//		Assert.isTrue(TagForm.getPassword().equals(TagForm.getConfirmPassword()));
//		Assert.isTrue(TagForm.isAccepConditions());
//		Tag result;
//		result=create();
//		UserAccount userAccount=result.getUserAccount();
//		userAccount.setUsername(TagForm.getUserName());
//		
//		Md5PasswordEncoder encoder;		
//		encoder= new Md5PasswordEncoder();
//		String password=encoder.encodePassword(TagForm.getPassword(), null);
//		userAccount.setPassword(password);
//		
//		result.setUserAccount(userAccount);
//		
//		result.setName(TagForm.getName());
//		result.setSurname(TagForm.getSurname());
//		result.setEmailAddress(TagForm.getEmailAddress());
//		result.setContactPhone(TagForm.getContactPhone());
//		result.setUrl(TagForm.getUrl());
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
