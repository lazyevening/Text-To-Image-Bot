package base;

import com.vdurmont.emoji.EmojiParser;

import java.awt.*;
import java.util.HashMap;

public class Constants {
    public static final String ADD_TEXT_COMMAND = "/add_text";
    public static final String START_COMMAND = "/start";
    public static final String CREATORS_COMMAND = "/creators";
    public static final String HELP_COMMAND = "/help";
    public static final String ADD_FILTER_COMMAND = "/add_filter";
    public static final String HELP_MSG = "Выберите одну из команд: \n\r" +
                                          ADD_TEXT_COMMAND + " - добавить текст на изображение \n\r" +
                                          ADD_FILTER_COMMAND + " - добавить фильтр на изображение";

    public static final String GET_IMAGE_COMMAND = "/get";
    public static final String RGB_COMMAND = "/RGB";
    public static final String START_MSG = "Привет! Добавим текст на изображение. " + HELP_MSG;
    public static final String ENTRY_POINT_MSG = "press: /start";
    public static final String GET_IMAGE_TEXT_MSG = "Отправьте изображение, на котором хотите разместить текст";
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
            "⬛️",
            "⬜️",
            "\uD83D\uDFEB",
            "\uD83D\uDFE5",
            "\uD83D\uDFE9",
            "\uD83D\uDFE6",
            "\uD83D\uDFE8",
            "\uD83D\uDFE7",
            "\uD83D\uDFEA"
    };

    public static final String FILTER_MSG = "Выберете один из фильтров либо оставьте без изменений";

    public static final String TO_GRAY_FILTER = "To gray";
    public static final String TO_BLACK_WHITE = "To black white";
    public static final String TO_NEGATIVE = "To negative";
    public static final String ADD_CONTRAST = "Add contrast";
    public static final String ADD_COLOR_NOISE = "add Color Noise";
    public static final String ADD_CUBES = "Add Cubes";
    public static final String ADD_SMOOTH = "Add Smooth";
    public static final String QUALITY_KILLER = "Quality Killer";
    public static final String NO_FILTER = "NO FILTER";


    public static final String[] FILTERS = {
            TO_GRAY_FILTER,
            TO_BLACK_WHITE,
            TO_NEGATIVE,
            ADD_CONTRAST,
            ADD_COLOR_NOISE,
            ADD_CUBES,
            ADD_SMOOTH,
            QUALITY_KILLER,
            NO_FILTER
    };
    public static final String GET_IMAGE_FILTER_MSG = "Отправьте изображение";
}
