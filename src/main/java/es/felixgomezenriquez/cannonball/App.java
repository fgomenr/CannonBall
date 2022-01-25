package es.felixgomezenriquez.cannonball;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {
    
    Pane root = new Pane();
    final int TAM_SCENE_X = 640;
    final int TAM_SCENE_Y = 480;    
    Scene scene = new Scene(root, TAM_SCENE_X, TAM_SCENE_Y);
    int bolaPosX=108;
    int bolaPosY=390;
    
    int velocidadBolaX=3;
    
    Circle bolaCanon = new Circle(bolaPosX, bolaPosY, 10, Color.DARKSLATEGREY);
    
    
    
    
    @Override
    public void start(Stage stage) {
        
        stage.setTitle("CannonBall");
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        
        stage.show();

        //Añadimos imagenes a la pantalla
        //Añadimos fondo
        Image backGround = new Image(getClass().getResourceAsStream("/images/full-background.png"));
        
        ImageView bgView = new ImageView(backGround);        
        
        bgView.setPreserveRatio(true);
        bgView.setFitHeight(500);
        bgView.setFitWidth(1000);
        
        root.getChildren().add(bgView);

        //Añadimos cañon
        Image cannon = new Image(getClass().getResourceAsStream("/images/cannon.png"));
        
        ImageView cannonView = new ImageView(cannon);
        cannonView.setX(15);
        cannonView.setY(380);
        
        
        root.getChildren().add(cannonView);
        
        
        crearPersonaje();
        
        bolaCanon.setVisible(true);//CAMBIAR ESTO A FALSE PARA EL TIRO 
        root.getChildren().add(bolaCanon);
        
        tiroCanon();
         
        //TIROS DESDE ARRIBA los metes en funcion tiroCanon, contar 60 segundos
        //para ganar o llegar al cañon
        //Al pasar 60 segundos cambia minijuego mismo personaje
        //Bajar el tiro ya hecho PAra poder saltarloo
        //MAS OBSTACULOS
        //Cambiar imagen cañon
    
    }
    
    
    public void tiroCanon(){

        Timeline tiroCanon = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                    if(bolaPosX>=TAM_SCENE_X){
                        bolaCanon.setVisible(true);
                        bolaPosX=108;
                    }
                    else{
                        bolaPosX += velocidadBolaX;
                        bolaCanon.setCenterX(bolaPosX);
                    }
                })
        );
        tiroCanon.setCycleCount(Timeline.INDEFINITE);
        tiroCanon.play();
    }
    
    public void crearPersonaje(){
    
        
        //Creamos personaje de pruebas 
        Rectangle cuerpo = new Rectangle(10, 10, 35, 40);
        cuerpo.setFill(Color.GRAY);
        Rectangle pierna1 = new Rectangle(15, 50, 10, 10);
        pierna1.setFill(Color.DARKGRAY);
        Rectangle pierna2 = new Rectangle(30, 50, 10, 10);
        pierna2.setFill(Color.DARKGRAY);
        Rectangle brazo1 = new Rectangle(46, 25, 5, 20);
        brazo1.setFill(Color.DARKGRAY);
        Rectangle brazo1_1 = new Rectangle(44, 25, 5, 5);
        brazo1_1.setFill(Color.DARKGRAY);
        Rectangle brazo2 = new Rectangle(4, 25, 5, 20);
        brazo2.setFill(Color.DARKGRAY);
        Rectangle brazo2_1 = new Rectangle(4, 25, 7, 5);
        brazo2_1.setFill(Color.DARKGRAY);
        Circle ojo1 = new Circle(20, 21, 2.5, Color.WHITE);
        Circle ojo2 = new Circle(35, 21, 2.5, Color.WHITE);

        //Creamos grupo de figuras geometricas
        Group personaje = new Group();
        
        personaje.getChildren().add(cuerpo);
        personaje.getChildren().add(pierna1);
        personaje.getChildren().add(pierna2);
        personaje.getChildren().add(brazo1);
        personaje.getChildren().add(brazo1_1);
        personaje.getChildren().add(brazo2);
        personaje.getChildren().add(brazo2_1);
        personaje.getChildren().add(ojo1);
        personaje.getChildren().add(ojo2);
        personaje.setLayoutX(550);
        personaje.setLayoutY(380);
        //Añadimos a la escena
        
        root.getChildren().add(personaje);
        
    
    
    }
    
    public static void main(String[] args) {
        launch();
        
    }
    
}
