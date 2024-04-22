package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ClientTcp {
    private Socket client;
    private IDataReceived dataReceived;

    public void init(IDataReceived dataReceived) throws IOException {
        client = new Socket("localhost", 6543);
        this.dataReceived = dataReceived ;
    }

    public void readData() {
        if (client != null && dataReceived != null) {
            Thread runClientTcpReadData = new Thread(() -> {
                DataInputStream dataInputStream = null;
                try {
                    dataInputStream = new DataInputStream(client.getInputStream());
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
            runClientTcpReadData.start();
        }
    }

    public void writeData(String data) {
        if (client == null) {
            System.out.println("client socket is null");
            return;
        }
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(client.getOutputStream());
            dataOutputStream.writeUTF(data);
            System.out.println("data = " + data);

        } catch (Exception ex) {
            System.out.println("Error = " + ex.getMessage());
            try {
                client.close();
            } catch (IOException e) {
                System.out.println("Error Write close = " + e.getMessage());
            }
        }
    }
}
