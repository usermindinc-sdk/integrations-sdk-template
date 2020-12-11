package com.usermind.usermindsdk;

import com.usermind.tracking.TrackingLog;
import com.usermind.usermindsdk.normalization.Normalizer;

import java.io.*;

public class TestUtils {

    private static TrackingLog.TrackingLogBuilder trackingLog = TrackingLog.builder();
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
             StringWriter stringWriterForInvalidRecords = new StringWriter();
             BufferedWriter bufferedWriterForInvalidRecords = new BufferedWriter(stringWriterForInvalidRecords);
             BufferedWriter bufferedWriter = new BufferedWriter(stringWriter)) {

            normalizer.normalizeData(entityName, bufferedReader, bufferedWriter, bufferedWriterForInvalidRecords, trackingLog);
            bufferedWriter.flush();
            flattenedData = stringWriter.toString();
        }
        return flattenedData;
    }


}
