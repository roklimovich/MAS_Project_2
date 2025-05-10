package pl.pja.edu.s27619.service;

import pl.pja.edu.s27619.exceptions.CheckDataException;
import pl.pja.edu.s27619.vehicle.Vehicle;
import pl.pja.edu.s27619.vehicle.component.EngineInspection;
import pl.pja.edu.s27619.vehicle.condition.VehicleCertificate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mechanic {
    private String name;
    private List<VehicleCertificate> issuedCertificates = new ArrayList<>();
    private List<EngineInspection> engineInspections = new ArrayList<>();

    public Mechanic(String name) {
        setName(name);
    }

    /**
     * Add certificates to issued one, which mechanic gave to the vehicle.
     *
     * @param cert contains information about the vehicle certificate
     */
    public void addIssuedCertificate(VehicleCertificate cert) {
        if (!issuedCertificates.contains(cert)) {
            issuedCertificates.add(cert);
            cert.setMechanic(this); // reverse link
        }
    }

    /**
     * Remove given certificate from issued ones, which mechanic gave to the vehicle.
     *
     * @param cert contains information about the vehicle certificate
     */
    public void removeIssuedCertificate(VehicleCertificate cert) {
        if (issuedCertificates.remove(cert)) {
            cert.removeMechanic(); // reverse link
        }
    }

    /**
     * Add engine inspection to the list of the engine inspections.
     *
     * @param inspection variable which contains information about the engine inspection
     */
    public void addEngineInspection(EngineInspection inspection) {
        if (!engineInspections.contains(inspection)) {
            engineInspections.add(inspection);
        }
    }

    /**
     * Method sets the name to Mechanic, firstly checks if the name empty or null, if yes - throw exception, otherwise
     * set the name.
     *
     * @param name String variable which contains information about the name
     */
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new CheckDataException("Name could not be null or empty");
        }

        this.name = name;
    }

    public List<EngineInspection> getEngineInspections() {
        return engineInspections;
    }

    public List<VehicleCertificate> getIssuedCertificates() {
        return Collections.unmodifiableList(issuedCertificates);
    }

    public String getName() {
        return name;
    }
}
