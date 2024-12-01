package es.florida.adria;


public class Main {
	public static void main(String[] args) {
        LoginView loginView = new LoginView();
        new LoginController(loginView);
        loginView.setVisible(true);
    }
}
