package codewizards.heal_trip.business;

import codewizards.heal_trip.dataAccess.RetreatDao;
import codewizards.heal_trip.entities.Retreat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetreatService implements IRetreatService{

    private RetreatDao retreatDao;

    @Autowired
    public RetreatService(RetreatDao retreatDao) {
        this.retreatDao = retreatDao;
    }

    public Retreat getRetreatById(int retreat_id) {
        return retreatDao.findById(retreat_id).orElse(null);
    }
}
