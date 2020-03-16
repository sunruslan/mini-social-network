package com.cis.minisocialnetwork.Controllers;

import com.cis.minisocialnetwork.RestResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class SecurityController {
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse currentUserName(Principal principal) {
        return RestResponse.createSuccessResponse(principal.getName());
    }
}