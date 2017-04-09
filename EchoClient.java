import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public final class EchoClient {

	public static void main(String[] args) throws Exception {
		try (Socket socket = new Socket("localhost", 22222)) {
			Scanner kb = new Scanner(System.in);
			boolean exit = false;
			while (exit != true) {
				System.out.print("Client> ");
				String input = kb.nextLine();
				if (input.equals("exit")){
					exit = true;
				}
				OutputStream os = socket.getOutputStream();
				PrintStream out = new PrintStream(os, true, "UTF-8");
				out.println(input);
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				System.out.println(br.readLine());
			}

		}
	}
}