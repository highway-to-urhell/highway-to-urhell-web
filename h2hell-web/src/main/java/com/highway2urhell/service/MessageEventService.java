package com.highway2urhell.service;

import com.highway2urhell.repository.EventRepository;
import com.highway2urhell.domain.Event;
import com.highway2urhell.domain.MessageEvent;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class MessageEventService {

    @Inject
    private EventRepository eventRepository;

    @Transactional
    public List<MessageEvent> findEvent(MessageEvent meInc){
        List<MessageEvent> result = new ArrayList<MessageEvent>();
        List<Event> listEvent = eventRepository.findEventByToken(meInc.getToken());
        for(Event ev : listEvent){
            MessageEvent me = new MessageEvent();
            me.setId(ev.getId());
            me.setReference(ev.getReference());
            me.setToken(ev.getToken());
            me.setTypeMessageEvent(ev.getTypeMessageEvent());
            me.setData(ev.getData());
            result.add(me);
            //change the status
            ev.setTreat(true);
            eventRepository.save(ev);
        }
        return result;
    }
}
