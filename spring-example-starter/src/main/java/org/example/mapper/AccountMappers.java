package org.example.mapper;

import java.util.List;
import org.example.dto.AccountDTO;
import org.example.model.Account;
import org.mapstruct.Mapper;

@Mapper
public interface AccountMappers {
 AccountDTO toDTO(Account account);

 Account toModel(AccountDTO accountDTO);

 List<AccountDTO> toDTOList(List<Account> accounts);
}
