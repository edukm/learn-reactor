package br.com.thinkdevelop.learnreactor.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.thinkdevelop.learnreactor.domain.Account;
import br.com.thinkdevelop.learnreactor.domain.Currency;
import br.com.thinkdevelop.learnreactor.repository.ReactiveAccountRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private final ReactiveAccountRepository reactiveAccountRepository;

	public AccountController(ReactiveAccountRepository reactiveAccountRepository) {
		this.reactiveAccountRepository = reactiveAccountRepository;
	}

	@GetMapping(value = "/search/bycurrency")
	public Flux<Account> findByCurrency(@RequestParam String currency) {
		return reactiveAccountRepository.findByCurrency(Currency.fromValue(currency));
	}

	@GetMapping(value = "/{id}")
	public Mono<Account> findById(@PathVariable String id) {
		return reactiveAccountRepository.findById(id);
	}

	@PostMapping(value = "/")
	public Mono<Account> save(@RequestBody Account account) {
		return reactiveAccountRepository.save(account);
	}

	@GetMapping(value = "/")
	public Flux<Account> findAll() {
		return reactiveAccountRepository.findAll();
	}
}