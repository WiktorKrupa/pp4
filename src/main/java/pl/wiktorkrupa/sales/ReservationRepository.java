package pl.wiktorkrupa.sales;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository
        extends JpaRepository<Reservation, String> {
}
