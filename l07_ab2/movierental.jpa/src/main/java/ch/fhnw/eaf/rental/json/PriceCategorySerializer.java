package ch.fhnw.eaf.rental.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import ch.fhnw.eaf.rental.model.PriceCategory;

public class PriceCategorySerializer extends StdSerializer<PriceCategory> {
	private static final long serialVersionUID = 8458153172143431216L;

	public PriceCategorySerializer() {
		this(null);
	}

	public PriceCategorySerializer(Class<PriceCategory> t) {
		super(t);
	}

	@Override
	public void serialize(PriceCategory value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {

		jgen.writeStartObject();
		jgen.writeNumberField("id", value.getId());
		jgen.writeStringField("type", value.getClass().getName());
		jgen.writeEndObject();
	}
}