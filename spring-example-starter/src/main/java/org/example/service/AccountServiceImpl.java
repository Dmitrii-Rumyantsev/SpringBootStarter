package org.example.service;

import java.util.List;
import org.example.model.Account;
import org.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  @Autowired
  public AccountServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public Account saveAccount(Account account) {
    accountRepository.save(account);
    return account;
  }

  @Override
  public List<Account> findAllAccount() {
    return accountRepository.findAll();
  }


  @Override
  public void deleteAccount(Account account) {
    accountRepository.delete(account);
  }

  @Override
  public Account findByIdAccount(Long id) {
    return accountRepository.findById(id).get();
  }
}
