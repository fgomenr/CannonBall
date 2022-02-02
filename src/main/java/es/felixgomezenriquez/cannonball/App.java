package es.felixgomezenriquez.cannonball;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {

    Pane root = new Pane();
    final int TAM_SCENE_X = 640;
    final int TAM_SCENE_Y = 480;
    Scene scene = new Scene(root, TAM_SCENE_X, TAM_SCENE_Y);
    
    //cracion y variables primera bola
    int bolaPosX = 60;
    int bolaPosY = 410;
    int velocidadBolaX = 3;
    Circle bolaCanon1 = new Circle(bolaPosX, bolaPosY, 7, Color.DARKSLATEGREY);

    //creacion y variables segunda bola 
    int bola2PosX = 182;
    int bola2PosY = 28;
    int velocidadBola2Y = 3;
    Circle bolaCanon2 = new Circle(bola2PosX, bola2PosY, 7, Color.DARKSLATEGREY);

    //creacion y variables tercera bola
    int bola3PosX = 432;
    int bola3PosY = 28;
    int velocidadBola3Y = 3;
    Circle bolaCanon3 = new Circle(bola3PosX, bola3PosY, 7, Color.DARKSLATEGREY);

    //Variables personaje
    int personajeX = 550;
    int personajeY = 380;
    int velocidadPersonajeX;
    int velocidadPersonajeY;
                              
    int numVidasActual=3;
    
    
    
    
    
                            


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

        //Añadimos cañones 
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
        
        //Añadimos vidas a la pantalla
        
        Image vidas = new Image(getClass().getResourceAsStream("/images/Heart.png"));
        
        ImageView vidasView[]= new ImageView[3];
        
        for (int i = 0; i <= 2; i++) {

            vidasView[i] = new ImageView(vidas);
            root.getChildren().add(vidasView[i]);
            vidasView[i].setY(15);

        }
        //Configuramos las posiciones de las vidas
        vidasView[0].setX(550);
        vidasView[1].setX(575);
        vidasView[2].setX(600);
        
        

        
        //Creamos layout para las vidas
        HBox layoutVidas = new HBox();
        layoutVidas.setTranslateY(45);
        layoutVidas.setTranslateX(535);
        root.getChildren().add(layoutVidas);
        

        Text numVidas = new Text("Vidas: 3");
        numVidas.setFont(Font.font(24));
        numVidas.setFill(Color.CORAL);
        
        //Anadimos los textos a los layouts
        layoutVidas.getChildren().add(numVidas);
      
        
        
        
        //Creamos layout para HAS GANADO
        HBox layoutGanado = new HBox();
        layoutGanado.setTranslateY(200);
        layoutGanado.setTranslateX(150);
        layoutGanado.setVisible(false);
        root.getChildren().add(layoutGanado);
        
        Text hasGanado = new Text("HAS GANADO! :)");
        hasGanado.setFont(Font.font(50));
        hasGanado.setFill(Color.CORAL);

        //Anadimos los textos a los layouts
        layoutGanado.getChildren().add(hasGanado);
        
        
        //Creamos layout para HAS PERDIDO
        HBox layoutPerdido = new HBox();
        layoutPerdido.setTranslateY(200);
        layoutPerdido.setTranslateX(150);
        layoutPerdido.setVisible(false);
        root.getChildren().add(layoutPerdido);
        
        Text hasPerdido = new Text("HAS PERDIDO! :( ");
        hasPerdido.setFont(Font.font(50));
        hasPerdido.setFill(Color.CORAL);

        //Anadimos los textos a los layouts
        layoutPerdido.getChildren().add(hasPerdido);
        
        
        

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
        
        
        //Creamos Rectangulo para hacer colisiones con las bolas
        
        Rectangle colisionJugador = new Rectangle(10, 10, 35, 45);
        colisionJugador.setFill(Color.BLUE);
        colisionJugador.setVisible(false);
        
        
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
        personaje.getChildren().add(colisionJugador);
        //Añadimos a la escena
        root.getChildren().add(personaje);

        //Añadimos la primera bola de cañon a la escena
        bolaCanon1.setVisible(false);//False para q no empieze a salir hasta q quiera
        root.getChildren().add(bolaCanon1);

        //Añadimos la segunda bola de cañon a la escena
        bolaCanon2.setVisible(false); //cambiar a false
        root.getChildren().add(bolaCanon2);

        //Añadimos la segunda bola de cañon a la escena
        bolaCanon3.setVisible(false); //cambiar a false
        root.getChildren().add(bolaCanon3);

        
        
        
  //HACER QUE EL JUEGO EMPIEZE CUANDO PULSEMOS LA TECLA ESPACIO
  
        //Lamamos al metodo previamente creado que realiza la funcion de disparo horizontal
        tiroCanonBajo(bolaCanon1, velocidadBolaX, bolaPosX);

        //Lamamos al metodo previamente creado que realiza la funcion de disparo vertical
        tiroCanonAlto(bolaCanon2, velocidadBola2Y);
        //Lamamos al metodo previamente creado que realiza la funcion de disparo vertical
        tiroCanonAlto(bolaCanon3, velocidadBola3Y);

        //Movimiento del personaje
        Timeline movimientoPersonaje = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {


                    if (personajeY <= 315) {

                        velocidadPersonajeY = 2;

                    } else if (personajeY > 380) {

                        velocidadPersonajeY = 0;
                        personajeY = 380;
                    }

                    if (personajeX <= 0) {

                        velocidadPersonajeX = 0;
                        personajeX = 0;
                    } else if (personajeX >= 630) {
                        velocidadPersonajeX = 0;
                        personajeX = 610;

                    }
                    
                    System.out.println("Esta es la Y del personaje:"+personajeY);
                    System.out.println("Esta es la X del personaje:"+personajeX);

                    personajeY += velocidadPersonajeY;
                    personaje.setLayoutY(personajeY);

                    personajeX += velocidadPersonajeX;
                    personaje.setLayoutX(personajeX);

                    scene.setOnKeyPressed((KeyEvent event) -> {
                        switch (event.getCode()) {

                            case UP:

                                if (velocidadPersonajeY == 2) {
                                    break;
                                }
                                velocidadPersonajeY = -2;

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

        //Timeline colisiones 
        
        
        
        
        
        
        
        
        
        
        
        
        //PONER TIMELINE CAÑONALTO SIN METODOS NI NA DA PROBLEMAS CON LAS COLISIOONES 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        Timeline detectarColisiones = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {

                    Shape colisionBola1 = Shape.intersect(bolaCanon1, colisionJugador);

                    boolean colisionVaciaBola1 = colisionBola1.getBoundsInLocal().isEmpty();

                    if (colisionVaciaBola1 == false) {
                        System.out.println("HA chocado con bola 1");
                        if (this.numVidasActual==3) {
                            numVidas.setText("Vidas: 2");
                            this.numVidasActual--;
                            bolaCanon1.setCenterX(60);
                            bolaPosX=60;
                            
                        } else if (this.numVidasActual==2){
                            numVidas.setText("Vidas: 1");
                            this.numVidasActual--;
                            bolaCanon1.setCenterX(60);//Movemos bola para evitar multiples contactos
                            bolaPosX=60;
                            
                        } else if (this.numVidasActual<=1){//NUMERO DE VIDAS 0
                            numVidas.setText("Vidas: 0");
                            this.numVidasActual=0;
                            bolaCanon1.setCenterX(60);
                            bolaPosX=60;
                        }
                   
                        //Meter condiciones si choca cn la bola q pasa
                        
                    }

                    Shape colisionBola2 = Shape.intersect(bolaCanon2, colisionJugador);

                    boolean colisionVaciaBola2 = colisionBola2.getBoundsInLocal().isEmpty();

                    if (colisionVaciaBola2 == false) {
                        System.out.println("Ha chocado con bola 2");
                        if (this.numVidasActual==3) {
                            numVidas.setText("Vidas: 2");
                            this.numVidasActual--;
                            bolaCanon2.setCenterY(28);
                            bola2PosY=28;
                        } else if (this.numVidasActual==2){
                            numVidas.setText("Vidas: 1");
                            this.numVidasActual--;
                            bolaCanon2.setCenterY(28);
                            bola2PosY=28;
                        } else if (this.numVidasActual<=1){
                            numVidas.setText("Vidas: 0");
                            this.numVidasActual=0;
                            bolaCanon2.setCenterY(28);
                            bola2PosY=28;
                        }
                    }

                    Shape colisionBola3 = Shape.intersect(bolaCanon3, colisionJugador);

                    boolean colisionVaciaBola3 = colisionBola3.getBoundsInLocal().isEmpty();

                    if (colisionVaciaBola3 == false) {
                        System.out.println("Ha chocado con bola 3");
                        if (this.numVidasActual==3) {
                            numVidas.setText("Vidas: 2");
                            this.numVidasActual--;
                            bolaCanon3.setCenterY(28);
                            bola2PosY=28;
                        } else if (this.numVidasActual==2){
                            numVidas.setText("Vidas: 1");
                            this.numVidasActual--;
                            bolaCanon3.setCenterY(28);
                            bola2PosY=28;
                        } else if (this.numVidasActual<=1){
                            numVidas.setText("Vidas: 0");
                            this.numVidasActual=0;
                            bolaCanon3.setCenterY(28);
                            bola2PosY=28;
                        }                    
                        
                    }
                })
        );

        detectarColisiones.setCycleCount(Timeline.INDEFINITE);
        detectarColisiones.play();
        
        
        
        //Timeline control vidas
        
        
        
        
        
        
        
    }
    

    public static void main(String[] args) {
        launch();

    }

    private void tiroCanonAlto(Circle bola, double velocidad) {

        Timeline tiroCanonAlto = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {

                    if (bola.getCenterY() >= TAM_SCENE_Y) {
                        bola.setVisible(true);
                        bola.setCenterY(28);
                        bola2PosY = 28;

                    } else {
                        bola2PosY += velocidad;
                        bola.setCenterY(bola2PosY);
                    }
                })
        );

        tiroCanonAlto.setCycleCount(Timeline.INDEFINITE);
        tiroCanonAlto.play();
    }

    private void tiroCanonBajo(Circle bola, double velocidad, int posicionX) {

        Timeline tiroCanonBajo = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {

                    bola.setVisible(true);

                    if (bolaPosX >= TAM_SCENE_X) {
                        bola.setVisible(true);
                        bolaPosX = posicionX;

                    } else {
                        bolaPosX += velocidad;
                        bola.setCenterX(bolaPosX);
                    }
                })
        );
        tiroCanonBajo.setCycleCount(Timeline.INDEFINITE);
        tiroCanonBajo.play();
    }

}
