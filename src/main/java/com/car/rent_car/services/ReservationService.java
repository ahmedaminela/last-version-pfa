package com.car.rent_car.services;

import com.car.rent_car.models.Client;
import com.car.rent_car.models.Voiture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.car.rent_car.models.Reservation;
import com.car.rent_car.repository.ReservationRepository;
import com.car.rent_car.requests.ReservationRequest;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final IVoitureService voitureService;
    private final IClientService clientService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, IVoitureService voitureService, IClientService clientService) {
        this.reservationRepository = reservationRepository;
        this.voitureService = voitureService;
        this.clientService = clientService;
    }

    @Override
    public Reservation createReservation(ReservationRequest reservationRequest) {
        // Retrieve the voiture and client objects
        Optional<Voiture> optionalVoiture = Optional.ofNullable(voitureService.getVoitureById(reservationRequest.getVoitureId()));
        Voiture voiture = optionalVoiture.orElseThrow(() -> new RuntimeException("Voiture not found with id: " + reservationRequest.getVoitureId()));

        Client client = clientService.getClientById(reservationRequest.getClientId());
        if (client == null) {
            throw new RuntimeException("Client not found with id: " + reservationRequest.getClientId());
        }

        // Create a new Reservation object
        Reservation reservation = new Reservation();
        reservation.setDateDebut(reservationRequest.getDateDebut());
        reservation.setDateFin(reservationRequest.getDateFin());
        reservation.setVoiture(voiture);
        reservation.setClient(client);

        // Save the reservation to the database
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation updateReservation(Long id, ReservationRequest reservationRequest) {
        Reservation existingReservation = reservationRepository.findById(id).orElse(null);
        if (existingReservation == null) {
            throw new RuntimeException("Reservation not found with id: " + id);
        }

        // Retrieve the voiture and client objects
        Optional<Voiture> optionalVoiture = Optional.ofNullable(voitureService.getVoitureById(reservationRequest.getVoitureId()));
        Voiture voiture = optionalVoiture.orElseThrow(() -> new RuntimeException("Voiture not found with id: " + reservationRequest.getVoitureId()));

        Client client = clientService.getClientById(reservationRequest.getClientId());
        if (client == null) {
            throw new RuntimeException("Client not found with id: " + reservationRequest.getClientId());
        }

        // Update the existing Reservation object
        existingReservation.setDateDebut(reservationRequest.getDateDebut());
        existingReservation.setDateFin(reservationRequest.getDateFin());
        existingReservation.setVoiture(voiture);
        existingReservation.setClient(client);

        // Save the updated reservation to the database
        return reservationRepository.save(existingReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
