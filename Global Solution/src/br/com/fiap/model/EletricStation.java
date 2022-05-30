package br.com.fiap.model;

import java.util.Arrays;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EletricStation {
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String stationName;
	private String street;
	private String neighborhood;
	private String city;
	private String state;
	private int rating;
	private String[] carPlug;
	private float priceKwh;
	
	public String getStationName() {
		return stationName;
	}
	
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getNeighborhood() {
		return neighborhood;
	}
	
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public Long getRating() {
		return (long) rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String[] getCarPlug() {
		return carPlug;
	}
	
	public void setCarPlug(String[] carPlug) {
		this.carPlug = carPlug;
	}
	
	public float getPriceKwh() {
		return priceKwh;
	}
	
	public void setPriceKwh(float priceKwh) {
		this.priceKwh = priceKwh;
	}
	
	public Vector<String> getData() {
		
		Vector<String> data = new Vector<String>(); 
        data.add(id.toString());
        data.add(stationName);
        data.add(street);
        data.add(neighborhood);
        data.add(city);
        data.add(state);
        data.add("2");
        data.add("1");
        data.add(getRating().toString());


        return data;
	}

	@Override
	public String toString() {
		return "EletricStation [id=" + id + ", stationName=" + stationName + ", street=" + street + ", neighborhood="
				+ neighborhood + ", city=" + city + ", state=" + state + ", rating=" + rating + ", carPlug="
				+ Arrays.toString(carPlug) + ", priceKwh=" + priceKwh + "]";
	}

	
	

}
