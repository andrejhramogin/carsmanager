package car;

import java.io.Serializable;
import java.util.List;

public class CarList {
    private List<Car> carList;

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
