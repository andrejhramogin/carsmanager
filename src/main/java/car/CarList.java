package car;

import java.io.Serializable;
import java.util.List;

public class CarList implements Serializable {

    private List<Car> carList;
    private static final long serialVersionUID = 1;

    public CarList(){};

    public CarList (List<Car> carList){
        this.carList = carList;
    }

    public List<Car> getCarList (){
        return carList;
    }

    public void setCarList(List<Car>carList){
        this.carList = carList;
    }
}
