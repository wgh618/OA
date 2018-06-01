package com.my.oa.core.config;

/**
 * 对应配置的对象（对应default.properties）
 *
 * Created by Administrator.
 *
 * @author 吴光辉
 * 
 */
public class Configuration {

	private static int pageSize;

	static {
		// TODO 读取配置default.properties文件，并初始化所有配置
		pageSize = 10;
	}

	public static int getPageSize() {
		return pageSize;
	}

}
