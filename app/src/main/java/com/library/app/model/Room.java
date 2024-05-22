package com.library.app.model;

import java.util.List;

public class Room {
    private String roomCode;
    private String roomType;
    private List<String> equipment;
    private String location;
    private int quantityAllowed;
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

    public int getQuantityAllowed() {
        return quantityAllowed;
    }

    public List<RoomTimeFrameResponse> getRoomTimeFrameStatus() {
        return roomTimeFrameStatus;
    }

    public Room(String roomCode, String roomType, List<String> equipment, String location, int quantityAllowed, List<RoomTimeFrameResponse> roomTimeFrameStatus) {
        this.roomCode = roomCode;
        this.roomType = roomType;
        this.equipment = equipment;
        this.location = location;
        this.quantityAllowed = quantityAllowed;
        this.roomTimeFrameStatus = roomTimeFrameStatus;
    }

    public class RoomTimeFrameResponse{
        private String timeFrame;
        private int startTime;
        private int endTime;
        private int status;

        public RoomTimeFrameResponse(String timeFrame, int startTime, int endTime, int status) {
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

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
