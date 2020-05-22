package com.usermind.usermindsdk;

import com.usermind.tracking.TrackingLog;
import com.usermind.usermindsdk.normalization.Normalizer;

import java.io.*;

public class TestUtils {

    private TestUtils() {}

    public static BufferedReader stringToBufferedReader(String input) {
        Reader reader = new StringReader(input);
        return new BufferedReader(reader);
    }

    public static String normalize(String entityName, String dataToNormalize, Normalizer normalizer) throws IOException {
        String flattenedData;
        try (Reader entityReader = new StringReader(dataToNormalize);
             BufferedReader bufferedReader = new BufferedReader(entityReader);
             StringWriter stringWriter = new StringWriter();
             BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);
             StringWriter stringWriterForInvalidRecords = new StringWriter();
             BufferedWriter bufferedWriterInvalidRecords = new BufferedWriter(stringWriterForInvalidRecords);) {

            normalizer.normalizeData(entityName, bufferedReader, bufferedWriter, bufferedWriterInvalidRecords);
            bufferedWriter.flush();
            flattenedData = stringWriter.toString();
        }
        return flattenedData;
    }


}
