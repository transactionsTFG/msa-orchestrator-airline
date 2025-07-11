package domainevent.command.reservation;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.createreservation.CreateReservationTravelRollbackEventQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;

import domainevent.publisher.jmseventpublisher.IEventPublisher;
import domainevent.publisher.orchestratoragencyqueue.JMSOrchestratorAgencyQualifier;
import msa.commons.event.EventId;


@Stateless
@CreateReservationTravelRollbackEventQualifier
@Local(CommnadHandler.class)
public class RollbackCreateReservationTravelByEventCreateReservation extends BaseEventHandler {
    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSOrchestratorAgencyQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.CREATE_RESERVATION_TRAVEL_ONLY_AIRLINE_ROLLBACK;
    }
}
