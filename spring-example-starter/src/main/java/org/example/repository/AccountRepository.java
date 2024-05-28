package org.example.repository;

import java.util.List;
import org.example.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

  @Query("SELECT a FROM Account a")
  List<Account> findAll();
}
