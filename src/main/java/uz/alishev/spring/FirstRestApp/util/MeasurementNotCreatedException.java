package uz.alishev.spring.FirstRestApp.util;

public class MeasurementNotCreatedException extends  RuntimeException{
    public MeasurementNotCreatedException(String msg){
        super(msg);
    }
}
