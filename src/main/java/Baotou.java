import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Baotou extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasPhoto()) {
            long chat_id = update.getMessage().getChatId();
            List<PhotoSize> photos = update.getMessage().getPhoto();

            String f_id = Objects.requireNonNull(photos.stream().max(Comparator.comparing(PhotoSize::getFileSize))
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
                BufferedImage image = ImageIO.read(downloadFile);
                ImageProcessor.textToImage(image, "ВО СКОЛЬКО ЗАБИВ?");
                ImageIO.write(image, "jpg", new File(downloadFile + ""));
            } catch (IOException e){
                System.out.println(":(((((");
            }

            SendPhoto messg = (new SendPhoto()).setChatId(chat_id).setPhoto(downloadFile);
            try {
                execute(messg);
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


    public String getBotUsername () { return "Zabivnoyy_bot"; }

    public String getBotToken () { return "1370087721:AAGIa9FNLpDgtY9rtauoI_blcOOlfwfHLlY"; }
}