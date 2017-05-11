# react-native-pdfview-android

##### React Native PDF View (support Android only)
> 本项目以原生方式直接下载解析展示pdf

#### 本项目参考了以下项目
> [OkGo](https://github.com/jeasonlzy/okhttp-OkGo)
> 
> [android-pdf-viewer](https://github.com/barteksc/AndroidPdfViewer)

### 集成方式

* npm intsall react-native-pdfview-android

* react-native link react-native-pdfview-android

### demo


	import PdfView from "react-native-pdfview-android";
	/*
	 * String url,
	 * String fileName
	 */
	PdfView.showPdf(url,"协议.pdf");
