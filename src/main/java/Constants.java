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
    public static final String CREATORS_MSG = "Очень умные люди и большие молодцы";
    public static final String START_MSG = "Добавим текст на изображение. " + HELP_MSG;
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
    /*
    public static final String TOP_LEFT = POSITION_TOP + " " + POSITION_LEFT;
    public static final String TOP_CENTER = POSITION_TOP + " " + POSITION_CENTER;
    public static final String TOP_RIGHT = POSITION_TOP + " " + POSITION_RIGHT;

    public static final String CENTER_LEFT = POSITION_CENTER + " " + POSITION_LEFT;
    public static final String CENTER_CENTER = POSITION_CENTER + " " + POSITION_CENTER;
    public static final String CENTER_RIGHT = POSITION_CENTER + " " + POSITION_RIGHT;

    public static final String BOTTOM_LEFT = POSITION_BOTTOM + " " + POSITION_LEFT;
    public static final String BOTTOM_CENTER = POSITION_BOTTOM + " " + POSITION_CENTER;
    public static final String BOTTOM_RIGHT = POSITION_BOTTOM + " " + POSITION_RIGHT;

    public static final String BLACK = "Black";
    public static final String WHITE = "White";
    public static final String GREY = "Grey";
    public static final String RED = "Red";
    public static final String GREEN = "Green";
    public static final String BLUE = "Blue";
    public static final String YELLOW = "Yellow";
    public static final String PINK = "Pink";
    public static final String MAGENTA = "Magenta";
    */
    public static final String COLOR_MSG = "Выберете цвет текста";
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
