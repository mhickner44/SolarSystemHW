/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solarsystem;


import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author mhick
 */
public class SolarSystem extends Application {
    
    private static double planetRadius = 5;
    private static int radiusX = 50;
    private static int radiusY = 0;
    private static int orbitalPathX = 1050;
    
    private Path createOrbitalPath(double centerX, double centerY, double radiusX, double radiusY, double rotate) {
        ArcTo arcTo = new ArcTo();
        arcTo.setX(centerX - radiusX + 1);
        arcTo.setY(centerY - radiusY);
        arcTo.setSweepFlag(false);
        arcTo.setLargeArcFlag(true);
        arcTo.setRadiusX(radiusX);
        arcTo.setRadiusY(radiusY);
        arcTo.setXAxisRotation(rotate);
        
        Path path = new Path();
        path.getElements().add(new MoveTo(centerX - radiusX, centerY - radiusY));
        path.getElements().add(arcTo);
        path.getElements().add(new ClosePath());
        
        path.setStroke(Color.BLUE);
        path.setStrokeWidth(2);
        path.setVisible(true);
        return path;
    }

  
   
    
    private void createStage(Stage stage) {
        Group root = new Group();
        stage.setResizable(true);
        stage.setMaximized(true);
        Scene scene = new Scene(root, 1000, 1000);
        Image background = new Image("/solarsystem/starBackground.png");
        scene.setFill(new ImagePattern(background));
        
        stage.setScene(scene);
//the sun 
        Circle sun = new Circle(30);
        Image sunPic = new Image("/solarsystem/sunPic.png");
        sun.setFill(new ImagePattern(sunPic));
        sun.setCenterX(1000);
        sun.setCenterY(500);
        root.getChildren().add(sun);
//the planets and their pictures
        Circle mercury = new Circle();
        Image mercPic = new Image("/solarsystem/Mercury_Pic.jpg");
        mercury.setFill(new ImagePattern(mercPic));
        
        Circle venus = new Circle();
        Image venusPic = new Image("/solarsystem/venusPic.jpg");
        venus.setFill(new ImagePattern(venusPic));
        
        Circle earth = new Circle();
        Image earthPic = new Image("/solarsystem/earthPic.jpg");
        earth.setFill(new ImagePattern(earthPic));
        
        Circle mars = new Circle();
        Image marsPic = new Image("/solarsystem/marsPicjpg.jpg");
        mars.setFill(new ImagePattern(marsPic));
        
        Circle jupiter = new Circle();
        Image jupiterPic = new Image("/solarsystem/jupiter.png");
        jupiter.setFill(new ImagePattern(jupiterPic));
        
        Circle saturn = new Circle();
        Image saturnPic = new Image("/solarsystem/saturnPic.jpg");
        saturn.setFill(new ImagePattern(saturnPic));
        
        Circle uranus = new Circle();
        Image uranusPic = new Image("/solarsystem/uranusPic.jpg");
        uranus.setFill(new ImagePattern(uranusPic));
        
        Circle neptune = new Circle();
        Image neptunePic = new Image("/solarsystem/neptunePic.jpg");
        neptune.setFill(new ImagePattern(neptunePic));
        
        Circle[] planetSize = {mercury, mars, venus, earth, neptune, uranus, saturn, jupiter};

 //setting planet size from smallest to largest incrementing by fixed amount
        for (int i = 0; i < planetSize.length; i++) {
            planetRadius += 6;
            planetSize[i].setRadius(planetRadius);
            root.getChildren().add(planetSize[i]);
        }

   //order of planets from closests to furthest setting path
        int increment = 0;
        Circle[] planetLineup = {mercury, venus, earth, mars, jupiter, saturn, uranus, neptune};
        for (int ii = 0; ii < planetLineup.length; ii++) {
            orbitalPathX += 60;
            radiusX += 80;
            radiusY += 60;
            Path i = createOrbitalPath(orbitalPathX, 500, radiusX, radiusY, 0);
            i.setVisible(false);
            root.getChildren().add(i);
            
            PathTransition t = new PathTransition();
            //for loop that usees array of time it takes to orbit the sun for each planet
            //increment an integer that goes through each time
            //seconds for each planet to make one revolution around the sun 
            double[] planetSpeed = {2.4, 6.17, 10, 18.8, 120, 290, 840, 1650};
            
            t.setDuration(Duration.seconds(planetSpeed[increment]));
            t.setPath(i);
            t.setNode(planetLineup[ii]);
            t.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
            
            t.setCycleCount(Timeline.INDEFINITE);
            t.setAutoReverse(false);
            t.setInterpolator(Interpolator.LINEAR);
            t.play();
            increment++;
        }

 //doing the same thing with text as done with circles
  //setting planet names and paths
        Text mercName = new Text("Mercury");
        mercName.setFill(Color.WHITE);
        
        
        Text venusName = new Text("Venus");
        venusName.setFill(Color.WHITE);
        
        Text earthName = new Text("Earth");
        earthName.setFill(Color.WHITE);
        
        Text marsName = new Text("Mars");
        marsName.setFill(Color.WHITE);
        
        Text jupiterName = new Text("Jupiter");
        jupiterName.setFill(Color.WHITE);
        
        Text saturnName = new Text("Saturn");
        saturnName.setFill(Color.WHITE);
        
        Text uranusName = new Text("Uranus");
        uranusName.setFill(Color.WHITE);
        
        Text neptuneName = new Text("Neptune");
        neptuneName.setFill(Color.WHITE);
        
        Text[] planetNames = {mercName, venusName, earthName, marsName, jupiterName, saturnName, uranusName, neptuneName};
 //adding the text 
        for (int i = 0; i < planetNames.length; i++) {
            
            root.getChildren().add(planetNames[i]);
        }
  //setting text speed and placement
        int incrementNames = 0;

        orbitalPathX = 1050;
        radiusX = 50;
        radiusY = 0;
        
        for (int iii = 0; iii < planetNames.length; iii++) {
            orbitalPathX += 50;
            radiusX += 70;
            radiusY += 50;
            Path ii = createOrbitalPath(orbitalPathX, 500, radiusX, radiusY, 0);
            ii.setVisible(false);
            root.getChildren().add(ii);
            
            PathTransition t = new PathTransition();
            //for loop that usees array of time it takes to orbit the sun for each planet
            //increment an integer that goes through each time
            //seconds for each planet to make one revolution around the sun 
            double[] planetSpeed = {2.4, 6.17, 10, 18.8, 120, 290, 840, 1650};
            
            t.setDuration(Duration.seconds(planetSpeed[incrementNames]));
            t.setPath(ii);
            t.setNode(planetNames[iii]);
            
            t.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
            t.setCycleCount(Timeline.INDEFINITE);
            t.setAutoReverse(false);
            t.setInterpolator(Interpolator.LINEAR);
            
            t.play();
            incrementNames++;
        }
        

    }
    
    @Override
    public void start(Stage primaryStage) {
        createStage(primaryStage);
        primaryStage.setTitle("Solar System");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
