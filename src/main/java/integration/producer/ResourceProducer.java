package integration.producer;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import integration.producer.qualifiers.AircraftQueue;
import integration.producer.qualifiers.AirportQueue;
import integration.producer.qualifiers.CustomerQueue;
import integration.producer.qualifiers.FlightQueue;
import integration.producer.qualifiers.ReservationQueue;
import msa.commons.consts.JMSQueueNames;

@ApplicationScoped
public class ResourceProducer {
    @Produces
    @Resource(lookup = "jms/connectionFactory")
    private ConnectionFactory connectionFactory;

    @Produces
    @Resource(lookup = JMSQueueNames.AIRLINE_AIRCRAFT_QUEUE)
    @AircraftQueue
    private Queue aircrafQueue;

    @Produces
    @Resource(lookup = JMSQueueNames.AIRLINE_AIRPORT)
    @AirportQueue
    private Queue airportQueue;

    @Produces
    @Resource(lookup = JMSQueueNames.AIRLINE_CUSTOMER)
    @CustomerQueue
    private Queue customerQueue;

    @Produces
    @Resource(lookup = JMSQueueNames.AIRLINE_FLIGHT_QUEUE)
    @FlightQueue
    private Queue flightQueue;

    @Produces
    @Resource(lookup = JMSQueueNames.AIRLINE_RESERVATION_QUEUE)
    @ReservationQueue
    private Queue reservationQueue;
}
