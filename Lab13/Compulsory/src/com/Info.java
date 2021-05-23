package com;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.*;

public class Info {

    public static void printInfo(ResourceBundle messages) {
        String pattern = messages.getString("info");

        Object[] arguments = {Locale.getDefault()};

        StringBuilder answer = new StringBuilder(new MessageFormat(pattern).format(arguments)).append("\n");

        if (Locale.getDefault().toString().equals("ro")) {
            answer.append("Country: ").append("România").append("\n");
        } else {
            answer.append("Country: ").append(Locale.getDefault().getDisplayCountry()).append("\n");
        }

        answer.append("Language: ").append(Locale.getDefault().getDisplayLanguage()).append("\n");
        answer.append("Week Days: ").append(Arrays.toString(new DateFormatSymbols(Locale.getDefault()).getWeekdays()).replace("[,", "").replace("]", "")).append("\n");
        answer.append("Months: ").append(Arrays.toString(new DateFormatSymbols(Locale.getDefault()).getMonths()).replace("[", "").replace(", ]", "")).append("\n");

        DateFormat dateFormat;
        Date todayRo;
        Date todayEn;
        String dateOut;

        dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ENGLISH);
        todayEn = new Date();
        dateOut = dateFormat.format(todayEn);

        answer.append("Today: ").append(dateOut).append(" (");

        dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
        todayRo = new Date();
        dateOut = dateFormat.format(todayRo);
        answer.append(dateOut).append(")");
        answer.append("\n");
        System.out.println(answer);
    }
}
