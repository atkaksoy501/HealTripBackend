package codewizards.heal_trip.business;

import codewizards.heal_trip.dataAccess.RetreatDTO;
import codewizards.heal_trip.entities.Retreat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetreatService implements IRetreatService{

    private RetreatDTO retreatDTO;

    @Autowired
    public RetreatService(RetreatDTO retreatDTO) {
        this.retreatDTO = retreatDTO;
    }

    public Retreat getRetreatById(int retreat_id) {
        return retreatDTO.findById(retreat_id).orElse(null);
    }
}
