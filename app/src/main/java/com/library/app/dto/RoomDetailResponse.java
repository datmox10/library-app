package com.library.app.dto;

import com.library.app.model.RoomBooking;
import com.library.app.model.RoomBookingDetail;

import java.util.List;

public class RoomDetailResponse {
    private RoomBooking room_booking;
    private List<RoomBookingDetail> list_room_detail;

    public RoomBooking getRoom_booking() {
        return room_booking;
    }

    public List<RoomBookingDetail> getList_room_detail() {
        return list_room_detail;
    }
}
