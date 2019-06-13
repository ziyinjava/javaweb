package com.ziyin.xml.jsoup;

/**
 * @author ziyin
 @create 2019-05-2019/5/30-15:06

 /C程序设计第四版（谭浩强）
 //章节：第八章 善于利用指针
 //题号：8.5
 //题目:有n个人围成一圈，顺序排号。从第一个人开始报数（从1到3报数），
 //凡报到3的人退出圈子，问最后留下的是原来第几号的那位。
 #include <stdio.h>
 void pick(int *p,int n)
 {
 int i,cnt,sum;
 for(i=0;i<n;i++)
  *(p+i)=1;		//起始时所有位置置为1,1表示有人
 i=0;
 cnt=0;	//cnt表示报数器
 sum=n;	//sum表示在场的人数
 while(sum>1)
 {
 if(*(p+i)==1)
 {
 cnt++;
 if(cnt%3==0)
 {
  *(p+i)=0;		//0表示该位置没有人
 cnt=0;		//报数器置0，重新报数
 sum--;		//在场的人数-1
 }
 }
 i++;
 i=i%n;	//取模运算，从头开始报数
 }
 for(i=0;i<n;i++)
 if(*(p+i)==1)
 printf("Position:%d",i+1);
 }
 int main()
 {
 int n,a[100];
 int *p=a;
 printf("how many people:\n");
 scanf("%d",&n);
 pick(p,n);
 return 0;
 }
 ---------------------
 作者：全幼儿园最聪明
 来源：CSDN
 原文：https://blog.csdn.net/weixin_44589540/article/details/86668866
 版权声明：本文为博主原创文章，转载请附上博文链接！
 */

public class Test {
	public int add(){
		 int i = 0;
		i++;
		return i;
	}

		}
class Child extends Parent {

	private String deparentment;
	public String getValue(){
		//return name;
		return null;
	}

}


class Parent {
	private String name;
	public Parent(){}
}