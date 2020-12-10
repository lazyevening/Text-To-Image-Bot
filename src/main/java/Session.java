import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Session {
    ArrayList<String> messages;
    BufferedImage image;
    File file;
    int state;

    Session(int state){
        this.state = state;
        this.messages = new ArrayList<>();
    }
}
