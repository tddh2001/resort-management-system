package com.manager.controller;

import com.manager.model.*;
import com.manager.repository.RoleRepository;
import com.manager.repository.UserRepository;
import com.manager.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EducationDegreeService educationDegreeService;
    private final PositionService positionService;
    private final DivisionService divisionService;
    private final GenderService genderService;
    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final BCryptPassword bCryptPassword;
    private final UserRepository userService;


    public EmployeeController(EmployeeService employeeService, EducationDegreeService educationDegreeService, PositionService positionService, DivisionService divisionService, GenderService genderService, RoleService roleService, RoleRepository roleRepository, BCryptPassword bCryptPassword, UserRepository userService) {
        this.employeeService = employeeService;
        this.educationDegreeService = educationDegreeService;
        this.positionService = positionService;
        this.divisionService = divisionService;
        this.genderService = genderService;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.bCryptPassword = bCryptPassword;
        this.userService = userService;
    }

    @ModelAttribute("gender")
    public Iterable<Gender> genders(){
        return genderService.findAll();
    }

    @ModelAttribute("positions")
    public List<Position> positionList() {
        return positionService.findAll();
    }

    @ModelAttribute("educationDegrees")
    public List<EducationDegree> educationDegreeList() {
        return educationDegreeService.findAll();
    }

    @ModelAttribute("divisions")
    public List<Division> divisionList() {
        return divisionService.findAll();
    }

    @ModelAttribute("roles")
    public List<Role> roleList() {
        return roleRepository.findAll();
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employees", employeeList);
        return "employee/list";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/create";
    }

    @PostMapping("create")
    public String create(@Valid @ModelAttribute Employee employee, BindingResult bindingResult) {
        Optional<User> user = Optional.ofNullable(userService.findByUsername(employee.getUser().getUsername()));
        if (user.isPresent()) {
            bindingResult.addError(new FieldError("employee", "user.username", "Account "+ employee.getUser().getUsername() +" is already exist!"));
            return "employee/create";
        }

        if (bindingResult.hasErrors()) {
            return "employee/create";
        }
        employee.getUser().setPassword(bCryptPassword.encodePassword(employee.getUser().getPassword()));
        Set<Role> roles = new HashSet<>();
        if (employee.getPosition().getPosition_id() == 6 || employee.getPosition().getPosition_id() == 5)
            roles.add(roleService.findById(2));
        else
            roles.add(roleService.findById(1));
        employee.getUser().setRoleList(roles);

        employeeService.save(employee);
        return "redirect:/employee/list";
    }

    @GetMapping("/edit/{id}")
    private String showEdit(@PathVariable int id, Model model){
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee",employee);
        return "/employee/edit";
    }

    @PostMapping("edit")
    public String editCustomer(@ModelAttribute("employee") Employee employee, Model model, BindingResult bindingResult) {
        Employee empl  = employeeService.findById(employee.getEmployeeId());
        Optional<User> user = Optional.ofNullable(userService.findByUsername(employee.getUser().getUsername()));
        if (user.isPresent() && !(empl.getUser().getUsername().compareTo(employee.getUser().getUsername()) == 0)) {
            bindingResult.addError(new FieldError("employee", "user.username", "Account name is already exist!"));
            return "employee/edit";
        }
        Set<Role> roles = new HashSet<>();
        if (employee.getPosition().getPosition_id() == 6 || employee.getPosition().getPosition_id() == 5)
            roles.add(roleService.findById(2));
        else
            roles.add(roleService.findById(1));
        employee.getUser().setRoleList(roles);
        employee.getUser().setPassword(bCryptPassword.encodePassword(employee.getUser().getPassword()));
        employeeService.save(employee);
        model.addAttribute("employee", employee);
        return "redirect:/employee/list";
    }

    @PostMapping("delete")
    private String deleteCustomer(@RequestParam(name = "idEmployee") int id){
        employeeService.remove(id);
        return "redirect:/employee/list";
    }

    @GetMapping("/view/{id}")
    private String viewCustomer(Model model, @PathVariable("id") int id){
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "/employee/view";
    }

}
