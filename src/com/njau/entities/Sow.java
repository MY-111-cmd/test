package com.njau.entities;

public class Sow {
	private String id;
	private String frequency;
	private int smallpig;
	private String delivery;
	private String expectant;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFrequency() {
		return frequency;
	}
	@Override
	public String toString() {
		return "Sow [id=" + id + ", frequency=" + frequency + ", smallpig=" + smallpig + ", delivery=" + delivery
				+ ", expectant=" + expectant + "]";
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public int getSmallpig() {
		return smallpig;
	}
	public void setSmallpig(int smallpig) {
		this.smallpig = smallpig;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public String getExpectant() {
		return expectant;
	}
	public void setExpectant(String expectant) {
		this.expectant = expectant;
	}

}