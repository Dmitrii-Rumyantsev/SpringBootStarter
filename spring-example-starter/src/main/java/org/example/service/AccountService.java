package org.example.service;

import java.util.List;
import org.example.model.Account;

public interface AccountService {

  Account saveAccount(Account accountDTO);

  List<Account> findAllAccount();

  void deleteAccount(Account accountDTO);

  Account findByIdAccount(Long id);

}
