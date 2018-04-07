
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.Event;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import mvc.controller.Controller;
import mvc.model.Grille;
import mvc.model.enumeration.Liens;
import mvc.model.enumeration.Symboles;

/**
 *
 * @author freder
 */
public class View extends Application {

    private static final int LARGEUR_GRID = 5;
    private static final int LONGUEUR_GRID = 5;
    private static final int SIZE_CELL = 100;
    private static final String TITLE = "Chat-pin et feuille de vache";

    Controller m;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        String symbolPath;
        Image imgSymbol;

        // initialisation du modèle que l'on souhaite utiliser
        m = new Controller();

        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();

        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();

        Grille grid = new Grille(LARGEUR_GRID, LONGUEUR_GRID);
        //Text[][] tabText = new Text[LARGEUR_GRID][LONGUEUR_GRID];
       ImageView[][] imgView = new ImageView[LARGEUR_GRID][LARGEUR_GRID];

        Text affichage = new Text(TITLE);
        affichage.setFont(Font.font("Verdana", 30));
        affichage.setFill(Color.LIGHTGRAY);
        border.setTop(affichage);

        // la vue observe les "update" du modèle, et réalise les mises à jour graphiques
        m.addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                Liens cellLink;
                String linkPath;
                Image imgLink;
                
                System.out.println("MAJ");
                //MAJ des liens
                for (int column = 0; column < LARGEUR_GRID; column++) {
                    for (int row = 0; row < LONGUEUR_GRID; row++) {
                        final ImageView imV;
                        
                        cellLink = grid.getCase(row, column).getLien();
                        //System.out.println(grid.getCase(row, column) + " - " + cellLink);
                        linkPath = Liens.VIDE.getImgPath();
                        try{
                            switch(cellLink){
                                case ANGLE_INF_DROIT :
                                    linkPath = Liens.ANGLE_INF_DROIT.getImgPath();
                                    break;
                                case ANGLE_INF_GAUCHE :
                                    linkPath = Liens.ANGLE_INF_GAUCHE.getImgPath();
                                    break;
                                case ANGLE_SUP_DROIT :
                                    linkPath = Liens.ANGLE_SUP_DROIT.getImgPath();
                                    break;
                                case ANGLE_SUP_GAUCHE :
                                    linkPath = Liens.ANGLE_SUP_GAUCHE.getImgPath();
                                    break;
                                case HORIZONTAL :
                                    linkPath = Liens.HORIZONTAL.getImgPath();
                                    break;
                                case VERTICAL :
                                    linkPath = Liens.VERTICAL.getImgPath();
                                    break;
                                default:
                                    break;

                            }
                            //System.out.println(linkPath);
                            imgLink = new Image(new FileInputStream(linkPath));
                            imV = new ImageView(imgLink);
                            imV.setFitHeight(SIZE_CELL);
                            imV.setFitWidth(SIZE_CELL);
                            imgView[column][row] = imV;
                            
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }//end FOR
            }
        });

        for (int column = 0; column < LARGEUR_GRID; column++) {
            for (int row = 0; row < LONGUEUR_GRID; row++) {

                final int fColumn = column;
                final int fRow = row;

                //final Text t = new Text(" " + column + "-" + row + " ");
                /*final Text t = new Text(grid.getCase(row, column).toString());
                tabText[column][row] = t;
                t.setFont(Font.font("Verdana", 25));*/
                //creation de la grille d'images
                final ImageView imV;
                symbolPath = grid.getCase(row, column).getSymbole().getImgPath();
                
                //creation du symbole 
                imgSymbol = new Image(new FileInputStream(symbolPath));
                imV = new ImageView(imgSymbol);
               
                imV.setFitHeight(SIZE_CELL);
                imV.setFitWidth(SIZE_CELL);
                imgView[column][row] = imV;
                
                imV.setOnDragDetected(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        if(grid.getCase(fRow, fColumn).getSymbole()!=Symboles.VIDE){
                            Dragboard db = imV.startDragAndDrop(TransferMode.MOVE);
                            ClipboardContent content = new ClipboardContent();
                            content.putString(""); // non utilisé actuellement
                            db.setContent(content);
                            event.consume();
                            m.startDD(fColumn, fRow, grid.getCase(fRow, fColumn));
                        }
                    }
                });

                imV.setOnDragEntered(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        m.parcoursDD(fColumn, fRow, grid.getCase(fRow, fColumn));
                        event.consume();
                    }
                });

                imV.setOnDragDone(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {

                        // attention, le setOnDragDone est déclenché par la source du Drag&Drop
                        m.stopDD(fColumn, fRow);

                    }
                });
                gPane.add(imgView[column][row], column, row);
            }
        }
        
        gPane.setGridLinesVisible(true);
        
        border.setCenter(gPane);

        Scene scene = new Scene(border, Color.CORAL);

        primaryStage.setTitle(TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
