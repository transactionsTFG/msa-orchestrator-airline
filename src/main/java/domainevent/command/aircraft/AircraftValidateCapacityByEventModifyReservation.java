package domainevent.command.aircraft;

import domainevent.command.handler.BaseEventHandler;


import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import domainevent.command.handler.CommnadHandler;
import domainevent.publisher.aircraftqueue.JMSAircraftPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;
import msa.commons.microservices.aircraft.modifyreservation.qualifier.ValidateCapacityAircrafEventModifyReservationftQualifier;


@Stateless
@ValidateCapacityAircrafEventModifyReservationftQualifier
@Local(CommnadHandler.class)
public class AircraftValidateCapacityByEventModifyReservation extends BaseEventHandler {

    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSAircraftPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.AIRCRAFT_VALIDATE_CAPACITY_RESERVATION_AIRLINE_MODIFY_RESERVATION;
    }
    
}
