import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Thread;

public final class EchoServer {

	public static void main(String[] args) throws Exception {
		try (ServerSocket serverSocket = new ServerSocket(22222)) {
			while (true) {
				final Socket socket = serverSocket.accept();
				Thread thread = new Thread(new Runnable() {
					public void run() {
						try {
							final String address = socket.getInetAddress().getHostAddress();
							String inputLine;
							InputStream is = socket.getInputStream();
							InputStreamReader isr = new InputStreamReader(is,
									"UTF-8");
							BufferedReader br = new BufferedReader(isr);
							OutputStream os = socket.getOutputStream();
							PrintStream out = new PrintStream(os, true, "UTF-8");
							System.out
									.printf("Client connected: %s%n", address);
							boolean exit = false;
							while ((inputLine = br.readLine()) != null
									&& exit != true) {
								if (inputLine.equals("exit")) {
									exit = true;
								}
								out.println("Server> " + inputLine);
							}
							System.out.printf("Client disconnected: %s%n",
									address);
						} catch (Exception e) {
							;
						}
					}
				});
				thread.start();
			}
		}
	}
}
