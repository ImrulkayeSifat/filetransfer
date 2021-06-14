
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client {

   // static Socket sock;
    static PrintWriter writer;
    static JTextArea incoming;
    static JTextField outgoing;
    static BufferedReader reader;
    static String name;
    static String ip;
    static JTextField ip1;
    static JTextField ip2;
    static JTextField ip3;
    static JTextField ip4;
    static JFrame nameAndIpframe;
    static JTextField nameTextField;

    public static void main(String[] args) {

        nameAndIpframe = new JFrame("client part");
        nameAndIpframe.setResizable(false);
        JPanel nameAndIpPanel = new JPanel(new FlowLayout());

        nameAndIpPanel.add(new JLabel("                                                          "));
        nameAndIpPanel.add(new JLabel("                                                           "));
        nameAndIpPanel.add(new JLabel("                                   Enter Your Name " ,SwingConstants.CENTER));
        nameAndIpPanel.add(new JLabel("                                                         "));

        nameTextField = new JTextField(30);
        name = nameTextField.getText();
        nameAndIpPanel.add(nameTextField);


        nameAndIpPanel.add(new JLabel("                                                         "));
        nameAndIpPanel.add(new JLabel("                                               "));
        JButton sendNameAndIp = new JButton("connect now");
        sendNameAndIp.addActionListener(new sendNameAndIpButtonListener());
        nameAndIpPanel.add(new JLabel("<html><space>&emsp&emsp&emsp &emsp&emsp&emsp</space>Server's port number is 1234<br> <space>&emsp&emsp&emsp&emsp&emsp &emsp&emsp</space>Enter valid Ip Address:</html>", SwingConstants.CENTER ));
        nameAndIpPanel.add(new JLabel("                                         "));
        nameAndIpPanel.add(new JLabel(""));
        nameAndIpPanel.add(new JLabel("                                                         "));
        nameAndIpPanel.add(new JLabel("                                                        "));

        nameAndIpPanel.add(new JLabel(""));
        ip1 = new JTextField(4);
        ip2 = new JTextField(4);
        ip3 = new JTextField(4);
        ip4 = new JTextField(4);
        nameAndIpPanel.add(ip1);
        nameAndIpPanel.add(new Label("."));
        nameAndIpPanel.add(ip2);
        nameAndIpPanel.add(new Label("."));
        nameAndIpPanel.add(ip3);
        nameAndIpPanel.add(new Label("."));
        nameAndIpPanel.add(ip4);
        nameAndIpPanel.add(sendNameAndIp);
        nameAndIpframe.getContentPane().add(BorderLayout.CENTER, nameAndIpPanel);
        nameAndIpframe.setSize(400, 400);
        nameAndIpframe.setVisible(true);
        nameAndIpframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    private static class sendNameAndIpButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            boolean isIpTrue = false;

            try {
                if (!isInteger(ip1.getText()))
                    throw new Exception();
                if (!isInteger(ip2.getText()))
                    throw new Exception();
                if (!isInteger(ip3.getText()))
                    throw new Exception();
                if (!isInteger(ip4.getText()))
                    throw new Exception();
                if (nameTextField.getText().equals("")) {
                    nameTextField.setText("you've not set your name ");
                } else {
                    isIpTrue = true;
                }

            } catch (Exception e) {
                System.err.println("Invalid IP Address");
                JFrame error = new JFrame("Error");
                JPanel ErrorPanel = new JPanel();
                ErrorPanel.add(new Label("Invalid IP "));
                error.getContentPane().add(BorderLayout.NORTH, ErrorPanel);
                error.setResizable(false);
                error.setSize(70, 100);
                error.setVisible(true);
                error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }

            if (isIpTrue) {
                name = nameTextField.getText();
                ip = ip1.getText() + "." + ip2.getText() + "." + ip3.getText() + "." + ip4.getText();
                System.out.println(ip);
                System.out.println("yeah");
                try {
                   Socket sock = new Socket(ip, 1234);

                    //  reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                    //writer = new PrintWriter(sock.getOutputStream());
                    System.out.println("Connection with the server Established");
                    nameAndIpframe.setVisible(false);


                  //  private void go() {


                        // Accessed from within inner class needs to be final or effectively final.
                        final File[] fileToSend = new File[1];

                        // Set the frame to house everything.
                        JFrame jFrame = new JFrame("website Client part");
                        // Set the size of the frame.
                        jFrame.setSize(450, 450);
                        // Make the layout to be box layout that places its children on top of each other.
                        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
                        // Make it so when the frame is closed the program exits successfully.
                        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        // Title above panel.
                        JLabel jlTitle = new JLabel(" File Sender/ client");
                        // Change the font family, size, and style.
                        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
                        // Add a border around the label for spacing.
                        jlTitle.setBorder(new EmptyBorder(20, 0, 10, 0));
                        // Make it so the title is centered horizontally.
                        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

                        // Label that has the file name.
                        JLabel jlFileName = new JLabel("Choose a file to send.");
                        // Change the font.
                        jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
                        // Make a border for spacing.
                        jlFileName.setBorder(new EmptyBorder(50, 0, 0, 0));
                        // Center the label on the x axis (horizontally).
                        jlFileName.setAlignmentX(Component.CENTER_ALIGNMENT);

                        // Panel that contains the buttons.
                        JPanel jpButtond = new JPanel();
                        // Border for panel that houses buttons.
                        jpButtond.setBorder(new EmptyBorder(75, 0, 10, 0));
                        // Create send file button.
                        JButton jbSendFile = new JButton("Send File");
                        // Set preferred size works for layout containers.
                        jbSendFile.setPreferredSize(new Dimension(150, 75));
                        // Changed the font style, type, and size for the button.
                        jbSendFile.setFont(new Font("Arial", Font.BOLD, 20));
                        // Make the second button to choose a file.
                        JButton jbChooseFile = new JButton("Choose File");
                        // Set the size which must be preferred size for within a container.
                        jbChooseFile.setPreferredSize(new Dimension(150, 75));
                        // Set the font for the button.
                        jbChooseFile.setFont(new Font("Arial", Font.BOLD, 20));

                        // Add the buttons to the panel.
                        jpButtond.add(jbSendFile);
                        jpButtond.add(jbChooseFile);

                        // Button action for choosing the file.
                        // This is an inner class so we need the fileToSend to be final.
                        jbChooseFile.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Create a file chooser to open the dialog to choose a file.
                                JFileChooser jFileChooser = new JFileChooser();
                                // Set the title of the dialog.
                                jFileChooser.setDialogTitle("Choose a file to send.");
                                // Show the dialog and if a file is chosen from the file chooser execute the following statements.
                                if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                                    // Get the selected file.
                                    fileToSend[0] = jFileChooser.getSelectedFile();
                                    // Change the text of the java swing label to have the file name.
                                    jlFileName.setText("The file you want to send is: " + fileToSend[0].getName());
                                }
                            }
                        });


                        // Sends the file when the button is clicked.
                        jbSendFile.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // If a file has not yet been selected then display this message.
                                if (fileToSend[0] == null) {
                                    jlFileName.setText("Please choose a file to send first!");
                                    // If a file has been selected then do the following.
                                } else {
                                    try {
                                        // Create an input stream into the file you want to send.
                                        FileInputStream fileInputStream = new FileInputStream(fileToSend[0].getAbsolutePath());
                                        // Create a socket connection to connect with the server.
                                       Socket socket = new Socket(ip, 1234);
                                        // Create an output stream to write to write to the server over the socket connection.
                                        //Socket socket = null;
                                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                                        // Get the name of the file you want to send and store it in filename.
                                        String fileName = fileToSend[0].getName();
                                        // Convert the name of the file into an array of bytes to be sent to the server.
                                        byte[] fileNameBytes = fileName.getBytes();
                                        // Create a byte array the size of the file so don't send too little or too much data to the server.
                                        byte[] fileBytes = new byte[(int) fileToSend[0].length()];
                                        // Put the contents of the file into the array of bytes to be sent so these bytes can be sent to the server.
                                        fileInputStream.read(fileBytes);
                                        // Send the length of the name of the file so server knows when to stop reading.
                                        dataOutputStream.writeInt(fileNameBytes.length);
                                        // Send the file name.
                                        dataOutputStream.write(fileNameBytes);
                                        // Send the length of the byte array so the server knows when to stop reading.
                                        dataOutputStream.writeInt(fileBytes.length);
                                        // Send the actual file.
                                        dataOutputStream.write(fileBytes);
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        });

                        // Add everything to the frame and make it visible.
                        jFrame.add(jlTitle);
                        jFrame.add(jlFileName);
                        jFrame.add(jpButtond);
                        jFrame.setVisible(true);

                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Server seems to be Unavailable. Make sure you typed in the IP address correctly");
                    JFrame error2 = new JFrame("Error");
                    JPanel ErrorPanel2 = new JPanel();
                    ErrorPanel2.add(new Label("Server seems to be Unavailable. Make sure you typed in the IP address correctly."));
                    error2.getContentPane().add(BorderLayout.NORTH, ErrorPanel2);
                    error2.setResizable(false);
                    error2.setSize(600, 100);
                    error2.setVisible(true);
                    error2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            }


        }



        public boolean isInteger(String input) {
            if (input == null) {
                return false;
            }
            int length = input.length();
            if (length == 0) {
                return false;
            }
            int i = 0;
            if (input.charAt(0) == '-') {
                if (length == 1) {
                    return false;
                }
                i = 1;
            }
            for (; i < length; i++) {
                char c = input.charAt(i);
                if (c <= '/' || c >= ':') {
                    return false;
                }
            }
            try {
                int n = Integer.parseInt(input);
                if (n >= 0 && n <= 255)
                    return true;

                else
                    return false;
            } catch (Exception e) {
                return false;
            }
        }

    }


}
