package edu.xaut.anyang;

import org.json.JSONObject;


/**
 * 收银机 - G1 - 打折
 * 
 * 商店里进行购物结算时会使用收银机系统，这台收银机会在结算时根据客户的购物车中的商品和商店正在进行的优惠活动进行结算和打印购物小票。
 * 
 * @author anyang
 *
 */
public class CashRegisterDiscount {
	
	// 项目根目录路径，各种模拟输入文件均存储在该目录下的data文件夹下
	public final static String PROJECTPATH = System.getProperty("user.dir");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 创建超市类，从文件初始化超市商品
		Supermarket supermarket = Supermarket.getInstance();
		// 创建收银机类
		CashRegister cashRegister = new CashRegister();
		// 收银机接收输入数据
		JSONObject shoppingCart = cashRegister.getShoppingCartGoods(PROJECTPATH + "\\data\\shoppingCart.txt");
		// 自定义折扣信息举例,只需新建折扣文件即可
//		cashRegister.setDiscount(new IDiscount() {
//
//			public JSONObject putDiscount() {
//				// TODO Auto-generated method stub
//				return FileUtils.discountFileParse(CashRegisterDiscount.PROJECTPATH + "\\data\\discountTest.txt");
//			}
//		});
		// 收银机打印购物小票
		cashRegister.printReceipt(supermarket, shoppingCart);
	}

}
