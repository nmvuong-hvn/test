package tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ServerTcp  {
    ServerSocket welcomeSocket;
    Thread runServerTcpReadData ;

    Socket socket ;

    IDataReceived dataReceived;
    public void init(IDataReceived dataReceived) throws IOException {
        welcomeSocket = new ServerSocket(6543);
        socket = welcomeSocket.accept();
        this.dataReceived = dataReceived;
    }
    public void readData(){
        runServerTcpReadData = new Thread(() -> {
            DataInputStream dataInputStream = null;
            try {
                dataInputStream = new DataInputStream(socket.getInputStream());
                while (true) {
                    String message = dataInputStream.readUTF();
                    System.out.println("message = " + Arrays.toString(message.getBytes(StandardCharsets.UTF_8)));
                    dataReceived.receivedData(message);
                }
            } catch (Exception exception) {
                System.out.println("Error = " + exception.getMessage());
                try {
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                } catch (IOException ioException) {
                    System.out.println("Error Read close = " + ioException.getMessage());

                }
            }
        });
        runServerTcpReadData.start();
    }
    public void writeData(String data){
        if (socket == null) {
            System.out.println("Socket is null");
            return;
        }
        DataOutputStream dataOutputStream = null ;
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(data);
            System.out.println("data = " + data);

        }catch (Exception ex){
            System.out.println("Error = " + ex.getMessage());
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error Write close = " + e.getMessage());
            }
        }
    }
}
