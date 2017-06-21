package com.chromediopside.service;

import com.chromediopside.datatransfer.LoginForm;
import com.chromediopside.datatransfer.TokenResponse;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class LoginService {

  private UserService userService;
  private ErrorService errorService;

  @Autowired
  public LoginService(UserService userService, ErrorService errorService) {
    this.userService = userService;
    this.errorService = errorService;
  }

  public ResponseEntity<?> login(LoginForm loginForm,
          BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return errorService.fieldErrors(bindingResult);
    }
    String appToken = userService.createAndSaveUser(loginForm);
    return new ResponseEntity<>(new TokenResponse(appToken), HttpStatus.OK);
  }
}
