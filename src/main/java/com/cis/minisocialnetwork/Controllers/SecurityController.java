package com.cis.minisocialnetwork.Controllers;

import com.cis.minisocialnetwork.Exception.ResourceNotFoundException;
import com.cis.minisocialnetwork.RestResponse;
import com.cis.minisocialnetwork.Security.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class SecurityController {
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse currentUserName(Principal principal) {
        return RestResponse.createSuccessResponse(principal.getName());
    }

    @GetMapping(value = "/check/{token}")
    @ApiOperation("check token")
    public RestResponse checkToken(@Valid @PathVariable("token") String token){
        try {
            return RestResponse.createSuccessResponse(jwtTokenProvider.validateToken(token));
        }
        catch(ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }
}
