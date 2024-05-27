package com.library.app.dto;

import android.util.Log;

import com.google.gson.annotations.JsonAdapter;

import java.sql.Date;
import java.util.List;

public class RoomBookingRequest {
    private String roomCode;
    private List<String> timeFrames;
    private String userCode;
    private String dateBook;
    private String reason;
    private int quantity;

    public RoomBookingRequest(String roomCode, List<String> timeFrames, String userCode, String dateBook, String reason, int quantity) {
        this.roomCode = roomCode;
        this.timeFrames = timeFrames;
        this.userCode = userCode;
        this.dateBook = dateBook;
        this.reason = reason;
        this.quantity = quantity;

        Log.d("RoomCode:", roomCode);
        Log.d("timeFrames length:", timeFrames.size()+"");
        Log.d("userCode:", userCode);
        Log.d("date:", dateBook.toString());
        Log.d("reason:", reason);
        Log.d("quantity:", quantity+"");
    }
}
