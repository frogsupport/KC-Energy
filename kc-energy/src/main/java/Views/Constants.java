package Views;

import java.awt.*;

// Constant values for select components of the window
public class Constants {

    // The window width
    public static final int WINDOW_WIDTH = 700;

    // The window height
    public static final int WINDOW_HEIGHT = 500;

    // The field width for text fields
    public static final int TEXT_FIELD_WIDTH = 20;

    // Valid monthly periods for a bill
    public static final String[] PERIOD_OPTIONS = new String[] {
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
    };

    // Valid tariff values
    public static final String[] TARIFF_OPTIONS = new String[] {"0.05", "0.10", "0.12", "0.15", "0.50"};

    // Valid energy rate values
    public static final String[] ENERGY_RATE_OPTIONS = new String[] {"50.00", "100.00", "200.00", "1000.00"};

    // Valid meter types
    public static final String[] METER_OPTIONS = new String[] {"Electric", "Gas"};

    // The font for titles
    public static final Font TITLE_FONT_LARGE = new Font(Font.SANS_SERIF, Font.BOLD, 19);
    public static final Font TITLE_FONT_MEDIUM = new Font(Font.SANS_SERIF, Font.BOLD, 14);

}
