package com.company;

public class Main {
//Author : Toma Andrei-Paul

    public static void main(String[] args) {

        System.out.println("Hello World!");

        //alocarea memoriei se face automat, in functie de numarul de elemente are au fost puse la initializarea tabloului.
        String languages[] = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        //metoda random() din clasa Math returneaza un double cuprins intre 0 si 1.
        int n = (int)(Math.random() * 1000000);

        n *= 3;

        /*Metoda parseInt() din clasa Integer converteste primul
            argument (sir de caractere) in Intreg, baza primului argument fiind specificata in al doilea argument.*/

        n += Integer.parseInt("10101", 2);
        n += Integer.parseInt("FF", 16);

        n *= 6;

        int result = 0;

        //aduna cifrele numarului n pana cand n < 10;

        while(n >= 10){
           while(n != 0){
               result += (n % 10);
               n /= 10;
           }
           n = result;
           result = 0;
        }

        result = n;

        /* operatorul + cand unul dintre operanzi este String => concatenarea celor 2 operanzi. */

        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
}
