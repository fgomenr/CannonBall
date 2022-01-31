package es.felixgomezenriquez.cannonball;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
    //cracion y variables primera bola
    int bolaPosX=60;
    int bolaPosY=410;
    int velocidadBolaX=3;
    Circle bolaCanon1 = new Circle(bolaPosX, bolaPosY, 7, Color.DARKSLATEGREY);

    //creacion y variables segunda bola ARRIBA IZQ
    int bola2PosX=182;
    int bola2PosY=28;
    int velocidadBola2Y=3;
    Circle bolaCanon2 = new Circle(bola2PosX, bola2PosY, 7, Color.DARKSLATEGREY);
    
    //creacion y variables tercera bola ARRIBA IZQ
    int bola3PosX=432;
    int bola3PosY=28;
    int velocidadBola3Y=3;
    Circle bolaCanon3 = new Circle(bola3PosX, bola3PosY, 7, Color.DARKSLATEGREY);
    
    //Variable de control
    int escenaNum=0;    
    Random r=new Random();
    
    //Variables personaje
    int personajeX=550;
    int personajeY=380;
    int velocidadPersonajeX;
    int velocidadPersonajeY;
    
    @Override
    public void start(Stage stage) throws InterruptedException {
        
        stage.setTitle("CannonBallRun");
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
        Image cannon = new Image(getClass().getResourceAsStream("/images/turret.png"));
        
        ImageView cannonView = new ImageView(cannon);
        cannonView.setFitHeight(50);
        cannonView.setFitWidth(50);
        cannonView.setX(15);
        cannonView.setY(395);
        root.getChildren().add(cannonView);
        
        
        ImageView cannon2 = new ImageView(cannon);
        cannon2.setFitHeight(50);
        cannon2.setFitWidth(50);
        cannon2.setX(150);
        cannon2.setY(-24);
        cannon2.setRotate(90);
        root.getChildren().add(cannon2);
        
        ImageView cannon3 = new ImageView(cannon);
        cannon3.setFitHeight(50);
        cannon3.setFitWidth(50);
        cannon3.setX(400);
        cannon3.setY(-24);
        cannon3.setRotate(90);
        root.getChildren().add(cannon3);
        
       
        //Creamos personaje y variables
        
        
        
        
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
        personaje.setLayoutX(personajeX);
        personaje.setLayoutY(personajeY);
        //Añadimos a la escena
        root.getChildren().add(personaje);

        
        //Añadimos la primera bola de cañon a la escena
        bolaCanon1.setVisible(false);//False para q no empieze a salir hasta q quiera
        root.getChildren().add(bolaCanon1);

        //Añadimos la segunda bola de cañon a la escena
        bolaCanon2.setVisible(true); //cambiar a false
        root.getChildren().add(bolaCanon2);
        
        //Añadimos la segunda bola de cañon a la escena
        bolaCanon3.setVisible(true); //cambiar a false
        root.getChildren().add(bolaCanon3);
        
        tiroCanonBajo(bolaCanon1,velocidadBolaX,bolaPosX);
        
        tiroCanonAlto(bolaCanon2,velocidadBola2Y,bola2PosY);
       
        tiroCanonAlto(bolaCanon3,velocidadBola3Y,bola3PosY);
        
        
        
        //Movimiento del personaje
             Timeline movimientoPersonaje = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                    
                    
                                                        
                    if (personajeY<=315){
                        
                        velocidadPersonajeY=2;
                    
                    } else if (personajeY>380){
                    
                        velocidadPersonajeY=0;
                        personajeY=380;
                    }
                    
                    //SaltoREALIZADO qUEDA CONTRLAR LIMITES EJE X
                    
                    System.out.println(personajeY);

                    personajeY += velocidadPersonajeY;
                    personaje.setLayoutY(personajeY);

                    personajeX += velocidadPersonajeX;
                    personaje.setLayoutX(personajeX);

                    scene.setOnKeyPressed((KeyEvent event) -> {
                        switch (event.getCode()) {
                            
                            case UP:
                                velocidadPersonajeY=-2;
                                
                                break;
                            case RIGHT:
                                velocidadPersonajeX = 3;
                                personajeX += velocidadPersonajeX;
                                break;
                                
                            case LEFT:
                                velocidadPersonajeX = -3;
                                
                                break;
                        }
                    });

                    scene.setOnKeyReleased((KeyEvent event) -> {
                        switch (event.getCode()) {
                            
                            case RIGHT:
                                velocidadPersonajeX = 0;
                                personajeX += velocidadPersonajeX;
                                break;
                                
                            case LEFT:
                                velocidadPersonajeX = 0;
                                
                                break;
                        }
                    });

                })
        );
        movimientoPersonaje.setCycleCount(Timeline.INDEFINITE);
        movimientoPersonaje.play();

        
        /*
        cambioEscena();
        
       
        if(escenaNum==1){
        
            System.out.println("ESCENA 1");
            //Hacer metodo escenas?¿?¿?¿¿?¿?
        
        }
        
        if (escenaNum==2){
            
            System.out.println("ESCENA 2");
        
        
        }
   
        //para ganar o llegar al cañon
        //Al pasar 60 segundos cambia minijuego mismo personaje
        
        */
        
    }
    
    
    
    public void cambioEscena(){
        
        Timeline cambioEscena = new Timeline(
                new KeyFrame(Duration.seconds(1), (ActionEvent ae) -> {
                    
                    int randNum=r.nextInt(2);
                    
                    
                    switch (randNum){
                        case 0: 
                            escenaNum=0;
                            break;
                        
                        
                        case 1:
                            escenaNum=1;
                            break;
                    }
                    
                
                            
                    /*       
                    
                    Variable de control aleatorio entre 1 y 2 
                    en funcion a esa variable elegir un escenario o otro
                    */
                    System.out.println("Han pasado 30 s"); 
                    System.out.println(randNum);
                    System.out.println(escenaNum);
            })
        );
        cambioEscena.setCycleCount(Timeline.INDEFINITE);
        cambioEscena.play();
    }
    
    
    
    public static void main(String[] args) {
        launch();
        
    }
    
    
    
    
    
    
    
    
    
    private void tiroCanonAlto(Circle bola, double velocidad,int posicionY){
    
        Timeline tiroCanonAlto = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                    
                    bola.setVisible(true);
                    
                    if(bola2PosY>=TAM_SCENE_Y){
                        bola.setVisible(true);
                        bola2PosY=28;
                    }
                    else{
                        bola2PosY += velocidad;
                        bola.setCenterY(bola2PosY);
                    }
                })
        );
        tiroCanonAlto.setCycleCount(Timeline.INDEFINITE);
        tiroCanonAlto.play();
    }
    
    
    private void tiroCanonBajo(Circle bola, double velocidad,int posicionX ){

        Timeline tiroCanonBajo = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                    
                    bola.setVisible(true);
                    
                    if(bolaPosX>=TAM_SCENE_X){
                        bola.setVisible(true);
                        bolaPosX=posicionX;
                        
                    }
                    else{
                        bolaPosX += velocidad;
                        bola.setCenterX(bolaPosX);
                    }
                })
        );
        tiroCanonBajo.setCycleCount(Timeline.INDEFINITE);
        tiroCanonBajo.play();
    }
    
    
    
} 
