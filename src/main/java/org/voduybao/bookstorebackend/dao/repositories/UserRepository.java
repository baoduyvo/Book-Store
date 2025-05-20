package org.voduybao.bookstorebackend.dao.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.voduybao.bookstorebackend.dao.entities.User;
import org.voduybao.bookstorebackend.dao.repositories.join.UserUserProfileJoin;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer>,
        PagingAndSortingRepository<User, Integer> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email")
    boolean existsUserByEmail(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.phoneNumber = :phoneNumber")
    boolean existsUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.phoneNumber = :phone")
    Optional<User> findUserByPhone(@Param("phone") String phone);

    @Query(value = """
        SELECT 
            u.user_id         AS userId,
            u.email           AS email,
            u.phone_number    AS phoneNumber,
            u.auth_provider   AS authProvider,
            u.provider_id     AS providerId,
            u.is_status       AS status,
            
            p.nickname        AS nickname,
            p.intro           AS intro,
            p.gender          AS gender,
            p.first_name      AS firstName,
            p.last_name       AS lastName,
            p.profile_image   AS image,
            
            p.job_id          AS jobId,
            p.edu_id          AS eduId,
            p.hobby_id        AS hobbyId,
            
            t.jti             AS jti,
            
            r.role_name       AS roleName
        FROM users u
        JOIN user_profile p ON p.user_id = u.user_id
        JOIN tokens t ON t.user_id = u.user_id
            LEFT JOIN users_roles ur ON ur.user_id = u.user_id
                LEFT JOIN roles r ON r.role_id = ur.role_id
        LIMIT :limit OFFSET :offset
        """, nativeQuery = true)
    List<UserUserProfileJoin> findAllUsers(@Param("limit") int limit, @Param("offset") int offset);

    @Query(value = "select count(user_id) from users", nativeQuery = true)
    long countItems();
}