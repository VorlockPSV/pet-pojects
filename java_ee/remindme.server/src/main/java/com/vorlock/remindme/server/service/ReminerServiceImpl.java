package com.vorlock.remindme.server.service;

import com.vorlock.remindme.server.entity.Remind;
import com.vorlock.remindme.server.repository.RemindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminerServiceImpl implements ReminderService {

    @Autowired
    private RemindRepository repository;

    @Override
    public List<Remind> getAll() {
        return repository.findAll();
    }

    @Override
    public Remind getByID(long id) {
        return repository.findOne(id);
    }

    @Override
    public Remind save(Remind remind) {
        return repository.saveAndFlush(remind);
    }

    @Override
    public void remove(long id) {
        repository.delete(id);
    }
}
