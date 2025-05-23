package domainevent.command.flight;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.createreservation.UpdateFlightByEventCreateReservationQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;
import domainevent.publisher.flightqueue.JMSFlightPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;

@Stateless
@UpdateFlightByEventCreateReservationQualifier
@Local(CommnadHandler.class)
public class UpdateFlightInstanceByEventCreateReservation extends BaseEventHandler {

    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSFlightPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.FLIGHT_UPDATE_FLIGHT_AIRLINE_CREATE_RESERVATION_COMMIT_SAGA;
    }
}
