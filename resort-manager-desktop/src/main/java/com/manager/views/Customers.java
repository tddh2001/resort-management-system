package com.manager.views;

import com.manager.model.Customer;
import com.manager.model.CustomerType;
import com.manager.model.PosException;
import com.manager.service.CustomerService;
import com.manager.service.CustomerTypeService;
import com.manager.service.GenderService;
import com.manager.views.common.Dialog;
import com.manager.views.popup.CustomerEdit;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class Customers extends AbstractController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerTypeService typeService;

	@Autowired
	private GenderService genderService;

	@FXML
	private Label message;

	@FXML
	private ComboBox<CustomerType> customerType;
	@FXML
	private TextField name;

	@FXML
	private void initialize() {
		customerType.getItems().clear();
		customerType.getItems().addAll(typeService.findAll());
		MenuItem edit = new MenuItem("Edit Customer");
		edit.setOnAction(event -> {
			Customer customer = tableView.getSelectionModel().getSelectedItem();
			if (null != customer) {
				CustomerEdit.edit(customer, this::save, typeService::findAll, genderService::findAll);
			}
		});
		MenuItem delete = new MenuItem("Delete Customer");
		delete.setOnAction(event -> {
			Customer customer = tableView.getSelectionModel().getSelectedItem();
			Dialog.DialogBuilder.builder()
					.title("Delete Customer")
					.message(String.format("Do you want to delete this customer?"))
					.okActionListener(() -> {
						try {
							customerService.delete(customer);
						} catch (PosException e) {
							message.setText(e.getMessage());
						}
						search();
					})
					.build().show();
		});
		tableView.setContextMenu(new ContextMenu(edit, delete));
	}

	@FXML
	private TableView<Customer> tableView;

	@FXML
	private void search() {
		tableView.getItems().clear();
		List<Customer> list = customerService.search(customerType.getValue(), name.getText());
		tableView.getItems().addAll(list);
	}

	@FXML
	private void clear() {
		customerType.setValue(null);
		name.clear();
		tableView.getItems().clear();
	}

	@FXML
	private void addNew() {
		CustomerEdit.addNew(this::save, typeService::findAll, genderService::findAll);
//		name.getScene().getWindow().hide();
	}

	private void save(Customer customer) {
		Optional<Customer> cus = customerService.findById(customer.getCustomerId());
		if (cus.isPresent()) {
			Customer cusTb = tableView.getSelectionModel().getSelectedItem();
			if (null == cusTb) {
				throw new PosException("Customer ID Already Exists");
			}
		}

		customerService.save(customer);
		customerType.setValue(customer.getCustomerType());
		search();
	}
}
