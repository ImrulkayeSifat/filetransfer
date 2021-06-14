package Climyfile;

public class MyFile {

    private int id;
    private String name;
    private byte[] data;
    private Object fileExtension;


    public MyFile(int fileId, String fileName, byte[] fileNameBytes, Object fileExtension) {
        this.id=fileId;
        this.name=fileName;
        this.data=fileNameBytes;
        this.fileExtension=fileExtension;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setData(byte[] data) { this.data = data;    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getData() {
        return data;
    }

    public Object getFileExtension() {
        return fileExtension;
    }
}
