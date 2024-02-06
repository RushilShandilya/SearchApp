import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Crawler {
    HashSet<String> urlSet;
    int MAX_DEPTH=2;
    Crawler(){
        urlSet = new HashSet<>();
    }
    void getPageTextAndLinks(String url , int depth){
        if(urlSet.contains(url)) return ;
        if(depth>=MAX_DEPTH) return;

        if(urlSet.add(url)) System.out.println(url);

        ++depth;
        try {
            Document document = Jsoup.connect(url).timeout(5000).get();
            Indexer indexer = new Indexer(document,url);
            System.out.println(document.title());
            Elements availableLinksOnPage = document.select("a[href]");
            for (Element currentLink : availableLinksOnPage) {
                getPageTextAndLinks(currentLink.attr("abs:href"), depth);
            }
        }catch(IOException ioexception){
            ioexception.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args){
        Crawler crawler = new Crawler();
        crawler.getPageTextAndLinks("https://www.javatpoint.com" ,0);

    }
}
