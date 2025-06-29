package org.voduybao.bookstorebackend.services.user;

import org.voduybao.bookstorebackend.dao.entities.user.Education;
import org.voduybao.bookstorebackend.dtos.EducationDto;

import java.util.List;

public interface EducationService {

    void create(EducationDto.Request request);

    void update(Integer id, EducationDto.Request request);

    List<Education> list();

    void delete(Integer id);

    Education getById(Integer id);
}
