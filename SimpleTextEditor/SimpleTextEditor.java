/* Programmer :- Adarsh Mishra
 * Project :- A Text Editor Using JAVA
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SimpleTextEditor extends JFrame {
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openItem, saveItem, closeItem;

    public SimpleTextEditor() {
        // Set up the frame of the Text Editor
        setTitle("Text Editor - Notes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 450);
        setLocationRelativeTo(null);

        // Create the text area for the Text Editor
        textArea = new JTextArea();

        // Create the menu bar for the Text Editor
        menuBar = new JMenuBar();

        // Create the file menu for the Text Editor
        fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        closeItem = new JMenuItem("Erase All");
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(closeItem);

        // Add components to the menu bar of Text Editor
        menuBar.add(fileMenu);

        // Set the menu bar for the frame of Text Editor
        setJMenuBar(menuBar);

        // Set the layout for the frame of Text Editor
        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Add action listeners
        openItem.addActionListener(new OpenActionListener());
        saveItem.addActionListener(new SaveActionListener());
        closeItem.addActionListener(new CloseActionListener());
    }

    // ActionListener for opening an Exisiting File
    private class OpenActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(SimpleTextEditor.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    reader.close();
                    textArea.setText(sb.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    // ActionListener for saving a new file to the System
    private class SaveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(SimpleTextEditor.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(textArea.getText());
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    // ActionListener for closing the file
    private class CloseActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.setText("");
        }
    }
    // Main method for the Execution.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleTextEditor textEditor = new SimpleTextEditor();
            textEditor.setVisible(true);
        });
    }
}
