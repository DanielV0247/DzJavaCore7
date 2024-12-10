import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8080;
    public static void main(String[] args) throws IOException {

        System.out.println("Сервер запущен.");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    String name = in.readLine();
                    System.out.println("Имя посетителя: " + name);
                    System.out.printf("Новое подключение\n Порт: %d%n", clientSocket.getPort());
                    out.println("Добро пожаловать "+ name + " Твой порт: "+ clientSocket.getPort());
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}