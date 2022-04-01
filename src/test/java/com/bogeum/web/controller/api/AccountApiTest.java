package com.bogeum.web.controller.api;

import com.bogeum.web.dto.account.AccountDto;
import com.bogeum.web.service.AccountService;
import com.bogeum.web.service.HashService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(AccountApi.class)
@AutoConfigureMockMvc
class AccountApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private HashService hashService;

    @Test
    @DisplayName("모든 계정 정보 가져오기")
    void getAllAccounts() {
    }

    @Test
    @DisplayName("계정 정보 1개 가져오기")
    void getAccount() throws Exception {
        long no = 10L;

        AccountDto dto = AccountDto.builder()
                .no(no)
                .email("test@test.com")
                .build();
        given(accountService.findByNo(no)).willReturn(dto);

        mockMvc.perform(get("/api/account/{no}", no))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dto.no").value(no))
                .andExpect(jsonPath("$.dto.email").value("test@test.com"))
                .andExpect(jsonPath("$.apiStatus.code").value(1))
                .andExpect(jsonPath("$.apiStatus.msg").value("Success"))
                .andDo(print());

        verify(accountService).findByNo(no);
    }

    @Test
    void getAccountException() throws Exception {
        mockMvc.perform(get("/api/account/{no}", "abc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.apiStatus.code").value(-3))
                .andExpect(jsonPath("$.apiStatus.msg").value("Not a number"))
                .andDo(print());

//        mockMvc.perform(get("/api/account/{no}", 1))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.apiStatus.code").value(-3))
//                .andExpect(jsonPath("$.apiStatus.msg").value("Not a number"))
//                .andDo(print());
    }

    @Test
    @DisplayName("계정 정보 Repository에 등록")
    void postAccount() {
    }

    @Test
    void putAccount() {
    }

    @Test
    void deleteAccount() {
    }

    @Test
    @DisplayName("이메일 중복 체크")
    void isValidEmail() {
    }
}