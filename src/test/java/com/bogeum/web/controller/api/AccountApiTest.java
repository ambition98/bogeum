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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    void getAllAccounts() throws Exception {
        AccountDto dto1 = AccountDto.builder()
                .no(1)
                .email("test1@test.com")
                .build();
        AccountDto dto2 = AccountDto.builder()
                .no(2)
                .email("test2@test.com")
                .build();

        List<AccountDto> dtoList = new ArrayList<>();
        dtoList.add(dto1);
        dtoList.add(dto2);

        given(accountService.findAll()).willReturn(dtoList);

        mockMvc.perform(get("/api/account"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(2))
                .andExpect(jsonPath("$.dtoList[0].no").value(1))
                .andExpect(jsonPath("$.dtoList[1].no").value(2))
                .andDo(print());
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
    void isValidEmail() throws Exception {
        String email = "test@test.com";
        given(accountService.isExistedEmail(email)).willReturn(true);

        mockMvc.perform(get("/api/account/exists?email=new-mail@test.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.apiStatus.code").value(1))
                .andExpect(jsonPath("$.apiStatus.msg").value("Success"))
                .andDo(print());

        mockMvc.perform(get("/api/account/exists?email=" + email))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.apiStatus.code").value(-100))
                .andExpect(jsonPath("$.apiStatus.msg").value("Email is already used"))
                .andDo(print());
    }
}