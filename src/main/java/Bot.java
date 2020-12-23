import org.glassfish.jersey.jaxb.internal.XmlCollectionJaxbProvider;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Properties;

public class Bot extends TelegramLongPollingBot{
    private final RequestHandler requestHandler = new RequestHandler();
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage())
            if (update.getMessage().hasText() && update.getMessage().getText().equals(Constants.GET_IMAGE_COMMAND)) {
                sendPhoto(update);
                sendMessage(handleMessage(update));
            }
            else
                sendMessage(handleMessage(update));
        }

    private void sendPhoto(Update update) {
        var answer = new SendPhoto();
        answer.setChatId(update.getMessage().getChatId());
        String user_id = update.getMessage().getFrom().getId().toString();
        answer.setPhoto(requestHandler.getPhoto(user_id));
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(SendMessage message){
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private SendMessage handleMessage(Update update){
        var answer = new SendMessage();
        answer.setChatId(update.getMessage().getChatId());
        String uid = update.getMessage().getFrom().getId().toString();
        if (update.getMessage().hasText()) {
            answer.setText(requestHandler.handle(
                     uid, update.getMessage().getChatId().toString(), update.getMessage().getText(),null));
        } else if (update.getMessage().hasPhoto()){
            answer.setText(requestHandler.handle(
                    uid, update.getMessage().getChatId().toString(), "", getFile(update)));
        }
        answer.setReplyMarkup(requestHandler.handleKeyboard(uid, update.getMessage().getText()));
        return answer;
    }

    private File downloadPhoto(String filePath) {
        try {
            return downloadFile(filePath);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("download-error");
        }
        return null;
    }

    public File getFile(Update update){
        List<PhotoSize> photos = update.getMessage().getPhoto();

        String f_id = Objects.requireNonNull(photos.stream()
                .max(Comparator.comparing(PhotoSize::getFileSize))
                .orElse(null)).getFileId();
        GetFile getFile = new GetFile().setFileId(f_id);
        String filePath = null;
        try {
            filePath = execute(getFile).getFilePath();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        File downloadFile = downloadPhoto(filePath);
        System.out.println("downloadedFilePath: " + downloadFile);
        return downloadFile;
    }

    public String getBotUsername () { return "Image_With_Text_Bot"; }

    public String getBotToken () {
        Properties prop = new Properties();
        try {
            prop.load(XmlCollectionJaxbProvider.App.class.getClassLoader().getResourceAsStream("config.properties"));

            return prop.getProperty("token");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
