package domainevent.publisher.reservationqueue;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.jms.Queue;

import domainevent.publisher.jmseventpublisher.BaseJMSEventPublisher;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import integration.producer.qualifiers.ReservationQueue;
import msa.commons.consts.JMSQueueNames;


@Stateless
@JMSReservationPublisherQualifier
@Local(IEventPublisher.class)
public class JMSReservationPublisher extends BaseJMSEventPublisher {

    @Inject
    @Override
    public void setQueueInject(@ReservationQueue Queue userServiceQueue) {
        this.queue = userServiceQueue;
    }

    @Override
    public String getQueueName() {
        return JMSQueueNames.AIRLINE_RESERVATION_QUEUE;
    }
}
