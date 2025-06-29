package org.voduybao.bookstorebackend.services.shared;

import org.voduybao.bookstorebackend.dtos.OtpDto;

public interface OtpService {

    void sendOtp(OtpDto.Request request);

    void verifyOtp(OtpDto.Request request);

}
