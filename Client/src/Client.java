
import Climyfile.MyFile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.Socket;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

public class Client {

   // static Socket sock;
    static PrintWriter writer;
    static JTextArea incoming;
    static JTextField outgoing;
    static BufferedReader reader;
    static String name;
    static String ip;
    static String portt;
    static JTextField ip1;
    static JTextField ip2;
    static JTextField ip3;
    static JTextField ip4;
    static JTextField port;
    static JFrame nameAndIpframe;
    static JTextField nameTextField;
    static int fileId=0;


    static ArrayList<MyFile> myFiles = new ArrayList<>();
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
        port=new JTextField(4);
        nameAndIpPanel.add(ip1);
        nameAndIpPanel.add(new Label("."));
        nameAndIpPanel.add(ip2);
        nameAndIpPanel.add(new Label("."));
        nameAndIpPanel.add(ip3);
        nameAndIpPanel.add(new Label("."));
        nameAndIpPanel.add(ip4);
        nameAndIpPanel.add(new JLabel("                                         "));
        nameAndIpPanel.add(new JLabel("port    "));
        nameAndIpPanel.add(new Label("."));
        nameAndIpPanel.add(port);
        nameAndIpPanel.add(new JLabel("                                         "));
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
                if (!isInteger(port.getText()))
                    throw new Exception();
                if (nameTextField.getText().equals("")) {
                   // nameTextField.setText("you've not set your name ");

                    JFrame error = new JFrame("Error");
                    JPanel ErrorPanel = new JPanel();
                    ErrorPanel.add(new Label("you've not set your name "));
                    error.getContentPane().add(BorderLayout.NORTH, ErrorPanel);
                    error.setResizable(false);
                    error.setSize(370, 200);
                    error.setVisible(true);
                    error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } else {
                    isIpTrue = true;
                }

            } catch (Exception e) {
                System.err.println("Invalid  port");
                JFrame error = new JFrame("Error");
                JPanel ErrorPanel = new JPanel();
                ErrorPanel.add(new Label("Invalid port"));
                error.getContentPane().add(BorderLayout.NORTH, ErrorPanel);
                error.setResizable(false);
                error.setSize(370, 200);
                error.setVisible(true);
                error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }

            if (isIpTrue) {
                name = nameTextField.getText();
                ip = ip1.getText() + "." + ip2.getText() + "." + ip3.getText() + "." + ip4.getText();
                portt=port.getText();
                System.out.println(ip);
                System.out.println(portt);
                try {
                   Socket sock = new Socket(ip, Integer.parseInt(portt));

                    System.out.println("Connection with the server Established");
                    nameAndIpframe.setVisible(false);



                        // Accessed from within inner class needs to be final or effectively final.
                        final File[] fileToSend = new File[1];
                   // final File[] fileTodown = new File[1];
                        // Set the frame to house everything.
                        JFrame jFrame = new JFrame("website Client part");
                        // Set the size of the frame.
                        jFrame.setSize(550, 650);
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




                    // Panel that will hold the title label and the other jpanels.
                    JPanel jPanel = new JPanel();

                    // Make the panel that contains everything to stack its child elements on top of eachother.
                    jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

                    // Make it scrollable when the data gets in jpanel.
                    JScrollPane jScrollPane = new JScrollPane(jPanel);
                    // Make it so there is always a vertical scrollbar.
                    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);






                    // This is an inner class so we need the fileToSend to be final.
                        jbChooseFile.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Create a file chooser to open the dialog to choose a file.
                                JFileChooser jFileChooser = new JFileChooser();
                                // Set the title of the dialog.
                                jFileChooser.setCurrentDirectory(new java.io.File("."));

                                jFileChooser.setDialogTitle("Choose a file to send.");
                                // Show the dialog and if a file is chosen from the file chooser execute the following statements.
                                if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                                    // Get the selected file.
                                    fileToSend[0] = jFileChooser.getSelectedFile();
                                    System.out.println("path file: " + fileToSend[0].getAbsolutePath());

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



                                        JPanel jpFileRow = new JPanel();
                                        jpFileRow.setLayout(new BoxLayout(jpFileRow, BoxLayout.X_AXIS));
                                        jpFileRow.setVisible(true);
                                        // Set the file name.
                                        JLabel jlFileName = new JLabel(fileName);
                                        jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
                                        jlFileName.setBorder(new EmptyBorder(10,0, 10,0));
                                        jlFileName.setVisible(true);


                                        if (getFileExtension(fileName).equals("txt")) {
                                            // Set the name to be the fileId so you can get the correct file from the panel.
                                            jpFileRow.setName((String.valueOf(fileId)));
                                            jpFileRow.addMouseListener(getMyMouseListener());
                                            // Add everything.
                                            jpFileRow.add(jlFileName);
                                            jPanel.add(jpFileRow);
                                            jFrame.validate();
                                        } else {
                                            // Set the name to be the fileId so you can get the correct file from the panel.
                                            jpFileRow.setName((String.valueOf(fileId)));
                                            // Add a mouse listener so when it is clicked the popup appears.
                                            jpFileRow.addMouseListener(getMyMouseListener());
                                            // Add the file name and pic type to the panel and then add panel to parent panel.
                                            jpFileRow.add(jlFileName);
                                            jPanel.add(jpFileRow);
                                            // Perform a relayout.
                                            jFrame.validate();
                                        }

                                        // Add the new file to the array list which holds all our data.
                                        myFiles.add(new MyFile(fileId,fileName,fileBytes,getFileExtension(fileName)));
                                        // Increment the fileId for the next file to be received.
                                        fileId++;



                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }

                            private MouseListener getMyMouseListener() {
                                return new MouseListener() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        // Get the source of the click which is the JPanel.
                                        JPanel jPanel = (JPanel) e.getSource();
                                        // Get the ID of the file.
                                        int fileId = Integer.parseInt(jPanel.getName());
                                        // Loop through the file storage and see which file is the selected one.
                                        for (MyFile myFile : myFiles) {
                                            if (myFile.getId() == fileId) {
                                                JFrame jfPreview = createFrame(myFile.getName(), myFile.getData(), myFile.getFileExtension());
                                                jfPreview.setVisible(true);
                                            }
                                        }
                                    }

                                    private JFrame createFrame(String fileName, byte[] fileData, Object fileExtension) {
                                     //   public static JFrame createFrame(String fileName, byte[] fileData, String fileExtension) {

                                            // Frame to hold everything.
                                            JFrame jFrame = new JFrame(" File Downloader/ delete");
                                            // Set the size of the frame.
                                            jFrame.setSize(700, 700);

                                            // Panel to hold everything.
                                            JPanel jPanel = new JPanel();
                                            // Make the layout a box layout with child elements stacked on top of each other.
                                            jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

                                            // Title above panel.
                                            JLabel jlTitle = new JLabel(" File Downloader");
                                            // Center the label title horizontally.
                                            jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
                                            // Change the font family, size, and style.
                                            jlTitle.setFont(new Font("Arial", Font.BOLD, 20));
                                            // Add spacing on the top and bottom of the element.
                                            jlTitle.setBorder(new EmptyBorder(5,0,5,0));

                                            // Label to prompt the user if they are sure they want to download the file.
                                            JLabel jlPrompt = new JLabel("Are you sure you want to download " + fileName + "?");
                                            // Change the font style, size, and family of the label.
                                            jlPrompt.setFont(new Font("Arial", Font.BOLD, 15));
                                            // Add spacing on the top and bottom of the label.
                                            jlPrompt.setBorder(new EmptyBorder(5,0,5,0));
                                            // Center the label horizontally.
                                            jlPrompt.setAlignmentX(Component.CENTER_ALIGNMENT);

                                            // Create the yes for accepting the download.
                                            JButton jbYes = new JButton("Yes");
                                            jbYes.setPreferredSize(new Dimension(80, 30));
                                            // Set the font for the button.
                                            jbYes.setFont(new Font("Arial", Font.BOLD, 10));

                                            // No button for rejecting the download.
                                            JButton jbNo = new JButton("No");
                                            // Change the size of the button must be preferred because if not the layout will ignore it.
                                            jbNo.setPreferredSize(new Dimension(70, 30));
                                            // Set the font for the button.
                                            jbNo.setFont(new Font("Arial", Font.BOLD, 10));


                                            // Panel to hold the yes and no buttons and make the next to each other left and right.
                                            JPanel jpButtons = new JPanel();
                                            // Add spacing around the panel.
                                          //  jpButtons.setBorder(new EmptyBorder(5, 0, 5, 0));
                                            // Add the yes and no buttons.

                                            jpButtons.add(jbYes);
                                            jpButtons.add(jbNo);


                                            // Label to hold the content of the file whether it be text of images.
                                            JLabel jlFileContent = new JLabel();
                                            // Align the label horizontally.
                                            jlFileContent.setAlignmentX(Component.CENTER_ALIGNMENT);


                                            //for delete
                                            // Label to prompt the user if they are sure they want to download the file.
                                            JLabel jlPrompt1 = new JLabel("Are you sure you want to delete " + fileName + "?");
                                            // Change the font style, size, and family of the label.
                                            jlPrompt1.setFont(new Font("Arial", Font.BOLD, 15));
                                            // Add spacing on the top and bottom of the label.
                                            jlPrompt1.setBorder(new EmptyBorder(5,0,5,0));
                                            // Center the label horizontally.
                                            jlPrompt1.setAlignmentX(Component.CENTER_ALIGNMENT);

                                            // Create the yes for accepting the download.
                                            JButton jbYesd = new JButton("Yes");
                                            jbYesd.setPreferredSize(new Dimension(80, 30));
                                            // Set the font for the button.
                                            jbYesd.setFont(new Font("Arial", Font.BOLD, 10));

                                            // No button for rejecting the download.
                                            JButton jbNod = new JButton("No");
                                            // Change the size of the button must be preferred because if not the layout will ignore it.
                                            jbNod.setPreferredSize(new Dimension(70, 30));
                                            // Set the font for the button.
                                            jbNod.setFont(new Font("Arial", Font.BOLD, 10));





                                            // Panel to hold the yes and no buttons and make the next to each other left and right.
                                            JPanel jpButtonsd = new JPanel();
                                            // Add spacing around the panel.
                                        //    jpButtonsd.setBorder(new EmptyBorder(5, 0, 5, 0));
                                            // Add the yes and no buttons.

                                            jpButtonsd.add(jbYesd);
                                            jpButtonsd.add(jbNod);

                                            // If the file is a text file then display the text.
                                            if (fileExtension.equals("txt")) {
                                                // Wrap it with <html> so that new lines are made.
                                                jlFileContent.setText("<html>" + new String(fileData) + "</html>");
                                                // If the file is not a text file then make it an image.
                                            } else {
                                               jlFileContent.setIcon(new ImageIcon(fileData));
                                                jlFileContent.setPreferredSize(new Dimension(470, 430));
                                                //fileData.getScaledInstance(Image.SCALE_SMOOTH);
                                               // Image resizedImage =
                                                //        fileData.getScaledInstance(500,500,Image.SCALE_SMOOTH);
                                              //  jlFileContent.setIcon(new ImageIcon(resizedImage));

                                            }

                                            // Yes so download file.
                                            jbYes.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    // Create the file with its name.


                                                    final File[] fileTodown = new File[1];
                                                    JFileChooser jFileChooser = new JFileChooser();
                                                    // Get the selected file.
                                                    jFileChooser.setCurrentDirectory(new java.io.File("."));
                                                    jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                                                    if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                                                        //System.out.println("getCurrentDirectory(): " + jFileChooser.getCurrentDirectory());
                                                        System.out.println("Selected Folder for download is : " + jFileChooser.getSelectedFile());
                                                    } else {
                                                        System.out.println("No Selection ");
                                                    }

                                                    fileTodown[0] = jFileChooser.getSelectedFile();


                                                    File fileToDownload = new File(fileTodown[0],fileName);
                                                    try {
                                                        // Create a stream to write data to the file.
                                                        FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
                                                        // Write the actual file data to the file.
                                                        fileOutputStream.write(fileData);
                                                        // Close the stream.
                                                        fileOutputStream.close();
                                                        // Get rid of the jFrame. after the user clicked yes.
                                                        jFrame.dispose();
                                                    } catch (IOException ex) {
                                                        ex.printStackTrace();
                                                    }

                                                }
                                            });

                                            // No so close window in download.
                                            jbNo.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    // User clicked no so don't download the file but close the jframe.
                                                    jFrame.dispose();
                                                }
                                            });





                                            //yes for delete
                                            jbYesd.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    // Create the file with its name.
                                                    File fileToDelete = new File(fileName);
                                                    if(fileToDelete.delete()) {
                                                        //jpFileRow.setVisible(false);
                                                        //jlFileName.setVisible(false);
                                                        // fileToDelete.setVisible(false);
                                                        //fileName.setVisible(false);
                                                        //nameTextField.setVisible(false);

                                                        // Label to prompt the user if they are sure they want to download the file.
                                                    /*    JLabel jlPrompt2 = new JLabel("Are you sure you want to download " + fileName + "?");
                                                        // Change the font style, size, and family of the label.
                                                        jlPrompt2.setFont(new Font("Arial", Font.BOLD, 15));
                                                        // Add spacing on the top and bottom of the label.
                                                        jlPrompt2.setBorder(new EmptyBorder(5,0,5,0));
                                                        // Center the label horizontally.
                                                        jlPrompt2.setAlignmentX(Component.CENTER_ALIGNMENT);

                                                     */

                                                    } else {
                                                        System.out.println("delete not done");

                                                        // Label to prompt the user if they are sure they want to download the file.
                                                      /*  JLabel jlPrompt3 = new JLabel("Are you sure you want to download " + fileName + "?");
                                                        // Change the font style, size, and family of the label.
                                                        jlPrompt3.setFont(new Font("Arial", Font.BOLD, 15));
                                                        // Add spacing on the top and bottom of the label.
                                                        jlPrompt3.setBorder(new EmptyBorder(5,0,5,0));
                                                        // Center the label horizontally.
                                                        jlPrompt3.setAlignmentX(Component.CENTER_ALIGNMENT);

                                                       */


                                                    }
                                                    //jpFileRow.add(jlFileName);

                                                }
                                            });





                                            // No so close window in delete.
                                            jbNod.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    // User clicked no so don't download the file but close the jframe.
                                                    jFrame.dispose();
                                                }
                                            });

                                            // Add everything to the panel before adding to the frame.
                                            jPanel.add(jlTitle);

                                            jPanel.add(jlPrompt);
                                            jPanel.add(jpButtons);

                                            jPanel.add(jlFileContent);

                                            jPanel.add(jlPrompt1);
                                            jPanel.add(jpButtonsd);

                                            // Add panel to the frame.
                                            jFrame.add(jPanel);

                                            // Return the jFrame so it can be passed the right data and then shown.
                                            return jFrame;



                                    }

                                    @Override
                                    public void mousePressed(MouseEvent e) {

                                    }

                                    @Override
                                    public void mouseReleased(MouseEvent e) {

                                    }

                                    @Override
                                    public void mouseEntered(MouseEvent e) {

                                    }

                                    @Override
                                    public void mouseExited(MouseEvent e) {

                                    }
                                };
                            }

                            private Object getFileExtension(String fileName) {
                                int i = fileName.lastIndexOf('.');
                                // If there is an extension.
                                if (i > 0) {
                                    // Set the extension to the extension of the filename.
                                    return fileName.substring(i + 1);
                                } else {
                                    return "No extension found.";
                                }
                            }


                        });






                        // Add everything to the frame and make it visible.
                        jFrame.add(jlTitle);
                        jFrame.add(jlFileName);
                        jFrame.add(jpButtond);
                    jFrame.add(jScrollPane);
                        jFrame.setVisible(true);

                } catch (IOException e) {
                   e.printStackTrace();
                    System.err.println("Server seems to be Unavailable. Make sure you typed in the IP address correctly");
                    JFrame error2 = new JFrame("Error");
                    JPanel ErrorPanel2 = new JPanel();
                    ErrorPanel2.add(new Label("Server seems to be Unavailable. Make sure you typed in the IP address correctly."));
                    error2.getContentPane().add(BorderLayout.NORTH, ErrorPanel2);
                    error2.setResizable(false);
                    error2.setSize(600, 200);
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
                if ((n >= 0 && n <= 255)||n==1234)
                    return true;

                else
                    return false;
            } catch (Exception e) {
                return false;
            }
        }

    }





}
