import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @class StressTest
 * @brief Classe per effettuare un test di carico (stress test) su un endpoint HTTP.
 * 
 * Questa classe invia molteplici richieste POST concorrenti a un server locale, simulando
 * diversi tipi di messaggi per valutare le prestazioni del server.
 */
public class StressTest {

    /** URL di destinazione su cui inviare le richieste POST */
    private static final String TARGET_URL = "http://localhost:8080/api/message";

    /** Numero totale di richieste da inviare */
    private static final int NUM_REQUESTS = 5000;

    /** Numero di thread utilizzati per inviare le richieste in parallelo */
    private static final int NUM_THREADS = 50;

    /**
     * Metodo principale che avvia il test di stress.
     * Crea un pool di thread fisso e invia NUM_REQUESTS richieste POST in parallelo.
     * Misura e stampa il tempo totale impiegato per completare tutte le richieste.
     * 
     * @param args argomenti da linea di comando (non utilizzati)
     */
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

    /**
     * Invia una singola richiesta POST al server con un messaggio generato casualmente.
     * La richiesta viene costruita con content-type application/x-www-form-urlencoded.
     * Stampa lo stato HTTP di risposta e una parte del messaggio inviato.
     * 
     * @param id identificatore della richiesta per il logging
     */
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

    /**
     * Genera un messaggio casuale di uno tra tre tipi:
     * - messaggio normale (stringa breve)
     * - messaggio molto lungo
     * - messaggio di allarme con ID numerico
     * 
     * @return una stringa contenente il messaggio generato
     */
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

    /**
     * Genera una stringa casuale composta da caratteri alfanumerici.
     * 
     * @param length lunghezza della stringa da generare
     * @return stringa casuale alfanumerica di lunghezza length
     */
    private static String randomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * Trunca una stringa se supera una lunghezza massima, aggiungendo "..." alla fine.
     * 
     * @param str stringa da troncare
     * @param maxLen lunghezza massima consentita
     * @return stringa troncata con "..." se superava maxLen, altrimenti la stringa originale
     */
    private static String truncate(String str, int maxLen) {
        return str.length() > maxLen ? str.substring(0, maxLen) + "..." : str;
    }
}
