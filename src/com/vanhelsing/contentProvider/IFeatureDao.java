package com.vanhelsing.contentProvider;

import com.vanhelsing.Feature;


public interface IFeatureDao {

	boolean persist(Feature feature);

	Feature get(String feature);

}