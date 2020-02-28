package pl.weglewski.praca_tydzien4.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.weglewski.praca_tydzien4.controller.CarController;
import pl.weglewski.praca_tydzien4.model.Car;

import java.util.Objects;


@Route("add-car")
public class AddCar extends VerticalLayout {

    private CarController carController;

    @Autowired
    public AddCar(CarController carController) {
        this.carController = carController;

        TextField textFieldMark = new TextField("Mark");
        TextField textFieldModel = new TextField("Model");
        TextField textFieldColor = new TextField("Color");
        add(textFieldMark);
        add(textFieldModel);
        add(textFieldColor);

        Button buttonAdd = new Button("Add");
        add(buttonAdd);

        buttonAdd.addClickListener(buttonClickEvent -> {
            int exe = carController.addCar(new Car(
                    carController.maxId()+1,
                    textFieldMark.getValue(),
                    textFieldModel.getValue(),
                    textFieldColor.getValue())).getStatusCodeValue();

            boolean fine = Objects.equals(exe, 201);
            if (fine) {
                Notification notification = Notification.show(
                        "Car added!", 3000,
                        Notification.Position.TOP_START);
            } else {
                Notification notification = Notification.show(
                        "Error. Nothing added!", 3000,
                        Notification.Position.TOP_START);
            }

        });
    }

}
