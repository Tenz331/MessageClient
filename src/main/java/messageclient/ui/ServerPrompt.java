package messageclient.ui;

import messageclient.Utils;

import javax.swing.*;
import java.awt.*;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerPrompt extends JFrame {
    private final BlockingQueue<InetSocketAddress> aRef;

    public ServerPrompt(String defaultHost, int defaultPort) {
        super("Select Server");
        this.aRef = new LinkedBlockingQueue<>();

        var address = new JTextField(defaultHost, 30);
        var port = new JTextField("" + defaultPort, 4);
        var button = new JButton("GO");

        button.addActionListener( l -> {
            aRef.add(Utils.parseInetAddress(address.getText(), port.getText()));
            this.setVisible(false);
        });

        add(createLinePanel(List.of(address, port, button)));

        pack();
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public InetSocketAddress waitForAddress() throws InterruptedException {
        return aRef.take();
    }

    private static JPanel createLinePanel(List<Component> components) {
        var pane = new JPanel();
        pane.setSize(600, 50);
        pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pane.setLayout(new BoxLayout(pane, BoxLayout.LINE_AXIS));
        for (var c : components) {
            pane.add(c);
        }
        return pane;
    }

}
