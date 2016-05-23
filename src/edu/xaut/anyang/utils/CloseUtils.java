package edu.xaut.anyang.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭Closeable对象工具类
 * 
 * @author anyang
 *
 */
public final class CloseUtils {
	
	public static void close(Closeable closeable) {
		if(null != closeable){
			try {
				closeable.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
