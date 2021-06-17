XImg
=====
[![](https://jitpack.io/v/LucasXu01/XImg.svg)](https://jitpack.io/#LucasXu01/XImg)

Simple package library for image loader

Download
--------
use Gradle:

Step 1. Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```gradle
dependencies {
	        implementation 'com.github.LucasXu01:XImg:0.0.3'
	}
```

How do I use ?
-------------------
```java
 XImg.getIns().loadImg(img_view, "图片地址");
```

* ### 相关方法
```
loadImg                         : 加载图
loadImgCircle                   : 加载圆形图（可设置全圆、边角弧度、间距）
loadImgBlur                     : 加载高斯模糊图
loadImg4ViewGroup               : 为ViewGroup加载图片
loadImg4ViewGroupBlur           : 为ViewGroup加载模糊图片
loadImg4Target                  : 为非view加载图片
loadImg4Notification            : 为notification加载图片
```
