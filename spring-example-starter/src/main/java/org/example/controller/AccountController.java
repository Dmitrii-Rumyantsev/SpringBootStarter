package org.example.controller;

import java.util.List;
import org.example.model.Account;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class AccountController {

  private final AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping("/create")
  public ResponseEntity<?> createAccount(@RequestBody Account account) {
    return new ResponseEntity<>(accountService.saveAccount(account), HttpStatus.CREATED);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteAccount(@RequestBody Account account) {
    accountService.deleteAccount(account);
    return ResponseEntity.ok("Успешно удален");
  }

  @GetMapping("/all")
  public ResponseEntity<?> findAllAccounts() {
    List<Account> accounts = accountService.findAllAccount();
    return new ResponseEntity<>(accounts, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findAccountById(@PathVariable Long id) {
    Account account = accountService.findByIdAccount(id);
    return new ResponseEntity<>(account, HttpStatus.OK);
  }
}
