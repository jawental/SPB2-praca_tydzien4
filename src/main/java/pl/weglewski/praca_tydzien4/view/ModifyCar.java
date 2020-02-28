package pl.weglewski.praca_tydzien4.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.weglewski.praca_tydzien4.controller.CarController;
import pl.weglewski.praca_tydzien4.model.Car;

import java.util.Objects;

@Route("mod")
public class ModifyCar extends VerticalLayout {

    private CarController carController;
    long Id;

    @Autowired
    public ModifyCar(CarController carController) {
        this.carController = carController;
        Grid<Car> grid = new Grid<>(Car.class);

        TextField textFieldMark = new TextField("Mark");
        TextField textFieldModel = new TextField("Model");
        TextField textFieldColor = new TextField("Color");
        add(textFieldMark);
        add(textFieldModel);
        add(textFieldColor);

        grid.setItems(carController.getCars().getBody());
        grid.asSingleSelect().addValueChangeListener(event -> {
            Id = event.getValue().getCarId();
            textFieldMark.setValue(event.getValue().getMark());
            textFieldModel.setValue(event.getValue().getModel());
            textFieldColor.setValue(event.getValue().getColor());
        });

        Button buttonChange = new Button("Change");

        add(grid, buttonChange);

        buttonChange.addClickListener(buttonClickEvent -> {
            int exe = carController.modCar(new Car(
                    Id,
                    textFieldMark.getValue(),
                    textFieldModel.getValue(),
                    textFieldColor.getValue())).getStatusCodeValue();

            boolean fine = Objects.equals(exe, 200);

            if (fine) {
                Notification notification = Notification.show(
                        "Car modified!", 3000,
                        Notification.Position.TOP_START);
            } else {
                Notification notification = Notification.show(
                        "Error. Nothing modified!", 3000,
                        Notification.Position.TOP_START);
            }
            grid.getDataProvider().refreshAll();

        });
    }

}
