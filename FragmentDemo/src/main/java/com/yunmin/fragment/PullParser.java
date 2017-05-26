package com.yunmin.fragment;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by luoyu on 2016/3/23.
 */
public class PullParser {

    public static void parserWeb(String str) {
        try {
            InputStream is = new ByteArrayInputStream(str.getBytes("utf-8"));
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(is, "utf-8");
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        String tagName = parser.getName();
                        Log.e("lym",tagName);
                        if ("div".equals(tagName)) {
                            Log.e("lym", "0");
                        } else if ("img".equals(tagName)) {
                            Log.e("lym", "0");
                        } else if ("p".equals(tagName)) {
                            Log.e("lym", "0");
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                event = parser.next();
            }
            is.close();
        } catch (XmlPullParserException e) {
            Log.e("lym","e");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String safeNextText(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        String result = parser.nextText();
        if (parser.getEventType() != XmlPullParser.END_TAG) {
            parser.nextTag();
        }
        return result;
    }
}
