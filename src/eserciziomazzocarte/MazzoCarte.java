package eserciziomazzocarte;

// <editor-fold desc="IMPORT VARI">
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
// </editor-fold>

public class MazzoCarte extends Application {
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        // <editor-fold desc="VARIABILI">
        Mazzo mazzoDaRamino = new Mazzo(); 
        ImageView[] IMVArray = new ImageView[104];
        Stage vittoriaStage = new Stage();
        // </editor-fold>
        
        // <editor-fold desc="INIZIALIZZAZIONI VARIE">
        vittoriaStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Mazzo carte da RAMINO");
        primaryStage.setResizable(false);
        // </editor-fold>
        
        // <editor-fold desc="GRIDPANE">
        GridPane gpEsterna = new GridPane();
        GridPane gpSuperiore = new GridPane();
        GridPane gpImmagini = new GridPane();
        GridPane gpVittoria = new GridPane();
    
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(100);
        gpEsterna.getColumnConstraints().addAll(column2);
        gpEsterna.setPadding(new Insets(15, 12, 15, 12));
        
        gpSuperiore.getColumnConstraints().addAll(column1, column1);
        
        gpEsterna.setConstraints(gpSuperiore, 0, 0); 
        gpEsterna.setConstraints(gpImmagini, 0, 1); 
        gpEsterna.getChildren().addAll(gpSuperiore, gpImmagini);
    
        gpImmagini.setPadding(new Insets(15, 0, 0, 0));
        gpVittoria.setPadding(new Insets(15, 12, 15, 12));
        gpVittoria.setHgap(18);
    
        // </editor-fold>
        
        // <editor-fold desc="PARTE SUPERIORE">
        TextField txtEstrai = new TextField();
        txtEstrai.setText("10");
        txtEstrai.setMaxWidth(Double.MAX_VALUE);
        
        gpSuperiore.setConstraints(txtEstrai, 0, 0); 
        
        Button btnEstrai = new Button();
        btnEstrai.setText("Estrai");
        btnEstrai.setMaxWidth(Double.MAX_VALUE);
        
        gpSuperiore.setConstraints(btnEstrai, 1, 0); 
        
        // </editor-fold>
       
        // <editor-fold desc="EVENTO BOTTONE">
        btnEstrai.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Carta cartaBonus;
                int CarteDaStampare;
                gpImmagini.getChildren().clear();
                gpVittoria.getChildren().clear();
                try
                {
                    CarteDaStampare = Integer.parseInt(txtEstrai.getText());
                    if(CarteDaStampare > 1 && CarteDaStampare < 31)
                    {
                        int cartaVincente = -1;
                        ImageView IMVcartaVincente1;
                        ImageView IMVcartaVincente2;
                        ImageView IMVcartaBonus;
                        Label lblVittoria;
                        Label lblBONUS;
                        Label lblRiepilogo;

                        mazzoDaRamino.mescola();

                        for (int i = 0; i < CarteDaStampare; i++)
                        {
                            IMVArray[i] = new ImageView(mazzoDaRamino.get(i).immagine); 
                            IMVArray[i].setFitWidth(98.714);
                            IMVArray[i].setFitHeight(150.857);
                            if (i < 10)
                            {
                                gpImmagini.add(IMVArray[i], i, 0);
                            }
                            else if (i < 20)
                            {
                                gpImmagini.add(IMVArray[i], i-10, 1);
                            }
                            else
                            {
                                gpImmagini.add(IMVArray[i], i-20, 2);
                            }                
                        }

                        cartaVincente = mazzoDaRamino.haiVinto(CarteDaStampare);
                        if(cartaVincente != -1)
                        {
                            cartaBonus = mazzoDaRamino.cartaBonus();
                            if (cartaBonus.numero == mazzoDaRamino.get(cartaVincente).numero)
                            {
                                lblRiepilogo = new Label("PUNTEGGIO TOTALE\n COPPIA - 1 Punto \n BONUS - 3 Punti \n TOT - 4 Punti");
                            }
                            else
                            {
                                lblRiepilogo = new Label("PUNTEGGIO TOTALE\n COPPIA - 1 Punto \n BONUS - 0 Punti \n TOT - 1 Punto");
                            }
                            lblVittoria = new Label("COPPIA VINCENTE");
                            lblBONUS = new Label("CARTA BONUS");

                            lblBONUS.setMaxWidth(Double.MAX_VALUE);
                            lblBONUS.setAlignment(Pos.CENTER);
                            lblBONUS.setStyle("-fx-font-weight: bold;");

                            lblRiepilogo.setMaxWidth(Double.MAX_VALUE);
                            lblRiepilogo.setAlignment(Pos.CENTER);
                            lblRiepilogo.setStyle("-fx-font-weight: bold;");

                            lblVittoria.setMaxWidth(Double.MAX_VALUE);
                            lblVittoria.setAlignment(Pos.CENTER);
                            lblVittoria.setStyle("-fx-font-weight: bold;");


                            vittoriaStage.setTitle("COMPLIMENTI HAI VINTO!");
                            IMVcartaVincente1 = new ImageView(mazzoDaRamino.get(cartaVincente).immagine);
                            IMVcartaVincente2 = new ImageView(mazzoDaRamino.get(cartaVincente).immagine);

                            IMVcartaBonus = new ImageView(cartaBonus.immagine);
                            IMVcartaVincente1.setFitWidth(168.714);
                            IMVcartaVincente1.setFitHeight(220.857);
                            IMVcartaVincente2.setFitWidth(168.714);
                            IMVcartaVincente2.setFitHeight(220.857);
                            IMVcartaBonus.setFitWidth(168.714);
                            IMVcartaBonus.setFitHeight(220.857);

                            ColorAdjust colorAdjust = new ColorAdjust(); 

                            colorAdjust.setHue(12);     
                            colorAdjust.setBrightness(0);  
                            colorAdjust.setSaturation(0.3);   
                            IMVcartaBonus.setEffect(colorAdjust);

                            gpVittoria.add(lblVittoria, 0, 0, 2, 1);
                            gpVittoria.add(lblBONUS, 2, 0);

                            gpVittoria.add(IMVcartaVincente1, 0, 1);
                            gpVittoria.add(IMVcartaVincente2, 1, 1);
                            gpVittoria.add(IMVcartaBonus, 2, 1);

                            gpVittoria.add(lblRiepilogo, 3, 1);
                            
                            vittoriaStage.setResizable(false);
                            vittoriaStage.showAndWait();
                        }
                    }
                    else
                    {
                        txtEstrai.setText("");
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Fuori range!");
                        alert.setHeaderText("Devi inserire un numero tra 2 e 30!");
                        alert.showAndWait();
                    }
                } 
                catch (NumberFormatException nfe) {
                    txtEstrai.setText("");
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Formato errato!");
                    alert.setHeaderText("Devi inserire un numero!");
                    alert.showAndWait();
                }       
               }
        });
        // </editor-fold>
        

        gpSuperiore.getChildren().addAll(txtEstrai, btnEstrai);
      
        Scene scene = new Scene(gpEsterna, 1024, 512);
        Scene scenaVittoria = new Scene(gpVittoria, 720, 266);
        
        vittoriaStage.setScene(scenaVittoria);
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
