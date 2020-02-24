package com.Web_CSGO.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
public interface ILoginService {
     Map<String,Object> login(String username, String password, int userType, String verification_code, HttpServletRequest request);
}
