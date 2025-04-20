package domainevent.command.reservation;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import domainevent.publisher.reservationqueue.JMSReservationPublisherQualifier;
import msa.commons.event.EventId;
import msa.commons.microservices.reservationairline.removereservation.qualifier.RemoveReservationByCommitQualifier;

@Stateless
@RemoveReservationByCommitQualifier
@Local(CommnadHandler.class)
public class CommitRemoveReservationByEventRemoveReservation extends BaseEventHandler {
    
    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSReservationPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.RESERVATION_AIRLINE_REMOVE_RESERVATION_COMMIT_SAGA;
    }
}
