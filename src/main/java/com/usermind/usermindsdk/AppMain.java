package com.usermind.usermindsdk;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.usermind.usermindsdk.dropwizard.DropWizardService;
import org.slf4j.LoggerFactory;

public class AppMain {

    //TODO - integrate with SignalFX for metrics https://app.signalfx.com/#/dashboard/DZUNGRxAYAA
    public static void main(String[] args) throws Exception {

        //Swagger throws a lot of reflection errors looking for APIs ... this just turns those off
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.reflections");
        rootLogger.setLevel(ch.qos.logback.classic.Level.INFO);

        new DropWizardService().run(args);
    }


}
