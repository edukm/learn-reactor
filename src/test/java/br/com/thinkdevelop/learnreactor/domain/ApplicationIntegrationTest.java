package br.com.thinkdevelop.learnreactor.domain;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.thinkdevelop.learnreactor.api.AccountController;
import br.com.thinkdevelop.learnreactor.repository.ReactiveAccountRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApplicationIntegrationTest {

    WebTestClient webTestClient;


    List<Account> expectedAccounts;

    @Autowired
    ReactiveAccountRepository reactiveAccountRepository;

    @Before
    public void setup() {
        webTestClient = WebTestClient.bindToController(new AccountController(reactiveAccountRepository)).build();
        expectedAccounts = reactiveAccountRepository.findAll().collectList().block();
    }

    @Test
    public void findAllAccountsTest() {
        this.webTestClient.get().uri("/accounts/")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Account.class).isEqualTo(expectedAccounts);
    }

    @Test
    public void streamAllAccountsTest() throws Exception {
        this.webTestClient.get()
                .uri("/accounts/")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Account.class);
    }

    @Test
    public void streamAllAccountsByCurrencyTest() throws Exception {
        this.webTestClient.get()
                .uri("/accounts/?currency=EUR")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Account.class);
    }
}