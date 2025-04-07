package domainevent.publisher.customerqueue;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.jms.Queue;

import domainevent.publisher.jmseventpublisher.BaseJMSEventPublisher;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import integration.producer.qualifiers.CustomerQueue;
import msa.commons.consts.JMSQueueNames;


@Stateless
@JMSCustomerPublisherQualifier
@Local(IEventPublisher.class)
public class JMSCustomerPublisher extends BaseJMSEventPublisher {

    @Inject
    @Override
    public void setQueueInject(@CustomerQueue Queue customerQueue) {
        this.queue = customerQueue;
    }

    @Override
    public String getQueueName() {
        return JMSQueueNames.AIRLINE_CUSTOMER;
    }
}
