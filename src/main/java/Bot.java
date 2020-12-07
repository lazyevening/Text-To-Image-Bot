import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {
    Map<Long, Session> Users = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        long chat_id = update.getMessage().getChatId();
        String username = update.getMessage().getChat().getUserName();
        if (update.hasMessage() && update.getMessage().hasPhoto()) {
            String f_id = update.getMessage().getPhoto().get(0).getFileId();
            GetFile getFile = new GetFile().setFileId(f_id);
            String filePath = null;
            try {
                filePath = execute(getFile).getFilePath();
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            File downloadFile = downloadPhoto(filePath);
            System.out.println("downloadedFilePath: " + downloadFile);
            try {
                assert downloadFile != null;
                BufferedImage image = ImageIO.read(downloadFile);
                if (!Users.containsKey(chat_id)){
                    Users.put(chat_id, new Session(username, image));
                    Users.get(chat_id).file = downloadFile;
                } else {
                    Users.get(chat_id).image = image;
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }else if (update.hasMessage() && update.getMessage().hasText()){
            BufferedImage image = Users.get(chat_id).image;
            ImageProcessor.textToImage(image, update.getMessage().getText());
            try {
                ImageIO.write(image, "jpg", new File(Users.get(chat_id).file.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Users.get(chat_id).messages.add(update.getMessage().getText());
            SendPhoto message = new SendPhoto()
                    .setChatId(chat_id)
                    .setPhoto(Users.get(chat_id).file);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
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


    public String getBotUsername () { return "Image_With_Text_Bot"; }

    public String getBotToken () { return "1464489153:AAHt2g6OgyYuvQQ9LPT12IZDH1QYCzJaoqo"; }
}