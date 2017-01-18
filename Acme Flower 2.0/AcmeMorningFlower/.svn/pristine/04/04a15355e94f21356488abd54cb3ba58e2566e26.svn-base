package forms;

import javax.persistence.Access;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.AccessType;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import domain.CreditCard;
import domain.Customer;
@Embeddable
@Access(AccessType.PROPERTY)
public class FlowerOrderForm {
	
	private String address;
	private double totalPrice;
	private CreditCard creditCard;
	private Customer sender;
	private Customer recipient;
	

	@Valid
	@OneToOne(optional=false,cascade=CascadeType.ALL)
	public Customer getSender() {
		return sender;
	}
	public void setSender(Customer sender) {
		this.sender = sender;
	}
	
	@Valid
	@OneToOne(optional=false,cascade=CascadeType.ALL)
	public Customer getRecipient() {
		return recipient;
	}
	public void setRecipient(Customer recipient) {
		this.recipient = recipient;
	}	
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Min(0)
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Valid
	@NotNull
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	//Relationships -------------------------------------------

	private int flower;

	public int getFlower() {
		return flower;
	}
	public void setFlower(int flower) {
		this.flower = flower;
	}
}
