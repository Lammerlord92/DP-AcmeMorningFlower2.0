package services;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;


import repositories.AdditionalCostRepository;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.AbstractTest;
import domain.AdditionalCost;
import domain.Administrator;
import domain.CreditCard;
import domain.Tag;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class TagServiceTest extends AbstractTest{
	@Autowired
	private TagService tagService;
		//----------------------------------------------------
		// POSITIVE TEST CASES
		//----------------------------------------------------
		
		//A user who is authenticated as an administrator must be able to:
		//	Update the catalogue of tags.
		@Test
		public void testUpdate(){
			authenticate("admin1");
			
			Tag tag=tagService.create();
			
			tag.setKeywords("adasdasdsa,asdasdsadasda");
			tagService.save(tag);
			
			unauthenticate();
		}

		//----------------------------------------------------
		// NEGATIVE TEST CASES
		//----------------------------------------------------

		//A user who is authenticated as an administrator must be able to:
		//	Update the catalogue of tags.
		// We will try to update as an unauthenticate user
		@Test(expected=IllegalArgumentException.class)
		public void testUpdateException(){			
			Tag tag=tagService.create();
			
			tag.setKeywords("adasdasdsa,asdasdsadasda");
			tagService.save(tag);

		}
}
