package com.gluonapplication;

import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class BasicView extends View {
    ArrayList<String> al =new ArrayList<String>();

    public BasicView() {
        al.add("Jhon");
        al.add("Jack");
        al.add("Ivan");
        al.add("Laura");
        
        Button b = new Button(al.get(0));
        //Label label = new Label("Hello JavaFX World!");

        //Button button = new Button("Change the World!");
        //button.setGraphic(new Icon(MaterialDesignIcon.LANGUAGE));
        //button.setOnAction(e -> label.setText("Hello shashank!"));
        
        VBox controls = new VBox(15.0, b);
        controls.setAlignment(Pos.CENTER);
        
        setCenter(controls);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> System.out.println("Menu")));
        appBar.setTitleText("Basic View");
        appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> System.out.println("Search")));
    }
    
}
