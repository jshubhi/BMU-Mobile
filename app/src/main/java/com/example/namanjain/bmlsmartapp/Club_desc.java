package com.example.namanjain.bmlsmartapp;

 class Club_desc {
    private String club_name;
    private String club_desc;
    private int club_logo;

Club_desc(String club_name, String club_desc, int club_logo) {
        this.club_name = club_name;
        this.club_desc = club_desc;
        this.club_logo = club_logo;
    }

 String getClub_name() {
        return club_name;
    }
String getClub_desc() {
        return club_desc;
    }
 int getClub_logo() {
        return club_logo;
    }
}

