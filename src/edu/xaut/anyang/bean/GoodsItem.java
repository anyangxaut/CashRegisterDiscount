package edu.xaut.anyang.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 商品信息，包含：条形码（伪），名称，数量单位，单价和类别。
 * 
 * { 
 *   barcode: 'ITEM000000', 
 *   name: '可口可乐', 
 *   unit: '瓶', 
 *   category: '食品', 
 *   subCategory: '碳酸饮料', 
 *   price: 3.00
 * }
 * 
 * @author anyang
 *
 */
public class GoodsItem{
	// 商品属性信息
	// 商品条形码
	private String barcode;
	// 商品名称
	private String name;
	// 商品数量单位
	private String unit;
	// 商品类别
	private String category;
	// 商品子类别
	private String subCategory;
	// 商品单价
	private double price;

	public GoodsItem(String barcode, String name, String unit, String category, String subCategory,
			double price) {
		// TODO Auto-generated constructor stub
		this.barcode = barcode;
		this.name = name;
		this.unit = unit;
		this.category = category;
		this.subCategory = subCategory;
		this.price = price;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	// 返回对象的JSON格式封装
	public JSONObject toJson(){
		JSONObject object = new JSONObject();
		try {
			object.put("barcode", barcode);
			object.put("name", name);
			object.put("unit", unit);
			object.put("category", category);
			object.put("subCategory", subCategory);
			object.put("price", price);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	} 
	
}
