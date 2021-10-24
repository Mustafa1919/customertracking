package com.msen.demo.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountLists <P,D> implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<P> listOfPrice;
	private List<D> listOfDate;
	
	public AccountLists(){
		this.listOfDate = new ArrayList<D>();
		this.listOfPrice = new ArrayList<P>();
	}
	
	public void add(P price, D date) {
		this.listOfDate.add(date);
		this.listOfPrice.add(price);
	}
	
	public void delete(P price, D date) {
		this.listOfDate.remove(date);
		this.listOfPrice.remove(price);
	}
	
	public void delete(int size) {
		if(size < this.listOfDate.size() & size>-1) {
			this.listOfDate.remove(size);
			this.listOfPrice.remove(size);
		}
	}
	
	public int lenght() {
		return this.listOfDate.size()==this.listOfPrice.size() ? this.listOfDate.size() : -1;
	}
	
	
	
}
