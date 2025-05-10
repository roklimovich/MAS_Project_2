package pl.pja.edu.s27619;

import pl.pja.edu.s27619.service.Mechanic;
import pl.pja.edu.s27619.vehicle.*;
import pl.pja.edu.s27619.vehicle.component.Engine;
import pl.pja.edu.s27619.vehicle.component.EngineInspection;
import pl.pja.edu.s27619.vehicle.component.EngineType;
import pl.pja.edu.s27619.vehicle.condition.VehicleCertificate;
import pl.pja.edu.s27619.vehicle.repair.ServiceRecord;

import java.time.LocalDate;
import java.util.Optional;


public class Main {
    public static void main(String[] args) {

        // Binary Association. Mechanic gives a VehicleCertificate to a Vehicle
        System.out.println("=== Binary Association ===");
        Mechanic john = new Mechanic("John");
        Engine toyotaEngine = new Engine(EngineType.DIESEL, 300);
        Car toyotaCar = new Car(VehicleType.CAR, "Toyota", "Avensis", toyotaEngine, 5);
        VehicleCertificate certificate = new VehicleCertificate("CERT-001", toyotaCar, john);

        System.out.println("Certificate created by: " + certificate.getMechanic().getName());
        System.out.println("Vehicle linked to certificate: " + certificate.getVehicle().getUniqueId());

        // Composition. Vehicle owns ServiceRecords
        System.out.println("\n=== Composition ===");
        ServiceRecord sr1 = new ServiceRecord(LocalDate.of(2023, 10, 1),
                "Oil change", 9000, toyotaCar);
        ServiceRecord sr2 = new ServiceRecord(LocalDate.of(2024, 1, 10),
                "Tire rotation", 50000, toyotaCar);

        System.out.println("Service records for vehicle:");
        toyotaCar.displayServiceHistory();

        // Qualified Association. Retrieve service record by date
        System.out.println("\n=== Qualified Association ===");
        Optional<ServiceRecord> record = toyotaCar
                .getServiceRecordByDate(LocalDate.of(2023, 10, 1));
        if (record.isPresent()) {
            System.out.println("Qualified service record: " + record.get().getServiceDate() + " - "
                    + record.get().getDescription());
        } else {
            System.out.println("No service record found for that date.");
        }

        // Association with Attribute. EngineInspection (between Engine and Mechanic)
        System.out.println("\n=== Association with Attribute ===");
        Engine engine = new Engine(EngineType.DIESEL, 500);
        Mechanic anna = new Mechanic("Anna");
        EngineInspection inspection1 = new EngineInspection(john, engine,
                LocalDate.of(2024, 2, 15), "Passed", "No issues");
        EngineInspection inspection2 = new EngineInspection(anna, engine,
                LocalDate.of(2024, 3, 1), "Needs Attention", "Oil leak");

        System.out.println("Engine inspections:");
        engine.getInspections().forEach(i -> {
            System.out.println(i.getInspectionDate() + " - " + i.getMechanic().getName() + ": " + i.getResult()
                    + " - " + i.getComments());
        });
    }
}

