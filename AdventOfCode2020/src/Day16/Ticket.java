package Day16;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Ticket {
    private List<Short> numberOnTicket;

    public Ticket() {
    }

    public Ticket(List<Short> numberOnTicket) {
        this.numberOnTicket = numberOnTicket;
    }

    public List<Short> getNumberOnTicket() {
        return numberOnTicket;
    }

    public void setNumberOnTicket(List<Short> numberOnTicket) {
        this.numberOnTicket = numberOnTicket;
    }

    public boolean isTicketValid(List<Validator> validators) {
        for (Short temp : this.numberOnTicket) {
            Optional<Validator> a = validators.stream().filter(validator -> validator.getValidNumbers().contains(temp)).findAny();
            if (a.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public List<Short> getInvalidTicketNumbersValid(List<Validator> validators) {
        List<Short> result = new ArrayList<>();
        for (Short temp : this.numberOnTicket) {
            Optional<Validator> a = validators.stream().filter(validator -> validator.getValidNumbers().contains(temp)).findAny();
            if (a.isEmpty()) {
                result.add(temp);
            }
        }
        return result;
    }
}
