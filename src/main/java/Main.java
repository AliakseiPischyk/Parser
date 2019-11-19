import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<String> getUrlsOfGoods(Document document) {
        Elements links = document.select(".b-teaser");
        List<String> ret = new ArrayList<String>(links.size());
        for (Element each : links) {
            ret.add(each.child(1).attr("href"));
        }
        return ret;
    }

    public static int getLastPageIdx(Document document){
        Elements linksToPages = document.select(".b-pagination__item");
        return  Integer.valueOf(linksToPages.get(linksToPages.size()-2).child(0).attr("data-page"));
    }

    public static List<String> enumPages(String url, List<Integer> indices){
        List<String> ret = new ArrayList<String>(indices.size());
        for(int idx: indices)
        {
            ret.add(url + idx);
        }
        return ret;
    }

    public static String getDiscountInfo(Document document){
        return document.select(".b-shop-teaser__cash-value-row").select("span").text();
    }

    public static String getShopName(Document document){
        return document.select(".container").get(2).child(0).child(0).child(2).text();
    }

    public static List<String> getConditions(Document document){
        Elements ass = document.select(".b-module-info--conditions").get(0).children();
        List<String> conditions = new ArrayList<String>();
        for(Element condition : ass){
            conditions.add(condition.text());
        }
        return conditions;
    }

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://letyshops.com/by/shops/lamoda-by").get();
        System.out.println(getConditions(doc));
        System.out.println(getShopName(doc) + " " + getDiscountInfo(doc));
    }




}
