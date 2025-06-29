package org.voduybao.bookstorebackend.factories;

import org.voduybao.bookstorebackend.dao.entities.user.Education;

import java.time.Instant;

public class EducationTestDataFactory {

    public static Education createEducation(Integer id, String title) {
        Education education = new Education();
        education.setId(id);
        education.setTitle(title);
        education.setCreatedAt(Instant.now());
        return education;
    }
}