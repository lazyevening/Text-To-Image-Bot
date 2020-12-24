import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Core {
    private final String USERS_FILE;
    private HashMap<String, User> users;

    public Core(String file_path) {
        USERS_FILE = file_path;
        var json_file = new File(USERS_FILE);
        var builder = new GsonBuilder();
        var gson = builder.create();
        try (FileReader fileReader = new FileReader(json_file)){
            var jsonString = FileUtils.readFileToString(json_file, StandardCharsets.UTF_8);
            Type type = new TypeToken<HashMap<String, User>>(){}.getType();
            HashMap<String, User> json_data = gson.fromJson(jsonString, type);
            users = Objects.requireNonNullElseGet(json_data, HashMap::new);
        } catch (FileNotFoundException exception) {
            users = new HashMap<>();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Core() {this(Constants.USERS_FILE) ; }

    public void createUser(String userId){
        var user = new User();
        users.put(userId, user);
        updateUsersState();
    }

    public void setUserFSMState(String userId, State state) {
        users.get(userId).setFsmState(state);
        updateUsersState();
    }

    private void updateUsersState() {
        var gson = new Gson();
        try (FileWriter file = new FileWriter(USERS_FILE)) {
            file.write(gson.toJson(users));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public State getUserFSMState(String userId) {
        if (users.containsKey(userId)) {
            return users.get(userId).getFsmState();
        }
        else { return null; }
    }

    public ReplyKeyboardMarkup getKeyboard(String[] collection){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        int counter = 0;
        for (var i = 0; i < 3; i++) {
            for (var j = 0; j < 3; j++){
                row.add(collection[counter + j]);
            }
            counter += 3;
            keyboard.add(row);
            row = new KeyboardRow();
        }
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    public void setImage(String user_id, File file){
            users.get(user_id).file = file;
    }

    public void putTextToPhoto(String user_id) {
        try {
            BufferedImage image = ImageIO.read(users.get(user_id).file);
            ImageProcessor.textToImage(
                    image, users.get(user_id).text, users.get(user_id).textPosition, users.get(user_id).textColor);
            ImageIO.write(image, "jpg", new File(users.get(user_id).file.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public File getUserPhoto(String user_id) {
        return users.get(user_id).file;
    }

    public void setUserText(String uid, String message) {
        users.get(uid).text = message;
    }

    public void setUserTextPosition(String uid, String message) {
        users.get(uid).textPosition = message;
    }

    public void setUserColor(String uid, String message) {
        users.get(uid).textColor = message;
    }
}
