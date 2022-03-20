package ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import model.FotbalPlayer;
import model.Team;
import services.Service;
import services.ServicesException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Controller {

    @FXML
    private TextField Name, Age, Height, Experience;
    @FXML
    private Text FId;

    @FXML
    private TextField Tname, Country, Game;

    @FXML
    private TextField fname;

    @FXML
    private TextField fname2;

    @FXML
    private TableView<FotbalPlayer> fResults;
    @FXML
    private TableView<FotbalPlayer> fResults2;

    @FXML
    private TableView<Team> teamResults;


    private Service Services;

    public Controller() {

    }

    public void setService(Service service) {
        this.Services = service;
    }

   /* @FXML
   // public void initialize() {
        //StringConverter<LocalDate> converter = new StringConverter<>() {
           // @Override
           // public String toString(LocalDate date) {
              //  return (date != null) ? dateFormatter.format(date) : "";
          //  }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty())
                        ? LocalDate.parse(string, dateFormatter)
                        : null;
            }
        };

        Date.setConverter(converter);
        Date.setValue(LocalDate.now());
        Date.setEditable(false);
    } */

    @FXML
    public void addfHandler(ActionEvent actionEvent) {
        String name = Name.getText();
        String age = Age.getText();
        String height = Height.getText();
        String experience = Experience.getText();
        if (checkString(name) && checkString(age) && checkString(height) && checkString(experience)) {
            try {
                int aged = Integer.parseInt(Age.getText());
                int id = Services.addF(name, aged, height, experience);
                FId.setText("FotbalPlayer identification number is " + id);
                showNotification("FotbalPlayer successfully added! ", Alert.AlertType.INFORMATION);
            } catch (NumberFormatException ex) {
                showNotification("The age must be a number! ", Alert.AlertType.ERROR);
                return;
            } catch (ServicesException ex) {
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        } else
            showNotification("You have to fill in all the fields! ", Alert.AlertType.ERROR);
    }

    @FXML
    public void clearFieldsHandler(ActionEvent actionEvent) {
        Name.setText("");
        Age.setText("");
        Height.setText("");
        Experience.setText(" ");
    }

    private void showNotification(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Notification");
        alert.setContentText(message);
        alert.showAndWait();
    }

   // private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private boolean checkString(String s) {
        return s == null || s.isEmpty() ? false : true;
    }

    @FXML
    public void getAllF(ActionEvent actionEvent) {
        List<FotbalPlayer> results = Services.ar.findAllfotbalplayers();
        fResults.getItems().clear();
        fResults.getItems().addAll(results);
    }

    @FXML
    public void addTeamHandler(ActionEvent actionEvent) {
        int selectedRace = fResults2.getSelectionModel().getSelectedIndex();
        if (selectedRace < 0) {
            showNotification("You must select a fotbalplayer first! ", Alert.AlertType.ERROR);
            return;
        }
        String tn = Tname.getText();
        String c = Country.getText();
        String ga = Game.getText();

        if (checkString(tn) && checkString(c) && checkString(ga)) {
            try {FotbalPlayer f = fResults2.getItems().get(selectedRace);


                Services.addTeam(tn,c, ga,f);
                fResults2.getItems().remove(selectedRace);
                showNotification("Team successfully added! ", Alert.AlertType.INFORMATION);
            } catch (NumberFormatException ex) {
                showNotification("went wrong ", Alert.AlertType.ERROR);
                return;
            } catch (ServicesException ex) {
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    public void searchfHandler(ActionEvent actionEvent) {
        String searchName = fname.getText();
        if (!checkString(searchName)) {
            showNotification("You must introduce a name! ", Alert.AlertType.ERROR);
            return;
        }
        List<FotbalPlayer> results = Services.getFByName(searchName);
        fResults2.getItems().clear();
        fResults2.getItems().addAll(results);
    }

    @FXML
    public void searchteamHandler(ActionEvent actionEvent) {
        String searchName = fname2.getText();
        if (!checkString(searchName)) {
            showNotification("You must introduce a name! ", Alert.AlertType.ERROR);
            return;
        }
        List<Team> results = Services.getTeamByRace(searchName);
        teamResults.getItems().clear();
        teamResults.getItems().addAll(results);
    }
}