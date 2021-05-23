package com;

import java.util.Locale;

public class SetLocale {

    public static void set(String country){
        Locale.setDefault(Locale.forLanguageTag(country));
    }

}
