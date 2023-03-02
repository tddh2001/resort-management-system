package com.manager.controller;

import com.manager.model.Customer;
import com.manager.model.CustomerType;
import com.manager.model.Gender;
import com.manager.service.CustomerService;
import com.manager.service.CustomerTypeService;
import com.manager.service.DuplicateIDException;
import com.manager.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerTypeService customerTypeService;
    @Autowired
    private GenderService genderService;

    @ModelAttribute("gender")
    public Iterable<Gender> genders() {
        return genderService.findAll();
    }

    @ModelAttribute("customerType")
    public Iterable<CustomerType> customerTypes() {
        return customerTypeService.findAll();
    }

//    @GetMapping("/")
//    private String index() {
//        return "index";
//    }

    @GetMapping("/404")
    private String error403(){
        return "/customer/inputs-not-acceptable";
    }

    @GetMapping("/list")
    private String list(@RequestParam("s") Optional<String> s, Model model, @PageableDefault(value = 5) Pageable pageable) {
        Page<Customer> customers;
        if (s.isPresent()){
            customers = customerService.findAllByCustomerNameContaining(s.get(), pageable);
        }else {
            customers = customerService.findAll(pageable);
        }
        model.addAttribute("customers", customers);
        return "/customer/list";
    }

    @GetMapping("/create")
    private String showCreate(Model model) {
        model.addAttribute("customer", new Customer());
        return "/customer/create";
    }

    @PostMapping("/create")
    private String create(@Validated @ModelAttribute("customer") Customer customer,
                          BindingResult bindingResult,
                          Model model,
                          RedirectAttributes redirectAttributes) throws DuplicateIDException {
            if (bindingResult.hasErrors()) {
                return "/customer/create";
            } else {
                if (!customerService.existId(customer.getCustomerId())) {
                    customerService.create(customer);
                    model.addAttribute("customer", new Customer());
                    redirectAttributes.addFlashAttribute("createSuccess", "Create customer successfully");
                    return "redirect:/customer/list";
                } else {
                    bindingResult.addError(new FieldError("customer", "customerId", "Customer's Id already exists!"));
                    return "customer/create";
                }
            }
    }

    @GetMapping("/edit/{id}")
    private String showEdit(@PathVariable String id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "/customer/edit";
    }

    @PostMapping("edit")
    private String edit(@Validated @ModelAttribute("customer") Customer customer,
                        BindingResult bindingResult,
                        Model model,
                        RedirectAttributes redirectAttributes) throws DuplicateIDException {
        if (bindingResult.hasErrors()){
            return "/customer/edit";
        }else {
            customerService.edit(customer);
            model.addAttribute("customer", customer);
            redirectAttributes.addFlashAttribute("editSuccess", "Edit customer successfully");
            return "redirect:/customer/list";
        }
    }

    @PostMapping("delete")
    private String deleteCustomer(@RequestParam(name = "idCustomer") String id) {
        customerService.remove(id);
        return "redirect:/customer/list";
    }

    @GetMapping("/view/{id}")
    private String viewCustomer(Model model, @PathVariable("id") String id) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "/customer/view";
    }
}
