package com.library.app.model;

import java.sql.Date;

public class RoomBookingDetail {
    private int id;
    private int roomBookingId;
    private String timeFrame;
    private String dateBook;
    private String roomCode;

    public int getId() {
        return id;
    }

    public int getRoomBookingId() {
        return roomBookingId;
    }

    public String getTimeFrame() {
        return timeFrame;
    }

    public String getDateBook() {
        return dateBook;
    }

    public String getRoomCode() {
        return roomCode;
    }
}
