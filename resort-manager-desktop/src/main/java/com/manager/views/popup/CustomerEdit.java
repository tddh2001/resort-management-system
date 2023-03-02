package com.manager.views.popup;

import com.manager.model.Customer;
import com.manager.model.CustomerType;
import com.manager.model.Gender;
import com.manager.model.PosException;
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

public class CustomerEdit {

	@FXML
	private Label message;

	@FXML
	private Label title;

	@FXML
	private ComboBox<CustomerType> customerType;
	@FXML
	private ComboBox<Gender> gender;
	@FXML
	private TextField id;
	@FXML
	private TextField name;
	@FXML
	private DatePicker birthday;
	@FXML
	private TextField email;
	@FXML
	private TextField phone;
	@FXML
	private TextField idCard;
	@FXML
	private TextField address;

	private Customer customer;
	private Consumer<Customer> saveHandler;

	public static void addNew(Consumer<Customer> saveHandler, Supplier<List<CustomerType>> supplier, Supplier<List<Gender>> supplierGender) {
		edit(null, saveHandler, supplier, supplierGender);
	}

	public static void edit(Customer customer, Consumer<Customer> saveHandler, Supplier<List<CustomerType>> supplierCusType, Supplier<List<Gender>> supplierGender) {
		try {
			Stage stage = new Stage(StageStyle.UNDECORATED);
			FXMLLoader loader = new FXMLLoader(CustomerEdit.class.getResource("CustomerEdit.fxml"));
			stage.setScene(new Scene(loader.load()));
			stage.initModality(Modality.APPLICATION_MODAL);

			CustomerEdit controller = loader.getController();
			controller.init(customer, saveHandler, supplierCusType, supplierGender);

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void save() {
		try {
			customer.setCustomerId(id.getText());
			customer.setCustomerType(customerType.getValue());
			customer.setCustomerGender(gender.getValue());
			customer.setCustomerName(name.getText());
			customer.setCustomerAddress(address.getText());
			customer.setCustomerBirthday(birthday.getValue());
			customer.setCustomerEmail(email.getText());
			customer.setCustomerPhone(phone.getText());
			customer.setCustomerIDCard(idCard.getText());
			saveHandler.accept(customer);

			close();
		} catch (PosException e) {
			message.setText(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void close() {
		address.getScene().getWindow().hide();
	}

	private void init(Customer customer, Consumer<Customer> saveHandler, Supplier<List<CustomerType>> supplier, Supplier<List<Gender>> supplierGender) {

		this.customer = customer;
		this.saveHandler = saveHandler;
		customerType.getItems().addAll(supplier.get());
		gender.getItems().addAll(supplierGender.get());

		if (null == customer) {
			title.setText("Add new Customer");
			this.customer = new Customer();
		} else {
			id.setDisable(true);
			title.setText("Edit Customer");
		}

		customerType.setValue(this.customer.getCustomerType());
		id.setText(this.customer.getCustomerId());
		name.setText(this.customer.getCustomerName());
		gender.setValue(this.customer.getCustomerGender());
		birthday.setValue(this.customer.getCustomerBirthday());
		idCard.setText(this.customer.getCustomerIDCard());
		phone.setText(this.customer.getCustomerPhone());
		email.setText(this.customer.getCustomerEmail());
		address.setText(this.customer.getCustomerAddress());
	}

}
