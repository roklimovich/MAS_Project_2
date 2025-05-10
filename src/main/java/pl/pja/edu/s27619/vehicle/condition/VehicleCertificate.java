package pl.pja.edu.s27619.vehicle.condition;

import pl.pja.edu.s27619.exceptions.CheckDataException;
import pl.pja.edu.s27619.service.Mechanic;
import pl.pja.edu.s27619.vehicle.Vehicle;

public class VehicleCertificate {
    private String certificateId;
    private Vehicle vehicle;
    private Mechanic mechanic;


    public VehicleCertificate(String certificateId, Vehicle vehicle, Mechanic mechanic) {
        setCertificateId(certificateId);

        if (vehicle == null || mechanic == null) {
            throw new CheckDataException("Vehicle and Mechanic cannot be null");
        }
        vehicle.addCertificate(this);
        setVehicle(vehicle);
        setMechanic(mechanic);
    }

    /**
     * Method sets certificate id, firstly checks if certificate id is null, or empty, if yes - throw exception,
     * otherwise set the certificate id
     *
     * @param certificateId String variable which contains certificate ID
     */
    public void setCertificateId(String certificateId) {
        if (certificateId == null || certificateId.isBlank()) {
            throw new CheckDataException("Certificate ID could not be null");
        }

        this.certificateId = certificateId;
    }

    /**
     * Method sets the vehicle automatically to the given certificate
     *
     * @param vehicle variable which contains information about the vehicle
     */
    public void setVehicle(Vehicle vehicle) {
        if (this.vehicle != vehicle) {
            this.vehicle = vehicle;
            vehicle.addCertificate(this);
        }
    }

    /**
     * Method sets the mechanic automatically to the given certificate
     *
     * @param mechanic variable which contains information about the mechanic
     */
    public void setMechanic(Mechanic mechanic) {
        if (this.mechanic != mechanic) {
            this.mechanic = mechanic;
            mechanic.addIssuedCertificate(this);
        }
    }

    public void removeVehicle() {
        if (this.vehicle != null) {
            Vehicle temp = this.vehicle;
            this.vehicle = null;
            temp.removeCertificate(this);
        }
    }

    public void removeMechanic() {
        if (this.mechanic != null) {
            Mechanic temp = this.mechanic;
            this.mechanic = null;
            temp.removeIssuedCertificate(this);
        }
    }

    public String getCertificateId() {
        return certificateId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }
}
