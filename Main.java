import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class ServerCheck {

    public static void main(String[] args) {

        if (!isInternetAvailable()) {
            System.out.println("Internet is NOT connected.");
            return;
        }

        System.out.println("Internet is connected.");

        String ip = getPublicIP();
        if (ip == null) {
            System.out.println("Could not fetch IP.");
            return;
        }

        System.out.println("Your Public IP: " + ip);

        String country = getCountryFromIP();
        if (country == null) {
            System.out.println("Could not detect country.");
            return;
        }

        System.out.println("Detected Country: " + country);

        if (country.equalsIgnoreCase("IR")) {
            System.out.println("Iran IP detected. Full access granted.");
        } else {
            System.out.println("Foreign IP detected. Limited access mode.");
        }
    }

    // Check internet connection
    public static boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("google.com");
            return address.isReachable(3000);
        } catch (Exception e) {
            return false;
        }
    }

    // Get public IP
    public static String getPublicIP() {
        try {
            URL url = new URL("https://api.ipify.org");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            return br.readLine();
        } catch (Exception e) {
            return null;
        }
    }

    // Detect country from IP
    public static String getCountryFromIP() {
        try {
            URL url = new URL("http://ip-api.com/line/?fields=countryCode");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            return in.readLine();
        } catch (Exception e) {
            return null;
        }
    }
}
