package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.DoughnutRepository;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Doughnut;
import domain.Flower;

@Transactional
@Service
public class DoughnutService {

	// Managed repository -----------------------------------------
	@Autowired
	private DoughnutRepository doughnutRepository;
	// Supporting services ----------------------------------------
	

	// Constructor ------------------------------------------------
	// Simple CRUD methods ----------------------------------------
	public Doughnut create() {
		Doughnut result = new Doughnut();
		result.setDeleted(false);
		return result;
	}

	
	public Collection<Doughnut> findAll(){
		isAdmin();
		Collection<Doughnut> result = doughnutRepository.findAll();
		return result;
	}

	public Doughnut findOne(int doughnutId) {
		Doughnut result = doughnutRepository.findOne(doughnutId);
		return result;
	}

	public void save(Doughnut doughnut) {
		isAdmin();
		doughnutRepository.saveAndFlush(doughnut);
	}

	
	 public void delete(int doughnutId){
		isAdmin();
		Doughnut doughnut=findOne(doughnutId);
		doughnut.setDeleted(true);
		doughnutRepository.saveAndFlush(doughnut);
	 }

	// Other business methods -------------------------------------
	// List the active doughnuts
	public Collection<Doughnut> findActives(){
		Collection<Doughnut> result = doughnutRepository.findNotDeleted();
		return result;
	}
	 public void undelete(int doughnutId){
		isAdmin();
		Doughnut doughnut=findOne(doughnutId);
		doughnut.setDeleted(false);
		doughnutRepository.saveAndFlush(doughnut);
	 }
	//The ratio of doughnuts that are not available.
	public Double ratioNotActives(){
		isAdmin();
		Collection<Doughnut> notActives = doughnutRepository.findNotDeleted();
		Collection<Doughnut> actives = doughnutRepository.findAll();
		Double result=(1.0*notActives.size())/actives.size();
		return result;
	}
	public void isAdmin() {
		UserAccount account = LoginService.getPrincipal();
		Collection<Authority> authorities = account.getAuthorities();
		Boolean res = false;
		for (Authority a : authorities) {
			if (a.getAuthority().equals("ADMIN"))
				res = true;
		}
		Assert.isTrue(res);
	}
}
