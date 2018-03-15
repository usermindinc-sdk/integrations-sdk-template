package com.usermind.usermindsdk.dropwizard.urlhandlers;

import com.usermind.usermindsdk.dropwizard.WorkerConfiguration;
import io.dropwizard.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RootView extends View {

    private final WorkerConfiguration config;

    @Autowired
    public RootView(WorkerConfiguration config) {
        super("/views/ftl/rootTemplate.ftl");
        this.config = config;
    }

    public long getThreads() {
        return Thread.activeCount();
    }
}
