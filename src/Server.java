import java.net.*;
import java.io.*;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Server started.");
        int number = 0;

        while (true) {
            Socket client = serverSocket.accept();
            Thr thr = new Thr(client, ++number);
            thr.start();
        }
    }
}

class Thr extends Thread{
    private Socket client;
    private int number;

    public Thr(Socket client, int number) {
        this.client = client;
        this.number = number;
    }

    @Override
    public void run() {
        try (OutputStreamWriter osw = new OutputStreamWriter(client.getOutputStream());
             BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()))){

            String message = reader.readLine();
            System.out.println("Client #" + number + ". Your message: " + message);

            osw.write("HTTP/1.0 200 OK\n" +
                    "Content-type: text/html\n" +
                    "\n" +
                    "Hello! You are #" + number + "\n");
            osw.flush();
            number++;

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
