package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


import security.UserAccount;
@Entity
public abstract class Actor extends DomainEntity{
	private UserAccount userAccount;
	
	@OneToOne(cascade=CascadeType.ALL)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
