package com.vorlock.remindme.server.repository;

import com.vorlock.remindme.server.entity.Remind;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemindRepository extends JpaRepository<Remind, Long> {

}
