package messageclient;

import java.net.InetSocketAddress;

public class Utils {

    public static InetSocketAddress parseInetAddress(String address, String port) {
        return new InetSocketAddress(address, Integer.parseInt(port));
    }
}
