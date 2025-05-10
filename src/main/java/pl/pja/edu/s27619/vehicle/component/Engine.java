package pl.pja.edu.s27619.vehicle.component;

import pl.pja.edu.s27619.exceptions.CheckDataException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Engine implements Serializable {
    private EngineType engineType;
    private int power;
    private List<EngineInspection> inspections = new ArrayList<>();

    /**
     * Constructor to initialize Engine object.
     *
     * @param engineType enum which contains engine type
     * @param power      integer variable contains power of the engine
     */
    public Engine(EngineType engineType, int power) {
        setEngineType(engineType);
        setPower(power);
    }

    public EngineType getEngineType() {
        return engineType;
    }

    /**
     * Method sets engineType, if it is not null, otherwise throws exception.
     *
     * @param engineType enum which contains engine type
     * @throws CheckDataException if engineType is null
     */
    public void setEngineType(EngineType engineType) {
        if (engineType == null) {
            throw new CheckDataException("Engine could not be null");
        }
        this.engineType = engineType;
    }

    /**
     * Method sets power engine, if it is not negative value, otherwise throws exception.
     *
     * @param power integer variable contains power of the engine
     * @throws CheckDataException if power is negative
     */
    public void setPower(int power) {
        if (power <= 0) {
            throw new CheckDataException("Power could not be less than 0");
        }
        this.power = power;
    }

    /**
     * Method adds inspection to the current engine.
     *
     * @param inspection contains detailed information about the inspection
     */
    public void addInspection(EngineInspection inspection) {
        if (!inspections.contains(inspection)) {
            inspections.add(inspection);
        }
    }

    public List<EngineInspection> getInspections() {
        return inspections;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        return engineType.toString() + " (" + power + " HP)";
    }

}
