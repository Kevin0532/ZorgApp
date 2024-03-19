package ZorgApp;

class patient {
    private int patientNummer;
    private String firstName;
    private String lastName;
    private String birthDay;
    private int age;
    private String city;
    private String phoneNumber;
    private String medicatie;
    private int lengte;
    private int gewicht;
    private String lungCapacity;
    private String consult;


    public patient() {}

    public patient(int patientNummer, String firstName, String lastName, String birthDay, int age, String city, String phoneNumber, String medicatie, int lengte, int gewicht, String lungCapacity, String consult) {
        this.patientNummer = patientNummer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.age = age;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.medicatie = medicatie;
        this.lengte = lengte;
        this.gewicht = gewicht;
        this.lungCapacity = lungCapacity;
        this.consult = consult;
    }

    public int getPatientNummer() {
        return patientNummer;
    }

    public void setPatientNummer(int patientNummer) {
        this.patientNummer = patientNummer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMedicatie() {
        return medicatie;
    }

    public void setMedicatie(String medicatie) {
        this.medicatie = medicatie;
    }

    public int getLengte() {
        return lengte;
    }

    public void setLengte(int lengte) {
        this.lengte = lengte;
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public String getLungCapacity() {
        return lungCapacity;
    }

    public void setLungCapacity(String lungCapacity) {
        this.lungCapacity = lungCapacity;
    }

    public String getConsult() {
        return consult;
    }

    public void setConsult(String consult) {
        this.consult = consult;
    }


}

