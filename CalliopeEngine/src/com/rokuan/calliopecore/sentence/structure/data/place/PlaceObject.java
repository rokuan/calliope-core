package com.rokuan.calliopecore.sentence.structure.data.place;

import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.content.ISecondObject;
import com.rokuan.calliopecore.sentence.structure.content.IVerbalObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.NominalGroup.GroupType;

public class PlaceObject extends PlaceAdverbial implements INominalObject, ISecondObject {
	public enum PlaceCategory {
		AIRPORT,
		AQUARIUM,
		ART_GALLERY,
		BAKERY,
		BANK,
		BAR,
		//bowling_alley
		BUS_STATION,
		CASINO,
		CEMETERY,
		CHURCH,
		DENTIST,
		DOCTOR,
		FIRE_STATION,
		FLORIST,
		GAS_STATION,
		GROCERY_OR_SUPERMARKET,
		GYM,
		//hair_care
		//hardware_store
		//health
		HINDU_TEMPLE,
		//home_goods_store
		HOSPITAL,
		//insurance_agency
		JEWELRY_STORE,
		LAUNDRY,
		LAWYER,
		LIBRARY,
		//liquor_store
		//local_government_office
		//locksmith
		//lodging
		//meal_delivery
		//meal_takeaway
		MOSQUE,
		//movie_rental
		MOVIE_THEATER,
		//moving_company
		MUSEUM,
		NIGHT_CLUB,
		//painter
		PARK,
		PARKING,
		//pet_store
		PHARMACY,
		//physiotherapist
		//place_of_worship
		PLUMBER,
		POLICE,
		//post_office
		//real_estate_agency
		RESTAURANT,
		//roofing_contractor
		//rv_park
		SCHOOL,
		//shoe_store
		SHOPPING_MALL,
		//spa
		//stadium
		//storage
		//store
		SUBWAY_STATION,
		SYNAGOGUE,
		TAXI_STAND,
		TRAIN_STATION,
		//travel_agency
		UNIVERSITY,
		//veterinary_care
		ZOO
	}
	
	public CountObject count;	
	public PlaceCategory placeCategory;	
	private INominalObject of;	
	private IVerbalObject which;
	
	@Override
	public PlaceType getPlaceType() {
		return PlaceType.PLACE_TYPE;
	}
	
	@Override
	public GroupType getGroupType() {
		return GroupType.PLACE_TYPE;
	}

	@Override
	public void setNominalSecondObject(INominalObject nObject) {
		of = nObject;
	}

	@Override
	public void setVerbalSecondObject(IVerbalObject vObject) {
		which = vObject;
	}

	@Override
	public INominalObject getNominalSecondObject() {
		return of;
	}

	@Override
	public IVerbalObject getVerbalSecondObject() {
		return which;
	}

	
}
