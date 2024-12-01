package es.florida.adria;

import javax.swing.*;

public class AdminController {
	 private AdminView view;

    public AdminController(AdminView view) {
        this.view = view;

        this.view.getNewUserButton().addActionListener(e -> openCreateUserView());
        this.view.getQueryButton().addActionListener(e -> openQueryExecutorView());
        this.view.getImportCsvButton().addActionListener(e -> handleImportCsv());
    }

    private void openCreateUserView() {
        RegisterUserView registerUserView = new RegisterUserView();
        new RegisterUserController(registerUserView);
        registerUserView.setVisible(true);
    }

    private void openQueryExecutorView() {
        QueryView queryView = new QueryView();
        new QueryController(queryView);
        queryView.setVisible(true);
    }

    private void handleImportCsv() {
    	ImportCsvController newCsvController = new ImportCsvController(view);
    	newCsvController.handleImportCsv();
    }
}