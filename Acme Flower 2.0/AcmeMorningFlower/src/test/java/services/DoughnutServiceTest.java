package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.hibernate.annotations.common.AssertionFailure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.AdditionalCost;
import domain.Administrator;
import domain.Customer;
import domain.Doughnut;
import domain.CreditCard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class DoughnutServiceTest extends AbstractTest {
	@Autowired
	private DoughnutService doughnutService;

	// ----------------------------------------------------
	// POSITIVE TEST CASES
	// ----------------------------------------------------
	
	// Manage the catalogue of doughnuts, which includes listing them,
	//modifying them, deleting them, and creating them. Deleting a doughnut
	//means that it is not available in the catalogue, but its information 
	//is not actually removed from the database.
	@Test
	public void testListAdmin() {
		authenticate("admin1");
		Collection<Doughnut> doughnut = doughnutService.findAll();
		Assert.isTrue(doughnut.size() == 2);
		authenticate(null);
	}

	// Modifying
	@Test
	public void testModifyingAdmin() {
		authenticate("admin1");
		Doughnut doughnut = doughnutService.findOne(14);
		doughnut.setCalories(30);
		doughnutService.save(doughnut);
		authenticate(null);
	}

	// Deleting
	@Test
	public void testDeletingAdmin() {
		authenticate("admin1");
		doughnutService.delete(14);
		authenticate(null);
	}

	// UnDeleting
	@Test
	public void testUndeletingAdmin() {
		authenticate("admin1");
		doughnutService.undelete(15);
		authenticate(null);
	}

	// Creating
	@Test
	public void testCreatingAdmin() {
		authenticate("admin1");
		Doughnut doughnut = doughnutService.create();
		doughnut.setIngredients("adasdasdasd");
		doughnut.setName("asdadsasd");
		doughnut.setCalories(12);
		doughnutService.save(doughnut);
		authenticate(null);
	}

	// - A user who is authenticated as an administrator must be able to:
	// o Display a dashboard with the following information:
	// The ratio of doughnuts that are not available.
	@Test
	public void testRatioNotActives() {
		authenticate("admin1");
		Double ratio = doughnutService.ratioNotActives();
		Assert.isTrue(ratio > 0);
		authenticate(null);
	}


	// ----------------------------------------------------
	// NEGATIVE TEST CASES
	// ----------------------------------------------------
	
	// Manage the catalogue of doughnuts, which includes listing them,
	//modifying them, deleting them, and creating them. Deleting a doughnut
	//means that it is not available in the catalogue, but its information 
	//is not actually removed from the database.
	// Trying as unregistered
	@Test(expected = IllegalArgumentException.class)
	public void testListAdminNegative() {
		Collection<Doughnut> doughnut = doughnutService.findAll();
		Assert.isTrue(doughnut.size() == 2);
	}

	// Modifying
	@Test(expected = IllegalArgumentException.class)
	public void testModifyingAdminNegative() {
		Doughnut doughnut = doughnutService.findOne(14);
		doughnut.setCalories(30);
		doughnutService.save(doughnut);
	}

	// Deleting
	@Test(expected = IllegalArgumentException.class)
	public void testDeletingAdminNegative() {
		doughnutService.delete(14);
	}

	// UnDeleting
	@Test(expected = IllegalArgumentException.class)
	public void testUndeletingAdminNegative() {
		doughnutService.undelete(15);
	}

	// Creating
	@Test(expected = IllegalArgumentException.class)
	public void testCreatingAdminNegative() {
		Doughnut doughnut = doughnutService.create();
		doughnut.setIngredients("adasdasdasd");
		doughnut.setName("asdadsasd");
		doughnut.setCalories(12);
		doughnutService.save(doughnut);
	}

	// - A user who is authenticated as an administrator must be able to:
	// o Display a dashboard with the following information:
	// The ratio of doughnuts that are not available.
	// Trying as unregistered
	@Test(expected = IllegalArgumentException.class)
	public void testRatioNotActivesNegative() {	
		Double ratio = doughnutService.ratioNotActives();
		Assert.isTrue(ratio > 0);
	}

}
