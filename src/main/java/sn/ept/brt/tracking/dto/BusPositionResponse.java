package sn.ept.brt.tracking.dto;

import java.util.List;

public class BusPositionResponse {

    private int total;
    private List<BusLocationDTO> buses;

    public BusPositionResponse() {}

    public BusPositionResponse(int total, List<BusLocationDTO> buses) {
        this.total = total;
        this.buses = buses;
    }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public List<BusLocationDTO> getBuses() { return buses; }
    public void setBuses(List<BusLocationDTO> buses) { this.buses = buses; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private int total;
        private List<BusLocationDTO> buses;

        public Builder total(int total) { this.total = total; return this; }
        public Builder buses(List<BusLocationDTO> buses) { this.buses = buses; return this; }

        public BusPositionResponse build() {
            return new BusPositionResponse(total, buses);
        }
    }
}
