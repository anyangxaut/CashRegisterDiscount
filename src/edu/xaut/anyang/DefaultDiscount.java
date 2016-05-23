package edu.xaut.anyang;

import org.json.JSONObject;

import edu.xaut.anyang.utils.FileUtils;
/**
 * 默认折扣信息---单品打折
 * 
 * "单品打折"是指，某一商品的总价会打完折再收取。店员设置该优惠，设置哪些条形码对应的商品可以享受此优惠以及折扣的比例。根据输入和设置的不同，输出小票。
 * 
 * @author anyang
 *
 */
public class DefaultDiscount implements IDiscount {

	// 从文件读取单品打折优惠信息，即哪些条形码对应的商品可以享受此优惠以及折扣的比例
	public JSONObject putDiscount() {
		// TODO Auto-generated method stub
		return FileUtils.discountFileParse(CashRegisterDiscount.PROJECTPATH + "\\data\\discount.txt");
	}
}
