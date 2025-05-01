package domainevent.command.reservation;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.modifyreservation.UpdateReservationByModifyReservationRollback;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;

import domainevent.publisher.jmseventpublisher.IEventPublisher;
import domainevent.publisher.reservationqueue.JMSReservationPublisherQualifier;
import msa.commons.event.EventId;

@Stateless
@UpdateReservationByModifyReservationRollback
@Local(CommnadHandler.class)
public class RollbackUpdateReservationByEventModifyReservation extends BaseEventHandler {

    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSReservationPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.RESERVATION_AIRLINE_MODIFY_RESERVATION_ROLLBACK_SAGA;
    }
    
}
