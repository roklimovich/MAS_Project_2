package pl.pja.edu.s27619.vehicle.component;

import pl.pja.edu.s27619.service.Mechanic;

import java.time.LocalDate;

public class EngineInspection {
    private Mechanic mechanic;
    private Engine engine;
    private LocalDate inspectionDate;
    private String result;
    private String comments;

    public EngineInspection(Mechanic mechanic, Engine engine, LocalDate date, String result, String comments) {
        if (mechanic == null || engine == null || date == null || result == null) {
            throw new IllegalArgumentException("Inspection details must not be null");
        }

        this.mechanic = mechanic;
        this.engine = engine;
        this.inspectionDate = date;
        this.result = result;
        this.comments = comments;

        this.mechanic.addEngineInspection(this);
        this.engine.addInspection(this);
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public Engine getEngine() {
        return engine;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public String getResult() {
        return result;
    }

    public String getComments() {
        return comments;
    }
}
