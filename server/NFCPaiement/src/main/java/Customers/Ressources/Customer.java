package Customers.Ressources;

/**
 * Created by rl613611 on 17/01/2017.
 */
public class Customer {
private String firstName;
private String lastName;
private int cardNumber;
private int cryptoG;
private Date expDate;

	public Customer(String fn, String ln, int cn, int cg, int ed){
		firstName=fn;
		lastName=ln;
		cardNumber=cn;
		cryptoG=cg;
		expDate=ed;
	}
	public String getFirstName(){return firstName;}
	public String getLastName(){return lastName;}
	public int getCardNumber(){return cardNumber;}
	public int getCryptoG(){return cryptoG;}
	public Date getExpDate(){return expDate;}
	
	public void setFirstName(String fn){firstName=fn;}
	public void setLastName(String ln){lastName=ln;}
	public void setCardNumber(int cn){cardNumber=cn;}
	public void setCryptoG(int cg){cryptoG=cg;}
	public void setExpDate(Date ed){expDate=ed;}
	
	public Object clone(){
		return new Customer(firstName,lastName,cardNumber,cryptoG,expDate);
	}
	public String ToString(){
		return "FirstName : "+firstName+", LastName : "+lastName+", CardNumber : "+cardNumber+", Cryptograme : "+cryptoG+", ExpirationDate : "+expDate;
	}
	public boolean equals(Object obj1){
		if(this.ToString()==obj1.ToString()) return true;
		else return false;
	}

}
