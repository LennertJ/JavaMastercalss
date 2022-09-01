package com.crm.miniCRM.service;

import com.crm.miniCRM.service.interfaces.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IEventService {
    @Autowired
    private EventService eventService;



}
