package clients;

import entities.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "compte-service")
public interface AccountRestClient {

    @GetMapping("/accounts/{id}")
    Account getAccountById(@PathVariable("id") Long id);

    @PutMapping("/accounts/{id}/debit")
    void debit(@PathVariable("id") Long id, @RequestParam("amount") double amount);

    @PutMapping("/accounts/{id}/credit")
    void credit(@PathVariable("id") Long id, @RequestParam("amount") double amount);
}
