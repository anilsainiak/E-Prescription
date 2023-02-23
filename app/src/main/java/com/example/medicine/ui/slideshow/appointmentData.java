package com.example.medicine.ui.slideshow;

public class appointmentData {
    private String appointment_Date;
    private String appointment_Staff;
    private String appointment_item;
    private String appointment_time;
    private String appointment_duration;
    private String appointment_notes;

    public appointmentData(String appointment_Date, String appointment_Staff, String appointment_item, String appointment_time, String appointment_duration, String appointment_notes) {
        this.appointment_Date = appointment_Date;
        this.appointment_Staff = appointment_Staff;
        this.appointment_item = appointment_item;
        this.appointment_time = appointment_time;
        this.appointment_duration = appointment_duration;
        this.appointment_notes = appointment_notes;
    }

    public String getAppointment_Date() {
        return appointment_Date;
    }

    public void setAppointment_Date(String appointment_Date) {
        this.appointment_Date = appointment_Date;
    }

    public String getAppointment_Staff() {
        return appointment_Staff;
    }

    public void setAppointment_Staff(String appointment_Staff) {
        this.appointment_Staff = appointment_Staff;
    }

    public String getAppointment_item() {
        return appointment_item;
    }

    public void setAppointment_item(String appointment_item) {
        this.appointment_item = appointment_item;
    }

    public String getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(String appointment_time) {
        this.appointment_time = appointment_time;
    }

    public String getAppointment_duration() {
        return appointment_duration;
    }

    public void setAppointment_duration(String appointment_duration) {
        this.appointment_duration = appointment_duration;
    }

    public String getAppointment_notes() {
        return appointment_notes;
    }

    public void setAppointment_notes(String appointment_notes) {
        this.appointment_notes = appointment_notes;
    }
}
