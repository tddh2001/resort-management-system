package com.manager.views;

import com.manager.LoginException;
import com.manager.PosApplication;
//import PosException;
//import com.jdc.pos.model.entity.Account;
//import com.jdc.pos.model.service.LoginService;
import com.manager.model.User;
import com.manager.service.LoginService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class Login {

	private static User loginUser;

	@Autowired
	private LoginService loginService;

	@FXML
	private Label message;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Button closeBtn;

	@FXML
	private Button loginBtn;

	private void attachEvent() {
		username.getScene().setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				if (closeBtn.isFocused()) {
					close();
				}
				if (loginBtn.isFocused()) {
					login();
				}
			}
		});
	}

	@FXML
	private void close() {
		closeBtn.getScene().getWindow().hide();
	}

	@FXML
	private void login() {
		try {
			loginUser = loginService.login(username.getText(), password.getText());
//			message.setText("Login success");
			// hien thi trang chu
			MainFrame.show();
			// dong trang login
			close();
		} catch (LoginException e) {
			message.setText(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			close();
		}

	}

	public static void loadView(Stage stage) {

		try {
			FXMLLoader loader = new FXMLLoader(Login.class.getResource("Login.fxml"));
			loader.setControllerFactory(PosApplication.getConfigurableApplicationContext()::getBean);

			Parent view = loader.load();
			stage.setScene(new Scene(view));

			Login controller = loader.getController();
			controller.attachEvent();

			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
