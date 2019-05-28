package nl.hu.v1wac2.Arango2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reiziger {
	private int reizigerID;
	private String voorletter;
	private String naam;
	private Date gbdatum;
	private ArrayList<Ovchipkaart> mijnOvchipkaarten= new ArrayList<>();

	
	public Reiziger(int id,String nm, Date gb, String vrl) {
		reizigerID = id;
		voorletter = vrl;
		naam = nm;
		gbdatum = gb;
	}

	
	public Reiziger() {};
	
	public Ovchipkaart addKaarten(Ovchipkaart a) {
		mijnOvchipkaarten.add(a);
		return a;
	}

	public ArrayList<Ovchipkaart> getMijnOvchipkaarten() {
		return mijnOvchipkaarten;
	}

	public void setMijnOvchipkaarten(ArrayList<Ovchipkaart> mijnOvchipkaarten) {
		this.mijnOvchipkaarten = mijnOvchipkaarten;
	}
	
	public int getId() {
		return reizigerID;
	}
	public void setId(int a) {
		this.reizigerID = a;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Date getGbdatum() {
		return gbdatum;
	}

	public void setGbdatum(Date gbdatum) {
		this.gbdatum = gbdatum;
	}
	
	public String toString() {
		String ovchipkaarten= "";
		for(int i = 0; i < this.mijnOvchipkaarten.size(); i++) {
			ovchipkaarten += this.mijnOvchipkaarten.get(i);
		}
		return naam+" geboren "+gbdatum+"["+ovchipkaarten+"]";
	}
	public ArrayList<Ovchipkaart> getMijnOvchipkaart() {
		return mijnOvchipkaarten;
	}

	public String getVoorletter() {
		return voorletter;
	}
	public void setVoorletter(String voorletters) {
		this.voorletter = voorletters;
	}
}
