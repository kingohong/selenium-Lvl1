package com.tests.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

public class JsonDataProvider {

    @DataProvider(name = "JsonData")
    public static Object[][] getTestData(Method method) throws IOException {
        String testName = method.getName();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("src/test/resources/dataset.json"));
        JsonNode testData = root.get(testName);

        if (testData == null || !testData.isArray()) {
            throw new RuntimeException("Test data not found or invalid format for: " + testName);
        }

        Object[][] data = new Object[testData.size()][1];
        int index = 0;
        for (Iterator<JsonNode> it = testData.iterator(); it.hasNext(); ) {
            data[index++][0] = it.next();
        }

        return data;
    }
}
