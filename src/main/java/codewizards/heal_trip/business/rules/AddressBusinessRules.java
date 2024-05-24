package codewizards.heal_trip.business.rules;

import codewizards.heal_trip.business.messages.AddressMessages;
import codewizards.heal_trip.core.utilities.exceptions.types.BusinessException;
import codewizards.heal_trip.dataAccess.AddressDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressBusinessRules {
    private final AddressDao addressDao;

    public void checkIfAddressExists(int id) {
        if (!addressDao.existsById(id)) {
            throw new BusinessException(AddressMessages.ADDRESS_NOT_FOUND);
        }
    }
}
