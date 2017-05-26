package com.yunmin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MainActivity extends AppCompatActivity {

    RadioButton radio1, radio2, radio3, radio4;

    String str = "[{\"Nid\":101,\"Ntittle\":\"期刊杂志类\",\"Ncontent\":\"\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t期刊杂志类\\n\\u003c/div\\u003e\\n\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t\\u003cimg style=\\\"margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/admin/JS/kindeditor-4.1.7/attached/image/20160301/20160301150937_7972.jpg\\\" width=\\\"600\\\" height=\\\"765\\\" /\\u003e \\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》杂志2016年1-2月合刊\\n\\u003c/p\\u003e\\n\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t\\u003cimg style=\\\"margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/admin/JS/kindeditor-4.1.7/attached/image/20160107/20160107154753_4879.jpg\\\" width=\\\"600\\\" height=\\\"765\\\" /\\u003e \\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》杂志2015年第十二期\\n\\u003c/p\\u003e\\n\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t\\u003cimg style=\\\"margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/admin/JS/kindeditor-4.1.7/attached/image/20151203/20151203110302_5689.jpg\\\" width=\\\"600\\\" height=\\\"765\\\" /\\u003e \\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》杂志2015年第十一期\\n\\u003c/p\\u003e\\n\\n\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t\\u003cimg style=\\\"margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/admin/JS/kindeditor-4.1.7/attached/image/20151110/20151110155503_6608.jpg\\\" width=\\\"600\\\" height=\\\"765\\\" /\\u003e \\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》杂志2015年第十期\\n\\u003c/p\\u003e\\n\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t\\u003cimg style=\\\"margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/admin/JS/kindeditor-4.1.7/attached/image/20151023/20151023162317_4375.jpg\\\" width=\\\"600\\\" height=\\\"765\\\" /\\u003e \\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》杂志2015年第九期\\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t\\u003cimg style=\\\"margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/admin/JS/kindeditor-4.1.7/attached/image/20150918/20150918083906_3769.jpg\\\" width=\\\"600\\\" height=\\\"773\\\" /\\u003e \\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》杂志2015年第八期\\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t\\u003cimg style=\\\"margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/admin/JS/kindeditor-4.1.7/attached/image/20150812/20150812111036_7392.jpg\\\" width=\\\"600\\\" height=\\\"763\\\" /\\u003e \\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》杂志2015年第七期\\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t\\u003cimg style=\\\"margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/admin/JS/kindeditor-4.1.7/attached/image/20150720/20150720164826_8144.jpg\\\" width=\\\"600\\\" height=\\\"762\\\" /\\u003e \\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》杂志2015年第六期\\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t\\u003cimg style=\\\"margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/admin/JS/kindeditor-4.1.7/attached/image/20150612/20150612164216_8896.jpg\\\" width=\\\"600\\\" height=\\\"762\\\" /\\u003e \\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》杂志2015年第五期\\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t\\u003cimg style=\\\"margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/admin/JS/kindeditor-4.1.7/attached/image/20150513/20150513150130_4307.jpg\\\" width=\\\"620\\\" height=\\\"788\\\" /\\u003e \\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》杂志2015年第四期\\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t\\u003cimg style=\\\"margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/temp/201503q.jpg\\\" width=\\\"620\\\" height=\\\"788\\\" /\\u003e \\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》杂志2015年第三期\\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t\\u003cimg style=\\\"margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/admin/JS/kindeditor-4.1.7/attached/image/20150312/20150312133836_4850.jpg\\\" width=\\\"620\\\" height=\\\"788\\\" /\\u003e \\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》杂志2015年第二期\\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t\\u003ca name=\\\"no5\\\"\\u003e\\u003c/a\\u003e\\u003cimg style=\\\"margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/admin/JS/kindeditor-4.1.7/attached/image/20150213/20150213105756_1345.jpg\\\" /\\u003e \\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》杂志2015年第一期\\n\\u003c/p\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cimg style=\\\"margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/admin/JS/kindeditor-4.1.7/attached/image/20141216/20141216100419_4069.jpg\\\" /\\u003e \\n\\u003c/div\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》杂志2014年第四期\\n\\u003c/p\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/fm20140826.jpg\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》2014年第三期\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/20140703.jpg\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《直销——权健自然医学十年特刊》\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/fm20140528.jpg\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》2014年第二期\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/q4001.jpg\\\" width=444 ;\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》2014年第一期\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/q4002.jpg\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》2013年第五期\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/q4003.jpg\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》2013 年第四期\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/q4004.jpg\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》2013年第三期\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/q4005.jpg\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》2013年第二期\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/q4006.jpg\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《权健自然医学》2013 年第一期\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/q4007.jpg\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《中国新闻报》2014年3月4日\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/q4008.jpg\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《人民政协报》2014年3月4日\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/q4009.jpg\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《经济参考报》2014年3 月5日\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/q4010.png\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《人民日报》2013年12月7日\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/q4011.jpg\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《当代商报》2012年8月6日\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/q4012.jpg\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《国际商报》2013年7月4日\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/q4014.jpg\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《经济参考报》2013年5月29日\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/q4015.jpg\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《中国经济导报》2012年3月15 日\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\" margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/q4017.jpg\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《直销》2013年9月刊\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003cIMG style=\\\"margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/images/q4018.jpg\\\" width=444 ;align=\\\"middle\\\"\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t《知识经济中国直销》2013年9 月刊\\n\\u003c/div\\u003e\\n\\u003cdiv align=\\\"center\\\"\\u003e\\n\\t\\u003ca name=\\\"undefined\\\"\\u003e\\u003c/a\\u003e \\u003cimg style=\\\"margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/admin/JS/kindeditor-4.1.7/attached/image/20141118/20141118150423_9785.jpg\\\" /\\u003e \\n\\u003c/div\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t《健康大视野》权健专刊\\n\\u003c/p\\u003e\\n\\u003cp align=\\\"center\\\"\\u003e\\n\\t\\u003ca name=\\\"no201504\\\"\\u003e\\u003c/a\\u003e\\u003cimg style=\\\"margin-left:-2em;\\\" src=\\\"http://www.ziranyixue.com/admin/JS/kindeditor-4.1.7/attached/image/20150420/20150420123443_9727.jpg\\\" width=\\\"600\\\" height=\\\"824\\\" /\\u003e\\u003c/p\\u003e\\n\\t\\u003cp align=\\\"center\\\"\\u003e\\n\\t\\t《中华儿女》（健康之梦特刊）\\n\\t\\u003c/p\\u003e\\n\\t\\n\\n\",\"Nishow\":1,\"Nishot\":0,\"Ntype\":12,\"Ncreatdate\":\"\\/Date(1458230400000)\\/\",\"Npic\":null}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject json = jsonArray.getJSONObject(0);
            if (json.has("Ncontent")) {
                String content = json.getString("Ncontent");
                Document doc = Jsoup.parse(content);
                Log.e("lym",doc.html());
                PullParser.parserWeb(doc.html());
//                Elements elements = doc.select("div");
//                int i = 0;
//                for (Element element : elements) {
//                    Log.e("lym", element.text());
//                    if (element.attr("src").equals("")) {
//                        Log.e("lym",element.attr("src"));
//                    }
//                    i++;
//                }
//                Log.e("lym", i + "");
//                elements = doc.select("img");
//                int j = 0;
//                for (Element element : elements) {
//                    Log.e("lym", element.attr("src"));
//                    j++;
//                }
//                Log.e("lym", j + "");
//                elements = doc.select("p");
//                int k = 0;
//                for (Element element : elements) {
//                    Log.e("lym", element.text());
//                    k++;
//                }
//                Log.e("lym", k + "");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio2);
        radio3 = (RadioButton) findViewById(R.id.radio3);
        radio4 = (RadioButton) findViewById(R.id.radio4);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
