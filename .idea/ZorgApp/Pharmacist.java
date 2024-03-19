package ZorgApp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pharmacist extends Actions {
    @Override
    public void menu() throws IOException {
        System.out.println("=".repeat(22));
        System.out.println("1: Medicatie bekijken");
        System.out.println("2: Medicatie toevoegen");
        System.out.println("3: Medicatie toewijzen");
        System.out.println("0: Programma afsluiten");
        int choice = Misc.input();

        if (choice == 1) {
            viewMedication();
        } else if (choice == 2) {
            addMedication();
        } else if (choice == 3) {
            assignMedication();
        } else {
            System.out.println();
        }
    }
    public static void viewMedication() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Een File wordt gemaakt dat verwijst naar een JSON-bestand op de locatie die is aangegevenn
        File jsonFile = new File("/Users/kevinversteeg/IdeaProjects/Kevins ZorgApp/src/MedicationData.JSON");

        // Controleert of het opgegeven JSON-bestand bestaat. Als het niet bestaat, wordt een bericht afgedrukt en de methode geeft dit aan met een bericht.
        if (!jsonFile.exists()) {
            System.out.println("Er zijn geen medicijnen beschikbaar.");
            return;
        }

        // De inhoud van het JSON-bestand wordt gelezen en omgezet naar een lijst van MedicationData objecten.
        List<MedicationData> medicationList = mapper.readValue(jsonFile, new TypeReference<>() {});

        // Itereert door elk MedicationData-object in de lijst.
        for (MedicationData medication : medicationList) {
            System.out.println("=".repeat(22));

            // Print de eigenschappen van elk MedicationData object.
            System.out.println("Medicatie ID: " + medication.getMedID());
            System.out.println("Naam: " + medication.getMedName());
            System.out.println("Voorraad: " + medication.getMedStock());
            System.out.println("Beschrijving: " + medication.getMedDesc());
        }
    }


    public void addMedication() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Een File-object wordt gemaakt dat verwijst naar een JSON-bestand op de opgegeven locatie.
        File jsonFile = new File("/Users/kevinversteeg/IdeaProjects/Kevins ZorgApp/src/MedicationData.JSON");

        // Een lijst voor MedicationData-objecten wordt gedeclareerd.
        List<MedicationData> medicationList;

        // Controleert of het JSON-bestand bestaat. Als het bestaat, worden de gegevens gelezen en toegewezen aan de lijst, anders wordt een nieuwe lijst gemaakt.
        if (jsonFile.exists()) {
            medicationList = mapper.readValue(jsonFile, new TypeReference<>() {});
        } else {
            medicationList = new ArrayList<>();
        }

        // Een Scanner-object wordt gemaakt om invoer van de gebruiker te lezen.
        Scanner sc = new Scanner(System.in);

        // Vraagt de gebruiker om de naam van de medicatie in te voeren en leest de invoer.
        System.out.print("Voer de naam van de medicatie in: ");
        String medName = sc.nextLine();

        // Vraagt de gebruiker om het medicatie ID in te voeren en gebruikt de input-methode van de klasse Misc om het in te lezen.
        System.out.print("Voer het medicatie ID in: ");
        int medID = Misc.input();

        // Vraagt de gebruiker om de dosering in te voeren en leest de invoer.
        System.out.print("Voer de dosering in: ");
        int medDosage = Misc.input();

        // Vraagt de gebruiker om een beschrijving in te voeren en leest de invoer.
        System.out.print("Voer een beschrijving in: ");
        String medDesc = sc.nextLine();

        // Toont opties voor voorraad en vraagt de gebruiker om de voorraadstatus in te voeren (0 of 1).
        System.out.println("0 = NIET op voorraad");
        System.out.println("1 = WEL op voorraad");
        System.out.print("Voer de voorraad in (0 of 1): ");
        String medStock = sc.next();

        // Maakt een nieuw MedicationData-object met de ingevoerde gegevens en voegt het toe aan de lijst.
        MedicationData newMedication = new MedicationData(medName, medID, medDosage, medDesc, medStock);
        medicationList.add(newMedication);

        // Schrijft de bijgewerkte lijst terug naar het JSON-bestand.
        mapper.writeValue(jsonFile, medicationList);

        // Print een bericht dat aangeeft dat de medicatie is toegevoegd aan de lijst.
        System.out.println("Medicatie is toegevoegd aan de lijst.");
    }


    public void assignMedication() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter write = mapper.writerWithDefaultPrettyPrinter();
        File patientJsonFile = new File("/Users/kevinversteeg/IdeaProjects/Kevins ZorgApp/src/patienten.JSON");
        File medicationJsonFile = new File("/Users/kevinversteeg/IdeaProjects/Kevins ZorgApp/src/MedicationData.JSON"); // Vervang dit pad door het juiste pad naar medicationDATA.json

        ArrayList<patient> patientsList = loadPatientsFromJSON();
        Scanner scanner = new Scanner(System.in);
        System.out.println("=".repeat(22));

        for (int i = 0; i < patientsList.size(); i++) {
            patient patient = patientsList.get(i);
            System.out.println((i + 1) + ": " + patient.getFirstName() + " " + patient.getLastName());
        }

        System.out.print("Kies een patiënt (1-" + patientsList.size() + "): ");
        int patientChoice = scanner.nextInt();
        scanner.nextLine();

        List<MedicationData> medicationList = mapper.readValue(medicationJsonFile, new TypeReference<>() {
        });

        // Toon de beschikbare medicatie-opties
        System.out.println("=".repeat(22));
        System.out.println("Beschikbare medicatie-opties:");
        for (int i = 0; i < medicationList.size(); i++) {
            MedicationData medication = medicationList.get(i);
            System.out.println((i + 1) + ": " + medication.getMedName() + " Voorraad: " + medication.getMedStock());
        }

        System.out.print("Kies medicatie voor de patiënt (1-" + medicationList.size() + "): ");
        int medicationChoice = scanner.nextInt();
        scanner.nextLine();

        if (patientChoice >= 1 && patientChoice <= patientsList.size() &&
                medicationChoice >= 1 && medicationChoice <= medicationList.size()) {

            patient selectedPatient = patientsList.get(patientChoice - 1);
            MedicationData selectedMedication = medicationList.get(medicationChoice - 1);

            selectedPatient.setMedicatie(selectedMedication.getMedName());

            write.writeValue(patientJsonFile, patientsList);
            System.out.println("Medicatie toegewezen aan " + selectedPatient.getFirstName() + " " + selectedPatient.getLastName());
        } else {
            System.out.println("Ongeldige keuze voor patiënt of medicatie.");
        }
    }
}
