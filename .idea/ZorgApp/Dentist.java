package ZorgApp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dentist extends Actions {
    String[][] dentistConsult = {
            {"Default", "20.00"},
            {"Simple", "30.00"},
            {"Complex", "55.00"}
    };
    @Override
    public void menu() throws IOException {
        System.out.println("=".repeat(22));
        System.out.println("1: Patientgegevens");
        System.out.println("2: Consultant");
        System.out.println("3: Bekijk medicatie gegevens");
        System.out.println("0: Programma afsluiten");
        int choice = Misc.input();

        switch (choice) {
            case 1 -> {
                System.out.println("=".repeat(22));
                System.out.println("1: Bekijk patientgegevens");
                System.out.println("2: Bewerk patientgegevens");
                int choicePatientMenu = Misc.input();
                if (choicePatientMenu == 1) {
                    viewPatientsDentist();
                } else if (choicePatientMenu == 2) {
                    editPatients();
                }
            }
            case 2 -> {
                System.out.println("=".repeat(22));
                System.out.println("1: Bekijk Consultant Prijzen");
                System.out.println("2: Bekijk Consultant");
                System.out.println("3: Maak Consultant");
                int choiceConsultantMenu = Misc.input();
                if (choiceConsultantMenu == 1) {
                    viewConsultDentist();
                } else if (choiceConsultantMenu == 2) {
                    viewConsultData();
                } else if (choiceConsultantMenu == 3) {
                    makeConsultantDentist();

                }
            }
            case 3 -> Pharmacist.viewMedication();
            default -> System.out.println("Verkeerde keuze probeer het opnieuw");
        }
    }
    public void viewConsultDentist() {
        System.out.println("=".repeat(22));
        System.out.println("Tandarts Consultant: ");
        System.out.println("=".repeat(22));
        for (String[] view : dentistConsult) {
            System.out.println(view[0] + ": €" + view[1]);
        }
    }

    public void makeConsultantDentist() throws IOException {
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

            System.out.println("Beschikbare consultopties:");
            for (int i = 0; i < dentistConsult.length; i++) {
                System.out.println((i + 1) + ": " + dentistConsult[i][0] + " - " + dentistConsult[i][1] + " euro");
            }

            System.out.print("Kies een consultoptie (1-" + dentistConsult.length + "): ");
            int choiceConsult = sc.nextInt();

            if (choiceConsult >= 1 && choiceConsult <= dentistConsult.length) {
                String selectedConsult = dentistConsult[choiceConsult - 1][0];
                double consultCost = Double.parseDouble(dentistConsult[choiceConsult - 1][1]);


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
