package uz.alishev.spring.FirstRestApp.util;

public class SensorNotCreatedException extends  RuntimeException{
    public SensorNotCreatedException(String msg){
        super(msg);
    }
}
