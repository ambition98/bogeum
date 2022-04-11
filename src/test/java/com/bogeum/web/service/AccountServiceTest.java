package com.bogeum.web.service;

import com.bogeum.exception.ResourceNotFoundException;
import com.bogeum.web.dto.account.AccountDto;
import com.bogeum.web.entity.AccountEntity;
import com.bogeum.web.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private AccountRepository accountRepository;

    @Test
    @DisplayName("중복 이메일을 구분")
    void isExistedEmail() {
        //given
        String email = "test@test.com";
        given(accountRepository.existsByEmail(email)).willReturn(true);

        //when
        boolean case1 = accountService.isExistedEmail(email);
        boolean case2 = accountService.isExistedEmail("test2@test.com");

        //then
        assertThat(case1).isTrue();
        assertThat(case2).isFalse();
    }

    @Test
    @DisplayName("해당 no의 DTO를 리턴")
    void findByNo() throws ResourceNotFoundException {
        //given
        AccountEntity entity = new AccountEntity();
        entity.setNo(1);
        entity.setEmail("test@test.com");
        given(accountRepository.findByNo(1)).willReturn(Optional.of(entity));

        AccountDto dto = AccountDto.builder()
                .no(1)
                .email("test@test.com")
                .build();
        given(modelMapper.map(entity, AccountDto.class)).willReturn(dto);

        //when
        AccountDto resultDto1 = accountService.findByNo(1);

        //then
        assertThat(resultDto1.getNo()).isEqualTo(1);
        assertThat(resultDto1.getEmail()).isEqualTo("test@test.com");
    }

    @Test
    @DisplayName("해당 no가 존재하지 않을 때 예외 Throw")
    void findByIdException() {
        try {
            AccountDto resultDto2 = accountService.findByNo(2);
            fail("Not throw 'ResourceNotFoundException'");
        } catch (ResourceNotFoundException e) {
            //pass
        }
    }

    @Test
    @DisplayName("모든 계정 DTO 리스트를 리턴")
    void findAll() throws ResourceNotFoundException {
        //given
        AccountEntity entity1 = new AccountEntity();
        entity1.setNo(1);
        entity1.setEmail("test1@test.com");

        AccountEntity entity2 = new AccountEntity();
        entity2.setNo(2);
        entity2.setEmail("test2@test.com");

        List<AccountEntity> entityList = new ArrayList<>();
        entityList.add(entity1);
        entityList.add(entity2);
        given(accountRepository.findAll()).willReturn(entityList);

        AccountDto dto1 = AccountDto.builder()
                .no(1)
                .email("test1@test.com")
                .build();
        AccountDto dto2 = AccountDto.builder()
                .no(2)
                .email("test2@test.com")
                .build();

        List<AccountDto> dtoList = new ArrayList<>();
        given(modelMapper.map(entity1, AccountDto.class)).willReturn(dto1);
        given(modelMapper.map(entity2, AccountDto.class)).willReturn(dto2);

        //when
        List<AccountDto> resultList = accountService.findAll();

        //then
        assertThat(resultList.size()).isEqualTo(2);
        assertThat(resultList.get(0).getNo()).isEqualTo(1);
        assertThat(resultList.get(1).getNo()).isEqualTo(2);
    }

    @Test
    @DisplayName("Account가 한 개도 존재하지 않을 시 예외 Throw")
    void findAllException() {
        //given
        given(accountRepository.findAll()).willReturn(new ArrayList<>());

        //when
        //then
        try {
            List<AccountDto> dtoList = accountService.findAll();
            fail("Not throw 'ResourceNotFoundException'");
        } catch (ResourceNotFoundException e) {
            //pass
        }
    }
}
