package integration.producer;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import integration.producer.qualifiers.AircraftQueue;
import integration.producer.qualifiers.AirportQueue;
import integration.producer.qualifiers.ConnectionFactoryAgency;
import integration.producer.qualifiers.ConnectionFactoryLocal;
import integration.producer.qualifiers.CustomerQueue;
import integration.producer.qualifiers.FlightQueue;
import integration.producer.qualifiers.OrchestratorAgencyQueue;
import integration.producer.qualifiers.ReservationQueue;
import msa.commons.consts.JMSQueueNames;

@ApplicationScoped
public class ResourceProducer {
    
    @Produces
    @Resource(lookup = JMSQueueNames.CONNECTION_FACTORY_LOCAL)
    @ConnectionFactoryLocal
    private ConnectionFactory connectionFactory;

    @Produces
    @Resource(lookup = JMSQueueNames.CONNECTION_FACTORY_AGENCY)
    @ConnectionFactoryAgency
    private ConnectionFactory connectionFactoryRemoteAgency;

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

    @Produces
    @Resource(lookup = JMSQueueNames.REMOTE_ORCHESTRATOR_AGENCY_QUEUE)
    @OrchestratorAgencyQueue
    private Queue orchestratorAgencyRemoteQueue;
}
