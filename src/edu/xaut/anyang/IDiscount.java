package edu.xaut.anyang;

import org.json.JSONObject;
/**
 * 折扣接口类---用于扩展产生各种折扣活动，也可以进行依赖注入
 * @author anyang
 *
 */
public interface IDiscount {
	// 设置折扣信息
	public JSONObject putDiscount();
}
