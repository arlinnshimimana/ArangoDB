package nl.hu.v1wac2.Arango2;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.Protocol;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CollectionEntity;
import com.arangodb.model.AqlQueryOptions;
import com.arangodb.util.MapBuilder;
import com.arangodb.velocypack.VPackSlice;
import com.arangodb.velocypack.exception.VPackException;

public class arangobasedao {
	private static ArangoDB arango;
	
		public arangobasedao() {
		arango = new ArangoDB.Builder().user("root").password("admin").build();
		}
		
		public ArangoDB getconnetion() {
			return arango;
		
		}
		

}