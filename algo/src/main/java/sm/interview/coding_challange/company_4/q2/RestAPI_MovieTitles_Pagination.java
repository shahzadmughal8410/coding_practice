/**
 * 
 */
package sm.interview.coding_challange.company_4.q2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author shahzadmughal8410
 *
 */
public class RestAPI_MovieTitles_Pagination {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String[] titles = getMovieTitles("spiderman");
		for (int i = 0; i < titles.length; i++) {
			System.out.println((i + 1) + " " + titles[i]);
		}
	}

	public static String doGet(String endpoint) {
		HttpURLConnection con = null;
		StringBuilder content = new StringBuilder();
		try {
			URL myurl = new URL(endpoint);
			con = (HttpURLConnection) myurl.openConnection();
			con.setRequestMethod("GET");

			try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
				String line;
				while ((line = in.readLine()) != null) {
					content.append(line);
					content.append(System.lineSeparator());
				}
			}

			System.out.println(content.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != con) {
				con.disconnect();
			}
		}
		return content.toString();
	}

	static String[] getMovieTitles(String substr) {
		String url = "https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr;
		String result = doGet(url);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		SearchResult searchResult = gson.fromJson(result, SearchResult.class);
		System.out.println("result=" + gson.toJson(searchResult));
		List<String> titles = new ArrayList<>();

		addTitles(searchResult.data, titles);

		int pageNo = searchResult.page;
		int totalPages = searchResult.total_pages;

		while (pageNo < totalPages) {
			pageNo = pageNo + 1;
			url = "https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr + "&page=" + pageNo;
			result = doGet(url);
			searchResult = gson.fromJson(result, SearchResult.class);
			System.out.println("result=" + gson.toJson(searchResult));

			addTitles(searchResult.data, titles);
			totalPages = searchResult.total_pages;
		}
		Collections.sort(titles);

		String[] titlesArr = new String[titles.size()];
		titlesArr = titles.toArray(titlesArr);
		return titlesArr;
	}

	public static void addTitles(List<MovieTitle> data, List<String> titles) {
		for (int i = 0; i < data.size(); i++) {
			titles.add(data.get(i).Title);
		}
	}
}

class MovieTitle {
	public String Poster;
	public String Title;
	public String Type;
	public int Year;
	public String imdbID;
}

class SearchResult {
	public int page;
	public int per_page;
	public int total;
	public int total_pages;
	public List<MovieTitle> data;
}
