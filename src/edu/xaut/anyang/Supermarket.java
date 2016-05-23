package edu.xaut.anyang;

import org.json.JSONObject;

import edu.xaut.anyang.utils.FileUtils;

public class Supermarket {
	// 超市商品列表
	public JSONObject supermarketGoods;
	// 单例模式---只有一个超市
	private volatile static Supermarket supermarketInstance = null;

	private Supermarket() {
		// TODO Auto-generated constructor stub
		// 初始化超市商品列表
		supermarketGoods = initGoods();
	}
	
	// 从文件读取超市商品信息
	private JSONObject initGoods(){
		return FileUtils.supermarketFileParse(CashRegisterDiscount.PROJECTPATH + "\\data\\supermarket.txt");
	}
	
	public static Supermarket getInstance(){
		// 避免不必要的同步
		if(supermarketInstance == null){
			synchronized(Supermarket.class) {
				// 保证singleInstance为null的情况下创建实例
				if(supermarketInstance == null){
					supermarketInstance = new Supermarket();
				} 
			}
		}
		return supermarketInstance;
	}

}
