package pirate;

import scs.comp5903.cucumber.EasyCucumber;
import scs.comp5903.cucumber.execution.JFeature;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class runner {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Path single_feature= Paths.get("src/test/resources/SinglePlayerScoring.feature");
        JFeature jFeature_single_feature= EasyCucumber.build(single_feature,new easyCucumber());

        Path fc_feature= Paths.get("src/test/resources/FortuneCardTest.feature");
        JFeature jFeature_fc= EasyCucumber.build(fc_feature,easyCucumber.class);
//
//        Path multi_feature= Paths.get("src/test/resources/PartThree.feature");
//        JFeature jFeature_multi= EasyCucumber.build(multi_feature,easyCucumber.class);
//        jFeature_single_feature.executeAll();
        jFeature_fc.executeAll();

    }
}
