package server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;
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


/**
 *
 * @author hasan
 */
public class Server {

   
    static int fileId = 0;
     static ArrayList<MyFile> myFiles = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        
        try{
    UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
}catch(Exception ee){
    System.out.println(ee);
}
        
           JFrame jFramee = new JFrame("website Server part");
             jFramee.setSize(550, 650);
            jFramee.setLayout(new BoxLayout(jFramee.getContentPane(), BoxLayout.Y_AXIS));
            jFramee.getContentPane().setBackground(new Color(152, 217, 186) );
            jFramee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
            
              JPanel jPanell = new JPanel();
            jPanell.setBackground(new Color(189, 217, 152));

            jPanell.setLayout(new BoxLayout(jPanell, BoxLayout.Y_AXIS));

            JScrollPane jScrollPane = new JScrollPane(jPanell);
            jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

          //  portt=(int) (Math.random()*(9999 - 1111)) + 1111;
            JLabel jlTitle = new JLabel(" server" + "  " + InetAddress.getLocalHost().getHostAddress()+"  port 1234");
            jlTitle.setFont(new Font("Arial", Font.BOLD, 18));
           jlTitle.setBorder(new EmptyBorder(30, 0, 20, 0));
            jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            
             JButton ref = new JButton("refresh");
               ref.setPreferredSize(new Dimension(50, 30));
                 ref.setAlignmentX(Component.LEFT_ALIGNMENT);
                  ref.setFont(new Font("Arial", Font.BOLD, 15));

            // Add everything to the main GUI.
            jFramee.add(jlTitle,ref);
            jFramee.add(ref);
            jFramee.add(jScrollPane);
        
            jFramee.setVisible(true);
           
// when server start load files from current path            
//    File fi = new File("C:\\Users\\hasan\\Downloads\\Compressed\\java-send-and-download-a-file-main\\program.txt");
             
                                  File folder = new File(System.getProperty("user.dir"));
            File[] filetoread = folder.listFiles();

            for (File file : filetoread) {
                if (file.isFile()) {
                    System.out.println("files-> fff  " + file.getName());
                    String fileName = file.getName();
                    byte[] fileContentBytes = new byte[(int) file.length()];
                        try {
                          FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
                          fileInputStream.read(fileContentBytes);
                      }
                      catch (IOException ex) {
                          ex.printStackTrace();
                      }
                    JPanel jpFileRow = new JPanel();
                    jpFileRow.setLayout(new BoxLayout(jpFileRow, BoxLayout.X_AXIS));
                jpFileRow.setBackground(new Color(189, 217, 152));
                  
                    JLabel jlFileName = new JLabel(fileName);
                    jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
                    jlFileName.setBorder(new EmptyBorder(10, 0, 10, 0));
             
                    jlFileName.setForeground(new Color(223, 124, 225));
                  

                        jpFileRow.setName((String.valueOf(fileId)));
                        jpFileRow.addMouseListener(getMyMouseListener());
                        // Add everything.
                        jpFileRow.add(jlFileName);
                        jPanell.add(jpFileRow);
                        jFramee.validate();
                  
             
                    myFiles.add(new MyFile(fileId, fileName, fileContentBytes, getFileExtension(fileName)));
               //  System.out.println("1 "+fileId+"  "+"  "+fileName+"  "+fileContentBytes+"  "+getFileExtension(fileName));

               
                    fileId++;
                } else if (file.isDirectory()) {
                

                }
            }

