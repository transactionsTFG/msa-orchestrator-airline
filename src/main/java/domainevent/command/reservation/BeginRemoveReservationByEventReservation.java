package domainevent.command.reservation;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.createreservation.CreateReservationEventQualifier;
import business.qualifier.removereservation.BeginRemoveReservationQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;

import domainevent.publisher.jmseventpublisher.IEventPublisher;
import domainevent.publisher.orchestratoragencyqueue.JMSOrchestratorAgencyQualifier;
import domainevent.publisher.reservationqueue.JMSReservationPublisherQualifier;
import msa.commons.event.EventData;
import msa.commons.event.EventId;
import msa.commons.event.eventoperation.reservation.DeleteReservation;


@Stateless
@BeginRemoveReservationQualifier
@Local(CommnadHandler.class)
public class BeginRemoveReservationByEventReservation  extends BaseEventHandler {
    private IEventPublisher jmsEventDispatcherAgency;

    @Override
    public void handle(EventData data) {
        if (DeleteReservation.DELETE_RESERVATION_ONLY_AIRLINE_BEGIN.equals(data.getOperation())) 
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
        return EventId.CREATE_RESERVATION_TRAVEL;
    }
}
