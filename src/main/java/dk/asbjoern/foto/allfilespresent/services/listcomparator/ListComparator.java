package dk.asbjoern.foto.allfilespresent.services.listcomparator;

import dk.asbjoern.foto.allfilespresent.beans.Image;

import java.util.List;

public interface ListComparator {

    List<Image> findDifferences(List<Image> source, List<Image> destination);

}
