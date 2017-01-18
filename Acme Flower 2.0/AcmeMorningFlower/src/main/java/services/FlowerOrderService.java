package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FlowerOrderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.AdditionalCost;
import domain.CreditCard;
import domain.Customer;
import domain.Flower;
import domain.FlowerOrder;
import forms.FlowerOrderForm;

@Transactional
@Service
public class FlowerOrderService {

	// Managed repository -----------------------------------------
	@Autowired
	private FlowerOrderRepository flowerOrderRepository;
	// Supporting services ----------------------------------------
	@Autowired
	private FlowerService flowerService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AdditionalCostService additionalCostService;

	// Constructor ------------------------------------------------
	// Simple CRUD methods ----------------------------------------
	// Place an order
	public FlowerOrder create(int flowerId) {
		FlowerOrder result;
		result = new FlowerOrder();
		Flower flower = flowerService.findOne(flowerId);
		result.setFlower(flower);

		return result;
	}

	// All catalogue
	public Collection<FlowerOrder> findAll() {
		Collection<FlowerOrder> result = flowerOrderRepository.findAll();
		return result;
	}

	// Display by ticker
	public FlowerOrder findOne(String ticker) {
		FlowerOrder result = flowerOrderRepository.findByTicker(ticker);
		return result;
	}

	// Saving
	public void save(FlowerOrder flowerOrder) {
		Date actualDate = new Date();
		long deliveryDate = actualDate.getTime()
				+ (long) (2.592 * Math.pow(10, 9));

		Date delDate = new Date(deliveryDate);

		
		delDate.setHours(8);
		delDate.setMinutes(0);
		if(flowerOrder.getCreditCard().getExpirationYear()==(delDate.getYear()+1900)){
			if(flowerOrder.getCreditCard().getExpirationMonth()<=(delDate.getMonth()+1))
				Assert.isTrue(false,"expired");
		}else	if(flowerOrder.getCreditCard().getExpirationYear()<(delDate.getYear()+1900))
			Assert.isTrue(false,"expired");
		
		long plus18=actualDate.getTime()
				- (long) (5.68024668 * Math.pow(10, 11));
		Date plus=new Date(plus18);
		if(flowerOrder.getSender().getBirthdate().after(plus)){
			Assert.isTrue(false,"18sender");
		}else	if(flowerOrder.getRecipient().getBirthdate().after(plus))
			Assert.isTrue(false,"18recipient");
		
		
		if (flowerOrder.getDoughnut() != null) {
			if (flowerOrder.getDoughnut().isDeleted())
				Assert.isTrue(false);
		}
		String ticker = generateTicker(actualDate);
		while (findOne(ticker) != null) {
			ticker = generateTicker(actualDate);
		}

		flowerOrder.setTicker(ticker);
		flowerOrder.setMoment(new Date(actualDate.getTime() - 1));
		flowerOrder.setDeliveryDate(delDate);
		flowerOrder.setStatus("PENDING");
		flowerOrderRepository.saveAndFlush(flowerOrder);
	}

	// Deleting (cancel)
	public void delete(String ticker) {
		FlowerOrder flowerOrder = findOne(ticker);

		Date actualDate = new Date();
		long deleteDate = flowerOrder.getDeliveryDate().getTime() - 43200000;
		Date delDate = new Date(deleteDate);
		Assert.isTrue(actualDate.before(delDate), "flowerOrder.deleteDate");
		if (flowerOrder.getStatus().equals("PENDING")) {

			flowerOrder.setStatus("CANCELLED");
			// TODO puede que pete la vista

		} else
			Assert.isTrue(false, "flowerOrder.isCancelled");
		flowerOrderRepository.saveAndFlush(flowerOrder);
	}

	// Other business methods -------------------------------------

	private String generateTicker(Date actualDate) {
		String result = "";
		result += actualDate.getYear() + 1900 + "" + actualDate.getMonth() + 1
				+ "" + actualDate.getDay() + 1 + "-";
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		result += uuid;
		return result;
	}

	public void deliver(int orderId) {
		isAdmin();
		FlowerOrder flowerOrder = flowerOrderRepository.findOne(orderId);
		if (flowerOrder.getStatus() == "CANCELLED") {
			Assert.isTrue(false, "flowerOrder.isCancelled");
		}
		flowerOrder.setStatus("DELIVERED");
		flowerOrderRepository.saveAndFlush(flowerOrder);
	}

