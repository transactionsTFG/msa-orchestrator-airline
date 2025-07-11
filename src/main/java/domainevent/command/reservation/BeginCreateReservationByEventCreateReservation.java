package domainevent.command.reservation;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.createreservation.BeginCreateReservationEventQualifier;
import business.qualifier.createreservation.CreateReservationCommitQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;

import domainevent.publisher.jmseventpublisher.IEventPublisher;
import domainevent.publisher.orchestratoragencyqueue.JMSOrchestratorAgencyQualifier;
import msa.commons.event.EventId;


@Stateless
@BeginCreateReservationEventQualifier
@Local(CommnadHandler.class)
public class BeginCreateReservationByEventCreateReservation extends BaseEventHandler {
    
    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSOrchestratorAgencyQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.RESERVATION_AIRLINE_CREATE_RESERVATION_BEGIN_SAGA;
    }

}
