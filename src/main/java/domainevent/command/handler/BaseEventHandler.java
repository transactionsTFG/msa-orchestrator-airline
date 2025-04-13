package domainevent.command.handler;

import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventData;
import msa.commons.event.EventId;

public abstract class BaseEventHandler implements CommnadHandler {
    protected IEventPublisher jmsEventDispatcher;

    @Override
    public void handle(EventData data) {
        this.jmsEventDispatcher.publish(this.sendEventId(), data);
    }

    public abstract void setJmsEventDispatcher(IEventPublisher jmsEventDispatcher);
    public abstract EventId sendEventId(); 
}
