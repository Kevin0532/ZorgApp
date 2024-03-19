package ZorgApp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Doctor extends Actions {

    Actions actions = new Actions();
    String[][] doctorConsult = {
            {"Default", "21.50"},
            {"Extended", "43.00"}
    };
    @Override
    public void menu() throws IOException {
        System.out.println("=".repeat(22));
        System.out.println("1: Patientgegevens");
        System.out.println("2: BMI bekijken");
        System.out.println("3: Consultant");
        System.out.println("4: Medicatie");
        System.out.println("9: Patient zoeken");
        System.out.println("0: Ga Terug");
        int choice = Misc.input();

        switch (choice) {
            case 1 -> {
                System.out.println("=".repeat(22));
                System.out.println("1: Bekijk patientgegevens");
                System.out.println("2: Bewerk patientgegevens");
                int choicePatientMenu = Misc.input();
                if (choicePatientMenu == 1) {
                    viewPatients();
                } else if (choicePatientMenu == 2) {
                    editPatients();
                }
            }
            case 2 -> {

                System.out.println("=".repeat(22));
                System.out.println("1: Bekijk BMI van Patient");
                System.out.println("2: Bekijk BMI Grafiek van Patient");
                int choiceBMIMenu = Misc.input();
                if (choiceBMIMenu == 1) {
                    calcBMI();
                } else if (choiceBMIMenu == 2) {
                    actions.bmiGraphByPatient();
                }
            }
            case 3 -> {
                System.out.println("=".repeat(22));
                System.out.println("1: Bekijk Consultant Prijzen");
                System.out.println("2: Maak Consultant");
                int choiceConsultMenu = Misc.input();
                if (choiceConsultMenu == 1) {
                    viewConsultDoctor();
                } else if (choiceConsultMenu == 2) {
                    makeConsultantDoctor();
                }
            }
            case 4 -> Pharmacist.viewMedication();
            case 9 -> patientSearch();
        }
    }

    public void viewConsultDoctor() {
        System.out.println("=".repeat(22));
        System.out.println("Dokter Consultant: ");
        System.out.println("=".repeat(22));
        for (String[] view : doctorConsult) {
            System.out.println(view[0] + ": €" + view[1]);
        }
    }

    public void makeConsultantDoctor() throws IOException {
        Scanner sc = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<patient> list = new ArrayList<>();
        File jsonFile = new File("/Users/kevinversteeg/IdeaProjects/Kevins ZorgApp/src/patienten.JSON");

        if (jsonFile.exists()) {
            list = mapper.readValue(jsonFile, new TypeReference<>() {});
        }

        viewData();
        System.out.print("Kies een patiënt (1-" + list.size() + "): ");
        int choicePatient = sc.nextInt();

        if (choicePatient >= 1 && choicePatient <= list.size()) {
            patient selectedPatient = list.get(choicePatient - 1);

            System.out.println("Beschikbare consultopties:");
            for (int i = 0; i < doctorConsult.length; i++) {
                System.out.println((i + 1) + ": " + doctorConsult[i][0] + " - " + doctorConsult[i][1] + " euro");
            }

            System.out.print("Kies een consultoptie (1-" + doctorConsult.length + "): ");
            int choiceConsult = sc.nextInt();

            if (choiceConsult >= 1 && choiceConsult <= doctorConsult.length) {
                String selectedConsult = doctorConsult[choiceConsult - 1][0];
                double consultCost = Double.parseDouble(doctorConsult[choiceConsult - 1][1]);


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
