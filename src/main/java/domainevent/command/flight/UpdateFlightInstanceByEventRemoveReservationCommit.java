package domainevent.command.flight;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.removereservation.UpdateFlightByRemoveReservationCommitQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;
import domainevent.publisher.flightqueue.JMSFlightPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;

@Stateless
@UpdateFlightByRemoveReservationCommitQualifier
@Local(CommnadHandler.class)
public class UpdateFlightInstanceByEventRemoveReservationCommit extends BaseEventHandler {
    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSFlightPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.FLIGHT_UPDATE_FLIGHT_BY_AIRLINE_REMOVE_RESERVATION_COMMIT_SAGA;
    }
}
