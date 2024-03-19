package ZorgApp;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Doctor doctor = new Doctor();
        Pharmacist pharmacist = new Pharmacist();
        Dentist dentist = new Dentist();
        Physiotherapist physiotherapist = new Physiotherapist();

        Misc misc = new Misc();
        while (true) {
            System.out.println("=".repeat(28));
            System.out.println("Welkom bij ZorgZonderZorgen");
            System.out.println("=".repeat(28));
            try {
                int provider = misc.chooseRole(sc);
                if (provider == 0) {
                    System.out.println("Systeem wordt afgesloten");
                    System.exit(0);
                } else if (provider >= 1 && provider <= 4) {
                    String[] roles = {"Apotheker", "Fysiotherapeut", "Huisarts", "Tandarts"};
                    String role = roles[provider - 1];

                    System.out.println();
                    System.out.println("=".repeat(28));
                    System.out.println("Uw rol: " + role);

                    switch (provider) {
                        case 1 -> pharmacist.menu();
                        case 2 -> physiotherapist.menu();
                        case 3 -> doctor.menu();
                        case 4 -> dentist.menu();
                        default -> System.out.println("Ongeldige keuze. Voer een getal in tussen 0 en 4");
                    }
                } else {
                    // De foutmelding bericht voor ongeldige
                    System.out.println("Ongeldige invoer. Voer een getal in tussen 0 en 4");
                }
                // Fout afhandeling als invoer van gebruiker niet oveereenkomt met het verwachte type.
            } catch (java.util.InputMismatchException e) {
                System.out.println("Ongeldige invoer. Voer een getal in tussen 0 en 4");
                sc.nextLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

