package ZorgApp;


public class Medication {
}

class MedicationData {
    private String medName;
    private int medID;
    private int medDosage;
    private String medDesc;
    private String medStock;

    public MedicationData() {}

    public MedicationData(String medName, int medID, int medDosage, String medDesc, String medStock) {
        this.medName = medName;
        this.medID = medID;
        this.medDosage = medDosage;
        this.medDesc = medDesc;
        this.medStock = medStock;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public int getMedID() {
        return medID;
    }

    public void setMedID(int medID) {
        this.medID = medID;
    }

    public int getMedDosage() {
        return medDosage;
    }

    public void setMedDosage(int medDosage) {
        this.medDosage = medDosage;
    }

    public String getMedDesc() {
        return medDesc;
    }

    public void setMedDesc(String medDesc) {
        this.medDesc = medDesc;
    }

    public String getMedStock() {
        return medStock;
    }

    public void setMedStock(String medStock) {
        this.medStock = medStock;
    }
}