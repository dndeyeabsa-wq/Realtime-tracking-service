package sn.ept.brt.tracking.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import sn.ept.brt.tracking.config.KafkaConfig;
import sn.ept.brt.tracking.event.BusLocationEvent;
import sn.ept.brt.tracking.service.TrackingService;

@Component
public class BusLocationConsumer {

    private static final Logger log = LoggerFactory.getLogger(BusLocationConsumer.class);

    private final TrackingService trackingService;

    public BusLocationConsumer(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @KafkaListener(
            topics = KafkaConfig.TOPIC_BUS_LOCATION,
            groupId = "tracking-group"
    )
    public void consumeBusLocation(BusLocationEvent event) {
        log.info("Event recu — bus: {} trip: {}",
                event.getBusId(), event.getTripId());
        trackingService.updateBusLocation(event);
    }

    @KafkaListener(
            topics = KafkaConfig.TOPIC_TRIP_STARTED,
            groupId = "tracking-group"
    )
    public void consumeTripStarted(BusLocationEvent event) {
        log.info("Trajet demarre — tripId: {}", event.getTripId());
    }
}