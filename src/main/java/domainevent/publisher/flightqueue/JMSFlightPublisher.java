package domainevent.publisher.flightqueue;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.jms.Queue;

import domainevent.publisher.jmseventpublisher.BaseJMSEventPublisher;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import integration.producer.qualifiers.FlightQueue;
import integration.producer.qualifiers.ReservationQueue;
import msa.commons.consts.JMSQueueNames;


@Stateless
@JMSFlightPublisherQualifier
@Local(IEventPublisher.class)
public class JMSFlightPublisher extends BaseJMSEventPublisher {

    @Inject
    @Override
    public void setQueueInject(@FlightQueue Queue fligbQueue) {
        this.queue = fligbQueue;
    }

    @Override
    public String getQueueName() {
        return JMSQueueNames.AIRLINE_FLIGHT_QUEUE;
    }
}
