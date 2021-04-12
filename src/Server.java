import java.net.*;
import java.io.*;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);

        Socket client = null;
        OutputStreamWriter osw = null;
        int t = 0;
        while (true) {
            client = serverSocket.accept();
            osw = new OutputStreamWriter(client.getOutputStream());
            osw.write("HTTP/1.0 200 OK\n" +
                    "Content-type: text/html\n" +
                    "\n" +
                    "Hello world!");
            System.out.println(t++);
            osw.flush();
            osw.close();
        }
    }
}