	// The ratio of orders that opt not to include a doughnut.
	public Double ratioOrdersNotIncludeDoughnut() {
		isAdmin();
		Collection<FlowerOrder> orders = findAll();
		int aux = flowerOrderRepository.ordersWithoutDoughnut();
		Double ratio = aux * 1.0 / orders.size();

		return ratio;
	}

	// The ratio of non-cancelled orders that opt not to include a doughnut.
	public Double ratioNonCancelledOrdersNotIncludeDoughnut() {
		isAdmin();
		Collection<FlowerOrder> orders = findAll();
		int aux = flowerOrderRepository.ordersNotCancelledDoughnut();
		Double ratio = aux * 1.0 / orders.size();

		return ratio;
	}

	// The ratio of orders per customer that opt not to include a doughnut.
	public Collection<Object[]> ratioOrdersNotIncludeDoughnutByCustomer() {
		isAdmin();
		Collection<Object[]> result = new ArrayList<Object[]>();
		Collection<Object[]> collection = flowerOrderRepository
				.ordersWithoutDoughnutByCustomerEmail();
		Double total = findAll().size() * 1.0;
		result = flowerOrderRepository.ordersWithoutDoughnutByCustomerEmail();
		for (Object[] o : collection) {
			Object[] resultValue = new Object[2];
			resultValue[0] = o[0];
			resultValue[1] = (long) (o[1]) / total;
			result.add(resultValue);
		}
		return result;
	}

	// The ratio of non-cancelled orders per customer that opt not to include a
	// doughnut.
	public Collection<Object[]> ratioOrdersNotCancelledNotIncludeDoughnutByCustomer() {
		isAdmin();
		Collection<Object[]> result = new ArrayList<Object[]>();
		Collection<Object[]> collection = flowerOrderRepository
				.ordersNotCancelledDoughnutByCustomerEmail();
		Double total = findAll().size() * 1.0;
		result = flowerOrderRepository.ordersWithoutDoughnutByCustomerEmail();
		for (Object[] o : collection) {
			Object[] resultValue = new Object[2];
			resultValue[0] = o[0];
			resultValue[1] = (long) (o[1]) / total;
			result.add(resultValue);
		}
		return result;
	}

	public Collection<Object[]> avgOrdersByCustomer() {
		isAdmin();
		Collection<Object[]> result = new ArrayList<Object[]>();
		Collection<Object[]> collection = flowerOrderRepository
				.numOrdersEmail();
		Double total = 1.0 * flowerOrderRepository.numNumberOrders();
		for (Object[] value : collection) {
			Object[] resultValue = new Object[2];
			resultValue[0] = value[0];
			resultValue[1] = (long) (value[1]) / total;
			result.add(resultValue);
		}
		return result;
	}

	public Collection<Object[]> numOrdersFlowers() {
		isAdmin();
		Collection<Object[]> result = new ArrayList<Object[]>();
		Collection<Object[]> collection = flowerOrderRepository
				.numOrdersFlowers();
		Double total = 1.0 * flowerOrderRepository.numNumberOrders();
		for (Object[] value : collection) {
			Object[] resultValue = new Object[2];
			resultValue[0] = value[0];
			resultValue[1] = (long) (value[1]) / total;
			result.add(resultValue);
		}
		return result;
	}

	public FlowerOrderForm construct(int flowerId) {
		Flower flower = flowerService.findOne(flowerId);
		if (flower.isDeleted() != false) {
			Assert.isTrue(false);
		}

		FlowerOrderForm result = new FlowerOrderForm();
		result.setFlower(flowerId);
		Customer sender = new Customer();
		Customer recipient = new Customer();

		AdditionalCost additionalCost = additionalCostService
				.findAdditionalCost();
		Double price = flower.getUnitPrice()
				* (1 + additionalCost.getTaxPercent() / 100)
				+ additionalCost.getShippingCost();
		result.setTotalPrice(price);
		result.setRecipient(recipient);
		result.setSender(sender);
		return result;
	}

	public FlowerOrder reconstruct(FlowerOrderForm flowerOrderForm) {
		FlowerOrder result;
		result = create(flowerOrderForm.getFlower());

		result.setRecipient(flowerOrderForm.getRecipient());
		result.setSender(flowerOrderForm.getSender());
		result.setCreditCard(flowerOrderForm.getCreditCard());
		result.setAddress(flowerOrderForm.getAddress());
		result.setTotalPrice(flowerOrderForm.getTotalPrice());
		result.setDoughnut(flowerOrderForm.getDoughnut());

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
