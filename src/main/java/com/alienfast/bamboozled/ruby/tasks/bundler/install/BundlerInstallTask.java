package com.alienfast.bamboozled.ruby.tasks.bundler.install;

import java.util.List;

import com.alienfast.bamboozled.ruby.rt.RubyLabel;
import com.alienfast.bamboozled.ruby.rt.RubyLocator;
import com.alienfast.bamboozled.ruby.rt.RubyRuntime;
import com.alienfast.bamboozled.ruby.rt.RuntimeLocatorException;
import com.alienfast.bamboozled.ruby.tasks.AbstractRubyTask;
import com.atlassian.bamboo.configuration.ConfigurationMap;

/**
 * Bamboo task which interfaces with RVM and runs bundler to install the gems required by the project.
 */
public class BundlerInstallTask extends AbstractRubyTask {

    public static final String PATH = "path";
    public static final String BIN_STUBS = "binstubs";

    @Override
    protected List<String> buildCommandList( RubyLabel rubyRuntimeLabel, ConfigurationMap config ) throws RuntimeLocatorException {


        final String path = config.get( PATH );
        final String binStubsFlag = config.get( BIN_STUBS );

        final RubyLocator rubyLocator = getRubyLocator( rubyRuntimeLabel.getRubyRuntimeManager() );
        final RubyRuntime rubyRuntime = rubyLocator.getRubyRuntime( rubyRuntimeLabel.getRubyRuntime() );
        final String rubyExecutablePath = getRubyExecutablePath( rubyRuntimeLabel );

        return new BundlerInstallCommandBuilder(getCapabilityContext(), rubyLocator, rubyRuntime, rubyExecutablePath )
                .addRubyExecutable()
                .addBundleExecutable()
                .addInstall()
                .addPath( path )
                .addIfBinStubs( binStubsFlag )
                .build();
    }

}
