package org.voduybao.bookstorebackend.services.user.Impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.user.Job;
import org.voduybao.bookstorebackend.dao.repositories.user.JobRepository;
import org.voduybao.bookstorebackend.dtos.JobDto;
import org.voduybao.bookstorebackend.services.user.JobService;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j(topic = "JOB-SERVICE")
public class JobServiceImpl implements JobService {

    @Setter(onMethod_ = @Autowired)
    private JobRepository jobRepository;

    @Override
    public void create(JobDto.Request request) {
        log.info("Job create ...!");
        Job job = Job.builder()
                .title(request.getTitle())
                .build();
        jobRepository.save(job);
    }

    @Override
    public void update(Integer id, JobDto.Request request) {
        log.info("Job update ...!");
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResponseException(ResponseErrors.JOB_NOT_FOUND));
        job.setTitle(request.getTitle());
        jobRepository.save(job);
    }

    @Override
    public List<Job> list() {
        log.info("Job get lists ...!");
        List<Job> results = new ArrayList<>();
        for (Job job : jobRepository.findAll()) {
            results.add(job);
        }
        return results;
    }

    @Override
    public void delete(Integer id) {
        log.info("Job delete ...!");
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResponseException(ResponseErrors.JOB_NOT_FOUND));
        jobRepository.delete(job);
    }

    @Override
    public Job getById(Integer id) {
        log.info("Job get by id ...!");
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResponseException(ResponseErrors.JOB_NOT_FOUND));
        return job;
    }
}