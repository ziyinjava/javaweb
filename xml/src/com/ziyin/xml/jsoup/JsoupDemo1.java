package com.ziyin.xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * @author ziyin
 @create 2019-05-2019/5/23-20:15
 */
public class JsoupDemo1 {

	public static void main(String[] args) throws IOException {
		String path = JsoupDemo1.class.getClassLoader().getResource("student.xml").getPath();
		Document doc = Jsoup.parse(new File(path), "utf-8");
		Elements elements = doc.getElementsByTag("name");
		System.out.println(elements.size());
		Element element = elements.get(0);
		System.out.println(element.text());
	}
}
