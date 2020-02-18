package cl.msaavedra.dofus.api;

import cl.msaavedra.dofus.model.Account;
import cl.msaavedra.dofus.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/account")
@RestController
public class AccountController {

  private final AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService){
    this.accountService = accountService;
  }

  @GetMapping
  public List<Account> getAllAccounts() {
    return accountService.getAllAccounts();
  }

  @GetMapping(path = "{id}")
  public Account getAccountById(@PathVariable("id") UUID id) {
    return accountService.getAccountById(id)
            .orElse(null);
  }

  @PostMapping
  public Account addAccount(@RequestBody Account account){
    return accountService.addAccount(account);
  }

  @DeleteMapping(path = "{id}")
  public Account deleteAccountById(@PathVariable("id") UUID id){
    return accountService.deleteAccount(id);
  }

  @PutMapping(path = "{id}")
  public Account updateAccount(@PathVariable("id") UUID id, @RequestBody Account accountToUpdate) {
    return accountService.updateAccount(id, accountToUpdate);
  }
}
