package com.yunmin.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{


    String url = "http://appws.ziranyixue.com/qjapp.asmx/GetNewsList";
    //i=13 num=50,page=1
    ListView listView;
    public static final int MESSAGE_WHAT = 1;
    DownLoadListAdapter adapter;
    List<DownLoadBean> mList;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (MESSAGE_WHAT == msg.what) {
                try {
                    String data = PullParser.readXML((String) msg.obj).getStr();
                    JSONArray jsonArray = new JSONArray(data);
                    mList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = 0;
                        String title = null;
                        String content = null;
                        String url = null;
                        String date = null;
                        if (jsonObject.has("Nid")) {
                            id = jsonObject.getInt("Nid");
                        }
                        if (jsonObject.has("Ntittle")) {
                            title = jsonObject.getString("Ntittle");
                        }
                        if (jsonObject.has("Ncontent")) {
                            content = jsonObject.getString("Ncontent");
                            Document doc = Jsoup.parse(content);
                            url = doc.select("a").attr("href");
                            if (!url.startsWith("http")) {
                                if (url.startsWith("\\/")) {
                                    url = Constance.WEB_URL + url;
                                } else {
                                    url = Constance.WEB_URL + File.separator + url;
                                }
                            }
                        }
                        if (jsonObject.has("Ncreatdate")) {
                            date = jsonObject.getString("Ncreatdate");
                            date = DateTools.splitDate(date);
                        }
                        DownLoadBean downLoadBean = new DownLoadBean(id, title, content, url, date);
                        mList.add(downLoadBean);
                    }
                    adapter = new DownLoadListAdapter(MainActivity.this, mList);
                    listView.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.download_list);
        new Thread(new MyRunnable()).start();
        IntentFilter intent = new IntentFilter(Constants.DOWNLOAD_MSG);
        registerReceiver(downloadStatusReceiver, intent);
    }

    /**
     * 初始化下载状态
     */
    private void initStatus() {
//        List<AppContent> list = DownloadFileDAO.getInstance(this.getApplicationContext()).getAll();
//        for (AppContent appContent : list) {
//            for (AppContent app : mList) {
//                if (app.getUrl().equals(appContent.getUrl())) {
//                    app.setStatus(appContent.getStatus());
//                    app.setDownloadPercent(appContent.getDownloadPercent());
//                    break;
//                }
//            }
//        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.e("lym",i+"");
    }

    public class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("i", "13"));
                list.add(new BasicNameValuePair("num", "50"));
                list.add(new BasicNameValuePair("page", "1"));
                post.setEntity(new UrlEncodedFormEntity(list));
                HttpResponse response = httpClient.execute(post);
                HttpEntity entity = response.getEntity();
                BufferedReader read = new BufferedReader(new InputStreamReader(
                        entity.getContent(), "utf-8"));
                StringBuffer sb = new StringBuffer();
                String content = "";
                while ((content = read.readLine()) != null) {
                    sb.append(content);
                }
                if (handler != null) {
                    Message message = handler.obtainMessage();
                    message.what = MESSAGE_WHAT;
                    message.obj = sb.toString();
                    handler.sendMessage(message);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(downloadStatusReceiver);
        super.onDestroy();
    }

    private BroadcastReceiver downloadStatusReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            DownLoadBean downLoadBean = intent.getParcelableExtra("downLoadBean");
            if (downLoadBean == null) return;
            int itemIndex = 0;
            for (DownLoadBean downLoadBean1 : mList) {
                if (downLoadBean.getNurl().equals(downLoadBean1.getNurl())) {
                    itemIndex = mList.indexOf(downLoadBean1);
                    downLoadBean1.setProgress(downLoadBean.getProgress());
                    break;
                }
            }
            updateView(itemIndex);
        }
    };

    private void updateView(int itemIndex) {
        //得到第一个可显示控件的位置，
        int visiblePosition = listView.getFirstVisiblePosition();
        //只有当要更新的view在可见的位置时才更新，不可见时，跳过不更新
        if (itemIndex - visiblePosition >= 0) {
            //得到要更新的item的view
            View view = listView.getChildAt(itemIndex - visiblePosition);
            //调用adapter更新界面
            adapter.updateView(view, itemIndex);
        }
    }
}
