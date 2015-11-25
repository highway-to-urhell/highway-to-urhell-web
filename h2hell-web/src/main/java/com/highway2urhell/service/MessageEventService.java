package com.highway2urhell.service;

import com.highway2urhell.dao.EventDao;
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
    private EventDao eventDao;

    @Transactional
    public List<MessageEvent> findEvent(MessageEvent meInc){
        List<MessageEvent> result = new ArrayList<MessageEvent>();
        List<Event> listEvent = eventDao.findEventByToken(meInc.getToken());
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
            eventDao.save(ev);
        }
        return result;
    }
}
