package app;

import com.DisplayLocales;
import com.Info;
import com.SetLocale;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplorer {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String configFile = "res.Messages";

        ResourceBundle messages = ResourceBundle.getBundle(configFile);

        String command;

        do {
            System.out.print(messages.getString("prompt"));
            command = scan.nextLine();
            String[] splitCommand = command.split(" ");
            if (splitCommand[0].equals("locales")) {

                DisplayLocales.display(messages);

            } else if (splitCommand[0].equals("locale.set")) {

                SetLocale.set(splitCommand[1]);

                messages = ResourceBundle.getBundle(configFile);

                String pattern = messages.getString("locale.set");

                Object[] arguments = {Locale.getDefault()};

                String answer = new MessageFormat(pattern).format(arguments);

                System.out.println(answer);
            } else if (splitCommand[0].equals("info")) {
                Info.printInfo(messages);
            } else {
                System.out.println(messages.getString("invalid"));
            }
        } while (!command.equals("exit"));
    }
}
