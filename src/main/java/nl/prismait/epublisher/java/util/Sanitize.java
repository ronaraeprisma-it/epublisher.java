package nl.prismait.epublisher.java.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class Sanitize {
	public static String sanitizeString(String unsafe) {
		
//		System.out.println("Unsafe:");
//		System.out.println(unsafe);
		
		Safelist sl = Safelist.none();
		sl.addTags(new String[] {"em","strong","s","u","li","ol","ul","sub","sup","a","p","blockquote","table","tbody",
				"tr","td","th","h1","h2","h3","h4","h5","h6","pre","address","div","br"});
		
		sl.addAttributes("a", "href", "title");
		sl.addAttributes("blockquote", "cite");
		sl.addAttributes("ol", "start", "type");
		sl.addAttributes("ul", "type");
		sl.addAttributes("table", "summary", "width");
		sl.addAttributes("td", "abbr", "axis", "colspan", "rowspan", "width");
		sl.addAttributes("th", "abbr", "axis", "colspan", "rowspan", "scope", "width");
		
		sl.addProtocols("a", "href", "ftp", "http", "https", "mailto");
		sl.addProtocols("blockquote", "cite", "http", "https");
		
		String safe = Jsoup.clean(unsafe, sl);
		
//		System.out.println("Safe:");
//		System.out.println(safe);
		
		return safe;
	}
}
