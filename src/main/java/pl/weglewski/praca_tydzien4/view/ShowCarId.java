package pl.weglewski.praca_tydzien4.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.weglewski.praca_tydzien4.controller.CarController;
import pl.weglewski.praca_tydzien4.model.Car;

@Route("show-id")
public class ShowCarId extends VerticalLayout {

    private CarController carController;

    @Autowired
    public ShowCarId(CarController carController) {
        this.carController = carController;
        TextField textFieldId = new TextField();
        textFieldId.setLabel("Podaj Id:");
        Button buttonId = new Button("Show");

        buttonId.addClickListener(buttonClickEvent -> {
            Grid<Car> grid = new Grid<>(Car.class);
            grid.setItems(carController.getCarById(Long.parseLong(textFieldId.getValue())).getBody());
            grid.removeColumnByKey("carId");
            grid.setSortableColumns();
            add(grid);
        });


        add(textFieldId, buttonId);
    }

}
