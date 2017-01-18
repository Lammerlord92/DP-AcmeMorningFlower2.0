package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Flower extends DomainEntity{
	private String vulgarName;
	private String scientificName;
	private double unitPrice;
	private String url;
	private boolean deleted;
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getVulgarName() {
		return vulgarName;
	}
	public void setVulgarName(String vulgarName) {
		this.vulgarName = vulgarName;
	}
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getScientificName() {
		return scientificName;
	}
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}
	
	@Min(0)
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	@URL
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	//Relationships -------------------------------------------
	private Tag tag;
//	private Collection<Comment> comments;
//	
//	@OneToMany(mappedBy="comment")
//	public Collection<Comment> getComments() {
//		return comments;
//	}
//	public void setComments(Collection<Comment> comments) {
//		this.comments = comments;
//	}
	@NotNull
	@ManyToOne(optional=false)
	public Tag getTag() {
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
	private Collection<FlowerOrder> flowerOrders;
	@OneToMany(mappedBy="flower")
	public Collection<FlowerOrder> getFlowerOrders() {
		return flowerOrders;
	}
	public void setFlowerOrders(Collection<FlowerOrder> flowerOrders) {
		this.flowerOrders = flowerOrders;
	}



}
