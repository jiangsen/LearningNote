
- equals与==的区别
  
		== 用于比较对象的相等性，两个对象引用相同则为相等
		equals 则表示对象之间的同一性，是Object的成员函数，有些类会重载该方法。用于判断等价性。
		例如String "abc",它们可能有不同的引用，那么==结果则为false而equal是相等的
	    具体可以参考下图：
	![](https://i.imgur.com/ixkggog.png)

- Switch能否使用String做参数

		可以，Switch可以用做可以转换为int的（Byte， char， int），String以及枚举类型
		不可用用Switch为Boolean double float long（long不可以直接转换为对象）

-  Object有哪些公用方法
	
		https://www.cnblogs.com/zhousysu/p/5483795.html

- Hashcode的作用

		https://blog.csdn.net/SEU_Calvin/article/details/52094115

- Excption与Error包结构。OOM你遇到过哪些情况，SOF你遇到过哪些情况
	
		https://www.jianshu.com/p/473c7b8b3bf3
		 