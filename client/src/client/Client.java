
package client;

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


/**
 *
 * @author hasan
 */
public class Client {

  
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

        try{
    UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
}catch(Exception ee){
    System.out.println(ee);
}
        nameAndIpframe = new JFrame("client part");
      //  nameAndIpframe.setBackground(new Color(150,203,253));
        nameAndIpframe.setResizable(false);
        JPanel nameAndIpPanel = new JPanel(new FlowLayout());
        nameAndIpPanel.setBackground(new Color(146, 222, 133));

        nameAndIpPanel.add(new JLabel("                                                           "));
        nameAndIpPanel.add(new JLabel("                                                           "));
        
        nameAndIpPanel.add(new JLabel("                                                           "));
        nameAndIpPanel.add(new JLabel("                                                           "));
        nameAndIpPanel.add(new JLabel("  Name" ,SwingConstants.CENTER));
       // nameAndIpPanel.add(new JLabel("                                                         "));

        nameTextField = new JTextField(15);
        name = nameTextField.getText();
        nameAndIpPanel.add(nameTextField);

 nameAndIpPanel.add(new JLabel("                                                          "));
        nameAndIpPanel.add(new JLabel("                                                           "));
      nameAndIpPanel.add(new JLabel("       IP" ,SwingConstants.CENTER));

        
        nameAndIpPanel.add(new JLabel(""));
        ip1 = new JTextField(4);
        ip2 = new JTextField(4);
        ip3 = new JTextField(4);
        ip4 = new JTextField(4);
        port=new JTextField(4);
        nameAndIpPanel.add(ip1);
        nameAndIpPanel.add(new Label(""));
        nameAndIpPanel.add(ip2);
        nameAndIpPanel.add(new Label(""));
        nameAndIpPanel.add(ip3);
        nameAndIpPanel.add(new Label(""));
        nameAndIpPanel.add(ip4);
        nameAndIpPanel.add(new JLabel("                                         "));
         nameAndIpPanel.add(new JLabel("                                                          "));
        nameAndIpPanel.add(new JLabel("                                            "));
       
        nameAndIpPanel.add(new JLabel("port"));
        //nameAndIpPanel.add(new Label(""));
        nameAndIpPanel.add(port);
        nameAndIpPanel.add(new JLabel("                                         "));
        
