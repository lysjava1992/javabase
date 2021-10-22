package com.learn.chapter14.controller;

import com.learn.chapter14.quota.HostRequestsQuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class TestApiController {

    @Autowired(required = false)
    private HostRequestsQuotaService quotaService;
    @PostMapping(value = "/test")
    public DeferredResult<ResponseEntity> postTest(HttpServletRequest request) {
        DeferredResult<ResponseEntity> responseWriter = new DeferredResult<ResponseEntity>();
        if (quotaExceeded(request, responseWriter)) {
            System.out.println("----------BAD--------");
            return responseWriter;
        }
        System.out.println("----------OK--------");
        responseWriter.setResult(new ResponseEntity<>(HttpStatus.ACCEPTED));
        return responseWriter;
    }
    private boolean quotaExceeded(HttpServletRequest request, DeferredResult<ResponseEntity> responseWriter) {

        if (quotaService.isQuotaExceeded(request.getRemoteAddr())) {
            responseWriter.setResult(new ResponseEntity<>(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED));
            return true;
        }
        return false;
    }

}
