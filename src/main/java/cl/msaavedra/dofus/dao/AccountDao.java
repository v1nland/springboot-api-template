package cl.msaavedra.dofus.dao;

import cl.msaavedra.dofus.model.Account;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface AccountDao {

  Account insertAccount(UUID id, Account account);

  default Account insertAccount(Account account){
    UUID id = UUID.randomUUID();
    return insertAccount(id, account);
  }

  List<Account> selectAllAccounts();

  Optional<Account> selectAccountById(UUID id);

  Account deleteAccountById(UUID id);

  Account updateAccountById(UUID id, Account account);
}
