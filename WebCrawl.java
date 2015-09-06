import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class WebCrawl {

	public void crawl(String url) throws Exception {
    final ArrayList<String> result = new ArrayList<String>();

    //String a = "http://minigoogle.msitprogram.net/page1.html";
    Document doc = Jsoup.connect(url).get();

    Elements links = doc.select("a[href]");
    for (Element link : links) {
      result.add(link.attr("abs:href"));
    }

    for (int i = 0; i < result.size() ; i++) {
      System.out.println(result.get(i) + "\n");
    }
    //System.out.println(result);
  }
}