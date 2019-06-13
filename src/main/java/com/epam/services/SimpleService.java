package com.epam.services;

import com.epam.dao.EventDao;
import com.epam.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {

    @Autowired
    private EventDao eventDao;

    public void addEventToDatabase(Event event) {
        eventDao.add(event);
    }

}
