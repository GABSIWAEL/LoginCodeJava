/*
 * Copyright 2023 MSI.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany.gestionmagasin;

import java.io.IOException;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ComboBox;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class HomePageController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private AnchorPane login_form;
    @FXML
    private TextField login_username;
    @FXML
    private PasswordField login_password;
    @FXML
    private TextField login_ShowPassword;
    @FXML
    private CheckBox login_SelectShowPassword;
    @FXML
    private Button login_btn;
    @FXML
    private Button login_createAcoount;
    @FXML
    private Hyperlink Login_ForgetPassword;
    @FXML
    private AnchorPane Signup_form;
    @FXML
    private TextField Signup_Email;
    @FXML
    private PasswordField SignUp_cPassword;
    @FXML
    private Button Signup_btn;
    @FXML
    private Button Signup_LoginAccount;
    @FXML
    private TextField Signup_Username;
    @FXML
    private PasswordField Signup_Password;
    @FXML
    private ComboBox<String> Signup_SelectQuestion;
    @FXML
    private TextField Signup_Answer;
    @FXML
    private AnchorPane Forget_form;
    @FXML
    private Button Forget_proceedbtn;
    @FXML
    private Button Forget_back;
    @FXML
    private TextField Forget_Username;
    @FXML
    private ComboBox<String> Forget_selectquestion;
    @FXML
    private TextField Forget_answer;
    @FXML
    private AnchorPane ChangePassword_form;
    @FXML
    private Button ChangePassword_btn;
    @FXML
    private Button ChangePassword_Back;
    @FXML
    private PasswordField ChangePassword_Password;
    @FXML
    private PasswordField ChangePassword_CPassword;
    /**
     * Initializes the controller class.
     */
    private Connection cnx = DatabaseHandler.getInstance().getCnx();

    @FXML
    public void login() throws IOException {
        alertMessage alert = new alertMessage();
        if (login_username.getText().isEmpty() || login_password.getText().isEmpty()) {
            alert.errorMessage("Incorrect username password!!");
        } else {
            String selectData = "SELECT * FROM users WHERE " + "username = ? and password = ?";
            if (login_SelectShowPassword.isSelected()){
                login_password.setText(login_ShowPassword.getText());
                
            }else{login_ShowPassword.setText(login_password.getText());
                
            }
            try (PreparedStatement selectDatastmp = cnx.prepareStatement(selectData)) {
                selectDatastmp.setString(1, login_username.getText());
                selectDatastmp.setString(2, login_password.getText());

                ResultSet result = selectDatastmp.executeQuery();
                if (result.next()) {
                    alert.succesMessage("successful login");
                    
                   String userType = result.getString("type");

        if ("admin".equals(userType)) {
            // Redirect to the admin scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/gestionmagasin/welcome.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            login_btn.getScene().getWindow().hide();
        } else {
            // Redirect to the worker scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/gestionmagasin/worker.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            login_btn.getScene().getWindow().hide();
        }

                } else {
                    alert.errorMessage("incorrecrt username/password");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void register() throws SQLException {
        alertMessage alert = new alertMessage();
        // check
        if (Signup_Email.getText().isEmpty() || Signup_Username.getText().isEmpty() || Signup_Password.getText().isEmpty() || SignUp_cPassword.getText().isEmpty() || Signup_Answer.getText().isEmpty() || Signup_SelectQuestion.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("all field must be filled!!");

        } else if (Signup_Password.getText() == SignUp_cPassword.getText()) {
            alert.errorMessage("password does not match");
        } else if (Signup_Password.getText().length() < 8) {
            alert.errorMessage("Invalid Password , at least 8 charachters needed");

        } else {
            String checkUsername = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement checkUsernameStmt = cnx.prepareStatement(checkUsername)) {
                checkUsernameStmt.setString(1, Signup_Username.getText());
                ResultSet result = checkUsernameStmt.executeQuery();
                if (result.next()) {
                    alert.errorMessage(Signup_Username.getText() + " is already taken");
                } else {
                    String insertData = "INSERT INTO users " + "(email, username, password, question, answer, date)" + " VALUES (?,?,?,?,?,?)";
                    try (PreparedStatement prepare = cnx.prepareStatement(insertData)) {

                        prepare.setString(1, Signup_Email.getText());
                        prepare.setString(2, Signup_Username.getText());
                        prepare.setString(3, Signup_Password.getText());
                        prepare.setString(4, Signup_SelectQuestion.getSelectionModel().getSelectedItem());
                        prepare.setString(5, Signup_Answer.getText());

                        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
                        prepare.setDate(6, sqlDate);

                        prepare.executeUpdate();
                        alert.succesMessage("Registred Successfully");
                        registerClearFields();
                        login_form.setVisible(true);
                        Signup_form.setVisible(false);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void registerClearFields() {
        Signup_Email.setText("");
        Signup_Username.setText("");
        Signup_Password.setText("");
        SignUp_cPassword.setText("");
        Signup_SelectQuestion.getSelectionModel().clearSelection();
        Signup_Answer.setText("");
    }

    @FXML
    public void switchForm(ActionEvent event) {
        if (event.getSource() == Signup_LoginAccount || event.getSource() == Forget_back) {
            login_form.setVisible(true);
            Signup_form.setVisible(false);
            Forget_form.setVisible(false);
            ChangePassword_form.setVisible(false);
        } else if (event.getSource() == login_createAcoount) {
            login_form.setVisible(false);
            Signup_form.setVisible(true);
            Forget_form.setVisible(false);
            ChangePassword_form.setVisible(false);
        } else if (event.getSource() == Login_ForgetPassword) {
            login_form.setVisible(false);
            Signup_form.setVisible(false);
            Forget_form.setVisible(true);
            ChangePassword_form.setVisible(false);

        } else if (event.getSource() == ChangePassword_Back) {
            login_form.setVisible(false);
            Signup_form.setVisible(false);
            Forget_form.setVisible(true);
            ChangePassword_form.setVisible(false);

        }

    }

    private String[] questionList = {
        "what is your favourite food?",
        "what is your favourite color?",
        "what is the name of your pet?",
        "what is your favourite sport?"
    };

    public void questions() {
        List<String> listQ = new ArrayList<>();
        for (String data : questionList) {
            listQ.add(data);
        }
        ObservableList<String> listData = FXCollections.observableArrayList(listQ);
        Signup_SelectQuestion.setItems(listData);
    }

    public void forgotListQuestion() {
        List<String> listQ = new ArrayList<>();
        for (String data : questionList) {
            listQ.add(data);
        }
        ObservableList<String> listData = FXCollections.observableArrayList(listQ);
        Forget_selectquestion.setItems(listData);
    }

    @FXML
    public void showPassword() {
        if (login_SelectShowPassword.isSelected()) {
            login_ShowPassword.setText(login_password.getText());
            login_ShowPassword.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_password.setText(login_ShowPassword.getText());
            login_ShowPassword.setVisible(false);
            login_password.setVisible(true);
        }
    }

    @FXML
    public void forgotPassword() {
        alertMessage alert = new alertMessage();
        if (Forget_Username.getText().isEmpty() || Forget_answer.getText().isEmpty() || Forget_selectquestion.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("please fill all blank fields ");

        } else {
            String checkData = "SELECT username, question, answer FROM users " + "WHERE username = ? AND question = ? AND answer = ?";
            try (
                    PreparedStatement checkDatastmp = cnx.prepareStatement(checkData)) {
                checkDatastmp.setString(1, Forget_Username.getText());
                checkDatastmp.setString(2, (String) Forget_selectquestion.getSelectionModel().getSelectedItem());
                checkDatastmp.setString(3, Forget_answer.getText());

                ResultSet result = checkDatastmp.executeQuery();
                if (result.next()) {
                    login_form.setVisible(false);
                    Signup_form.setVisible(false);
                    Forget_form.setVisible(false);
                    ChangePassword_form.setVisible(true);

                } else {
                    alert.errorMessage("incorrecrt informations ");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    public void changePassword() {
        alertMessage alert = new alertMessage();
        if (ChangePassword_Password.getText().isEmpty() || ChangePassword_CPassword.getText().isEmpty()) {
            alert.errorMessage("please fill all blank fields ");

        } else if (!ChangePassword_Password.getText().equals(ChangePassword_CPassword.getText())) {
            alert.errorMessage("password does not match ");

        } else if (ChangePassword_Password.getText().length() < 8) {
            alert.errorMessage("Invalid Password, at least 8 characters needed ");

        } else {
            String updateData = "UPDATE users SET password = ?,update_date = ? " + "WHERE username = '" + Forget_Username.getText() + "'";
            try (PreparedStatement prepare = cnx.prepareStatement(updateData)) {

                prepare.setString(1, ChangePassword_Password.getText());
                java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
                prepare.setDate(2, sqlDate);

                prepare.executeUpdate();
                alert.succesMessage("Succesfully changed password");
                login_form.setVisible(true);
                Signup_form.setVisible(false);
                Forget_form.setVisible(false);
                ChangePassword_form.setVisible(false);
                
                login_username.setText("");
                login_password.setVisible(true);
                login_password.setText("");
                login_ShowPassword.setVisible(false);
                
                login_SelectShowPassword.setSelected(false);
                        
                ChangePassword_Password.setText("");
                ChangePassword_CPassword.setText("");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        questions();
        forgotListQuestion();
    }

}
