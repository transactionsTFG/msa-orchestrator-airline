package domainevent.publisher.aircraftqueue;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.jms.Queue;

import domainevent.publisher.jmseventpublisher.BaseJMSEventPublisher;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import integration.producer.qualifiers.AircraftQueue;
import msa.commons.consts.JMSQueueNames;

@Stateless
@JMSAircraftPublisherQualifier
@Local(IEventPublisher.class)
public class JMSAircraftPublisher extends BaseJMSEventPublisher {
    @Inject
    @Override
    public void setQueueInject(@AircraftQueue Queue aircraQueue) {
        this.queue = aircraQueue;
    }

    @Override
    public String getQueueName() {
        return JMSQueueNames.AIRLINE_AIRCRAFT_QUEUE;
    }
}
