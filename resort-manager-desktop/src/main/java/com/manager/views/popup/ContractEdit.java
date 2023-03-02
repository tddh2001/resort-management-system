package com.manager.views.popup;

import com.manager.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ContractEdit {

	@FXML
	private ComboBox<Customer> customer;
	@FXML
	private ComboBox<Employee> employee;
	@FXML
	private ComboBox<Service> service;
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker endDate;
	@FXML
	private TextField deposite;
	@FXML
	private TextField totalMoney;

	@FXML
	private Label message;

	@FXML
	private Label title;


	private Contract contract;
	private Consumer<Contract> saveHandler;

	public static void addNew(Consumer<Contract> saveHandler,
	                          Supplier<List<Service>> serviceListSupplier,
	                          Supplier<List<Employee>> employeeListSupplier,
	                          Supplier<List<Customer>> customerListSupplier) {
		edit(null, saveHandler, serviceListSupplier, employeeListSupplier, customerListSupplier);
	}

	public static void edit(Contract contract, Consumer<Contract> saveHandler,
	                        Supplier<List<Service>> serviceListSupplier,
	                        Supplier<List<Employee>> employeeListSupplier,
	                        Supplier<List<Customer>> customerListSupplier) {
		try {
			Stage stage = new Stage(StageStyle.UNDECORATED);
			FXMLLoader loader = new FXMLLoader(ContractEdit.class.getResource("ContractEdit.fxml"));
			stage.setScene(new Scene(loader.load()));
			stage.initModality(Modality.APPLICATION_MODAL);

			ContractEdit controller = loader.getController();
			controller.init(contract, saveHandler,
					serviceListSupplier,
					employeeListSupplier,
					customerListSupplier);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void save() {
		try {
			contract.setService(service.getValue());
			contract.setEmployee(employee.getValue());
			contract.setCustomer(customer.getValue());
			contract.setContract_start_date(startDate.getValue());
			contract.setContract_end_date(endDate.getValue());
			contract.setContract_deposite(Double.parseDouble(deposite.getText()));
			contract.setContract_total_money(Double.parseDouble(totalMoney.getText()));

			saveHandler.accept(contract);
			close();
		} catch (PosException e) {
			message.setText(e.getMessage());
		} catch (NumberFormatException e) {
			message.setText("Please enter with digit.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void close() {
		totalMoney.getScene().getWindow().hide();
	}

	private void init(Contract contract, Consumer<Contract> saveHandler, Supplier<List<Service>> serviceListSupplier, Supplier<List<Employee>> employeeListSupplier, Supplier<List<Customer>> customerListSupplier) {

		this.saveHandler = saveHandler;
		this.contract = contract;
		service.getItems().addAll(serviceListSupplier.get());
		employee.getItems().addAll(employeeListSupplier.get());
		customer.getItems().addAll(customerListSupplier.get());

		if (contract == null) {
			title.setText("Add new Contract");
			this.contract = new Contract();
		} else {
			service.setDisable(true);
			title.setText("Edit Contract");
			this.contract = contract;
		}
		service.setValue(this.contract.getService());
		employee.setValue(this.contract.getEmployee());
		customer.setValue(this.contract.getCustomer());
		startDate.setValue(this.contract.getContract_start_date());
		endDate.setValue(this.contract.getContract_end_date());
		deposite.setText(String.valueOf(this.contract.getContract_deposite()));
		totalMoney.setText(String.valueOf(this.contract.getContract_total_money()));
	}
}
