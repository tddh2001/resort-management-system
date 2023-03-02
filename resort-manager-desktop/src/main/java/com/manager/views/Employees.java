package com.manager.views;

import com.manager.model.Employee;
import com.manager.model.PosException;
import com.manager.model.Position;
import com.manager.service.*;
import com.manager.views.common.Dialog;
import com.manager.views.popup.EmployeeEdit;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class Employees extends AbstractController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private PositionService positionService;

	@Autowired
	private GenderService genderService;

	@Autowired
	private DivisionService divisionService;

	@Autowired
	private EducationDegreeService educationDegreeService;

	@FXML
	private ComboBox<Position> position;
	@FXML
	private TextField name;
	@FXML
	private Label message;

	@FXML
	private void initialize() {
		position.getItems().clear();
		position.getItems().addAll(positionService.findAll());
		MenuItem edit = new MenuItem("Edit Employee");
		edit.setOnAction(event -> {
			Employee employee = tableView.getSelectionModel().getSelectedItem();
			if (employee != null) {
				EmployeeEdit.edit(employee, this::save,
						positionService::findAll,
						divisionService::findAll,
						educationDegreeService::findAll,
						genderService::findAll);
			}
		});
		MenuItem delete = new MenuItem("Delete Employee");
		delete.setOnAction(event -> {
			Employee employee = tableView.getSelectionModel().getSelectedItem();
			Dialog.DialogBuilder.builder()
					.title("Delete employee")
					.message(String.format("Do you want to delete this employee?"))
					.okActionListener(() -> {
						try {
							employeeService.delete(employee);
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
	private TableView<Employee> tableView;

	@FXML
	private void search() {
		tableView.getItems().clear();
		List<Employee> list = employeeService.search(position.getValue(), name.getText());
		tableView.getItems().addAll(list);
	}

	@FXML
	private void clear() {
		position.setValue(null);
		name.clear();
		tableView.getItems().clear();
	}

	@FXML
	private void addNew() {
		EmployeeEdit.addNew(this::save,
				positionService::findAll,
				divisionService::findAll,
				educationDegreeService::findAll,
				genderService::findAll);
	}

	private void save(Employee employee) {
		employeeService.save(employee);
		position.setValue(employee.getPosition());
//		name.setText(employee.getEmployeeName());
		search();
	}
}
