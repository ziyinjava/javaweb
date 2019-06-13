package cn.itcast.test;

import cn.itcast.domain.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ziyin
 @create 2019-06-2019/6/5-14:36
 */
public class MyTest {


	@Test
	public void test1() throws Exception {
		Person p  = new Person();
		p.setName("张三");
		p.setAge(23);
		p.setGender("男");

		ObjectMapper om = new ObjectMapper();

		String s = om.writeValueAsString(p);
		//System.out.println(s);


		om.writeValue(new FileWriter("F://a.txt"),p);
		FileOutputStream fos = new FileOutputStream("b.txt");
		om.writeValue(fos,p);

		FileInputStream fis = new FileInputStream("b.txt");
		byte[] bytes = new byte[1024];
		int len = 0;
		while ((len = fis.read(bytes)) != -1) {
			System.out.println(new String(bytes,0,len));
		}

	}

	@Test
	public void test2() throws IOException {
		String json = "{\"gender\":\"男\",\"name\":\"张三\",\"age\":23}";
		ObjectMapper om = new ObjectMapper();
		Person person = om.readValue(json, Person.class);
		System.out.println(person);
	}


}
