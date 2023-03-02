package com.manager.views.popup;

import com.manager.service.EmployeeService;
import com.manager.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class EmployeeEdit {

	@Autowired
	private EmployeeService employeeService;

	@FXML
	private Label message;

	@FXML
	private Label title;
	@FXML
	private ComboBox<Position> position;
	@FXML
	private ComboBox<Division> division;
	@FXML
	private ComboBox<EducationDegree> educationDegree;
	@FXML
	private ComboBox<Gender> gender;
	@FXML
	private DatePicker birthday;
	@FXML
	private TextField name;
	@FXML
	private TextField address;
	@FXML
	private TextField idCard;
	@FXML
	private TextField email;
	@FXML
	private TextField phone;
//	@FXML
//	private TextField accountName;
//	@FXML
//	private PasswordField password;
	@FXML
	private TextField salary;

	private Employee employee;
	private Consumer<Employee> saveHandler;


	public static void addNew(Consumer<Employee> saveHandler,
	                          Supplier<List<Position>> positionListSupplier,
	                          Supplier<List<Division>> divisionListSupplier,
	                          Supplier<List<EducationDegree>> educationDegreeListSupplier,
	                          Supplier<List<Gender>> genderListSupplier) {
		edit(null, saveHandler, positionListSupplier, divisionListSupplier, educationDegreeListSupplier, genderListSupplier);
	}

	public static void edit(Employee employee, Consumer<Employee> saveHandler,
	                        Supplier<List<Position>> positionListSupplier,
	                        Supplier<List<Division>> divisionListSupplier,
	                        Supplier<List<EducationDegree>> educationDegreeListSupplier,
	                        Supplier<List<Gender>> genderListSupplier) {
		try {
			Stage stage = new Stage(StageStyle.UNDECORATED);
			FXMLLoader loader = new FXMLLoader(EmployeeEdit.class.getResource("EmployeeEdit.fxml"));
			stage.setScene(new Scene(loader.load()));
			stage.initModality(Modality.APPLICATION_MODAL);

			EmployeeEdit controller = loader.getController();
			controller.init(employee, saveHandler,
					positionListSupplier,
					divisionListSupplier,
					educationDegreeListSupplier,
					genderListSupplier);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void save() {
		try {
			employee.setPosition(position.getValue());
			employee.setDivision(division.getValue());
			employee.setEducationDegree(educationDegree.getValue());
			employee.setEmployeeGender(gender.getValue());
			employee.setEmployeeSalary(Double.parseDouble(salary.getText()));
			employee.setEmployeeName(name.getText());
			employee.setEmployeeAddress(address.getText());
			employee.setEmployeePhone(phone.getText());
			employee.setEmployeeEmail(email.getText());
			employee.setEmployeeBirthday(birthday.getValue());
			employee.setEmployeeIDCard(idCard.getText());

//			Random rn = new Random();
//			int rdi = rn.nextInt();

//			employee.getUser().setUsername("user"+rdi);
//			employee.getUser().setPassword(bCryptPasswordEncoder.encode("123456"));
//
//			employeeService.save(employee);

			saveHandler.accept(employee);
			close();
		} catch (PosException e) {
			message.setText(e.getMessage());
		} catch (NumberFormatException e) {
			message.setText("Please enter Salary with digit");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void close() {
		address.getScene().getWindow().hide();
	}

	private void init(Employee employee, Consumer<Employee> saveHandler, Supplier<List<Position>> positionListSupplier, Supplier<List<Division>> divisionListSupplier, Supplier<List<EducationDegree>> educationDegreeListSupplier, Supplier<List<Gender>> genderListSupplier) {

		this.saveHandler = saveHandler;
		this.employee = employee;

		position.getItems().addAll(positionListSupplier.get());
		division.getItems().addAll(divisionListSupplier.get());
		educationDegree.getItems().addAll(educationDegreeListSupplier.get());
		gender.getItems().addAll(genderListSupplier.get());

		if (employee == null) {
			title.setText("Add New Employee");
			this.employee = new Employee();
		} else {
			title.setText("Edit Employee");
		}
		position.setValue(this.employee.getPosition());
		division.setValue(this.employee.getDivision());
		educationDegree.setValue(this.employee.getEducationDegree());
		gender.setValue(this.employee.getEmployeeGender());
		name.setText(this.employee.getEmployeeName());
		birthday.setValue(this.employee.getEmployeeBirthday());
		idCard.setText(this.employee.getEmployeeIDCard());
		address.setText(this.employee.getEmployeeAddress());
		phone.setText(this.employee.getEmployeePhone());
		email.setText(this.employee.getEmployeeEmail());
		salary.setText(String.valueOf(this.employee.getEmployeeSalary()));
//		accountName.setText(this.employee.getUser().getUsername());
//		password.setText(this.employee.getUser().getPassword());

	}
}
