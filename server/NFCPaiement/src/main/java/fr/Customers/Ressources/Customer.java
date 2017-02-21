package fr.Customers.Ressources;

import java.util.Date;

public class Customer {
private String mail;
private String firstName;
private String lastName;
private int cardNumber;
private int cryptoG;
private Date expDate;

	public Customer(String m,String fn, String ln, int cn, int cg, Date ed){
		mail=m;
		firstName=fn;
		lastName=ln;
		cardNumber=cn;
		cryptoG=cg;
		expDate=ed;
	}
	public String getMail(){return mail;}
	public String getFirstName(){return firstName;}
	public String getLastName(){return lastName;}
	public int getCardNumber(){return cardNumber;}
	public int getCryptoG(){return cryptoG;}
	public Date getExpDate(){return expDate;}

	public void setMail(String m){mail=mail;}
	public void setFirstName(String fn){firstName=fn;}
	public void setLastName(String ln){lastName=ln;}
	public void setCardNumber(int cn){cardNumber=cn;}
	public void setCryptoG(int cg){cryptoG=cg;}
	public void setExpDate(Date ed){expDate=ed;}
	
	public Object clone(){
		return new Customer(mail,firstName,lastName,cardNumber,cryptoG,expDate);
	}
	public String ToString(){
		return "Mail : "+mail+", FirstName : "+firstName+", LastName : "+lastName+", CardNumber : "+cardNumber+", Cryptograme : "+cryptoG+", ExpirationDate : "+expDate;
	}
	public boolean equals(Object obj1){
		if(this.ToString()==obj1.toString()) return true;
		else return false;
	}

}
