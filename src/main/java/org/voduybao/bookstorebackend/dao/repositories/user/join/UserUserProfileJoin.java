package org.voduybao.bookstorebackend.dao.repositories.user.join;

public interface UserUserProfileJoin {
    Integer getUserId();

    String getEmail();

    String getPhoneNumber();

    String getAuthProvider();

    String getProviderId();

    Boolean getIsActive();

    String getNickname();

    String getIntro();

    String getGender();

    String getFirstName();

    String getLastName();

    String getJobId();

    String getEduId();

    String getHobbyId();

    String getJti();

    String getRoleName();
}
