package ZorgApp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Physiotherapist extends Actions {

    String[][] physioConsult = {
            {"Default", "17.50"},
            {"Short", "22.50"},
            {"Extended", "45.00"},
            {"Facilities", "5.00"}
    };
    @Override
    public void menu() throws IOException {
        System.out.println("=".repeat(22));
        System.out.println("1: Registeer Longinhoud");
        System.out.println("2: Bekijk Consultant prijzen");
        System.out.println("3: Maak Consultant");
        System.out.println("0: Programma afsluiten");
        int choice = Misc.input();

        switch (choice) {
            case 1 -> longCapacity();
            case 2 -> viewConsultPhysio();
            case 3 -> makeConsultantPhysio();
            case 0 -> System.exit(0);
        }
    }

    public void viewConsultPhysio() {
        System.out.println("=".repeat(22));
        System.out.println("Fysiotherapeut Consultant:");
        System.out.println("=".repeat(22));
        for (String[] view : physioConsult) {
            System.out.println(view[0] + ": €" + view[1]);
        }
    }

    public void makeConsultantPhysio() throws IOException {
        Scanner sc = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<patient> list = new ArrayList<>();
        File jsonFile = new File("/Users/kevinversteeg/IdeaProjects/Kevins ZorgApp/src/patienten.JSON");

        if (jsonFile.exists()) {
            list = mapper.readValue(jsonFile, new TypeReference<ArrayList<patient>>() {});
        }

        viewData();
        System.out.print("Kies een patiënt (1-" + list.size() + "): ");
        int choicePatient = sc.nextInt();

        if (choicePatient >= 1 && choicePatient <= list.size()) {
            patient selectedPatient = list.get(choicePatient - 1);

            System.out.println("=".repeat(22));
            System.out.println("Beschikbare consultopties:");
            for (int i = 0; i < physioConsult.length; i++) {
                System.out.println((i + 1) + ": " + physioConsult[i][0] + " - " + physioConsult[i][1] + " euro");
            }

            System.out.print("Kies een consultoptie (1-" + physioConsult.length + "): ");
            int choiceConsult = sc.nextInt();

            if (choiceConsult >= 1 && choiceConsult <= physioConsult.length) {
                String selectedConsult = physioConsult[choiceConsult - 1][0];
                double consultCost = Double.parseDouble(physioConsult[choiceConsult - 1][1]);

                System.out.println("Gekozen consultoptie: " + selectedConsult);
                System.out.println("Kosten: " + consultCost + " euro");


                selectedPatient.setConsult(selectedConsult);

                mapper.writeValue(jsonFile, list);

                System.out.println("Consultoptie is toegevoegd aan " + selectedPatient.getFirstName() + " " + selectedPatient.getLastName());
            } else {
                System.out.println("Ongeldige keuze voor consultoptie.");
            }
        } else {
            System.out.println("Ongeldige keuze voor patiënt.");
        }
    }
}
