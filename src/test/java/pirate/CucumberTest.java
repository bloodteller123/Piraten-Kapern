package pirate;

import org.junit.jupiter.api.Test;
import scs.comp5903.cucumber.EasyCucumber;
import scs.comp5903.cucumber.execution.JFeature;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CucumberTest {
//    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
    @Test
    public void cucumberTest() throws InvocationTargetException, IllegalAccessException {
        Path single_feature= Paths.get("src/test/resources/SinglePlayerScoring.feature");
        JFeature jFeature_single_feature= EasyCucumber.build(single_feature,new EasyCucumberSD());

        Path fc_feature= Paths.get("src/test/resources/FortuneCardTest.feature");
        JFeature jFeature_fc= EasyCucumber.build(fc_feature,EasyCucumberSD.class);
//
        Path multi_feature= Paths.get("src/test/resources/PartThree.feature");
        JFeature jFeature_multi= EasyCucumber.build(multi_feature,EasyCucumberSD.class);

        jFeature_single_feature.executeAll();
        jFeature_fc.executeAll();
        jFeature_multi.executeAll();
    }
}
