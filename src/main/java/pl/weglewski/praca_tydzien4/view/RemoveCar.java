package pl.weglewski.praca_tydzien4.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.weglewski.praca_tydzien4.controller.CarController;
import pl.weglewski.praca_tydzien4.model.Car;

@Route("remove")
public class RemoveCar extends VerticalLayout {

    private CarController carController;

    Grid<Car> grid;

    @Autowired
    public RemoveCar(CarController carController) {
        this.carController = carController;
        grid = new Grid<>(Car.class);
        grid.setItems(carController.getCars().getBody());
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addColumn(new NativeButtonRenderer<>("Delete", item -> {
            carController.removeCar(item.getCarId());
            grid.getDataProvider().refreshAll();
        }));

        add(grid);
    }


}
