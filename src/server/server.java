import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetSocketAddress;

import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

class Server {
  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(12345)) {
      Socket socket = serverSocket.accept();
      InputStream input = socket.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(input));

      OutputStream output = socket.getOutputStream();
      PrintWriter writer = new PrintWriter(output);

      System.out.printf("Received connection from: ");
      System.out.printf(socket.getRemoteSocketAddress().toString());
      InetSocketAddress remote = (InetSocketAddress)socket.getRemoteSocketAddress();
      InetSocketAddress local = (InetSocketAddress)socket.getLocalSocketAddress();
      System.out.println(local.getAddress().toString().equals(remote.getAddress().toString()));

      String msg;
      while(true) {
        msg = reader.readLine();
        if (msg != null) {
          System.out.println(msg);
          writer.println(msg);
          writer.flush();
          System.out.println("Sent");
        }
      }
    } catch (Exception e) {
      System.out.printf("Server Error: ");
      System.out.printf(e.getMessage());
      System.out.println();
      e.printStackTrace();
      System.exit(-1);
    }
  }
}
