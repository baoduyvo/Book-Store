package org.voduybao.bookstorebackend.services.user;

import org.voduybao.bookstorebackend.dtos.UserDto;
import org.voduybao.bookstorebackend.tools.response.panigation.PaginationResult;

public interface UserService {

    void changePassword(int userID, UserDto.ChangePasswordRequest request);

    void forgotPassword(UserDto.ForgotPasswordRequest request);

    void confirmPassword(int userID, UserDto.ConfirmForgotPasswordRequest request);

    void deleteAccount(int userID);

    PaginationResult<UserDto.UsersResponse> findAllUser(int page, int pageSize);

}
