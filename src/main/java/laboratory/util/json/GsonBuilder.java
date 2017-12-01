package laboratory.util.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.DateTime;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * <PRE>
 * Filename : GsonBuilder.java
 * Class    : GsonBuilder
 * Function :
 * Comment  :
 *
 * Copyright (c) 2016 by exanadu Corp. All Rights Reserved.
 * </PRE>
 *
 * @since 2016-12-12
 * @author fixalot
 */
public class GsonBuilder {
	private com.google.gson.GsonBuilder gsonBuilder;
	private Gson gson;

	public Gson getGson() {
		return this.gson;
	}

	public GsonBuilder() {
		this.gsonBuilder = new com.google.gson.GsonBuilder();
		registCharTypeAdapter();
		registShortTypeAdapter();
		registIntegerTypeAdapter();
		registLongTypeAdapter();
		registDoubleTypeAdapter();
		registBigDecimalTypeAdapter();
		registBooleanTypeAdapter();
//		registDateTypeAdapter();
		this.gson = this.gsonBuilder.create();
	}

	private void registCharTypeAdapter() {
		this.gsonBuilder.registerTypeAdapter(Character.class, new TypeAdapter<Character>() {
			@Override
			public Character read(JsonReader in) throws IOException {
				if (in.peek() == JsonToken.NULL) {
					in.nextNull();
					return null;
				}
				String str = in.nextString();
				if (str.isEmpty()) {
					return null;
				}
				try {
					return str.charAt(0);
				} catch (Exception e) {
					throw new JsonSyntaxException(e);
				}
			}

			@Override
			public void write(JsonWriter out, Character value) throws IOException {
				out.value(value);
			}
		});
	}

	private void registShortTypeAdapter() {
		this.gsonBuilder.registerTypeAdapter(Short.class, new TypeAdapter<Short>() {
			@Override
			public Short read(JsonReader in) throws IOException {
				if (in.peek() == JsonToken.NULL) {
					in.nextNull();
					return null;
				}
				String str = in.nextString();
				if (str.isEmpty()) {
					return null;
				}
				try {
					return new Short(str);
				} catch (Exception e) {
					throw new JsonSyntaxException(e);
				}
			}

			@Override
			public void write(JsonWriter out, Short value) throws IOException {
				out.value(value);
			}
		});
	}

	private void registIntegerTypeAdapter() {
		this.gsonBuilder.registerTypeAdapter(Integer.class, new TypeAdapter<Integer>() {
			@Override
			public Integer read(JsonReader in) throws IOException {
				if (in.peek() == JsonToken.NULL) {
					in.nextNull();
					return null;
				}
				String str = in.nextString();
				if (str.isEmpty()) {
					return null;
				}
				try {
					return new Integer(str);
				} catch (Exception e) {
					throw new JsonSyntaxException(e);
				}
			}

			@Override
			public void write(JsonWriter out, Integer value) throws IOException {
				out.value(value);
			}
		});
	}

	private void registLongTypeAdapter() {
		this.gsonBuilder.registerTypeAdapter(Long.class, new TypeAdapter<Long>() {
			@Override
			public Long read(JsonReader in) throws IOException {
				if (in.peek() == JsonToken.NULL) {
					in.nextNull();
					return null;
				}
				String str = in.nextString();
				if (str.isEmpty()) {
					return null;
				}
				try {
					return new Long(str);
				} catch (Exception e) {
					throw new JsonSyntaxException(e);
				}
			}

			@Override
			public void write(JsonWriter out, Long value) throws IOException {
				out.value(value);
			}
		});
	}

	private void registDoubleTypeAdapter() {
		this.gsonBuilder.registerTypeAdapter(Double.class, new TypeAdapter<Double>() {
			@Override
			public Double read(JsonReader in) throws IOException {
				if (in.peek() == JsonToken.NULL) {
					in.nextNull();
					return null;
				}
				String str = in.nextString();
				if (str.isEmpty()) {
					return null;
				}
				try {
					return new Double(str);
				} catch (Exception e) {
					throw new JsonSyntaxException(e);
				}
			}

			@Override
			public void write(JsonWriter out, Double value) throws IOException {
				out.value(value);
			}
		});
	}

	private void registBigDecimalTypeAdapter() {
		this.gsonBuilder.registerTypeAdapter(BigDecimal.class, new TypeAdapter<BigDecimal>() {
			@Override
			public BigDecimal read(JsonReader in) throws IOException {
				if (in.peek() == JsonToken.NULL) {
					in.nextNull();
					return null;
				}
				String str = in.nextString();
				if (str.isEmpty()) {
					return null;
				}
				try {
					return new BigDecimal(str);
				} catch (Exception e) {
					throw new JsonSyntaxException(e);
				}
			}

			@Override
			public void write(JsonWriter out, BigDecimal value) throws IOException {
				out.value(value);
			}
		});
	}

	private void registBooleanTypeAdapter() {
		this.gsonBuilder.registerTypeAdapter(Boolean.class, new TypeAdapter<Boolean>() {
			@Override
			public Boolean read(JsonReader in) throws IOException {
				if (in.peek() == JsonToken.NULL) {
					in.nextNull();
					return null;
				}
				String str = in.nextString();
				if (str.isEmpty()) {
					return null;
				}
				try {
					return Boolean.valueOf(str);
				} catch (Exception e) {
					throw new JsonSyntaxException(e);
				}
			}

			@Override
			public void write(JsonWriter out, Boolean value) throws IOException {
				out.value(value);
			}
		});
	}

	/**
	 * @author fixalot
	 * @deprecated 2016-12-14, fixalot, 받는 문자열이 어떤 포맷인지 일일이 지정해야 하는 문제 있음.
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private void registDateTypeAdapter() {
		this.gsonBuilder.registerTypeAdapter(Date.class, new TypeAdapter<Date>() {
			@Override
			public Date read(JsonReader in) throws IOException {
				if (in.peek() == JsonToken.NULL) {
					in.nextNull();
					return null;
				}
				String str = in.nextString();
				if (str.isEmpty()) {
					return null;
				}
				try {
					return DateTime.parse(str, org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
				} catch (Exception e) {
					throw new JsonSyntaxException(e);
				}
			}

			@Override
			public void write(JsonWriter out, Date value) throws IOException {
				out.value(new DateTime(value).toString(org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
			}
		});
	}
}
