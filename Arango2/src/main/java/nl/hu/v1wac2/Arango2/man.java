package nl.hu.v1wac2.Arango2;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class man {

	public static void main(String[] args) throws ParseException {
		arangoimpl ar = new arangoimpl();
		ovchipkaartimpl ov = new ovchipkaartimpl();
		
		System.out.println(ar.getconnetion());
		Ovchipkaart o = new Ovchipkaart();
		o.setKaartNummer(23232424);
		o.setKlasse(3);
		o.setSaldo(24.23);
		o.setGeldigTot(new SimpleDateFormat("dd-MM-yyyy").parse("18-09-2004"));
		
		Reiziger r = new Reiziger();
		r.setId(9);
		r.setNaam("arlin");
		r.setVoorletter("a");
		r.setGbdatum(new SimpleDateFormat("yyyy-MM-dd").parse("2004-01-03"));;
		
		//ov.save(o);
		//ar.create(r);
		//System.out.println(ov.del(57401);
		System.out.println(ar.save(r));
		System.out.println(ar.findall());
		
	}

}
