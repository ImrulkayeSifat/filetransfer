import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {


    // Array list to hold information about the files received.
    static ArrayList<MyFile> myFiles = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        // Used to track the file (jpanel that has the file name in it on a label).
        int fileId = 0;

        // Main container, set the name.
        JFrame jFrame = new JFrame("website Server part");
        // Set the size of the frame.
        jFrame.setSize(550, 650);
        // Give the frame a box layout that stacks its children on top of each other.
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        // When closing the frame also close the program.
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel that will hold the title label and the other jpanels.
        JPanel jPanel = new JPanel();

        // Make the panel that contains everything to stack its child elements on top of eachother.
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        // Make it scrollable when the data gets in jpanel.
        JScrollPane jScrollPane = new JScrollPane(jPanel);
        // Make it so there is always a vertical scrollbar.
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Title above panel.
        JLabel jlTitle = new JLabel(" server"+"  "+InetAddress.getLocalHost().getHostAddress());
        // Change the font of the title.
        jlTitle.setFont(new Font("Arial", Font.BOLD, 18));
        // Add a border around the title for spacing.
        jlTitle.setBorder(new EmptyBorder(30,0,20,0));
        // Center the title horizontally in the middle of the frame.
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add everything to the main GUI.
        jFrame.add(jlTitle);
        jFrame.add(jScrollPane);
        // Make the GUI show up.
        jFrame.setVisible(true);
    //    File fi = new File("C:\\Users\\hasan\\Downloads\\Compressed\\java-send-and-download-a-file-main\\program.txt");

     // String st= fi.getAbsolutePath();
         //       fi.list().forEach(System.out.print("sjgfkej"));

        // Create a server socket that the server will be listening with.
        ServerSocket serverSocket = new ServerSocket(1234);

        // This while loop will run forever so the server will never stop unless the application is closed.
        while (true) {

            try {
                // Wait for a client to connect and when they do create a socket to communicate with them.
                Socket socket = serverSocket.accept();

                // Stream to receive data from the client through the socket.
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                System.out.println("connected");

                // Read the size of the file name so know when to stop reading.
                int fileNameLength = dataInputStream.readInt();
                // If the file exists
                if (fileNameLength > 0) {
                    // Byte array to hold name of file.
                    byte[] fileNameBytes = new byte[fileNameLength];
                    // Read from the input stream into the byte array.
                    dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);
                    // Create the file name from the byte array.
                    String fileName = new String(fileNameBytes);
                    // Read how much data to expect for the actual content of the file.
                    int fileContentLength = dataInputStream.readInt();
                    // If the file exists.
                    if (fileContentLength > 0) {
                        // Array to hold the file data.
                        byte[] fileContentBytes = new byte[fileContentLength];
                        // Read from the input stream into the fileContentBytes array.
                        dataInputStream.readFully(fileContentBytes, 0, fileContentBytes.length);
                        // Panel to hold the picture and file name.
                        JPanel jpFileRow = new JPanel();
                        jpFileRow.setLayout(new BoxLayout(jpFileRow, BoxLayout.X_AXIS));
                        // Set the file name.
                        JLabel jlFileName = new JLabel(fileName);
                        jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
                        jlFileName.setBorder(new EmptyBorder(10,0, 10,0));

                        // server default path C:\Users\hasan\Downloads\Compressed\ventoy-1.0.40
                        File fileToDownloa = new File(fileName);

                        try {
                            // Create a stream to write data to the file.
                            FileOutputStream fileOutputStream = new FileOutputStream(fileToDownloa);

                            // Write the actual file data to the file.
                            fileOutputStream.write(fileContentBytes);

                            // Close the stream.
                            fileOutputStream.close();
                            // Get rid of the jFrame. after the user clicked yes.
                            //jFrame.dispose();



                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        if (getFileExtension(fileName).equalsIgnoreCase("txt")) {
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
                        myFiles.add(new MyFile(fileId,fileName,fileContentBytes,getFileExtension(fileName)));
                        // Increment the fileId for the next file to be received.
                        fileId++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param fileName
     * @return The extension type of the file.
     */
    public static String getFileExtension(String fileName) {
        // Get the file type by using the last occurence of . (for example aboutMe.txt returns txt).
        // Will have issues with files like myFile.tar.gz.
        int i = fileName.lastIndexOf('.');
        // If there is an extension.
        if (i > 0) {
            // Set the extension to the extension of the filename.
            return fileName.substring(i + 1);
        } else {
            return "No extension found.";
        }
    }


    public static MouseListener getMyMouseListener() {
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

    public static JFrame createFrame(String fileName, byte[] fileData, String fileExtension) {

        // Frame to hold everything.
        JFrame jFrame = new JFrame(" File Downloader");
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
       // jlPrompt.setBorder(new EmptyBorder(5,0,5,0));
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
       // jpButtons.setBorder(new EmptyBorder(5, 0, 5, 0));
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
      //  jpButtonsd.setBorder(new EmptyBorder(5, 0, 5, 0));
        // Add the yes and no buttons.

        jpButtonsd.add(jbYesd);
        jpButtonsd.add(jbNod);

        // If the file is a text file then display the text.
        if (fileExtension.equalsIgnoreCase("txt")) {
            // Wrap it with <html> so that new lines are made.
            jlFileContent.setText("<html>" + new String(fileData) + "</html>");
            // If the file is not a text file then make it an image.
        } else {
            jlFileContent.setIcon(new ImageIcon(fileData));
          //  jlFileContent.setBorder(new EmptyBorder(50, 0, 50, 0));

            jlFileContent.setPreferredSize(new Dimension(470, 430));

            //jlFileContent.setSize(300,300,fileData);

        }

        // Yes so download file.
        jbYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
//
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


}
