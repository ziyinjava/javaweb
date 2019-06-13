package com.ziyin.xml.jsoup;

/**
 * @author ziyin
 @create 2019-05-2019/5/30-15:18
 */
public class Example {
	String str = "good";
	char[] ch = {'a','b','c'};

	public static void main(String[] args) {
		Example ex = new Example();
		ex.change(ex.str,ex.ch);
		System.out.println(ex.str + "and");
		System.out.println(ex.ch);
	}

	public void change(String str,char[] ch){
		str = "test ok";
		ch[1] = 'g';
	}

}
