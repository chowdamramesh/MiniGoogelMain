import java.util.*;
public class MiniGoogleMain
{
	public static void main(String ar[]) throws Exception {

		WebPage html = new WebPage();

		String seed_Url = "http://minigoogle.msitprogram.net/";
		String data="";
		data = html.getContent(seed_Url);
		//System.out.println(data);

		List<String> urlArray = new ArrayList<String>();
		urlArray = html.getAllLinks(seed_Url);

		//List<String> linksArray = new ArrayList<String>();
		WebCrawl allLinks = new WebCrawl();

		for(String url : urlArray) {
			allLinks.crawl(url);
			//System.out.println(url);
		}

		/*for(String url : urlArray) {
			System.out.println(url);
		}*/
		//System.out.println(urlArray);

		List<String> stopWords = new ArrayList<String>();
		stopWords = html.getStopWords("StopWords.txt");
		//System.out.println(stopWords);

		List<String> words = new ArrayList<String>();
		words = html.getWords(data);
		//System.out.println(words);

		List<String> keyWords = new ArrayList<String>();
		keyWords = html.getKeyWords(words, stopWords);
		//System.out.println(keyWords);
	}
}