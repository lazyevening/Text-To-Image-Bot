import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
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
}
