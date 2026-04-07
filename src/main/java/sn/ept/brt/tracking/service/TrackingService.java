package sn.ept.brt.tracking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import sn.ept.brt.tracking.dto.BusLocationDTO;
import sn.ept.brt.tracking.dto.BusPositionResponse;
import sn.ept.brt.tracking.event.BusLocationEvent;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TrackingService {

    private static final Logger log = LoggerFactory.getLogger(TrackingService.class);

    private final RedisTemplate<String, Object> redisTemplate;
    private final SimpMessagingTemplate messagingTemplate;

    @Value("${tracking.redis.ttl-seconds:300}")
    private long ttlSeconds;

    private static final String KEY_PREFIX = "bus:location:";

    public TrackingService(RedisTemplate<String, Object> redisTemplate,
                           SimpMessagingTemplate messagingTemplate) {
        this.redisTemplate = redisTemplate;
        this.messagingTemplate = messagingTemplate;
    }

    public void updateBusLocation(BusLocationEvent event) {
        String key = KEY_PREFIX + event.getBusId();

        BusLocationDTO dto = BusLocationDTO.builder()
                .busId(event.getBusId())
                .tripId(event.getTripId())
                .latitude(event.getLatitude())
                .longitude(event.getLongitude())
                .speed(event.getSpeed())
                .status(event.getStatus())
                .lastUpdate(LocalDateTime.now())
                .build();

        redisTemplate.opsForValue().set(key, dto, Duration.ofSeconds(ttlSeconds));
        log.info("Position mise a jour — bus: {} lat: {} lng: {}",
                event.getBusId(), event.getLatitude(), event.getLongitude());

        messagingTemplate.convertAndSend("/topic/bus/" + event.getBusId(), dto);
        messagingTemplate.convertAndSend("/topic/tracking/all", dto);
    }

    public BusLocationDTO getBusLocation(String busId) {
        String key = KEY_PREFIX + busId;
        return (BusLocationDTO) redisTemplate.opsForValue().get(key);
    }

    public BusPositionResponse getAllActiveBuses() {
        Set<String> keys = redisTemplate.keys(KEY_PREFIX + "*");
        List<BusLocationDTO> buses = new ArrayList<>();

        if (keys != null) {
            for (String key : keys) {
                BusLocationDTO dto = (BusLocationDTO) redisTemplate
                        .opsForValue().get(key);
                if (dto != null) buses.add(dto);
            }
        }

        return BusPositionResponse.builder()
                .total(buses.size())
                .buses(buses)
                .build();
    }

    public void removeBus(String busId) {
        redisTemplate.delete(KEY_PREFIX + busId);
        log.info("Bus {} retire du tracking", busId);
    }
}