package org.example.service;

import java.util.List;
import org.example.dto.AccountDTO;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
  AccountDTO saveAccount(AccountDTO accountDTO);

  List<AccountDTO> findAllAccount();

  AccountDTO deleteAccount(AccountDTO accountDTO);

  AccountDTO findByIdAccount(Long id);

}
