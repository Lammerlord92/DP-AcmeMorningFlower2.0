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
import domain.Flower;
import domain.CreditCard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class FlowerServiceTest extends AbstractTest {
	@Autowired
	private FlowerService flowerService;
	@Autowired
	private TagService tagService;

	// ----------------------------------------------------
	// POSITIVE TEST CASES
	// ----------------------------------------------------

	// - A user who is not authenticated must be able to:
	// o List the catalogue of flowers.
	@Test
	public void testListFlowers() {
		Collection<Flower> flower = flowerService.findAllUndeleted();
		Assert.isTrue(flower.size() == 2);
	}

	// - A user who is not authenticated must be able to:
	// o Search for the flowers that have a single keyword
	// in their vulgar name, scientific name, or tag.
	@Test
	public void testSearchFlowers() {
		Collection<Flower> flower = flowerService.findByKeyword("e");
		Assert.isTrue(flower.size() == 2);
	}

	// - A user who is authenticated as an administrator must be able to:
	// o Manage the catalogue of flowers, which includes listing them, modifying
	// them,
	// deleting them, or creating them. Deleting a flower means that it is
	// flagged as
	// not available, which implies that it is not listed in the catalogue and
	// cannot
	// be further edited, but the information about the flower is not actually
	// removed
	// from the database. A flower that is deleted can be undeleted later.
	// Listing
	@Test
	public void testListAdminFlowers() {
		authenticate("admin1");
		Collection<Flower> flower = flowerService.findAll();
		Assert.isTrue(flower.size() == 4);
		authenticate(null);
	}

	// Modifying
	@Test
	public void testModifyingAdminFlowers() {
		authenticate("admin1");
		Flower flower = flowerService.findOne(12);
		flower.setVulgarName("asdasdsad");
		authenticate(null);
	}

	// Deleting
	@Test
	public void testDeletingAdminFlowers() {
		authenticate("admin1");
		flowerService.delete(10);
		authenticate(null);
	}

	// UnDeleting
	@Test
	public void testUndeletingAdminFlowers() {
		authenticate("admin1");
		flowerService.undelete(12);
		authenticate(null);
	}

	// Creating
	@Test
	public void testCreatingAdminFlowers() {
		authenticate("admin1");
		Flower flower = flowerService.create();
		flower.setVulgarName("asdasdsad");
		flower.setScientificName("asdadsasd");
		flower.setUnitPrice(12.0);
		flower.setTag(tagService.findOne(9));
		authenticate(null);
	}

	// - A user who is authenticated as an administrator must be able to:
	// o Display a dashboard with the following information:
	// The catalogue of best-selling flowers, which includes the
	// flowers that have been ordered in more than 2.5% of the orders.
	@Test
	public void testBestSelledFlowers() {
		authenticate("admin1");
		Collection<Flower> flower = flowerService.bestSelledFlowers();
		Assert.isTrue(flower.size() == 3);
		authenticate(null);
	}

	// The catalogue of flowers that have never been ordered.
	@Test
	public void testNeverOrderedFlowers() {
		authenticate("admin1");
		Collection<Flower> flower = flowerService.notOrderedFlowers();
		Assert.isTrue(flower.size() == 1);
		authenticate(null);
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES
	// ----------------------------------------------------
	// - A user who is not authenticated must be able to:
	// o List the catalogue of flowers.
	@Test(expected = IllegalArgumentException.class)
	public void testListFlowersNegative() {
		Collection<Flower> flower = flowerService.findAllUndeleted();
		Assert.isTrue(flower.size() == 3);
	}

	// - A user who is not authenticated must be able to:
	// o Search for the flowers that have a single keyword
	// in their vulgar name, scientific name, or tag.
	@Test(expected = IllegalArgumentException.class)
	public void testSearchFlowersNegative() {
		Collection<Flower> flower = flowerService.findByKeyword("e");
		Assert.isTrue(flower.size() == 3);
	}

	// - A user who is authenticated as an administrator must be able to:
	// o Manage the catalogue of flowers, which includes listing them, modifying
	// them,
	// deleting them, or creating them. Deleting a flower means that it is
	// flagged as
	// not available, which implies that it is not listed in the catalogue and
	// cannot
	// be further edited, but the information about the flower is not actually
	// removed
	// from the database. A flower that is deleted can be undeleted later.
	// Listing as unregistered
	@Test(expected = IllegalArgumentException.class)
	public void testListAdminFlowersNegative() {
		Collection<Flower> flower = flowerService.findAll();
		Assert.isTrue(flower.size() == 3);
	}

	// Modifying as unregistered
	@Test(expected = IllegalArgumentException.class)
	public void testModifyingAdminFlowersNegative() {
		Flower flower = flowerService.findOne(12);
		flower.setVulgarName("asdasdsad");
		flowerService.save(flower);

	}

	// Deleting as unregistered
	@Test(expected = IllegalArgumentException.class)
	public void testDeletingAdminFlowersNegative() {

		flowerService.delete(10);

	}

	// UnDeleting as unregistered
	@Test(expected = IllegalArgumentException.class)
	public void testUndeletingAdminFlowersNegative() {

		flowerService.undelete(12);

	}

	// Creating as unregistered
	@Test(expected = IllegalArgumentException.class)
	public void testCreatingAdminFlowersNegative() {

		Flower flower = flowerService.create();
		flower.setVulgarName("asdasdsad");
		flower.setScientificName("asdadsasd");
		flower.setUnitPrice(12.0);
		flower.setTag(tagService.findOne(9));

	}

	// - A user who is authenticated as an administrator must be able to:
	// o Display a dashboard with the following information:
	// The catalogue of best-selling flowers, which includes the
	// flowers that have been ordered in more than 2.5% of the orders.
	//	Trying as unregistered
	@Test(expected=IllegalArgumentException.class)
	public void testBestSelledFlowersException() {
		Collection<Flower> flower = flowerService.bestSelledFlowers();
		Assert.isTrue(flower.size() == 3);
	}

	// The catalogue of flowers that have never been ordered.
	//	Trying as unregistered
	@Test(expected=IllegalArgumentException.class)
	public void testNeverOrderedFlowersException() {
		Collection<Flower> flower = flowerService.notOrderedFlowers();
		Assert.isTrue(flower.size() == 1);
	}

}
