package ch.fhnw.eaf.rental.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import ch.fhnw.eaf.rental.model.PriceCategory;

public class PriceCategoryDeserializer extends StdDeserializer<PriceCategory> {
	private static final long serialVersionUID = -9048630333978322298L;

	public PriceCategoryDeserializer() {
		this(null);
	}

	public PriceCategoryDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public PriceCategory deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		String type = node.get("type").asText();
		try {
			PriceCategory pc = (PriceCategory) Class.forName(type).getDeclaredConstructor().newInstance();
			long id = (Integer) ((IntNode) node.get("id")).numberValue();
			pc.setId(id);
			return pc;
		} catch (Exception e) {
			return null;
		}
	}
}