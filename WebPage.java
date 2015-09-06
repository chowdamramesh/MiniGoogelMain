import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.net.*;
import java.io.*;

class WebPage {

    public String getContent(String url) throws Exception {
        String htmlData ="";
        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            htmlData =  htmlData + inputLine;
        }
        //System.out.println(htmlData);
        in.close();
    return htmlData;
    }

    	/*public List getAllLinks(String content)
	{
		int check = 0;
		int skipContent=8;
		List<String> urlArray = new ArrayList<String>();
		while(true) 
		{
			int start = content.indexOf( "a href=\"" ) + skipContent ;
			String newcontent = content.substring(start);
			check = start ;
			if(check == (skipContent - 1))
				break;
			start=0;
			int stop = newcontent.indexOf("\"" );
			String url = newcontent.substring( start , stop );
			content = newcontent.substring( stop + 1 );
			urlArray.add(url);
		}
		return urlArray;
	}*/
	public List<String> getAllLinks(String url) throws Exception{
    
    final ArrayList<String> result = new ArrayList<String>();

    Document doc = Jsoup.connect(url).get();

    Elements links = doc.select("a[href]");
    for (Element link : links) {
      result.add(link.attr("abs:href"));
    }
    //System.out.println(result);

    for (int i = 0; i < result.size() ; i++) {
      System.out.println(result.get(i) + "\n");
    }
    return result;

  }


	public List getStopWords(String fileName) throws Exception
	{
		String stopWord = null;
		List<String> stopWordsArray = new ArrayList<String>();
		BufferedReader br = null;
			String currentLine;
			br = new BufferedReader(new FileReader(fileName));
			while ((currentLine = br.readLine()) != null) {
				stopWord = currentLine ;
				stopWordsArray.add(stopWord);
			}
		return stopWordsArray;
	}


	public List getWords(String content)
	{
		int flag = 0;
		String word = "";
		List<String> wordsArray = new ArrayList<String>();
		for(int i=0; i<content.length()-1; i++)
		{
			if(content.charAt(i) == '<') {
				flag = 1;
			}
			else if(content.charAt(i)=='>') {
				flag=0;
			}
			else if(Character.isSpace(content.charAt(i)))
			{
				while(Character.isSpace(content.charAt(i))) {
					i++;
				}
				i--;
			}
			else if(Character.isLetter(content.charAt(i)) && flag!=1 )
			{
				word = "";
				while(Character.isLetter(content.charAt(i)) && flag!=1 ) {
					word = word + content.charAt(i);
					i++;
				}
				i--;
				wordsArray.add(word);
			}
		}
		return wordsArray;
	}


	public List getKeyWords(List<String> wordsArray, List<String> stopWordsArray) {
		int flag =0;
		List<String> keyWordsArray = new ArrayList<String>();
		for(String word : wordsArray)
		{
			flag =0;
			for(String stopWord : stopWordsArray)
			{
				if(word.compareToIgnoreCase(stopWord) == 0)
				{
					flag=1;
					break;
				}
			}
			if(flag == 0)
				keyWordsArray.add(word); 
		}
		return keyWordsArray;
	}
	
	/*public int getKeywordFrequency(String keyword) {

	}*/
}

