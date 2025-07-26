package domainevent.command.reservation;


import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.modifyreservation.UpdateReservationBeginByModifyReservation;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;

import domainevent.publisher.jmseventpublisher.IEventPublisher;
import domainevent.publisher.orchestratoragencyqueue.JMSOrchestratorAgencyQualifier;
import domainevent.publisher.reservationqueue.JMSReservationPublisherQualifier;
import msa.commons.event.EventData;
import msa.commons.event.EventId;
import msa.commons.event.eventoperation.reservation.UpdateReservation;


@Stateless
@UpdateReservationBeginByModifyReservation
@Local(CommnadHandler.class)
public class BeginUpdateReservationByEventUpdateReservation extends BaseEventHandler {
    private IEventPublisher jmsEventDispatcherAgency;

    @Override
    public void handle(EventData data) {
        if (UpdateReservation.UPDATE_RESERVATION_ONLY_AIRLINE_BEGIN.equals(data.getOperation())) 
            this.jmsEventDispatcher.publish(this.sendEventId(), data);
        else 
            this.jmsEventDispatcherAgency.publish(this.sendEventId(), data);
    }

    @Inject
    public void setJmsEventDispatcherAgency(@JMSOrchestratorAgencyQualifier IEventPublisher jmsEventDispatcherAgency) {
        this.jmsEventDispatcherAgency = jmsEventDispatcherAgency;
    }

    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSReservationPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.UPDATE_RESERVATION_TRAVEL;
    }
}
