package forms;

import javax.persistence.Access;
import javax.persistence.Embeddable;
import javax.persistence.AccessType;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
@Embeddable
@Access(AccessType.PROPERTY)
public class CommentForm {
	
	// Attributes ----------------------------------------------------------
	private Integer parent;
	private int flower;
	private String nick;
	private String text;
	private String url;

	
	// Getters&Setters ----------------------------------------------------

	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	public int getFlower() {
		return flower;
	}
	public void setFlower(int flower) {
		this.flower = flower;
	}
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@URL
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
