package nl.prismait.epublisher.java.model.narrowcasting;

import java.util.Comparator;

public class BroadcastWrapperComparator implements Comparator<BroadcastWrapper>{
    @Override
    public int compare(BroadcastWrapper o1, BroadcastWrapper o2) {
        return Integer.valueOf(o1.getOrderOfBroadcast()).compareTo(Integer.valueOf(o2.getOrderOfBroadcast()));
    }
    
}
