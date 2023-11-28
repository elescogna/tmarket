package entities;

import java.time.LocalDateTime;

public interface StudentFactory {
    /** Requires: password is valid. */
    Student create(String name, String password, String homeAddress, String uoftEmail);

}
