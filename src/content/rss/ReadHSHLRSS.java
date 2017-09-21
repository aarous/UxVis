package content.rss;

public class ReadHSHLRSS {
	static Feed newsFeed;
	Feed blogFeed;
	Feed presseFeed;

	public ReadHSHLRSS() {
		RSSFeedParser newsParser = new RSSFeedParser(
				"http://www.hshl.de/news/rss");
		RSSFeedParser blogParser = new RSSFeedParser(
				"http://www.hshl.de/news/rss");
		RSSFeedParser presseParser = new RSSFeedParser(
				"http://www.hshl.de/news/rss");

		// newsFeed = newsParser.readFeed();
		// blogFeed = blogParser.readFeed();
		// presseFeed = presseParser.readFeed();

		/*
		 * System.out.println(feed); for (FeedMessage message :
		 * feed.getMessages()) { System.out.println(message);
		 * 
		 * }
		 */
	}

	public static Feed getNewsFeed() {
		return newsFeed;
	}

	public Feed getBlogFeed() {
		return blogFeed;
	}

	public Feed getPresseFeed() {
		return presseFeed;
	}
}