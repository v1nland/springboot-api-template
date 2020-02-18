package cl.msaavedra.dofus.service;

import cl.msaavedra.dofus.dao.AccountDao;
import cl.msaavedra.dofus.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

  private final AccountDao accountDao;

  @Autowired
  public AccountService(@Qualifier("postgres") AccountDao accountDao){
    this.accountDao = accountDao;
  }

  public Account addAccount(Account account){
    return accountDao.insertAccount(account);
  }

  public List<Account> getAllAccounts() {
    return accountDao.selectAllAccounts();
  }

  public Optional<Account> getAccountById(UUID id){
    return accountDao.selectAccountById(id);
  }

  public Account deleteAccount( UUID id ){
    return accountDao.deleteAccountById(id);
  }

  public Account updateAccount( UUID id, Account newAccount ){
    return accountDao.updateAccountById(id, newAccount);
  }
}