           nameAndIpPanel.add(new JLabel("                "));
               nameAndIpPanel.add(new JLabel("                                                          "));
        nameAndIpPanel.add(new JLabel("                                                    "));
       
      
        JButton sendNameAndIp = new JButton("connect now");
         sendNameAndIp.setPreferredSize(new Dimension(100, 50));
        sendNameAndIp.setBackground(new Color(123,124,125));
        //sendNameAndIp.setForeground(new Color(223,124,225));
        sendNameAndIp.setFocusable(false);
        
        
        sendNameAndIp.addActionListener(new sendNameAndIpButtonListener());
   
        
        nameAndIpPanel.add(sendNameAndIp);
        nameAndIpframe.getContentPane().add(BorderLayout.CENTER, nameAndIpPanel);
        nameAndIpframe.setSize(400, 400);
        nameAndIpframe.setVisible(true);
        nameAndIpframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    private static class sendNameAndIpButtonListener implements ActionListener {
                               JFrame jFrame = new JFrame("client");
                    JPanel jPanel1 = new JPanel();

        public void actionPerformed(ActionEvent ae) {

            boolean isIpTrue = true;
             boolean isportTrue = true;
              boolean isnameTrue = true;

                 try{
                 if (!isinteger(ip1.getText()))
                    throw new Exception();
               else if (!isinteger(ip2.getText()))
                    throw new Exception();
               else if (!isinteger(ip3.getText()))
                    throw new Exception();
               else if (!isinteger(ip4.getText()))
                    throw new Exception();
                  else
                    isIpTrue=true;
                 
              } catch (Exception e) {
                System.err.println("Invalid  ip");
                JFrame error = new JFrame("Error");
                JPanel ErrorPanel = new JPanel();
                ErrorPanel.add(new Label("Invalid ip"));
                error.getContentPane().add(BorderLayout.NORTH, ErrorPanel);
                error.setResizable(false);
                error.setSize(370, 200);
                error.setVisible(true);
                error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                isIpTrue=false;
            }
            

              if (!isport(port.getText()))
              {
                   System.err.println("Invalid  port");
                JFrame error = new JFrame("Error");
                JPanel ErrorPanel = new JPanel();
                ErrorPanel.add(new Label("Invalid port"));
                error.getContentPane().add(BorderLayout.NORTH, ErrorPanel);
                error.setResizable(false);
                error.setSize(370, 200);
                error.setVisible(true);
                error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                 isportTrue=false; 
              }
              else{ isportTrue=true; 
                
             }  
             
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
                     isnameTrue=false;
                }else
                    isnameTrue=true;
              

            if (isIpTrue && isportTrue && isnameTrue) {
                name = nameTextField.getText();
                ip = ip1.getText() + "." + ip2.getText() + "." + ip3.getText() + "." + ip4.getText();
                portt=port.getText();
                System.out.println(ip);
                System.out.println(portt);
                try {
                   Socket sock = new Socket(ip, Integer.parseInt(portt));

                    System.out.println("Connection with the server Established");
                    nameAndIpframe.setVisible(false);


                  final File[] fileToSend = new File[1];
                       jFrame.setSize(650, 650);
                         jFrame.getContentPane().setBackground(new Color(189, 217, 152) );
                         jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
                        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        
                        JLabel jlTitle = new JLabel(name);
                       jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
                        jlTitle.setBorder(new EmptyBorder(20, 0, 10, 0));
                       jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

                        JLabel jlFileName = new JLabel("Choose a file to send.");
                        jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
                       jlFileName.setBorder(new EmptyBorder(20, 0, 0, 0));
                        jlFileName.setAlignmentX(Component.CENTER_ALIGNMENT);

                    JPanel jpButtond = new JPanel();
                     jpButtond.setBorder(new EmptyBorder(35, 0, 10, 0));
                    jpButtond.setBackground(new Color(148, 186, 97));
                    JButton jbSendFile = new JButton("Send");
                    jbSendFile.setPreferredSize(new Dimension(100, 50));
                    jbSendFile.setBackground(new Color(123,124,124));
                    jbSendFile.setFocusable(false);

                    jbSendFile.setFont(new Font("Arial", Font.BOLD, 20));
                    JButton jbChooseFile = new JButton("Choose");
                    jbChooseFile.setPreferredSize(new Dimension(100, 50));
                    jbChooseFile.setBackground(new Color(123,124,124));
                    jbChooseFile.setFocusable(false);
                    jbChooseFile.setFont(new Font("Arial", Font.BOLD, 20));

                    // Add the buttons to the panel.
                    jpButtond.add(jbSendFile);
                    jpButtond.add(jbChooseFile);
               


//                    java.awt.Container container=jFrame.getContentPane();
//                    BoxLayout boxLayout=new BoxLayout(container,BoxLayout.Y_AXIS);
//                    container.setLayout(boxLayout);

                    
                     JLabel jlTitle1 = new JLabel("server file's");
                    jlTitle1.setFont(new Font("Arial", Font.BOLD, 15));
                    jlTitle1.setBorder(new EmptyBorder(20, 0, 10, 0));
                     JButton refresh = new JButton("refresh");
                             refresh.setPreferredSize(new Dimension(70, 30));
                             refresh.setFont(new Font("Arial", Font.BOLD, 10));
                     JPanel jPanel3 = new JPanel();
                      jPanel3.setBackground(new Color(148, 186, 97));
                    jPanel3.setAlignmentX(Component.RIGHT_ALIGNMENT);
                 
                    jPanel1.setBackground(new Color(253, 203, 214));
                   jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
                    jPanel1.setBounds(0,250,450,450);
                 
                    JScrollPane jScrollPanel = new JScrollPane(jPanel1);
                    jScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                    // client will load all server file from this path
                    File folder = new File("C:\\Users\\hasan\\OneDrive\\Documents\\NetBeansProjects\\server");
                    File[] filetoread = folder.listFiles();

                     for (File file : filetoread) {
                        if (file.isFile()) {
                            System.out.println("files-> " + file.getName());
                            java.lang.String fileName = file.getName();
                            byte[] fileContentBytes = new byte[(int) file.length()];
                     try {
                          FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
                          fileInputStream.read(fileContentBytes);
                      }
                      catch (IOException ex) {
                          ex.printStackTrace();
                      }
                            JPanel jpfileRow = new JPanel();
                            jpfileRow.setLayout(new BoxLayout(jpfileRow, BoxLayout.X_AXIS));
                              jpfileRow.setBackground(new Color(253, 203, 214));
                            jpfileRow.setVisible(true);
                            JLabel jlfileName = new JLabel(fileName);
                            jlfileName.setFont(new Font("Arial", Font.BOLD, 20));
                            jlfileName.setBorder(new EmptyBorder(10, 0, 10, 0));
                            jlfileName.setBackground(new Color(138, 59, 104));
                            jlfileName.setForeground(new Color(223, 124, 225));
                            jlfileName.setVisible(true);
                                 jpfileRow.setName((String.valueOf(fileId)));
                                jpfileRow.addMouseListener(getMyMouseListener());
                                jpfileRow.add(jlfileName);
                                jPanel1.add(jpfileRow);
                              
                                jFrame.validate();
                         
myFiles.add(new MyFile(fileId,fileName,fileContentBytes,getFileExtension(fileName)));
                            fileId++;
                        } else if (file.isDirectory()) {
                            //System.out.println("folder-> "+file.getName());

                        }
                    }

                     //when refresh button pressed it will call update function everytime
                                        refresh.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                 
                                    updatee();
                                                }
                     
                                            });



