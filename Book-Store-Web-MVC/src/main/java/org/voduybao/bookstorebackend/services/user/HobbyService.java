package org.voduybao.bookstorebackend.services.user;

import org.voduybao.bookstorebackend.dao.entities.user.Hobby;
import org.voduybao.bookstorebackend.dtos.HobbyDto;

import java.util.List;

public interface HobbyService {
    void create(HobbyDto.Request request);

    void update(Integer id, HobbyDto.Request request);

    List<Hobby> list();

    void delete(Integer id);

    Hobby getById(Integer id);
}
