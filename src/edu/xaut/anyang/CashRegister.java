package edu.xaut.anyang;

import java.text.DecimalFormat;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import edu.xaut.anyang.bean.GoodsItem;
import edu.xaut.anyang.utils.FileUtils;

/**
 * 超市收银机---我们可以对收银机进行设置，使之支持各种优惠。
 * 
 * 需要实现一个名为打印小票的小模块，收银机会将输入的数据转换成一个JSON数据，然后一次性传给我们这个小模块，我们将从控制台中输出结算清单的文本。
 * 
 * @author anyang
 *
 */
public class CashRegister {
	
	// 默认折扣---单品打折
	private IDiscount discount = new DefaultDiscount();
	
	// 设置优惠信息
	public void setDiscount(IDiscount discount) {
		this.discount = discount;
	}
	
	// 获取购物车商品信息(商品条码，商品数量)
	public JSONObject getShoppingCartGoods(String filePath){
		return FileUtils.shoppingCartFileParse(filePath);
	}
	
	// 根据折扣信息计算商品价格
	public StringBuilder calculatePrice(Supermarket supermarket, JSONObject shoppingCart){
		// 默认折扣---单品打折
		JSONObject discountJsonObject = discount.putDiscount();
		// 超市商品列表
		JSONObject supermarketGoods = supermarket.supermarketGoods;
		// 小数点后保留两位
		DecimalFormat df = new DecimalFormat("#.00");
		// 小票构建器
		StringBuilder receiptBuilder = new StringBuilder();
		StringBuilder receiptDiscountBuilder = new StringBuilder();
		// 初始化小票标题
		receiptBuilder.append("***<没钱赚商店>购物清单***\n");
		receiptDiscountBuilder.append("----------------------\n");
		receiptDiscountBuilder.append("单品打折商品：\n");
		// 总计价格
		double sumPrice = 0;
		// 节省价格
		double saveSumPrice = 0;
		// 当前购物列表是否含有打折商品
		boolean discountFlag = false;
		// 遍历购物车物品
		@SuppressWarnings("unchecked")
		Iterator<String> ketSet = shoppingCart.keys();
		while(ketSet.hasNext()){
			String key = (String) ketSet.next();
			try {
				// 条码为key，条码所对应的商品数量为value
				int number = shoppingCart.getInt(key);
				// 商品有优惠活动
				if(discountJsonObject.has(key)) {
					// 当前购物列表包含打折商品
					discountFlag = true;
					GoodsItem goodItem = (GoodsItem) supermarketGoods.get(key);
					double discountPercent = (double) discountJsonObject.getInt(key) / 100;
					double discountPrice = number * goodItem.getPrice() * discountPercent;
					double savePrice = number * goodItem.getPrice() * (1 - discountPercent);  
					receiptBuilder.append("名称：" + goodItem.getName() + "，数量：" + number + goodItem.getUnit() + 
							"，单价：" + goodItem.getPrice() + "(元)，小计：" + df.format(discountPrice) + "(元)，优惠 "
							+ df.format(savePrice) + "(元)\n");
					receiptDiscountBuilder.append("名称：" + goodItem.getName() + "，折扣：" + 
							discountJsonObject.getInt(key) + "折\n");
					sumPrice += discountPrice;
					saveSumPrice += savePrice;
				}else{
					// 商品无优惠活动
					GoodsItem goodItem = (GoodsItem) supermarketGoods.get(key);
					double originPrice = number * goodItem.getPrice();
					sumPrice += originPrice;
					receiptBuilder.append("名称：" + goodItem.getName() + "，数量：" + number + goodItem.getUnit() + 
							"，单价：" + goodItem.getPrice() + "(元)，小计：" + df.format(originPrice) + "(元)\n");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(discountFlag){
			receiptBuilder.append(receiptDiscountBuilder.toString());
		}
		receiptBuilder.append("----------------------\n");
		receiptBuilder.append("总计：" + df.format(sumPrice) + "(元)\n");
		if(discountFlag){
			receiptBuilder.append("节省：" + df.format(saveSumPrice) + "(元)\n");
		}
		receiptBuilder.append("**********************\n");
		return receiptBuilder;
	}
	
	// 打印小票
	public void printReceipt(Supermarket supermarket, JSONObject shoppingCart){
		// 计算商品价格
		StringBuilder receiptBuilder = calculatePrice(supermarket, shoppingCart);
		// 打印小票
		System.out.print(receiptBuilder.toString());
	}
	
	
}




