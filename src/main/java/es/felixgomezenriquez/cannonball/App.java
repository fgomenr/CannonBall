package es.felixgomezenriquez.cannonball;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;



public class App extends Application {

    @Override
    public void start(Stage stage) {
        
        final int TAM_SCENE_X=640;
        
        final int TAM_SCENE_Y=480;
        
        
        Pane root  = new Pane();
        Scene scene = new Scene(root, TAM_SCENE_X, TAM_SCENE_Y);
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
        
        
        //Funcion disparo
        
        //X cañon boca 108  Y cañon boca 390
        
       //PROBANDO AVE SI DIBUJA LINEA
       
        Line line=new Line();
        
       
        //          x         y
        //y = ( x – 10 )^2 + 20
        
        
        for (int x= 0; )
        
        
            
            
       }
        
       
        
        scene.setOnMouseClicked((MouseEvent mouseEvent) -> {
        
            System.out.println("Ratón clicado en (x, y): (" +mouseEvent.getX() + ", " + mouseEvent.getY() + ")");
            
            });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
                

    }

    public static void main(String[] args) {
            launch();

    }

}

