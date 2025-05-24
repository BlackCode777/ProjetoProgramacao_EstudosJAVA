package com.blackcode.concepts_java;

import java.util.ArrayList;
import java.util.List;

public class StudingOfTheRecordsInJava_OldFeature {
	
	private Long idJavaOldFeature;
	private List<StudingOfTheRecordsInJava_NewFeature> recodsUsingList = new ArrayList<>();

	public StudingOfTheRecordsInJava_OldFeature(
			Long idJavaOldFeature, List<StudingOfTheRecordsInJava_NewFeature> recodsUsingList) {
		this.idJavaOldFeature = idJavaOldFeature;
		this.recodsUsingList = recodsUsingList;
	}

	public Long getIdJavaOldFeature() {
		return idJavaOldFeature;
	}

	public void setIdJavaOldFeature(Long idJavaOldFeature) {
		this.idJavaOldFeature = idJavaOldFeature;
	}

	public List<StudingOfTheRecordsInJava_NewFeature> getRecodsUsingList() {
		return recodsUsingList;
	}

	public void setRecodsUsingList(List<StudingOfTheRecordsInJava_NewFeature> recodsUsingList) {
		this.recodsUsingList = recodsUsingList;
	}	
}
