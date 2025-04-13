package domainevent.command.aircraft;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;
import domainevent.publisher.aircraftqueue.JMSAircraftPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;
import msa.commons.microservices.aircraft.qualifier.ValidateCapacityAircraEventCreateReservationftQualifier;


@Stateless
@ValidateCapacityAircraEventCreateReservationftQualifier
@Local(CommnadHandler.class)
public class AircraftValidateCapacityByEventCreateReservation extends BaseEventHandler {

    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSAircraftPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.AIRCRAFT_VALIDATE_CAPACITY_RESERVATION_AIRLINE_CREATE_RESERVATION;
    }
    
}
