package com.xfsw.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xfsw.common.classes.BusinessException;
import com.xfsw.common.consts.ErrorConstant;

public class MapUtil {

	private static Logger logger = LoggerFactory.getLogger(MapUtil.class);

	public static boolean isEmpty(Map<?, ?> map) {
		if (map == null) {
			return true;
		} else {
			return map.size() == 0;
		}
	}

	public static Object map2Pojo(Map<?, ?> result, Class<?> clazz) {
		Object entity = ReflectUtil.instance(clazz);
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			ReflectUtil.invokeField(entity, field, result.get(field.getName()), true);
		}
		return entity;
	}

	public static <T> T map2Entity(Map<?, ?> mp, Class<T> clazz) {
		String json = JsonUtil.entity2Json(mp);
		return JsonUtil.json2Entity(json, clazz);
//			BeanInfo beanInfo = null;
//			try {
//				beanInfo = Introspector.getBeanInfo(clazz);
//			} catch (IntrospectionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//			T entity = null;
//			try {
//				entity = clazz.newInstance();
//			} catch (InstantiationException | IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			for (PropertyDescriptor property : propertyDescriptors) {
//				String key = property.getName();
//				if (mp.containsKey(key)) {
//					Object value = mp.get(key);
//					// Java中提供了用来访问某个属性的getter/setter方法
//					Method setter = property.getWriteMethod();
//					try {
//						setter.invoke(entity, value);
//					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//						logger.debug(setter.getName());
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//			return entity;
////			throw new BusinessException(ErrorConstant.ERROR_SYSTEM_KNOWN, "Map transform to entity failed!", e);
	}

	public static <T> T obj2Entity(Object obj, Class<T> clazz) {
		try {
			T entity = clazz.newInstance();
			BeanUtils.copyProperties(entity, obj);
			return entity;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
			throw new BusinessException(ErrorConstant.ERROR_SYSTEM_KNOWN, "Object transform to entity failed!", e);
		}
	}

	/**
	 * 将POJO对象转成Map
	 * @param obj	实体类对象
	 * @return	map
	 */
	@Deprecated
	public static Map<String, Object> pojoToMap(Object obj) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		Class<? extends Object> c = obj.getClass();
		Method m[] = c.getDeclaredMethods();

		try {
			for (int i = 0; i < m.length; i++) {
				if (m[i].getName().indexOf("get") == 0) {
					hashMap.put(m[i].getName().substring(3, 4).toLowerCase() + m[i].getName().substring(4), m[i].invoke(obj, new Object[0]));
				}
			}
			if (!"java.lang.Object".equals(c.getSuperclass().getName())) {
				Method sm[] = c.getSuperclass().getDeclaredMethods();

				for (int i = 0; i < sm.length; i++) {
					if (sm[i].getName().indexOf("get") == 0) {
						hashMap.put(sm[i].getName().substring(3, 4).toLowerCase() + sm[i].getName().substring(4), sm[i].invoke(obj, new Object[0]));
					}
				}
			}
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("pojoToMap反射类 " + obj.getClass().getName() + " 方法参数异常IllegalArgumentException", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("pojoToMap反射类 " + obj.getClass().getName() + " 方法作用域异常IllegalAccessException", e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("pojoToMap反射类 " + obj.getClass().getName() + " 方法执行异常InvocationTargetException", e);
		}
		return hashMap;
	}

	/**
	 * 获取entity中不为null的字段名称和字段值
	 * @param entity	实体类对象
	 * @return	map
	 */
	@Deprecated
	public static Map<String, Object> pojoToMapNotNullField(Object entity) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer methodName = null;
		Object val = null;
		Class<? extends Object> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// 跳過static類型的變量
			if (Modifier.isStatic(field.getModifiers()))
				continue;
			methodName = new StringBuffer();
			methodName.append("get").append(StringUtil.initialFirstUppercase(field.getName()));
			val = ReflectUtil.invoke(entity, methodName.toString());
			if (val != null)
				map.put(field.getName(), val);
		}
		return map;
	}

	/**
	 * 获取entity中不为null的字段名称和字段值
	 * @param entity	实体类对象
	 * @return	sortmap
	 */
	public static SortedMap<String, Object> pojoToSortedMapNotNullField(Object entity) {
		SortedMap<String, Object> map = new TreeMap<String, Object>();
		StringBuffer methodName = null;
		Object val = null;
		Class<? extends Object> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		logger.debug("类递归加载到map当中----------------方法名称列表-----------");
		for (Field field : fields) {
			methodName = new StringBuffer();
			methodName.append("get").append(StringUtil.initialFirstUppercase(field.getName()));
			val = ReflectUtil.invoke(entity, methodName.toString());
			if (val != null) {
				logger.debug("字段名称：" + field.getName() + ",方法名称：" + methodName.toString());
				map.put(field.getName(), val);
			}
		}
		logger.debug("类递归加载到map结束---------------------------------");
		return map;
	}

	/**
	 * 实体类转换成执行存储过程的map,map的key默认前缀带$
	 * @param entity	实体类对象
	 * @return	map
	 */
	public static Map<String, Object> entity2ProcedureParams(Object entity) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer methodName = null;
		Object val = null;
		Class<? extends Object> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			methodName = new StringBuffer();
			methodName.append("get").append(StringUtil.initialFirstUppercase(field.getName()));
			val = ReflectUtil.invoke(entity, methodName.toString());
			map.put(field.getName(), val);
		}
		return map;
	}

	/**
	 * 实体类(不为null的字段)转换成执行存储过程的map,map的key默认前缀带$
	 * @param entity	实体类对象
	 * @return	map
	 */
	public static Map<String, Object> entity2ProcedureParamsNotNull(Object entity) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer methodName = null;
		Object val = null;
		Class<? extends Object> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			methodName = new StringBuffer();
			methodName.append("get").append(StringUtil.initialFirstUppercase(field.getName()));
			val = ReflectUtil.invoke(entity, methodName.toString());
			if (val != null)
				map.put("$" + field.getName(), val);
		}
		return map;
	}

	/**
	 * list转换成map，key为list中的下标（从1开始）
	 * @param list	数组列表
	 * @return	map
	 */
	public static Map<String, Object> list2Map(List<Object> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (list != null)
			for (int i = 0, j = 0; i < list.size(); i++, j++)
				map.put(String.valueOf(j), list.get(i));
		return map;
	}

	/**
	 * 使用 Map按key进行排序
	 * @param map	map
	 * @return	map(排序之后)
	 */
	public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, Object> sortMap = new TreeMap<String, Object>(new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}

	public static Map<String, Object> entityToMap(Object bean) {
		Class<? extends Object> clazz = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = null;
					result = readMethod.invoke(bean, new Object[0]);
					returnMap.put(propertyName, result);
				}
			}
			return returnMap;
		} catch (IntrospectionException e) {
			throw new RuntimeException("分析类属性失败 ", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("实例化 JavaBean 失败 ", e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("映射错误 ", e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("调用属性的 setter 方法失败 ", e);
		}
	}
	
	public static Map<String, Object> entityToMapInNotNullField(Object bean) {
		Class<? extends Object> clazz = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = null;
					result = readMethod.invoke(bean, new Object[0]);
					if(result!=null)
						returnMap.put(propertyName, result);
				}
			}
			return returnMap;
		} catch (IntrospectionException e) {
			throw new RuntimeException("分析类属性失败 ", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("实例化 JavaBean 失败 ", e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("映射错误 ", e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("调用属性的 setter 方法失败 ", e);
		}
	}
}

// 比较器类
class MapKeyComparator implements Comparator<String> {
	public int compare(String str1, String str2) {
		return str1.compareTo(str2);
	}
}