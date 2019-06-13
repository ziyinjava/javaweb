package com.ziyin.xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * @author ziyin
 @create 2019-05-2019/5/24-6:22
 */
public class JsoupDemo3 {
	public static void main(String[] args) throws IOException {
		String path = JsoupDemo3.class.getClassLoader().getResource("student.xml").getPath();
		Document doc = Jsoup.parse(new File(path), "utf-8");

		Elements names = doc.select("name");
		System.out.println(names);
		System.out.println("------------------");

		Elements ele = doc.select("#itcast");
		System.out.println(ele);

		System.out.println("--------222222------------");
		Elements e = doc.select("student[number='heima_0001']");
		System.out.println(e);
	}

}
