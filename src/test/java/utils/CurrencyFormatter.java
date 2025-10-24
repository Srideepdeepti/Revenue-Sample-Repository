package utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {
	
	public static String convertToAud(long amount) {
       

        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        String formatted = formatter.format(amount);

       return formatted;

}
}
