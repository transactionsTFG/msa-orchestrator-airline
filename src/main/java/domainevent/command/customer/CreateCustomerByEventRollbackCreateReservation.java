package domainevent.command.customer;


import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;
import domainevent.publisher.customerqueue.JMSCustomerPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;
import msa.commons.microservices.customerairline.qualifier.CreateCustomerByCreateReservationEventRollbackQualifier;

@Stateless
@CreateCustomerByCreateReservationEventRollbackQualifier
@Local(CommnadHandler.class)
public class CreateCustomerByEventRollbackCreateReservation extends BaseEventHandler{

    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSCustomerPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.CUSTOMER_AIRLINE_CREATE_CUSTOMER_RESERVATION_AIRLINE_CREATE_RESERVATION_ROLLBACK_SAGA;
    }
    
}
