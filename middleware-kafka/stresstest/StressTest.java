import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StressTest {

    private static final String TARGET_URL = "http://localhost:8080/api/message";
    private static final int NUM_REQUESTS = 5000;
    private static final int NUM_THREADS = 50;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        long start = System.currentTimeMillis();

        for (int i = 0; i < NUM_REQUESTS; i++) {
            int finalI = i;
            executor.submit(() -> sendPost(finalI));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Aspetta che tutte le richieste siano completate
        }

        long duration = System.currentTimeMillis() - start;
        System.out.println("\nCompletate " + NUM_REQUESTS + " richieste in " + duration + " ms");
    }

    private static void sendPost(int id) {
        try {
            String message = generateMessage();
            String param = "message=" + URLEncoder.encode(message, "UTF-8");

            URL url = new URL(TARGET_URL + "?" + param);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStream os = con.getOutputStream();
            os.write(param.getBytes());
            os.flush();
            os.close();

            int status = con.getResponseCode();
            System.out.println("[" + id + "] " + status + " - " + truncate(message, 60));
            con.disconnect();

        } catch (Exception e) {
            System.err.println("[" + id + "] Errore: " + e.getMessage());
        }
    }

    private static String generateMessage() {
        Random rand = new Random();
        int type = rand.nextInt(3); // 0 = normale, 1 = lungo, 2 = ALLARME

        if (type == 0) {
            return "Messaggio normale " + randomString(20);
        } else if (type == 1) {
            return "Messaggio molto lungo " + randomString(300);
        } else {
            return "ALLARME! Qualcosa non va! ID=" + rand.nextInt(10000);
        }
    }

    private static String randomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private static String truncate(String str, int maxLen) {
        return str.length() > maxLen ? str.substring(0, maxLen) + "..." : str;
    }
}
