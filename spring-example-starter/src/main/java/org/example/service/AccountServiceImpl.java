package org.example.service;

import java.util.List;
import org.example.dto.AccountDTO;
import org.example.mapper.AccountMappers;
import org.example.model.Account;
import org.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final AccountMappers accountMappers;

  @Autowired
  public AccountServiceImpl(AccountRepository accountRepository, AccountMappers accountMappers) {
    this.accountRepository = accountRepository;
    this.accountMappers = accountMappers;
  }

  @Override
  public AccountDTO saveAccount(AccountDTO accountDTO) {
    Account account = accountMappers.toModel(accountDTO);
    accountRepository.save(account);
    return accountMappers.toDTO(account);
  }

  @Override
  public List<AccountDTO> findAllAccount() {
    List<Account> accounts = accountRepository.findAll();
    List<AccountDTO> accountDTO = accountMappers.toDTOList(accounts);
    return accountDTO;
  }


  @Override
  public AccountDTO deleteAccount(AccountDTO accountDTO) {
    return null;
  }

  @Override
  public AccountDTO findByIdAccount(Long id) {
    return null;
  }
}
