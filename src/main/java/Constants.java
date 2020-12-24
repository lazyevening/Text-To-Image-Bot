import java.awt.*;
import java.util.HashMap;

public class Constants {
    public static final String ADD_TEXT_COMMAND = "/add_text";
    public static final String START_COMMAND = "/start";
    public static final String CREATORS_COMMAND = "/creators";
    public static final String HELP_COMMAND = "/help";
    public static final String HELP_MSG = "Выберите одну из команд: \n\r" +
                                          "/add_text - add your text on image";
    public static final String GET_IMAGE_COMMAND = "/get";
    public static final String RGB_COMMAND = "/RGB";
    public static final String CREATORS_MSG = "Очень умные люди и большие молодцы";
    public static final String START_MSG = "Привет! Добавим текст на изображение. " + HELP_MSG;
    public static final String ENTRY_POINT_MSG = "press: /start";
    public static final String GET_IMAGE_MSG = "Отправьте изображение, на котором хотите разместить текст";
    public static final String GET_TEXT_MSG = "Отправьте текст для размещения";
    public static final String USERS_FILE = "users.json";
    public static final String BOT_WAITING_COMMANDS = "Введите одну из доступных комманд: " + HELP_COMMAND;
    public static final String READY_TO_GET_MSG = "Изображение готово к экспорту: " + GET_IMAGE_COMMAND;


    public static final String POSITION_MSG = "Выберете позицию текста";
    public static final String POSITION_CENTER = "Center";
    public static final String POSITION_LEFT = "Left";
    public static final String POSITION_RIGHT = "Right";
    public static final String POSITION_TOP = "Top";
    public static final String POSITION_BOTTOM = "Bottom";
    public static final String[] POSITIONS = {
            POSITION_TOP + " " + POSITION_LEFT,
            POSITION_TOP + " " + POSITION_CENTER,
            POSITION_TOP + " " + POSITION_RIGHT,
            POSITION_CENTER + " " + POSITION_LEFT,
            POSITION_CENTER + " " + POSITION_CENTER,
            POSITION_CENTER + " " + POSITION_RIGHT,
            POSITION_BOTTOM + " " + POSITION_LEFT,
            POSITION_BOTTOM + " " + POSITION_CENTER,
            POSITION_BOTTOM + " " + POSITION_RIGHT
    };
    public static final String COLOR_MSG = "Выберете цвет текста, " +
            "либо задайте его с помощью соотношения RBG: " + RGB_COMMAND;
    public static final String RGB_MSG = "Введите 3 числа как соотношение цветов Red Green Blue через пробел, " +
            "каждое число должно находиться в диапазоне от 0 до 255. \n\r" +
            "Например ваша команда может выглядеть так: \n\r" +
            "255 255 255";
    public static final String[] COLORS = {
            "Black",
            "White",
            "Grey",
            "Red",
            "Green",
            "Blue",
            "Yellow",
            "Pink",
            "Magenta"
    };


}
