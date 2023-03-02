package com.manager.views;

import com.manager.service.ContractService;
import com.manager.service.CustomerService;
import com.manager.service.EmployeeService;
import com.manager.service.ServiceService;
import com.manager.views.common.Dialog;
import com.manager.views.popup.ContractEdit;
import com.manager.model.Contract;
import com.manager.model.Customer;
import com.manager.model.Employee;
import com.manager.model.Service;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class Contracts extends AbstractController {
	@FXML
	private ComboBox<Customer> customer;
	@FXML
	private ComboBox<Employee> employee;
	@FXML
	private ComboBox<Service> service;
//	@FXML
//	private TextField name;
	@FXML
	private TableView<Contract> tableView;

	@Autowired
	private CustomerService customerService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ServiceService serviceService;
	@Autowired
	private ContractService contractService;

	@FXML
	private void initialize() {
		service.getItems().clear();
		service.getItems().addAll(serviceService.findAll());
		MenuItem edit = new MenuItem("Edit Contract");
		edit.setOnAction(event -> {
			Contract contract = tableView.getSelectionModel().getSelectedItem();
			if (contract != null) {
				ContractEdit.edit(contract, this::save,
						serviceService::findAll,
						employeeService::findAll,
						customerService::findAll);
			}
		});
		MenuItem delete = new MenuItem("Delete Contract");
		delete.setOnAction(event -> {
			Contract contract = tableView.getSelectionModel().getSelectedItem();
			Dialog.DialogBuilder.builder()
					.title("Delete Contract")
					.message(String.format("Do you want to delete this contract?"))
					.okActionListener(() -> {
						contractService.delete(contract);
						search();
					})
					.build().show();
		});
		tableView.setContextMenu(new ContextMenu(edit, delete));
	}

	@FXML
	private void search() {
		tableView.getItems().clear();
		List<Contract> list = contractService.search(service.getValue());
		tableView.getItems().addAll(list);
	}

	@FXML
	private void clear() {
		service.setValue(null);
		tableView.getItems().clear();
	}

	@FXML
	private void addNew() {
		ContractEdit.addNew(this::save,
				serviceService::findAll,
				employeeService::findAll,
				customerService::findAll);
	}

	private void save(Contract contract) {
		contractService.save(contract);
		service.setValue(contract.getService());
		search();
	}
}
