package easy.utility;

import java.util.function.Function;

/*
 * 空对象帮助类
 */
public class NullHelper {
	private NullHelper() {

	}
	/*
	 * 如果指定对象为空，则返回默认值
	 */
	public static <T> T ifNull(T obj, T defualt) {
		if(obj == null){
			return defualt;
		}
		return obj;
	}
	
	/**
	 * 空引用访问帮助方法
	 * @param obj
	 * @param defaultValue
	 * @param func
	 * @return
	 */
	public static  <T,R> R ifNull(T obj,R defaultValue,Function<T,R> func){
		if(obj == null){
			return defaultValue;
		}
		return func.apply(obj);
	}
}
