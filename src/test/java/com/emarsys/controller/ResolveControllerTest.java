package com.emarsys.controller;

import com.emarsys.model.TimeRequest;
import com.emarsys.service.TimeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ResolveControllerTest {

    @Mock
    private TimeService timeService;

    @InjectMocks
    private ResolveController underTest;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    public void testResolveController_whenStatusIsOk_thenReturn() throws Exception {
        //GIVEN
        TimeRequest timeRequest = new TimeRequest();
        String timeRequestJson = new ObjectMapper().writeValueAsString(timeRequest);
        long result = 1615815915L;
        //WHEN
        when(timeService.calculateDueDate(any())).thenReturn(result);
        //THEN
        mockMvc.perform(post("/resolve")
                .content(timeRequestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(result), Long.class));
    }
}
