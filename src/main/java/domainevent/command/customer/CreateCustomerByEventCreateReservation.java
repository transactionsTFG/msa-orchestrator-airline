package domainevent.command.customer;


import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.createreservation.CreateCustomerByCreateReservationEventQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;
import domainevent.publisher.customerqueue.JMSCustomerPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;

@Stateless
@CreateCustomerByCreateReservationEventQualifier
@Local(CommnadHandler.class)
public class CreateCustomerByEventCreateReservation extends BaseEventHandler{

    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSCustomerPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.CUSTOMER_AIRLINE_CREATE_CUSTOMER_RESERVATION_AIRLINE_CREATE_RESERVATION_COMMIT_SAGA;
    }
    
}
