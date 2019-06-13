package com.epam.dao;

import com.epam.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EventDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(Event event) {
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS events (id varchar(15) PRIMARY KEY, name varchar (100), message varchar(255));");
        jdbcTemplate.update("insert into events values (?, ?, ?)", event.getId(), "VANYA", event.getMsg());
    }
}
