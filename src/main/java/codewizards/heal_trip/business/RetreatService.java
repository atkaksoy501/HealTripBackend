package codewizards.heal_trip.business;

import codewizards.heal_trip.dataAccess.RetreatDao;
import codewizards.heal_trip.entities.Retreat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int addRetreat(Retreat retreat) {
        retreat = retreatDao.save(retreat);
        return retreat.getId();
    }

    public boolean deleteRetreat(int retreat_id) {
        retreatDao.deleteById(retreat_id);
        Retreat retreat = retreatDao.findById(retreat_id).orElse(null);
        if (retreat == null)
            return true;
        else
            return false;
    }

    public Retreat updateRetreat(Retreat retreat, int retreat_id) {
        Retreat dbRetreat = retreatDao.findById(retreat_id).orElse(null);
        if (dbRetreat != null) {
            if (retreat.getRetreat_name() != null)
                dbRetreat.setRetreat_name(retreat.getRetreat_name());
            if (retreat.getDescription() != null)
                dbRetreat.setDescription(retreat.getDescription());
            dbRetreat = retreatDao.save(dbRetreat);
        }
        return dbRetreat;
    }

    public Iterable<Retreat> getAllRetreats() {
        return retreatDao.findAll();
    }
}