            //refresh butoon call
            ref.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             
               updatee();
            }
            
            //when refresh button press everytime update will execute 
            //and load all the files in server folder
            private void updatee() {
           
              jPanell.updateUI();                  
                 jPanell.removeAll();
                 fileId=0;
                 myFiles.clear();
            File folder = new File(System.getProperty("user.dir"));
            File[] filetoread = folder.listFiles();
            for (File file : filetoread) {
                if (file.isFile()) {
                    System.out.println("files-> " + file.getName());
                    String fileName = file.getName();
                    byte[] fileContentBytes = new byte[(int) file.length()];
                        try {
                          FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
                          fileInputStream.read(fileContentBytes);
                      }
                      catch (IOException ex) {
                          ex.printStackTrace();
                      }
                    JPanel jpFileRow = new JPanel();
                    jpFileRow.setLayout(new BoxLayout(jpFileRow, BoxLayout.X_AXIS));
                jpFileRow.setBackground(new Color(189, 217, 152));
                 
                    JLabel jlFileName = new JLabel(fileName);
                    jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
                    jlFileName.setBorder(new EmptyBorder(10, 0, 10, 0));
                  
                    jlFileName.setForeground(new Color(223, 124, 225));
               

                        jpFileRow.setName((String.valueOf(fileId)));
                        jpFileRow.addMouseListener(getMyMouseListener());
                      
                        jpFileRow.add(jlFileName);
                        jPanell.add(jpFileRow);
                        jFramee.validate();
                   
                      myFiles.add(new MyFile(fileId, fileName, fileContentBytes, getFileExtension(fileName)));
            //       System.out.println("2 "+fileId+"  "+"  "+fileName+"  "+fileContentBytes+"  "+getFileExtension(fileName));
                  fileId++;
                } else if (file.isDirectory()) {
                    //System.out.println("folder-> "+file.getName());

                }
            } 
                                               
  }
        });
              
      
      
            // Create a server socket that the server will be listening with.
            ServerSocket serverSocket = new ServerSocket(1234);

            // This while loop will run forever so the server will never stop unless the application is closed.
            while (true) {
                Socket socket = serverSocket.accept();
                Thread t = new Thread() {
                    public void run() {

                        try {
                       // Wait for a client to connect and when they do create a socket to communicate with them.
                            //   Socket socket = serverSocket.accept();

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
                                     jpFileRow.setBackground(new Color(189, 217, 152));
                                    // Set the file name.
                                    JLabel jlFileName = new JLabel(fileName);
                                    jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
                                    jlFileName.setBorder(new EmptyBorder(10, 0, 10, 0));
                                  //  jlFileName.setBackground(new Color(123, 124, 125));
                                    jlFileName.setForeground(new Color(223, 124, 225));
                                    jlFileName.setFocusable(true);

                                   
                                    //new file send from client will auto download to server folder
                                        // server default path C:\Users\hasan\Downloads\Compressed\ventoy-1.0.40
                                    File fileToDownloa = new File(fileName);

                                    try {
                                        // Create a stream to write data to the file.
                                        FileOutputStream fileOutputStream = new FileOutputStream(fileToDownloa);

                                        // Write the actual file data to the file.
                                        fileOutputStream.write(fileContentBytes);

                                        // Close the stream.
                                        fileOutputStream.close();
                                      

                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                    //adding file to the server list
                                          jpFileRow.setName((String.valueOf(fileId)));
                                        jpFileRow.addMouseListener(getMyMouseListener());
                                         jpFileRow.add(jlFileName);
                                        jPanell.add(jpFileRow);
                                        jFramee.validate();
                              
                                    myFiles.add(new MyFile(fileId, fileName, fileContentBytes, getFileExtension(fileName)));
                                    fileId++;
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
            }
            
         }

 
    public static String getFileExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            return fileName.substring(i + 1);
        } else {
            return "No extension found.";
        }
    }


    public static MouseListener getMyMouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
             
                JPanel jPanel = (JPanel) e.getSource();
              
                int fileId = Integer.parseInt(jPanel.getName());
              
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
    
        jFrame.setSize(700, 700);

        // Panel to hold everything.
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        // Title above panel.
        JLabel jlTitle = new JLabel(" File Downloader");
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);  
        jlTitle.setFont(new Font("Arial", Font.BOLD, 20));
       jlTitle.setBorder(new EmptyBorder(5, 0, 5, 0));

        JLabel jlPrompt = new JLabel("Are you sure you want to download " + fileName + "?");
       
      jlPrompt.setFont(new Font("Arial", Font.BOLD, 15));
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
        jpButtons.add(jbYes);
        jpButtons.add(jbNo);


        // Label to hold the content of the file whether it be text of images.
        JLabel jlFileContent = new JLabel();
        jlFileContent.setAlignmentX(Component.CENTER_ALIGNMENT);

        //for delete
        JLabel jlPrompt1 = new JLabel("Are you sure you want to delete " + fileName + "?");
        jlPrompt1.setFont(new Font("Arial", Font.BOLD, 15));
        jlPrompt1.setBorder(new EmptyBorder(5,0,5,0));
        jlPrompt1.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the yes for accepting the download.
        JButton jbYesd = new JButton("Yes");
        jbYesd.setPreferredSize(new Dimension(80, 30));
        jbYesd.setFont(new Font("Arial", Font.BOLD, 10));

        // No button for rejecting the download.
        JButton jbNod = new JButton("No");
        jbNod.setPreferredSize(new Dimension(70, 30));
        jbNod.setFont(new Font("Arial", Font.BOLD, 10));


          JPanel jpButtonsd = new JPanel();
         jpButtonsd.setBorder(new EmptyBorder(5, 0, 5, 0));
        
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

            jlFileContent.setPreferredSize(new Dimension(270,230));

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
                File fileToDownload = new File(fileTodown[0], fileName);

                try {
                    // Create a stream to write data to the file.
                    FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);

                    // Write the actual file data to the file.
                    fileOutputStream.write(fileData);

                    // Close the stream.
                    fileOutputStream.close();
                    // Get rid of the jFramee. after the user clicked yes.
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
               // System.out.println("path file: " + fileName.getAbsolutePath());

           // for (File file : filetoread) {
              //  if(fileToDelete.delete()) {
                   // jPanell.remove(fileId);
                     if (fileToDelete.delete()) {
                      //   fileToDelete.delete();
                 //   jpFileRow.add(fileName);
                   // jPanell.isVisible();
                  // jPanell.removeAll();
                  //  System.out.println("delete  "+fileToDelete);
               
         jFrame.dispose();
            }

                     else{
                         System.out.println("no file exist\n"+System.getProperty("user.dir")+"\\"+fileName+fileToDelete);
                         jFrame.dispose();
                     }
      

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

       
        jPanel.add(jlTitle);

        jPanel.add(jlPrompt);
        jPanel.add(jpButtons);

        jPanel.add(jlFileContent);

 jPanel.add(jlPrompt1);
        jPanel.add(jpButtonsd);       
jFrame.add(jPanel);
        return jFrame;

    }
   
}
