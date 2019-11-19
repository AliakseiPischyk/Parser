import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    List<String> getUrlsOfGoods(Elements elements) {
        List<String> ret = new ArrayList<String>(elements.size());
        for (Element each : elements){
            ret.add(each.child(1).attr("href"));
        }
        return ret;
    }
    String getLinkOnNextPage(){
        return null;
    }

    int getLastPageIdx(Document document){
        Elements linksToPages = document.select(".b-pagination__item");
        return  Integer.valueOf(linksToPages.get(linksToPages.size()-2).child(0).attr("data-page"));
    }

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://letyshops.com/by/shops").get();
        //Elements elements = document.select("[id]");
        Elements links = document.select(".b-teaser");
        Elements linksToPages = document.select(".b-pagination__item");
        Element pageWithLastIndex = linksToPages.get(linksToPages.size()-2);
        String lastPageNum = pageWithLastIndex.child(0).attr("data-page");
        System.out.println("debug");
    }


}
