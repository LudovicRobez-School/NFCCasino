package fr.Customers.Ressources;

import java.util.Date;
import java.util.Map;

public class Customer {
private String mail;
private String firstName;
private String lastName;
private String city;
private String country;
private String address;
private String postalCode;
private String state;

	public Customer(Map<String, String> customer) {
		this.mail = customer.get("mail");
		this.firstName = customer.get("firstName");
		this.lastName = customer.get("lastName");
		this.city = customer.get("city");
		this.country = customer.get("country");
		this.address = customer.get("address");
		this.postalCode = customer.get("postalCode");
		this.state = customer.get("state");
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMail(){return mail;}
	public String getFirstName(){return firstName;}
	public String getLastName(){return lastName;}

	public void setMail(String m){mail=mail;}
	public void setFirstName(String fn){firstName=fn;}
	public void setLastName(String ln){lastName=ln;}

	/*
	public Object clone(){
		return new Customer(mail, firstName, lastName, city, country, address, postalCode, state);
	}
	public String ToString(){
		return "Mail : "+mail+", FirstName : "+firstName+", LastName : "+lastName;
	}
	public boolean equals(Object obj1){
		if(this.ToString()==obj1.toString()) return true;
		else return false;
	}
	*/

}
