package nl.hu.v1wac2.Arango2;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.BaseDocument;
import com.arangodb.util.MapBuilder;

public class ovchipkaartimpl extends arangobasedao {
	private String dbName = "_system";
	private String collectionName = "ovchipkaart";

	public List<Ovchipkaart> findall() {
		ArangoDB conn = super.getconnetion();
		List<Ovchipkaart> al = new ArrayList<Ovchipkaart>();
		try {
			String query = "FOR t IN ovchipkaart RETURN t";
			ArangoCursor<BaseDocument> cursor = conn.db(dbName).query(query, null, null, BaseDocument.class);
			cursor.forEachRemaining(aDocument -> {
				Ovchipkaart o = new Ovchipkaart();
				o.setKaartNummer(Integer.valueOf((aDocument.getAttribute("kaartNummer") + "")));
				;
				try {
					o.setSaldo(NumberFormat.getInstance().parse((aDocument.getAttribute("saldo") + "")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String c = aDocument.getAttribute("klasse") + "";
				o.setKlasse(Integer.valueOf(c));
				String aa = "" + aDocument.getAttribute("geldigTot") + "";
				try {
					o.setGeldigTot(new SimpleDateFormat("yyyy-MM-dd").parse(aa));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				arangoimpl ar = new arangoimpl();

				o.setReiziger(ar.findbyid(Integer.valueOf(aDocument.getAttribute("reizigerID")+"")));
				al.add(o);
			});
		} catch (ArangoDBException e) {
			System.err.println("Failed to execute query. " + e.getMessage());
		}

		return al;
	}

	public Ovchipkaart save(Ovchipkaart ovchipkaart) {
		ArangoDB conn = super.getconnetion();
		String id = "" + ovchipkaart.getKaartNummer() + "";
		BaseDocument myObject = new BaseDocument();
		myObject.addAttribute("kaartNummer", ovchipkaart.getKaartNummer());
		myObject.addAttribute("klasse", ovchipkaart.getKlasse());
		myObject.addAttribute("saldo", ovchipkaart.getSaldo());
		myObject.addAttribute("geldigTot", ovchipkaart.getGeldigTot());
		myObject.addAttribute("reizigerID", ovchipkaart.getReiziger().getId());
		try {
			conn.db(dbName).collection(collectionName).insertDocument(myObject);
			System.out.println("Document created");
		} catch (ArangoDBException e) {
			System.err.println("Failed to create document. " + e.getMessage());
		}
		return ovchipkaart;

	}


	public void del(int kaartnummer) {
		ArangoDB conn = super.getconnetion();
		try {
			  String query = "FOR t IN ovchipkaart FILTER t.kaartNummer == @kaartNummer Remove t in ovchipkaart";
			  Map<String, Object> bindVars = new MapBuilder().put("kaartNummer", kaartnummer+"").get();
			  ArangoCursor<BaseDocument> cursor = conn.db(dbName).query(query, bindVars, null,
			      BaseDocument.class);
			  cursor.forEachRemaining(aDocument -> {
			    System.out.println("deleted");
			  });
			} catch (ArangoDBException e) {
			  System.err.println("Failed to execute query. " + e.getMessage());
			}

	}

	public Ovchipkaart findbyKaartnummer(int kaartnummer) {
		ArangoDB conn = super.getconnetion();
		  Ovchipkaart o = new Ovchipkaart();

		try {
			String query = "FOR t IN ovchipkaart filter t.kaartNummer == @kaartNummer RETURN t";
			  Map<String, Object> bindVars = new MapBuilder().put("kaartNummer", kaartnummer+"").get();

			 ArangoCursor<BaseDocument> cursor = conn.db(dbName).query(query, bindVars, null,
				      BaseDocument.class);
			
			  cursor.forEachRemaining(aDocument -> {
					o.setKaartNummer(Integer.valueOf((aDocument.getAttribute("kaartNummer") + "")));
					;
					try {
						o.setSaldo(NumberFormat.getInstance().parse((aDocument.getAttribute("saldo") + "")));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String c = aDocument.getAttribute("klasse") + "";
					o.setKlasse(Integer.valueOf(c));
					String aa = "" + aDocument.getAttribute("geldigTot") + "";
					try {
						o.setGeldigTot(new SimpleDateFormat("yyyy-MM-dd").parse(aa));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				arangoimpl ar = new arangoimpl();

					arangoimpl ar1 = new arangoimpl();
					o.setReiziger(ar1.findbyid(Integer.valueOf(aDocument.getAttribute("reizigerID") + "")));
				  });
				} catch (ArangoDBException e) {
				  System.err.println("Failed to execute query. " + e.getMessage());
				}			
		return o;
	}

	public ArrayList<Ovchipkaart> findbyreiziger(Reiziger r) {
		// TODO Auto-generated method stub
		ArangoDB conn = super.getconnetion();
		 ArrayList< Ovchipkaart> aso = new ArrayList<Ovchipkaart>();

		try {
			String query = "FOR t IN ovchipkaart filter t.reizigerID == @reizigerID RETURN t";
			  Map<String, Object> bindVars = new MapBuilder().put("reizigerID", r.getId()).get();

			 ArangoCursor<BaseDocument> cursor = conn.db(dbName).query(query, bindVars, null,
				      BaseDocument.class);
			
			  cursor.forEachRemaining(aDocument -> {
				  Ovchipkaart o = new Ovchipkaart();
					o.setKaartNummer(Integer.valueOf((aDocument.getAttribute("kaartNummer") + "")));
					;
					try {
						o.setSaldo(NumberFormat.getInstance().parse((aDocument.getAttribute("saldo") + "")));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String c = aDocument.getAttribute("klasse") + "";
					o.setKlasse(Integer.valueOf(c));
					String aa = "" + aDocument.getAttribute("geldigTot") + "";
					try {
						o.setGeldigTot(new SimpleDateFormat("yyyy-MM-dd").parse(aa));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				arangoimpl ar = new arangoimpl();

					o.setReiziger(r);
					aso.add(o);
				  });
				} catch (ArangoDBException e) {
				  System.err.println("Failed to execute query. " + e.getMessage());
				}			
		return aso;
	}

}
