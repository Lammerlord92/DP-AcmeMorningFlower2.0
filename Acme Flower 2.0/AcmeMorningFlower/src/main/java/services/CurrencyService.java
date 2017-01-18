package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import repositories.CurrencyRepository;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Currency;

@Transactional
@Service
public class CurrencyService {

	//	Managed repository -----------------------------------------	
	@Autowired
	private CurrencyRepository currencyRepository;
	//	Supporting services ----------------------------------------
	//	Constructor ------------------------------------------------
	//	Simple CRUD methods ----------------------------------------
	public Currency create(){
		isAdmin();
		Currency result;
		result=new Currency();		
		return result;
	}
	
	public Collection<Currency> findAll(){
		Collection<Currency> result = currencyRepository.findAll();
		return result;
	}
	
	public Currency findOne(int currencyId){
		Currency result = currencyRepository.findOne(currencyId);
		return result;
	}
	
	public void save(Currency currency){
		isAdmin();
		Currency forbid=currencyRepository.findByName("EUR");
		Assert.isTrue(!currency.equals(forbid));
		currencyRepository.saveAndFlush(currency);
	}
	
	public void delete(int currencyId){
		isAdmin();
		Currency currency=findOne(currencyId);
		currencyRepository.delete(currency);
	}
	
	//	Other business methods -------------------------------------
	public Collection<Object[]> convert(double money) {
		Collection<Object[]> result;
		result=currencyRepository.convert(money);
		return result;
	}
	
	public void isAdmin() {
		UserAccount account=LoginService.getPrincipal();
		Collection<Authority> authorities= account.getAuthorities();
		Boolean res=false;
		for(Authority a:authorities){
			if(a.getAuthority().equals("ADMIN")) res=true;
		}
		Assert.isTrue(res);
	}

	public Currency findByName(String currency) {
		
		return currencyRepository.findByName(currency);
	}


}
