package com.library.app.model;

import java.util.List;

public class Room {
    private String roomCode;
    private String roomType;
    private List<String> equipment;
    private String location;
    private String quantityAllowed;
    private List<RoomTimeFrameResponse> roomTimeFrameStatus;

    public String getRoomCode() {
        return roomCode;
    }

    public String getRoomType() {
        return roomType;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public String getLocation() {
        return location;
    }

    public String getQuantityAllowed() {
        return quantityAllowed;
    }

    public List<RoomTimeFrameResponse> getRoomTimeFrameStatus() {
        return roomTimeFrameStatus;
    }

    public Room(String roomCode, String roomType, List<String> equipment, String location, String quantityAllowed, List<RoomTimeFrameResponse> roomTimeFrameStatus) {
        this.roomCode = roomCode;
        this.roomType = roomType;
        this.equipment = equipment;
        this.location = location;
        this.quantityAllowed = quantityAllowed;
        this.roomTimeFrameStatus = roomTimeFrameStatus;
    }

    public class RoomTimeFrameResponse{
        private String timeFrame;
        private String startTime;
        private String endTime;
        private String status;

        public RoomTimeFrameResponse(String timeFrame, String startTime, String endTime, String status) {
            this.timeFrame = timeFrame;
            this.startTime = startTime;
            this.endTime = endTime;
            this.status = status;
        }

        public String getTimeFrame() {
            return timeFrame;
        }

        public void setTimeFrame(String timeFrame) {
            this.timeFrame = timeFrame;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
