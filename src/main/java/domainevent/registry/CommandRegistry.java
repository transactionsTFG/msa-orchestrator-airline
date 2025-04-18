package domainevent.registry;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import domainevent.command.handler.CommnadHandler;
import msa.commons.event.EventId;
import msa.commons.microservices.aircraft.modifyreservation.qualifier.ValidateCapacityAircrafEventModifyReservationftQualifier;
import msa.commons.microservices.aircraft.qualifier.ValidateCapacityAircraEventCreateReservationftQualifier;
import msa.commons.microservices.customerairline.qualifier.CreateCustomerByCreateReservationEventQualifier;
import msa.commons.microservices.customerairline.qualifier.CreateCustomerByCreateReservationEventRollbackQualifier;
import msa.commons.microservices.customerairline.qualifier.GetCustomerByCreateReservationEventQualifier;
import msa.commons.microservices.flight.modifyreservation.qualifier.UpdateFlightByModifyReservationCommit;
import msa.commons.microservices.flight.modifyreservation.qualifier.UpdateFlightByModifyReservationRollback;
import msa.commons.microservices.flight.modifyreservation.qualifier.ValidateFlightByModifyReservation;
import msa.commons.microservices.flight.qualifier.UpdateFlightByEventCreateReservationQualifier;
import msa.commons.microservices.flight.qualifier.UpdateFlightByEventCreateReservationRollbackQualifier;
import msa.commons.microservices.flight.qualifier.ValidateFlightByEventCreateReservationQualifier;
import msa.commons.microservices.reservationairline.qualifier.CreateReservationCommitQualifier;
import msa.commons.microservices.reservationairline.qualifier.CreateReservationRollbackQualifier;
import msa.commons.microservices.reservationairline.updatereservation.qualifier.UpdateReservationByModifyReservationCommit;
import msa.commons.microservices.reservationairline.updatereservation.qualifier.UpdateReservationByModifyReservationRollback;

@Singleton
@Startup
public class CommandRegistry {
    private Map<EventId, CommnadHandler> handlers = new EnumMap<>(EventId.class);
    /* CREATE RESERVATION CYCLE */
    private CommnadHandler getCustomerByEventCreateReservation;
    private CommnadHandler flightValidateByEventCreteReservation;
    private CommnadHandler aircraftValidateCapacityByEventCreateReservation;
    private CommnadHandler rollbackCreateReservationByEventCreateReservation;
    private CommnadHandler commitCreateReservationByEventCreateReservation; 
    private CommnadHandler createCustomerByEventCreateReservation; 
    private CommnadHandler createCustomerByEventCreateReservationRollback;
    private CommnadHandler updateFlightInstanceByEventCreateReservationRollback;
    private CommnadHandler updateFlightInstanceByEventCreateReservation;
    /* MODIFY RESERVATION CYCLE */
    private CommnadHandler aircraftValidateCapacityByEventModifyReservation;
    private CommnadHandler flightValidateByEventModifyReservation;
    private CommnadHandler flightUpdateByEventModifyReservationCommit;
    private CommnadHandler flightUpdateByEventModifyReservationRollback;
    private CommnadHandler modifyReservationByEventModifyReservationCommit;
    private CommnadHandler modifyReservationByEventModifyReservationRollback;
    @PostConstruct
    public void init(){
        this.handlers.put(EventId.CUSTOMER_AIRLINE_GET_CUSTOMER_RESERVATION_AIRLINE_CREATE_RESERVATION, getCustomerByEventCreateReservation);
        this.handlers.put(EventId.FLIGHT_VALIDATE_FLIGHT_RESERVATION_AIRLINE_CREATE_RESERVATION, flightValidateByEventCreteReservation);
        this.handlers.put(EventId.AIRCRAFT_VALIDATE_CAPACITY_RESERVATION_AIRLINE_CREATE_RESERVATION, aircraftValidateCapacityByEventCreateReservation);
        this.handlers.put(EventId.RESERVATION_AIRLINE_CREATE_RESERVATION_COMMIT_SAGA, commitCreateReservationByEventCreateReservation);
        this.handlers.put(EventId.RESERVATION_AIRLINE_CREATE_RESERVATION_ROLLBACK_SAGA, rollbackCreateReservationByEventCreateReservation);
        this.handlers.put(EventId.AIRCRAFT_PROPAGATION_SAVE_RESERVATION_AIRLINE_CREATE_RESERVATION_COMMIT_SAGA, updateFlightInstanceByEventCreateReservation);
        this.handlers.put(EventId.FLIGHT_UPDATE_FLIGHT_AIRLINE_CREATE_RESERVATION_ROLLBACK_SAGA, updateFlightInstanceByEventCreateReservationRollback);
        this.handlers.put(EventId.CUSTOMER_AIRLINE_CREATE_CUSTOMER_RESERVATION_AIRLINE_CREATE_RESERVATION_COMMIT_SAGA, createCustomerByEventCreateReservation);
        this.handlers.put(EventId.CUSTOMER_AIRLINE_CREATE_CUSTOMER_RESERVATION_AIRLINE_CREATE_RESERVATION_ROLLBACK_SAGA, createCustomerByEventCreateReservationRollback);
        this.handlers.put(EventId.AIRCRAFT_VALIDATE_CAPACITY_RESERVATION_AIRLINE_MODIFY_RESERVATION, aircraftValidateCapacityByEventModifyReservation);
        this.handlers.put(EventId.FLIGHT_VALIDATE_FLIGHT_RESERVATION_AIRLINE_MODIFY_RESERVATION, flightValidateByEventModifyReservation);
        this.handlers.put(EventId.FLIGHT_UPDATE_FLIGHT_BY_AIRLINE_MODIFY_RESERVATION_COMMIT_SAGA, flightUpdateByEventModifyReservationCommit);
        this.handlers.put(EventId.FLIGHT_UPDATE_FLIGHT_BY_AIRLINE_MODIFY_RESERVATION_ROLLBACK_SAGA, flightUpdateByEventModifyReservationRollback);
        this.handlers.put(EventId.RESERVATION_AIRLINE_MODIFY_RESERVATION_COMMIT_SAGA, modifyReservationByEventModifyReservationCommit);
        this.handlers.put(EventId.RESERVATION_AIRLINE_MODIFY_RESERVATION_ROLLBACK_SAGA, modifyReservationByEventModifyReservationRollback);
    }

