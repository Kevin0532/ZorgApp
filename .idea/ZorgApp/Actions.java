package ZorgApp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Actions {

    public void menu() throws IOException {
        System.out.println("Default menu.");
    }


    static String[][] nameConsultant = {
            {"Default", "Simple", "Complex"},
            {"Default", "Short", "Extended", "Facilities"},
            {"Default", "Extended"}
    };
    static double[][] prijzenConsultant = {
            {20.00, 30.00, 55.00},              // Tandarts:  0. Default, 1. Simple, 2. Complex
            {17.50, 22.50, 45.00, 5.00},        // Fysio:     0. Default, 1. Short, 2. Extended, 3. Facilities
            {21.50, 43.00}                      // Huisarts:  0. Default, 1. Extended
    };


    public static ArrayList<patient> loadPatientsFromJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<patient> list = new ArrayList<>();
        File jsonFile = new File("/Users/kevinversteeg/IdeaProjects/Kevins ZorgApp/src/patienten.JSON");
        if (jsonFile.exists()) {
            list = mapper.readValue(jsonFile, new TypeReference<>() {
            });
        }
        return list;
    }

    public void viewData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<patient> list = new ArrayList<>();
        File jsonFile = new File("/Users/kevinversteeg/IdeaProjects/Kevins ZorgApp/src/patienten.JSON");
        if (jsonFile.exists()) {
            list = mapper.readValue(jsonFile, new TypeReference<>() {
            });
        }
        System.out.println("=".repeat(22));
        System.out.println("Lijst van patiënten:");
        for (int i = 0; i < list.size(); i++) {
            patient patient = list.get(i);
            System.out.println((i + 1) + ": " + patient.getFirstName() + " " + patient.getLastName());
        }
    }

    public void viewPatients() throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<patient> list = loadPatientsFromJSON();
        viewData();
        System.out.print("Kies een patiënt (1-" + list.size() + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Verbruik de newline

        if (choice >= 1 && choice <= list.size()) {
            patient selectedPatient = list.get(choice - 1);
            // Toon de gegevens van de geselecteerde patiënt
            System.out.println("=".repeat(11) + " " + selectedPatient.getPatientNummer() + " " + "=".repeat(11));
            System.out.println("Patiëntnummer: " + selectedPatient.getPatientNummer());
            System.out.println("Voornaam: " + selectedPatient.getFirstName());
            System.out.println("Achternaam: " + selectedPatient.getLastName());
            System.out.println("Geboortedatum: " + selectedPatient.getBirthDay());
            System.out.println("Leeftijd: " + calcAge(selectedPatient.getBirthDay()));
            System.out.println("Woonplaats: " + selectedPatient.getCity());
            System.out.println("Telefoonnummer: " + selectedPatient.getPhoneNumber());
            System.out.println("Medicatie: " + selectedPatient.getMedicatie());
            System.out.println("Lengte: " + selectedPatient.getLengte() + "cm");
            System.out.println("Gewicht: " + selectedPatient.getGewicht() + "Kg");
            System.out.println("Long capaciteit: " + selectedPatient.getLungCapacity() + "l");
            System.out.println("Consult: " + selectedPatient.getConsult());
            System.out.println("=".repeat(22));
        } else {
            System.out.println("Ongeldige keuze.");
        }
    }

    public void editPatients() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        File jsonFile = new File("/Users/kevinversteeg/IdeaProjects/Kevins ZorgApp/src/patienten.JSON");

        ArrayList<patient> list = loadPatientsFromJSON();
        Scanner scanner = new Scanner(System.in);
        viewData();
        System.out.print("Kies een patiënt (1-" + list.size() + "): ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice >= 1 && choice <= list.size()) {
            patient selectedPatient = list.get(choice - 1);

            System.out.println("=".repeat(22));
            System.out.println("Welk gegeven wil je aanpassen?");
            System.out.println("1: Voornaam");
            System.out.println("2: Achternaam");
            System.out.println("3: Geboortedatum");
            System.out.println("4: Woonplaats");
            System.out.println("5: Telefoonnummer");
            System.out.println("6: Medicatie");
            System.out.println("7: Lengte");
            System.out.println("8: Gewicht");
            System.out.println("9: Longcapaciteit");
            System.out.println("10: Consult");
            System.out.print("Uw keuze: ");
            int fieldChoice = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Voer de nieuwe waarde in: ");
            String newValue = scanner.nextLine();

            switch (fieldChoice) {
                case 1 -> selectedPatient.setFirstName(newValue);
                case 2 -> selectedPatient.setLastName(newValue);
                case 3 -> selectedPatient.setBirthDay(newValue);
                case 4 -> selectedPatient.setCity(newValue);
                case 5 -> selectedPatient.setPhoneNumber(newValue);
                case 6 -> selectedPatient.setMedicatie(newValue);
                case 7 -> selectedPatient.setLengte(Integer.parseInt(newValue));
                case 8 -> selectedPatient.setGewicht(Integer.parseInt(newValue));
                case 9 -> selectedPatient.setLungCapacity(newValue);
                case 10 -> selectedPatient.setConsult(newValue);
                default -> System.out.println("Ongeldige keuze.");
            }

            writer.writeValue(jsonFile, list);

            System.out.println("Patiëntgegevens bijgewerkt.");
        } else {
            System.out.println("Ongeldige keuze.");
        }
    }

    public void calcBMI() throws IOException {
        Scanner berekenBMI = new Scanner(System.in);
        ArrayList<patient> list = loadPatientsFromJSON();
        viewData();
        System.out.print("Uw keuze: ");
        int keuze = berekenBMI.nextInt();
        if (keuze >= 1 && keuze <= list.size()) {
            patient patient = list.get(keuze - 1);
            double bmi = calculateBMI(patient.getGewicht(), patient.getLengte());
            String bmiFormatted = String.format("%.2f", bmi);
            System.out.println("=".repeat(22));
            System.out.println("BMI van " + patient.getFirstName() + " " + patient.getLastName() + " is: " + bmiFormatted);
        } else {
            System.out.println("Ongeldige keuze.");
        }
    }


    public double calculateBMI(int gewicht, int lengte) {
        double lengteInMeters = lengte / 100.0;
        return gewicht / (lengteInMeters * lengteInMeters);
    }

    public void viewConsultant() throws IOException {
        Scanner sc = new Scanner(System.in);
        ArrayList<patient> list = loadPatientsFromJSON();
        viewData();
        System.out.print("Kies een patiënt (1-" + list.size() + "): ");
        int choicePatient = sc.nextInt();

        if (choicePatient >= 1 && choicePatient <= list.size()) {
            patient selectedPatient = list.get(choicePatient - 1);

            System.out.println("=".repeat(22));
            System.out.println("Consultantgegevens voor patiënt:");
            System.out.println("Patiëntnummer: " + selectedPatient.getPatientNummer());
            System.out.println("Naam: " + selectedPatient.getFirstName() + " " + selectedPatient.getLastName());
            System.out.println("Type Consult: " + selectedPatient.getConsult());

            int consultantIndex = -1;
            for (int i = 0; i < nameConsultant.length; i++) {
                for (int j = 0; j < nameConsultant[i].length; j++) {
                    consultantIndex++;
                    if (nameConsultant[i][j].equals(selectedPatient.getConsult())) {
                        System.out.println("Prijs Consult: $" + prijzenConsultant[i][j]);
                    }
                }
            }
            System.out.println("Datum consultant: ");
        } else {
            System.out.println("Ongeldige keuze voor patiënt.");
        }
    }


    public void viewConsultData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<patient> list = new ArrayList<>();
        File jsonFile = new File("/Users/kevinversteeg/IdeaProjects/Kevins ZorgApp/src/patienten.JSON");
        if (jsonFile.exists()) {
            list = mapper.readValue(jsonFile, new TypeReference<>() {
            });
        }
        viewData();
        int choice = Misc.input();

        if (choice >= 1 && choice <= list.size()) {
            patient selectedPatient = list.get(choice - 1);

            System.out.println("=".repeat(22));
            System.out.println("Naam: " + selectedPatient.getFirstName() + " " + selectedPatient.getLastName());
            System.out.println("Consult: " + selectedPatient.getConsult());

        }
    }

    public void longCapacity() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        File jsonFile = new File("/Users/kevinversteeg/IdeaProjects/Kevins ZorgApp/src/patienten.JSON");

        ArrayList<patient> list = loadPatientsFromJSON();
        Scanner scanner = new Scanner(System.in);
        viewData();
        System.out.println("Kies een patiënt (1-" + list.size() + "): ");
        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= list.size()) {
            patient selectedPatient = list.get(choice - 1);

            System.out.print("Voer de nieuwe long inhoud toe:");
            int newValue = scanner.nextInt();

            selectedPatient.setLungCapacity(String.valueOf(newValue));

            writer.writeValue(jsonFile, list);
            System.out.println("Longinhoud geregisteerd.");
        } else {
            System.out.println("Ongeldige keuze.");
        }
    }

    public int calcAge(String birthday) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birth = LocalDate.parse(birthday, formatter);
        LocalDate today = LocalDate.now();
        return Period.between(birth, today).getYears();
    }

    public int input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Voer uw keuze in: ");
        return sc.nextInt();
    }

    public void viewPatientsDentist() throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<patient> list = loadPatientsFromJSON();
        viewData();
        System.out.print("Kies een patiënt (1-" + list.size() + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice >= 1 && choice <= list.size()) {
            patient selectedPatient = list.get(choice - 1);

            System.out.println("=".repeat(11) + " " + selectedPatient.getPatientNummer() + " " + "=".repeat(11));
            System.out.println("Patiëntnummer: " + selectedPatient.getPatientNummer());
            System.out.println("Voornaam: " + selectedPatient.getFirstName());
            System.out.println("Achternaam: " + selectedPatient.getLastName());
            System.out.println("Geboortedatum: " + selectedPatient.getBirthDay());
            System.out.println("Leeftijd: " + calcAge(selectedPatient.getBirthDay()));
            System.out.println("Woonplaats: " + selectedPatient.getCity());
            System.out.println("Telefoonnummer: " + selectedPatient.getPhoneNumber());
            System.out.println("Medicatie: " + selectedPatient.getMedicatie());
            System.out.println("Consult: " + selectedPatient.getConsult());
            System.out.println("=".repeat(22));
        } else {
            System.out.println("Ongeldige keuze.");
        }
    }
    public void patientSearch() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File patientFile = new File("/Users/kevinversteeg/IdeaProjects/Kevins ZorgApp/src/patienten.JSON");
        JsonNode jsonData = objectMapper.readTree(patientFile);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Zoek naar een patiënt op achternaam: ");

        // Gebruikersinvoer
        String query = scanner.nextLine().toLowerCase().trim();

        if (jsonData.isArray()) {
            boolean found = false;

            for (JsonNode patient : jsonData) {
                JsonNode firstNameNode = patient.get("firstName");
                JsonNode lastNameNode = patient.get("lastName");

                if (firstNameNode != null && lastNameNode != null) {
                    String firstName = firstNameNode.asText().toLowerCase();
                    String lastName = lastNameNode.asText();
                    String birthday = patient.get("birthDay").asText();

                    if (lastName.equalsIgnoreCase(query)) {
                        found = true;

                        System.out.println("=".repeat(22));
                        System.out.println("Overeenkomende patiënt:");
                        System.out.println("Voornaam: " + firstName);
                        System.out.println("Achternaam: " + lastName);
                        System.out.println("Geboortedatum: " + birthday);
                    }
                }
            }

            if (!found) {
                System.out.println("Geen overeenkomende patiënten gevonden.");
            }
        } else {
            System.out.println("Geen gegevens gevonden in het JSON-bestand.");
        }
    }



    // BMI Grafiek
    // Werkt niet na behoren.
    public void bmiGraphByPatient() {
        String jsonFileName = "/Users/kevinversteeg/IdeaProjects/Kevins ZorgApp/src/patienten.JSON";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<patient> patients = objectMapper.readValue(new File(jsonFileName), new TypeReference<List<patient>>() {});

            System.out.println("Beschikbare patiënten:");
            for (int i = 0; i < patients.size(); i++) {
                System.out.println(i + 1 + ". " + patients.get(i).getFirstName() + " " + patients.get(i).getLastName());
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Kies een patiënt (voer het nummer in): ");
            int patientIndex = scanner.nextInt() - 1;

            if (patientIndex >= 0 && patientIndex < patients.size()) {
                patient selectedPatient = patients.get(patientIndex);

                int lengte = selectedPatient.getLengte();
                int gewicht = selectedPatient.getGewicht();

                int numDataPoints = 10;
                double[] BMI = new double[numDataPoints];

                for (int i = 0; i < numDataPoints; i++) {
                    BMI[i] = calculateBMI(lengte / 100.0, gewicht - (i * 5));
                }

                double[] BMISorted = BMI.clone();
                Arrays.sort(BMISorted);

                // Toon de BMI-grafiek
                for (int row = numDataPoints - 1; row >= 0; row--) {
                    if (row % 2 == 0) {
                        System.out.print(String.format("%.2f", BMISorted[row]) + "|");
                    } else {
                        System.out.print("     |");
                    }
                    for (double value : BMI) {
                        if (value == BMISorted[row]) {
                            System.out.print("▄ ");
                        } else if (value > BMISorted[row]) {
                            System.out.print("█ ");
                        } else if (value + 0.1 >= BMISorted[row]) {
                            System.out.print("▂ ");
                        } else {
                            System.out.print("  ");
                        }
                    }
                    System.out.println(); // Ga naar de volgende rij
                }

                // Toon x-as labels (optioneel)
                for (int i = 0; i < BMI.length; i++) {
                    System.out.print("-------");
                }
                System.out.println();
                System.out.print("     |");
                for (int i = 0; i < BMI.length; i++) {
                    System.out.print(i + 1 + " ");
                }
            } else {
                System.out.println("Ongeldige patiëntselectie.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private double calculateBMI(double height, double weight) {
        return weight / (height * height);
    }
}
