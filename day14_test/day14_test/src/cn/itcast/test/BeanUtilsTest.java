package cn.itcast.test;

import cn.itcast.domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class BeanUtilsTest {

	@Test
	public void test() {
		User user = new User();
		try {
			BeanUtils.setProperty(user, "hehe", "male");
			System.out.println(user);

			String gender = BeanUtils.getProperty(user, "hehe");
			System.out.println(gender);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testPopulate(){
		Map<String,String> map = new HashMap<>();
		map.put("a","b");
		map.put("password","123");
		User user = new User();
		try {
			BeanUtils.populate(user,map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
}
