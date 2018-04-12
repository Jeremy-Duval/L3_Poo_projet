
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import mvc.controller.Controller;
import mvc.model.Grille;
import mvc.model.enumeration.Audios;
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
    private int numLevel;
    Controller m;

    /**
     * Fonction principale de la vue.
     *
     * @param primaryStage Stage : l'application
     * @throws FileNotFoundException Exception soulevée si une image n'a pas
     * étée trouvée
     */
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        String symbolPath;
        Image imgSymbol;

        // initialisation du modèle que l'on souhaite utiliser
        m = new Controller();

        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();
        BorderPane borderTop = new BorderPane();

        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();

        Grille grid = new Grille(LARGEUR_GRID, LONGUEUR_GRID);
        numLevel = m.nextLevel(grid);

        ImageView[][] imgView = new ImageView[LARGEUR_GRID][LARGEUR_GRID];

        Text affichage = new Text("Niveau " + numLevel);
        affichage.setFont(Font.font("Verdana", 30));
        affichage.setFill(Color.BLACK);
        borderTop.setLeft(affichage);

        Button resetButton = new Button("  Effacer  ");
        resetButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                m.reinitMap();
            }
        });
        borderTop.setRight(resetButton);
        borderTop.setPadding(new Insets(10, 10, 0, 0));

        border.setTop(borderTop);

        // la vue observe les "update" du modèle, et réalise les mises à jour graphiques
        m.addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                Liens cellLink;
                Symboles cellSymb;
                String linkPath;
                boolean inStopDD = false;

                if (arg instanceof Boolean) {
                    inStopDD = (boolean) arg;
                }

                //MAJ des liens
                for (int column = 0; column < LARGEUR_GRID; column++) {
                    for (int row = 0; row < LONGUEUR_GRID; row++) {
                        if (grid.getCase(row, column).getSymbole() == Symboles.VIDE) {
                            cellLink = grid.getCase(row, column).getLien();

                            linkPath = Liens.VIDE.getImgPath();
                            try {
                                switch (cellLink) {
                                    case ANGLE_INF_DROIT:
                                        linkPath = Liens.ANGLE_INF_DROIT.getImgPath();
                                        break;
                                    case ANGLE_INF_GAUCHE:
                                        linkPath = Liens.ANGLE_INF_GAUCHE.getImgPath();
                                        break;
                                    case ANGLE_SUP_DROIT:
                                        linkPath = Liens.ANGLE_SUP_DROIT.getImgPath();
                                        break;
                                    case ANGLE_SUP_GAUCHE:
                                        linkPath = Liens.ANGLE_SUP_GAUCHE.getImgPath();
                                        break;
                                    case HORIZONTAL:
                                        linkPath = Liens.HORIZONTAL.getImgPath();
                                        break;
                                    case VERTICAL:
                                        linkPath = Liens.VERTICAL.getImgPath();
                                        break;
                                    default:
                                        break;

                                }
                                Image imgLink = new Image(new FileInputStream(linkPath));
                                imgView[column][row].setImage(imgLink);
                                ((ImageView) gPane.getChildren().get(column * LONGUEUR_GRID + row)).setImage(imgLink);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }//end if
                    }
                }//end FOR

                if ((inStopDD) && (m.victory(grid, LARGEUR_GRID, LONGUEUR_GRID))) {
                    playAudio(Audios.VICTORY, false);
                    affichage.setText(affichage.getText() + " Victoire !");

                    numLevel = m.nextLevel(grid);
                    affichage.setText("Niveau " + numLevel);
                    //actualisation des images
                    for (int column = 0; column < LARGEUR_GRID; column++) {
                        for (int row = 0; row < LONGUEUR_GRID; row++) {
                            if (grid.getCase(row, column).getSymbole() != Symboles.VIDE) {
                                cellSymb = grid.getCase(row, column).getSymbole();
                                linkPath = Symboles.VIDE.getImgPath();
                                try {
                                    switch (cellSymb) {
                                        case CAT:
                                            linkPath = Symboles.CAT.getImgPath();
                                            break;
                                        case COW:
                                            linkPath = Symboles.COW.getImgPath();
                                            break;
                                        case LEAF:
                                            linkPath = Symboles.LEAF.getImgPath();
                                            break;
                                        case PINE:
                                            linkPath = Symboles.PINE.getImgPath();
                                            break;
                                        default:
                                            break;

                                    }
                                    Image imgLink = new Image(new FileInputStream(linkPath));
                                    imgView[column][row].setImage(imgLink);
                                    ((ImageView) gPane.getChildren().get(column * LONGUEUR_GRID + row)).setImage(imgLink);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }//end if
                        }
                    }//end FOR
                    m.actualize();
                }//end if traitement victoire
            }
        });

        for (int column = 0; column < LARGEUR_GRID; column++) {
            for (int row = 0; row < LONGUEUR_GRID; row++) {

                final int fColumn = column;
                final int fRow = row;

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
                        if (grid.getCase(fRow, fColumn).getSymbole() != Symboles.VIDE) {
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
        border.setPadding(new Insets(0, 0, 0, 10));
        Scene scene = new Scene(border, Color.CORAL);

        primaryStage.setTitle(TITLE);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    private static void playAudio(Audios audio, boolean loop) {
        Media pick = new Media(new File(audio.getSoundPath()).toURI().toString());
        MediaPlayer player = new MediaPlayer(pick);

        if (loop) {
            player.setCycleCount(MediaPlayer.INDEFINITE);
        }

        player.play();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //ajout de la musique
        Media pick = new Media(new File(Audios.MUSIC.getSoundPath()).toURI().toString());
        MediaPlayer player = new MediaPlayer(pick);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
        //lancement de l'appli
        launch(args);
    }

}
