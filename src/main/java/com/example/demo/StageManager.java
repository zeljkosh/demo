package com.example.demo;


import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Manages switching Scenes on the Primary Stage
 */
public class StageManager {


    private static final Logger LOG = getLogger(StageManager.class);
    private final Stage primaryStage;
    private final SpringFXMLLoader springFXMLLoader;
    	
    public StageManager(SpringFXMLLoader springFXMLLoader, Stage stage) {
        this.springFXMLLoader = springFXMLLoader;
        
        this.primaryStage = stage;
        
        
        
    }

    public void switchScene(final FxmlView view) {
    	
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());

        showLogin(viewRootNodeHierarchy, view.getTitle());

        //primaryStage.setMaximized(false);
    }
  public void switchSceneLogin(final FxmlView view) {
    	
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());
        
        showLogin2(viewRootNodeHierarchy, view.getTitle());
        //primaryStage.setMaximized(true);
        //primaryStage.setMaximized(true);
        
       
    }


   public Parent loadParent(final FxmlView view) {
    	
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());
         
       primaryStage.setTitle(view.getTitle());
       
       
      
       
       return viewRootNodeHierarchy;
    }
    

    private void showLogin(final Parent rootnode, String title) {

        Scene scene = prepareScene(rootnode);

       // new JMetro(JMetro.Style.DARK).applyTheme(scene);
        primaryStage.setTitle(title);
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(false);

        primaryStage.setResizable(false);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
       // ResizeHelper.addResizeListener(primaryStage);
       // primaryStage.sizeToScene();
        //primaryStage.centerOnScreen();
        //	primaryStage.setMaximized(true);
        
        try {
            primaryStage.show();
        } catch (Exception exception) {
            logAndExit ("Unable to show scene for title" + title,  exception);
        }
    }
  private void showLogin2(final Parent rootnode, String title) {
        
        Scene scene = prepareScene(rootnode);



        //new JMetro(JMetro.Style.LIGHT).applyTheme(scene);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
       /* primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());*/
       primaryStage.setResizable(true);
       primaryStage.setHeight(800);
       primaryStage.setWidth(1000);






       primaryStage.setTitle(title);
       primaryStage.setScene(scene);

        //primaryStage.setMaximized(true);
     //   primaryStage.initStyle(StageStyle.UNDECORATED);
      //  ResizeHelper.addResizeListener(primaryStage);
       // primaryStage.sizeToScene();
        //primaryStage.centerOnScreen();
        //	primaryStage.setMaximized(true);
        
        try {
            primaryStage.show();
        } catch (Exception exception) {
            logAndExit ("Unable to show scene for title" + title,  exception);
        }
    }
    
    private Scene prepareScene(Parent rootnode){
         
        Scene scene = primaryStage.getScene();
        	
        if (scene == null) {
            scene = new Scene(rootnode);
        }
        scene.setRoot(rootnode);

          
        return scene;
    }
    
  

    /**
     * Loads the object hierarchy from a FXML document and returns to root node
     * of that hierarchy.
     *
     * @return Parent root node of the FXML document hierarchy
     */
    private Parent loadViewNodeHierarchy(String fxmlFilePath) {
        Parent rootNode = null;
        try {
           
            rootNode = springFXMLLoader.load(fxmlFilePath);
           
            Objects.requireNonNull(rootNode, "A Root FXML node must not be null");
        } catch (Exception exception) {
            logAndExit("Unable to load FXML view" + fxmlFilePath, exception);
        }
        return rootNode;
    }
    
    
    private void logAndExit(String errorMsg, Exception exception) {
        LOG.error(errorMsg, exception, exception.getCause());
        Platform.exit();
    }

    private String propInfo(){
        Properties prop = new Properties();
        InputStream input = null;
        String stylePath = new String() ;

        try {

            String filename = "preferences.properties";

            input = getClass().getClassLoader().getResourceAsStream(filename);
            if(input==null){
                System.out.println("Sorry, unable to find " + filename);

            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            stylePath =prop.getProperty("style");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  stylePath;
    }

}
