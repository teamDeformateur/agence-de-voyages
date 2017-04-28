package agence.dao;

import agence.model.CompagnieAerienneVol;

public interface CompagnieAerienneVolDao
        extends Dao<CompagnieAerienneVol, Integer>
{

    CompagnieAerienneVol findById(Integer id);

}
