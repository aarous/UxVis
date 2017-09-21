package rssprocessor;

import java.io.*;
import java.net.*;

public class RSSReader {

	
	public RSSReader(){
		System.out.println(readRSS("http://hshl.de/news/rss"));
	}
	
	public String readRSS(String url){
		try{
			String sourceCode = "";
			String line;
			URL rssURL;
			rssURL = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(rssURL.openStream()));
			// Reads the title of the rss
			while((line = in.readLine()) != null){
				if(line.contains("<title>")){
					int firstPos = line.indexOf("<title>");
					String temp = line.substring(firstPos);
					temp = temp.replace("<title>", "");
					int lastPos = line.indexOf("</title>");
					//temp = temp.substring(0, lastPos);
					sourceCode += temp + "\n";
				}
				
				
			}
			in.close();
			return sourceCode;
		} catch(MalformedURLException ue){
			System.out.println("Mit der URL stimmt was nicht");
		} catch(IOException io){
			System.out.println("Mit dem Reader stimmt was nicht");
		}
		return null;
	}
}
