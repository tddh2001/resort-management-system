package com.manager.config;

import com.manager.model.Customer;
import com.manager.model.Log;
import com.manager.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class Logger {
    @Autowired
    LogService logService;

    @Around("execution(public * com.manager.service.CustomerService.create(..))")
    public Customer logCreate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = (Customer) proceedingJoinPoint.proceed();
        String nameUpdate = customer.getCustomerName();
        String iDUpdate = customer.getCustomerId();
        Date date = new Date();
        String name = proceedingJoinPoint.getSignature().getName();
        Log log = new Log(name, date, nameUpdate, iDUpdate, ((UserDetails)obj).getUsername());
        logService.save(log);
        return customer;
    }

    @Around("execution(public * com.manager.service.CustomerService.edit(..))")
    public Customer logEdit(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = (Customer) proceedingJoinPoint.proceed();
        String nameUpdate = customer.getCustomerName();
        String idUpdate = customer.getCustomerId();
        Date date = new Date();
        String name = proceedingJoinPoint.getSignature().getName();
        Log log = new Log(name, date, nameUpdate, idUpdate, ((UserDetails)obj).getUsername());
        logService.save(log);
        return customer;
    }

    @Around("execution(public * com.manager.service.CustomerService.remove(..))")
    public Customer logRemove(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = (Customer) proceedingJoinPoint.proceed();
        String nameUpdate = customer.getCustomerName();
        String idUpdate = customer.getCustomerId();

        Date date = new Date();
        String name = proceedingJoinPoint.getSignature().getName();
        Log log = new Log(name, date, nameUpdate, idUpdate, ((UserDetails)obj).getUsername());
        logService.save(log);
        return customer;
    }

//    @Around("execution(public * com.manager.service.ContractService.create(..))")
//    public Contract logCreateContract(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Contract contract = (Contract) proceedingJoinPoint.proceed();
//        String nameUpdate = contract.getCustomer().getCustomerName();
//        String iDUpdate = contract.getCustomer().getCustomerId();
//        Date date = new Date();
//        String name = proceedingJoinPoint.getSignature().getName();
//        Log log = new Log(name, date, nameUpdate, iDUpdate, ((UserDetails)obj).getUsername());
//        logService.save(log);
//        return contract;
//    }
}
