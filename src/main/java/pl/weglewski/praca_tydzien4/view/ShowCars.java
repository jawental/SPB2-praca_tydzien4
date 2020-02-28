package pl.weglewski.praca_tydzien4.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.weglewski.praca_tydzien4.controller.CarController;
import pl.weglewski.praca_tydzien4.model.Car;

@Route("show-all")
public class ShowCars extends VerticalLayout {

    private CarController carController;

    @Autowired
    public ShowCars(CarController carController) {
        this.carController = carController;
        Grid<Car> grid = new Grid<>(Car.class);
        grid.setItems(carController.getCars().getBody());
        add(grid);
    }

}
