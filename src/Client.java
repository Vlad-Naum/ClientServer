import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("127.0.0.1", 8000);
             OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){

            osw.write("Hello Server." + "\n");
            osw.flush();
            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
