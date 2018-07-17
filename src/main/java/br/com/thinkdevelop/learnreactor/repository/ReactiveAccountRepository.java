package br.com.thinkdevelop.learnreactor.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import br.com.thinkdevelop.learnreactor.domain.Account;
import br.com.thinkdevelop.learnreactor.domain.Currency;
import reactor.core.publisher.Flux;

public interface ReactiveAccountRepository extends ReactiveCrudRepository<Account, String> {

	Flux<Account> findByCurrency(Currency currency);
}
