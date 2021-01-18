
import java.net.ServerSocket;
import java.net.Socket;

import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Console;

class Client {
  public static void main(String[] args) {
    try (Socket socket = new Socket("localhost", 12345)) {
      InputStream input = socket.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(input));

      OutputStream output = socket.getOutputStream();
      PrintWriter writer = new PrintWriter(output);
      Console console = System.console();

      String msg;
      while (true) {
        msg = console.readLine("Input: "); 
        writer.println(msg);
        writer.flush();
        System.out.println("Sent");
        System.out.println(reader.readLine());
      }
      
    } catch (Exception e) {
      System.out.printf(e.getMessage());
      System.out.println();
      e.printStackTrace();
    }
  }
}
