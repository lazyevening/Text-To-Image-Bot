import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Session {
    ArrayList<String> messages;
    String username;
    BufferedImage image;
    File file;

    Session(String username, BufferedImage image){
        this.username = username;
        this.messages = new ArrayList<>();
        this.image = image;
    }
}
