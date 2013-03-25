/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.model;

import java.util.ArrayList;

/**
 * Represents all available price data for an item and operations on that data.
 * 
 * TODO: Only select data from date range or grocery stores in radius.
 * @author mike
 */
public class ItemPriceData {

	private ArrayList<ItemPriceDataEntry> priceData;
	private Item item;
	private float average;
	private float low;
	private float high;
	private ItemPriceDataEntry lowEntry;
	private ItemPriceDataEntry highEntry;

	public ItemPriceData(ArrayList<ItemPriceDataEntry> priceData, Item item){
		this.priceData = priceData;
		this.item = item;
	}

	/**
	 * Pass over the data once, setting fields: average, low, and high.
	 */
	public void processData(){
		//If there is no data, leave values as zero.
		if(priceData.isEmpty()){
			average = 0;
			low = 0;
			high = 0;
		} else {
			float total = 0;
			low = 1000000;
			high = 0;
			for(ItemPriceDataEntry data: priceData){
				total += data.getPrice();
				if(data.getPrice() < low){
					low = data.getPrice();
					lowEntry = data;
				}
				if(data.getPrice() > high){
					high = data.getPrice();
					highEntry = data;
				}
			}
			average = total/priceData.size();
		}
	}

	public float getAveragePrice(){
		return average;
	}

	public float getHighPrice(){
		return high;
	}

	public float getLowPrice(){
		return low;
	}

	public int getNumPricePoints(){
		return priceData.size();
	}
}
