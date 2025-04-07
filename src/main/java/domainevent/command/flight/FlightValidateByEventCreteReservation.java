package domainevent.command.flight;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.EventHandler;
import domainevent.publisher.flightqueue.JMSFlightPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;
import msa.commons.microservices.flight.qualifier.ValidateFlightByEventCreateReservationQualifier;

@Stateless
@ValidateFlightByEventCreateReservationQualifier
@Local(EventHandler.class)
public class FlightValidateByEventCreteReservation extends BaseEventHandler {

    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSFlightPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.FLIGHT_VALIDATE_FLIGHT_RESERVATION_AIRLINE_CREATE_RESERVATION;
    }
    
}
