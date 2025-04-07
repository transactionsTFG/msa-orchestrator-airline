package domainevent.publisher.customerqueue;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.jms.Queue;

import domainevent.publisher.jmseventpublisher.BaseJMSEventPublisher;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import integration.producer.qualifiers.ReservationQueue;
import msa.commons.consts.JMSQueueNames;


@Stateless
@JMSCustomerPublisherQualifier
@Local(IEventPublisher.class)
public class JMSCustomerPublisher extends BaseJMSEventPublisher {

    @Inject
    @Override
    public void setQueueInject(@ReservationQueue Queue userServiceQueue) {
        this.queue = userServiceQueue;
    }

    @Override
    public String getQueueName() {
        return JMSQueueNames.AIRLINE_CUSTOMER;
    }
}
