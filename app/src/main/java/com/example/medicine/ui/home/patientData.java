package com.example.medicine.ui.home;

public class patientData {
    private String patient_name;
    private String patient_dob;
    private String patient_number;
    private String patient_email;
    private String patient_address;

    public patientData(String patient_name, String patient_dob, String patient_number, String patient_email, String patient_address) {
        this.patient_name = patient_name;
        this.patient_dob = patient_dob;
        this.patient_number = patient_number;
        this.patient_email = patient_email;
        this.patient_address = patient_address;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_dob() {
        return patient_dob;
    }

    public void setPatient_dob(String patient_dob) {
        this.patient_dob = patient_dob;
    }

    public String getPatient_number() {
        return patient_number;
    }

    public void setPatient_number(String patient_number) {
        this.patient_number = patient_number;
    }

    public String getPatient_email() {
        return patient_email;
    }

    public void setPatient_email(String patient_email) {
        this.patient_email = patient_email;
    }

    public String getPatient_address() {
        return patient_address;
    }

    public void setPatient_address(String patient_address) {
        this.patient_address = patient_address;
    }
}
