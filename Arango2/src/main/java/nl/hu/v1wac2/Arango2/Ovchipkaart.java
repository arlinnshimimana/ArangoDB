package nl.hu.v1wac2.Arango2;

import java.util.Date;

public class Ovchipkaart {
	private int kaartNummer;
	private Date geldigTot;
	private int klasse;
	private Number saldo;
	private Reiziger reiziger;
	
	public Ovchipkaart(int kn, Date gld, int kl, Number sal) {
		kaartNummer= kn;
		geldigTot = gld;
		klasse = kl;
		saldo = sal;
	}
	public Ovchipkaart() {};

	public Reiziger getReiziger() {
		return reiziger;
	}

	public void setReiziger(Reiziger reiziger) {
		this.reiziger = reiziger;
	}

	public int getKaartNummer() {
		return kaartNummer;
	}

	public void setKaartNummer(int kaartNummer) {
		this.kaartNummer = kaartNummer;
	}

	public Date getGeldigTot() {
		return geldigTot;
	}

	public void setGeldigTot(Date geldigTot) {
		this.geldigTot = geldigTot;
	}

	public int getKlasse() {
		return klasse;
	}

	public void setKlasse(int klasse) {
		this.klasse = klasse;
	}

	public Number getSaldo() {
		return saldo;
	}

	public void setSaldo(Number saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Ovchipkaart [kaartNummer=" + kaartNummer + ", geldigTot=" + geldigTot + ", klasse=" + klasse
				+ ", saldo=" + saldo + ", reiziger= "+ reiziger.getId()+"]";
	}

	
	
	

}
