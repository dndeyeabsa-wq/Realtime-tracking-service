package sn.ept.brt.tracking.dto;

import java.time.LocalDateTime;

public class BusLocationDTO {

    private String busId;
    private String tripId;
    private Double latitude;
    private Double longitude;
    private Double speed;
    private String status;
    private LocalDateTime lastUpdate;

    public BusLocationDTO() {}

    public BusLocationDTO(String busId, String tripId, Double latitude,
                          Double longitude, Double speed, String status,
                          LocalDateTime lastUpdate) {
        this.busId = busId;
        this.tripId = tripId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.status = status;
        this.lastUpdate = lastUpdate;
    }

    public String getBusId() { return busId; }
    public void setBusId(String busId) { this.busId = busId; }

    public String getTripId() { return tripId; }
    public void setTripId(String tripId) { this.tripId = tripId; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public Double getSpeed() { return speed; }
    public void setSpeed(Double speed) { this.speed = speed; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String busId;
        private String tripId;
        private Double latitude;
        private Double longitude;
        private Double speed;
        private String status;
        private LocalDateTime lastUpdate;

        public Builder busId(String busId) { this.busId = busId; return this; }
        public Builder tripId(String tripId) { this.tripId = tripId; return this; }
        public Builder latitude(Double latitude) { this.latitude = latitude; return this; }
        public Builder longitude(Double longitude) { this.longitude = longitude; return this; }
        public Builder speed(Double speed) { this.speed = speed; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder lastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; return this; }

        public BusLocationDTO build() {
            return new BusLocationDTO(busId, tripId, latitude, longitude,
                    speed, status, lastUpdate);
        }
    }
}