import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Bot extends TelegramLongPollingBot {
    Map<Long, Session> Users = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        long chat_id = update.getMessage().getChatId();

        if (!Users.containsKey(chat_id)){
            Users.put(chat_id, new Session(1));
        }
        int cond = Users.get(chat_id).state;


            switch (cond) {
                case 0:
                    sendHelp(chat_id);
                    Users.get(chat_id).state = 1;
                    break;
                case 1:
                    if (update.getMessage().hasText()) {
                        String message_text = update.getMessage().getText();
                        switch (message_text) {
                            case "/add_text":
                                Users.get(chat_id).state = 2;
                                sendMessage(chat_id,
                                        "Отправьте изображение, на котором хотите разместить текст");
                                break;
                            case "/start":
                                sendHelp(chat_id);
                                break;
                            case "/creators":
                                sendMessage(chat_id, "Два любителя ебланить");
                                break;
                            default:
                                sendMessage(chat_id, "If you want to start over, press: /start");
                                break;
                        }
                    }
                    break;
                case 2:
                    if (update.getMessage().hasPhoto()) {
                        setPhoto(chat_id, update);
                        Users.get(chat_id).state = 3;
                        sendMessage(chat_id, "Отправьте текст для размещения");
                    } else {
                        sendMessage(chat_id, "Отправьте изображение, на котором хотите разместить текст");
                    }
                    break;
                case 3:
                    if (update.getMessage().hasText()) {
                        String text = update.getMessage().getText();
                        sendPhotoWithText(chat_id, text);
                        Users.get(chat_id).state = 1;
                        sendHelp(chat_id);
                    } else sendMessage(chat_id, "Отправьте текст для размещения");
                    break;
                default:
                    break;
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

    private void sendHelp(long chat_id){
        sendMessage(chat_id,
                "Hello! Этот бот может обрабатывать изображения. \n\r" +
                        "Выберите одну из команд: \n\r" +
                        "/add_text - add your text on image; \n\r" +
                        "/start - wanna start over?; \n\r" +
                        "/creators - ^-^; \n\r");
    }

    private void sendMessage(long chat_id, String message_text){
        SendMessage message = new SendMessage()
                .setChatId(chat_id)
                .setText(message_text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendPhotoWithText(long chat_id, String text){
        BufferedImage image = Users.get(chat_id).image;
        try {
            ImageProcessor.textToImage(image, text);
            ImageIO.write(image, "jpg", new File(Users.get(chat_id).file.toString()));
        } catch (IOException e){
            e.printStackTrace();
        }

        SendPhoto message = (new SendPhoto()).setChatId(chat_id).setPhoto(Users.get(chat_id).file);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setPhoto(long chat_id, Update update){
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

        try {
            assert downloadFile != null;
            Users.get(chat_id).image = ImageIO.read(downloadFile);
            Users.get(chat_id).file = downloadFile;
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public String getBotUsername () { return "Image_With_Text_Bot"; }

    public String getBotToken () { return ""; }
}
