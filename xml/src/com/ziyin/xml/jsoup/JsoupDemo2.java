package com.ziyin.xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * @author ziyin
 @create 2019-05-2019/5/24-6:22
 */
public class JsoupDemo2 {
	public static void main(String[] args) throws IOException {
		String path = JsoupDemo2.class.getClassLoader().getResource("student.xml").getPath();
		Document doc = Jsoup.parse(new File(path), "utf-8");
		Elements names = doc.getElementsByTag("name");
		System.out.println(names.size());

		Element student = doc.getElementsByTag("student").get(0);
		Elements name = student.getElementsByTag("name");
		System.out.println(name.size());

		String number = student.attr("number");
		System.out.println(number);

		String text = name.text();
		String html = name.html();
		System.out.println(text);
		System.out.println("-------------");
		System.out.println(html);
	}
}