                     JLabel jlTitle2 = new JLabel("uploaded file's");
                    // Change the font family, size, and style.
                    jlTitle2.setFont(new Font("Arial", Font.BOLD, 15));
                    // Add a border around the label for spacing.
                    jlTitle2.setBorder(new EmptyBorder(20, 0, 5, 0));
                     JPanel jPanel4 = new JPanel();
                       jPanel4.setBackground(new Color(148, 186, 97));
                    jPanel4.setAlignmentX(Component.LEFT_ALIGNMENT);
                   
                    JPanel jPanel = new JPanel();
                    jPanel.setBackground(new Color(223,203,253));

                    jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
               
                     jPanel.setBounds(0,450,500,750);
                    JScrollPane jScrollPane = new JScrollPane(jPanel);
                    // Make it so there is always a vertical scrollbar.
                    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    jScrollPane.setPreferredSize(new Dimension(80,250));
                    jScrollPanel.setPreferredSize(new Dimension(80,250));


                  jScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);

                  jScrollPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);



                    // Button action for choosing the file.
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
                                       Socket socket = new Socket(ip, Integer.parseInt(portt));
                                        // Create an output stream to write to write to the server over the socket connection.
                                        //Socket socket = null;
                                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                                        // Get the name of the file you want to send and store it in filename.
                                        String fileName = fileToSend[0].getName();
                                        // Convert the name of the file into an array of bytes to be sent to the server.
                                        byte[] fileNameBytes = fileName.getBytes();
                                        // Create a byte array the size of the file so don't send too little or too much data to the server.
                                        byte[] fileContentBytes = new byte[(int) fileToSend[0].length()];
                                        // Put the contents of the file into the array of bytes to be sent so these bytes can be sent to the server.
                                        fileInputStream.read(fileContentBytes);
                                        // Send the length of the name of the file so server knows when to stop reading.
                                        dataOutputStream.writeInt(fileNameBytes.length);
                                        // Send the file name.
                                        dataOutputStream.write(fileNameBytes);
                                        // Send the length of the byte array so the server knows when to stop reading.
                                        dataOutputStream.writeInt(fileContentBytes.length);
                                        // Send the actual file.
                                        dataOutputStream.write(fileContentBytes);



                                        //jpanel for server  file list
                                          JPanel jpfileRow = new JPanel();
                                        jpfileRow.setLayout(new BoxLayout(jpfileRow, BoxLayout.X_AXIS));
                                          jpfileRow.setBackground(new Color(253, 203, 214));
                                        jpfileRow.setVisible(true);
                                         // Set the file name.
                                        JLabel jlfileName = new JLabel(fileName);
                                        jlfileName.setFont(new Font("Arial", Font.BOLD, 20));
                                        jlfileName.setBorder(new EmptyBorder(10, 0, 10, 0));
                                        jlfileName.setForeground(new Color(223, 124, 225));
                                        jlfileName.setVisible(true);
                            
                                        //jpanel for client sended file list
                                        JPanel jpFileRow = new JPanel();
                                        jpFileRow.setLayout(new BoxLayout(jpFileRow, BoxLayout.X_AXIS));
                                        jpFileRow.setBackground(new Color(223,203,253));

                                        jpFileRow.setVisible(true);
                                        // Set the file name.
                                        JLabel jlFileName = new JLabel(fileName);
                                        jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
                                        jlFileName.setBorder(new EmptyBorder(10,0, 10,0));
                                        jlFileName.setForeground(new Color(223,124,225));
                                   

                                        jlFileName.setVisible(true);

                                            jpFileRow.setName((String.valueOf(fileId)));
                                           // jpFileRow.addMouseListener(getMyMouseListener());
                                            // Add everything.
                                            jpFileRow.add(jlFileName);
                                            jPanel.add(jpFileRow);
                                         
                                           jpfileRow.setName((String.valueOf(fileId)));
                                            jpfileRow.addMouseListener(getMyMouseListener());
                                           updatee();  
                                           
                                            jFrame.validate();
                          myFiles.add(new MyFile(fileId,fileName,fileContentBytes,getFileExtension(fileName)));

                                        fileId++;



                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }                    
                    
                        });


                        // Add everything to the frame and make it visible.
                        jFrame.add(jlTitle);
                        jFrame.add(jlFileName);
                            
                           jPanel4.add(jlTitle2);
                    jFrame.add(jPanel4);
                          jFrame.add(jScrollPane);
                 //   container.add(jScrollPane);
                    //container.add(Box.createRigidArea(new Dimension(0,40)));
                    //container.add(jScrollPanel);
                    //  jFrame.add(jPanel);
                     jPanel3.add(jlTitle1);// jlTitle1.setAlignmentX(Component.LEFT_ALIGNMENT);
                    jPanel3.add(refresh);
                     jFrame.add(jPanel3);
                    jFrame.add(jScrollPanel);
                    //jFrame.add(jPanel1);
                    jFrame.add(jpButtond);
                  //  container.add(jScrollPane);
                    //container.add(jScrollPanel);
                    jFrame.setVisible(true);

                } catch (IOException e) {
                   e.printStackTrace();
                    System.err.println("Server seems to be Unavailable. Make sure you typed in the IP address correctly");
                    JFrame error2 = new JFrame("Error");
                    JPanel ErrorPanel2 = new JPanel();
                    ErrorPanel2.add(new Label("Server seems to be Unavailable. Make sure you typed in the IP address correctly. bye"));
                    error2.getContentPane().add(BorderLayout.NORTH, ErrorPanel2);
                    error2.setResizable(false);
                    error2.setSize(600, 200);
                    error2.setVisible(true);
                    error2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            }


        }

      

          public boolean isinteger(String input) {
            if (input == null) {
                return false;
            }
            int length = input.length();
            if (length == 0) {
                return false;
            }

            try {
                int n = Integer.parseInt(input);
                 if ((n >= 0 && n <= 255))
                    return true;
                else
                    return false;
            } catch (Exception e) {
                return false;
            }
        }
           
            private boolean isport(String input) {
                if (input == null) {
                return false;
            }
            int length = input.length();
            if (length == 0) {
                return false;
            }
          
            try {
                int n = Integer.parseInt(input);
                 if ((n ==1234))
                    return true;
                else
                    return false;
            } catch (Exception e) {
                return false;
            } 
                
        }
          
          
          
          private void updatee() {
                 jPanel1.updateUI();
                                    jPanel1.removeAll();
                                    fileId=0;
                                      myFiles.clear();
                        File folder = new File("C:\\Users\\hasan\\OneDrive\\Documents\\NetBeansProjects\\server");
                    File[] filetoread = folder.listFiles();

                    for (File file : filetoread) {
                        if (file.isFile()) {
                            System.out.println("files-> " + file.getName());
                            java.lang.String fileName = file.getName();
                            byte[] fileContentBytes = new byte[(int) file.length()];
                                     try {
                          FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
                          fileInputStream.read(fileContentBytes);
                      }
                      catch (IOException ex) {
                          ex.printStackTrace();
                      }
                            JPanel jpfileRow = new JPanel();
                            jpfileRow.setLayout(new BoxLayout(jpfileRow, BoxLayout.X_AXIS));
                              jpfileRow.setBackground(new Color(253, 203, 214));
                            jpfileRow.setVisible(true);
                            // Set the file name.
                            JLabel jlfileName = new JLabel(fileName);
                            jlfileName.setFont(new Font("Arial", Font.BOLD, 20));
                            jlfileName.setBorder(new EmptyBorder(10, 0, 10, 0));
                            jlfileName.setBackground(new Color(138, 59, 104));
                            jlfileName.setForeground(new Color(223, 124, 225));
                            //  jlFileName.setFocusable(false);
                            jlfileName.setVisible(true);

                         
                                jpfileRow.setName((String.valueOf(fileId)));
                              
                                jpfileRow.addMouseListener(getMyMouseListener());
                                jpfileRow.add(jlfileName);
                                jPanel1.add(jpfileRow);
                              
                                jFrame.validate();
                           
myFiles.add(new MyFile(fileId,fileName,fileContentBytes,getFileExtension(fileName)));
                         

                            fileId++;
                        } else if (file.isDirectory()) {
                            //System.out.println("folder-> "+file.getName());

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

                                    private JFrame createFrame(String fileName, byte[] fileData, String fileExtension) {
                                    
                                            // Frame to hold everything.
                                            JFrame jFrame = new JFrame(" File Downloader/ delete");
                                            jFrame.setSize(700, 700);

                                            // Panel to hold everything.
                                            JPanel jPanel = new JPanel();
                                            jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

                                            // Title above panel.
                                            JLabel jlTitle = new JLabel(" File Downloader");
                                            jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
                                            jlTitle.setFont(new Font("Arial", Font.BOLD, 20));
                                            jlTitle.setBorder(new EmptyBorder(5,0,5,0));

                                            // Label to prompt the user if they are sure they want to download the file.
                                            JLabel jlPrompt = new JLabel("Are you sure you want to download " + fileName + "?");
                                            jlPrompt.setFont(new Font("Arial", Font.BOLD, 15));
                                            jlPrompt.setBorder(new EmptyBorder(5,0,5,0));
                                           jlPrompt.setAlignmentX(Component.CENTER_ALIGNMENT);

                                            // Create the yes for accepting the download.
                                            JButton jbYes = new JButton("Yes");
                                            jbYes.setPreferredSize(new Dimension(80, 30));
                                            jbYes.setFont(new Font("Arial", Font.BOLD, 10));

                                            // No button for rejecting the download.
                                            JButton jbNo = new JButton("No");
                                            jbNo.setPreferredSize(new Dimension(70, 30));
                                            jbNo.setFont(new Font("Arial", Font.BOLD, 10));


                                            // Panel to hold the yes and no buttons and make the next to each other left and right.
                                            JPanel jpButtons = new JPanel();
                                            // Add spacing around the panel.
                                            jpButtons.setBorder(new EmptyBorder(5, 0, 5, 0));
                                            // Add the yes and no buttons.

                                            jpButtons.add(jbYes);
                                          jpButtons.add(jbNo);
                                          


                                            // Label to hold the content of the file whether it be text of images.
                                            JLabel jlFileContent = new JLabel();
                                            // Align the label horizontally.
                                            jlFileContent.setAlignmentX(Component.CENTER_ALIGNMENT);
 
                                         
                                            // If the file is a text file then display the text.
                                            if (fileExtension.equals("txt")) {
                                                // Wrap it with <html> so that new lines are made.
                                                jlFileContent.setText("<html>" + new String(fileData) + "</html>");
                                                // If the file is not a text file then make it an image.
                                            } else {
                                               jlFileContent.setIcon(new ImageIcon(fileData));
                                                jlFileContent.setPreferredSize(new Dimension(290, 330));
                                          
                                            }
                                            System.out.println(fileData);

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
                                                      
                                                        FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
                                                      
                                                        fileOutputStream.write(fileData);
                                                      
                                                        fileOutputStream.close();
                                                       
                                                       
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
                                                  
                                                    jFrame.dispose();
                                                }
                                            });
                                        
                                            jPanel.add(jlTitle);

                                            jPanel.add(jlPrompt);
                          
                                            jPanel.add(jlFileContent);
                                                 jPanel.add(jpButtons);                                       

                                            jFrame.add(jPanel);

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


        private String getFileExtension(String fileName) {
             
                                int i = fileName.lastIndexOf('.');
                             
                                if (i > 0) {
                               return fileName.substring(i + 1);
                                } else {
                                    return "No extension found.";
                                }
       }
    }
}
