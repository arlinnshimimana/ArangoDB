package nl.hu.v1wac2.Arango2;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.BaseDocument;
import com.arangodb.util.MapBuilder;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class arangoimpl extends arangobasedao {
	private String dbName = "_system";
	private String collectionName = "reiziger";

	ovchipkaartimpl da = new ovchipkaartimpl();

	public List<Reiziger> findall() {
		ArangoDB conn = super.getconnetion();
		List<Reiziger> al = new ArrayList<Reiziger>();
		try {
			String query = "FOR t IN reiziger RETURN t";
			ArangoCursor<BaseDocument> cursor = conn.db(dbName).query(query, null, null, BaseDocument.class);
			cursor.forEachRemaining(aDocument -> {
				System.out.println("Key: " + aDocument.getKey());
				Reiziger r = new Reiziger();
				r.setId(Integer.valueOf(aDocument.getAttribute("reizigersID") + ""));
				r.setNaam(aDocument.getAttribute("achternaam") + "");
				r.setVoorletter(aDocument.getAttribute("voorletters") + "");
				String aa = "" + aDocument.getAttribute("gebortedatum") + "";
				try {
					r.setGbdatum(new SimpleDateFormat("yyyy-mm-dd").parse(aa));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ArrayList<Ovchipkaart> s = da.findbyreiziger(r);
				r.setMijnOvchipkaarten(s);
				al.add(r);
			});
		} catch (ArangoDBException e) {
			System.err.println("Failed to execute query. " + e.getMessage());
		}

		return al;
	}

	public Reiziger save(Reiziger reiziger) {
		ArangoDB conn = super.getconnetion();
		BaseDocument myObject = new BaseDocument();
		myObject.addAttribute("reizigerID", reiziger.getId());
		myObject.addAttribute("voorletter", reiziger.getVoorletter());
		myObject.addAttribute("achternaam", reiziger.getNaam());
		myObject.addAttribute("geboortedatum", reiziger.getGbdatum());
		try {
			conn.db(dbName).collection(collectionName).insertDocument(myObject);
			System.out.println("Document created");
		} catch (ArangoDBException e) {
			System.err.println("Failed to create document. " + e.getMessage());
		}
		return reiziger;

	}

	public void delete1(int reizigerID) {
		ArangoDB conn = super.getconnetion();
		String query = "FOR t IN reiziger FILTER t.reizigersID == @reizigersID Remove t in reiziger";
		Map<String, Object> bindVars = new MapBuilder().put("reizigersID", reizigerID).get();
		ArangoCursor<BaseDocument> cursor = conn.db(dbName).query(query, bindVars, null, BaseDocument.class);
		cursor.forEachRemaining(aDocument -> {
		});
	}

	public Reiziger findbyid(int id) {
		ArangoDB conn = super.getconnetion();
		Reiziger r = new Reiziger();
		try {
			String query = "FOR t IN reiziger FILTER t.reizigersID == @reizigersID RETURN t";
			Map<String, Object> bindVars = new MapBuilder().put("reizigersID", id).get();
			ArangoCursor<BaseDocument> cursor = conn.db(dbName).query(query, bindVars, null, BaseDocument.class);
			cursor.forEachRemaining(aDocument -> {
				System.out.println("Key: " + aDocument.getKey());
				r.setId(id);
				r.setNaam(aDocument.getAttribute("achternaam") + "");
				r.setVoorletter(aDocument.getAttribute("voorletters") + "");
				String a = "" + aDocument.getAttribute("gebortedatum") + "";
				r.setGbdatum(java.sql.Date.valueOf(a));
			});
		} catch (ArangoDBException e) {
			System.err.println("Failed to execute query. " + e.getMessage());
		}

		return r;
	}

	public Reiziger update(Reiziger reiziger) {
		ArangoDB conn = super.getconnetion();
		try {
			String query = "FOR t IN reiziger FILTER t.reizigersID == @reizigersID Update t with {achternaam:" + '"'
					+ reiziger.getNaam() + '"' + ",voorletters:" + '"' + reiziger.getVoorletter() + '"'
					+ ",gebortedatum: " + '"' + reiziger.getGbdatum() + '"' + " } in reiziger";
			Map<String, Object> bindVars = new MapBuilder().put("reizigersID", reiziger.getId()).get();
			ArangoCursor<BaseDocument> cursor = conn.db(dbName).query(query, bindVars, null, BaseDocument.class);
			cursor.forEachRemaining(aDocument -> {
			});
		} catch (ArangoDBException e) {
			System.err.println("Failed to execute query. " + e.getMessage());
		}

		return reiziger;
	}
}