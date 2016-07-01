****************************************************
说明文档
****************************************************

## 文件说明

1. data文件夹中的三个文件分别代表:
		discount.txt:打折信息文件，模拟打折活动，文件格式为商品条码，折扣比例，即ITEM000001,95，其中95代表九五折。
		shoppingCart.txt:购物车文件，模拟用户购物商品，文件格式为ITEM000001或ITEM000003-2，即输入列表分为两种模式：（1）扫描仪一次扫一个条码，扫多次；（2）扫描仪只扫一次条码，然后手动输入数量。
		supermarket.txt:超市商品列表文件，模拟超市，文件格式为条形码（伪），名称，数量单位，类别，子类别和单价，即ITEM000000,可口可乐,瓶,食品,碳酸饮料,3.00。

2. 项目包说明：
		包edu.xaut.anyang.bean下存放商品实体类GoodsItem。
		包edu.xaut.anyang.utils下存放工具类CloseUtils和FileUtils。
		包edu.xaut.anyang下存放主要文件，包括收银机类，打折类等。

## 用法说明

设置data文件夹下discount，shoppingCart和supermarket三个文件，分别模拟打折活动，用户购物车和超市，然后执行即可在控制台得到输出小票信息。若店员需要设置优惠活动，
则可以通过在CashRegisterDiscount类的main方法内加入下面的代码即可，其中discountFileParse方法的参数为优惠活动文件路径。
		cashRegister.setDiscount(new IDiscount() {

			public JSONObject putDiscount() {
				// TODO Auto-generated method stub
				return FileUtils.discountFileParse(CashRegisterDiscount.PROJECTPATH + "\\data\\discountTest.txt");
			}
		});
		
## Github项目链接

https://github.com/anyangxaut/CashRegisterDiscount.git


