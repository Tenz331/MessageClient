package messageclient.ui;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.PrintWriter;

public class ClientWindow extends JFrame {
    private static final Font FONT = new Font(Font.MONOSPACED, Font.PLAIN, 16);
    private final JTextArea textArea;

    public ClientWindow(PrintWriter out) {
        super("MessageClient");

        textArea = createTextArea();
        add(createScrollableTextArea(textArea), BorderLayout.CENTER);
        add(createTextField(out), BorderLayout.SOUTH);

        setSize(800, 800);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void append(String string)  {
        textArea.append(string);
    }

    private static JTextField createTextField(PrintWriter out) {
        JTextField textField = new JTextField();
        textField.setFont(FONT);
        textField.addActionListener(e -> {
            var msg = e.getActionCommand();
            out.println(msg);
            out.flush();
            textField.setText("");
        });
        return textField;
    }

    private static JTextArea createTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(FONT);
        DefaultCaret caret = new DefaultCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textArea.setCaret(caret);
        return textArea;
    }

    private static JScrollPane createScrollableTextArea(JTextArea textArea) {
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        return scroll;
    }
}
