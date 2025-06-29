package org.voduybao.bookstorebackend.tools;

import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ResultAssert {

    public static void resultSuccess(ResultActions resultActions) throws Exception {
        resultActions
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Success !!!"));
    }

    public static void assertEmptyList(ResultActions resultActions) throws Exception {
        resultActions
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }
}