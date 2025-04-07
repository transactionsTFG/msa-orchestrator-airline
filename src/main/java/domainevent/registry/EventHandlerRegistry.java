package domainevent.registry;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import domainevent.command.handler.EventHandler;
import msa.commons.event.EventId;
import msa.commons.microservices.aircraft.qualifier.ValidateCapacityAircraEventCreateReservationftQualifier;
import msa.commons.microservices.customerairline.qualifier.GetCustomerByCreateReservationEventQualifier;
import msa.commons.microservices.flight.qualifier.ValidateFlightByEventCreateReservationQualifier;
import msa.commons.microservices.reservationairline.qualifier.CreateReservationCommitQualifier;
import msa.commons.microservices.reservationairline.qualifier.CreateReservationRollbackQualifier;

@Singleton
@Startup
public class EventHandlerRegistry {
    private Map<EventId, EventHandler> handlers = new EnumMap<>(EventId.class);
    private EventHandler getCustomerByEventCreateReservation;
    private EventHandler flightValidateByEventCreteReservation;
    private EventHandler aircraftValidateCapacityByEventCreateReservation;
    private EventHandler rollbackCreateReservationByEventCreateReservation;
    private EventHandler commitCreateReservationByEventCreateReservation; 
    private EventHandler createCustomerByEventCreateReservation; 

    @PostConstruct
    public void init(){
        this.handlers.put(EventId.CUSTOMER_AIRLINE_GET_CUSTOMER_RESERVATION_AIRLINE_CREATE_RESERVATION, getCustomerByEventCreateReservation);
        this.handlers.put(EventId.FLIGHT_VALIDATE_FLIGHT_RESERVATION_AIRLINE_CREATE_RESERVATION, flightValidateByEventCreteReservation);
        this.handlers.put(EventId.AIRCRAFT_VALIDATE_CAPACITY_RESERVATION_AIRLINE_CREATE_RESERVATION, aircraftValidateCapacityByEventCreateReservation);
        this.handlers.put(EventId.RESERVATION_AIRLINE_CREATE_RESERVATION_COMMIT_SAGA, commitCreateReservationByEventCreateReservation);
        this.handlers.put(EventId.RESERVATION_AIRLINE_CREATE_RESERVATION_ROLLBACK_SAGA, rollbackCreateReservationByEventCreateReservation);
        this.handlers.put(EventId.AIRCRAFT_PROPAGATION_SAVE_RESERVATION_AIRLINE_CREATE_RESERVATION_COMMIT_SAGA, createCustomerByEventCreateReservation);
    }

    public EventHandler getHandler(EventId eventId) {
        return this.handlers.get(eventId);
    }

    @Inject
    public void setGetCustomerByEventCreateReservation(@GetCustomerByCreateReservationEventQualifier EventHandler getCustomerByEventCreateReservation) {
        this.getCustomerByEventCreateReservation = getCustomerByEventCreateReservation;
    }

    @Inject
    public void setFlightValidateByEventCreteReservation(@ValidateFlightByEventCreateReservationQualifier EventHandler flightValidateByEventCreteReservation) {
        this.flightValidateByEventCreteReservation = flightValidateByEventCreteReservation;
    }

    @Inject
    public void setAircraftValidateCapacityByEventCreateReservation(@ValidateCapacityAircraEventCreateReservationftQualifier EventHandler aircraftValidateCapacityByEventCreateReservation) {
        this.aircraftValidateCapacityByEventCreateReservation = aircraftValidateCapacityByEventCreateReservation;
    }

    @Inject
    public void setCommitCreateReservationByEventCreateReservation(@CreateReservationCommitQualifier EventHandler commitCreateReservationByEventCreateReservation) {
        this.commitCreateReservationByEventCreateReservation = commitCreateReservationByEventCreateReservation;
    }

    @Inject
    public void setRollbackCreateReservationByEventCreateReservation(@CreateReservationRollbackQualifier EventHandler rollbackCreateReservationByEventCreateReservation) {
        this.rollbackCreateReservationByEventCreateReservation = rollbackCreateReservationByEventCreateReservation;
    }
}
