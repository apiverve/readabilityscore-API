// Converter.java

// To use this code, add the following Maven dependency to your project:
//
//
//     com.fasterxml.jackson.core     : jackson-databind          : 2.9.0
//     com.fasterxml.jackson.datatype : jackson-datatype-jsr310   : 2.9.0
//
// Import this package:
//
//     import com.apiverve.data.Converter;
//
// Then you can deserialize a JSON string with
//
//     TextReadabilityScoreData data = Converter.fromJsonString(jsonString);

package com.apiverve.readabilityscore.data;

import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Converter {
    // Date-time helpers

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_INSTANT)
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetDateTime parseDateTimeString(String str) {
        return ZonedDateTime.from(Converter.DATE_TIME_FORMATTER.parse(str)).toOffsetDateTime();
    }

    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_TIME)
            .parseDefaulting(ChronoField.YEAR, 2020)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetTime parseTimeString(String str) {
        return ZonedDateTime.from(Converter.TIME_FORMATTER.parse(str)).toOffsetDateTime().toOffsetTime();
    }
    // Serialize/deserialize helpers

    public static TextReadabilityScoreData fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(TextReadabilityScoreData obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return Converter.parseDateTimeString(value);
            }
        });
        mapper.registerModule(module);
        reader = mapper.readerFor(TextReadabilityScoreData.class);
        writer = mapper.writerFor(TextReadabilityScoreData.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}

// TextReadabilityScoreData.java

package com.apiverve.readabilityscore.data;

import com.fasterxml.jackson.annotation.*;

public class TextReadabilityScoreData {
    private TextCounts textCounts;
    private Readability readability;
    private ReadabilityText readabilityText;

    @JsonProperty("textCounts")
    public TextCounts getTextCounts() { return textCounts; }
    @JsonProperty("textCounts")
    public void setTextCounts(TextCounts value) { this.textCounts = value; }

    @JsonProperty("readability")
    public Readability getReadability() { return readability; }
    @JsonProperty("readability")
    public void setReadability(Readability value) { this.readability = value; }

    @JsonProperty("readabilityText")
    public ReadabilityText getReadabilityText() { return readabilityText; }
    @JsonProperty("readabilityText")
    public void setReadabilityText(ReadabilityText value) { this.readabilityText = value; }
}

// Readability.java

package com.apiverve.readabilityscore.data;

import com.fasterxml.jackson.annotation.*;

public class Readability {
    private double fleschReadingEase;
    private double fleschKincaidGrade;
    private double gunningFog;
    private double colemanLiauIndex;
    private double smogIndex;
    private double automatedReadabilityIndex;
    private long daleChallReadabilityScore;

    @JsonProperty("fleschReadingEase")
    public double getFleschReadingEase() { return fleschReadingEase; }
    @JsonProperty("fleschReadingEase")
    public void setFleschReadingEase(double value) { this.fleschReadingEase = value; }

    @JsonProperty("fleschKincaidGrade")
    public double getFleschKincaidGrade() { return fleschKincaidGrade; }
    @JsonProperty("fleschKincaidGrade")
    public void setFleschKincaidGrade(double value) { this.fleschKincaidGrade = value; }

    @JsonProperty("gunningFog")
    public double getGunningFog() { return gunningFog; }
    @JsonProperty("gunningFog")
    public void setGunningFog(double value) { this.gunningFog = value; }

    @JsonProperty("colemanLiauIndex")
    public double getColemanLiauIndex() { return colemanLiauIndex; }
    @JsonProperty("colemanLiauIndex")
    public void setColemanLiauIndex(double value) { this.colemanLiauIndex = value; }

    @JsonProperty("smogIndex")
    public double getSmogIndex() { return smogIndex; }
    @JsonProperty("smogIndex")
    public void setSmogIndex(double value) { this.smogIndex = value; }

    @JsonProperty("automatedReadabilityIndex")
    public double getAutomatedReadabilityIndex() { return automatedReadabilityIndex; }
    @JsonProperty("automatedReadabilityIndex")
    public void setAutomatedReadabilityIndex(double value) { this.automatedReadabilityIndex = value; }

    @JsonProperty("daleChallReadabilityScore")
    public long getDaleChallReadabilityScore() { return daleChallReadabilityScore; }
    @JsonProperty("daleChallReadabilityScore")
    public void setDaleChallReadabilityScore(long value) { this.daleChallReadabilityScore = value; }
}

// ReadabilityText.java

package com.apiverve.readabilityscore.data;

import com.fasterxml.jackson.annotation.*;

public class ReadabilityText {
    private String fleschReadingEase;
    private String daleChallReadabilityScore;

    @JsonProperty("fleschReadingEase")
    public String getFleschReadingEase() { return fleschReadingEase; }
    @JsonProperty("fleschReadingEase")
    public void setFleschReadingEase(String value) { this.fleschReadingEase = value; }

    @JsonProperty("daleChallReadabilityScore")
    public String getDaleChallReadabilityScore() { return daleChallReadabilityScore; }
    @JsonProperty("daleChallReadabilityScore")
    public void setDaleChallReadabilityScore(String value) { this.daleChallReadabilityScore = value; }
}

// TextCounts.java

package com.apiverve.readabilityscore.data;

import com.fasterxml.jackson.annotation.*;

public class TextCounts {
    private long syllableCount;
    private long lexiconCount;
    private long sentenceCount;

    @JsonProperty("syllableCount")
    public long getSyllableCount() { return syllableCount; }
    @JsonProperty("syllableCount")
    public void setSyllableCount(long value) { this.syllableCount = value; }

    @JsonProperty("lexiconCount")
    public long getLexiconCount() { return lexiconCount; }
    @JsonProperty("lexiconCount")
    public void setLexiconCount(long value) { this.lexiconCount = value; }

    @JsonProperty("sentenceCount")
    public long getSentenceCount() { return sentenceCount; }
    @JsonProperty("sentenceCount")
    public void setSentenceCount(long value) { this.sentenceCount = value; }
}