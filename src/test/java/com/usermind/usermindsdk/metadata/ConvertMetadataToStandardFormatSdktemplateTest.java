package com.usermind.usermindsdk.metadata;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.usermind.usermindsdk.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ConvertMetadataToStandardFormatSdktemplateTest extends TestBase {

    private ConvertMetadataToStandardFormatSdktemplate converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new ConvertMetadataToStandardFormatSdktemplate(objectMapper);
    }

    @Test
    void testHardcodedEntities() throws Exception {
        String sampleInput = loadFileFixtureAsString("MetadataCreatedInCode.json");

        Map<String, MetadataRecords> results = converter.processMetaData(sampleInput);
        assertThat(results.get("TemplateList")).isNotNull();
        return;
    }

    @Test
    void testMetadataFromIntegration() throws Exception {
        String metadata = loadFileFixtureAsString("MetadataFromIntegration.json");

        Map<String, MetadataRecords> results = converter.processMetaData(metadata);

        //Check some data - pull one entity from the map
        MetadataRecords record = results.get("An EntityName");
        assertThat(record.getPrimaryKey()).isEmpty();

        //Check some records of different types
        MetadataRecordItem item = record.getRecords().stream().filter(r->r.getFieldName().equals("Name Of String Field")).findAny().orElse(null);
        assertThat(item.getType()).isEqualTo(MetadataTypes.STRING);
        assertThat(item.isRequired()).isFalse();

        item = record.getRecords().stream().filter(r->r.getFieldName().equals("Number Field")).findAny().orElse(null);
        assertThat(item.getType()).isEqualTo(MetadataTypes.NUMBER);
        assertThat(item.isRequired()).isTrue();

        item = record.getRecords().stream().filter(r->r.getFieldName().equals("Boolean Field")).findAny().orElse(null);
        assertThat(item.getType()).isEqualTo(MetadataTypes.BOOLEAN);
        assertThat(item.isRequired()).isFalse();

        item = record.getRecords().stream().filter(r->r.getFieldName().equals("Date Field")).findAny().orElse(null);
        assertThat(item.getType()).isEqualTo(MetadataTypes.DATE);
        assertThat(item.isRequired()).isTrue();

        //And repeat that for one or two other entities if desired

    }

}
