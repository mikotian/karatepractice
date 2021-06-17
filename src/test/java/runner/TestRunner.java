package runner;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.junit5.Karate;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TestRunner {

    private final String FEATURES = "features";
    private final String TAGS = "tags";
    private final String PARALLEL_COUNT = "parallel";

    private final String FEATURE_PATH = "classpath:features";
    private final String SEPARATOR = ",";
    private final int parallelCount = 1;


    @Test
    public void testItems() {
        System.out.println("hello");
        Results results=Runner.path(getFeatures()).outputCucumberJson(true).tags(getTags()).parallel(this.parallelCount);

        generateReport(results.getReportDir());
    }

    public List<String> getFeatures(){
        List<String> temp = new ArrayList<>();
        if(System.getProperty(FEATURES) != null){
            if (!System.getProperty(FEATURES).isEmpty()){
                Arrays.stream(System.getProperty(FEATURES).split(SEPARATOR))
                        .forEach(x -> temp.add(FEATURE_PATH.concat(x)));
            }
        } else {
            System.out.println("-Dfeatures is not provided or is empty");
            temp.add(FEATURE_PATH);
        }
        return temp;
    }

    private List<String> getTags(){
        List<String> temp = new ArrayList<>();
        if(System.getProperty(TAGS)!=null){
            if(!System.getProperty(TAGS).isEmpty()){
                String tags = System.getProperty(TAGS).replaceAll("\\|\\|",",");
                temp = Arrays.asList(tags.split("&&"));
            }
        } else {
            System.out.println("-Dtags is not provided or is empty");
            temp.add("~@ignore");
        }
        return temp;
    }

    public static void generateReport(String karateOutputPath) {
        System.out.println(karateOutputPath);
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        final List<String> jsonPaths = new ArrayList(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "demo");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}
