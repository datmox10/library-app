package com.library.app.dto;

public class RoomBookingResponse {
    public String message;

    public RoomBookingResponse(String message) {
        this.message = message;
    }

    public RoomBookingResponse() {

    }

    @Override
    public String toString() {
        return "RoomBookingResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
