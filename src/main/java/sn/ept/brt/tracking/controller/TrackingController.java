package sn.ept.brt.tracking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ept.brt.tracking.dto.BusLocationDTO;
import sn.ept.brt.tracking.dto.BusPositionResponse;
import sn.ept.brt.tracking.service.TrackingService;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    private final TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping("/buses")
    public ResponseEntity<BusPositionResponse> getAllBuses() {
        return ResponseEntity.ok(trackingService.getAllActiveBuses());
    }

    @GetMapping("/buses/{busId}")
    public ResponseEntity<BusLocationDTO> getBusLocation(
            @PathVariable String busId) {
        BusLocationDTO dto = trackingService.getBusLocation(busId);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/buses/{busId}")
    public ResponseEntity<Void> removeBus(@PathVariable String busId) {
        trackingService.removeBus(busId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("realtime-tracking-service UP");
    }
}
