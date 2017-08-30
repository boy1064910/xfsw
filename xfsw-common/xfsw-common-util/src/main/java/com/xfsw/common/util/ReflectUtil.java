package com.xfsw.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ReflectUtil {

	private static Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

	public static Class<?> forName(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("反射类 " + className + "找不到此类!",e);
		}
	}

	public static Object instance(Class<?> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			logger.error("反射类 " + clazz.getName() + "实例化失败", e);
			throw new RuntimeException("反射类 " + clazz.getName() + "实例化失败", e);
		} catch (IllegalAccessException e) {
			logger.error("反射类 " + clazz.getName() + " 类作用域异常IllegalAccessException", e);
			throw new RuntimeException("反射类 " + clazz.getName() + " 类作用域异常IllegalAccessException", e);
		}
	}

	public static Object instance(String className) {
		try {
			return Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			logger.error("反射类 " + className + "实例化失败", e);
			throw new RuntimeException("反射类 " + className + "实例化失败", e);
		} catch (IllegalAccessException e) {
			logger.error("反射类 " + className + " 类作用域异常IllegalAccessException", e);
			throw new RuntimeException("反射类 " + className + " 类作用域异常IllegalAccessException", e);
		} catch (ClassNotFoundException e) {
			logger.error("反射类 " + className + " 找不到类", e);
			throw new RuntimeException("反射类 " + className + " 找不到类", e);
		}
	}

	@SuppressWarnings("null")
	public static Object invoke(String className, String methodName) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
			Object obj = clazz.newInstance();
			Method method = clazz.getMethod(methodName);
			return method.invoke(obj);
		} catch (IllegalAccessException e) {
			logger.error("反射类 " + clazz.getName() + " 方法作用域异常IllegalAccessException:" + methodName, e);
			throw new RuntimeException("反射类 " + clazz.getName() + " 方法作用域异常IllegalAccessException:" + methodName, e);
		} catch (NoSuchMethodException e) {
			logger.error("反射类 " + clazz.getName() + " 找不到方法:" + methodName, e);
			throw new RuntimeException("反射类 " + clazz.getName() + " 找不到方法:" + methodName, e);
		} catch (SecurityException e) {
			logger.error("反射类 " + clazz.getName() + " 方法作用域异常SecurityException:" + methodName, e);
			throw new RuntimeException("反射类 " + clazz.getName() + " 方法作用域异常SecurityException:" + methodName, e);
		} catch (IllegalArgumentException e) {
			logger.error("反射类 " + clazz.getName() + " 方法参数异常IllegalArgumentException:" + methodName, e);
			throw new RuntimeException("反射类 " + clazz.getName() + " 方法参数异常IllegalArgumentException:" + methodName, e);
		} catch (InvocationTargetException e) {
			logger.error("反射类 " + clazz.getName() + " 方法执行异常InvocationTargetException:" + methodName, e);
			throw new RuntimeException("反射类 " + clazz.getName() + " 方法执行异常InvocationTargetException:" + methodName, e);
		} catch (ClassNotFoundException e) {
			logger.error("反射类 " + clazz.getName() + "找不到类文件", e);
			throw new RuntimeException("反射类 " + clazz.getName() + "找不到类文件", e);
		} catch (InstantiationException e) {
			logger.error("反射类 " + clazz.getName() + "实例化失败", e);
			throw new RuntimeException("反射类 " + clazz.getName() + "实例化失败", e);
		}
	}

	/**
	 * java 反射执行方法 ,methodName带参数,只有一个参数,并且为String类型
	 * @param className	类名
	 * @param methodName	方法名
	 * @param methodParam	方法参数值
	 * @return	对象
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月4日下午9:14:38
	 */
	@SuppressWarnings("null")
	public static Object invoke(String className, String methodName, String methodParam) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
			Object obj = clazz.newInstance();
			Method method = clazz.getMethod(methodName, methodParam.getClass());
			return method.invoke(obj, methodParam);
		} catch (IllegalAccessException e) {
			logger.error("反射类 " + clazz.getName() + " 方法作用域异常IllegalAccessException:" + methodName, e);
			throw new RuntimeException("反射类 " + clazz.getName() + " 方法作用域异常IllegalAccessException:" + methodName, e);
		} catch (NoSuchMethodException e) {
			logger.error("反射类 " + clazz.getName() + " 找不到方法:" + methodName, e);
			throw new RuntimeException("反射类 " + clazz.getName() + " 找不到方法:" + methodName, e);
		} catch (SecurityException e) {
			logger.error("反射类 " + clazz.getName() + " 方法作用域异常SecurityException:" + methodName, e);
			throw new RuntimeException("反射类 " + clazz.getName() + " 方法作用域异常SecurityException:" + methodName, e);
		} catch (IllegalArgumentException e) {
			logger.error("反射类 " + clazz.getName() + " 方法参数异常IllegalArgumentException:" + methodName, e);
			throw new RuntimeException("反射类 " + clazz.getName() + " 方法参数异常IllegalArgumentException:" + methodName, e);
		} catch (InvocationTargetException e) {
			logger.error("反射类 " + clazz.getName() + " 方法执行异常InvocationTargetException:" + methodName, e);
			throw new RuntimeException("反射类 " + clazz.getName() + " 方法执行异常InvocationTargetException:" + methodName, e);
		} catch (ClassNotFoundException e) {
			logger.error("反射类 " + clazz.getName() + "找不到类文件", e);
			throw new RuntimeException("反射类 " + clazz.getName() + "找不到类文件", e);
		} catch (InstantiationException e) {
			logger.error("反射类 " + clazz.getName() + "实例化失败", e);
			throw new RuntimeException("反射类 " + clazz.getName() + "实例化失败", e);
		}
	}

	/**
	 * java 反射执行方法 ,methodName不带参数
	 * @param obj	对象
	 * @param methodName	方法名
	 * @return	对象反射执行的方法得到的值
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月4日下午9:15:15
	 */
	public static Object invoke(Object obj, String methodName) {
		try {
			Method method = obj.getClass().getMethod(methodName);
			if (method != null) {
				return method.invoke(obj);
			} else {
				return null;
			}
		} catch (IllegalAccessException e) {
			logger.error("反射类 " + obj.getClass().getName() + " 方法作用域异常IllegalAccessException:" + methodName, e);
			throw new RuntimeException("反射类 " + obj.getClass().getName() + " 方法作用域异常IllegalAccessException:" + methodName, e);
		} catch (NoSuchMethodException e) {
			logger.error("反射类 " + obj.getClass().getName() + " 找不到方法:" + methodName, e);
			throw new RuntimeException("反射类 " + obj.getClass().getName() + " 找不到方法:" + methodName, e);
		} catch (SecurityException e) {
			logger.error("反射类 " + obj.getClass().getName() + " 方法作用域异常SecurityException:" + methodName, e);
			throw new RuntimeException("反射类 " + obj.getClass().getName() + " 方法作用域异常SecurityException:" + methodName, e);
		} catch (IllegalArgumentException e) {
			logger.error("反射类 " + obj.getClass().getName() + " 方法参数异常IllegalArgumentException:" + methodName, e);
			throw new RuntimeException("反射类 " + obj.getClass().getName() + " 方法参数异常IllegalArgumentException:" + methodName, e);
		} catch (InvocationTargetException e) {
			logger.error("反射类 " + obj.getClass().getName() + " 方法执行异常InvocationTargetException:" + methodName, e);
			throw new RuntimeException("反射类 " + obj.getClass().getName() + " 方法执行异常InvocationTargetException:" + methodName, e);
		}
	}

	public static Object invoke(Object obj, String methodName, Object methodParam) {
		try {
			Method method = obj.getClass().getMethod(methodName, methodParam.getClass());
			return method.invoke(obj, methodParam);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RuntimeException("反射执行方法失败，methodName："+methodName+",methodParam："+(methodParam==null?"":methodParam.toString()), e);
		}
	}

	public static void invokeNotNull(Object obj, String methodName, Object methodParam) throws Exception {
		Method method = obj.getClass().getMethod(methodName, methodParam.getClass());
		if (method != null) {
			method.invoke(obj, methodParam);
		}
	}

	public static void invokeField(Object entity, Field field, Object value, boolean accessible) {
		if(Modifier.isStatic(field.getModifiers()))
			return;
		if(Modifier.isFinal(field.getModifiers())){
			return;
		}
		field.setAccessible(accessible);
		try {
			//TODO 后续调整关于类型转换的逻辑
			if(field.getType().getName().equals("java.math.BigInteger")&&value instanceof java.lang.Long){
				BigInteger bigValue = BigInteger.valueOf(Long.valueOf(value.toString()));
				field.set(entity, bigValue);
			}
//			else if(field.getType().getName().equals("java.math.BigDecimal")&&value instanceof java.lang.Double){
//				Double doubleValue = Double.valueOf(value.toString());
//				BigDecimal bigValue = BigDecimal.valueOf(doubleValue);
//				field.set(entity, bigValue);
//			}
			else{
				field.set(entity, value);
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException("反射类 " + field.getName() + " 字段赋值异常", e);
		}
	}

	/**
	 * 复制对象source的属性值到target的属性值中
	 * @param source	源
	 * @param target	目标
	 * @param isNotNull	空值是否复制
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月4日下午9:15:33
	 */
	public static void copyValue(Object source, Object target, boolean isNotNull) {
		Class<?> sourceClass = source.getClass();
		Class<?> targetClass = target.getClass();
		Field[] fs = targetClass.getDeclaredFields();
		try {
			for (int i = 0; i < fs.length; i++) {
				Field f = (Field) fs[i];
				Method setm = null;
				Method getm;
				try {
					getm = sourceClass.getMethod("get" + StringUtil.initialFirstUppercase(f.getName()));
					Object o = getm.invoke(source);
					if (isNotNull) {// 空数据不复制
						if (o != null) {
							setm = targetClass.getMethod("set" + StringUtil.initialFirstUppercase(f.getName()), f.getType());
							setm.invoke(target, o);
						}
					} else {// 整体复制
						setm = targetClass.getMethod("set" + StringUtil.initialFirstUppercase(f.getName()), f.getType());
						setm.invoke(target, o);
					}
				} catch (NoSuchMethodException e) {
					continue;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("复制对象source的属性值到target的属性值中, " + source.getClass() + " 执行异常.", e);
		}
	}
}
