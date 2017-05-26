package com.yunmin.download;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class PullParser {
    public static ResponseData readXML(String str) throws Exception {
        InputStream is = new ByteArrayInputStream(str.getBytes("utf-8"));
        XmlPullParser xml = Xml.newPullParser();
        xml.setInput(is, "utf-8");
        int type = xml.getEventType();
        ResponseData currentData = null;
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
                    break;
                case XmlPullParser.START_TAG:// 开始元素事件
                    String start_tag = xml.getName();
                    if (start_tag.equals("string")) {
                        currentData = new ResponseData();
                        currentData.setStr(xml.nextText());
                    }
                    break;
                case XmlPullParser.END_TAG:// 结束元素事件
                    String end_tag = xml.getName();
                    if (end_tag.equals("string")) {
                        currentData = null;
                    }
                    break;
            }
            type = xml.next();
        }
        is.close();
        return currentData;
    }
}
