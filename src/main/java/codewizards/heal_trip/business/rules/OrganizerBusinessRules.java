package codewizards.heal_trip.business.rules;

import codewizards.heal_trip.business.messages.OrganizerMessages;
import codewizards.heal_trip.dataAccess.HospitalOrganizerDao;
import codewizards.heal_trip.dataAccess.HotelOrganizerDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizerBusinessRules {
    private final HospitalOrganizerDao hospitalOrganizerDao;
    private final HotelOrganizerDao hotelOrganizerDao;

    public void checkIfHospitalOrganizerExists(int hospitalOrganizerId) {
        if (!hospitalOrganizerDao.existsById(hospitalOrganizerId)) {
            throw new IllegalArgumentException(OrganizerMessages.HOSPITAL_ORGANIZER_NOT_FOUND);
        }
    }

    public void checkIfHotelOrganizerExists(int hotelOrganizerId) {
        if (!hotelOrganizerDao.existsById(hotelOrganizerId)) {
            throw new IllegalArgumentException(OrganizerMessages.HOTEL_ORGANIZER_NOT_FOUND);
        }
    }
}
