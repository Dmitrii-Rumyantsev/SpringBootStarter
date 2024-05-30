package org.example.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "201",
          description = "Создание пользователя",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = Account.class)
          )
      ),
      @ApiResponse(
          responseCode = "400",
          description = "Ошибка создания",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = Account.class)
          )
      )
  })
  public ResponseEntity<?> createAccount(@RequestBody Account account) {
    return new ResponseEntity<>(accountService.saveAccount(account), HttpStatus.CREATED);
  }

  @DeleteMapping("/delete")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Удаление аккаунта",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = Account.class)
          )
      ),
      @ApiResponse(
          responseCode = "401",
          description = "Ошибка поиска",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = Account.class)
          )
      )
  })
  public ResponseEntity<?> deleteAccount(@RequestBody Account account) {
    accountService.deleteAccount(account);
    return ResponseEntity.ok("Успешно удален");
  }

  @GetMapping("/all")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Получение всех пользовотелей",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = Account.class)
          )
      )}
  )
  public ResponseEntity<?> findAllAccounts() {
    List<Account> accounts = accountService.findAllAccount();
    return new ResponseEntity<>(accounts, HttpStatus.OK);
  }
  @GetMapping("/{id}")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "302",
          description = "Получение аккаунта по ID",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = Account.class)
          )
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Аккаунт не найден",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = String.class)
          )
      )
  })
  public ResponseEntity<?> findAccountById(@PathVariable Long id) {
    Account account = accountService.findByIdAccount(id);
    return new ResponseEntity<>(account, HttpStatus.FOUND);
  }
}
