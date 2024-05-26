package com.library.app.dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class RoomsBookedHistoryResponse {
    private UserBook userBook;
    private Set<RoomBookResponse> roomBookResponses = new HashSet<>();

    public Set<RoomBookResponse> getRoomBookResponses() {
        return roomBookResponses;
    }

    public static class UserBook {
        private String id;
        private String email;
        private String phoneNumber;
        private String fullName;
        private String userName;
    }

    public static class RoomBookResponse {
        private String id;
        private Timestamp createdDate;
        private String userBookCode;
        private String reason;
        private String quantity;
        private String status;
        private String dateBook;
        private Set<RoomBookDetailResponse> roomBookDetailResponses = new HashSet<>();

        public Set<RoomBookDetailResponse> getRoomBookDetailResponse() {
            return roomBookDetailResponses;
        }

        public String getDateBook() {
            return dateBook;
        }
    }

    public static class RoomBookDetailResponse {
        private int id;
        private String roomBookingId;
        private String timeFrame;

        private String dateBook;
        private RoomResponse roomResponse;
        public String getRoomBookingId() {
            return roomBookingId;
        }
        public RoomResponse getRoomResponse() {
            return roomResponse;
        }
    }

    public static class RoomResponse {
        private String roomCode;
        // phân loại room
        private String roomType;
        // thiết bị bên trong phòng
        private String equipment;
        private String location;
        // trang thai cua phong : hoat dong? ko hoat dong
        private Boolean status;
        // Số lượng người cho phép sử dụng phòng cùng một lúc
        private String quantityAllowed;

        public String getRoomCode() {
            return roomCode;
        }
    }
}
