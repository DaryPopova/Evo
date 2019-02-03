import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Utils {

    public static String sendPostRequest(String host, String urlParameters) throws Exception {
        URL url = new URL(host);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestProperty( "Content-Type", "application/json");
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
        writer.write(urlParameters);
        writer.flush();
        InputStream inputStream;
        if (conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
            inputStream = conn.getInputStream();
        } else {
            inputStream = conn.getErrorStream();
        }
        String line;
        StringBuffer result = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        writer.close();
        reader.close();
        return result.toString();
    }

    public static String sendGetRequest(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        InputStream inputStream;
        if (conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
            inputStream = conn.getInputStream();
        } else {
            inputStream = conn.getErrorStream();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();
        return result.toString();
    }
}
