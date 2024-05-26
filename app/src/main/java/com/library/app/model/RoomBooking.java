package com.library.app.model;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;
import java.sql.Date;


public class RoomBooking {
    private Integer id;
    private Timestamp createdDate;
    private String userBookCode;
    private String reason;
    private Integer quantity;
    private String status;
    private String dateBook;
    private Set<RoomBookingDetail> roomBookingsDetail;

    public Integer getId() {
        return id;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public String getUserBookCode() {
        return userBookCode;
    }

    public String getReason() {
        return reason;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public String getDateBook() {
        return dateBook;
    }

    public Set<RoomBookingDetail> getRoomBookingsDetail() {
        return roomBookingsDetail;
    }
}
