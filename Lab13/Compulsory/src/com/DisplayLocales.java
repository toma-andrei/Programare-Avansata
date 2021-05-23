package com;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocales {
    public static void display(ResourceBundle messages){
        System.out.println(messages.getString("locales"));
        for(Locale locale : Locale.getAvailableLocales()){
            System.out.print(locale.getDisplayCountry() + "\t" + locale.getDisplayLanguage() + "  ///  ");
        }
        System.out.println();
        System.out.println();
    }
}
