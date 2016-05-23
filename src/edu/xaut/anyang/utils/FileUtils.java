package edu.xaut.anyang.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

import edu.xaut.anyang.bean.GoodsItem;

/**
 * 文件读取工具类
 * 
 * @author anyang
 *
 */
public final class FileUtils {

	private FileUtils() { }
	
	/**
	 * 将购物车列表转换为Json格式，并进行数量统计。输入列表分为两种模式：（1）扫描仪一次扫一个条码，扫多次；（2）扫描仪只扫一次条码，然后手动输入数量
	 * @param filePath	购物车商品文件---模拟用户购物车
	 * @return	以键值对的形式返回购物商品条码及其数量，即在JSONObject中以商品条码为key，商品数量为value进行存储，例如{"ITEM000005":3,"ITEM000003":2}
	 */
	public static JSONObject shoppingCartFileParse(String filePath){
		// 创建存储信息的json对象
		JSONObject inputDataJsonFormat = new JSONObject();
		// 根据文件目录及其名称创建File对象
		File file = new File(filePath);		
		// 判断文件是否存在，且其属性是否为file
		if(file.exists() && file.isFile()){ 
			BufferedReader bufferedReader = null;
			String lineData = "";
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
				// 逐行从文件中读取输入数据
				while((lineData = bufferedReader.readLine()) != null){
					// 区分第二种模式（扫描仪只扫一次条码，然后手动输入数量）---对'ITEM000003-2'来说，"-"之前的是标准的条形码，"-"之后的是数量。
					if(lineData.contains("-")){
						String[] temp = lineData.split("-");
						inputDataJsonFormat.put(temp[0], Integer.parseInt(temp[1]));
					}else{
						// 区分第一种模式（扫描仪一次扫一个条码，扫多次），如果之前已经扫描过一次该商品的条码，则直接将数量加1，否则创建新的条码记录
						if(inputDataJsonFormat.has(lineData)){
							int number = inputDataJsonFormat.getInt(lineData) + 1;
							inputDataJsonFormat.put(lineData, number);
						}else{
							inputDataJsonFormat.put(lineData, 1);
						}
					}		
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				CloseUtils.close(bufferedReader);
			}		
		}	
		return inputDataJsonFormat;
	}	
	
	/**
	 * 从文件读取折扣信息
	 * @param filePath	优惠信息文件---模拟超市打折
	 * @return	以键值对的形式返回优惠商品条码及其折扣比例，即在JSONObject中以优惠商品条码为key，商品折扣比例为value进行存储，例如{"ITEM000005":97}
	 */
	public static JSONObject discountFileParse(String filePath){
		// 创建存储信息的json对象
		JSONObject inputDataJsonFormat = new JSONObject();
		// 根据文件目录及其名称创建File对象
		File file = new File(filePath);		
		// 判断文件是否存在，且其属性是否为file
		if(file.exists() && file.isFile()){ 
			BufferedReader bufferedReader = null;
			String lineData = "";
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
				// 逐行从文件中读取输入数据
				while((lineData = bufferedReader.readLine()) != null){
					String[] temp = lineData.split(",");
					inputDataJsonFormat.put(temp[0], Integer.parseInt(temp[1]));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				CloseUtils.close(bufferedReader);
			}		
		}	
		return inputDataJsonFormat;
	}
	
	/**
	 * 从文件读取超市所有商品信息
	 * @param filePath	超市商品列表---模拟超市
	 * @return	以键值对的形式返回超市商品列表，即在JSONObject中以商品条码为key，商品属性信息为value进行存储
	 */
	public static JSONObject supermarketFileParse(String filePath){
		// 超市商品清单
		JSONObject supermarket = new JSONObject();
		// 根据文件目录及其名称创建File对象
		File file = new File(filePath);		
		// 判断文件是否存在，且其属性是否为file
		if(file.exists() && file.isFile()){ 
			BufferedReader bufferedReader = null;
			String lineData = "";
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
				// 逐行从文件中读取输入数据
				while((lineData = bufferedReader.readLine()) != null){
					String[] temp = lineData.split(",");
					// 存储商品信息
					supermarket.put(temp[0], new GoodsItem(temp[0], temp[1], temp[2], temp[3], temp[4], 
							Double.parseDouble(temp[5])));
				}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					CloseUtils.close(bufferedReader);
				}		
			}	
		return supermarket;
	}
	
}
