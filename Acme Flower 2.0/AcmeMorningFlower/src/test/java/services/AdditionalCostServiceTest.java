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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AdditionalCostServiceTest extends AbstractTest{
	@Autowired
	private AdditionalCostService additionalCostService;
		//----------------------------------------------------
		// POSITIVE TEST CASES
		//----------------------------------------------------
		
		//A user who is authenticated as an administrator must be able to:
		//	Update the information regarding handling and shipping costs and taxes.
		@Test
		public void testUpdate(){
			authenticate("admin1");
			
			AdditionalCost additionalCost=additionalCostService.findAdditionalCost();
			Assert.notNull(additionalCost);
			
			additionalCost.setShippingCost(6.0);
			additionalCostService.save(additionalCost);
			
			unauthenticate();
		}

		//----------------------------------------------------
		// NEGATIVE TEST CASES
		//----------------------------------------------------

		//A user who is authenticated as an administrator must be able to:
		//	Update the information regarding handling and shipping costs and taxes.
		// We will try to update as an unauthenticate user
		@Test(expected=IllegalArgumentException.class)
		public void testRegisterException(){
			AdditionalCost additionalCost=additionalCostService.findAdditionalCost();
			Assert.notNull(additionalCost);
			
			additionalCost.setShippingCost(6.0);
			additionalCostService.save(additionalCost);
		}
}
