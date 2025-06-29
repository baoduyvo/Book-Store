package org.voduybao.bookstorebackend.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.voduybao.bookstorebackend.configuration.EducationServiceConfig;
import org.voduybao.bookstorebackend.dtos.EducationDto;
import org.voduybao.bookstorebackend.factories.EducationTestDataFactory;
import org.voduybao.bookstorebackend.services.user.EducationService;
import org.voduybao.bookstorebackend.tools.ResultAssert;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(properties = "server.servlet.context-path=/bookstore/api")
@Import(EducationServiceConfig.class)
public class EducationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EducationService educationService;

    @Test
    void should_Return_List_Educations() throws Exception {
        var edu1 = EducationTestDataFactory.createEducation(1, "ABC University");
        var edu2 = EducationTestDataFactory.createEducation(2, "XYZ College");

        when(educationService.list()).thenReturn(List.of(edu1, edu2));

        var result = mockMvc.perform(get("/v1/educations"))
                .andExpect(status().isOk());

        ResultAssert.resultSuccess(result);

        result.andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void should_Return_Create_Education() throws Exception {
        var request = new EducationDto.Request("University");

        doNothing().when(educationService).create(any(EducationDto.Request.class));

        var result = mockMvc.perform(
                        post("/v1/educations")
                                .contentType("application/json")
                                .content(new ObjectMapper().writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk());
        ResultAssert.resultSuccess(result);

        result.andExpect(jsonPath("$.data").doesNotExist());
    }
}