    public CommnadHandler getHandler(EventId eventId) {
        return this.handlers.get(eventId);
    }

    @Inject
    public void setGetCustomerByEventCreateReservation(@GetCustomerByCreateReservationEventQualifier CommnadHandler getCustomerByEventCreateReservation) {
        this.getCustomerByEventCreateReservation = getCustomerByEventCreateReservation;
    }

    @Inject
    public void setFlightValidateByEventCreteReservation(@ValidateFlightByEventCreateReservationQualifier CommnadHandler flightValidateByEventCreteReservation) {
        this.flightValidateByEventCreteReservation = flightValidateByEventCreteReservation;
    }

    @Inject
    public void setAircraftValidateCapacityByEventCreateReservation(@ValidateCapacityAircraEventCreateReservationftQualifier CommnadHandler aircraftValidateCapacityByEventCreateReservation) {
        this.aircraftValidateCapacityByEventCreateReservation = aircraftValidateCapacityByEventCreateReservation;
    }

    @Inject
    public void setCommitCreateReservationByEventCreateReservation(@CreateReservationCommitQualifier CommnadHandler commitCreateReservationByEventCreateReservation) {
        this.commitCreateReservationByEventCreateReservation = commitCreateReservationByEventCreateReservation;
    }

    @Inject
    public void setRollbackCreateReservationByEventCreateReservation(@CreateReservationRollbackQualifier CommnadHandler rollbackCreateReservationByEventCreateReservation) {
        this.rollbackCreateReservationByEventCreateReservation = rollbackCreateReservationByEventCreateReservation;
    }

    @Inject
    public void setCreateCustomerByEventCreateReservation(@CreateCustomerByCreateReservationEventQualifier CommnadHandler createCustomerByEventCreateReservation) {
        this.createCustomerByEventCreateReservation = createCustomerByEventCreateReservation;
    }

    @Inject
    public void setUpdateFlightInstanceByEventCreateReservation(@UpdateFlightByEventCreateReservationQualifier CommnadHandler updateFlightInstanceByEventCreateReservation) {
        this.updateFlightInstanceByEventCreateReservation = updateFlightInstanceByEventCreateReservation;
    }
    @Inject
    public void setCreateCustomerByEventCreateReservationRollback(@CreateCustomerByCreateReservationEventRollbackQualifier CommnadHandler createCustomerByEventCreateReservationRollback) {
        this.createCustomerByEventCreateReservationRollback = createCustomerByEventCreateReservationRollback;
    }
    @Inject
    public void setUpdateFlightInstanceByEventCreateReservationRollback(@UpdateFlightByEventCreateReservationRollbackQualifier CommnadHandler updateFlightInstanceByEventCreateReservationRollback) {
        this.updateFlightInstanceByEventCreateReservationRollback = updateFlightInstanceByEventCreateReservationRollback;
    }

    @Inject
    public void setAircraftValidateCapacityByEventModifyReservation(@ValidateCapacityAircrafEventModifyReservationftQualifier CommnadHandler aircraftValidateCapacityByEventModifyReservation) {
        this.aircraftValidateCapacityByEventModifyReservation = aircraftValidateCapacityByEventModifyReservation;
    }

    @Inject
    public void setFlightValidateByEventModifyReservation(@ValidateFlightByModifyReservation CommnadHandler flightValidateByEventModifyReservation) {
        this.flightValidateByEventModifyReservation = flightValidateByEventModifyReservation;
    }

    @Inject
    public void setFlightUpdateByEventModifyReservationCommit(@UpdateFlightByModifyReservationCommit CommnadHandler flightUpdateByEventModifyReservationCommit) {
        this.flightUpdateByEventModifyReservationCommit = flightUpdateByEventModifyReservationCommit;
    }

    @Inject
    public void setFlightUpdateByEventModifyReservationRollback(@UpdateFlightByModifyReservationRollback CommnadHandler flightUpdateByEventModifyReservationRollback) {
        this.flightUpdateByEventModifyReservationRollback = flightUpdateByEventModifyReservationRollback;
    }

    @Inject
    public void setModifyReservationByEventModifyReservationCommit(@UpdateReservationByModifyReservationCommit CommnadHandler modifyReservationByEventModifyReservationCommit) {
        this.modifyReservationByEventModifyReservationCommit = modifyReservationByEventModifyReservationCommit;
    }

    @Inject
    public void setModifyReservationByEventModifyReservationRollback(@UpdateReservationByModifyReservationRollback CommnadHandler modifyReservationByEventModifyReservationRollback) {
        this.modifyReservationByEventModifyReservationRollback = modifyReservationByEventModifyReservationRollback;
    }
}

