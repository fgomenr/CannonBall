package es.felixgomezenriquez.cannonball;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
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

    //La mayoria de variables declaradas aqui son para su posterior uso dentro del timeline
    Pane root = new Pane();
    final int TAM_SCENE_X = 640;
    final int TAM_SCENE_Y = 480;
    Scene scene = new Scene(root, TAM_SCENE_X, TAM_SCENE_Y);

    //cracion y variables primera bola
    int bolaPosX = 60;
    int bolaPosY = 410;
    int velocidadBolaX = 4;
    Circle bolaCanon1 = new Circle(bolaPosX, bolaPosY, 7, Color.DARKSLATEGREY);

    //creacion y variables segunda bola 
    int bola2PosX = 182;
    int bola2PosY = 28;
    int velocidadBola2Y = 6;
    Circle bolaCanon2 = new Circle(bola2PosX, bola2PosY, 7, Color.DARKSLATEGREY);

    //creacion y variables tercera bola
    int bola3PosX = 432;
    int bola3PosY = 28;
    int velocidadBola3Y = 6;
    Circle bolaCanon3 = new Circle(bola3PosX, bola3PosY, 7, Color.DARKSLATEGREY);

    //Variables personaje
    int personajeX = 550;
    int personajeY = 380;
    int velocidadPersonajeX;
    int velocidadPersonajeY;

    //Variables numero de vidas 
    int numVidasActual = 3;

    // texto cantidad vidas
    Text numVidas = new Text("Vidas: 3");

    //Añadimos vidas a la pantalla 
    Image vidas = new Image(getClass().getResourceAsStream("/images/Heart.png"));

    ImageView vidasView[] = new ImageView[3];

    //Variable control Juego 
    boolean controlVictoria;

    //Creamos layout de vitoria
    HBox layoutGanado = new HBox();

    //Creamos layout de derrota
    HBox layoutPerdido = new HBox();

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

        //Añadimos la flecha que indica el camino a seguir
        Image flecha = new Image(getClass().getResourceAsStream("/images/rodyk.png"));

        ImageView flechaView = new ImageView(flecha);
        flechaView.setFitHeight(50);
        flechaView.setFitWidth(50);
        flechaView.setX(15);
        flechaView.setY(230);
        flechaView.setRotate(180);
        root.getChildren().add(flechaView);

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

        //Añadimos las vidas 
        for (int i = 0; i <= 2; i++) {

            vidasView[i] = new ImageView(vidas);
            root.getChildren().add(vidasView[i]);
            vidasView[i].setY(15);

        }
        //Configuramos las posiciones de las vidas
        vidasView[0].setX(550);
        vidasView[1].setX(575);
        vidasView[2].setX(600);

        //Creamos layout para el reinicio
        HBox layoutReset = new HBox();
        layoutReset.setTranslateY(50);
        layoutReset.setTranslateX(195);
        layoutReset.setVisible(true);
        root.getChildren().add(layoutReset);

        Text reset = new Text("Presione 'R' para reinicar la partida ");
        reset.setFont(Font.font(15));
        reset.setFill(Color.rgb(208, 136, 0));

        //Anadimos los textos al layout
        layoutReset.getChildren().add(reset);

        //Creamos layout para las vidas
        HBox layoutVidas = new HBox();
        layoutVidas.setTranslateY(45);
        layoutVidas.setTranslateX(535);
        root.getChildren().add(layoutVidas);

        numVidas.setFont(Font.font(24));
        numVidas.setFill(Color.rgb(208, 136, 0));

        //Anadimos los textos al layout
        layoutVidas.getChildren().add(numVidas);

        //Creamos layout para HAS GANADO
        layoutGanado.setTranslateY(170);
        layoutGanado.setTranslateX(150);
        layoutGanado.setVisible(false);
        root.getChildren().add(layoutGanado);

        Text hasGanado = new Text("HAS GANADO! :)");
        hasGanado.setFont(Font.font(50));
        hasGanado.setFill(Color.rgb(114, 227, 83));

        //Anadimos los textos al layout
        layoutGanado.getChildren().add(hasGanado);

        //Creamos layout para HAS PERDIDO
        layoutPerdido.setTranslateY(170);
        layoutPerdido.setTranslateX(150);
        layoutPerdido.setVisible(false);
        root.getChildren().add(layoutPerdido);

        Text hasPerdido = new Text("HAS PERDIDO! :( ");
        hasPerdido.setFont(Font.font(50));
        hasPerdido.setFill(Color.DARKCYAN);

        //Anadimos los textos al layout
        layoutPerdido.getChildren().add(hasPerdido);

        //Creamos personaje
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

        //Creamos Rectangulo para hacer colision al final de la partida
        Rectangle colisionFinJuego = new Rectangle(-15, 380, 50, 45);
        colisionFinJuego.setFill(Color.BLUE);
        colisionFinJuego.setVisible(false);
        root.getChildren().add(colisionFinJuego);

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
        bolaCanon1.setVisible(true);
        root.getChildren().add(bolaCanon1);

        //Añadimos la segunda bola de cañon a la escena
        bolaCanon2.setVisible(true);
        root.getChildren().add(bolaCanon2);

        //Añadimos la segunda bola de cañon a la escena
        bolaCanon3.setVisible(true);
        root.getChildren().add(bolaCanon3);

        //Timeline encargado del tiro de los cañones 
        Timeline tiroCanon = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {

                    if (bolaPosX >= TAM_SCENE_X) {
                        bolaPosX = 60;

                    } else {
                        bolaPosX += velocidadBolaX;
                        bolaCanon1.setCenterX(bolaPosX);
                    }

                    if (bolaCanon2.getCenterY() >= TAM_SCENE_Y) {
                        bolaCanon2.setCenterY(28);
                        bola2PosY = 28;

                    } else {
                        bola2PosY += velocidadBola2Y;
                        bolaCanon2.setCenterY(bola2PosY);
                    }

                    if (bolaCanon3.getCenterY() >= TAM_SCENE_Y) {
                        bolaCanon3.setCenterY(28);
                        bola3PosY = 28;

                    } else {
                        bola3PosY += velocidadBola3Y;
                        bolaCanon3.setCenterY(bola3PosY);
                    }

                })
        );

        tiroCanon.setCycleCount(Timeline.INDEFINITE);
        tiroCanon.play();

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

                    System.out.println("Esta es la Y del personaje:" + personajeY);
                    System.out.println("Esta es la X del personaje:" + personajeX);

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
                            case R:
                                try {
                                this.reset();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }

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

        //Timeline colisiones y control de las vidas
        Timeline detectarColisionesControlVidas = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {

                    Shape colisionBola1 = Shape.intersect(bolaCanon1, colisionJugador);

                    boolean colisionVaciaBola1 = colisionBola1.getBoundsInLocal().isEmpty();

                    if (colisionVaciaBola1 == false) {
                        System.out.println("Ha chocado con bola 1");
                        if (this.numVidasActual == 3) {
                            numVidas.setText("Vidas: 2");
                            this.numVidasActual--;
                            bolaCanon1.setCenterX(60);
                            bolaPosX = 60;
                            //Quitamos una imagen de vida 
                            vidasView[0].setVisible(false);
                            //Aumentamos velocidad para mayor dificultad
                            velocidadBolaX = 5;
                            velocidadBola2Y = 7;
                            velocidadBola3Y = 7;

                        } else if (this.numVidasActual == 2) {
                            numVidas.setText("Vidas: 1");
                            this.numVidasActual--;
                            bolaCanon1.setCenterX(60);//Movemos bola para evitar multiples contactos
                            bolaPosX = 60;
                            //Quitamos una imagen de vida 
                            vidasView[1].setVisible(false);
                            //Aumentamos velocidad para mayor dificultad
                            velocidadBolaX = 6;
                            velocidadBola2Y = 8;
                            velocidadBola3Y = 8;

                        } else if (this.numVidasActual <= 1) {//NUMERO DE VIDAS 0
                            numVidas.setText("Vidas: 0");
                            this.numVidasActual = 0;
                            bolaCanon1.setCenterX(60);
                            bolaPosX = 60;
                            //Quitamos una imagen de vida 
                            vidasView[2].setVisible(false);
                            //Aumentamos velocidad para mayor dificultad
                            velocidadBolaX = 7;
                            velocidadBola2Y = 9;
                            velocidadBola3Y = 9;
                        }

                        //Meter condiciones si choca cn la bola q pasa SONIDOOOO etc
                    }

                    Shape colisionBola2 = Shape.intersect(bolaCanon2, colisionJugador);

                    boolean colisionVaciaBola2 = colisionBola2.getBoundsInLocal().isEmpty();

                    if (colisionVaciaBola2 == false) {
                        System.out.println("Ha chocado con bola 2");
                        if (this.numVidasActual == 3) {
                            numVidas.setText("Vidas: 2");
                            this.numVidasActual--;
                            bolaCanon2.setCenterY(28);
                            bola2PosY = 28;
                            //Quitamos una imagen de vida 
                            vidasView[0].setVisible(false);
                            //Aumentamos velocidad para mayor dificultad
                            velocidadBolaX = 5;
                            velocidadBola2Y = 7;
                            velocidadBola3Y = 7;
                        } else if (this.numVidasActual == 2) {
                            numVidas.setText("Vidas: 1");
                            this.numVidasActual--;
                            bolaCanon2.setCenterY(28);
                            bola2PosY = 28;
                            //Quitamos una imagen de vida 
                            vidasView[1].setVisible(false);
                            //Aumentamos velocidad para mayor dificultad
                            velocidadBolaX = 6;
                            velocidadBola2Y = 8;
                            velocidadBola3Y = 8;

                        } else if (this.numVidasActual <= 1) {
                            numVidas.setText("Vidas: 0");
                            this.numVidasActual = 0;
                            bolaCanon2.setCenterY(28);
                            bola2PosY = 28;
                            //Quitamos una imagen de vida 
                            vidasView[2].setVisible(false);
                            //Aumentamos velocidad para mayor dificultad
                            velocidadBolaX = 7;
                            velocidadBola2Y = 9;
                            velocidadBola3Y = 9;

                        }
                        //Meter condiciones si choca cn la bola q pasa sonido etc

                    }

                    Shape colisionBola3 = Shape.intersect(bolaCanon3, colisionJugador);

                    boolean colisionVaciaBola3 = colisionBola3.getBoundsInLocal().isEmpty();

                    if (colisionVaciaBola3 == false) {
                        System.out.println("Ha chocado con bola 3");
                        if (this.numVidasActual == 3) {
                            numVidas.setText("Vidas: 2");
                            this.numVidasActual--;
                            bolaCanon3.setCenterY(28);
                            bola3PosY = 28;
                            //Quitamos una imagen de vida 
                            vidasView[0].setVisible(false);
                            //Aumentamos velocidad para mayor dificultad
                            velocidadBolaX = 5;
                            velocidadBola3Y = 7;
                            velocidadBola2Y = 7;

                        } else if (this.numVidasActual == 2) {
                            numVidas.setText("Vidas: 1");
                            this.numVidasActual--;
                            bolaCanon3.setCenterY(28);
                            bola3PosY = 28;
                            //Quitamos una imagen de vida 
                            vidasView[1].setVisible(false);
                            //Aumentamos velocidad para mayor dificultad
                            velocidadBolaX = 6;
                            velocidadBola3Y = 8;
                            velocidadBola2Y = 8;
                        } else if (this.numVidasActual <= 1) {
                            numVidas.setText("Vidas: 0");
                            this.numVidasActual = 0;
                            bolaCanon3.setCenterY(28);
                            bola3PosY = 28;
                            //Quitamos una imagen de vida 
                            vidasView[2].setVisible(false);
                            //Aumentamos velocidad para mayor dificultad
                            velocidadBolaX = 7;
                            velocidadBola3Y = 9;
                            velocidadBola2Y = 9;
                        }
                        //Meter condiciones si choca cn la bola q pasa sonido etc
                    }
                })
        );
        detectarColisionesControlVidas.setCycleCount(Timeline.INDEFINITE);
        detectarColisionesControlVidas.play();

        //Timeline Control finalPartida
        Timeline controlPartida = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {

                    //detectar colision con la zona para ganar 
                    Shape colisionFinPartida = Shape.intersect(colisionJugador, colisionFinJuego);

                    boolean colisionVaciaFinPartida = colisionFinPartida.getBoundsInLocal().isEmpty();

                    if (numVidasActual == 0) {
                        bolaCanon1.setVisible(false);
                        bolaCanon2.setVisible(false);
                        bolaCanon3.setVisible(false);
                        velocidadBolaX = 0;
                        velocidadBola2Y = 0;
                        velocidadBola3Y = 0;
                        velocidadPersonajeX = 0;
                        velocidadPersonajeY = 0;
                        personajeX = 550;
                        personajeY = 380;
                        System.out.println("Has perdido");
                        layoutPerdido.setVisible(true);
                        this.controlVictoria = false;

                    } else if (colisionVaciaFinPartida == false) {

                        bolaCanon1.setVisible(false);
                        bolaCanon2.setVisible(false);
                        bolaCanon3.setVisible(false);
                        velocidadBolaX = 0;
                        velocidadBola2Y = 0;
                        velocidadBola3Y = 0;
                        velocidadPersonajeX = 0;
                        velocidadPersonajeY = 0;
                        personajeX = 0;
                        personajeY = 380;
                        System.out.println("HAS GANADO");
                        layoutGanado.setVisible(true);
                        this.controlVictoria = true;

                    }
                })
        );

        controlPartida.setCycleCount(Timeline.INDEFINITE);
        controlPartida.play();

    }

    //Metodo para reiniciar el Juego
    public void reset() throws InterruptedException {

        for (int i = 0; i <= 2; i++) {

            vidasView[i].setVisible(true);
            System.out.println(i);

        }

        layoutGanado.setVisible(false);
        layoutPerdido.setVisible(false);
        bolaCanon1.setVisible(true);
        bolaCanon2.setVisible(true);
        bolaCanon3.setVisible(true);

        numVidas.setText("Vidas: 3");

        bolaPosX = 60;
        bolaPosY = 410;
        velocidadBolaX = 4;

        bola2PosX = 182;
        bola2PosY = 28;
        velocidadBola2Y = 6;

        bola3PosX = 432;
        bola3PosY = 28;
        velocidadBola3Y = 6;

        personajeX = 550;
        personajeY = 380;
        velocidadPersonajeX = 0;
        velocidadPersonajeY = 0;

        numVidasActual = 3;

    }

    public static void main(String[] args) {
        launch();

    }

}
