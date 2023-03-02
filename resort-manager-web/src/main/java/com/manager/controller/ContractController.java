package com.manager.controller;

import com.manager.model.Contract;
import com.manager.model.Customer;
import com.manager.model.Employee;
import com.manager.model.Service;
import com.manager.repository.ContractRepository;
import com.manager.repository.CustomerRepository;
import com.manager.repository.ServiceRepository;
import com.manager.service.ContractService;
import com.manager.service.CustomerService;
import com.manager.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(value = "/contract")
public class ContractController {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final EmployeeService employeeService;
    private final ContractService contractService;
    private final ServiceRepository serviceRepository;

    private final ContractRepository contractRepository;

    public ContractController(CustomerService customerService, CustomerRepository customerRepository, EmployeeService employeeService, ContractService contractService, ServiceRepository serviceRepository, ContractRepository contractRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.employeeService = employeeService;
        this.contractService = contractService;
        this.serviceRepository = serviceRepository;
        this.contractRepository = contractRepository;
    }

    @ModelAttribute("listCus")
    public List<Customer> getCustomer() {
        return customerService.findAll();
    }

    @ModelAttribute("listSer")
    public List<Service> getService() {
        return serviceRepository.findAll();
    }

    @ModelAttribute("listEmp")
    public List<Employee> getEmployee() {
        return employeeService.findAll();
    }

    @GetMapping("/list")
    private String list(Model model) {
        List<Contract> contracts = contractService.findAll();
        model.addAttribute("contracts", contracts);
        return "/contract/list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("contract", new Contract());
        return "/contract/create";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute Contract contract,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) throws Exception {
        if (bindingResult.hasFieldErrors()) {
            return "/contract/create";
        }
//		LocalDate startDate = contract.getContract_start_date();
//		LocalDate endDate = contract.getContract_end_date();
//		if (ChronoUnit.DAYS.between(startDate, endDate) < 1) {
//			bindingResult.addError(new FieldError("contract", "contractEndDate", "End date must be greater than start date"));
//			return "/contract/create";
//		}
        Optional<Contract> service = contractRepository.findByService(contract.getService().getService_id());
        if (service.isPresent()) {
			throw new Exception("This service is being used.");
        }
        contractService.create(contract);
        return "redirect:/contract/list";
    }

    @GetMapping("/view/{id}")
    public String showDetail(Model model, @PathVariable int id) {
        Contract contract = contractService.findById(id);
        model.addAttribute("contract", contract);
        return "/contract/view";
    }

    @GetMapping("/edit/{id}")
    public String editPage(Model model, @PathVariable int id) {
        Contract contract = contractService.findById(id);
        model.addAttribute("contract", contract);
        return "/contract/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute Contract contract,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes redirectAttributes) throws Exception {
        if (bindingResult.hasErrors()) {
            return "/contract/edit";
        }
        Date startDate = contract.getContract_start_date();
        Date endDate = contract.getContract_end_date();
//		if (ChronoUnit.DAYS.between(startDate, endDate) < 1) {
//			throw new Exception("End date must be greater than start date");
////			bindingResult.addError(new FieldError("contract", "contractEndDate", "End date must be greater than start date"));
//		}
        contractService.update(contract);
        return "redirect:/contract/list";
    }

    @PostMapping("delete")
    private String deleteContract(@RequestParam(name = "idContract") int id) {
        contractService.delete(id);
        return "redirect:/contract/list";
    }

}
