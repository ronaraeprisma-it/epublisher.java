package nl.prismait.epublisher.java.business.comparator;

import java.util.Comparator;

import nl.prismait.epublisher.java.model.Profile;

public class ProfileComparatorOnId implements Comparator<Profile> 
{
	@Override
	public int compare(Profile arg0, Profile arg1) {
		return Integer.valueOf(arg0.getId()).compareTo(Integer.valueOf(arg1.getId()));
	}
}
