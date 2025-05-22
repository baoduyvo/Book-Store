package org.voduybao.bookstorebackend.services.shared;

import org.voduybao.bookstorebackend.dao.entities.user.Job;
import org.voduybao.bookstorebackend.dtos.JobDto;

import java.util.List;

public interface OtpService {

    void create(JobDto.Request request);

    void update(Integer id, JobDto.Request request);

    List<Job> list();

    void delete(Integer id);

    Job getById(Integer id);

}
