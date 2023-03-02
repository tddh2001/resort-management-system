package com.manager.views;

import com.manager.PosApplication;
import com.manager.utils.Menu;
import com.manager.views.common.Dialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFrame {

	@FXML
	private VBox sideBar;
	@FXML
	private StackPane contentView;
	@FXML
	private void initialize() {
		loadView(Menu.Home);
	}

	@FXML
	private void clickMenu(MouseEvent event) {
		Node node = (Node) event.getSource();
//			System.out.println(node.getId());
		if (node.getId().compareTo("Exit") == 0) {
			Dialog.DialogBuilder.builder()
					.title("Confirm")
					.message("Do you want to exit?")
					.okActionListener(() -> sideBar.getScene().getWindow().hide())
					.build().show();
//			sideBar.getScene().getWindow().hide();
		} else {
			Menu menu = Menu.valueOf(node.getId());
			loadView(menu);
		}
	}

	private void loadView(Menu menu) {
		try {
			for (Node node : sideBar.getChildren()) {
				node.getStyleClass().remove("active");
				if (node.getId().equals(menu.name())) {
					node.getStyleClass().add("active");
				}
			}

			contentView.getChildren().clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(menu.getFxml()));
			loader.setControllerFactory(PosApplication.getConfigurableApplicationContext()::getBean);
			Parent view = loader.load();

//			AbstractController controller = loader.getController();
//			controller.setTitle(menu);

			contentView.getChildren().add(view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void show() {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(MainFrame.class.getResource("MainFrame.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
