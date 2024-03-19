package ZorgApp;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Misc {

    public static int input(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Voer uw keuze in: ");
        return sc.nextInt();
    }


    public int chooseRole(Scanner sc) {
        System.out.println("1: Apotheker");
        System.out.println("2: Fysiotherapeut");
        System.out.println("3: Huisarts");
        System.out.println("4: Tandarts");
        System.out.println("0: Programma sluiten");
        System.out.print("Kies uw rol: ");
        return sc.nextInt();
    }

    public String calcBMI(double gewicht, double lengte) {
        double length = lengte/100;
        double result = gewicht/(length*length);
        DecimalFormat f = new DecimalFormat("##.00");
        return f.format(result);
    }

    public static int calcAge(String birthday) {
        LocalDate today = LocalDate.now();
        LocalDate birth = LocalDate.parse(birthday.substring(6) + "-" + birthday.substring(3,5) + "-" + birthday.substring(0,2));
        return Period.between(birth, today).getYears();
    }

    int[] lengte = {190, 190, 190};
    int[] gewicht = {90,80,70};
}
