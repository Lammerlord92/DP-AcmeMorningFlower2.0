package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Customer;
import domain.Doughnut;
import domain.FlowerOrder;
import domain.CreditCard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class FlowerOrderServiceTest extends AbstractTest {
	@Autowired
	private FlowerOrderService flowerOrderService;
	@Autowired
	private DoughnutService doughnutService;

	// ----------------------------------------------------
	// POSITIVE TEST CASES
	// ----------------------------------------------------
	// - A user who is not authenticated must be able to:
	// o Place an order to send a flower to a recipient.
	@Test
	public void testPlaceFlowerOrder() {
		FlowerOrder flowerOrder = flowerOrderService.create(10);
		flowerOrder.setAddress("aADSDADASDasdasd");

		CreditCard creditCard = new CreditCard();
		creditCard.setBrandName("adasdasdad");
		creditCard.setCvvCode(122);
		creditCard.setExpirationMonth(1);
		creditCard.setExpirationYear(2020);
		creditCard.setHolderName("asdasdsad");
		creditCard.setNumber("4929468422750785");

		Customer recipient = new Customer();
		recipient.setEmailAddress("aasda@gmail.com");
		recipient.setName("adsadasdasdd");
		recipient.setSurname("asdasdasdasawq");
		recipient.setPhoneNumber("+34sadsad dadsad");
		Date birthdate = new Date();
		birthdate.setDate(4);
		birthdate.setMonth(4);
		birthdate.setYear(90);
		recipient.setBirthdate(birthdate);

		Customer sender = new Customer();
		sender.setEmailAddress("aasda@gmail.com");
		sender.setName("adsadasdasdd");
		sender.setSurname("asdasdasdasawq");
		sender.setPhoneNumber("+34sadsad dadsad");
		Date birthdate2 = new Date();
		birthdate2.setDate(4);
		birthdate2.setMonth(4);
		birthdate2.setYear(90);
		sender.setBirthdate(birthdate2);

		flowerOrder.setRecipient(recipient);
		flowerOrder.setSender(sender);
		flowerOrder.setCreditCard(creditCard);

		flowerOrderService.save(flowerOrder);
	}

	// A user who can place an order must be able to:
	// o Select a doughnut from the catalogue and
	// add it to the order. Note that the doughnut
	// will be delivered at no charge and that a user
	// may opt not to select any doughnuts.
	@Test
	public void testPlaceFlowerOrderWithDoughnut() {
		FlowerOrder flowerOrder = flowerOrderService.create(10);
		flowerOrder.setAddress("aADSDADASDasdasd");

		CreditCard creditCard = new CreditCard();
		creditCard.setBrandName("adasdasdad");
		creditCard.setCvvCode(122);
		creditCard.setExpirationMonth(1);
		creditCard.setExpirationYear(2020);
		creditCard.setHolderName("asdasdsad");
		creditCard.setNumber("4929468422750785");

		Customer recipient = new Customer();
		recipient.setEmailAddress("aasda@gmail.com");
		recipient.setName("adsadasdasdd");
		recipient.setSurname("asdasdasdasawq");
		recipient.setPhoneNumber("+34sadsad dadsad");
		Date birthdate = new Date();
		birthdate.setDate(4);
		birthdate.setMonth(4);
		birthdate.setYear(90);
		recipient.setBirthdate(birthdate);

		Customer sender = new Customer();
		sender.setEmailAddress("aasda@gmail.com");
		sender.setName("adsadasdasdd");
		sender.setSurname("asdasdasdasawq");
		sender.setPhoneNumber("+34sadsad dadsad");
		Date birthdate2 = new Date();
		birthdate2.setDate(4);
		birthdate2.setMonth(4);
		birthdate2.setYear(90);
		sender.setBirthdate(birthdate2);

		flowerOrder.setRecipient(recipient);
		flowerOrder.setSender(sender);
		flowerOrder.setCreditCard(creditCard);

		Doughnut doughnut = doughnutService.findOne(14);
		flowerOrder.setDoughnut(doughnut);

		flowerOrderService.save(flowerOrder);
	}

	// A user who is not authenticated must be able to:
	// o Cancel an order using its ticker, as long as the order has not been
	// cancelled previously or is already delivered. No cancellation is accepted
	// during the 12 hours before the flower is delivered.
	@Test
	public void testCancelFlowerOrder() {
		flowerOrderService.delete("20150309-ahsaSWWQAQW32131");
		FlowerOrder flowerOrder = flowerOrderService
				.findOne("20150309-ahsaSWWQAQW32131");
		Assert.isTrue(flowerOrder.getStatus().equals("CANCELLED"));
	}

	// A user who is not authenticated must be able to:
	// o Display an order by providing its ticker.
	@Test
	public void testFindFlowerOrder() {
		FlowerOrder flowerOrder = flowerOrderService
				.findOne("20150309-ahsaSWWQAQW32131");
		Assert.notNull(flowerOrder);
	}

	// - A user who is authenticated as an administrator must be able to:
	// -The average number of orders placed by a customer.
	@Test
	public void testAvgOrdersByCustomer() {
		authenticate("admin1");
		Collection<Object[]> flowerOrders = flowerOrderService
				.avgOrdersByCustomer();
		Assert.isTrue(!flowerOrders.isEmpty());
		authenticate(null);
	}

	// -The average number of times that every flower's been ordered.
	@Test
	public void testNumOrdersFlowers() {
		authenticate("admin1");
		Collection<Object[]> flowerOrders = flowerOrderService
				.numOrdersFlowers();
		Assert.isTrue(!flowerOrders.isEmpty());
		authenticate(null);
	}

	// - A user who is authenticated as an administrator must be able to:
	// o Display a dashboard with the following information:
	// The ratio of orders that opt not to include a doughnut.
	@Test
	public void testRatioOrdersNotIncludeDoughnut() {
		authenticate("admin1");
		Double ratio = flowerOrderService.ratioOrdersNotIncludeDoughnut();
		Assert.isTrue(ratio > 0);
		authenticate(null);
	}

	// The ratio of non-cancelled orders that opt not to include a doughnut.
	@Test
	public void testRatioNonCancelledOrdersNotIncludeDoughnut() {
		authenticate("admin1");
		Double ratio = flowerOrderService
				.ratioNonCancelledOrdersNotIncludeDoughnut();
		Assert.isTrue(ratio > 0);
		authenticate(null);
	}

	// The ratio of orders per customer that opt not to include a doughnut.
	@Test
	public void testRatioOrdersNotIncludeDoughnutByCustomer() {
		authenticate("admin1");
		Collection<Object[]> flowerOrders = flowerOrderService
				.ratioOrdersNotIncludeDoughnutByCustomer();
		Assert.isTrue(!flowerOrders.isEmpty());
		authenticate(null);
	}

	// The ratio of non-cancelled orders per customer that opt not to include a
	// doughnut.
	@Test
	public void testRatioOrdersNotCancelledNotIncludeDoughnutByCustomer() {
		authenticate("admin1");
		Collection<Object[]> flowerOrders = flowerOrderService
				.ratioOrdersNotCancelledNotIncludeDoughnutByCustomer();
		Assert.isTrue(!flowerOrders.isEmpty());
		authenticate(null);
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES
	// ----------------------------------------------------

	// - A user who is not authenticated must be able to:
	// o Place an order to send a flower to a recipient.
	// We will try to put a null value in one customer
	@Test(expected = ConstraintViolationException.class)
	public void testPlaceFlowerOrderNegative() {
		FlowerOrder flowerOrder = flowerOrderService.create(10);
		flowerOrder.setAddress("aADSDADASDasdasd");

		CreditCard creditCard = new CreditCard();
		creditCard.setBrandName("adasdasdad");
		creditCard.setExpirationMonth(1);
		creditCard.setExpirationYear(2020);
		creditCard.setCvvCode(122);
		creditCard.setHolderName("");
		creditCard.setNumber("4929468422750785");

		Customer recipient = new Customer();
		recipient.setEmailAddress("aasda@gmail.com");
		recipient.setName("adsadasdasdd");
		recipient.setSurname("asdasdasdasawq");
		recipient.setPhoneNumber("+34sadsad dadsad");
		Date birthdate = new Date();
		birthdate.setDate(4);
		birthdate.setMonth(4);
		birthdate.setYear(1990);
		recipient.setBirthdate(birthdate);

		Customer sender = new Customer();
		sender.setEmailAddress("aasda@gmail.com");
		sender.setName("adsadasdasdd");
		sender.setSurname("asdasdasdasawq");
		sender.setPhoneNumber("+34sadsad dadsad");
		Date birthdate2 = new Date();
		birthdate2.setDate(4);
		birthdate2.setMonth(4);
		birthdate2.setYear(1990);
		sender.setBirthdate(birthdate2);

		flowerOrder.setRecipient(recipient);
		flowerOrder.setSender(sender);
		flowerOrder.setCreditCard(creditCard);

		flowerOrderService.save(flowerOrder);
	}

	// A user who can place an order must be able to:
	// o Select a doughnut from the catalogue and
	// add it to the order. Note that the doughnut
	// will be delivered at no charge and that a user
	// may opt not to select any doughnuts.
	// Trying with a deleted doughnut
	@Test(expected = IllegalArgumentException.class)
	public void testPlaceFlowerOrderWithDoughnutNegative() {
		FlowerOrder flowerOrder = flowerOrderService.create(10);
		flowerOrder.setAddress("aADSDADASDasdasd");

		CreditCard creditCard = new CreditCard();
		creditCard.setBrandName("adasdasdad");
		creditCard.setCvvCode(122);
		creditCard.setExpirationMonth(1);
		creditCard.setExpirationYear(2020);
		creditCard.setHolderName("asdasdsad");
		creditCard.setNumber("4929468422750785");

		Customer recipient = new Customer();
		recipient.setEmailAddress("aasda@gmail.com");
		recipient.setName("adsadasdasdd");
		recipient.setSurname("asdasdasdasawq");
		recipient.setPhoneNumber("+34sadsad dadsad");
		Date birthdate = new Date();
		birthdate.setDate(4);
		birthdate.setMonth(4);
		birthdate.setYear(90);
		recipient.setBirthdate(birthdate);

		Customer sender = new Customer();
		sender.setEmailAddress("aasda@gmail.com");
		sender.setName("adsadasdasdd");
		sender.setSurname("asdasdasdasawq");
		sender.setPhoneNumber("+34sadsad dadsad");
		Date birthdate2 = new Date();
		birthdate2.setDate(4);
		birthdate2.setMonth(4);
		birthdate2.setYear(90);
		sender.setBirthdate(birthdate2);

		flowerOrder.setRecipient(recipient);
		flowerOrder.setSender(sender);
		flowerOrder.setCreditCard(creditCard);

		Doughnut doughnut = doughnutService.findOne(15);
		flowerOrder.setDoughnut(doughnut);

		flowerOrderService.save(flowerOrder);
	}

	// A user who is not authenticated must be able to:
	// o Cancel an order using its ticker, as long as the order has not been
	// cancelled previously or is already delivered. No cancellation is accepted
	// during the 12 hours before the flower is delivered.
	// Trying with a delivery order
	@Test(expected = IllegalArgumentException.class)
	public void testCancelFlowerOrderNegative() {
		flowerOrderService.delete("20150309-ahsdahdsaus232131");
		FlowerOrder flowerOrder = flowerOrderService
				.findOne("20150309-ahsdahdsaus232131");
		Assert.isTrue(flowerOrder.getStatus().equals("CANCELLED"));
	}

	// A user who is not authenticated must be able to:
	// o Display an order by providing its ticker.
	// Trying with a null ticker
	@Test(expected = IllegalArgumentException.class)
	public void testReplyFlowerOrderNegative() {
		FlowerOrder flowerOrder = flowerOrderService.findOne(null);
		Assert.notNull(flowerOrder);
	}

	// - A user who is authenticated as an administrator must be able to:
	// -The average number of orders placed by a customer.
	// -Trying as unregistered user
	@Test(expected = IllegalArgumentException.class)
	public void testAvgOrdersByCustomerNegative() {
		Collection<Object[]> flower = flowerOrderService.avgOrdersByCustomer();
		Assert.isTrue(!flower.isEmpty());
	}

	// -The average number of times that every flower's been ordered.
	// -Trying as unregistered user
	@Test(expected = IllegalArgumentException.class)
	public void testNumOrdersFlowersNegative() {
		Collection<Object[]> flower = flowerOrderService.numOrdersFlowers();
		Assert.isTrue(!flower.isEmpty());
	}

	// - A user who is authenticated as an administrator must be able to:
	// o Display a dashboard with the following information:
	// The ratio of orders that opt not to include a doughnut.
	// -Trying as unregistered user
	@Test(expected = IllegalArgumentException.class)
	public void testRatioOrdersNotIncludeDoughnutNegative() {
		Double ratio = flowerOrderService.ratioOrdersNotIncludeDoughnut();
		Assert.isTrue(ratio > 0);
	}

	// The ratio of non-cancelled orders that opt not to include a doughnut.
	// -Trying as unregistered user
	@Test(expected = IllegalArgumentException.class)
	public void testRatioNonCancelledOrdersNotIncludeDoughnutNegative() {
		Double ratio = flowerOrderService
				.ratioNonCancelledOrdersNotIncludeDoughnut();
		Assert.isTrue(ratio > 0);
	}

	// The ratio of orders per customer that opt not to include a doughnut.
	// -Trying as unregistered user
	@Test(expected = IllegalArgumentException.class)
	public void testRatioOrdersNotIncludeDoughnutByCustomerNegative() {
		Collection<Object[]> flowerOrders = flowerOrderService
				.ratioOrdersNotIncludeDoughnutByCustomer();
		Assert.isTrue(!flowerOrders.isEmpty());
	}

	// The ratio of non-cancelled orders per customer that opt not to include a
	// doughnut.
	// -Trying as unregistered user
	@Test(expected = IllegalArgumentException.class)
	public void testRatioOrdersNotCancelledNotIncludeDoughnutByCustomerNegative() {
		Collection<Object[]> flowerOrders = flowerOrderService
				.ratioOrdersNotCancelledNotIncludeDoughnutByCustomer();
		Assert.isTrue(!flowerOrders.isEmpty());
	}
}
