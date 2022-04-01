package com.bogeum.test.unit.account.repository;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bogeum.web.entity.AccountEntity;
import com.bogeum.web.repository.AccountRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class AccountRepoTest {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Test
	@DisplayName("AccountRepository에 저장이 되는지 확인")
	void save() {
		// given
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		AccountEntity entity = new AccountEntity();
		entity.setEmail("test@test.com");
		
		// when
		em.persist(entity);
		em.detach(entity);
		em.persist(entity);
		
		// then
//		assertThat(entity).isSameAs(savedEntity);
//		assertThat(savedEntity.getNo()).isNotNull();
//		assertThat(savedEntity.getNo()).isEqualTo(1L);
//		assertThat(savedEntity.getEmail()).isEqualTo(savedEntity.getEmail());
//		assertThat(savedEntity.getRegdate()).isNotNull();
//		assertThat(savedEntity.getIsVerified()).isFalse();
//		assertThat(accountRepository.count()).isEqualTo(1);
	}
	
	void findByNo() {
		// given
		AccountEntity entity1 = new AccountEntity();
		AccountEntity entity2 = new AccountEntity();
		entity1.setEmail("test1@test.com");
		entity2.setEmail("test2@test.com");
		
		entity1 = accountRepository.save(entity1);
		entity2 = accountRepository.save(entity2);
		
		// when
		AccountEntity findedEntity1 = accountRepository.findByNo(1).get();
		AccountEntity findedEntity2 = accountRepository.findByNo(2).get();
		
		// then
		assertThat(entity1).isSameAs(findedEntity1);
		assertThat(entity2).isSameAs(findedEntity2);
		assertThat(entity1.getNo()).isEqualTo(1L);
		assertThat(entity2.getNo()).isEqualTo(2L);
		assertThat(entity1.getEmail()).isEqualTo(findedEntity1.getEmail());
		assertThat(entity2.getEmail()).isEqualTo(findedEntity2.getEmail());
	}

}
