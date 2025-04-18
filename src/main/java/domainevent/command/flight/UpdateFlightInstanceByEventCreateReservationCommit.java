package domainevent.command.flight;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;
import domainevent.publisher.flightqueue.JMSFlightPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;
import msa.commons.microservices.flight.modifyreservation.qualifier.UpdateFlightByModifyReservationCommit;

@Stateless
@UpdateFlightByModifyReservationCommit
@Local(CommnadHandler.class)
public class UpdateFlightInstanceByEventCreateReservationCommit extends BaseEventHandler {
    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSFlightPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.FLIGHT_UPDATE_FLIGHT_BY_AIRLINE_MODIFY_RESERVATION_COMMIT_SAGA;
    }
}
