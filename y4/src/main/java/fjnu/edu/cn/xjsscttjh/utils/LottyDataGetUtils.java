package fjnu.edu.cn.xjsscttjh.utils;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fjnu.edu.cn.xjsscttjh.bean.TrendInfo;

/**
 * Created by GaoFei on 2018/1/28.
 * 彩票数据获取器
 */

public class LottyDataGetUtils {
    private static final String TAG = "LottyDataGetUtils";
    private LottyDataGetUtils(){

    }

    /**
     * 从发彩网抓取走势消息
     * @return
     */
    public static List<TrendInfo> getAllTrendInfoByFC(){
        List<TrendInfo> trendInfoList = new ArrayList<>();
        try{
            Document document = Jsoup.connect("http://www.es123.com/trends/").get();
            Element fcElement = document.getElementById("fucai_trend1l");
            Elements trendElements = fcElement.getElementsByClass("fucai_lottery_list clearfix");
            for(Element itemElement : trendElements){
                TrendInfo trendInfo = new TrendInfo();
                Elements logoElements = itemElement.getElementsByClass("fc_list_logo");
                String imgUrl = logoElements.get(0).getElementsByTag("img").get(0).attr("src");
                String name = logoElements.get(0).getElementsByTag("span").get(0).text();
                Elements liElements = itemElement.getElementsByClass("clearfix").get(0).getElementsByTag("li");
                Map<String, String> liMap = new LinkedHashMap<>();
                for(Element itemLiElemnt : liElements){
                    String trendName = itemLiElemnt.getElementsByTag("a").get(0).text();
                    String trendUrl = itemElement.getElementsByTag("a").attr("href");
                    liMap.put(trendName, trendUrl);
                }
                trendInfo.setImgUrl(imgUrl);
                trendInfo.setName(name);
                trendInfo.setTrendUrl(liMap);
                trendInfoList.add(trendInfo);
            }


            Element tcElement = document.getElementsByClass("fucai_trend").get(1);
            trendElements = tcElement.getElementsByClass("fucai_lottery_list clearfix");
            for(Element itemElement : trendElements){
                TrendInfo trendInfo = new TrendInfo();
                Elements logoElements = itemElement.getElementsByClass("fc_list_logo");
                String imgUrl = logoElements.get(0).getElementsByTag("img").get(0).attr("src");
                String name = logoElements.get(0).getElementsByTag("span").get(0).text();
                Elements liElements = itemElement.getElementsByClass("clearfix").get(0).getElementsByTag("li");
                Map<String, String> liMap = new LinkedHashMap<>();
                for(Element itemLiElemnt : liElements){
                    String trendName = itemLiElemnt.getElementsByTag("a").get(0).text();
                    String trendUrl = itemElement.getElementsByTag("a").attr("href");
                    liMap.put(trendName, trendUrl);
                }
                trendInfo.setImgUrl(imgUrl);
                trendInfo.setName(name);
                trendInfo.setTrendUrl(liMap);
                trendInfoList.add(trendInfo);
            }

        }catch (Exception e){
            //no handle
            Log.i(TAG, "getAllTrendInfoByFC->exception:" + e);
        }
        return  trendInfoList;
    }
}
