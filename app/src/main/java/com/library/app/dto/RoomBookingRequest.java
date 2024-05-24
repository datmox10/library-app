package com.library.app.dto;

import com.google.gson.annotations.JsonAdapter;

import java.sql.Date;
import java.util.List;

public class RoomBookingRequest {
    private String roomCode;
    private List<String> timeFrames;
    private String userCode;
    private Date dateBook;
    private String reason;
    private int quantity;

    public RoomBookingRequest(String roomCode, List<String> timeFrames, String userCode, Date dateBook, String reason, int quantity) {
        this.roomCode = roomCode;
        this.timeFrames = timeFrames;
        this.userCode = userCode;
        this.dateBook = dateBook;
        this.reason = reason;
        this.quantity = quantity;
    }
}
